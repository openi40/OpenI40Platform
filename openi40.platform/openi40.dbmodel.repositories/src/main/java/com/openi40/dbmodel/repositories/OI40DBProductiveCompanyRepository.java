package com.openi40.dbmodel.repositories;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.dbmodel.entities.OI40DBProductiveCompany;
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
@Repository
@RestController
@RequestMapping(path = "/integration/OI40DBProductiveCompany")
public interface OI40DBProductiveCompanyRepository
		extends OI40BaseRepository<OI40DBProductiveCompany> {
	@Override
	default boolean isDeletable(@NotNull String code) {
		Integer cntr=this.hasRelatedRecords(code);
		return cntr==null || cntr==0;
	}
	@Query(value="SELECT SUM(CNTR) FROM (SELECT COUNT(*) AS CNTR FROM plant where prdive_company_code=?1 UNION SELECT COUNT(*) AS CNTR FROM prdive_company_prd_setting where prdive_company_code=?1)",nativeQuery=true)
	Integer hasRelatedRecords(String code);
	
}