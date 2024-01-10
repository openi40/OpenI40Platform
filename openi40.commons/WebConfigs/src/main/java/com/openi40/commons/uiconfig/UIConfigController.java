package com.openi40.commons.uiconfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.commons.webconfigs.OpenI40WebConfig;

@RestController
@RequestMapping(path = "uiConfig/UIConfigController")
public class UIConfigController {
	static final String UI_CONFIG_FILE="classpath:/ui-config.json";
	OpenI40WebConfig baseConfig=null;
	static Logger LOGGER=LoggerFactory.getLogger(UIConfigController.class);
	public UIConfigController(@Autowired(required = false) OpenI40WebConfig config) {
		baseConfig=config;
		
	}
	@GetMapping(produces = "application/json")
	
	public String get() throws IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin get()");
		}
		String outValue="{}";
		InputStream is=this.getClass().getResourceAsStream(UI_CONFIG_FILE);
		if (baseConfig!=null && baseConfig.getAngularUi()!=null && baseConfig.getAngularUi() && is!=null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("UI enabled and returned "+UI_CONFIG_FILE);
				ByteArrayOutputStream bos=new ByteArrayOutputStream();
				FileCopyUtils.copy(is, bos);
				bos.flush();
				outValue=bos.toString("UTF-8");
			}
		}else {
			throw new RuntimeException("Unavailable resource");
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End get() returning "+outValue);
		}
		return outValue;
	}

}
