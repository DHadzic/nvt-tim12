import { Component, OnInit } from '@angular/core';
import { MapService } from 'src/app/services/map.service';
import { catchError } from 'rxjs/operators';
import { Observable,throwError } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-delete-bus-stop',
  templateUrl: './delete-bus-stop.component.html',
  styleUrls: ['./delete-bus-stop.component.scss']
})
export class DeleteBusStopComponent implements OnInit {
  public busStops;
  public selectedStop;

  constructor(private mapService: MapService,private router: Router) {
    this.busStops = [];

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

  displayStop(id){
    this.selectedStop = id;
  }

  isSelected(){
    return this.selectedStop == undefined;
  }

  deleteStop(){
    var _this = this;

    if(this.selectedStop == undefined){
      return;
    }

    var observer = {
      next(value) {
        alert(value)
        _this.router.navigate(["/main"])
      },
      error(msg) {
        alert("Semething went wrong");
        console.log(msg);
      }
    }
  
  
    this.mapService.deleteBusStaiton(this.selectedStop).pipe(catchError(err => {
      return throwError(err);
    })).subscribe(observer);
  }


}
