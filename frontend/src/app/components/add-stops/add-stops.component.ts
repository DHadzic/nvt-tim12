import { Component, OnInit } from '@angular/core';
import { AgmPolylinePoint } from '@agm/core/directives/polyline-point';
import { google, GoogleMap } from '@agm/core/services/google-maps-types';
import { MapService } from 'src/app/services/map.service';

@Component({
  selector: 'app-add-stops',
  templateUrl: './add-stops.component.html',
  styleUrls: ['./add-stops.component.scss']
})
export class AddStopsComponent implements OnInit {

  public marker;
  public busStops;
  public lastPoint;

  constructor(private mapService: MapService) { 
    this.marker = {};
    this.busStops = [];
  }

  ngOnInit() {
    
  }


  setMarker(event){
    console.log(event.coords);
    this.marker = {
      lat: event.coords.lat,
      lng: event.coords.lng
    };
  }

  addPoint(){
    if(this.marker.lat == undefined){
      return;
    }

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
    }; 


  }
}
