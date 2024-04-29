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
package com.openi40.scheduler.input.channels;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.openi40.scheduler.common.datamodel.DataInputValidatorImpl;
import com.openi40.scheduler.common.datamodel.ValidationMessage;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;

public class TestArbaleteCompanyModelReading {
	@Test
	public void testArbaletesModelReading() throws JsonParseException, JsonMappingException, IOException {
		try {
			InputStream input = getClass().getResourceAsStream("/demosource/ArbaleteCompany.json");
			ObjectMapper objectMapper = new ObjectMapper();

			ApsInputData inputData = objectMapper.readValue(input, ApsInputData.class);
			input.close();
			objectMapper.writeValue(System.out, inputData);
			DataInputValidatorImpl dataInputValidator = new DataInputValidatorImpl();
			List<ValidationMessage> validationMessages = dataInputValidator.validate(inputData, ApsInputData.class,
					InputDto.class, (InputDto b) -> {
						return b.getCode();
					});
			for (ValidationMessage validationMessage : validationMessages) {
				System.err.println(validationMessage.toString());
			}
			assertTrue(validationMessages.isEmpty());
		} catch (Throwable t) {
			t.printStackTrace();
			assertTrue(false);
		}
	}

}
