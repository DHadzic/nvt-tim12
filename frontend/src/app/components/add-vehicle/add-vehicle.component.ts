import { Component, OnInit } from '@angular/core';
import { EventEmitter,Output } from '@angular/core';
import { AddVehicleService } from 'src/app/services/add-vehicle.service';
import { scheduleMicroTask } from '@angular/core/src/util';

@Component({
  selector: 'app-add-vehicle',
  templateUrl: './add-vehicle.component.html',
  styleUrls: ['./add-vehicle.component.scss']
})
export class AddVehicleComponent implements OnInit {
  time = {hour: 13, minute: 30};
  public vehicle;
  public schedule = {};
  public price:number;
  public workDay = [];
  public saturday = [];
  public sunday = [];
  public scheduleTime = "workDay";
  public type = "BUS";
  public lines;

  
  constructor(private addVehicleService:AddVehicleService) { 
    this.vehicle = {type:"BUS",schedule:{}}
  }

  ngOnInit() {
    this.addVehicleService.getLines().subscribe(success => {this.setLines(success)});
  }

  setLines(data){
    this.lines = data;
    console.log(this.lines);

  }
  

  create(){
    
    if (this.scheduleTime == "workDay"){
      var collator = new Intl.Collator(undefined, {numeric: true, sensitivity: 'base'});
      var converted = this.time.hour.toString() + ":" + this.time.minute.toString();
      if(!(this.workDay.includes(converted))){
        this.workDay.push(converted);
        this.workDay.sort(collator.compare);
        this.vehicle.schedule.workDay = this.workDay;
        this.vehicle.type = this.type;
      }
    }else if (this.scheduleTime == "saturday" ){
      var collator = new Intl.Collator(undefined, {numeric: true, sensitivity: 'base'});
      var converted = this.time.hour.toString() + ":" + this.time.minute.toString();
      if(!(this.saturday.includes(converted))){
        this.saturday.push(converted);
        this.saturday.sort(collator.compare);
        this.vehicle.schedule.saturday = this.saturday;
        this.vehicle.type = this.type;
      }
      
    }else if (this.scheduleTime == "sunday" ){
      var collator = new Intl.Collator(undefined, {numeric: true, sensitivity: 'base'});
      var converted = this.time.hour.toString() + ":" + this.time.minute.toString();
      if(!(this.sunday.includes(converted))){
        this.sunday.push(converted);
        this.sunday.sort(collator.compare);
        this.vehicle.schedule.sunday = this.sunday;
        this.vehicle.type = this.type;
      }
    }
    console.log(this.vehicle.schedule);

    this.addVehicleService.create(this.vehicle).subscribe(success => {console.log(success);},
    error => {console.log(error);});
    
  }

}
