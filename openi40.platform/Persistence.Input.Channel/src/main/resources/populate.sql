insert into aps_window (code,description,modified_ts,start_dt,end_dt) values ('APS_WINDOW','Aps window',now(),'2020-12-01T00:00:00','2021-06-01T23:59:59');
insert into tsheet_meta (code,description,modified_ts) values ('DEFAULT','Default working tsheet',now());
insert into tsheet_meta_working_time_rule (code,tsheet_meta_code,description,modified_ts,day_of_week,start_time,end_time) values ('LUNEDI-MATTINA','DEFAULT','lunedi mattina',now(),2,'08:00:00','13:00:00');
insert into tsheet_meta_working_time_rule (code,tsheet_meta_code,description,modified_ts,day_of_week,start_time,end_time) values ('LUNEDI-POMERIGGIO','DEFAULT','lunedi pomeriggio',now(),2,'13:00:00','19:00:00');

insert into tsheet_meta_working_time_rule (code,tsheet_meta_code,description,modified_ts,day_of_week,start_time,end_time) values ('MARTEDI-MATTINA','DEFAULT','martedi mattina',now(),3,'08:00:00','13:00:00');
insert into tsheet_meta_working_time_rule (code,tsheet_meta_code,description,modified_ts,day_of_week,start_time,end_time) values ('MARTEDI-POMERIGGIO','DEFAULT','martedi pomeriggio',now(),3,'13:00:00','19:00:00');

insert into tsheet_meta_working_time_rule (code,tsheet_meta_code,description,modified_ts,day_of_week,start_time,end_time) values ('MERCOLEDI-MATTINA','DEFAULT','mercoledi mattina',now(),4,'08:00:00','13:00:00');
insert into tsheet_meta_working_time_rule (code,tsheet_meta_code,description,modified_ts,day_of_week,start_time,end_time) values ('MERCOLEDI-POMERIGGIO','DEFAULT','mercoledi pomeriggio',now(),4,'13:00:00','19:00:00');

insert into tsheet_meta_working_time_rule (code,tsheet_meta_code,description,modified_ts,day_of_week,start_time,end_time) values ('GIOVEDI-MATTINA','DEFAULT','giovedi mattina',now(),5,'08:00:00','13:00:00');
insert into tsheet_meta_working_time_rule (code,tsheet_meta_code,description,modified_ts,day_of_week,start_time,end_time) values ('GIOVEDI-POMERIGGIO','DEFAULT','giovedi pomeriggio',now(),5,'13:00:00','19:00:00');

insert into tsheet_meta_working_time_rule (code,tsheet_meta_code,description,modified_ts,day_of_week,start_time,end_time) values ('VENERDI-MATTINA','DEFAULT','venerdi mattina',now(),6,'08:00:00','13:00:00');
insert into tsheet_meta_working_time_rule (code,tsheet_meta_code,description,modified_ts,day_of_week,start_time,end_time) values ('VENERDI-POMERIGGIO','DEFAULT','venerdi pomeriggio',now(),6,'13:00:00','19:00:00');

insert into tsheet_meta_exc_rule (code,tsheet_meta_code,description,modified_ts,working,start_period,end_period) values ('IMMACOLATA','DEFAULT','8 dicembre',now(),false,'2020-12-08T00:00:00','2020-12-09T00:00:00');
insert into tsheet_meta_exc_rule (code,tsheet_meta_code,description,modified_ts,working,start_period,end_period) values ('NATALE','DEFAULT','natale dicembre',now(),false,'2020-12-24T00:00:00','2020-12-26T00:00:00');
insert into tsheet_meta_exc_rule (code,tsheet_meta_code,description,modified_ts,working,start_period,end_period) values ('PRIMA_SETTIMANA_ANNO','DEFAULT','prima settimana annuale',now(),false,'2020-12-31T00:00:00','2021-01-07T00:00:00');
-- productive companies
insert into prdive_company (code,description,modified_ts,removed) values ('ACOMPANY002','Florida keys sport stainless steel company',now(),false);

-- plant
insert into plant (code,description,prdive_company_code,tsheet_meta_code,modified_ts,removed) values ('STB002','Sugarlof Stainless steel Plant','ACOMPANY002','DEFAULT',now(),false)

-- departments
insert into dept (code,description,plant_code,modified_ts,removed) values ('PRD002','Production department','STB002',now(),false);

-- product

insert into prd (code,description,average_min_purch_qty,can_be_prdcd_by_scheduler,can_be_purchd_by_scheduler,lead_time_days) values (
			 'INOXCLIP001',
			'Inox wire clip',
			120,
			false,
			 true,
			7
		),
		(
			'INOXREEL001',
			 'Inox reel',
			 120,
			 true,
			 false,
			 7
		),
		(
			 'AISI316SHEET600x600x3mm',
			 'Aisi 316 sheet 600 x 600 x 3mm',
			 120,
			 false,
			 true,
			 7
		),
		(
			 'SANDVICKSPINE7x1.5mm',
			 'Sandvick spine 7 x 1.5 mm',
			 120,
			 false,
			 true,
			 7
		),
		(
			 'SANDVICKSHEET600x600x1mm',
			 'Aisi 316 sheet 600 x 600 x 1mm',
			 120,
			 false,
			 true,
			 7
		),
		(
			 'SANDVICKSHAFTWING001',
			 'Sandvick shaft wing',
			 120,
			 true,
			 false,
			 7
		),
		(
			 'AISI316BAR500x50x15mm',
			 'Aisi 316 bar 500 x 50 x 15 mm',
			 120,
			 false,
			 true,
			 7
		),
		(
			 'SANDVIKROUNDBAR1500x7mm',
			 'Stainless steel sandwick round bar 1500 x 7 mm',
			 120,
			 false,
			 true,
			 7
		),
		(
			 'SANDVIKROUNDBAR20x3mm',
			 'Stainless steel sandwick round bar 20 x 3 mm',
			 120,
			 false,
			 true,
			 7
		),
		(
			 'AISI316ROUNDBAR200x4mm',
			 'Stainless steel aisi316 round bar 200 x 4 mm',
			 120,
			 false,
			 true,
			 7
		),
		(
			 'AISI316SPINE15x4mm',
			 'Stainless steel aisi316 spine 15 x 4 mm',
			 120,
			 true,
			 false,
			 7
		),
		(
			 'AISI316SPINE60x4mm',
			 'Stainless steel aisi316 spine 60 x 4 mm',
			 120,
			 true,
			 false,
			 7
		),
		(
			 'AISI316SQUARE50x50x3mm',
			 'Stainless steel aisi316 square 50x50x3mm',
			 120,
			 true,
			 false,
			 7
		),
		(
			 'FIRINGLEVER001',
			 'Inox trigger mechanism firing lever',
			 120,
			 true,
			 false,
			 7
		),
		(
			 'SHAFTHOLDER002',
			 'Inox trigger mechanism shaft holder',
			 120,
			 true,
			 false,
			 7
		),
		(
			 'TRIGGER001',
			 'Inox trigger mechanism',
			 120,
			 true,
			 false,
			 7
		),
		(
			 'SHAFT130CM',
			 'Shaft 130 cm',
			 120,
			 true,
			 false,
			 7
		),
		(
			 'SCREW001',
			 'Inox m4 screw',
			 120,
			 false,
			 true,
			 7
		);

-- secondary resource group
insert into resource_group (code,description,dept_code,resources_number ,tsheet_meta_code) values (
			 'EMPLOYEE-SS',
			 'General manifactury employee',
			 'PRD002',
			 15,
			 'DEFAULT'
		),
		(
			 'WELDING-EXPERTS',
			 'Cnc specialized employees',
			 'PRD002',
			 5,
			 'DEFAULT'
		);
		
-- work center 

insert into work_center (code,description,dept_code,tsheet_meta_code) values (
			 'SS-LASER-CUTTING-WKC',
			 'Stainless steel laser cutting workcenter',
			 'PRD002',
			 'DEFAULT'
		),
		(
			 'SS-MITER-SAW-WKC',
			 'Miter saw stations workcenter',
			 'PRD002',
			 'DEFAULT'
		),
		(
			 'SS-DRILLING-WKC',
			 'Drilling workcenter',
			 'PRD002',
			 'DEFAULT'
		),
		(
			 'SS-CNC-WKC',
			 'Stainless steel cnc workcenter',
			 'PRD002',
			 'DEFAULT'
		),
		(
			 'SS-BENDING-WKC',
			 'Stainless steel Bending workcenter',
			 'PRD002',
			 'DEFAULT'
		),
		(
			 'SS-ASSEMBLY-WKC',
			 'Stainless steel assembly workcenter',
			 'PRD002',
			 'DEFAULT'
		),
		(
			 'SS-TURNING-WKC',
			 'Turning workcenter',
			 'PRD002',
			 'DEFAULT'
		),
		(
			 'SS-WELDING-WKC',
			 'Stainless steel welding workcenter',
			 'PRD002',
			 'DEFAULT'
		);
-- machine 
		insert into mac (code,description,work_center_code,disabled,tsheet_meta_code) values (
			'SS-LASERCUTMACHINE-01',
			'Laser cut machine 01',
			'SS-LASER-CUTTING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-LASERCUTMACHINE-02',
			'Laser cut machine  02',
			'SS-LASER-CUTTING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-LASERCUTMACHINE-03',
			'Laser cut machine  03',
			'SS-LASER-CUTTING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-TURNING-WKC-01',
			'Turning machine 01',
			'SS-TURNING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-TURNING-WKC-02',
			'Turning cut machine  02',
			'SS-TURNING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-TURNING-WKC-03',
			'Turning cut machine  03',
			'SS-TURNING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-MITER-SAW-WKC-01',
			'Miter saw workstation 01',
			'SS-MITER-SAW-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-MITER-SAW-WKC-02',
			'Miter saw workstation 02',
			'SS-MITER-SAW-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-MITER-SAW-WKC-03',
			'Miter saw workstation 03',
			'SS-MITER-SAW-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-DRILLING-01',
			'Drill station 01',
			'SS-DRILLING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-DRILLING-02',
			'Drill station 02',
			'SS-DRILLING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-DRILLING-03',
			'Drill station 03',
			'SS-DRILLING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-BENDING-01',
			'Bending machine 01',
			'SS-BENDING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-BENDING-02',
			'Bending machine  02',
			'SS-BENDING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-BENDING-03',
			'Bending machine  03',
			'SS-BENDING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-CNC-01',
			'Metal cnc machine 01',
			'SS-CNC-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-CNC-02',
			'Metal cnc machine 02',
			'SS-CNC-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-CNC-03',
			'Metal cnc machine 03',
			'SS-CNC-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-ASSEMBLY-01',
			'Assembly workbench 01',
			'SS-ASSEMBLY-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-ASSEMBLY-02',
			'Assembly workbench 02',
			'SS-ASSEMBLY-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-ASSEMBLY-03',
			'Assembly workbench 03',
			'SS-ASSEMBLY-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-WELDING-01',
			'Welding station 01',
			'SS-WELDING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-WELDING-02',
			'Welding station 02',
			'SS-WELDING-WKC',
			false,
			'DEFAULT'
		),
		(
			'SS-WELDING-03',
			'Welding station 03',
			'SS-WELDING-WKC',
			false,
			'DEFAULT'
		);
--warehouse 
insert into whouse  (code,description,removed,infinite_capacity,modified_ts,plant_code,tsheet_meta_code) values ('WH002','Warehouse 00A',false,false,now(),'STB002','DEFAULT');
-- changeover matrix
insert into chng_over_matrix_item (code,description,setup_group_code_from,setup_group_code_to,work_center_code,setup_time) values (
			'TRG001-BND-TO-TRG001-BND',
			'Changeover from trigger to trigger bending, same equipment, just 1 Minutes',
			'TRIGGER001-BENDING',
			'TRIGGER001-BENDING',
			'SS-BENDING-WKC',
			1.0
		),
		(
			'SW-BND-TO-SW-BND',
			'Changeover from trigger to trigger bending, same equipment, just 1 Minutes',
			'SHAFTWING-BENDING',
			'SHAFTWING-BENDING',
			'SS-BENDING-WKC',
			1.0
		),
		(
			'TRG001-BND-TO-SW-BND',
			'Changeover from trigger to shaft wing, not same equipment, 5 Minutes',
			'TRIGGER001-BENDING',
			'SHAFTWING-BENDING',
			'SS-BENDING-WKC',
			5.0
		),
		(
			'SW-BND-TO-TRG001-BND',
			'Changeover from shaft wing to trigger bending, same equipment, just 3 Minutes',
			'SHAFTWING-BENDING',
			'TRIGGER001-BENDING',
			'SS-BENDING-WKC',
			3.0
		);
-- stock supply

insert into stock_supply (code,prd_code,description,whouse_code,physical_stock_quantity) values (
			'SSINVENTORY001',
			'AISI316SHEET600x600x3mm',
			'Aisi316 sheet AISI316SHEET600x600x3mm',
			'WH002',
			1000
		),
		(
			'SSINVENTORY002',
			'AISI316BAR500x50x15mm',
			'Aisi316 bar AISI316BAR500x50x15mm',
			'WH002',
			1000
		),
		(
			'SSINVENTORY003',
			'AISI316ROUNDBAR200x4mm',
			'Aisi316 round bar AISI316ROUNDBAR200x4mm',
			'WH002',
			1000
		),
		(
			'SSINVENTORY004',
			'SANDVIKROUNDBAR1500x7mm',
			'Sandvik round bar SANDVIKROUNDBAR1500x7mm',
			'WH002',
			1000
		),
		(
			'SSINVENTORY005',
			'SANDVIKROUNDBAR20x3mm',
			'Sandvik round bar SANDVIKROUNDBAR20x3mm',
			'WH002',
			1000
		),
		(
			'SSINVENTORY006',
			'SANDVICKSHEET600x600x1mm',
			'Sandvick sheet 600x600x1mm',
			'WH002',
			1000
		),
		(
			'SSINVENTORY007',
			'SANDVICKSPINE7x1.5mm',
			'Sandvick sping 7x1.5 mm',
			'WH002',
			1000
		);
-- sales order		
insert into sales_order (code,plant_code ,description,order_type ,dept_code) values ('SS/ORD003-2020','STB002','Sales order SS/ORD003-2020','SALES','PRD002'),('SS/ORD004-2020','STB002','Sales order SS/ORD004-2020','SALES','PRD002');
-- sales order line
insert into sales_order_line (code,order_code,plant_code ,whouse_code ,asked_del_date ,prd_code ,residual_qty ,completed_qty ,total_qty ,explode_work_orders ,color  ) values (
					'SS/ORD003-2020/001',
					'SS/ORD003-2020',
					'STB002',
					'WH002',
					'2020-12-06T10:00:00',
					'TRIGGER001',
					 150,
					 0,
					 150,
					true,
					 'rgb(57, 252, 64)'
				),
				(
					'SS/ORD003-2020/002',
					'SS/ORD003-2020',
					'STB002',
					'WH002',
					'2020-12-10T10:00:00',
					'SHAFT130CM',
					 3,
					 0,
					 3,
					true,
					 '#00b399'
				),
				(
					'SS/ORD003-2020/003',
					'SS/ORD003-2020',
					'STB002',
					'WH002',
					'2020-12-10T10:00:00',
					'SANDVICKSHAFTWING001',
					 3,
					 0,
					 3,
					false,
					 '#00b399'
				),(
					'SS/ORD004-2020/001',
					'SS/ORD004-2020',
					'STB002',
					'WH002',
					'2020-12-09T10:00:00',
					'TRIGGER001',
					 200,
					 0,
					 200,
					true,
					 'rgb(166, 57, 252)'
				),
				(
					'SS/ORD004-2020/002',
					'SS/ORD004-2020',
					'STB002',
					'WH002',
					'2020-12-11T10:00:00',
					'SHAFT130CM',
					 30,
					 0,
					 30,
					true,
					 '#3992FC'
				);
-- cycle-model				
insert into cycle_model (code,description ,prd_code,plant_code,whouse_code,default_prd_cycle) values (
			'CYCLE-AISI316SQUARE50x50x3mm',
			 'Cutting AISI316SQUARE50x50x3mm piece',
			 'AISI316SQUARE50x50x3mm',
			'STB002',
			'WH002',
			true
			
		),
		(
			'CYCLE-TRIGGER001',
			 'Trigger mechanism TRIGGER001 maching',
			 'TRIGGER001',
			'STB002',
			'WH002',
			true
			
		),
		(
			'CYCLE-FIRINGLEVER001',
			 'Cnc machining FIRINGLEVER001 piece',
			 'FIRINGLEVER001',
			'STB002',
			'WH002',
			true
			
		),
		(
			'CYCLE-AISI316SPINE15x4mm',
			 'Cutting AISI316SPINE15x4mm piece',
			 'AISI316SPINE15x4mm',
			'STB002',
			'WH002',
			true
			
		),
		(
			'CYCLE-AISI316SPINE60x4mm',
			 'Cutting AISI316SPINE60x4mm piece',
			 'AISI316SPINE60x4mm',
			'STB002',
			'WH002',
			true
			
		),
		(
			'CYCLE-SHAFTHOLDER002',
			 'Cutting SHAFTHOLDER002 piece',
			 'SHAFTHOLDER002',
			'STB002',
			'WH002',
			true
			
		),
		(
			'CYCLE-SANDVICKSHAFTWING001',
			 'Making shaft wing ',
			 'SANDVICKSHAFTWING001',
			'STB002',
			'WH002',
			true
			
		),(
			'CYCLE-SHAFT130CM',
			'Making shaft 130 CM',
			'SHAFT130CM',
			'STB002',
			'WH002',
			true
		);				
		
INSERT INTO op_model (code,cons_batch_qty ,cons_transfer_type ,cycle_code ,description,modified_ts ,plant_code,prd_batch_qty ,prd_transfer_type ,sequence_code) values ('OC-TRIGGER001-002',1.0,'CONTINUOUS','CYCLE-TRIGGER001','4 drills of 4mm for spine insertions',null,null,1.0,'CONTINUOUS','003'),
('OC-SHAFT130CM-003',1.0,'CONTINUOUS','CYCLE-SHAFT130CM','Cutting fins',null,null,1.0,'CONTINUOUS','003'),
('OC-TRIGGER001-001',1.0,'CONTINUOUS','CYCLE-TRIGGER001','Cutting 15x30mm window in SQUARE50x50x3mm',null,null,1.0,'CONTINUOUS','001'),
('OC-FIRINGLEVER001-001',1.0,'CONTINUOUS','CYCLE-FIRINGLEVER001','Cutting firing lever',null,null,1.0,'CONTINUOUS','001'),
('OC-SHAFT130CM-002',1.0,'CONTINUOUS','CYCLE-SHAFT130CM','Making sharp point',null,null,1.0,'CONTINUOUS','002'),
('OC-AISI316SPINE15x4mm-001',1.0,'CONTINUOUS','CYCLE-AISI316SPINE15x4mm','Cutting AISI316SPINE15x4mm',null,null,1.0,'CONTINUOUS','001'),
('OC-SANDVICKSHAFTWING001-002',1.0,'CONTINUOUS','CYCLE-SANDVICKSHAFTWING001','Bending sandvick piece to obtain raw wing',null,null,1.0,'CONTINUOUS','002'),
('OC-SHAFT130CM-001',1.0,'CONTINUOUS','CYCLE-SHAFT130CM','Trunkating SANDVIKROUNDBAR1500x7mm for 130 cm length',null,null,1.0,'CONTINUOUS','001'),
('OC-TRIGGER001-003',1.0,'CONTINUOUS','CYCLE-TRIGGER001','Trigger mechanism assembly',null,null,1.0,'CONTINUOUS','004'),
('OC-SANDVICKSHAFTWING001-001',1.0,'CONTINUOUS','CYCLE-SANDVICKSHAFTWING001','Cutting sandvick sheet',null,null,1.0,'CONTINUOUS','001'),
('OC-SANDVICKSHAFTWING001-003',1.0,'CONTINUOUS','CYCLE-SANDVICKSHAFTWING001','Drilling raw wing for spine insertion',null,null,1.0,'CONTINUOUS','003'),
('OC-AISI316SPINE60x4mm-001',1.0,'CONTINUOUS','CYCLE-AISI316SPINE60x4mm','Cutting AISI316SPINE60x4mm',null,null,1.0,'CONTINUOUS','001'),
('OC-SHAFT130CM-006',1.0,'CONTINUOUS','CYCLE-SHAFT130CM','Assembly shaft wing and shaft together',null,null,1.0,'CONTINUOUS','006'),
('OC-SHAFT130CM-005',1.0,'CONTINUOUS','CYCLE-SHAFT130CM','Drilling line and blade holes',null,null,1.0,'CONTINUOUS','005'),
('OC-SHAFT130CM-004',1.0,'CONTINUOUS','CYCLE-SHAFT130CM','Welding fins to raw shaft',null,null,1.0,'CONTINUOUS','004'),
('OC-AISI316SQUARE50x50x3mm-001',1.0,'CONTINUOUS','CYCLE-AISI316SQUARE50x50x3mm','Cutting square aisi316 piece 50x50x3 mm',null,null,1.0,'CONTINUOUS','001'),
('OC-SHAFTHOLDER002-001',1.0,'CONTINUOUS','CYCLE-SHAFTHOLDER002','Shaping shaft holder',null,null,1.0,'CONTINUOUS','001');

update op_model set plant_code ='STB002';


INSERT INTO bom_item_model(code,cons_batch_qty ,cons_transfer_type ,description,modified_ts ,op_code,required_prd_code ,use_coefficient ,whouse_code) values 
('SHAFTHOLDER002-001-AISI316BAR500x50x15mm',1.0,'CONTINUOUS','',null,'OC-SHAFTHOLDER002-001','AISI316BAR500x50x15mm',0.025,'WH002'),
('SH130CM-001-BAR1500x7mm',1.0,'CONTINUOUS','',null,'OC-SHAFT130CM-001','SANDVIKROUNDBAR1500x7mm',1.0,'WH002'),
('AISQ50x50x3mm-001-SHEET600x600x3mm',1.0,'CONTINUOUS','',null,'OC-AISI316SQUARE50x50x3mm-001','AISI316SHEET600x600x3mm',0.01,'WH002'),
('TRG001-003-SPINE15x4mm',1.0,'CONTINUOUS','Spines for trigger mechanism assembly',null,'OC-TRIGGER001-003','AISI316SPINE15x4mm',2.0,'WH002'),
('TRG001-003-SPINE60x4mm',1.0,'CONTINUOUS','Spines for trigger mechanism assembly with the gun',null,'OC-TRIGGER001-003','AISI316SPINE60x4mm',2.0,'WH002'),
('SPINE15x4mm-001-BAR200x4mm',1.0,'CONTINUOUS','',null,'OC-AISI316SPINE15x4mm-001','AISI316ROUNDBAR200x4mm',0.076923,'WH002'),
('TRG001-003-SHFT002',1.0,'CONTINUOUS','Shaft holder piece',null,'OC-TRIGGER001-003','SHAFTHOLDER002',1.0,'WH002'),
('FR001-001-BAR500x50x15mm',1.0,'CONTINUOUS','',null,'OC-FIRINGLEVER001-001','AISI316BAR500x50x15mm',0.025,'WH002'),
('SHFT130CM-006-SDVK001',1.0,'CONTINUOUS','',null,'OC-SHAFT130CM-006','SANDVICKSHAFTWING001',1.0,'WH002'),
('TRG001-001-SQRS50x50x3mm',1.0,'CONTINUOUS','Cutting 15x30mm window in SQUARE50x50x3mm',null,'OC-TRIGGER001-001','AISI316SQUARE50x50x3mm',1.0,'WH002'),
('SHFT130CM-003-BAR20x3mm',1.0,'CONTINUOUS','',null,'OC-SHAFT130CM-003','SANDVIKROUNDBAR20x3mm',0.2,'WH002'),
('SHFT001-001-SHEET600x600x1mm',1.0,'CONTINUOUS','',null,'OC-SANDVICKSHAFTWING001-001','SANDVICKSHEET600x600x1mm',0.002,'WH002'),
('SHFT130CM-006-SPINE7x1.5mm',1.0,'CONTINUOUS','',null,'OC-SHAFT130CM-006','SANDVICKSPINE7x1.5mm',1.0,'WH002'),
('TRG001-003-FIRING001',1.0,'CONTINUOUS','Firing lever for trigger mechanism',null,'OC-TRIGGER001-003','FIRINGLEVER001',1.0,'WH002'),
('SPINE60x4mm-001-RNDBR200x4mm',1.0,'CONTINUOUS','',null,'OC-AISI316SPINE60x4mm-001','AISI316ROUNDBAR200x4mm',0.333,'WH002');



INSERT INTO op_equip_spec (code,description,mac_time ,mac_time_spec ,modified_ts ,op_code ,setup_group_code ,setup_time ,work_center_code) 
values 
 ('ES1',null,1.0,'TIME4PIECE',null,'OC-AISI316SQUARE50x50x3mm-001',null,10.0,'SS-LASER-CUTTING-WKC'),
('ES3',null,3.0,'TIME4PIECE',null,'OC-TRIGGER001-002','TRIGGER001-BENDING',11.0,'SS-BENDING-WKC'),
('ES2',null,1.0,'TIME4PIECE',null,'OC-TRIGGER001-001',null,1.0,'SS-LASER-CUTTING-WKC'),
('ES5',null,5.0,'TIME4PIECE',null,'OC-TRIGGER001-003',null,1.0,'SS-ASSEMBLY-WKC'),
('ES4',null,3.0,'TIME4PIECE',null,'OC-TRIGGER001-002',null,1.0,'SS-DRILLING-WKC'),
('ES7',null,3.0,'TIME4PIECE',null,'OC-AISI316SPINE15x4mm-001',null,3.0,'SS-MITER-SAW-WKC'),
('ES6',null,3.0,'TIME4PIECE',null,'OC-FIRINGLEVER001-001',null,10.0,'SS-CNC-WKC'),
('ES9',null,3.0,'TIME4PIECE',null,'OC-SHAFTHOLDER002-001',null,10.0,'SS-CNC-WKC'),
('ES8',null,3.0,'TIME4PIECE',null,'OC-AISI316SPINE60x4mm-001',null,3.0,'SS-MITER-SAW-WKC'),
('ES10',null,13.0,'TIME4PIECE',null,'OC-SANDVICKSHAFTWING001-001',null,10.0,'SS-LASER-CUTTING-WKC'),
('ES11',null,7.0,'TIME4PIECE',null,'OC-SANDVICKSHAFTWING001-002','SHAFTWING-BENDING',10.0,'SS-BENDING-WKC'),
('ES12',null,17.0,'TIME4PIECE',null,'OC-SANDVICKSHAFTWING001-003',null,10.0,'SS-DRILLING-WKC'),
('ES13',null,17.0,'TIME4PIECE',null,'OC-SHAFT130CM-001',null,10.0,'SS-MITER-SAW-WKC'),
('ES14',null,11.0,'TIME4PIECE',null,'OC-SHAFT130CM-002',null,10.0,'SS-TURNING-WKC'),
('ES15',null,5.0,'TIME4PIECE',null,'OC-SHAFT130CM-003',null,10.0,'SS-MITER-SAW-WKC'),
('ES16',null,17.0,'TIME4PIECE',null,'OC-SHAFT130CM-004',null,10.0,'SS-WELDING-WKC'),
('ES17',null,9.0,'TIME4PIECE',null,'OC-SHAFT130CM-005',null,10.0,'SS-DRILLING-WKC'),
('ES18',null,11.0,'TIME4PIECE',null,'OC-SHAFT130CM-006',null,10.0,'SS-ASSEMBLY-WKC');

INSERT INTO RC_USE_SPEC(after_start_minutes ,before_stop_minutes ,code,description,max_qty ,min_qty ,modified_ts ,qty,rc_group_code ,use_type ,used_time,op_equip_spec_code) values 
(0,0,'SR2-ES2',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES2'),
(0,0,'SR5-ES5',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES5'),
(0,0,'SR14-ES14',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES14'),
(0,0,'SR11-ES11',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES11'),
(0,0,'SR4-ES4',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES4'),
(0,0,'SR17-ES17',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES17'),
(0,0,'SR8-ES8',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES8'),
(0,0,'SR15-ES15',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES15'),
(0,0,'SR1-ES1',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES1'),
(0,0,'SR12-ES12',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES12'),
(0,0,'SR6-ES6',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES6'),
(0,0,'SR9-ES9',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES9'),
(0,0,'SR18-ES18',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES18'),
(0,0,'SR10-ES10',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES10'),
(0,0,'SR3-ES3',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES3'),
(0,0,'SR7-ES7',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES7'),
(0,0,'SR16-ES16',null,0,0,null,1,'WELDING-EXPERTS','CONSTANT','SETUP_WORK','ES16'),
(0,0,'SR13-ES13',null,0,0,null,1,'EMPLOYEE-SS','CONSTANT','SETUP_WORK','ES13');

insert into stock_supply(code,prd_code,description,whouse_code,physical_stock_quantity) values
(
			 'SSINVENTORY001',
			'AISI316SHEET600x600x3mm',
			'Aisi316 sheet AISI316SHEET600x600x3mm',
			'WH002',
			1000
		),
		(
			 'SSINVENTORY002',
			'AISI316BAR500x50x15mm',
			'Aisi316 bar AISI316BAR500x50x15mm',
			'WH002',
			1000
		),
		(
			 'SSINVENTORY003',
			'AISI316ROUNDBAR200x4mm',
			'Aisi316 round bar AISI316ROUNDBAR200x4mm',
			'WH002',
			1000
		),
		(
			 'SSINVENTORY004',
			'SANDVIKROUNDBAR1500x7mm',
			'Sandvik round bar SANDVIKROUNDBAR1500x7mm',
			'WH002',
			1000
		),
		(
			 'SSINVENTORY005',
			'SANDVIKROUNDBAR20x3mm',
			'Sandvik round bar SANDVIKROUNDBAR20x3mm',
			'WH002',
			1000
		),
		(
			 'SSINVENTORY006',
			'SANDVICKSHEET600x600x1mm',
			'Sandvick sheet 600x600x1mm',
			'WH002',
			1000
		),
		(
			 'SSINVENTORY007',
			'SANDVICKSPINE7x1.5mm',
			'Sandvick sping 7x1.5 mm',
			'WH002',
			1000
		);
