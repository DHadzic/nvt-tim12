import { Component, OnInit } from '@angular/core';
import { MapService } from "../../services/map.service";
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { Observable,throwError } from 'rxjs';
import { LinesPerType } from '../../../model/linesPerType';
import { LineInfo } from '../../../model/lineInfo';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {
  public transport_types;
  public selectedType;
  public lines : LinesPerType;
  public selectedLineBus;
  public selectedLineTram;
  public selectedLineTrolleybus;
  public routePoints;
  public busAtStops;
  public markerOptions;
  public selectError;

  constructor(private mapService: MapService, private router: Router) { 
    this.transport_types = ["Bus","Tram","Trolleybus"];
    var _this = this;
    this.selectedType = "Bus";
    this.routePoints = {};
    this.busAtStops = [];
    this.lines = {} as LinesPerType;
    this.lines["busLines"] = [];
    this.lines["tramLines"] = [];
    this.lines["trolleybusLines"] = [];
    this.selectError = false;

    this.markerOptions = {
    };

    var observer = {
      next(value) {
        _this.lines = JSON.parse(value) as LinesPerType;
        console.log(_this.lines);
      },
      error(msg) {
        alert("Couldn't load the existing stops");
      }
    }
  
  
    this.mapService.getLinesPerType().pipe(catchError(err => {
      return throwError(err);
    })).subscribe(observer);
  
  }

  ngOnInit() {
  }

  onChange(something){
    console.log(something);
  }

  displayLine(){
    this.selectError = false;
    var lineToSend;

    if(this.selectedType == "Bus"){
      lineToSend = this.selectedLineBus
    }
    if(this.selectedType == "Tram"){
      lineToSend = this.selectedLineTram
    }
    if(this.selectedType == "Trolleybus"){
      lineToSend = this.selectedLineTrolleybus
    }

    if(lineToSend == undefined){
      this.selectError = true;
      return;
    }

    var _this = this;

    var observer = {
      next(value) {
        var lineInfo : LineInfo = JSON.parse(value) as LineInfo;
        var line = lineInfo.line;
        _this.routePoints = {};
        _this.routePoints["origin"] = {};
        _this.routePoints["origin"]["lat"] = parseFloat(String(line.stations[0]["lat"]));
        _this.routePoints["origin"]["lng"] = parseFloat(String(line.stations[0]["lng"]));
        _this.routePoints["dest"] = {};
        _this.routePoints["dest"]["lat"]= parseFloat(String(line.stations[line.stations.length - 1]["lat"]));
        _this.routePoints["dest"]["lng"]= parseFloat(String(line.stations[line.stations.length - 1]["lng"]));
        _this.routePoints["waypoints"] = [];

        for(let i in line.stations){
          if(parseInt(i) == (line.stations.length - 1) || i == "0"){
            continue;
          }
    
          _this.routePoints["waypoints"].push({
            location : {
              lat : parseFloat(String(line.stations[parseInt(i)]["lat"])),
              lng : parseFloat(String(line.stations[parseInt(i)]["lng"]))
            },
            stopover : false
          })
        }

        var bus_url = "http://maps.google.com/mapfiles/kml/shapes/bus.png";

        _this.markerOptions["origin"] = {label: {color: 'black', text:'Start', fontWeight:'bold', fontSize:'12px'},clickable : false};
        _this.markerOptions["waypoints"] = [];
        for(let index in line.stations){
          if(parseInt(index) == 1 || parseInt(index) == line.stations.length - 1){
            continue;
          }
          _this.markerOptions["waypoints"].push({label: {color: 'black', text:'S', fontWeight:'bold', fontSize:'15px'},clickable : false});
        }
        _this.markerOptions["destination"] = {label: {color: 'black', text:'End', fontWeight:'bold', fontSize:'12px'},clickable : false};

        var stationProperties = {clickable : false , icon:{ url : bus_url , scaledSize : { width : 33 , height : 33}}};


        for(let stop in line.stations){
          for(let index of lineInfo.atStations){
            if(Number(stop) == index){
              if(index == 0){
                _this.markerOptions["origin"] = stationProperties;
              }
              if(index == line.stations.length - 1){
                _this.markerOptions["destination"] = stationProperties;
              }else{
                _this.markerOptions["waypoints"][index.valueOf() - 1] = stationProperties;
              }
            }
          }
        }

      },
      error(msg) {
        alert("Couldn't load that line");
      }
    }
  
  
    this.mapService.getLineInfo(this.selectedType.toUpperCase(),lineToSend).pipe(catchError(err => {
      return throwError(err);
    })).subscribe(observer);

  }

}
