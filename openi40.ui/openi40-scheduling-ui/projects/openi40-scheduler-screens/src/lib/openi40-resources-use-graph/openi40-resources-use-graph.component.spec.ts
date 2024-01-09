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

import { Openi40ResourcesUseGraphComponent } from './openi40-resources-use-graph.component';

describe('Openi40ResourcesUseGraphComponent', () => {
  let component: Openi40ResourcesUseGraphComponent;
  let fixture: ComponentFixture<Openi40ResourcesUseGraphComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Openi40ResourcesUseGraphComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Openi40ResourcesUseGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
