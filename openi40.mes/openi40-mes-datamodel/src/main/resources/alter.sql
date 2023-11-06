ALTER TABLE MES_ASSET ADD CONSTRAINT FK_ASSET_TYPE FOREIGN KEY  (mes_asset_type_code) REFERENCES MES_ASSET_TYPE (CODE);
ALTER TABLE MES_ASSET ADD CONSTRAINT FK_ASSET_GROUP FOREIGN KEY (mes_asset_group_code) REFERENCES MES_ASSET_GROUP (CODE);
ALTER TABLE MES_ASSET ADD CONSTRAINT KF_ASSET_STATUS FOREIGN KEY (mes_asset_status_code) references MES_ASSET_STATUS (CODE); 


ALTER TABLE mes_task add constraint fk_mes_task foreign key  (aps_task_code) references aps_task(code);
ALTER TABLE mes_task_event add constraint fk_mes_tsk_evt_task foreign key (mes_task_code) references mes_task(code);
ALTER TABLE mes_task_event_equip add constraint fk_mes_tsk_evt_equip foreign key (event_id) references mes_task_event(id);
ALTER TABLE mes_task_material_event add constraint fk_mes_tsk_mat_equip foreign key (event_id) references mes_task_event(id);