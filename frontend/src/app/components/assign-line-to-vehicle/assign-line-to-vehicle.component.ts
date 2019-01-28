import { Component, OnInit } from '@angular/core';
import { AddVehicleService } from 'src/app/services/add-vehicle.service';
import { AssignLineToVehicleService } from 'src/app/services/assign-line-to-vehicle.service';
import { Location } from '@angular/common';


@Component({
  selector: 'app-assign-line-to-vehicle',
  templateUrl: './assign-line-to-vehicle.component.html',
  styleUrls: ['./assign-line-to-vehicle.component.scss']
})
export class AssignLineToVehicleComponent implements OnInit {


  public response;
  public vehicles = [];
  public lines = [];
  public vehicle;
  public nrSelect = "";
  public line = "";
  constructor(private assigneLineToVehicleService:AssignLineToVehicleService, location:Location) { 
    this.response = {vehicle:"",line:""};
  }

  ngOnInit() {
    this.assigneLineToVehicleService.getVehicleWithoutLines().subscribe(success => {this.setVehicles(success)});
    this.assigneLineToVehicleService.getAvailableLines().subscribe(success => {this.setLines(success)});
  }

  setVehicles(data){
    if(data.length != 0){
      this.vehicles = data;
      this.nrSelect = this.vehicles[0].name;
    }
    

  }

  setLines(data){
    if(data.length != 0){
      this.lines = data;
      this.line = this.lines[0].name;
    }
  }

  assign(){
    this.response.line = this.line;
    this.response.vehicle = this.nrSelect;
    this.assigneLineToVehicleService.assign(this.response).subscribe(success => {console.log(success);},
    error => {console.log(error);});
    location.reload();

  }
}
