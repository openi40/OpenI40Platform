CREATE TABLE aps_window(
code varchar(255) NOT NULL,
description varchar(255),
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
CREATE TABLE tsheet_meta(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
PRIMARY KEY (code)
);
-- table for class ProductiveCompany
CREATE TABLE prdive_company(
code varchar(255) NOT NULL,
description varchar(255),
-- field address
address varchar(255),
-- field city
city varchar(255),
-- field companyName
company_name varchar(255),
-- field country
country varchar(255),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field provincia
provincia varchar(255),
-- field zipCode
zip_code varchar(255),
PRIMARY KEY (code)
);
-- table for class Plant
CREATE TABLE plant(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field productiveCompanyCode
prdive_company_code varchar(255),
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(255),
PRIMARY KEY (code)
);
-- table for class Department
CREATE TABLE dept(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(255),
PRIMARY KEY (code)
);
-- table for class WorkCenter
CREATE TABLE work_center(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(255),
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(255),
PRIMARY KEY (code)
);
-- table for class Machine
CREATE TABLE mac(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field disabled
disabled BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(255),
-- field workCenterCode
work_center_code varchar(255),
PRIMARY KEY (code)
);
-- table for class ChangeOverMatrixItem
CREATE TABLE chng_over_matrix_item(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field machineCode
mac_code varchar(255),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field setupGroupCodeFrom
setup_group_code_from varchar(255),
-- field setupGroupCodeTo
setup_group_code_to varchar(255),
-- field setupTime
setup_time DOUBLE PRECISION,
-- field workCenterCode
work_center_code varchar(255),
PRIMARY KEY (code)
);
-- table for class ResourceGroup
CREATE TABLE resource_group(
code VARCHAR(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(255),
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field resourcesNumber
resources_number INTEGER,
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(255),
PRIMARY KEY (code)
);
-- table for class SecondaryResource
CREATE TABLE rc(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field disabled
disabled BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field resourceGroupCode
resource_group_code varchar(255),
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(255),
PRIMARY KEY (code)
);
-- table for class Warehouse
CREATE TABLE whouse(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(255),
-- field timesheetMetaInfoCode
tsheet_meta_code varchar(255),
PRIMARY KEY (code)
);
-- table for class Product
CREATE TABLE prd(
code varchar(255) NOT NULL,
description varchar(255),
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
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field physicalStockQuantity
physical_stock_quantity DOUBLE PRECISION,
-- field productCode
prd_code varchar(255),
-- field warehouseCode
whouse_code varchar(255),
PRIMARY KEY (code)
);
-- table for class CycleModel
CREATE TABLE cycle_model(
code varchar(255) NOT NULL,
description varchar(255),
-- field defaultProductCycle
default_prd_cycle BOOLEAN,
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(255),
-- field productCode
prd_code varchar(255),
-- field warehouseCode
whouse_code varchar(255),
PRIMARY KEY (code)
);
-- table for class SalesOrder
CREATE TABLE sales_order(
code varchar(255) NOT NULL,
description varchar(255),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field customPriority
custom_priority INTEGER,
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(255),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderStatus
order_status varchar(255),
-- field orderType
order_type varchar(255),
-- field partner
partner varchar(255),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code varchar(255),
PRIMARY KEY (code)
);
-- table for class PurchaseOrder
CREATE TABLE purch_order(
code varchar(255) NOT NULL,
description varchar(255),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field customPriority
custom_priority INTEGER,
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(255),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderStatus
order_status varchar(255),
-- field orderType
order_type varchar(255),
-- field partner
partner varchar(255),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code varchar(255),
PRIMARY KEY (code)
);
-- table for class ProductiveCompanyProductSetting
CREATE TABLE prdive_company_prd_setting(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field produced
prdcd BOOLEAN,
-- field productCode
prd_code varchar(255),
-- field productionBySchedulerEnabled
prdion_by_scheduler_enabled BOOLEAN,
-- field productiveCompanyCode
prdive_company_code varchar(255),
-- field purchaseBySchedulerEnabled
purch_by_scheduler_enabled BOOLEAN,
-- field purchased
purchd BOOLEAN,
PRIMARY KEY (code)
);
-- table for class PlantProductSetting
CREATE TABLE plant_prd_setting(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(255),
-- field produced
prdcd BOOLEAN,
-- field productCode
prd_code varchar(255),
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
code varchar(255) NOT NULL,
description varchar(255),
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
prd_code varchar(255),
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
whouse_code varchar(255),
PRIMARY KEY (code)
);
-- table for class SalesOrderLine
CREATE TABLE sales_order_line(
code varchar(255) NOT NULL,
description varchar(255),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field color
color varchar(255),
-- field completedQty
completed_qty DOUBLE PRECISION,
-- field customPriority
custom_priority INTEGER,
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(255),
-- field explodeWithCycleCode
explode_with_cycle_code varchar(255),
-- field explodeWorkOrders
explode_work_orders BOOLEAN,
-- field lineStatus
line_status varchar(255),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderCode
order_code varchar(255),
-- field orderType
order_type varchar(255),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code varchar(255),
-- field productCode
prd_code varchar(255),
-- field residualQty
residual_qty DOUBLE PRECISION,
-- field totalQty
total_qty DOUBLE PRECISION,
-- field warehouseCode
whouse_code varchar(255),
PRIMARY KEY (code)
);



-- table for class PurchaseOrderLine
CREATE TABLE purch_order_line(
code varchar(255) NOT NULL,
description varchar(255),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field color
color varchar(255),
-- field completedQty
completed_qty DOUBLE PRECISION,
-- field customPriority
custom_priority INTEGER,
-- field removed
removed BOOLEAN,
-- field departmentCode
dept_code varchar(255),
-- field lineStatus
line_status varchar(255),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderCode
order_code varchar(255),
-- field orderType
order_type varchar(255),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code varchar(255),
-- field productCode
prd_code varchar(255),
-- field residualQty
residual_qty DOUBLE PRECISION,
-- field totalQty
total_qty DOUBLE PRECISION,
-- field warehouseCode
whouse_code varchar(255),
PRIMARY KEY (code)
);
-- table for class WorkOrder
CREATE TABLE work_order(
code varchar(255) NOT NULL,
description varchar(255),
-- field color
color varchar(255),
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
plant_code varchar(255),
-- field productCode
prd_code varchar(255),
-- field salesOrderLineCode
sales_order_line_code varchar(255),
-- field startExecutionDate
start_execution_date TIMESTAMP,
-- field totalQty
total_qty DOUBLE PRECISION,
PRIMARY KEY (code)
);
alter table work_order add column cycle_code varchar(255);
alter table work_order add column root_task boolean;
-- table for class Task
CREATE TABLE task(
code varchar(255) NOT NULL,
description varchar(255),
-- field cycleCode
cycle_code varchar(255),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code varchar(255),
-- field predefinedMachineCode
pred_mac_code varchar(255),
-- field scheduledMachineCode
scheduled_mac_code varchar(255),
-- field sequenceCode
sequence_code varchar(255),
-- field successfullyScheduled
successfully_scheduled BOOLEAN,
-- field workCenterCode
work_center_code varchar(255),
-- field workOrderCode
work_order_code varchar(255),
PRIMARY KEY (code)
);
alter table task add forced_mac_code varchar(80);
alter table task add success_scheduled boolean;
alter table task add work_order_root boolean;
alter table task add start_preparation timestamp;
alter table task add end_preparation timestamp;
alter table task add start_execution timestamp;
alter table task add end_execution timestamp;
alter table TASK add column equip_spec_code varchar(255);	
alter table task add asked_del_time timestamp;
alter table task add sales_line_code varchar(255);
alter table task add qty_total double precision;
alter table task add qty_produced double precision;
alter table task add custom_priority integer;
alter table task add setup_time double precision;
alter table task add work_time double precision;
alter table TASK add column setup_group_code varchar(255);


-- table for class TaskRelation
CREATE TABLE task_relation(
code varchar(255) NOT NULL,
description varchar(255),
-- field consumerTaskCode
consmr_task_code varchar(255),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field supplierTaskCode
suplr_task_code varchar(255),
PRIMARY KEY (code)
);
-- table for class TimesheetMetaInfoExceptionRule
CREATE TABLE tsheet_meta_exc_rule(
code varchar(255) NOT NULL,
description varchar(255),
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
tsheet_meta_code varchar(255),
PRIMARY KEY (code)
);
alter table task_relation add column alignment_type varchar(20);
alter table task_relation add column time_range_type varchar(10);
alter table task_relation add column  pegging_edge boolean;
alter table task_relation add column pegging_code varchar(255);
alter table task_relation add column offset_millisecs bigint;
alter table task_relation add column bom_item_code varchar(255);
alter table task_relation add column cons_transfer_type varchar(255);
alter table task_relation add column cons_batch_qty double precision;
-- table for class TimesheetMetaInfoWorkingTimeRule
CREATE TABLE tsheet_meta_working_time_rule(
code varchar(255) NOT NULL,
description varchar(255),
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
tsheet_meta_code varchar(255),
PRIMARY KEY (code)
);
-- table for class OperationModel
CREATE TABLE op_model(
code varchar(255) NOT NULL,
description varchar(255),
-- field cycleCode
cycle_code varchar(255),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(255),
-- field sequenceCode
sequence_code varchar(255),
PRIMARY KEY (code)
);
alter table op_model  add column cons_transfer_type varchar(255);
alter table op_model  add column prd_transfer_type varchar(255);
alter table op_model  add column cons_batch_qty double precision;
alter table op_model  add column prd_batch_qty double precision;
-- table for class BomItemModel
CREATE TABLE bom_item_model(
code varchar(80) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code varchar(255),
-- field requiredProductCode
required_prd_code varchar(255),
-- field useCoefficient
use_coefficient DOUBLE PRECISION,
-- field warehouseCode
whouse_code varchar(255),
PRIMARY KEY (code)
);
alter table bom_item_model  add column cons_transfer_type varchar(255);
alter table bom_item_model  add column cons_batch_qty double precision;


-- table for class CoProductItem
CREATE TABLE co_prd_item(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code varchar(255),
-- field producedQty
prdcd_qty DOUBLE PRECISION,
-- field productCode
prd_code varchar(255),
-- field warehouseCode
whouse_code varchar(255),
PRIMARY KEY (code)
);
ALTER TABLE co_prd_item add column op_code varchar(80);
-- table for class ConsumingBatchingInfo
CREATE TABLE cons_batching_info(
code varchar(255) NOT NULL,
description varchar(255),
-- field batchQty
batch_qty DOUBLE PRECISION,
-- field batchTransferType
batch_transfer_type VARCHAR(60),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code varchar(255),
PRIMARY KEY (code)
);
-- table for class OperationEquipmentSpecification
CREATE TABLE op_equip_spec(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field machineTime
mac_time DOUBLE PRECISION,
-- field machineTimeSpec
mac_time_spec VARCHAR(60),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field setupGroupCode
setup_group_code varchar(255),
-- field setupTime
setup_time DOUBLE PRECISION,
-- field workCenterCode
work_center_code varchar(255),
PRIMARY KEY (code)
);
ALTER TABLE op_equip_spec add column op_code varchar(80);
-- table for class MachineEquipmentSpecification
CREATE TABLE mac_equip_spec(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field machineCode
mac_code varchar(255),
-- field machineTime
mac_time DOUBLE PRECISION,
-- field machineTimeSpec
mac_time_spec VARCHAR(60),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field priority
priority INTEGER,
-- field setupGroupCode
setup_group_code varchar(255),
-- field setupTime
setup_time DOUBLE PRECISION,
PRIMARY KEY (code)
);

ALTER TABLE mac_equip_spec add column op_code varchar(80);

-- table for class ProducingBatchingInfo
CREATE TABLE producing_batching_info(
code varchar(255) NOT NULL,
description varchar(255),
-- field batchQty
batch_qty DOUBLE PRECISION,
-- field batchTransferType
batch_transfer_type VARCHAR(60),
-- field removed
removed BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code varchar(255),
PRIMARY KEY (code)
);

-- table for class MachinePriority
CREATE TABLE mac_priority(
code varchar(255) NOT NULL,
description varchar(255),
-- field removed
removed BOOLEAN,
-- field machineCode
mac_code varchar(255),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field priority
priority INTEGER,
PRIMARY KEY (code)
);
ALTER TABLE mac_priority ADD COLUMN op_equip_spec_code varchar(80);
-- table for class SecondaryResourceUseSpecification
CREATE TABLE rc_use_spec(
code varchar(255) NOT NULL,
description varchar(255),
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
rc_group_code varchar(255),
-- field useType
use_type VARCHAR(60),
-- field usedTime
used_time VARCHAR(60),
PRIMARY KEY (code)
);
ALTER TABLE RC_USE_SPEC ADD COLUMN op_equip_spec_code varchar(80);

create table rc_group_reserv (
	code varchar(255) not null,
	description varchar(255),
	task_code varchar(255) not null,
	rc_code varchar(255),
	rc_group_code varchar(255) not null,
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
		code varchar(255) not null,
		description varchar(255) ,
		removed boolean,
		modified_ts timestamp,
		whouse_code varchar(255) not null,
		task_code varchar(255) not null,
		mac_code varchar(255) not null,
		work_center_code varchar(255) not null,
		start_transfer timestamp,
		end_transfer timestamp,
		transfer_type varchar(255),
		batch_qty double precision,
		nr_transfers double precision,
		qty_reserved double precision,
		prd_code varchar(255) not null,
		primary key(code),
		foreign key (whouse_code) references whouse(code) on delete cascade,
		foreign key (mac_code) references mac(code) on delete cascade,
		foreign key (task_code) references task(code) on delete cascade,
		foreign key (work_center_code) references work_center(code) on delete cascade,
		foreign key (prd_code) references prd(code) on delete cascade
);

create table task_picklist(
		code varchar(255) not null,
		description varchar(255) ,
		removed boolean,
		modified_ts timestamp,
		whouse_code varchar(255) not null,
		task_code varchar(255) not null,
		mac_code varchar(255) not null,
		work_center_code varchar(255) not null,
		supply_task varchar(255) not null,
		supply_work_order varchar(255) not null,
		start_transfer timestamp,
		end_transfer timestamp,
		transfer_type varchar(255),
		batch_qty double precision,
		nr_transfers double precision,
		qty_reserved double precision,
		prd_code varchar(255) not null,
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
		code varchar(255) not null,
		description varchar(255) ,
		removed boolean,
		modified_ts timestamp,
		whouse_code varchar(255) not null,
		task_code varchar(255) not null,
		mac_code varchar(255) not null,
		work_center_code varchar(255) not null,
		purch_order varchar(255) not null,
		purch_order_line varchar(255) not null,		
		start_transfer timestamp,
		end_transfer timestamp,
		transfer_type varchar(255),
		batch_qty double precision,
		nr_transfers double precision,
		qty_reserved double precision,
		prd_code varchar(255) not null,
		primary key(code),
		foreign key (whouse_code) references whouse(code) on delete cascade,
		foreign key (mac_code) references mac(code) on delete cascade,
		foreign key (task_code) references task(code) on delete cascade,
		foreign key (work_center_code) references work_center(code) on delete cascade,
		foreign key (purch_order) references purch_order(code) on delete cascade,
		foreign key (purch_order_line) references purch_order_line(code) on delete cascade,
		foreign key (prd_code) references prd(code) on delete cascade
);
alter table task_relation add column consmr_worder_code varchar(255) ;
alter table task_relation add column suplr_worder_code varchar(255) ;


create table pegging(
		code varchar(255) not null,
		description varchar(255) ,
		removed boolean,
		modified_ts timestamp,
		cons_worder_code varchar(255),
	  	cons_task_code  varchar(255),
	  	prdcr_worder_code  varchar(255),
	  	prdcr_task_code  varchar(255),
	 	pegging_qty double precision,
		primary key(code),
		foreign key (cons_worder_code) references work_order(code) on delete cascade,
		foreign key (prdcr_worder_code) references work_order(code) on delete cascade,
		foreign key (cons_task_code) references task(code) on delete cascade,
		foreign key (prdcr_task_code) references task(code) on delete cascade
	);

create table scheduling_set (
	code varchar(255) NOT NULL,
	description varchar(255),
-- field removed
	removed BOOLEAN,
	position integer ,
-- field modifiedTimestamp
	modified_ts TIMESTAMP,
	primary key(code)
);
create table scheduled_wo(
	code varchar(255) NOT NULL,
	description varchar(255),
	-- field removed
	removed BOOLEAN,
	position integer,
	-- field modifiedTimestamp
	modified_ts TIMESTAMP,
	sched_set_code varchar(255) not null,
	work_order_code varchar(255) not null,
	primary key (code),
	foreign key (sched_set_code) references scheduling_set(code) on delete cascade,
	foreign key (work_order_code) references work_order(code) on delete cascade
);
ALTER TABLE scheduling_set add column options clob;
ALTER TABLE scheduling_set add column algo_dir varchar(20);
ALTER TABLE scheduling_set add column algo_type varchar(20);

ALTER TABLE plant ADD CONSTRAINT CNSTRNT1 foreign key (prdive_company_code) references prdive_company(code);
ALTER TABLE plant ADD CONSTRAINT CNSTRNT2 foreign key (tsheet_meta_code) references tsheet_meta(code);
ALTER TABLE dept ADD CONSTRAINT CNSTRNT3 foreign key (plant_code) references plant(code);
ALTER TABLE work_center ADD CONSTRAINT CNSTRNT4 foreign key (dept_code) references dept(code);
ALTER TABLE work_center ADD CONSTRAINT CNSTRNT5 foreign key (tsheet_meta_code) references tsheet_meta(code);
ALTER TABLE mac ADD CONSTRAINT CNSTRNT6 foreign key (tsheet_meta_code) references tsheet_meta(code);
ALTER TABLE mac ADD CONSTRAINT CNSTRNT7 foreign key (work_center_code) references work_center(code);
ALTER TABLE chng_over_matrix_item ADD CONSTRAINT CNSTRNT8 foreign key (mac_code) references mac(code);
ALTER TABLE chng_over_matrix_item ADD CONSTRAINT CNSTRNT9 foreign key (work_center_code) references work_center(code);
ALTER TABLE resource_group ADD CONSTRAINT CNSTRNT10 foreign key (dept_code) references dept(code);
ALTER TABLE resource_group ADD CONSTRAINT CNSTRNT11 foreign key (tsheet_meta_code) references tsheet_meta(code);
ALTER TABLE rc ADD CONSTRAINT CNSTRNT12 foreign key (resource_group_code) references resource_group(code);
ALTER TABLE rc ADD CONSTRAINT CNSTRNT13 foreign key (tsheet_meta_code) references tsheet_meta(code);
ALTER TABLE whouse ADD CONSTRAINT CNSTRNT14 foreign key (plant_code) references plant(code);
ALTER TABLE whouse ADD CONSTRAINT CNSTRNT15 foreign key (tsheet_meta_code) references tsheet_meta(code);
ALTER TABLE stock_supply ADD CONSTRAINT CNSTRNT16 foreign key (prd_code) references prd(code);
ALTER TABLE stock_supply ADD CONSTRAINT CNSTRNT17 foreign key (whouse_code) references whouse(code);
ALTER TABLE cycle_model ADD CONSTRAINT CNSTRNT18 foreign key (plant_code) references plant(code);
ALTER TABLE cycle_model ADD CONSTRAINT CNSTRNT19 foreign key (prd_code) references prd(code);
ALTER TABLE cycle_model ADD CONSTRAINT CNSTRNT20 foreign key (whouse_code) references whouse(code);
ALTER TABLE sales_order ADD CONSTRAINT CNSTRNT21 foreign key (dept_code) references dept(code);
ALTER TABLE sales_order ADD CONSTRAINT CNSTRNT22 foreign key (plant_code) references plant(code);
ALTER TABLE purch_order ADD CONSTRAINT CNSTRNT23 foreign key (dept_code) references dept(code);
ALTER TABLE purch_order ADD CONSTRAINT CNSTRNT24 foreign key (plant_code) references plant(code);
ALTER TABLE prdive_company_prd_setting ADD CONSTRAINT CNSTRNT25 foreign key (prd_code) references prd(code);
ALTER TABLE prdive_company_prd_setting ADD CONSTRAINT CNSTRNT26 foreign key (prdive_company_code) references prdive_company(code);
ALTER TABLE plant_prd_setting ADD CONSTRAINT CNSTRNT27 foreign key (plant_code) references plant(code);
ALTER TABLE plant_prd_setting ADD CONSTRAINT CNSTRNT28 foreign key (prd_code) references prd(code);
ALTER TABLE whouse_prd_setting ADD CONSTRAINT CNSTRNT29 foreign key (prd_code) references prd(code);
ALTER TABLE whouse_prd_setting ADD CONSTRAINT CNSTRNT30 foreign key (whouse_code) references prdive_company(code);
ALTER TABLE sales_order_line ADD CONSTRAINT CNSTRNT31 foreign key (dept_code) references dept(code);
ALTER TABLE sales_order_line ADD CONSTRAINT CNSTRNT32 foreign key (plant_code) references plant(code);
ALTER TABLE sales_order_line ADD CONSTRAINT CNSTRNT33 foreign key (prd_code) references prd(code);
ALTER TABLE sales_order_line ADD CONSTRAINT CNSTRNT34 foreign key (whouse_code) references whouse(code);
ALTER TABLE purch_order_line ADD CONSTRAINT CNSTRNT35 foreign key (dept_code) references dept(code);
ALTER TABLE purch_order_line ADD CONSTRAINT CNSTRNT36 foreign key (plant_code) references plant(code);
ALTER TABLE purch_order_line ADD CONSTRAINT CNSTRNT37 foreign key (prd_code) references prd(code);
ALTER TABLE purch_order_line ADD CONSTRAINT CNSTRNT38 foreign key (whouse_code) references whouse(code);
ALTER TABLE work_order ADD CONSTRAINT CNSTRNT39 foreign key (plant_code) references plant(code);
ALTER TABLE work_order ADD CONSTRAINT CNSTRNT40 foreign key (prd_code) references prd(code);
ALTER TABLE work_order ADD CONSTRAINT CNSTRNT41 foreign key (sales_order_line_code) references sales_order_line(code);
ALTER TABLE task ADD CONSTRAINT CNSTRNT42 foreign key (cycle_code) references cycle_model(code);
ALTER TABLE task ADD CONSTRAINT CNSTRNT43 foreign key (op_code) references op_model(code);
ALTER TABLE task ADD CONSTRAINT CNSTRNT44 foreign key (pred_mac_code) references mac(code);
ALTER TABLE task ADD CONSTRAINT CNSTRNT45 foreign key (scheduled_mac_code) references mac(code);
ALTER TABLE task ADD CONSTRAINT CNSTRNT46 foreign key (work_center_code) references work_center(code);
ALTER TABLE task ADD CONSTRAINT CNSTRNT47 foreign key (work_order_code) references work_order(code);
ALTER TABLE task_relation ADD CONSTRAINT CNSTRNT48 foreign key (consmr_task_code) references task(code);
ALTER TABLE task_relation ADD CONSTRAINT CNSTRNT49 foreign key (suplr_task_code) references task(code);
ALTER TABLE op_model ADD CONSTRAINT CNSTRNT50 foreign key (cycle_code) references cycle_model(code);
ALTER TABLE op_model ADD CONSTRAINT CNSTRNT51 foreign key (plant_code) references plant(code);
ALTER TABLE bom_item_model ADD CONSTRAINT CNSTRNT52 foreign key (op_code) references op_model(code);
ALTER TABLE bom_item_model ADD CONSTRAINT CNSTRNT53 foreign key (required_prd_code) references prd(code);
ALTER TABLE bom_item_model ADD CONSTRAINT CNSTRNT54 foreign key (whouse_code) references whouse(code);
ALTER TABLE co_prd_item ADD CONSTRAINT CNSTRNT55 foreign key (plant_code) references plant(code);
ALTER TABLE co_prd_item ADD CONSTRAINT CNSTRNT56 foreign key (prd_code) references prd(code);
ALTER TABLE co_prd_item ADD CONSTRAINT CNSTRNT57 foreign key (whouse_code) references whouse(code);
ALTER TABLE cons_batching_info ADD CONSTRAINT CNSTRNT58 foreign key (op_code) references op_model(code);
ALTER TABLE op_equip_spec ADD CONSTRAINT CNSTRNT59 foreign key (work_center_code) references work_center(code);
ALTER TABLE mac_equip_spec ADD CONSTRAINT CNSTRNT60 foreign key (mac_code) references mac(code);
ALTER TABLE producing_batching_info ADD CONSTRAINT CNSTRNT61 foreign key (op_code) references op_model(code);

ALTER TABLE mac_priority ADD CONSTRAINT CNSTRNT63 foreign key (mac_code) references mac(code);
ALTER TABLE rc_use_spec ADD CONSTRAINT CNSTRNT64 foreign key (rc_group_code) references resource_group(code);