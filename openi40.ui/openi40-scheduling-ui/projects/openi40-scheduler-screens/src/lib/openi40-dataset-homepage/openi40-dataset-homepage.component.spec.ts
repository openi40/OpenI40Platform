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

import { Openi40DatasetHomepageComponent } from './openi40-dataset-homepage.component';

describe('Openi40DatasetHomepageComponent', () => {
  let component: Openi40DatasetHomepageComponent;
  let fixture: ComponentFixture<Openi40DatasetHomepageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Openi40DatasetHomepageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Openi40DatasetHomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
