import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import { LoginUser } from 'src/model/loginUser';
import { AuthenticationService } from '../services/security/authentication-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public user;

  public wrongUsernameOrPass:boolean;

  constructor(private authenticationService:AuthenticationService,
              private router: Router) {
    this.user = {};
    this.wrongUsernameOrPass = false;
   }

  ngOnInit() {
  }

  login():void{
    this.authenticationService.login(this.user.name, this.user.password).subscribe(
      (loggedIn:boolean) => {
        if(loggedIn){
          this.router.navigate(['/main']);          
        }
      }
    ,
    (err:Error) => {
      if(err.toString()==='Ilegal login'){
        this.wrongUsernameOrPass = true;
        console.log(err);
      }
      else{
        Observable.throw(err);
      }
    });
  }

  

}
