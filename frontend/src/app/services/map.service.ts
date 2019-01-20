import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MapService {
  private path: string = "/api/line";

  constructor(private http: HttpClient) {
   }

  getBusStations(){
    return this.http.get(this.path + "/get_stations",{responseType : 'text' as 'json'})
  }

  addBusStation(station_json){
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.put(this.path + "/add_station",station_json
            ,{headers, responseType : 'text' as 'json'});
  }

  addLine(line_json){
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(this.path + "/add_line",line_json
            ,{headers, responseType : 'text' as 'json'});
  }
}