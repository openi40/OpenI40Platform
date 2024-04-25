CREATE TABLE META_DOCTYPE (
	CODE VARCHAR(10) NOT NULL,
	DESCRIPTION VARCHAR(255) NOT NULL,
	PRIMARY KEY (CODE)
);

CREATE TABLE DOCTYPE (
  CODE VARCHAR(10) NOT NULL,
  DESCRIPTION VARCHAR(255) NOT NULL,
  meta_doctype_code CHAR(10) not null,
  PRIMARY KEY (CODE)
);
CREATE TABLE PARTNER_TYPE (
  CODE VARCHAR(20) NOT NULL,
  DESCRIPTION VARCHAR(255) NOT NULL,
);
CREATE TABLE PARTNER (
  id bigint not null,
  CODE VARCHAR(20) NOT NULL,
  DESCRIPTION VARCHAR(255) NOT NULL,  
  PRIMARY KEY (id)
);
CREATE TABLE PARTNER_RELTYPE (
	PARTNER_ID BIGINT NOT NULL,
	PARTNER_TYPE_CODE VARCHAR(20) NOT NULL,
	PRIMARY KEY (PARTNER_ID,PARTNER_TYPE_CODE)
);
CREATE TABLE PARTNER_DEPT (
  ID BIGINT NOT NULL,
  CODE VARCHAR(20) NOT NULL,
  DESCRIPTION VARCHAR(255) NOT NULL,
  PARTNER_CODE VARCHAR(20) NOT NULL,
  PRIMARY KEY (CODE)
);

CREATE TABLE PRODUCT_STATUS (
	code varchar(20) not null,
	description VARCHAR(80),
	PRIMARY KEY (CODE)	 	
);
CREATE TABLE UM (
    code varchar(4) not null,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)	 	
);
CREATE TABLE CLASS1FAM1 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS1FAM2 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS1FAM3 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS1FAM4 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS1FAM5 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS1FAM6 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS2FAM1 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS2FAM2 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS2FAM3 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS2FAM4 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS2FAM5 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS2FAM6 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS3FAM1 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS3FAM2 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS3FAM3 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS3FAM4 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS3FAM5 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);
CREATE TABLE CLASS3FAM6 (
	CODE VARCHAR(20) NOT NULL,
	description VARCHAR(80) not null,	
	PRIMARY KEY (CODE)
);

CREATE TABLE ITEM (
    ID BIGINT NOT NULL,
	code varchar(20) not null,
	description VARCHAR(80),
	status_code varchar(20),
	class1fam1 varchar(20),
	class1fam2 varchar(20),
	class1fam3 varchar(20),
	class1fam4 varchar(20),
	class1fam5 varchar(20),
	class1fam6 varchar(20),
	class2fam1 varchar(20),
	class2fam2 varchar(20),
	class2fam3 varchar(20),
	class2fam4 varchar(20),
	class2fam5 varchar(20),
	class2fam6 varchar(20),
	class3fam1 varchar(20),
	class3fam2 varchar(20),
	class3fam3 varchar(20),
	class3fam4 varchar(20),
	class3fam5 varchar(20),
	class3fam6 varchar(20),
	um_technical_code varchar(4) not null default 'PZ',
	um_movement_code varchar(4) not null default 'PZ',
	um_stock_code varchar(4) not null default 'PZ',
	um_sales_code varchar(4) not null default 'PZ',
	um_purch_code varchar(4) not null default 'PZ',
	tech2movcoeff double default 1,
	tech2stockcoeff double default 1,
	tech2salescoeff double default 1,
	tech2purchcoeff double default 1,	
	PRIMARY KEY (ID)	 	
);
CREATE TABLE doc_header (
    ID BIGINT NOT NULL,
    code varchar(20) not null,
	doctype_code varchar(10) not null,	
	DESCRIPTION VARCHAR(80),
	fiscal_year INTEGER,
	doc_date date,
	delivery_date date,
	parent_doc_code varchar(20),
	partner_doc_code varchar(40),	
	PRIMARY KEY (ID)	 	
);
CREATE TABLE doc_row (
    ID BIGINT NOT NULL, 
	code varchar(20) not null,
	doc_header_id BIGINT not null,
	parent_row_id  BIGINT, 
	warehouse_id bigint,
	rownumber integer,
	delivery_ts TIMESTAMP,
	asked_delivery_ts TIMESTAMP,
	delivered_ts TIMESTAMP,
	DESCRIPTION VARCHAR(80),
	item_id bigint NOT NULL,
	unitary_price double,
	UM_CODE varchar(4) NOT NULL,
	qty double default 0,
	qty_done double default 0,
	qty_residual double default 0,
	total_amount double default 0,
	PRIMARY KEY (ID)	
);
CREATE TABLE MOVMETATYPE (
	code varchar(20) not null,
	description VARCHAR(80),
	operation_src_war char(2) default '-',
	operation_dst_war char(2) default '+',
	PRIMARY KEY (CODE)
);
CREATE TABLE MOVTYPE (
    code varchar(20) not null,
	description VARCHAR(80),
	operation_src_war char(2) default '-',
	operation_dst_war char(2) default '+',
	movmetatype_code varchar(20) not null,
	PRIMARY KEY (CODE)
);
CREATE TABLE ORG_COMPANY (
    ID BIGINT NOT NULL,
    code varchar(20) not null,
	description VARCHAR(80),
	PRIMARY KEY (ID)
);
CREATE TABLE ORG_PLANT (
	ID BIGINT NOT NULL,
    code varchar(20) not null,
	description VARCHAR(80),
	org_company_id bigint not null,
	PRIMARY KEY (ID)
);
CREATE TABLE ORG_WAREHOUSE (
	ID BIGINT NOT NULL,
    code varchar(20) not null,
	description VARCHAR(80),
	org_plant_id bigint not null,
	PRIMARY KEY (ID)
);
CREATE TABLE WAREINVENTORY (
    ID BIGINT NOT NULL,
	org_warehouse_id bigint not null,
	item_id bigint not null,
	phys_stock double default 0,
	avail_stock double default 0,
	reserved_stock double default 0,
	on_acquire_order double default 0,
	um_code VARCHAR(4) NOT NULL,
	PRIMARY KEY (ID)		
);
CREATE TABLE LOT (
	ID BIGINT NOT NULL,
	item_id BIGINT NOT NULL,
	partner_id bigint NOT NULL,
	org_company_id bigint not null,
	CODE VARCHAR(80) NOT NULL,
	DESCRIPTION VARCHAR(255) NOT NULL,
	PRIMARY KEY (ID)		
);
CREATE TABLE WAREMOVEMENT (
    ID BIGINT NOT NULL,
	code varchar(20) not null,
	movtype_code varchar(20) not null,
	description VARCHAR(80),
	item_id BIGINT NOT NULL,
	document_row_id bigint not null,
	warehouse_id_src bigint NOT NULL,
	warehouse_id_dest bigint NOT NULL,	
	unitary_price double,
	moved_qty double,
	moved_value double,
	mov_ts  timestamp,
	mov_date date,
	UM_CODE varchar(20) NOT NULL,
	PRIMARY KEY (ID)	
);
CREATE TABLE WARTRACING (
	ID BIGINT NOT NULL,
	waremovement_id bigint not null,
	lot_id BIGINT NOT NULL,
	moved_qty double,
	moved_value double,
	serial varchar(80),
	UM_CODE varchar(20) NOT NULL,
	PRIMARY KEY (ID)	
);
