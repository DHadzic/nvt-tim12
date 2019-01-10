import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MapService {
  private path: string = "http://router.project-osrm.org/table/v1/driving/";

  constructor(private http: HttpClient) { }

  getPoints(firstPoint,secondPoint){
      var newPath = this.path + firstPoint.lat + "," + firstPoint.lng + ";" + secondPoint.lat + "," + secondPoint.lng;

      this.http.get(newPath,{responseType : 'text' as 'json'}).subscribe(
        success => {console.log(success);}
      )
  }
}
