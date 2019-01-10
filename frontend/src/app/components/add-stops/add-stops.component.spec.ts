import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddStopsComponent } from './add-stops.component';

describe('AddStopsComponent', () => {
  let component: AddStopsComponent;
  let fixture: ComponentFixture<AddStopsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddStopsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddStopsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
