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
ALTER TABLE pegging ADD CONSTRAINT CNSTRNT50 foreign key (consmr_work_order_code) references work_order(code);
ALTER TABLE pegging ADD CONSTRAINT CNSTRNT51 foreign key (prdcr_work_order_code) references work_order(code);
ALTER TABLE op_model ADD CONSTRAINT CNSTRNT52 foreign key (cycle_code) references cycle_model(code);
ALTER TABLE op_model ADD CONSTRAINT CNSTRNT53 foreign key (plant_code) references plant(code);
ALTER TABLE sales_order_line ADD CONSTRAINT CNSTRNT54 foreign key (dept_code) references dept(code);
ALTER TABLE sales_order_line ADD CONSTRAINT CNSTRNT55 foreign key (plant_code) references plant(code);
ALTER TABLE sales_order_line ADD CONSTRAINT CNSTRNT56 foreign key (prd_code) references prd(code);
ALTER TABLE sales_order_line ADD CONSTRAINT CNSTRNT57 foreign key (whouse_code) references whouse(code);
ALTER TABLE bom_item_model ADD CONSTRAINT CNSTRNT58 foreign key (op_code) references op_model(code);
ALTER TABLE bom_item_model ADD CONSTRAINT CNSTRNT59 foreign key (required_prd_code) references prd(code);
ALTER TABLE bom_item_model ADD CONSTRAINT CNSTRNT60 foreign key (whouse_code) references whouse(code);
ALTER TABLE co_prd_item ADD CONSTRAINT CNSTRNT61 foreign key (op_code) references op_model(code);
ALTER TABLE co_prd_item ADD CONSTRAINT CNSTRNT62 foreign key (plant_code) references plant(code);
ALTER TABLE co_prd_item ADD CONSTRAINT CNSTRNT63 foreign key (prd_code) references prd(code);
ALTER TABLE co_prd_item ADD CONSTRAINT CNSTRNT64 foreign key (whouse_code) references whouse(code);
ALTER TABLE cons_batching_info ADD CONSTRAINT CNSTRNT65 foreign key (op_code) references op_model(code);
ALTER TABLE op_equip_spec ADD CONSTRAINT CNSTRNT66 foreign key (op_code) references op_model(code);
ALTER TABLE op_equip_spec ADD CONSTRAINT CNSTRNT67 foreign key (work_center_code) references work_center(code);
ALTER TABLE mac_equip_spec ADD CONSTRAINT CNSTRNT68 foreign key (mac_code) references mac(code);
ALTER TABLE mac_equip_spec ADD CONSTRAINT CNSTRNT69 foreign key (op_code) references op_model(code);
ALTER TABLE producing_batching_info ADD CONSTRAINT CNSTRNT70 foreign key (op_code) references op_model(code);
ALTER TABLE bom_item_batch_info ADD CONSTRAINT CNSTRNT71 foreign key (bom_item_code) references bom_item_model(code);
ALTER TABLE mac_priority ADD CONSTRAINT CNSTRNT72 foreign key (mac_code) references mac(code);
ALTER TABLE rc_use_spec ADD CONSTRAINT CNSTRNT73 foreign key (rc_group_code) references resource_group(code);
