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
package com.openi40.platform.persistence.input.channel.impl;

import org.springframework.stereotype.Service;

import com.openi40.platform.persistence.input.channel.AbstractPersistenceInputDataStream;
import com.openi40.platform.persistence.input.channel.model.ProductiveCompanyProductSetting;
import com.openi40.platform.persistence.input.channel.providers.ProductiveCompanyProductSettingInputChannelRepository;
import com.openi40.scheduler.input.model.material.configuration.ProductiveCompanyProductSettingInputDto;

@Service
public class ProductiveCompanyProductSettingPersistenceInputDataStream extends
		AbstractPersistenceInputDataStream<ProductiveCompanyProductSetting, ProductiveCompanyProductSettingInputDto, ProductiveCompanyProductSettingInputChannelRepository> {

	public ProductiveCompanyProductSettingPersistenceInputDataStream(
			ProductiveCompanyProductSettingInputChannelRepository repository) {
		super(ProductiveCompanyProductSetting.class, ProductiveCompanyProductSettingInputDto.class, repository);

	}

}
