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

import { Openi40SchedulingSettingsWizardComponent } from './openi40-scheduling-settings-wizard.component';

describe('Openi40SchedulingSettingsWizardComponent', () => {
  let component: Openi40SchedulingSettingsWizardComponent;
  let fixture: ComponentFixture<Openi40SchedulingSettingsWizardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Openi40SchedulingSettingsWizardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Openi40SchedulingSettingsWizardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
