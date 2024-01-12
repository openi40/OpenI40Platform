import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { getBaseUrl } from './common-utils';
export interface UILoadedConfig {
  angularUI?:boolean;
  backofficeUI?:boolean;
  schedulerUI?:boolean;
}
@Injectable({
  providedIn: 'root'
})
export class Openi40CommonUiService {

  constructor(private http:HttpClient) { }
  public getUIConfiguration():Observable<UILoadedConfig> {
    const relativeUrl:string="/uiConfig/UIConfigController";
    const baseUrl=getBaseUrl();
    const cfgUrl=baseUrl + relativeUrl;
    return this.http.get<UILoadedConfig>(cfgUrl);
  }
}
