import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignLineToVehicleComponent } from './assign-line-to-vehicle.component';

describe('AssignLineToVehicleComponent', () => {
  let component: AssignLineToVehicleComponent;
  let fixture: ComponentFixture<AssignLineToVehicleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignLineToVehicleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignLineToVehicleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
