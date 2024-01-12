import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Openi40CommonUiComponent } from './openi40-common-ui.component';

describe('Openi40CommonUiComponent', () => {
  let component: Openi40CommonUiComponent;
  let fixture: ComponentFixture<Openi40CommonUiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Openi40CommonUiComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Openi40CommonUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
