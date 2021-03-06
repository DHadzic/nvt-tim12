import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';

import { RouterModule,Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { AuthGuardGuard } from './services/security/auth-guard.guard';
import { JwtUtilsService } from './services/security/jwt-utils.service';
import { HttpClientModule } from '@angular/common/http'; 
import { AuthenticationService } from './services/security/authentication-service.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './services/security/token-interceptor.service';
import { RegisterComponent } from './components/register/register.component'
import { LoginGuardGuard } from './services/security/login-guard.guard';
import { TicketsComponent } from './components/tickets/tickets.component';
import { AddStopsComponent } from './components/add-stops/add-stops.component';
import { AgmCoreModule } from '@agm/core';
import { AgmDirectionModule } from 'agm-direction';
import { ValidateComponent } from './components/validate/validate.component';
import { AddLineComponent } from './components/add-line/add-line.component';
import { VerifyComponent } from './components/verify/verify.component';
import { AddVehicleComponent } from './components/add-vehicle/add-vehicle.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { DeleteBusStopComponent } from './components/delete-bus-stop/delete-bus-stop.component';
import { DeleteLineComponent } from './components/delete-line/delete-line.component';
import { AssignLineToVehicleComponent } from './components/assign-line-to-vehicle/assign-line-to-vehicle.component';
import { ListScheduleComponent } from './components/list-schedule/list-schedule.component';
import { ChangeScheduleComponent } from './components/change-schedule/change-schedule.component';
import { ManagePricelistComponent } from './components/manage-pricelist/manage-pricelist.component';
import { DeleteVehicleComponent } from './components/delete-vehicle/delete-vehicle.component';


const appRoutes: Routes = [
  { path: 'main', 
    component: MainPageComponent
  },
  { path: 'login',
    component: LoginPageComponent, 
    canActivate: [LoginGuardGuard] 
  },
  { path: '',
    redirectTo: 'main',
    pathMatch: 'full'
  },
  {
    path: 'addStop',
    component: AddStopsComponent,
    data: {roles: ['ADMIN_ROLE']},
    canActivate: [AuthGuardGuard]
  },{
    path: 'addLine',
    component: AddLineComponent,
    data: {roles: ['ADMIN_ROLE']},
    canActivate: [AuthGuardGuard]
  },
  { path: 'deleteLine',
    component: DeleteLineComponent,
    data: {roles: ['ADMIN_ROLE']},
    canActivate: [AuthGuardGuard]
  },
  { path: 'deleteStop',
    component: DeleteBusStopComponent,
    data: {roles: ['ADMIN_ROLE']},
    canActivate: [AuthGuardGuard]
  },{
    path: 'validate',
    component: ValidateComponent,
    data: {roles: ['VALIDATOR_ROLE']},
    canActivate: [AuthGuardGuard]
  },
  { path: 'tickets',
    component: TicketsComponent,
    data: {roles: ['PASSENGER_ROLE']},
    canActivate: [AuthGuardGuard]
  },{
    path: 'verify',
    component: VerifyComponent,
    data: {roles: ['PASSENGER_ROLE', 'VALIDATOR_ROLE']},
    canActivate: [AuthGuardGuard]
  },{
    path: 'pricelists',
    component: ManagePricelistComponent,
    data: {roles: ['ADMIN_ROLE']},
    canActivate: [AuthGuardGuard]
  },
  { path: 'addVehicle',
    component: AddVehicleComponent,
  },
  { path: 'assignLine',
    component: AssignLineToVehicleComponent,
    data: {roles: ['ADMIN_ROLE']},
    canActivate: [AuthGuardGuard]
  },
  { path: 'listSchedule',
  component: ListScheduleComponent
  },
  { path: 'changeSchedule',
  component: ChangeScheduleComponent,
  data: {roles: ['ADMIN_ROLE']},
  canActivate: [AuthGuardGuard]
  },
  {
    path: 'deleteStop',
    component:  DeleteBusStopComponent,
    data: {roles: ['ADMIN_ROLE']},
    canActivate: [AuthGuardGuard]
    
  },
  {
    path: 'deleteLine',
    component:  DeleteLineComponent,
    data: {roles: ['ADMIN_ROLE']},
    canActivate: [AuthGuardGuard]
  },
  {
    path: 'deleteVehicle',
    component:  DeleteVehicleComponent,
    data: {roles: ['ADMIN_ROLE']},
    canActivate: [AuthGuardGuard]
  },
  { path: '**', component: NotFoundPageComponent },
 
];


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LoginPageComponent,
    MainPageComponent,
    NotFoundPageComponent,
    RegisterComponent,
    TicketsComponent,
    AddStopsComponent,
    ValidateComponent,
    AddLineComponent,
    AddVehicleComponent,
    DeleteBusStopComponent,
    DeleteLineComponent,
    AssignLineToVehicleComponent,
    ListScheduleComponent,
    ChangeScheduleComponent,
    VerifyComponent,
    ManagePricelistComponent,
    DeleteVehicleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true }), // <-- debugging purposes only
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBG-Cyqxezk9fYYllu_VmEe8q3ZBFf30Yc'
    }),
    AgmDirectionModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    AuthGuardGuard,
    JwtUtilsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    AuthenticationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
