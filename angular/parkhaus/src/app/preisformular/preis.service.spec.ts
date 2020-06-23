import { TestBed } from '@angular/core/testing';

import { PreisService } from './preis.service';

describe('PreisService', () => {
  let service: PreisService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PreisService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
