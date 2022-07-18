package com.openi40.scheduler.outputchannels.streamoutputs.config;

import org.springframework.context.annotation.Configuration;
import lombok.Data;
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
@Data
public class JsonFilesOutputConfig  extends AbstractOutputDataConsumerFactoryConfig<EntityOutputSetting>{
	String baseFolder=null;
	

}
