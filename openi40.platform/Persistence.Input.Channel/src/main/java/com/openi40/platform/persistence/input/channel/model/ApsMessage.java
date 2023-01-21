package com.openi40.platform.persistence.input.channel.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.openi40.scheduler.input.model.tasks.ApsMessageInputDto;

@Entity
@Table(name = "aps_msg")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "taskCode", column = @Column(name = "task_code")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "position", column = @Column(name = "pos")),
		@AttributeOverride(name = "messageCategory", column = @Column(name = "msg_category")),
		@AttributeOverride(name = "messageCode", column = @Column(name = "msg_code")),
		@AttributeOverride(name = "messageDescription", column = @Column(name = "msg_descr")),
		@AttributeOverride(name = "msgLevel", column = @Column(name = "msg_level")),
		@AttributeOverride(name = "sourceModule", column = @Column(name = "src_module")),
		@AttributeOverride(name = "sourceObjectClass", column = @Column(name = "src_obj_class")) ,
		@AttributeOverride(name = "globalPosition", column = @Column(name = "glb_cntr"))})
public class ApsMessage extends ApsMessageInputDto {

	public ApsMessage() {

	}

}
