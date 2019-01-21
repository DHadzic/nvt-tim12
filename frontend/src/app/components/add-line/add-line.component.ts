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

  constructor(private mapService: MapService) { 
    this.busStops = [];
    this.selectedStops  =[{
      id: "1"
    }];


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
    var observer = {
      next(value) {
        console.log(value);
      },
      error(msg) {
        alert(msg.error);
      }
    }

    var line = JSON.stringify(this.selectedStops);

    this.mapService.addLine(line).pipe(catchError(err => {
      return throwError(err);
    })).subscribe(observer);
  }

}
