import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteBusStopComponent } from './delete-bus-stop.component';

describe('DeleteBusStopComponent', () => {
  let component: DeleteBusStopComponent;
  let fixture: ComponentFixture<DeleteBusStopComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteBusStopComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteBusStopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
