import { TestBed } from '@angular/core/testing';

import { DeleteVehicleService } from './delete-vehicle.service';

describe('DeleteVehicleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeleteVehicleService = TestBed.get(DeleteVehicleService);
    expect(service).toBeTruthy();
  });
});
