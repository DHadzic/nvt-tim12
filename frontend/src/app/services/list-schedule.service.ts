import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ListScheduleService {

  private readonly basePath = 'api/addVehicle';
 
  constructor(private http: HttpClient) {
    this.http = http;
   }


   getSchedule(){
    return this.http.get(this.basePath +  "/getVehiclesWithLine",{responseType: 'json'});
   }

   getVehicleByName(name){
     console.log(name);
    return this.http.post<string>(this.basePath +  "/getVehicleByName",JSON.stringify(name),{responseType:  'json'});
   }

}
