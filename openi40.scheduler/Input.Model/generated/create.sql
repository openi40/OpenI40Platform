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
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
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
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
PRIMARY KEY (code)
);
-- table for class ProductiveCompany
CREATE TABLE prdive_company(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field address
address VARCHAR(253),
-- field city
city VARCHAR(253),
-- field companyName
company_name VARCHAR(253),
-- field country
country VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field provincia
provincia VARCHAR(253),
-- field zipCode
zip_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class Plant
CREATE TABLE plant(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field productiveCompanyCode
prdive_company_code VARCHAR(253),
-- field timesheetMetaInfoCode
tsheet_meta_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class Department
CREATE TABLE dept(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class WorkCenter
CREATE TABLE work_center(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field departmentCode
dept_code VARCHAR(253),
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field timesheetMetaInfoCode
tsheet_meta_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class Machine
CREATE TABLE mac(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field disabled
disabled BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field timesheetMetaInfoCode
tsheet_meta_code VARCHAR(253),
-- field workCenterCode
work_center_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class ChangeOverMatrixItem
CREATE TABLE chng_over_matrix_item(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field machineCode
mac_code VARCHAR(253),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field setupGroupCodeFrom
setup_group_code_from VARCHAR(253),
-- field setupGroupCodeTo
setup_group_code_to VARCHAR(253),
-- field setupTime
setup_time DOUBLE PRECISION,
-- field workCenterCode
work_center_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class ResourceGroup
CREATE TABLE resource_group(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field departmentCode
dept_code VARCHAR(253),
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field resourcesNumber
resources_number INTEGER,
-- field timesheetMetaInfoCode
tsheet_meta_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class SecondaryResource
CREATE TABLE rc(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field disabled
disabled BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field resourceGroupCode
resource_group_code VARCHAR(253),
-- field timesheetMetaInfoCode
tsheet_meta_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class Warehouse
CREATE TABLE whouse(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field infiniteCapacity
infinite_capacity BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code VARCHAR(253),
-- field timesheetMetaInfoCode
tsheet_meta_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class Product
CREATE TABLE prd(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field averageMinPurchaseQty
average_min_purch_qty DOUBLE PRECISION,
-- field canBeProducedByScheduler
can_be_prdcd_by_scheduler BOOLEAN,
-- field canBePurchasedByScheduler
can_be_purchd_by_scheduler BOOLEAN,
-- field deleted
deleted BOOLEAN,
-- field leadTimeDays
lead_time_days INTEGER,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
PRIMARY KEY (code)
);
-- table for class StockSupply
CREATE TABLE stock_supply(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field physicalStockQuantity
physical_stock_quantity DOUBLE PRECISION,
-- field productCode
prd_code VARCHAR(253),
-- field warehouseCode
whouse_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class CycleModel
CREATE TABLE cycle_model(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field defaultProductCycle
default_prd_cycle BOOLEAN,
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code VARCHAR(253),
-- field productCode
prd_code VARCHAR(253),
-- field warehouseCode
whouse_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class SalesOrder
CREATE TABLE sales_order(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field customPriority
custom_priority INTEGER,
-- field deleted
deleted BOOLEAN,
-- field departmentCode
dept_code VARCHAR(253),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderStatus
order_status VARCHAR(253),
-- field orderType
order_type VARCHAR(253),
-- field partner
partner VARCHAR(253),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class PurchaseOrder
CREATE TABLE purch_order(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field customPriority
custom_priority INTEGER,
-- field deleted
deleted BOOLEAN,
-- field departmentCode
dept_code VARCHAR(253),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderStatus
order_status VARCHAR(253),
-- field orderType
order_type VARCHAR(253),
-- field partner
partner VARCHAR(253),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class ProductiveCompanyProductSetting
CREATE TABLE prdive_company_prd_setting(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field produced
prdcd BOOLEAN,
-- field productCode
prd_code VARCHAR(253),
-- field productionBySchedulerEnabled
prdion_by_scheduler_enabled BOOLEAN,
-- field productiveCompanyCode
prdive_company_code VARCHAR(253),
-- field purchaseBySchedulerEnabled
purch_by_scheduler_enabled BOOLEAN,
-- field purchased
purchd BOOLEAN,
PRIMARY KEY (code)
);
-- table for class PlantProductSetting
CREATE TABLE plant_prd_setting(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code VARCHAR(253),
-- field produced
prdcd BOOLEAN,
-- field productCode
prd_code VARCHAR(253),
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
description VARCHAR(253),
-- field averageMinPurchaseQty
average_min_purch_qty DOUBLE PRECISION,
-- field averageProductionDays
average_prdion_days INTEGER,
-- field averageleadTimeDays
averagelead_time_days INTEGER,
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field produceAccordingToReorderLevel
produce_according_to_reorder_level BOOLEAN,
-- field produced
prdcd BOOLEAN,
-- field productCode
prd_code VARCHAR(253),
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
whouse_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class SalesOrderLine
CREATE TABLE sales_order_line(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field color
color VARCHAR(253),
-- field completedQty
completed_qty DOUBLE PRECISION,
-- field customPriority
custom_priority INTEGER,
-- field deleted
deleted BOOLEAN,
-- field departmentCode
dept_code VARCHAR(253),
-- field explodeWithCycleCode
explode_with_cycle_code VARCHAR(253),
-- field explodeWorkOrders
explode_work_orders BOOLEAN,
-- field lineStatus
line_status VARCHAR(253),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderCode
order_code VARCHAR(253),
-- field orderType
order_type VARCHAR(253),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code VARCHAR(253),
-- field productCode
prd_code VARCHAR(253),
-- field residualQty
residual_qty DOUBLE PRECISION,
-- field totalQty
total_qty DOUBLE PRECISION,
-- field warehouseCode
whouse_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class PurchaseOrderLine
CREATE TABLE purch_order_line(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field color
color VARCHAR(253),
-- field completedQty
completed_qty DOUBLE PRECISION,
-- field customPriority
custom_priority INTEGER,
-- field deleted
deleted BOOLEAN,
-- field departmentCode
dept_code VARCHAR(253),
-- field lineStatus
line_status VARCHAR(253),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderCode
order_code VARCHAR(253),
-- field orderType
order_type VARCHAR(253),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code VARCHAR(253),
-- field productCode
prd_code VARCHAR(253),
-- field residualQty
residual_qty DOUBLE PRECISION,
-- field totalQty
total_qty DOUBLE PRECISION,
-- field warehouseCode
whouse_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class WorkOrder
CREATE TABLE work_order(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field color
color VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field deliveryDate
del_date TIMESTAMP,
-- field endExecutionDate
end_execution_date TIMESTAMP,
-- field idx
idx INTEGER,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code VARCHAR(253),
-- field productCode
prd_code VARCHAR(253),
-- field salesOrderLineCode
sales_order_line_code VARCHAR(253),
-- field startExecutionDate
start_execution_date TIMESTAMP,
-- field totalQty
total_qty DOUBLE PRECISION,
PRIMARY KEY (code)
);
-- table for class Task
CREATE TABLE task(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field cycleCode
cycle_code VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code VARCHAR(253),
-- field predefinedMachineCode
pred_mac_code VARCHAR(253),
-- field scheduledMachineCode
scheduled_mac_code VARCHAR(253),
-- field sequenceCode
sequence_code VARCHAR(253),
-- field successfullyScheduled
successfully_scheduled BOOLEAN,
-- field workCenterCode
work_center_code VARCHAR(253),
-- field workOrderCode
work_order_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class TaskRelation
CREATE TABLE task_relation(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field consumerTaskCode
consmr_task_code VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field supplierTaskCode
suplr_task_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class Pegging
CREATE TABLE pegging(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field consumerWorkOrderCode
consmr_work_order_code VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field peggingQty
pegging_qty DOUBLE PRECISION,
-- field producerWorkOrderCode
prdcr_work_order_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class TimesheetMetaInfoExceptionRule
CREATE TABLE tsheet_meta_exc_rule(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field endPeriod
end_period TIMESTAMP,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field startPeriod
start_period TIMESTAMP,
-- field working
working BOOLEAN,
PRIMARY KEY (code)
);
-- table for class TimesheetMetaInfoWorkingTimeRule
CREATE TABLE tsheet_meta_working_time_rule(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field dayOfWeek
day_of_week INTEGER,
-- field deleted
deleted BOOLEAN,
-- field endTime
end_time TIMESTAMP,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field startTime
start_time TIMESTAMP,
PRIMARY KEY (code)
);
-- table for class OperationModel
CREATE TABLE op_model(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field cycleCode
cycle_code VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field plantCode
plant_code VARCHAR(253),
-- field sequenceCode
sequence_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class SalesOrderLine
CREATE TABLE sales_order_line(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field askedDeliveryDate
asked_del_date TIMESTAMP,
-- field color
color VARCHAR(253),
-- field completedQty
completed_qty DOUBLE PRECISION,
-- field customPriority
custom_priority INTEGER,
-- field deleted
deleted BOOLEAN,
-- field departmentCode
dept_code VARCHAR(253),
-- field explodeWithCycleCode
explode_with_cycle_code VARCHAR(253),
-- field explodeWorkOrders
explode_work_orders BOOLEAN,
-- field lineStatus
line_status VARCHAR(253),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field orderCode
order_code VARCHAR(253),
-- field orderType
order_type VARCHAR(253),
-- field plannedDeliveryDate
pld_del_date TIMESTAMP,
-- field plantCode
plant_code VARCHAR(253),
-- field productCode
prd_code VARCHAR(253),
-- field residualQty
residual_qty DOUBLE PRECISION,
-- field totalQty
total_qty DOUBLE PRECISION,
-- field warehouseCode
whouse_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class BomItemModel
CREATE TABLE bom_item_model(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code VARCHAR(253),
-- field requiredProductCode
required_prd_code VARCHAR(253),
-- field useCoefficient
use_coefficient DOUBLE PRECISION,
-- field warehouseCode
whouse_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class CoProductItem
CREATE TABLE co_prd_item(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code VARCHAR(253),
-- field plantCode
plant_code VARCHAR(253),
-- field producedQty
prdcd_qty DOUBLE PRECISION,
-- field productCode
prd_code VARCHAR(253),
-- field warehouseCode
whouse_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class ConsumingBatchingInfo
CREATE TABLE cons_batching_info(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field batchQty
batch_qty DOUBLE PRECISION,
-- field batchTransferType
batch_transfer_type VARCHAR(60),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class OperationEquipmentSpecification
CREATE TABLE op_equip_spec(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field machineTime
mac_time DOUBLE PRECISION,
-- field machineTimeSpec
mac_time_spec VARCHAR(60),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code VARCHAR(253),
-- field setupGroupCode
setup_group_code VARCHAR(253),
-- field setupTime
setup_time DOUBLE PRECISION,
-- field workCenterCode
work_center_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class MachineEquipmentSpecification
CREATE TABLE mac_equip_spec(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field machineCode
mac_code VARCHAR(253),
-- field machineTime
mac_time DOUBLE PRECISION,
-- field machineTimeSpec
mac_time_spec VARCHAR(60),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code VARCHAR(253),
-- field priority
priority INTEGER,
-- field setupGroupCode
setup_group_code VARCHAR(253),
-- field setupTime
setup_time DOUBLE PRECISION,
PRIMARY KEY (code)
);
-- table for class ProducingBatchingInfo
CREATE TABLE producing_batching_info(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field batchQty
batch_qty DOUBLE PRECISION,
-- field batchTransferType
batch_transfer_type VARCHAR(60),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field operationCode
op_code VARCHAR(253),
PRIMARY KEY (code)
);
-- table for class BomItemBatchInfo
CREATE TABLE bom_item_batch_info(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field batchQty
batch_qty DOUBLE PRECISION,
-- field batchTransferType
batch_transfer_type VARCHAR(60),
-- field bomItemCode
bom_item_code VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
PRIMARY KEY (code)
);
-- table for class MachinePriority
CREATE TABLE mac_priority(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field deleted
deleted BOOLEAN,
-- field machineCode
mac_code VARCHAR(253),
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field priority
priority INTEGER,
PRIMARY KEY (code)
);
-- table for class SecondaryResourceUseSpecification
CREATE TABLE rc_use_spec(
code VARCHAR(30) NOT NULL,
description VARCHAR(253),
-- field afterStartMinutes
after_start_minutes INTEGER,
-- field beforeStopMinutes
before_stop_minutes INTEGER,
-- field deleted
deleted BOOLEAN,
-- field maxQty
max_qty INTEGER,
-- field minQty
min_qty INTEGER,
-- field modifiedTimestamp
modified_ts TIMESTAMP,
-- field qty
qty INTEGER,
-- field secondaryResourceGroupCode
rc_group_code VARCHAR(253),
-- field useType
use_type VARCHAR(60),
-- field usedTime
used_time VARCHAR(60),
PRIMARY KEY (code)
);
