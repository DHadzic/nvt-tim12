import { Component, OnInit } from '@angular/core';
import { MapService } from 'src/app/services/map.service';
import { catchError } from 'rxjs/operators';
import { Observable,throwError } from 'rxjs';
import { Router } from '@angular/router';


@Component({
  selector: 'app-delete-line',
  templateUrl: './delete-line.component.html',
  styleUrls: ['./delete-line.component.scss']
})
export class DeleteLineComponent implements OnInit {
  
  public lines;
  public selectedLine;
  public routePoints;

  constructor(private mapService: MapService,private route : Router) {
    this.lines = [];

    this.selectedLine = {
      id : "",
      name : ""
    };

    this.routePoints = {
      origin : "",
      waypoints : [{lat:"",lng:""}],
      dest: ""
    };

    var _this = this;

    var observer = {
      next(value) {
        _this.lines = JSON.parse(value);
        //for(let stop of _this.busStops){
        //  stop.id = String(stop.id);
        //}
      },
      error(msg) {
        alert("Couldn't load the existing stops");
      }
    }
  
  
    this.mapService.getLines().pipe(catchError(err => {
      return throwError(err);
    })).subscribe(observer);
  
  }

  ngOnInit() {
  }

  isSelected(){
    if(this.selectedLine.id != ""){
      return false;
    }else{
      return true;
    }
  }

  displayLine(id){
    for(let line of this.lines){
      if(Number(line.id) == Number(id)){

        this.selectedLine = line;
        this.routePoints = {};
        this.routePoints["origin"] = {};
        this.routePoints["origin"]["lat"] = parseFloat(line.stations[0]["lat"]);
        this.routePoints["origin"]["lng"] = parseFloat(line.stations[0]["lng"]);
        this.routePoints["dest"] = {};
        this.routePoints["dest"]["lat"]= parseFloat(line.stations[line.stations.length - 1]["lat"]);
        this.routePoints["dest"]["lng"]= parseFloat(line.stations[line.stations.length - 1]["lng"]);
        this.routePoints["waypoints"] = [];

        for(let i in line.stations){
          if(parseInt(i) == (line.stations.length - 1) || i == "0"){
            continue;
          }
    
          this.routePoints["waypoints"].push({
            location : {
              lat : parseFloat(line.stations[parseInt(i)]["lat"]),
              lng : parseFloat(line.stations[parseInt(i)]["lng"])
            },
            stopover : false
          })
          }
          break;
        }
    }
    console.log(this.routePoints);
  }

  deleteLine(){
    var _this = this;

    if(this.selectedLine.id == undefined){
      return;
    }

    var observer = {
      next(value) {
        alert(value)
        _this.route.navigate(["/main"])
      },
      error(msg) {
        alert("Couldn't load the existing lines");
      }
    }
  
  
    this.mapService.deleteLine(this.selectedLine.id).pipe(catchError(err => {
      return throwError(err);
    })).subscribe(observer);
  }

}
