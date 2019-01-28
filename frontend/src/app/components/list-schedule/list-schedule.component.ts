import { Component, OnInit } from '@angular/core';
import { ListScheduleService } from 'src/app/services/list-schedule.service';
import { AddVehicleService } from 'src/app/services/add-vehicle.service';
import { listLazyRoutes } from '@angular/compiler/src/aot/lazy_routes';

@Component({
  selector: 'app-list-schedule',
  templateUrl: './list-schedule.component.html',
  styleUrls: ['./list-schedule.component.scss']
})
export class ListScheduleComponent implements OnInit {

  public vehicles;
  public vehicle;
  public vehicleObject;
  public workDay = [];
  public saturday= [];
  public sunday= [];
  public i =0;
  public bool = false;
  public time;
  public time2;
  public time3;

  constructor(private listScheduleService:ListScheduleService, private addVehicleService:AddVehicleService) { 
    this.vehicles = [];
  }

  ngOnInit() {
    this.listScheduleService.getSchedule().subscribe(success => {this.setVehicles(success)});

  }


  setVehicles(data){
    this.vehicles = data;
  }


  getSchedule(){
    this.listScheduleService.getVehicleByName(this.vehicle).subscribe(success => this.getVehicle(success));
    this.bool = true;

  };

  getVehicle(data){
    this.vehicleObject = data;
    this.workDay = this.vehicleObject.schedule.workDay;
    this.saturday = this.vehicleObject.schedule.saturday;
    this.sunday = this.vehicleObject.schedule.sunday;
  }

  deleteHours_workday(w){
    console.log(this.workDay.length);
    var a = this.workDay.indexOf(w);
    this.workDay.splice(a,1);
    this.vehicleObject.schedule.workDay = this.workDay;
    this.addVehicleService.update(this.vehicleObject).subscribe(success => {console.log(success);},
    error => {console.log(error);});
  }

  addHours_workday(){
    var collator = new Intl.Collator(undefined, {numeric: true, sensitivity: 'base'});
    var converted = this.time.hour.toString() + ":" + this.time.minute.toString();
    if(!(this.vehicleObject.schedule.workDay.includes(converted))){
      this.vehicleObject.schedule.workDay.push(converted);
      this.vehicleObject.schedule.workDay.sort(collator.compare);
      this.addVehicleService.update(this.vehicleObject).subscribe(success=> {console.log(success)},error => {console.log(error);});
    }
  }

    deleteHours_saturday(w){
      var a = this.saturday.indexOf(w);
      this.saturday.splice(a,1);
      this.vehicleObject.schedule.saturday = this.saturday;
      this.addVehicleService.update(this.vehicleObject).subscribe(success => {console.log(success);},
      error => {console.log(error);});
    }
  
    addHours_saturday(){
      var collator = new Intl.Collator(undefined, {numeric: true, sensitivity: 'base'});
      var converted = this.time2.hour.toString() + ":" + this.time2.minute.toString();
      if(!(this.vehicleObject.schedule.saturday.includes(converted))){
        this.vehicleObject.schedule.saturday.push(converted);
        this.vehicleObject.schedule.saturday.sort(collator.compare);
        this.addVehicleService.update(this.vehicleObject).subscribe(success=> {console.log(success)},error => {console.log(error);});
      }
    
    }

    deleteHours_sunday(w){
      var a = this.sunday.indexOf(w);
      this.sunday.splice(a,1);
      this.vehicleObject.schedule.sunday = this.sunday;
      this.addVehicleService.update(this.vehicleObject).subscribe(success => {console.log(success);},
      error => {console.log(error);});
    }
  
    addHours_sunday(){
      var collator = new Intl.Collator(undefined, {numeric: true, sensitivity: 'base'});
      var converted = this.time3.hour.toString() + ":" + this.time3.minute.toString();
      if(!(this.vehicleObject.schedule.sunday.includes(converted))){
        this.vehicleObject.schedule.sunday.push(converted);
        this.vehicleObject.schedule.sunday.sort(collator.compare);
        this.addVehicleService.update(this.vehicleObject).subscribe(success=> {console.log(success)},error => {console.log(error);});
      }
    
   }
}
