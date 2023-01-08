package com.openi40.scheduler.mapper.iomessages2apsmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.iomessages.AbstractBaseIOMessage;
import com.openi40.scheduler.iomessages.EndSetupIOMessage;
import com.openi40.scheduler.iomessages.EndWorkIOMessage;
import com.openi40.scheduler.iomessages.OutOfOrderMachineIOMessage;
import com.openi40.scheduler.iomessages.PauseTaskIOMessage;
import com.openi40.scheduler.iomessages.StartSetupIOMessage;
import com.openi40.scheduler.iomessages.StartWorkIOMessage;
import com.openi40.scheduler.iomessages.UnderMantainmentMachineIOMessage;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.mapper.TypeMap;
import com.openi40.scheduler.model.messages.AbstractBaseMessage;
import com.openi40.scheduler.model.messages.EndSetupMessage;
import com.openi40.scheduler.model.messages.EndWorkMessage;
import com.openi40.scheduler.model.messages.OutOfOrderMachineMessage;
import com.openi40.scheduler.model.messages.PauseTaskMessage;
import com.openi40.scheduler.model.messages.StartSetupMessage;
import com.openi40.scheduler.model.messages.StartWorkMessage;
import com.openi40.scheduler.model.messages.UnderMantainmentMachineMessage;

@Service
public class InputIOMessage2ApsMessageModelService
		extends AbstractInputIOMessage2ApsMessageModelService<AbstractBaseIOMessage, AbstractBaseMessage> {
	static TypeMap typeMap=new TypeMap();
	static {
		try {
			typeMap.add(StartSetupIOMessage.class, StartSetupMessage.class);
			typeMap.add(EndSetupIOMessage.class, EndSetupMessage.class);
			typeMap.add(StartWorkIOMessage.class, StartWorkMessage.class);
			typeMap.add(EndWorkIOMessage.class, EndWorkMessage.class);
			typeMap.add(OutOfOrderMachineIOMessage.class, OutOfOrderMachineMessage.class);
			typeMap.add(PauseTaskIOMessage.class, PauseTaskMessage.class);
			typeMap.add(UnderMantainmentMachineIOMessage.class, UnderMantainmentMachineMessage.class);
		} catch (MapperException e) {
			throw new RuntimeException("Cannot initialize IOMEssages typesmap", e);
		}
	}
	@Autowired
	public InputIOMessage2ApsMessageModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory) {
		super(beanFactory, businessModelFactory, AbstractBaseIOMessage.class, AbstractBaseMessage.class,typeMap);

	}

}
