package com.openi40.platform.persistence.input.channel.providers;

import org.springframework.stereotype.Repository;

import com.openi40.platform.persistence.input.channel.model.ApsMessage;
import com.openi40.scheduler.input.model.tasks.ApsMessageInputDto;
@Repository
public interface ApsMessageInputChannelRepository extends InputChannelRepository<ApsMessage, ApsMessageInputDto> {

}
