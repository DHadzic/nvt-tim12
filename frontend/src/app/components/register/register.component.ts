import { Component, OnInit } from '@angular/core';
import { EventEmitter,Output } from '@angular/core';
import { UserService } from "../../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  public myBool : boolean;
  public passenger;

  @Output()
  changeDisplay:EventEmitter<any> = new EventEmitter();

  constructor(private userService:UserService) { 
    this.passenger = {};
    this.myBool = true;
  }

  
  ngOnInit() {
  }

  openRegistration(){
    this.changeDisplay.emit();
  }

  register(){
    console.log(this.userService.register(this.passenger));
  }
}
