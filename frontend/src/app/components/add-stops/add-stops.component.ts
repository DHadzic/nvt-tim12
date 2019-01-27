import { Component, OnInit } from '@angular/core';
import { MapService } from 'src/app/services/map.service';
import { catchError } from 'rxjs/operators';
import { Observable,throwError } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-stops',
  templateUrl: './add-stops.component.html',
  styleUrls: ['./add-stops.component.scss']
})
export class AddStopsComponent implements OnInit {

  public marker;
  public busStops;
  public lastPoint;
  public mapNotClicked;
  public polygonDots;

  constructor(private mapService: MapService,private router: Router) { 
    this.marker = {};
    this.busStops = [];
    this.mapNotClicked = true;
    this.polygonDots = [{
      lat: 45.271652105740415,
      lng: 19.785163978393484
    },{
      // PRVI
      lat: 45.271652105740415,
      lng: 19.807694198063132
    },{
      // DRUGI
      lat: 45.301403448327434,
      lng: 19.807694198063132
    },{
      // TRECI
      lat: 45.301403448327434,
      lng: 19.85146784918618
    },{
      // CETVRTI
      lat: 45.271652105740415,
      lng: 19.85146784918618
    },{
      lat: 45.271652105740415,
      lng: 19.893825630004812
    },{
      lat: 45.22028630783431,
      lng: 19.893825630004812
    },{
      lat: 45.22028630783431,
      lng: 19.785163978393484
    },{
      lat: 45.271652105740415,
      lng: 19.785163978393484
    }
  ]

  var _this = this;

  var observer = {
    next(value) {
      _this.busStops = JSON.parse(value);
      for(let stop of _this.busStops){
        stop.id = String(stop.id);
      }
    },
    error(msg) {
      alert("Couldn't load the existing stops");
    }
  }


  this.mapService.getBusStations().pipe(catchError(err => {
    return throwError(err);
  })).subscribe(observer);

  }

  ngOnInit() {
    
  }

  isMapClicked(){
    return this.mapNotClicked;
  }
  
  
setMarker(event){
    var lat = parseFloat(event.coords.lat);
    var lng = parseFloat(event.coords.lng);

    var isInside = false;

    if(lat > 45.271652105740415 && lat < 45.301403448327434 && lng > 19.807694198063132 && lng < 19.85146784918618)
      isInside = true;
    if(lat > 45.22028630783431 && lat < 45.271652105740415 && lng > 19.785163978393484 && lng < 19.893825630004812)
      isInside = true;

    if(!isInside)
      return;

    this.mapNotClicked = false;
    console.log(event.coords);
    this.marker = {
      lat: event.coords.lat,
      lng: event.coords.lng
    };
  }

  addStation(){
    var _this = this;

    var observer = {
      next(value) {
        alert(value)
        _this.router.navigate(['/main']);
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
