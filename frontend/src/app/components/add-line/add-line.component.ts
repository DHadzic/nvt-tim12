import { Component, OnInit } from '@angular/core';
import { MapService } from 'src/app/services/map.service';
import { catchError } from 'rxjs/operators';
import { Observable,throwError } from 'rxjs';

@Component({
  selector: 'app-add-line',
  templateUrl: './add-line.component.html',
  styleUrls: ['./add-line.component.scss']
})
export class AddLineComponent implements OnInit {

  public busStops;
  public selectedStops;
  public routePoints;
  public new_line;
  public nameError;
  public showRoute;

  constructor(private mapService: MapService) { 
    this.busStops = [];
    this.selectedStops  =[];
    this.routePoints = {};

    this.showRoute = false;
    this.new_line = {
      name : "",
      stations : []
    }
    this.nameError = {};
    this.nameError.nameFormat = false;
    this.nameError.nameTaken = false;
    var _this = this;

    var observer = {
      next(value) {
        _this.busStops = JSON.parse(value);
        for(let stop of _this.busStops){
          stop.id = String(stop.id);
        }
        },
        error(msg) {
          alert("Couldn't load the existing stops." + msg);
        }
    }

    this.mapService.getBusStations().pipe(catchError(err => {
      return throwError(err);
    })).subscribe(observer);

  }

  ngOnInit() {
  }

  markerClickHandle(id){
    var isSelected = false;
    var index;

    for(let stop of this.selectedStops){
      if(stop.id == id){
        isSelected = true;
        index = this.selectedStops.indexOf(stop);
        break;
      }
    }

    if(isSelected){
      this.selectedStops.splice(index,1);
      return;
    }else{
      for(let stop of this.busStops){
        if(stop.id == id){
          this.selectedStops.push(stop);
        }
      }
    }

  }

  addLine(){
    
    this.nameError.nameTaken = false;
    this.nameError.nameFormat = false;

    if(this.new_line.name.length < 1 || this.new_line.name.length > 10){
      this.nameError.nameFormat = true;
      return;
    }

    if(this.selectedStops.length < 2){
      return;
    }

    var _this = this;

    var observer = {
      next(value) {
        console.log(value);
      },
      error(msg) {
        if(msg.error.includes("Line name taken")){
          _this.nameError.nameTaken = true;
        }
      }
    }
    
    this.new_line["stations"] = this.selectedStops;

    var line_json = JSON.stringify(this.new_line);

    console.log(line_json);

    this.mapService.addLine(line_json).pipe(catchError(err => {
      return throwError(err);
    })).subscribe(observer);
  }

  displayRoute(){

    this.showRoute = !this.showRoute;
    console.log(this.showRoute);

    if(!this.showRoute){
      return;
    }

    if(this.selectedStops.length < 2){
      return;
    }

    this.routePoints = {};
    this.routePoints["origin"] = this.selectedStops[0];
    this.routePoints["origin"]["lat"] = parseFloat(this.routePoints["origin"]["lat"]);
    this.routePoints["origin"]["lng"] = parseFloat(this.routePoints["origin"]["lng"]);
    this.routePoints["dest"] = this.selectedStops[this.selectedStops.length - 1];
    this.routePoints["dest"]["lat"] = parseFloat(this.routePoints["dest"]["lat"]);
    this.routePoints["dest"]["lng"] = parseFloat(this.routePoints["dest"]["lng"]);
    this.routePoints["waypoints"] = [];

    for(let i in this.selectedStops){
      if(parseInt(i) == (this.selectedStops.length - 1) || i == "0"){
        continue;
      }

      this. routePoints["waypoints"].push({
        location : {
          lat : parseFloat(this.selectedStops[parseInt(i)]["lat"]),
          lng : parseFloat(this.selectedStops[parseInt(i)]["lng"])
        },
        stopover : false
      })
      }
  }

}
