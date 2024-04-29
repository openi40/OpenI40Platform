package com.openi40.mes.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.openi40.mes.shared.model.OI40DBMesBaseEntity;
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
@NoRepositoryBean
public interface OI40MesBaseRepository<OI40Type extends OI40DBMesBaseEntity> extends JpaRepository<OI40Type, String> {
	
}
