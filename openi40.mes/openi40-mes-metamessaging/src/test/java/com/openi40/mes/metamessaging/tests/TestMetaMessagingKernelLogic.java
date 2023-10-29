package com.openi40.mes.metamessaging.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.mes.metamessaging.handlers.MessageReceiver;
import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;
import com.openi40.mes.metamessaging.handlers.OI40IOTMessageReceiver;
import com.openi40.mes.metamessaging.kernel.MetaMessagingKernel;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@ComponentScan("com.openi40.mes.metamessaging")
public class TestMetaMessagingKernelLogic {

	public TestMetaMessagingKernelLogic() {

	}

	@Test
	public void doNothingTest() {
	}

	static class TestKernelMessage extends AbstractOI40IOTMetaMessage {
		TestKernelMessage() {
			this.setMsgId("KERNEL-MSG-0");
		}
	}

	static class TestApplicationMessage extends AbstractOI40IOTApplicationMessage {
		TestApplicationMessage() {
			this.setMsgId("APPLICATION-MSG-0");
		}
	}

	static ObjectMapper mapper = new ObjectMapper();

	static class NestedReceiver<Mtype extends AbstractOI40IOTMetaMessage> implements OI40IOTMessageReceiver<Mtype> {
		static Logger LOGGER = LoggerFactory.getLogger(NestedReceiver.class);
		String layer;
		String uniqueID;

		public NestedReceiver(String layer, String uniqueID) {
			this.layer = layer;
			this.uniqueID = uniqueID;
		}

		@Override
		public boolean isCanManage(Mtype msg) {

			return true;
		}

		@Override
		public void onMessage(Mtype msg, MessagingEnvironment environment) {

			try {
				LOGGER.info("[" + getHandlerId() + "] Received message=>" + msg.getMsgId() + "=>"
						+ mapper.writeValueAsString(msg));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public ManagedMessageType[] getHandledTypes() {

			return new ManagedMessageType[] { new ManagedMessageType(TestKernelMessage.class),
					new ManagedMessageType(TestApplicationMessage.class) };
		}

		@Override
		public String getLayerId() {

			return layer;
		}

		@Override
		public String getHandlerId() {

			return "openi40::" + getLayerId() + "::" + uniqueID;
		}

		static <Mtype extends AbstractOI40IOTMetaMessage> List<OI40IOTMessageReceiver> of(NestedReceiver<Mtype> r) {
			List<OI40IOTMessageReceiver> out = new ArrayList<OI40IOTMessageReceiver>();
			out.add(r);
			return out;
		}
	}

	static class MaliciusCycleForcing<Mtype extends AbstractOI40IOTMetaMessage> extends NestedReceiver<Mtype> {
		public MaliciusCycleForcing(String layer, String uniqueId) {
			super(layer, uniqueId);
		}

		@Override
		public void onMessage(Mtype msg, MessagingEnvironment environment) {
			super.onMessage(msg, environment);
			environment.getLoopbackSender().onMessage(msg, environment);
		}
	}

	@Test
	public void checkLayersMultiplexing() {
		NestedReceiver<TestKernelMessage> kernelReceiver = new NestedReceiver<TestMetaMessagingKernelLogic.TestKernelMessage>(
				MetaMessagingKernel.IOT_KERNEL_RECEIVER, "kernel");
		NestedReceiver<TestKernelMessage> systemReceiver = new NestedReceiver<TestMetaMessagingKernelLogic.TestKernelMessage>(
				MetaMessagingKernel.IOT_SYSTEM_RECEIVER, "system");
		OI40IOTMessageReceiver<? extends AbstractOI40IOTApplicationMessage> applicationReceiver = new NestedReceiver<TestMetaMessagingKernelLogic.TestApplicationMessage>(
				MetaMessagingKernel.IOT_APPLICATION_RECEIVER, "application");
		List<OI40IOTMessageReceiver> apphandlers = new ArrayList<OI40IOTMessageReceiver>();
		apphandlers.add(applicationReceiver);
		MetaMessagingKernel kernel = new MetaMessagingKernel(NestedReceiver.of(kernelReceiver),
				NestedReceiver.of(systemReceiver), new ArrayList(apphandlers));
		MessagingEnvironment environment = new MessagingEnvironment() {

			@Override
			public MessageReceiver getLoopbackSender() {

				return kernel;
			}
		};
		TestKernelMessage kernelMessage = new TestKernelMessage();
		TestApplicationMessage applicationMessage = new TestApplicationMessage();
		kernel.onMessage(kernelMessage, environment);

		assert kernelMessage.isAlreadyHandledFrom(kernelReceiver.getHandlerId());
		assert kernelMessage.isAlreadyHandledFrom(systemReceiver.getHandlerId());
		assert !kernelMessage.isAlreadyHandledFrom(applicationReceiver.getHandlerId());
		kernel.onMessage(applicationMessage, environment);
		assert applicationMessage.isAlreadyHandledFrom(kernelReceiver.getHandlerId());
		assert applicationMessage.isAlreadyHandledFrom(systemReceiver.getHandlerId());
		assert applicationMessage.isAlreadyHandledFrom(applicationReceiver.getHandlerId());

	}

	@Test
	public void checkCycleAvoiding() {
		NestedReceiver<TestKernelMessage> kernelReceiver = new NestedReceiver<TestMetaMessagingKernelLogic.TestKernelMessage>(
				MetaMessagingKernel.IOT_KERNEL_RECEIVER, "kernel");
		NestedReceiver<TestKernelMessage> systemReceiver = new NestedReceiver<TestMetaMessagingKernelLogic.TestKernelMessage>(
				MetaMessagingKernel.IOT_SYSTEM_RECEIVER, "system");
		OI40IOTMessageReceiver<? extends AbstractOI40IOTApplicationMessage> applicationReceiver = new MaliciusCycleForcing<TestMetaMessagingKernelLogic.TestApplicationMessage>(
				MetaMessagingKernel.IOT_APPLICATION_RECEIVER, "application");
		List<OI40IOTMessageReceiver> apphandlers = new ArrayList<OI40IOTMessageReceiver>();
		apphandlers.add(applicationReceiver);
		MetaMessagingKernel kernel = new MetaMessagingKernel(NestedReceiver.of(kernelReceiver),
				NestedReceiver.of(systemReceiver), new ArrayList(apphandlers));
		MessagingEnvironment environment = new MessagingEnvironment() {

			@Override
			public MessageReceiver getLoopbackSender() {

				return kernel;
			}
		};
		TestKernelMessage kernelMessage = new TestKernelMessage();
		TestApplicationMessage applicationMessage = new TestApplicationMessage();
		kernel.onMessage(kernelMessage, environment);

		assert kernelMessage.isAlreadyHandledFrom(kernelReceiver.getHandlerId());
		assert kernelMessage.isAlreadyHandledFrom(systemReceiver.getHandlerId());
		assert !kernelMessage.isAlreadyHandledFrom(applicationReceiver.getHandlerId());
		kernel.onMessage(applicationMessage, environment);
		assert applicationMessage.isAlreadyHandledFrom(kernelReceiver.getHandlerId());
		assert applicationMessage.isAlreadyHandledFrom(systemReceiver.getHandlerId());
		assert applicationMessage.isAlreadyHandledFrom(applicationReceiver.getHandlerId());

	}
}
