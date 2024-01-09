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
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Openi40TasksDatatableComponent } from './openi40-tasks-datatable.component';

describe('Openi40TasksDatatableComponent', () => {
  let component: Openi40TasksDatatableComponent;
  let fixture: ComponentFixture<Openi40TasksDatatableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Openi40TasksDatatableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Openi40TasksDatatableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
