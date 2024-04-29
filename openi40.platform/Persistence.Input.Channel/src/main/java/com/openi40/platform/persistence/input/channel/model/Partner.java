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
package com.openi40.platform.persistence.input.channel.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.openi40.scheduler.input.model.companystructure.PartnerInputDto;
@Entity
@Table(name = "partner")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "address", column = @Column(name = "address")),
@AttributeOverride(name = "city", column = @Column(name = "city")),
@AttributeOverride(name = "companyName", column = @Column(name = "company_name")),
@AttributeOverride(name = "country", column = @Column(name = "country")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "provincia", column = @Column(name = "provincia")),
@AttributeOverride(name = "zipCode", column = @Column(name = "zip_code")),
@AttributeOverride(name = "customer", column = @Column(name = "customer")),
@AttributeOverride(name = "supplier", column = @Column(name = "supplier"))
})
public class Partner extends PartnerInputDto {

}
