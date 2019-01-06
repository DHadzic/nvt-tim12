import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import { EventEmitter,Output } from '@angular/core';
import { AuthenticationService } from '../../services/security/authentication-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public user;

  @Output()
  changeDisplay:EventEmitter<any> = new EventEmitter();
  
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
        console.log(loggedIn);
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

  public openRegistration(){
    this.changeDisplay.emit();
  }

  

}
