import {Injectable, InjectionToken,Inject} from '@angular/core';
import { UI } from '../ui-meta-description/ui-meta-description';
export const UI_CONFIG = new InjectionToken<UI>('ui-configuration');
@Injectable({providedIn:"root"})
export class UIConfigurationRepositoryService {
    constructor(@Inject(UI_CONFIG) private configs :UI[]){

    }
    public getConfig(configId:string):UI|undefined{
        let cfg:UI|undefined;
        if (this.configs && Array.isArray(this.configs)) {
            cfg=this.configs.find(ui=>ui.uniqueUiKey===configId);
        }
        return cfg;
    }
}