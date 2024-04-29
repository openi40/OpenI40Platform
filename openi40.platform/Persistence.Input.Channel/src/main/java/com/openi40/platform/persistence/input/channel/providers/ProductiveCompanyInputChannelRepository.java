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
package com.openi40.platform.persistence.input.channel.providers;

import org.springframework.stereotype.Repository;

import com.openi40.platform.persistence.input.channel.model.ProductiveCompany;
import com.openi40.scheduler.input.model.companystructure.ProductiveCompanyInputDto;


@Repository
public interface ProductiveCompanyInputChannelRepository
		extends InputChannelRepository<ProductiveCompany, ProductiveCompanyInputDto> {

}
