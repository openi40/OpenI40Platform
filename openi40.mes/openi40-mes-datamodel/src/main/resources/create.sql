
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
			 up_address varchar(250),			 
			 event_type varchar(40),
			 event_payload TEXT,
			 processed_time timestamp,
			 processed_status varchar(10) 		
); 

ALTER TABLE MES_ASSET_EVENT ADD COLUMN ALT_CODE VARCHAR(40);
create sequence mes_asset_event_seq as bigint start with 1000;