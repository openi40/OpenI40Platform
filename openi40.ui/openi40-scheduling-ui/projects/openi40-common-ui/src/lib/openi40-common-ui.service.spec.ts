import { TestBed } from '@angular/core/testing';

import { Openi40CommonUiService } from './openi40-common-ui.service';

describe('Openi40CommonUiService', () => {
  let service: Openi40CommonUiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Openi40CommonUiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
