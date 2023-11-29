
CREATE TABLE MES_ASSET_TYPE (
			code varchar(40) NOT NULL PRIMARY KEY,
		    description varchar(50),
		    removed boolean default false,
		    modified_ts timestamp 
);
CREATE TABLE MES_ASSET_STATUS (
			code varchar(40) NOT NULL PRIMARY KEY,
		    description varchar(50),
		    removed boolean default false,
		    modified_ts timestamp 
); 

CREATE TABLE MES_ASSET_GROUP (
			CODE VARCHAR(40) NOT NULL PRIMARY KEY,
			description varchar(50),
		    removed boolean default false,
		    modified_ts timestamp,
		    context_type varchar(40) NOT NULL,
		    parent_object_code varchar(250) NOT NULL
					    
);

CREATE TABLE MES_ASSET (
			CODE VARCHAR(40) NOT NULL PRIMARY KEY,
			description varchar(50),
		    removed boolean default false,
		    modified_ts timestamp,
			mes_asset_type_code varchar(40) not null,
			mes_asset_group_code varchar(40) not null,
			mes_asset_status_code varchar(40),
			MAC_CODE VARCHAR(250) ,
			ALT_CODE VARCHAR(250) ,
			IP_ADDRESS VARCHAR(20)  			
);

ALTER TABLE MES_ASSET_TYPE ADD COLUMN integration_ts timestamp;
ALTER TABLE MES_ASSET_STATUS ADD COLUMN integration_ts timestamp;
ALTER TABLE MES_ASSET_GROUP ADD COLUMN integration_ts timestamp;
ALTER TABLE MES_ASSET ADD COLUMN integration_ts timestamp;
 
CREATE TABLE MES_ASSET_EVENT (
			 event_id bigint not null primary key,
			 event_time timestamp not null,
			 mes_asset_code varchar(40),
			 mac_code varchar(250),
			 ip_address varchar(250),			 
			 event_type varchar(40),
			 event_payload CLOB,
			 processed_time timestamp,
			 processed_status varchar(10) 		
); 

ALTER TABLE MES_ASSET_EVENT ADD COLUMN ALT_CODE VARCHAR(40);
create sequence mes_asset_event_seq as bigint start with 1000;

CREATE TABLE MES_LOGICAL_MSG (
		id bigint not null primary key,
		message_time timestamp,
		message_type varchar(256) not null,
		message_uuid varchar(40) not null,
		originalMsgId varchar(40),
	    correlationId varchar(40),
	    from_ref varchar(80),
	    to_ref varchar(80),
	    mes_asset_code varchar(40),
		mac_code varchar(250),
		ip_address varchar(250),		
	    payload CLOB
);

create sequence mes_logical_msg_seq as bigint start with 1000;
CREATE TABLE MES_SPOOL_MSG (
		id bigint not null primary key,
		message_time timestamp,
		message_type varchar(256) not null,
		message_uuid varchar(40) not null,
		spool_type varchar(10) not null,
		resend_trheshold timestamp,
		originalMsgId varchar(40),
	    correlationId varchar(40),
	    from_ref varchar(80),
	    to_ref varchar(80),
	    mes_asset_code varchar(40),
		mac_code varchar(250),
		ip_address varchar(250),	
		sent boolean,	 	
	    payload CLOB
);
create sequence mes_spool_msg_seq as bigint start with 1000;
CREATE TABLE MES_UNMANAGED_MSG (
		id bigint not null primary key,
		message_time timestamp,
		message_type varchar(256) not null,
		message_uuid varchar(40) not null,
		originalMsgId varchar(40),
	    correlationId varchar(40),
	    from_ref varchar(80),
	    to_ref varchar(80),
	     mes_asset_code varchar(40),
			 mac_code varchar(250),
			 ip_address varchar(250),		
	    payload CLOB
);
create sequence mes_unmanaged_msg_seq as bigint start with 1000;


ALTER TABLE MES_ASSET ADD COLUMN INTEGRATION_ID VARCHAR(30);
ALTER TABLE MES_ASSET ADD COLUMN INTEGRATION_READ_URL VARCHAR(255);
ALTER TABLE MES_ASSET ADD COLUMN READ_CONTENT_TYPE VARCHAR(40);
ALTER TABLE MES_ASSET ADD COLUMN INTEGRATION_WRITE_URL VARCHAR(255);
ALTER TABLE MES_ASSET ADD COLUMN WRITE_CONTENT_TYPE VARCHAR(40);
ALTER TABLE MES_ASSET ADD COLUMN PROTOCOL_TYPE VARCHAR(20);
ALTER TABLE MES_ASSET ADD COLUMN JSON_CONFIG CLOB;



CREATE TABLE aps_task (
	code varchar(254) NOT NULL,
	description varchar(253) ,
	cycle_code varchar(253) ,
	removed boolean ,
	modified_ts timestamp ,
	op_code varchar(253) ,
	pred_mac_code varchar(253) ,
	scheduled_mac_code varchar(253) ,
	sequence_code varchar(253) ,
	successfully_scheduled boolean ,
	work_center_code varchar(253) ,
	work_order_code varchar(253),
	forced_mac_code varchar(80),
	success_scheduled boolean,
	work_order_root boolean,
	start_preparation timestamp,
	end_preparation timestamp,
	start_execution timestamp,
	end_execution timestamp,
	equip_spec_code varchar(254) ,
	asked_del_time timestamp ,
	sales_line_code varchar(254) ,
	qty_total double precision ,
	qty_produced double precision ,
	custom_priority integer ,
	setup_time double precision ,
	work_time double precision ,
	setup_group_code varchar(254) ,
	status varchar(20) ,
	acq_prep_start timestamp ,
	acq_prep_end timestamp ,
	acq_prd_start timestamp ,
	acq_prd_end timestamp ,
	acq_prd_upd timestamp ,
	min_prd_date timestamp ,
	max_prd_date timestamp ,
	acq_machine_code varchar(255) ,
	integration_ts timestamp ,
	CONSTRAINT task_pkey PRIMARY KEY (code)
);


create table mes_task (
	code varchar(254) NOT NULL,
	aps_task_code varchar(254) NOT NULL,
	mes_asset_code varchar(40),
	PRIMARY KEY (code)
);

create table mes_task_event (
    id bigint not null,
	mes_task_code varchar(254) not null,
	machine_code varchar(254),
	event_type varchar(30) not null,
	event_time timestamp,
	mes_asset_code varchar(40),
	primary key (id)
);

create table mes_task_event_equip(
    id bigint not null,
	event_id bigint not null,
	resource_code varchar(255),
	resource_group_code varchar(255) not null,
	cardinality int default 1,
	mes_asset_code varchar(40),
	primary key (id)
);

create table mes_task_material_event (
	id bigint not null,
	event_id bigint not null,
	product_code varchar(255) not null,
    traceability varchar(255),
	unity varchar(4) not null,
	quantity int default 1,
	mes_asset_code varchar(40)
);

