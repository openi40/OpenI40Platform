/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Openi40MaterialScenarioPanelComponent } from './openi40-material-scenario-panel.component';

describe('Openi40MaterialScenarioPanelComponent', () => {
  let component: Openi40MaterialScenarioPanelComponent;
  let fixture: ComponentFixture<Openi40MaterialScenarioPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Openi40MaterialScenarioPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Openi40MaterialScenarioPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
