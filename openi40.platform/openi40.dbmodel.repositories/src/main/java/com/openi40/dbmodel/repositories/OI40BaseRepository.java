package com.openi40.dbmodel.repositories;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openi40.dbmodel.entities.OI40DBBaseEntity;
import com.openi40.dbmodel.utils.AutoCompleteData;
import com.openi40.dbmodel.utils.LookupData;
import com.openi40.dbmodel.utils.PageInfo;
import com.openi40.dbmodel.utils.QbeSupport;
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
@NoRepositoryBean
public interface OI40BaseRepository<OI40Type extends OI40DBBaseEntity> extends JpaRepository<OI40Type, String> {
	@GetMapping(path = "byCode/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	default public OI40Type findByCode(@PathVariable("code") String code) {
		Optional<OI40Type> value = this.findById(code);
		if (value.isPresent())
			return value.get();
		else
			return null;
	}

	@GetMapping(path = "deleteByCode/{code}")
	@Transactional
	default public void deleteByCode(@PathVariable("code") String code) {
		this.deleteById(code);
	}

	@PostMapping(path = "deleteByCodes")
	@Transactional
	default public void deleteByCodes(@RequestBody List<String> codes) {
		for (Iterator iterator = codes.iterator(); iterator.hasNext();) {
			String code = (String) iterator.next();
			this.deleteById(code);
		}
	}

	@PostMapping(path = "findByCodes", produces = MediaType.APPLICATION_JSON_VALUE)
	default public List<OI40Type> findByCodes(@RequestBody List<String> codes) {
		Iterable<OI40Type> outValue = this.findAllById(codes);
		List<OI40Type> outdata = new ArrayList<OI40Type>();
		for (OI40Type oi40Type : outValue) {
			outdata.add(oi40Type);
		}

		return outdata;
	}

	@PostMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	default public List<OI40Type> update(@RequestBody List<OI40Type> data) {
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			OI40Type oi40Type = (OI40Type) iterator.next();
			oi40Type.setModifiedTimestamp(new Date());
		}
		Iterable<OI40Type> outValue = this.saveAll(data);
		List<OI40Type> outdata = new ArrayList<OI40Type>();
		for (OI40Type oi40Type : outValue) {
			outdata.add(oi40Type);
		}
		return outdata;
	}
	@PostMapping(path = "updateSingle", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	default public OI40Type updateSingle(@RequestBody OI40Type data) {
		data.setModifiedTimestamp(new Date());
		data = this.saveAndFlush(data);		
		return data;
	}
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	List<OI40Type> findAll();

	@PostMapping(path = "findAllPaged", produces = MediaType.APPLICATION_JSON_VALUE)
	default public Page<OI40Type> findAll(@NotNull @RequestBody PageInfo p) {
		return this.findAll(PageInfo.from(p));
	}

	@PostMapping(path = "findByQbe", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	default public List<OI40Type> findByQbe(@NotNull @RequestBody OI40Type qbe) {
		List<OI40Type> out = this.findAll(Example.of(qbe));
		return out;
	}

	@PostMapping(path = "findByQbePaged", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	default public Page<OI40Type> findByQbePaged(@NotNull @RequestBody QbeSupport<OI40Type> qbe) {
		Pageable p=PageInfo.from(qbe.getPage());
		Page<OI40Type> out = this.findAll(Example.of(qbe.getQbe()), p);
		return out;
	}

	@GetMapping(path = "integratedAfter/{ts}")
	@Query("select e from #{#entityName} e where e.integrationTs>=?1")
	public List<OI40Type> findByAfterIntegrationTs(@NotNull @PathVariable("ts") Timestamp ts);

	@GetMapping(path = "modifiedAfter/{ts}")
	@Query("select e from #{#entityName} e where e.modifiedTimestamp>=?1")
	public List<OI40Type> findByAfterModifiedTimestamp(@NotNull @PathVariable("ts") Timestamp ts);
	
	//@Query("select e from #{#entityName} e where ((?1 is null || ?1=='')  OR  (upper(e.code) LIKE (upper(?1) || '%' ) )) AND ((?2 is null || ?2=='')  OR  (upper(e.description) LIKE ('%' || upper(?2) || '%' ) ))")
	public Page<OI40Type> findByCodeStartsWithIgnoreCaseAndDescriptionContainsIgnoreCase(String code,String description,Pageable page) ;
	public Page<OI40Type> findByCodeContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String code,String description,Pageable page) ;
	@PostMapping("doAutocomplete")
	default public Page<OI40Type> doAutocomplete(@NotNull @RequestBody AutoCompleteData autoCompleteData) {
		return this.findByCodeContainsIgnoreCaseOrDescriptionContainsIgnoreCase(autoCompleteData.getSearchString()!=null?autoCompleteData.getSearchString().trim():"",autoCompleteData.getSearchString()!=null?autoCompleteData.getSearchString().trim():"", PageInfo.from(autoCompleteData.getPage()));
	}
	@PostMapping("doLookup")
	default public Page<OI40Type> doLookup(@NotNull @RequestBody LookupData lookup) {
		return this.findByCodeStartsWithIgnoreCaseAndDescriptionContainsIgnoreCase(lookup.getCode()!=null?lookup.getCode().trim():"",lookup.getDescription()!=null?lookup.getDescription().trim():"", PageInfo.from(lookup.getPage()));
	}

}
