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
package com.openi40.scheduler.mapper.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.openi40.scheduler.client.model.aps.ApsDataDto;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;

@ComponentScan("com.openi40.scheduler")
@AutoConfigurationPackage
@SpringBootTest(classes = IMapperFactory.class)
@RunWith(SpringRunner.class)

public class DefaultMapperTests {

	public DefaultMapperTests() {

	}

	public static class A {
		String dataString = null;

		public String getDataString() {
			return dataString;
		}

		public void setDataString(String dataString) {
			this.dataString = dataString;
		}

		public Integer getIntValue() {
			return intValue;
		}

		public void setIntValue(Integer intValue) {
			this.intValue = intValue;
		}

		Integer intValue = 0;
	};

	public static class B {
		String dataString = null;

		public String getDataString() {
			return dataString;
		}

		public void setDataString(String dataString) {
			this.dataString = dataString;
		}

		public Integer getIntValue() {
			return intValue;
		}

		public void setIntValue(Integer intValue) {
			this.intValue = intValue;
		}

		Integer intValue = 0;
	}

	public static class AReferrer {
		A nestedObject = new A();
		String propValue = null;
		Date nowValue = new Date();

		public Date getNowValue() {
			return nowValue;
		}

		public void setNowValue(Date nowValue) {
			this.nowValue = nowValue;
		}

		public String getPropValue() {
			return propValue;
		}

		public void setPropValue(String propValue) {
			this.propValue = propValue;
		}

		public A getNestedObject() {
			return nestedObject;
		}

		public void setNestedObject(A nestedObject) {
			this.nestedObject = nestedObject;
		}
	}

	public static class BReferrer {
		B nestedObject = new B();
		String propValue = null;
		Date nowValue = null;

		public Date getNowValue() {
			return nowValue;
		}

		public void setNowValue(Date nowValue) {
			this.nowValue = nowValue;
		}

		public String getPropValue() {
			return propValue;
		}

		public void setPropValue(String propValue) {
			this.propValue = propValue;
		}

		public B getNestedObject() {
			return nestedObject;
		}

		public void setNestedObject(B nestedObject) {
			this.nestedObject = nestedObject;
		}
	}

	public static class ARefWithList extends AReferrer {
		List<A> list = new ArrayList<DefaultMapperTests.A>();

		public List<A> getList() {
			return list;
		}

		public void setList(List<A> list) {
			this.list = list;
		}

	}

	public static class BRefWithList extends BReferrer {
		List<B> list = new ArrayList<DefaultMapperTests.B>();

		public List<B> getList() {
			return list;
		}

		public void setList(List<B> list) {
			this.list = list;
		}
	}

	@Autowired
	IMapperFactory factory;

	@Test
	public void testApsModel2ClientModel() throws MapperException {
		ApsData apsData = new ApsData();
		apsData.setCode("0001");
		apsData.setDescription("MYDESCRIPTION");
		IMapper<ApsData, ApsDataDto> mapper = factory.createMapper(ApsData.class, ApsDataDto.class);
		ApsDataDto dtoCopy = mapper.mapInstance(apsData, ApsDataDto.class, DefaultEntitiesFactory.Instance,
				new HashMap(), true);
		assertEquals(apsData.getId(), dtoCopy.getId());
		assertEquals(apsData.getCode(), dtoCopy.getCode());
		assertEquals(apsData.getDescription(), dtoCopy.getDescription());
	}

	@Test
	public void testMapper() throws MapperException {
		IMapper<A, B> mapper = factory.createMapper(A.class, B.class);
		A a = new A();
		a.setDataString("provaString");
		a.setIntValue(1000);
		B bInstance = mapper.mapInstance(a, B.class, null, null, true);
		assertEquals(a.getDataString(), bInstance.getDataString());
		assertEquals(a.getIntValue(), bInstance.getIntValue());
		AReferrer aReferrer = new AReferrer();
		aReferrer.setNestedObject(a);
		aReferrer.setPropValue("Stuka");
		IMapper<AReferrer, BReferrer> mapper2 = factory.createMapper(AReferrer.class, BReferrer.class);
		BReferrer bReferrer = mapper2.mapInstance(aReferrer, BReferrer.class, null, null, true);
		assertEquals(aReferrer.getNowValue(), bReferrer.getNowValue());
		assertEquals(aReferrer.getPropValue(), bReferrer.getPropValue());
		assertEquals(aReferrer.getNestedObject().getDataString(), bReferrer.getNestedObject().getDataString());
		assertEquals(aReferrer.getNestedObject().getIntValue(), bReferrer.getNestedObject().getIntValue());
		ARefWithList aReferrer1 = new ARefWithList();
		aReferrer1.setNestedObject(a);
		aReferrer1.setPropValue("Stuka");
		IMapper<ARefWithList, BRefWithList> map = factory.createMapper(ARefWithList.class, BRefWithList.class);
		BRefWithList bReferr1 = map.mapInstance(aReferrer1, BRefWithList.class, null, null, true);
		assertEquals(aReferrer1.getNowValue(), bReferr1.getNowValue());
		assertEquals(aReferrer1.getPropValue(), bReferr1.getPropValue());
		assertEquals(aReferrer1.getNestedObject().getDataString(), bReferr1.getNestedObject().getDataString());
		assertEquals(aReferrer1.getNestedObject().getIntValue(), bReferr1.getNestedObject().getIntValue());
	}
}
