package com.openi40.dbmodel.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@AttributeOverride(name = "supplier", column = @Column(name = "supplier")),
@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))

})

public class OI40DBPartner extends OI40DBBaseEntity {
	private String address = null;
	private String city = null;
	private String companyName = null;
	private String country = null;
	private String provincia = null;
	private String zipCode = null;
	private Boolean customer=null,supplier=null;
	public OI40DBPartner() {
		
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Boolean getCustomer() {
		return customer;
	}
	public void setCustomer(Boolean customer) {
		this.customer = customer;
	}
	public Boolean getSupplier() {
		return supplier;
	}
	public void setSupplier(Boolean supplier) {
		this.supplier = supplier;
	}

}
