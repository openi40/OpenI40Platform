package com.openi40.scheduler.inputchannels.streaminputs.handlers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.hc.core5.http.io.entity.EmptyInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONTokener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.openi40.scheduler.common.datamodel.IDataInputValidator;
import com.openi40.scheduler.common.datamodel.ValidationMessage;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;
import com.openi40.scheduler.inputchannels.streaminputs.config.AbstractInputDataStreamFactoryConfig;
import com.openi40.scheduler.inputchannels.streaminputs.config.EntityImportSetting;
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
public abstract class AbstractConfigurableInputDataStreamFactory<ImportEntryType extends EntityImportSetting, CType extends AbstractInputDataStreamFactoryConfig<ImportEntryType>>
		implements IInputDataStreamFactory {
	protected CType config = null;
	protected ObjectMapper mapper = null;
	protected IDataInputValidator inputValidator = null;
	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public AbstractConfigurableInputDataStreamFactory(CType config, ObjectMapper mapper,
			IDataInputValidator inputValidator) {
		this.config = config;
		this.mapper = mapper;
		this.inputValidator = inputValidator;
	}

	@Override
	public String getDataSourceName() {

		return config.getDataSourceName();
	}

	@Override
	public String getDataSetName() {

		return config.getDataSetName();
	}

	@Override
	public String getDataSetVariant() {
		return config.getDataSetVariant();
	}

	protected ImportEntryType getOrProduceConfigurationEntry(Class<? extends InputDto> requiredType, Date modifiedAfter)
			throws InputDataStreamException {
		List<ImportEntryType> found = new ArrayList<ImportEntryType>();
		config.getEntitiesSetting().forEach((x) -> {
			if (x.getEntityName() != null && x.getEntityName().equalsIgnoreCase(requiredType.getSimpleName())) {
				found.add(x);
			}
		});
		if (!found.isEmpty()) {
			return found.get(0);
		}
		if (config.isCreateURIByEntityName()) {
			String _template = config.getRelativePathTemplate();
			String _relativeString = _template != null ? _template.replace("${entity}", requiredType.getSimpleName())
					: requiredType.getSimpleName();
			if (modifiedAfter != null) {
				java.sql.Timestamp d = new java.sql.Timestamp(modifiedAfter.getTime());
				_relativeString = _relativeString.replace("${modifiedAfter}", URLEncoder.encode(d.toString()));
			}
			return createTemplatedEntry(requiredType, _relativeString);
		}
		throw new InputDataStreamException("no configuration for class: " + requiredType.getSimpleName());
	}

	protected abstract ImportEntryType createTemplatedEntry(Class<? extends InputDto> requiredType,
			String relativeString) throws InputDataStreamException;

	protected abstract InputStream resolveConfigurationEntry(CType _config, ImportEntryType _importSetting)
			throws InputDataStreamException;

	protected abstract InputStream resolveConfigurationEntry(CType _config, ImportEntryType _importSetting,
			Date modifiedAfter) throws InputDataStreamException;

	protected class SpliteratorImpl<DtoEntityType extends InputDto> implements Spliterator<DtoEntityType> {
		ObjectReader reader = null;
		InputStream is = null;
		Class<DtoEntityType> requiredType = null;
		ImportEntryType configuration = null;

		public SpliteratorImpl(ImportEntryType configuration, InputStream is, Class<DtoEntityType> requiredType) {
			this.requiredType = requiredType;
			this.is = is;
			this.configuration = configuration;
		}

		static final int openParentesis = '{';
		static final int closedParentesis = '}';

		InputStream cacheJSONObjectContent(InputStream is) throws IOException {
			InputStream ois = null;

			int lastRead = 0;
			int depth = 0;
			// position on first open graph parentesis
			while ((lastRead = is.read()) != openParentesis) {
				if (lastRead == -1)
					throw new EOFException("End of stream reached while reading content");
			}
			if (lastRead == openParentesis) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				do {
					// start to compute parentesis balance
					if (lastRead == openParentesis) {
						depth++;
					}
					// if closed parentesis parentesis balance decrement
					if (lastRead == closedParentesis) {
						depth--;
					}
					bos.write(lastRead);
					if (depth != 0) {
						lastRead = is.read();
						if (lastRead < 0) {
							throw new EOFException("End of stream reached while reading content");
						}
					}
				} while (depth != 0 && lastRead >= 0);
				if (depth == 0) {
					bos.flush();
					bos.close();
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Extracted JSON fragment:"+bos.toString("UTF-8"));
					}
					ois = new ByteArrayInputStream(bos.toByteArray());
				}
			}
			return ois;
		}

		@Override
		public boolean tryAdvance(Consumer<? super DtoEntityType> action) {
			try {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Begin tryAdvance(..) for " + requiredType.getName());
				}
				boolean readObject = false;
				if (this.configuration.isMultipleObjectsAsArray()) {
					if (this.reader == null) {
						int firstRead = is.read();
						if (firstRead != '[') {
							throw new RuntimeException("Expected '[' at beginning of readed stream");
						}

						this.reader = mapper.readerFor(requiredType);

						readObject = true;
					} else {
						int firstRead = is.read();
						readObject = firstRead == ',';
					}
				} else {
					if (this.reader == null) {
						this.reader = mapper.readerFor(requiredType);
					}
					readObject = true;
				}
				
				if (readObject) {
					InputStream bufferedIS = cacheJSONObjectContent(is);
					
					DtoEntityType eType = (DtoEntityType) mapper.readValue(bufferedIS, requiredType);;
					if (LOGGER.isDebugEnabled() && eType != null) {
						LOGGER.debug("Read object==>" + mapper.writeValueAsString(eType));
					}
					action.accept(eType);
				}
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("End tryAdvance(..) for " + requiredType.getName());
				}
				return readObject;
			} catch (org.apache.hc.core5.http.StreamClosedException exc) {
				return false;
			} catch (EOFException exc) {
				return false;
			} catch (IOException exc) {
				LOGGER.error("Error reading from inputStream",exc);
				
				throw new RuntimeException("unable to advance", exc);
			}
		}

		@Override
		public Spliterator<DtoEntityType> trySplit() {

			return null;
		}

		@Override
		public long estimateSize() {

			return -1;
		}

		@Override
		public int characteristics() {

			return IMMUTABLE;
		}
	};

	protected <DtoEntityType extends InputDto> Stream<DtoEntityType> getStreamFromInput(
			ImportEntryType entityConfiguration, Class<DtoEntityType> requiredType, InputStream is) throws IOException {
		if (is == null)
			return new ArrayList<DtoEntityType>().stream();
		return StreamSupport.stream(new SpliteratorImpl<DtoEntityType>(entityConfiguration, is, requiredType), false);
	}

	ApsInputDataStreamFactory wrappedSingleSource = null;
	TreeMap<Date, ApsInputDataStreamFactory> cachedObjects = new TreeMap<Date, ApsInputDataStreamFactory>();

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType)
			throws InputDataStreamException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin getStream(" + requiredType.getName() + ")");
		}
		if (config.getSingleApsInputUri() != null && wrappedSingleSource == null) {
			ApsInputData apsInput = loadSingleSource(config, null);
			List<ValidationMessage> validations = this.inputValidator.validate(apsInput, ApsInputData.class,
					InputDto.class, (InputDto x) -> {
						return x.getCode();
					});
			if (!validations.isEmpty()) {
				throw new InputDataStreamException("", validations);
			}
			wrappedSingleSource = new ApsInputDataStreamFactory(apsInput);
		}
		if (wrappedSingleSource != null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("End getStream(" + requiredType.getName() + ")");
			}
			return wrappedSingleSource.getStream(requiredType);
		} else {
			ImportEntryType entry = getOrProduceConfigurationEntry(requiredType, null);
			InputStream is = resolveConfigurationEntry(config, entry);

			try {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("End getStream(" + requiredType.getName() + ")");
				}
				if (is == null)
					return new ArrayList<DtoEntityType>().stream();
				return getStreamFromInput(entry, requiredType, is);
			} catch (IOException e) {
				throw new InputDataStreamException("error reading object stream", e);
			}
		}

	}

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType,
			Date modifiedAfter) throws InputDataStreamException {

		if (!config.isHasIncrementalUpdates())
			return new ArrayList<DtoEntityType>().stream();
		if (modifiedAfter == null)
			return getStream(requiredType);
		ApsInputDataStreamFactory wrapped = null;
		if (config.getSingleApsInputUri() != null && !cachedObjects.isEmpty()
				&& modifiedAfter.before(cachedObjects.firstKey())) {
			throw new InputDataStreamException("Trying to query system with past timestamp informations...");
		}
		if (config.getSingleApsInputUri() != null && !cachedObjects.isEmpty()
				&& !cachedObjects.containsKey(modifiedAfter)) {
			cachedObjects.clear();
		}
		if (config.getSingleApsInputUri() != null && !cachedObjects.containsKey(modifiedAfter)) {
			ApsInputData apsInput = loadSingleSource(config, modifiedAfter);
			wrapped = new ApsInputDataStreamFactory(apsInput);
			cachedObjects.put(modifiedAfter, wrapped);
		} else if (config.getSingleApsInputUri() != null && cachedObjects.containsKey(modifiedAfter)) {
			wrapped = cachedObjects.get(modifiedAfter);
		}
		if (wrapped != null)
			return wrapped.getStream(requiredType, modifiedAfter);
		else {
			ImportEntryType entry = getOrProduceConfigurationEntry(requiredType, modifiedAfter);
			InputStream is = resolveConfigurationEntry(config, entry, modifiedAfter);
			try {
				if (is == null)
					return new ArrayList<DtoEntityType>().stream();
				return getStreamFromInput(entry, requiredType, is);
			} catch (IOException e) {
				throw new InputDataStreamException("error reading object stream", e);
			}
		}
	}

	protected abstract ApsInputData loadSingleSource(CType config2, Date modifiedAfter) throws InputDataStreamException;

}
