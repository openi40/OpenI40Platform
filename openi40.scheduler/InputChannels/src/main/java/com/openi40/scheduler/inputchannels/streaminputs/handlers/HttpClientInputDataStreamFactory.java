package com.openi40.scheduler.inputchannels.streaminputs.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Principal;
import java.util.Date;

import org.apache.hc.client5.http.auth.AuthCache;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.Credentials;
import org.apache.hc.client5.http.auth.CredentialsProvider;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.auth.BasicAuthCache;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.auth.BasicScheme;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.entity.EmptyInputStream;
import org.apache.hc.core5.http.protocol.HttpContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.scheduler.common.datamodel.IDataInputValidator;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;
import com.openi40.scheduler.inputchannels.streaminputs.config.EntityImportSetting;
import com.openi40.scheduler.inputchannels.streaminputs.config.HttpClientInputDataConfig;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
public class HttpClientInputDataStreamFactory
		extends AbstractConfigurableInputDataStreamFactory<EntityImportSetting, HttpClientInputDataConfig> {

	public HttpClientInputDataStreamFactory(HttpClientInputDataConfig config, ObjectMapper mapper,
			IDataInputValidator inputValidator) {
		super(config, mapper, inputValidator);

	}

	private InputStream getRequestedStream(String uri) throws InputDataStreamException {
		InputStream is = null;
		try {
			URL url = new URL(uri);
			HttpHost target = new HttpHost(url.getHost(), url.getPort());
			HttpClientContext localContext = HttpClientContext.create();
			ClassicHttpRequest request = new HttpGet(uri);
			CredentialsProvider provider = null;
			if (config.isUseBasicAuthentication()) {
				provider = new BasicCredentialsProvider() {
					@Override
					public Credentials getCredentials(AuthScope authScope, HttpContext httpContext) {

						return new Credentials() {

							@Override
							public Principal getUserPrincipal() {

								return new Principal() {

									@Override
									public String getName() {

										return config.getUsername();
									}
								};
							}

							@Override
							public char[] getPassword() {

								return config.getPassword().toCharArray();
							}
						};
					}
				};
				AuthCache authCache = new BasicAuthCache();
				authCache.put(target, new BasicScheme());
				localContext.setAuthCache(authCache);
			} else if (config.getAuthenticationToken() != null) {
				request.setHeader("Authorization", "Bearer " + config.getAuthenticationToken());
			}
			CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
			CloseableHttpResponse response = httpClient.execute(target, request, localContext);
			if (response != null && response.getCode() == 200) {
				HttpEntity entity = response.getEntity();

				is = entity.getContent();

				if (is instanceof EmptyInputStream) {
					is = null;
				}
			} else if (response != null)
				throw new InputDataStreamException(
						"error accessing url=" + url + " response status=>" + response.getCode() + "");
			else
				throw new InputDataStreamException("error accessing url response with no return");

		} catch (

		IOException e) {
			throw new InputDataStreamException("error accessing url", e);
		}
		return is;
	}

	private String getURL(HttpClientInputDataConfig _config, EntityImportSetting _importSetting, Date modifiedAfter) {
		String url = null;
		if (_importSetting.isRelativePath()) {
			url = _config.getBaseURL() + _importSetting.getPath();
		} else
			url = _importSetting.getPath();

		return url;
	}

	@Override
	protected InputStream resolveConfigurationEntry(HttpClientInputDataConfig _config,
			EntityImportSetting _importSetting) throws InputDataStreamException {
		if (_importSetting.isNoContent())
			return new InputStream() {

				@Override
				public int read() throws IOException {

					return -1;
				}
			};
		String url = getURL(_config, _importSetting, null);

		return getRequestedStream(url);
	}

	@Override
	protected InputStream resolveConfigurationEntry(HttpClientInputDataConfig _config,
			EntityImportSetting _importSetting, Date modifiedAfter) throws InputDataStreamException {
		if (_importSetting.isNoContent())
			return new InputStream() {

				@Override
				public int read() throws IOException {

					return -1;
				}
			};
		String url = getURL(_config, _importSetting, modifiedAfter);
		return getRequestedStream(url);
	}

	@Override
	protected EntityImportSetting createTemplatedEntry(Class<? extends InputDto> requiredType, String relativeString)
			throws InputDataStreamException {
		EntityImportSetting eis = new EntityImportSetting();
		eis.setEntityName(requiredType.getSimpleName());
		eis.setNoContent(false);
		eis.setPath(relativeString);
		eis.setRelativePath(true);
		return eis;
	}

	@Override
	protected ApsInputData loadSingleSource(HttpClientInputDataConfig config2, Date modifiedAfter)
			throws InputDataStreamException {

		return null;
	}

	@Override
	public String getDataSourceDescription() {

		return this.config.getDescription();
	}

}
