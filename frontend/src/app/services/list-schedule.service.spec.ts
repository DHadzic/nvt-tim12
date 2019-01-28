import { TestBed } from '@angular/core/testing';

import { ListScheduleService } from './list-schedule.service';

describe('ListScheduleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ListScheduleService = TestBed.get(ListScheduleService);
    expect(service).toBeTruthy();
  });
});
