package com.openi40.scheduler.client.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.engine.aps.ApsLogics;

import io.swagger.annotations.Api;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@RestController
@RequestMapping("schedulerClient/ApsAlgorithms")
@Api
public class ApsAlgorithms {
	
	
	public static class ApsAlgorithm{
		public ApsAlgorithm() {
			
		}
		public ApsAlgorithm(String alg) {
			this.algorithmTypeCode=alg;
		}
		private String algorithmTypeCode=null;
		public String getAlgorithmTypeCode() {
			return algorithmTypeCode;
		}
		public void setAlgorithmTypeCode(String algorithmTypeCode) {
			this.algorithmTypeCode = algorithmTypeCode;
		}
		
	}
	public ApsAlgorithms() {

	}
	@GetMapping(produces = "application/json")
	
	public List<ApsAlgorithm> get() {
		List<ApsAlgorithm> list=new ArrayList<ApsAlgorithms.ApsAlgorithm>();
		
		list.add(new ApsAlgorithm(ApsLogics.BACKWARD_APS));
		list.add(new ApsAlgorithm(ApsLogics.FORWARD_APS));
		
		return list;
	}
}
