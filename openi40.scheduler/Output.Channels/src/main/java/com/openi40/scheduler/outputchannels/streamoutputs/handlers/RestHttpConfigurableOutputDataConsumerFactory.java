package com.openi40.scheduler.outputchannels.streamoutputs.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputDataStreamException;
import com.openi40.scheduler.outputchannels.streamoutputs.config.EntityOutputSetting;
import com.openi40.scheduler.outputchannels.streamoutputs.config.HttpClientOutputDataConfig;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
public class RestHttpConfigurableOutputDataConsumerFactory
		extends AbstractConfigurableOutputDataConsumerFactory<EntityOutputSetting, HttpClientOutputDataConfig> {
	RestTemplate restTemplate = null;

	
	public static class ErrorMSG {
		String errorCode = null, errorDescription = null;

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorDescription() {
			return errorDescription;
		}

		public void setErrorDescription(String errorDescription) {
			this.errorDescription = errorDescription;
		}
	};

	
	public static class SaveStatus {
		boolean success = true;
		List<ErrorMSG> messages = new ArrayList<>();
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public List<ErrorMSG> getMessages() {
			return messages;
		}
		public void setMessages(List<ErrorMSG> messages) {
			this.messages = messages;
		}
	};

	public RestHttpConfigurableOutputDataConsumerFactory(HttpClientOutputDataConfig cfg, RestTemplate restTemplate) {
		super(cfg);
		this.restTemplate = restTemplate;
	}

	@Override
	public <DtoEntityType extends OutputDto> void send(HttpClientOutputDataConfig config, EntityOutputSetting cfgItem,
			List<DtoEntityType> list) {
		HttpEntity<List<DtoEntityType>> entity = new HttpEntity<List<DtoEntityType>>(list);
		String _url = cfgItem.isRelativePath() ? config.getBaseURL() + cfgItem.getPath() : cfgItem.getPath();
		ResponseEntity<SaveStatus> result = restTemplate.exchange(_url, HttpMethod.POST, entity, SaveStatus.class);
	}

	@Override
	public <DtoEntityType extends OutputDto> void send(HttpClientOutputDataConfig config, EntityOutputSetting cfgItem,
			DtoEntityType entry) {
		List<DtoEntityType> list = new ArrayList<DtoEntityType>();
		list.add(entry);
		this.send(config, cfgItem, list);

	}

	@Override
	protected EntityOutputSetting createTemplatedEntry(Class<? extends OutputDto> requiredType, String relativeString)
			throws OutputDataStreamException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
