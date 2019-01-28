import { TestBed } from '@angular/core/testing';

import { AssignLineToVehicleService } from './assign-line-to-vehicle.service';

describe('AssignLineToVehicleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AssignLineToVehicleService = TestBed.get(AssignLineToVehicleService);
    expect(service).toBeTruthy();
  });
});
