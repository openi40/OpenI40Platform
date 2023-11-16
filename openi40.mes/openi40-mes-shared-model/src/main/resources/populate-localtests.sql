insert into mes_asset_group (code,description,context_type,parent_object_code) values ('ASSGRP001','Asset group 01','MACHINE','SS-LASERCUTMACHINE-01'); 

insert into mes_asset_status (code,description) values ('ONLINE','Online');

insert into mes_asset_type(code,description) values ('SERIAL','Serial device'); 

insert into mes_asset (code,description,mes_asset_type_code,mes_asset_status_code,mes_asset_group_code,integration_id,protocol_type,integration_read_url)
values                ('SERIAL001','Serial RS885 to LAN adapter','SERIAL','ONLINE','ASSGRP001','MANUALLY-CONFIGURED-DEVICES','MQTT','zlansub')