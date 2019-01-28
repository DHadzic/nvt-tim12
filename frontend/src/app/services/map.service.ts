import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LinesPerType } from '../../model/linesPerType';

@Injectable({
  providedIn: 'root'
})
export class MapService {
  private path: string = "/api/line";

  constructor(private http: HttpClient) {
   }
  
  getLinesPerType(){
    return this.http.get("api/addVehicle/getLinesPerType",{responseType : 'text' as 'json'})
  }

  getLineInfo(type : String, line_name : String){
    return this.http.get("api/addVehicle/getLineInfo/" + type + "/" + line_name,{responseType : 'text' as 'json'})
  }

  getBusStations(){
    return this.http.get(this.path + "/get_stations",{responseType : 'text' as 'json'})
  }

  addBusStation(station_json){
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.put(this.path + "/add_station",station_json
            ,{headers, responseType : 'text' as 'json'});
  }

  deleteBusStaiton(id){
    return this.http.delete(this.path + "/delete_station/"+ id,{responseType : 'text' as 'json'})
  }

  addLine(line_json){
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(this.path + "/add_line",line_json
            ,{headers, responseType : 'text' as 'json'});
  }

  getLines(){
    return this.http.get(this.path + "/get_lines",{responseType : 'text' as 'json'})
  }

  deleteLine(id){
    return this.http.delete(this.path + "/delete_line/"+ id,{responseType : 'text' as 'json'})
  }
}