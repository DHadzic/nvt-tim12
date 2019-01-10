import { Component, OnInit } from '@angular/core';
import { EventEmitter,Output } from '@angular/core';
import { UserService } from "../../services/user.service";
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { Observable,throwError } from 'rxjs';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  public myBool : boolean;
  public passenger;
  public pass_types;
  public error_messages;

  @Output()
  changeDisplay:EventEmitter<any> = new EventEmitter();

  constructor(private userService:UserService,private router: Router) { 
    this.passenger = {};
    this.pass_types = ["Student","Regular","Retiree"];
    this.passenger.type = this.pass_types[0];
    this.myBool = true;
    this.error_messages = {};
    this.error_messages.username = false;
    this.error_messages.password = false;
    this.error_messages.password_r = false;
    this.error_messages.name = false;
    this.error_messages.surname = false;
    this.error_messages.birthDate = false;
  }

  
  ngOnInit() {
  }

  openRegistration(){
    this.changeDisplay.emit();
  }

  register(){

    if(!this.handleValidation())
      return;

    this.passenger.type = this.passenger.type.toUpperCase();
    delete this.passenger.password_r;
    console.log(this.passenger);

    var observer = {
      next(value) {
        this.router.navigate(['/login']);
      },
      error(msg) {
        console.log(msg.error);
      }
    }

    this.userService.register(this.passenger)
        .pipe(catchError(err => {
          return throwError(err);
        })).subscribe(observer);
      
    this.passenger.password_r = this.passenger.password;
  }

  handleValidation(): boolean{
    this.error_messages.username = false;
    this.error_messages.password = false;
    this.error_messages.password_r = false;
    this.error_messages.name = false;
    this.error_messages.surname = false;
    this.error_messages.birthDate = false;

    var isSuccessful = true;
    if(this.passenger.username != undefined){
      if(this.passenger.username.length < 3 || this.passenger.username.length >20){
        isSuccessful = false;
        this.error_messages.username = true;
      }
    }else{
      isSuccessful = false;
      this.error_messages.username = true;
    }

    if(this.passenger.password != undefined){
      if(this.passenger.password.length < 3 || this.passenger.password.length >20){
        isSuccessful = false;
        this.error_messages.password = true;
      }
    }else{
      isSuccessful = false;
      this.error_messages.password = true;
    }

    if(this.passenger.password_r != undefined){
      if(this.passenger.password_r != this.passenger.password){
        isSuccessful = false;
        this.error_messages.password_r = true;
      }
    }else{
      isSuccessful = false;
      this.error_messages.password_r = true;
    }

    if(this.passenger.name != undefined){
      if(this.passenger.name.length < 3 || this.passenger.name.length >20){
        isSuccessful = false;
        this.error_messages.name = true;
      }
    }else{
      isSuccessful = false;
      this.error_messages.name = true;
    }

    if(this.passenger.surname != undefined){
      if(this.passenger.surname.length < 3 || this.passenger.surname.length >20){
        isSuccessful = false;
        this.error_messages.surname = true;
      }
    }else{
      isSuccessful = false;
      this.error_messages.surname = true;
    }

    if(this.passenger.birthDate != undefined){
      var dateSplit = this.passenger.birthDate.split("-");
      if(Number(dateSplit[0]) < 1900 || Number(dateSplit[0]) > 2011 ){
        isSuccessful = false;
        this.error_messages.birthDate = true;
      }
    }else{
      isSuccessful = false;
      this.error_messages.birthDate = true;
    }

    return isSuccessful;
  }
}
