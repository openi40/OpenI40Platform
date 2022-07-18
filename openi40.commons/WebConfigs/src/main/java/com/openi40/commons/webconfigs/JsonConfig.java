package com.openi40.commons.webconfigs;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
@Configuration
public class JsonConfig {
	private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public static class CustomDateSerializer extends JsonSerializer<java.util.Date> {

		

		@Override
		public void serialize(java.util.Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeString(formatter.format(date));
		}
	}
	private final static SimpleDateFormat tformatter = new SimpleDateFormat("HH:mm:ss");
	public static class CustomTimeSerializer extends JsonSerializer<java.sql.Time> {

		

		@Override
		public void serialize(java.sql.Time date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeString(tformatter.format(date));
		}
	}
	public static class CustomSqlDateSerializer extends JsonSerializer<Date> {
		

		@Override
		public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeString(formatter.format(date));
		}
	}

	public static class CustomTimestampSerializer extends JsonSerializer<Timestamp> {
		

		@Override
		public void serialize(Timestamp date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeString(formatter.format(date));
		}
	}

	public static class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
		

		@Override
		public void serialize(LocalDateTime date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			String _dateFmt = "" + date.getYear();
			if (date.getMonthValue() < 10) {
				_dateFmt += "-0" + date.getMonthValue();
			} else {
				_dateFmt += "-" + date.getMonthValue();
			}
			if (date.getDayOfMonth() < 10) {
				_dateFmt += "-0" +date.getDayOfMonth() ;
			} else {
				_dateFmt += "-" +date.getDayOfMonth() ;
			}
			_dateFmt+=" ";
			if (date.getHour() < 10) {
				_dateFmt += "0" + date.getHour();
			} else {
				_dateFmt += "" + date.getHour();
			}
			_dateFmt += ":";
			if (date.getMinute() < 10) {
				_dateFmt += "0" + date.getMinute();
			} else {
				_dateFmt += "" + date.getMinute();
			}
			_dateFmt += ":";
			if (date.getSecond() < 10) {
				_dateFmt += "0" + date.getSecond();
			} else {
				_dateFmt += "" + date.getSecond();
			}
			jsonGenerator.writeString(_dateFmt);
		}
	}

	@Bean
	@Primary
	public ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(Time.class,new CustomTimeSerializer());
		javaTimeModule.addSerializer(java.util.Date.class, new CustomDateSerializer());
		javaTimeModule.addSerializer(Date.class, new CustomSqlDateSerializer());
		javaTimeModule.addSerializer(Timestamp.class, new CustomTimestampSerializer());
		javaTimeModule.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
		objectMapper.registerModule(javaTimeModule);
		return objectMapper;
	}

}
