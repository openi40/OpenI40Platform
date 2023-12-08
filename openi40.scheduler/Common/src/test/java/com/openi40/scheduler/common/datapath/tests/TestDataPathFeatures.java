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
package com.openi40.scheduler.common.datapath.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.openi40.scheduler.common.datapath.DataPathException;
import com.openi40.scheduler.common.datapath.DataPathExtractorImpl;

public class TestDataPathFeatures {

	public TestDataPathFeatures() {

	}

	static int indexFourth = 1;
	static int indexThird = 1;

	
	public static class FourthBloodyClass {
		int fourthClassProperty = 0;

		public FourthBloodyClass() {
			fourthClassProperty = indexFourth;
			indexFourth++;
		}

		public int getFourthClassProperty() {
			return fourthClassProperty;
		}

		public void setFourthClassProperty(int fourthClassProperty) {
			this.fourthClassProperty = fourthClassProperty;
		}
	}

	
	public static class ThirdBloodyClass {
		int thirdClassProperty = 0;
		List<FourthBloodyClass> fourthBloodyList = new ArrayList<TestDataPathFeatures.FourthBloodyClass>();

		public ThirdBloodyClass() {
			thirdClassProperty = indexThird;
			indexThird++;
		}

		public int getThirdClassProperty() {
			return thirdClassProperty;
		}

		public void setThirdClassProperty(int thirdClassProperty) {
			this.thirdClassProperty = thirdClassProperty;
		}

		public List<FourthBloodyClass> getFourthBloodyList() {
			return fourthBloodyList;
		}

		public void setFourthBloodyList(List<FourthBloodyClass> fourthBloodyList) {
			this.fourthBloodyList = fourthBloodyList;
		}
	}

	
	public static class SecondStepClass {
		String propertyValue = null;
		List<ThirdBloodyClass> thirdBloodyList = new ArrayList<>();
		public String getPropertyValue() {
			return propertyValue;
		}
		public void setPropertyValue(String propertyValue) {
			this.propertyValue = propertyValue;
		}
		public List<ThirdBloodyClass> getThirdBloodyList() {
			return thirdBloodyList;
		}
		public void setThirdBloodyList(List<ThirdBloodyClass> thirdBloodyList) {
			this.thirdBloodyList = thirdBloodyList;
		}
	}


	public static class FirstStepClass {
		SecondStepClass secondStep = null;

		public SecondStepClass getSecondStep() {
			return secondStep;
		}

		public void setSecondStep(SecondStepClass secondStep) {
			this.secondStep = secondStep;
		}

	}

	
	public static class RootClass {
		FirstStepClass firstStep = null;

		public FirstStepClass getFirstStep() {
			return firstStep;
		}

		public void setFirstStep(FirstStepClass firstStep) {
			this.firstStep = firstStep;
		}

	}

	@Test
	public void testDataPathFeatures() throws DataPathException {
		RootClass root = new RootClass();
		root.setFirstStep(new FirstStepClass());
		root.getFirstStep().setSecondStep(new SecondStepClass());
		root.getFirstStep().getSecondStep().setPropertyValue("ok");
		ThirdBloodyClass t31;
		root.getFirstStep().getSecondStep().getThirdBloodyList().add(t31 = new ThirdBloodyClass());
		ThirdBloodyClass t32;
		root.getFirstStep().getSecondStep().getThirdBloodyList().add(t32 = new ThirdBloodyClass());
		FourthBloodyClass t41;
		t31.getFourthBloodyList().add(t41 = new FourthBloodyClass());
		FourthBloodyClass t42;
		t31.getFourthBloodyList().add(t42 = new FourthBloodyClass());
		FourthBloodyClass t43;
		t32.getFourthBloodyList().add(t43 = new FourthBloodyClass());
		FourthBloodyClass t44;
		t32.getFourthBloodyList().add(t44 = new FourthBloodyClass());
		DataPathExtractorImpl datapathextractor = new DataPathExtractorImpl();
		List<SecondStepClass> values = datapathextractor.readByPath(root, RootClass.class, SecondStepClass.class,
				"RootClass/FirstStep/FirstStepClass/SecondStep");
		assert values.size() == 1;
		assert values.get(0).getPropertyValue().equals("ok");
		List<ThirdBloodyClass> thirdList = datapathextractor.readByPath(root, RootClass.class, ThirdBloodyClass.class,
				"RootClass/FirstStep/FirstStepClass/SecondStep/SecondStepClass/ThirdBloodyList");
		assert thirdList.size() == 2;
		List<FourthBloodyClass> fourthList = datapathextractor.readByPath(root, RootClass.class,
				FourthBloodyClass.class,
				"RootClass/FirstStep/FirstStepClass/SecondStep/SecondStepClass/ThirdBloodyList/ThirdBloodyClass/FourthBloodyList");
		assert fourthList.size() == 4;

	}
}
