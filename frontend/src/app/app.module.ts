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
  },
  { path: 'tickets',
    component: TicketsComponent,
  },
  { path: '**', component: NotFoundPageComponent }
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
    AddStopsComponent
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
    HttpClientModule
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
