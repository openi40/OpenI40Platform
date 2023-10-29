package com.openi40.mes.metamessaging;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MetaMessagingKernel implements MessageReceiver<AbstractOI40IOTMetaMessage> {
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
				kernelConditionalReceivers.add(AvoidCycleConditionalMessageReceiverWrapper.of(oi40iotMessageReceiver));
			}
		}
		if (this.iotSystemReceivers != null) {
			for (OI40IOTMessageReceiver oi40iotMessageReceiver : iotSystemReceivers) {
				systemConditionalReceivers.add(AvoidCycleConditionalMessageReceiverWrapper.of(oi40iotMessageReceiver));
			}
		}
		if (this.iotApplicationReceivers != null) {
			for (OI40IOTMessageReceiver<AbstractOI40IOTApplicationMessage> oi40iotMessageReceiver : appreceivers) {
				applicationConditionalReceivers
						.add(AvoidCycleConditionalMessageReceiverWrapper.of(oi40iotMessageReceiver));
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
				applicationMultiplexer.onMessage((AbstractOI40IOTApplicationMessage) msg, null);

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
		this.mainMultiplexer.onMessage(msg, environment);
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

}
