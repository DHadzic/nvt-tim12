import { Component } from '@angular/core';
import { AuthenticationService } from './services/security/authentication-service.service';
import { Router } from '@angular/router';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  private verifyBtn = false;
  
  constructor(private authService: AuthenticationService,
    public router: Router){}

    ngOnInit() {
      this.checkVerification();
    }
    
    loggedIn():boolean{
      return this.authService.isLoggedIn();
    }

    login():void{
      this.router.navigate(['/login']);
    }

    logout(){
      this.authService.logout();
      this.router.navigate(['/login']);
    }

    navigateTo(url){
      this.router.navigate['/' + url];
    }

    isAdmin(){
      var roles = this.authService.getRoles();
      return roles.includes("ADMIN_ROLE");
    }

    isPassenger(){
      var roles = this.authService.getRoles();
      return roles.includes("PASSENGER_ROLE");
    }

    checkVerification(){
      if (this.isPassenger()){
        this.verifyBtn = true;
      }
    }
  }
