-- abbreviation rules:
-- timestamp --> ts
-- product --> prd
-- specification --> spec
-- planned --> pld
-- secondary_resource --> rc
-- meta_info --> meta
-- produced --> prdcd
-- predefined --> pred
-- machine --> mac
-- delivery --> del
-- department --> dept
-- _date_time --> _dt
-- consumer --> consmr
-- producer --> prdcr
-- supplier --> suplr
-- purchase --> purch
-- operation --> op
-- equipment --> equip
-- timesheet --> tsheet
-- change --> chng
-- exception --> exc
-- consuming --> cons
-- warehouse --> whouse
-- end abbreviation rules
-- table for class ApsWindow
CREATE TABLE aps_window(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field endDateTime
end_dt TIMESTAMP,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field startDateTime
start_dt TIMESTAMP,
PRIMARY KEY (code)
);
-- table for class TimesheetMetaInfo
create table tsheet_meta(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
PRIMARY KEY (code)
);
-- table for class ProductiveCompany
CREATE TABLE prdive_company(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field address
address varchar(254),
-- field city
city varchar(254),
-- field companyName
company_name varchar(254),
-- field country
country varchar(254),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field provincia
provincia varchar(254),
-- field zipCode
zip_code varchar(254),
PRIMARY KEY (code)
);
-- table for class Plant
CREATE TABLE plant(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field productiveCompanyCode
prdive_company_code varchar(30),
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(30),
PRIMARY KEY (code)
);
-- table for class Department
CREATE TABLE dept(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(30),
PRIMARY KEY (code)
);
-- table for class WorkCenter
CREATE TABLE work_center(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(30),
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(30),
PRIMARY KEY (code)
);
-- table for class Machine
CREATE TABLE mac(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field disabled
disabled BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(30),
-- field workCenterCode
work_center_code varchar(30),
PRIMARY KEY (code)
);
-- table for class ChangeOverMatrixItem
CREATE TABLE chng_over_matrix_item(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field machineCode
mac_code varchar(30),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field setupGroupCodeFrom
setup_group_code_from varchar(254),
-- field setupGroupCodeTo
setup_group_code_to varchar(254),
-- field setupTime
setup_time DOUBLE PRECISION,
-- field workCenterCode
work_center_code varchar(30),
PRIMARY KEY (code)
);
-- table for class ResourceGroup
CREATE TABLE resource_group(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(30),
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field resourcesNumber
resources_number INTEGER,
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(30),
PRIMARY KEY (code)
);
-- table for class SecondaryResource
CREATE TABLE rc(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field disabled
disabled BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field resourceGroupCode
resource_group_code varchar(30),
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(30),
PRIMARY KEY (code)
);
-- table for class Warehouse
CREATE TABLE whouse(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(30),
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(30),
PRIMARY KEY (code)
);
-- table for class Product
CREATE TABLE prd(
code VARCHAR(254) NOT NULL,
description varchar(254),
-- field averageMinPurchaseQty
average_min_purch_qty DOUBLE PRECISION,
-- field canBeProducedByScheduler
can_be_prdcd_by_scheduler BOOLEAN,
-- field canBePurchasedByScheduler
can_be_purchd_by_scheduler BOOLEAN,
-- field removed
removed BOOLEAN,
-- field leadTimeDays
lead_time_days INTEGER,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
PRIMARY KEY (code)
);
-- table for class StockSupply
CREATE TABLE stock_supply(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field physicalStockQuantity
physical_stock_quantity DOUBLE PRECISION,
-- field productCode
prd_code varchar(254),
-- field warehouseCode
whouse_code varchar(30),

PRIMARY KEY (code)
);
-- table for class CycleModel
CREATE TABLE cycle_model(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field defaultProductCycle
default_prd_cycle BOOLEAN,
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(30),
-- field productCode
prd_code varchar(254),
-- field warehouseCode
whouse_code varchar(30),
PRIMARY KEY (code)
);
-- table for class SalesOrder
CREATE TABLE sales_order(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field customPriority
custom_priority INTEGER,
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(30),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderStatus
order_status varchar(254),
-- field orderType
order_type varchar(254),
-- field partner
partner varchar(254),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code varchar(30),
PRIMARY KEY (code)
);
-- table for class PurchaseOrder
CREATE TABLE purch_order(
code VARCHAR(254) NOT NULL,
description varchar(254),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field customPriority
custom_priority INTEGER,
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(30),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderStatus
order_status varchar(254),
-- field orderType
order_type varchar(254),
-- field partner
partner varchar(254),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code varchar(30),
PRIMARY KEY (code)
);
-- table for class ProductiveCompanyProductSetting
CREATE TABLE prdive_company_prd_setting(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field produced
prdcd BOOLEAN,
-- field productCode
prd_code varchar(254),
-- field productionBySchedulerEnabled
prdion_by_scheduler_enabled BOOLEAN,
-- field productiveCompanyCode
prdive_company_code varchar(30),
-- field purchaseBySchedulerEnabled
purch_by_scheduler_enabled BOOLEAN,
-- field purchased
purchd BOOLEAN,
PRIMARY KEY (code)
);
-- table for class PlantProductSetting
CREATE TABLE plant_prd_setting(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(30),
-- field produced
prdcd BOOLEAN,
-- field productCode
prd_code varchar(254),
-- field productionBySchedulerEnabled
prdion_by_scheduler_enabled BOOLEAN,
-- field purchaseBySchedulerEnabled
purch_by_scheduler_enabled BOOLEAN,
-- field purchased
purchd BOOLEAN,
PRIMARY KEY (code)
);
-- table for class WarehouseProductSetting
CREATE TABLE whouse_prd_setting(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field averageMinPurchaseQty
average_min_purch_qty DOUBLE PRECISION,
-- field averageProductionDays
average_prdion_days INTEGER,
-- field averageleadTimeDays
averagelead_time_days INTEGER,
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field produceAccordingToReorderLevel
produce_according_to_reorder_level BOOLEAN,
-- field produced
prdcd BOOLEAN,
-- field productCode
prd_code varchar(254),
-- field productionBySchedulerEnabled
prdion_by_scheduler_enabled BOOLEAN,
-- field purchaseAccordingToReorderLevel
purch_according_to_reorder_level BOOLEAN,
-- field purchaseBySchedulerEnabled
purch_by_scheduler_enabled BOOLEAN,
-- field purchased
purchd BOOLEAN,
-- field securityStock
security_stock DOUBLE PRECISION,
-- field warehouseCode
whouse_code varchar(30),
PRIMARY KEY (code)
);
-- table for class SalesOrderLine
CREATE TABLE sales_order_line(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field color
color varchar(254),
-- field completedQty
completed_qty DOUBLE PRECISION,
-- field customPriority
custom_priority INTEGER,
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(30),
-- field explodeWithCycleCode
explode_with_cycle_code varchar(30),
-- field explodeWorkOrders
explode_work_orders BOOLEAN,
-- field lineStatus
line_status varchar(254),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderCode
order_code varchar(254),
-- field orderType
order_type varchar(254),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code varchar(30),
-- field productCode
prd_code varchar(254),
-- field residualQty
residual_qty DOUBLE PRECISION,
-- field totalQty
total_qty DOUBLE PRECISION,
-- field warehouseCode
whouse_code varchar(30),
PRIMARY KEY (code)
);



-- table for class PurchaseOrderLine
CREATE TABLE purch_order_line(
code VARCHAR(254) NOT NULL,
description varchar(254),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field color
color varchar(254),
-- field completedQty
completed_qty DOUBLE PRECISION,
-- field customPriority
custom_priority INTEGER,
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(30),
-- field lineStatus
line_status varchar(254),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderCode
order_code VARCHAR(30),
-- field orderType
order_type varchar(254),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code varchar(30),
-- field productCode
prd_code varchar(254),
-- field residualQty
residual_qty DOUBLE PRECISION,
-- field totalQty
total_qty DOUBLE PRECISION,
-- field warehouseCode
whouse_code varchar(30),
PRIMARY KEY (code)
);
-- table for class WorkOrder
CREATE TABLE work_order(
code VARCHAR(254) NOT NULL,
description varchar(254),
-- field color
color varchar(254),
-- field removed
removed BOOLEAN,
-- field deliveryDate
del_date TIMESTAMP,
-- field endExecutionDate
end_execution_date TIMESTAMP,
-- field idx
idx INTEGER,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(30),
-- field productCode
prd_code varchar(254),
-- field salesOrderLineCode
sales_order_line_code varchar(30),
-- field startExecutionDate
start_execution_date TIMESTAMP,
-- field totalQty
total_qty DOUBLE PRECISION,
PRIMARY KEY (code)
);
alter table work_order add column cycle_code varchar(30);
alter table work_order add column root_task boolean;
-- table for class Task
CREATE TABLE task(
code VARCHAR(254) NOT NULL PRIMARY KEY,
description varchar(254),
-- field cycleCode
cycle_code varchar(30),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code varchar(30),
-- field predefinedMachineCode
pred_mac_code varchar(30),
-- field scheduledMachineCode
scheduled_mac_code varchar(30),
-- field sequenceCode
sequence_code varchar(254),
-- field successfullyScheduled
successfully_scheduled BOOLEAN,
-- field workCenterCode
work_center_code varchar(30),
-- field workOrderCode
work_order_code varchar(254) 

);
alter table task add forced_mac_code varchar(80);
alter table task add success_scheduled boolean;
alter table task add work_order_root boolean;
alter table task add start_preparation timestamp;
alter table task add end_preparation timestamp;
alter table task add start_execution timestamp;
alter table task add end_execution timestamp;
alter table TASK add column equip_spec_code varchar(254);	
alter table task add asked_del_time timestamp;
alter table task add sales_line_code varchar(254);
alter table task add qty_total double precision;
alter table task add qty_produced double precision;
alter table task add custom_priority integer;
alter table task add setup_time double precision;
alter table task add work_time double precision;
alter table TASK add column setup_group_code varchar(254);


-- table for class TaskRelation
CREATE TABLE task_relation(
code VARCHAR(50) NOT NULL,
description varchar(254),
-- field consumerTaskCode
consmr_task_code varchar(254),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field supplierTaskCode
suplr_task_code varchar(254),
PRIMARY KEY (code)
);
-- table for class TimesheetMetaInfoExceptionRule
CREATE TABLE tsheet_meta_exc_rule(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field endPeriod
end_period TIMESTAMP,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field startPeriod
start_period TIMESTAMP,
-- field working
working BOOLEAN,
tsheet_meta_code varchar(30),
PRIMARY KEY (code)
);
alter table task_relation add column alignment_type varchar(20);
alter table task_relation add column time_range_type varchar(10);
alter table task_relation add column  pegging_edge boolean;
alter table task_relation add column pegging_code varchar(40);
alter table task_relation add column offset_millisecs bigint;
alter table task_relation add column bom_item_code varchar(80);
alter table task_relation add column cons_transfer_type varchar(50);
alter table task_relation add column cons_batch_qty double precision;
-- table for class TimesheetMetaInfoWorkingTimeRule
CREATE TABLE tsheet_meta_working_time_rule(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field dayOfWeek
day_of_week INTEGER,
-- field removed
removed BOOLEAN,
-- field endTime
end_time TIME,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field startTime
start_time TIME,
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(30),
PRIMARY KEY (code)
);
-- table for class OperationModel
CREATE TABLE op_model(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field cycleCode
cycle_code varchar(30),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(30),
-- field sequenceCode
sequence_code varchar(254),
PRIMARY KEY (code)
);
alter table op_model  add column cons_transfer_type varchar(50);
alter table op_model  add column prd_transfer_type varchar(50);
alter table op_model  add column cons_batch_qty double precision;
alter table op_model  add column prd_batch_qty double precision;
-- table for class BomItemModel
CREATE TABLE bom_item_model(
code VARCHAR(80) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code varchar(30),
-- field requiredProductCode
required_prd_code varchar(254),
-- field useCoefficient
use_coefficient DOUBLE PRECISION,
-- field warehouseCode
whouse_code varchar(30),
PRIMARY KEY (code)
);
alter table bom_item_model  add column cons_transfer_type varchar(50);
alter table bom_item_model  add column cons_batch_qty double precision;
-- table for class CoProductItem
CREATE TABLE co_prd_item(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(30),
-- field producedQty
prdcd_qty DOUBLE PRECISION,
-- field productCode
prd_code varchar(254),
-- field warehouseCode
whouse_code varchar(30),
PRIMARY KEY (code)
);
ALTER TABLE co_prd_item add column op_code varchar(30);
-- table for class ConsumingBatchingInfo
CREATE TABLE cons_batching_info(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field batchQty
batch_qty DOUBLE PRECISION,
-- field batchTransferType
batch_transfer_type VARCHAR(60),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code varchar(30),
PRIMARY KEY (code)
);
-- table for class OperationEquipmentSpecification
CREATE TABLE op_equip_spec(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field machineTime
mac_time DOUBLE PRECISION,
-- field machineTimeSpec
mac_time_spec VARCHAR(60),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field setupGroupCode
setup_group_code varchar(254),
-- field setupTime
setup_time DOUBLE PRECISION,
-- field workCenterCode
work_center_code varchar(30),
PRIMARY KEY (code)
);
ALTER TABLE op_equip_spec add column op_code varchar(30);
-- table for class MachineEquipmentSpecification
CREATE TABLE mac_equip_spec(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field machineCode
mac_code varchar(30),
-- field machineTime
mac_time DOUBLE PRECISION,
-- field machineTimeSpec
mac_time_spec VARCHAR(60),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field priority
priority INTEGER,
-- field setupGroupCode
setup_group_code varchar(254),
-- field setupTime
setup_time DOUBLE PRECISION,
PRIMARY KEY (code)
);

ALTER TABLE mac_equip_spec add column op_code varchar(30);

-- table for class ProducingBatchingInfo
CREATE TABLE producing_batching_info(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field batchQty
batch_qty DOUBLE PRECISION,
-- field batchTransferType
batch_transfer_type VARCHAR(60),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code varchar(30),
PRIMARY KEY (code)
);
-- table for class BomItemBatchInfo
CREATE TABLE bom_item_batch_info(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field batchQty
batch_qty DOUBLE PRECISION,
-- field batchTransferType
batch_transfer_type VARCHAR(60),
-- field bomItemCode
bom_item_code varchar(80),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
PRIMARY KEY (code)
);
-- table for class MachinePriority
CREATE TABLE mac_priority(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field removed
removed BOOLEAN,
-- field machineCode
mac_code varchar(30),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field priority
priority INTEGER,
PRIMARY KEY (code)
);
ALTER TABLE mac_priority ADD COLUMN op_equip_spec_code varchar(80);
-- table for class SecondaryResourceUseSpecification
CREATE TABLE rc_use_spec(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field afterStartMinutes
after_start_minutes INTEGER,
-- field beforeStopMinutes
before_stop_minutes INTEGER,
-- field removed
removed BOOLEAN,
-- field maxQty
max_qty INTEGER,
-- field minQty
min_qty INTEGER,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field qty
qty INTEGER,
-- field secondaryResourceGroupCode
rc_group_code varchar(30),
-- field useType
use_type VARCHAR(60),
-- field usedTime
used_time VARCHAR(60),
PRIMARY KEY (code)
);
ALTER TABLE RC_USE_SPEC ADD COLUMN op_equip_spec_code varchar(80);

create table rc_group_reserv (
	code varchar(254) not null,
	description varchar(254),
	task_code varchar(254) not null,
	rc_code varchar(254),
	rc_group_code varchar(30) not null,
	start_reserv timestamp not null,
	end_reserv timestamp not null,	
	use_type varchar(10) not null,
	primary key(code),
	foreign key (task_code) references task(code) on delete cascade,
	foreign key (rc_group_code) references resource_group(code)  on delete cascade
);
alter table rc_group_reserv add column removed BOOLEAN;
alter table rc_group_reserv add column modified_ts TIMESTAMP;


create table whouse_picklist (
		code varchar(254) not null,
		description varchar(254) ,
		removed boolean,
		modified_ts timestamp,
		whouse_code varchar(30) not null,
		task_code varchar(254) not null,
		mac_code varchar(30) not null,
		work_center_code varchar(30) not null,
		start_transfer timestamp,
		end_transfer timestamp,
		transfer_type varchar(50),
		batch_qty double precision,
		nr_transfers double precision,
		qty_reserved double precision,
		prd_code varchar(254) not null,
		primary key(code),
		foreign key (whouse_code) references whouse(code) on delete cascade,
		foreign key (mac_code) references mac(code) on delete cascade,
		foreign key (task_code) references task(code) on delete cascade,
		foreign key (work_center_code) references work_center(code) on delete cascade,
		foreign key (prd_code) references prd(code) on delete cascade
);

create table task_picklist(
		code varchar(254) not null,
		description varchar(254) ,
		removed boolean,
		modified_ts timestamp,
		whouse_code varchar(30) not null,
		task_code varchar(254) not null,
		mac_code varchar(30) not null,
		work_center_code varchar(30) not null,
		supply_task varchar(254) not null,
		supply_work_order varchar(254) not null,
		start_transfer timestamp,
		end_transfer timestamp,
		transfer_type varchar(50),
		batch_qty double precision,
		nr_transfers double precision,
		qty_reserved double precision,
		prd_code varchar(254) not null,
		primary key(code),
		foreign key (whouse_code) references whouse(code) on delete cascade,
		foreign key (mac_code) references mac(code) on delete cascade,
		foreign key (task_code) references task(code) on delete cascade,
		foreign key (supply_task) references task(code) on delete cascade,
		foreign key (work_center_code) references work_center(code) on delete cascade,
		foreign key (supply_work_order) references work_order(code)  on delete cascade,
		foreign key (prd_code) references prd(code) on delete cascade
	);
create table purchase_picklist (
		code varchar(254) not null,
		description varchar(254) ,
		removed boolean,
		modified_ts timestamp,
		whouse_code varchar(30) not null,
		task_code varchar(254) not null,
		mac_code varchar(30) not null,
		work_center_code varchar(30) not null,
		purch_order varchar(254) not null,
		purch_order_line varchar(254) not null,		
		start_transfer timestamp,
		end_transfer timestamp,
		transfer_type varchar(50),
		batch_qty double precision,
		nr_transfers double precision,
		qty_reserved double precision,
		prd_code varchar(254) not null,
		primary key(code),
		foreign key (whouse_code) references whouse(code) on delete cascade,
		foreign key (mac_code) references mac(code) on delete cascade,
		foreign key (task_code) references task(code) on delete cascade,
		foreign key (work_center_code) references work_center(code) on delete cascade,
		foreign key (purch_order) references purch_order(code) on delete cascade,
		foreign key (purch_order_line) references purch_order_line(code) on delete cascade,
		foreign key (prd_code) references prd(code) on delete cascade
);
alter table task_relation add column consmr_worder_code varchar(254) ;
alter table task_relation add column suplr_worder_code varchar(254) ;


create table pegging(
		code varchar(40) not null,
		description varchar(254) ,
		removed boolean,
		modified_ts timestamp,
		cons_worder_code varchar(254),
	  	cons_task_code  varchar(254),
	  	prdcr_worder_code  varchar(254),
	  	prdcr_task_code  varchar(254),
	 	pegging_qty double precision,
		primary key(code),
		foreign key (cons_worder_code) references work_order(code) on delete cascade,
		foreign key (prdcr_worder_code) references work_order(code) on delete cascade,
		foreign key (cons_task_code) references task(code) on delete cascade,
		foreign key (prdcr_task_code) references task(code) on delete cascade
	);

create table scheduling_set (
	code VARCHAR(40) NOT NULL,
	description varchar(254),
-- field removed
	removed BOOLEAN,
	position integer ,
-- field modifiedTimestamp
	modified_ts TIMESTAMP,
	primary key(code)
);
create table scheduled_wo(
	code VARCHAR(40) NOT NULL,
	description varchar(254),
	-- field removed
	removed BOOLEAN,
	position integer,
	-- field modifiedTimestamp
	modified_ts TIMESTAMP,
	sched_set_code varchar(40) not null,
	work_order_code varchar(254) not null,
	primary key (code),
	foreign key (sched_set_code) references scheduling_set(code) on delete cascade,
	foreign key (work_order_code) references work_order(code) on delete cascade
);
ALTER TABLE scheduling_set add column options CLOB;
ALTER TABLE scheduling_set add column algo_dir varchar(20);
ALTER TABLE scheduling_set add column algo_type varchar(20);


-- mods after 25/05/2022

ALTER TABLE prd add column class1fam1 varchar(250);
ALTER TABLE prd add column class1fam2 varchar(250);
ALTER TABLE prd add column class1fam3 varchar(250);
ALTER TABLE prd add column class2fam1 varchar(250);
ALTER TABLE prd add column class2fam2 varchar(250);
ALTER TABLE prd add column class2fam3 varchar(250);
ALTER TABLE prd add column mov_unity varchar(3) default 'PZ';
ALTER TABLE prd add column purch_unity varchar(3) ;
ALTER TABLE prd add column mov2purch_coeff double precision;
ALTER TABLE prd add column reorder_qty double precision;
ALTER TABLE prd add column net_weight double precision;




ALTER TABLE prd add column lead_time double precision;


ALTER TABLE OP_EQUIP_SPEC ADD COLUMN min_next_phase_delay double precision default 0;
ALTER TABLE OP_EQUIP_SPEC ADD COLUMN max_next_phase_delay double precision  default 0;

ALTER TABLE MAC_EQUIP_SPEC ADD COLUMN min_next_phase_delay double precision default 0;
ALTER TABLE MAC_EQUIP_SPEC ADD COLUMN max_next_phase_delay double precision default 0;


ALTER TABLE OP_EQUIP_SPEC ADD COLUMN def_mac_code varchar(40);

CREATE TABLE partner(
code VARCHAR(30) NOT NULL,
description varchar(254),
-- field address
address varchar(254),
-- field city
city varchar(254),
-- field companyName
company_name varchar(254),
-- field country
country varchar(254),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field provincia
provincia varchar(254),
-- field zipCode
zip_code varchar(254),
PRIMARY KEY (code)
);


ALTER TABLE partner add column customer boolean default false;
ALTER TABLE partner add column supplier boolean default false;

ALTER TABLE aps_window ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE tsheet_meta ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE prdive_company ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE plant ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE dept ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE work_center ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE mac ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE chng_over_matrix_item ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE resource_group ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE rc ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE whouse ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE prd ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE stock_supply ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE cycle_model ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE sales_order ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE purch_order ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE prdive_company_prd_setting ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE plant_prd_setting ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE whouse_prd_setting ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE sales_order_line ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE purch_order_line ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE work_order ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE task ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE task_relation ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE tsheet_meta_exc_rule ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE tsheet_meta_working_time_rule ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE op_model ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE bom_item_model ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE co_prd_item ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE cons_batching_info ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE op_equip_spec ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE mac_equip_spec ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE producing_batching_info ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE bom_item_batch_info ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE mac_priority ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE rc_use_spec ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER table rc_group_reserv  ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER table whouse_picklist  ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER table task_picklist ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER table purchase_picklist  ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER table pegging ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER table scheduling_set  ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER table scheduled_wo ADD COLUMN INTEGRATION_TS TIMESTAMP ;
ALTER TABLE partner ADD COLUMN INTEGRATION_TS TIMESTAMP ;



CREATE TABLE OI40_INCREMENTAL_SYNC(
	ALGORITHM_ID VARCHAR(30) NOT NULL,
	TABLE_NAME varchar(254) NOT NULL,
	TS_PROPERTY varchar(254) NOT NULL,	
	TS_VALUE TIMESTAMP ,
	PRIMARY KEY(ALGORITHM_ID,TABLE_NAME,TS_PROPERTY)
);
CREATE TABLE OI40_DELETED_ENTRIES (
	code VARCHAR(30) NOT NULL,
	removed BOOLEAN,
	modified_ts TIMESTAMP,
	TABLENAME VARCHAR(40) NOT NULL,
	INTEGRATION_TS TIMESTAMP,
	content_type  varchar(50) not null,
	RECORD_VALUE CLOB,
	PRIMARY KEY (code)	
);
-- 15/07/2022
ALTER TABLE stock_supply add column infinite_capacity boolean;
-- 19/07/2022
ALTER TABLE task add column min_prd_date timestamp;
ALTER TABLE task add column max_prd_date timestamp;
ALTER TABLE purch_order_line add column min_prd_date timestamp;
ALTER TABLE purch_order_line add column max_prd_date timestamp;
ALTER TABLE sales_order_line add column min_prd_date timestamp;
ALTER TABLE sales_order_line add column max_prd_date timestamp;
ALTER TABLE work_order add column min_prd_date timestamp;
ALTER TABLE work_order add column max_prd_date timestamp;

-- 22/07/2022 
ALTER TABLE mac add column availability VARCHAR(10) default 'AVAILABLE';
ALTER TABLE rc add column availability VARCHAR(10) default 'AVAILABLE';

-- 05-01-2023

ALTER TABLE task add column status varchar(20);
ALTER TABLE task add column acq_prep_start TIMESTAMP;
ALTER TABLE task add column acq_prep_end TIMESTAMP;
ALTER TABLE task add column acq_prd_start TIMESTAMP;
ALTER TABLE task add column acq_prd_end TIMESTAMP;
ALTER TABLE task add column acq_prd_upd TIMESTAMP;
ALTER TABLE task add column acq_machine_code varchar(254);


CREATE TABLE acq_setup_resources (
		code varchar(40) NOT NULL PRIMARY KEY,
		description varchar(50),
		removed boolean default false,
		modified_ts timestamp,
		task_code varchar(254) NOT NULL,
		rc_codes varchar(1200),
		resource_group varchar(254)
);

CREATE TABLE acq_work_resources (
		code varchar(40) NOT NULL PRIMARY KEY,
		description varchar(50),
		removed boolean default false,
		modified_ts timestamp,
		task_code varchar(254) NOT NULL,
		rc_codes varchar(1200),
		resource_group varchar(254)
);


CREATE TABLE msg_spooler_entry(
	msg_entry_id bigint not null primary key,
	data_src_name varchar(40) not null,
	data_set_name varchar(40) not null,
	data_set_variant  varchar(40) not null,
	code varchar(40) not null,
	ts_message timestamp,
	ts_memorized timestamp,		
	msg_class_name varchar(512) not null,
	json_dump CLOB,
	processed_status varchar(20) not null,
	processed_ts timestamp	);
	
CREATE UNIQUE INDEX MSGSPENTRUNQ ON 	msg_spooler_entry(data_src_name,data_set_name,data_set_variant,code);
CREATE INDEX MSGSPENTRORD ON 	msg_spooler_entry(data_src_name asc,data_set_name asc,data_set_variant asc,ts_message asc,processed_status asc) ;




CREATE TABLE msg_spooler_proc(
            id bigint not null primary key,
	        msg_entry_id  bigint not null,
	        process_ok boolean default false,
	        error_code varchar(100),
	        error_msg CLOB,
	        processed_ts timestamp
);
CREATE  SEQUENCE msg_spooler_entry_seq  AS bigint INCREMENT by 1  START  WITH  1 ;
CREATE  SEQUENCE msg_spooler_proc_seq  AS bigint INCREMENT by 1  START  WITH  1 ;  
