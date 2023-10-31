package com.openi40.mes.metamessaging.kernel;

import java.beans.beancontext.BeanContextSupport;
import java.util.List;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.mes.metamessaging.handlers.PollableMessagesStreamFactory;
@Singleton
@Service
public class KernelPoller {
	List<PollableMessagesStreamFactory> factories=null;
	public KernelPoller(@Autowired (required = false) List<PollableMessagesStreamFactory> factories) {
		this.factories=factories;
	}
	@Autowired KernelPollerWorker worker;
	public void doPoll() {
		
		if (factories!=null) {
			for (PollableMessagesStreamFactory pollableMessagesStreamFactory : factories) {
				worker.consumeStream(pollableMessagesStreamFactory);
			}
		}
	}

}
