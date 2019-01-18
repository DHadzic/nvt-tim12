import { Component, OnInit } from '@angular/core';
import { MapService } from 'src/app/services/map.service';
import { catchError } from 'rxjs/operators';
import { Observable,throwError } from 'rxjs';

@Component({
  selector: 'app-add-stops',
  templateUrl: './add-stops.component.html',
  styleUrls: ['./add-stops.component.scss']
})
export class AddStopsComponent implements OnInit {

  public marker;
  public busStops;
  public lastPoint;
  public isEnabled;

  constructor(private mapService: MapService) { 
    this.marker = {};
    this.busStops = [];
  }

  ngOnInit() {
    var observer = {
      next(value) {
        this.busStops = value;
      },
      error(msg) {
        alert(msg.error);
      }
    }


    this.mapService.getBusStations().pipe(catchError(err => {
      return throwError(err);
    })).subscribe(observer);

    
  }


  setMarker(event){
    this.isEnabled = true;
    console.log(event.coords);
    this.marker = {
      lat: event.coords.lat,
      lng: event.coords.lng
    };
  }

  addStation(){

    var observer = {
      next(value) {
        alert(value);
      },
      error(msg) {
        alert(msg.error);
      }
    }

    var station = JSON.stringify(this.marker);

    this.mapService.addBusStation(station).pipe(catchError(err => {
      return throwError(err);
    })).subscribe(observer);

    /*
    ZA DIRECTIONS
    if(this.lastPoint != undefined){
      this.busStops.push(
        {
          origin : this.lastPoint,
          dest : {
              lat: this.marker.lat,
              lng: this.marker.lng
          }
      })
    }

    this.lastPoint = {
      lat: this.marker.lat,
      lng: this.marker.lng
    };*/ 


  }
}
