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

  public busStops = []
  public selectedStops =[{
    id: "1"
  },{
    id: "2"
  },{
    id: "3"
  }]

  constructor(private mapService: MapService) { }

  ngOnInit() {
    var observer = {
      next(value) {
        this.busStops = value;
      },
      error(msg) {
        alert("Couldn't load the existing stops");
      }
    }


    this.mapService.getBusStations().pipe(catchError(err => {
      return throwError(err);
    })).subscribe(observer);

  }

  addStopToSelected(){
    this.selectedStops.push({
      id:"5"
    })
  }

  addLine(){

    var observer = {
      next(value) {
        this.busStops = value;
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
