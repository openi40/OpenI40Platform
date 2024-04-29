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

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openi40.platform.persistence.input.channel.InputChannelStreamProvider;
import com.openi40.scheduler.input.model.InputDto;

@NoRepositoryBean
public interface InputChannelRepository<PClass extends InputClass, InputClass extends InputDto>
		extends JpaRepository<PClass, String>, InputChannelStreamProvider<InputClass> {
	@Override
	@Transactional(readOnly = true)

	@Query("select e from #{#entityName} e where e.code in ?1 ")
	List<PClass> findAllById(Iterable<String> ids);

	/*
	 * @Override
	 * 
	 * @Query("update #{#entityName} e set e.removed=true where e.id = ?1")
	 * 
	 * @Transactional
	 * 
	 * @Modifying void deleteById(String id);
	 */

	@Transactional(readOnly = true)
	@Query("select e from #{#entityName} e where e.code = ?1 ")
	Optional<PClass> findById(String code);

	/*
	 * @Transactional(readOnly = true)
	 * 
	 * @Query("select e from #{#entityName} e where e.code in ?1 and e.deleted = ?2"
	 * ) List<PClass> findByCodes(List<String> codes, boolean deleted);
	 */

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	@QueryHints(value = {

			@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE, value = "10000"),
			@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "false"),
			@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_READONLY, value = "true") })
	@Query("select e from #{#entityName} e   order by code")

	Stream<PClass> streamPClassAll();

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)

	@QueryHints(value = {

			@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE, value = "10000"),
			@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "false"),
			@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_READONLY, value = "true") })
	@Query("select e from #{#entityName} e where  modifiedTimestamp>?1 order by code")
	Stream<PClass> streamPClassModifiedAfter(Timestamp timestamp);

	@Query("select e from #{#entityName} e where  modifiedTimestamp>?1 order by code")
	List<PClass> findPClassModifiedAfter(Timestamp timestamp);

	@Transactional(readOnly = true)
	@Override
	default Stream<InputClass> streamAll(boolean useDBStreaming, int batchingSize) {
		Stream<PClass> stream = null;
		if (useDBStreaming) {
			stream = this.streamPClassAll();
		} else {
			stream = this.findAll().stream();
		}
		return stream.map(p -> p);
	}

	@Transactional(readOnly = true)
	@Override
	default Stream<InputClass> streamModifiedAfter(Timestamp timestamp, boolean useDBStreaming, int batchingSize) {
		Stream<PClass> stream = null;
		if (useDBStreaming) {
			stream = this.streamPClassModifiedAfter(timestamp);
		} else {
			stream = this.findPClassModifiedAfter(timestamp).stream();
		}
		return stream.map(p -> p);

	}
}
