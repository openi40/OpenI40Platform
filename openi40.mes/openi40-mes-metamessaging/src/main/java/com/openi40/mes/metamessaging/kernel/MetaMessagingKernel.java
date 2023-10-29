package com.openi40.mes.metamessaging.kernel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.openi40.mes.metamessaging.handlers.MessageMultiplexer;
import com.openi40.mes.metamessaging.handlers.MessageReceiver;
import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;
import com.openi40.mes.metamessaging.handlers.OI40IOTMessageReceiver;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;
import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

@Service
public class MetaMessagingKernel implements MessageReceiver<AbstractOI40IOTMetaMessage>, MessagingEnvironment {
	public final static String IOT_KERNEL_RECEIVER = "openi40::iot::kernel-receiver";
	public final static String IOT_SYSTEM_RECEIVER = "openi40::iot::system-receiver";
	public final static String IOT_APPLICATION_RECEIVER = "openi40::iot::application-receiver";
	protected List<OI40IOTMessageReceiver> iotKernelReceivers = null;
	protected List<OI40IOTMessageReceiver> iotSystemReceivers = null;
	protected List<OI40IOTMessageReceiver<AbstractOI40IOTApplicationMessage>> iotApplicationReceivers = null;
	protected List<MessageReceiver<AbstractOI40IOTMetaMessage>> kernelConditionalReceivers = new ArrayList<MessageReceiver<AbstractOI40IOTMetaMessage>>();
	protected List<MessageReceiver<AbstractOI40IOTMetaMessage>> systemConditionalReceivers = new ArrayList<MessageReceiver<AbstractOI40IOTMetaMessage>>();
	protected List<MessageReceiver<AbstractOI40IOTApplicationMessage>> applicationConditionalReceivers = new ArrayList<MessageReceiver<AbstractOI40IOTApplicationMessage>>();
	protected MessageMultiplexer<AbstractOI40IOTMetaMessage> kernelMultiplexer = null, systemMultiplexer = null;
	protected MessageMultiplexer<AbstractOI40IOTApplicationMessage> applicationMultiplexer = null;
	protected MessageMultiplexer mainMultiplexer = null;

	public MetaMessagingKernel(
			@Autowired(required = false) @Qualifier(value = IOT_KERNEL_RECEIVER) List<OI40IOTMessageReceiver> kernelreceivers,
			@Autowired(required = false) @Qualifier(value = IOT_SYSTEM_RECEIVER) List<OI40IOTMessageReceiver> sysreceivers,
			@Autowired(required = false) @Qualifier(value = IOT_APPLICATION_RECEIVER) List<OI40IOTMessageReceiver<AbstractOI40IOTApplicationMessage>> appreceivers) {
		this.iotKernelReceivers = kernelreceivers;// AvoidCycleConditionalMessageReceiverWrapper.of(kernelreceivers);
		this.iotSystemReceivers = sysreceivers; // AvoidCycleConditionalMessageReceiverWrapper.of(sysreceivers);
		this.iotApplicationReceivers = appreceivers;// AvoidCycleConditionalMessageReceiverWrapper.of(new
													// ArrayList(appreceivers));
		if (this.iotKernelReceivers != null) {
			for (OI40IOTMessageReceiver oi40iotMessageReceiver : iotKernelReceivers) {
				kernelConditionalReceivers.add(AvoidCycleMessageReceiverWrapper.of(oi40iotMessageReceiver));
			}
		}
		if (this.iotSystemReceivers != null) {
			for (OI40IOTMessageReceiver oi40iotMessageReceiver : iotSystemReceivers) {
				systemConditionalReceivers.add(AvoidCycleMessageReceiverWrapper.of(oi40iotMessageReceiver));
			}
		}
		if (this.iotApplicationReceivers != null) {
			for (OI40IOTMessageReceiver<AbstractOI40IOTApplicationMessage> oi40iotMessageReceiver : appreceivers) {
				applicationConditionalReceivers.add(AvoidCycleMessageReceiverWrapper.of(oi40iotMessageReceiver));
			}
		}
		this.kernelMultiplexer = new MessageMultiplexer<AbstractOI40IOTMetaMessage>(this.kernelConditionalReceivers) {
			@Override
			public String getLayerId() {

				return "kernel";
			}
		};
		this.systemMultiplexer = new MessageMultiplexer<AbstractOI40IOTMetaMessage>(this.systemConditionalReceivers) {
			@Override
			public String getLayerId() {

				return "system";
			}
		};
		;
		this.applicationMultiplexer = new MessageMultiplexer<AbstractOI40IOTApplicationMessage>(
				applicationConditionalReceivers) {
			@Override
			public String getLayerId() {

				return "application";
			}
		};
		;
		List<MessageReceiver> mainMultiplexed = new ArrayList<MessageReceiver>();
		mainMultiplexed.add(this.kernelMultiplexer);
		mainMultiplexed.add(this.systemMultiplexer);
		mainMultiplexed.add(new OI40IOTMessageReceiver<AbstractOI40IOTMetaMessage>() {

			@Override
			public ManagedMessageType[] getHandledTypes() {

				return applicationMultiplexer.getHandledTypes();
			}

			@Override
			public void onMessage(AbstractOI40IOTMetaMessage msg, MessagingEnvironment environment) {
				applicationMultiplexer.onMessage((AbstractOI40IOTApplicationMessage) msg, environment);

			}

			public boolean isCanManage(AbstractOI40IOTMetaMessage msg) {
				return msg instanceof AbstractOI40IOTApplicationMessage
						&& applicationMultiplexer.isCanManage((AbstractOI40IOTApplicationMessage) msg);
			};
		});
		this.mainMultiplexer = new MessageMultiplexer(mainMultiplexed);

	}

	@Override
	public void onMessage(AbstractOI40IOTMetaMessage msg, MessagingEnvironment environment) {
		this.mainMultiplexer.onMessage(msg, this);
	}

	@Override
	public ManagedMessageType[] getHandledTypes() {

		return this.mainMultiplexer.getHandledTypes();
	}

	@Override
	public boolean isCanManage(AbstractOI40IOTMetaMessage msg) {

		return true;
	}

	@Override
	public String getLayerId() {

		return "Kernel-Router";
	}

	class LoopBackSender implements MessageReceiver<AbstractOI40IOTMetaMessage> {
		Logger LOGGER = LoggerFactory.getLogger(LoopBackSender.class);

		@Override
		public void onMessage(AbstractOI40IOTMetaMessage msg, MessagingEnvironment environment) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Begin LoopBackSender::onMessage(..)");
			}
			MetaMessagingKernel.this.onMessage(msg, MetaMessagingKernel.this);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("End LoopBackSender::onMessage(..)");
			}
		}

		@Override
		public boolean isCanManage(AbstractOI40IOTMetaMessage msg) {

			return MetaMessagingKernel.this.isCanManage(msg);
		}

		@Override
		public ManagedMessageType[] getHandledTypes() {

			return MetaMessagingKernel.this.getHandledTypes();
		}

	}

	MessageReceiver loopBackSender = new LoopBackSender();

	@Override
	public MessageReceiver getLoopbackSender() {

		return loopBackSender;
	}

}
