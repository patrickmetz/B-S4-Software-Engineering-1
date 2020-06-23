import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreisformularComponent } from './preisformular.component';

describe('PreisformularComponent', () => {
  let component: PreisformularComponent;
  let fixture: ComponentFixture<PreisformularComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreisformularComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreisformularComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
