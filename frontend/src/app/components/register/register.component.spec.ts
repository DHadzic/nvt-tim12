import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import {} from 'jasmine';

import { RegisterComponent } from './register.component';
import { FormsModule } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { Observable } from 'rxjs';
import { of } from 'rxjs';

fdescribe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let userService: any;
  let router: any;

  beforeEach(async(() => {
    let userService = {
      register : jasmine.createSpy('register').and.returnValue(of([]))
    }

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports : [ FormsModule ],
      declarations: [ RegisterComponent ],
      providers: [ { provide: UserService , useValue: userService},
                  { provide: Router , useValue: routerMock} ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    userService = TestBed.get(UserService);
    router = TestBed.get(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set error properties to true',() =>{
    component.passenger.username = "a";
    component.passenger.password = "a";
    component.passenger.password_r = "aa";
    component.passenger.name = "a";
    component.passenger.surname = "a";
    component.passenger.birthDate = "12-12-1000";

    component.handleValidation();

    expect(component.error_messages.username).toBe(true);
    expect(component.error_messages.password).toBe(true);
    expect(component.error_messages.password_r).toBe(true);
    expect(component.error_messages.name).toBe(true);
    expect(component.error_messages.surname).toBe(true);
    expect(component.error_messages.birthDate).toBe(true);
  });

  it('should set error properties to true 2',() =>{
    component.passenger.username = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    component.passenger.password = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    component.passenger.password_r = "a";
    component.passenger.name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    component.passenger.surname = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    component.passenger.birthDate = "12-12-3000";

    component.handleValidation();

    expect(component.error_messages.username).toBe(true);
    expect(component.error_messages.password).toBe(true);
    expect(component.error_messages.password_r).toBe(true);
    expect(component.error_messages.name).toBe(true);
    expect(component.error_messages.surname).toBe(true);
    expect(component.error_messages.birthDate).toBe(true);
  });

  it('should set error properties to false',() =>{
    component.passenger.username = "aaaaaaaaaaaa";
    component.passenger.password = "aaaaaaaaaaaa";
    component.passenger.password_r = "aaaaaaaaaaaa";
    component.passenger.name = "aaaaaaaaaaaa";
    component.passenger.surname = "aaaaaaaaaaaa";

    component.handleValidation();

    expect(component.error_messages.username).toBe(false);
    expect(component.error_messages.password).toBe(false);
    expect(component.error_messages.password_r).toBe(false);
    expect(component.error_messages.name).toBe(false);
    expect(component.error_messages.surname).toBe(false);
  });

  it('should call service set properties',() =>{
    spyOn(component, 'handleValidation').and.returnValue(true);
    component.passenger.username = "aaaaaaaaaaaa";
    component.passenger.password = "aaaaaaaaaaaa";
    component.passenger.password_r = "aaaaaaaaaaaa";
    component.passenger.name = "aaaaaaaaaaaa";
    component.passenger.surname = "aaaaaaaaaaaa";
    expect(component.error_messages.username).toBe(false);
    expect(component.error_messages.password).toBe(false);
    expect(component.error_messages.password_r).toBe(false);
    expect(component.error_messages.name).toBe(false);
    expect(component.error_messages.surname).toBe(false);

    component.register();

    expect(component.handleValidation).toHaveBeenCalled();
    expect(userService.register).toHaveBeenCalled();
    expect(router.navigate).toHaveBeenCalledWith(["/main"]);
  });

});
