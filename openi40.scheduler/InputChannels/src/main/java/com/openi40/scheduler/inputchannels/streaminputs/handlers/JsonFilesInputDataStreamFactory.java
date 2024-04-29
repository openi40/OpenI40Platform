package com.openi40.scheduler.inputchannels.streaminputs.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.openi40.scheduler.common.datamodel.IDataInputValidator;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;
import com.openi40.scheduler.inputchannels.streaminputs.config.EntityImportSetting;
import com.openi40.scheduler.inputchannels.streaminputs.config.JsonFilesInputConfig;
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
public class JsonFilesInputDataStreamFactory
		extends AbstractConfigurableInputDataStreamFactory<EntityImportSetting, JsonFilesInputConfig> {

	public JsonFilesInputDataStreamFactory(JsonFilesInputConfig config, ObjectMapper mapper,
			IDataInputValidator inputValidator) {
		super(config, mapper, inputValidator);

	}

	@Override
	protected InputStream resolveConfigurationEntry(JsonFilesInputConfig _config, EntityImportSetting _importSetting)
			throws InputDataStreamException {
		if (_importSetting.isNoContent())
			return new InputStream() {

				@Override
				public int read() throws IOException {

					return -1;
				}
			};
		String _path = _importSetting.isRelativePath()
				? (_config.getBaseFolder() != null ? _config.getBaseFolder() + "/" : "") + _importSetting.getPath()
				: _importSetting.getPath();
		InputStream is = getClass().getClassLoader().getResourceAsStream(_path);
		if (is == null) {
			File file = new File(_path);
			if (!file.exists())
				throw new InputDataStreamException("The file " + file.getName() + " cannot be read");
			try {
				is = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new InputDataStreamException("The file " + file.getName() + " cannot be read", e);
			}
		}
		return is;
	}

	@Override
	protected InputStream resolveConfigurationEntry(JsonFilesInputConfig _config, EntityImportSetting _importSetting,
			Date modifiedAfter) throws InputDataStreamException {

		return new InputStream() {

			@Override
			public int read() throws IOException {

				return -1;
			}
		};
	}

	@Override
	protected EntityImportSetting createTemplatedEntry(Class<? extends InputDto> requiredType,
			String relativeString) throws InputDataStreamException {
		EntityImportSetting eis = new EntityImportSetting();
		eis.setEntityName(requiredType.getSimpleName());
		eis.setNoContent(false);
		eis.setPath(relativeString);
		eis.setRelativePath(true);
		return eis;
	}

	@Override
	protected ApsInputData loadSingleSource(JsonFilesInputConfig config, Date modifiedAfter)
			throws InputDataStreamException {
		InputStream is = null;
		try {
			String _path = config.getBaseFolder() + "/" + config.getSingleApsInputUri();
			ObjectReader reader = mapper.readerFor(ApsInputData.class);
			is = getClass().getResourceAsStream(_path);
			if (is == null) {
				is = getClass().getResourceAsStream("/" + _path);
			}
			if (is == null) {
				FileInputStream fis = null;
				File file = new File(_path);
				if (!file.exists())
					throw new IllegalStateException(
							"The path:" + _path + " is not found in jar archives or as external file");
				fis = new FileInputStream(file);
				is = fis;
			}
			ApsInputData inputData = (ApsInputData) reader.readValue(is);
			return inputData;
		} catch (IOException ex) {
			throw new InputDataStreamException("Error accessing input file", ex);
		} finally {
			try {
				is.close();
			} catch (Throwable t) {
			}
		}
	}

	@Override
	public String getDataSourceDescription() {
		return this.config.getDescription();
	}

}
