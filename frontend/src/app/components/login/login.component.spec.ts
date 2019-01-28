import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AuthenticationService } from '../../services/security/authentication-service.service';
import { Router } from '@angular/router';
import {} from 'jasmine';

import { LoginComponent } from './login.component';
import { compileComponent } from '@angular/core/src/render3/jit/directive';
import { FormsModule } from '@angular/forms';

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authenticationService: any;
  let router: any;

  beforeEach(async(() => {
    let authenticationServiceMock = {
      login : jasmine.createSpy('login')
    }

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports : [ FormsModule ],
      declarations: [ LoginComponent ],
      providers: [ { provide: AuthenticationService , useValue: authenticationServiceMock},
                  { provide: Router , useValue: routerMock} ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    authenticationService = TestBed.get(AuthenticationService);
    router = TestBed.get(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call service after sign in is clicked', () => {
    component.user.name = "user";
    component.user.password = "password";

    component.login();
    expect(authenticationService.login).toHaveBeenCalled();    
  });

  it('should navigate after successful login', () =>{
    component.handleLogin(true);
    expect(router.navigate).toHaveBeenCalledWith(
      ['/main']);
  });

  it('should read wrongUserNameOrPassword attribute as false', () =>{
    component.handleLogin(false);

    expect(component.wrongUsernameOrPass).toBe(true);
  });

});
