package com.openi40.dbmodel.repositories;

import java.sql.Timestamp;
import java.util.ArrayList;
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
		Iterable<OI40Type> outValue = this.saveAll(data);
		List<OI40Type> outdata = new ArrayList<OI40Type>();
		for (OI40Type oi40Type : outValue) {
			outdata.add(oi40Type);
		}
		return outdata;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	List<OI40Type> findAll();

	@PostMapping(path = "findAllPaged", produces = MediaType.APPLICATION_JSON_VALUE)
	Page<OI40Type> findAll(@RequestBody Pageable p);

	@PostMapping(path = "findByQbe", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	default public List<OI40Type> findByQbe(@RequestBody OI40Type qbe) {
		List<OI40Type> out = this.findAll(Example.of(qbe));
		return out;
	}

	@PostMapping(path = "findByQbePaged", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	default public Page<OI40Type> findByQbePaged(@RequestBody OI40Type qbe, Pageable p) {

		Page<OI40Type> out = this.findAll(Example.of(qbe), p);
		return out;
	}

	@GetMapping(path = "integratedAfter/{ts}")
	@Query("select e from #{#entityName} e where e.integrationTs>=?1")
	public List<OI40Type> findByAfterIntegrationTs(@NotNull @PathVariable("ts") Timestamp ts);

	@GetMapping(path = "modifiedAfter/{ts}")
	@Query("select e from #{#entityName} e where e.modifiedTimestamp>=?1")
	public List<OI40Type> findByAfterModifiedTimestamp(@NotNull @PathVariable("ts") Timestamp ts);

}
