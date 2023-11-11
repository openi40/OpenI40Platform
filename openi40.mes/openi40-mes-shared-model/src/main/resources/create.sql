
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
		    modified_ts timestamp
					    
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

ALTER TABLE MES_ASSET_GROUP ADD COLUMN context_type varchar(40) NOT NULL;
ALTER TABLE MES_ASSET_GROUP ADD COLUMN parent_object_code varchar(250) NOT NULL;

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
			 event_payload TEXT,
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
	    payload text
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
	    payload text
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
	    payload text
);
create sequence mes_unmanaged_msg_seq as bigint start with 1000;


ALTER TABLE MES_ASSET ADD COLUMN INTEGRATION_ID VARCHAR(30);
ALTER TABLE MES_ASSET ADD COLUMN INTEGRATION_READ_URL VARCHAR(255);
ALTER TABLE MES_ASSET ADD COLUMN READ_CONTENT_TYPE VARCHAR(40);
ALTER TABLE MES_ASSET ADD COLUMN INTEGRATION_WRITE_URL VARCHAR(255);
ALTER TABLE MES_ASSET ADD COLUMN WRITE_CONTENT_TYPE VARCHAR(40);
ALTER TABLE MES_ASSET ADD COLUMN PROTOCOL_TYPE VARCHAR(20);
ALTER TABLE MES_ASSET ADD COLUMN JSON_CONFIG TEXT;



CREATE TABLE aps_task (
	code varchar(254) NOT NULL,
	description varchar(253) NULL,
	cycle_code varchar(253) NULL,
	removed bool NULL,
	modified_ts timestamp NULL,
	op_code varchar(253) NULL,
	pred_mac_code varchar(253) NULL,
	scheduled_mac_code varchar(253) NULL,
	sequence_code varchar(253) NULL,
	successfully_scheduled bool NULL,
	work_center_code varchar(253) NULL,
	work_order_code varchar(253) NULL,
	forced_mac_code varchar(80) NULL,
	success_scheduled bool NULL,
	work_order_root bool NULL,
	start_preparation timestamp NULL,
	end_preparation timestamp NULL,
	start_execution timestamp NULL,
	end_execution timestamp NULL,
	equip_spec_code varchar(254) NULL,
	asked_del_time timestamp NULL,
	sales_line_code varchar(254) NULL,
	qty_total float8 NULL,
	qty_produced float8 NULL,
	custom_priority int4 NULL,
	setup_time float8 NULL,
	work_time float8 NULL,
	setup_group_code varchar(254) NULL,
	status varchar(20) NULL,
	acq_prep_start timestamp NULL,
	acq_prep_end timestamp NULL,
	acq_prd_start timestamp NULL,
	acq_prd_end timestamp NULL,
	acq_prd_upd timestamp NULL,
	min_prd_date timestamp NULL,
	max_prd_date timestamp NULL,
	acq_machine_code varchar(255) NULL,
	integration_ts timestamp NULL,
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

