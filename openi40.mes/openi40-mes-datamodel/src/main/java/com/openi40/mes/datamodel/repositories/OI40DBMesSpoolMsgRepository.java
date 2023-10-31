package com.openi40.mes.datamodel.repositories;

import java.sql.Timestamp;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openi40.mes.datamodel.OI40DBMesSpoolMsg;
@Repository
public interface OI40DBMesSpoolMsgRepository extends JpaRepository<OI40DBMesSpoolMsg, Long> {
	public Stream<OI40DBMesSpoolMsg> findAllBySpoolTypeEqualAndResentTrhresholdMajorThanAndSentEqualsFalse(
			String spoolType, Timestamp timestamp);

	public default Stream<OI40DBMesSpoolMsg> findAllMessagesToRetry() {
		return this.findAllBySpoolTypeEqualAndResentTrhresholdMajorThanAndSentEqualsFalse("RETRYSPOOL",
				new Timestamp(System.currentTimeMillis()));
	}
}
