import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AssignLineToVehicleService {
  private readonly basePath = 'api/addVehicle';

  constructor(private http: HttpClient) {
    this.http = http;
   }


   getVehicleWithoutLines(){
    return this.http.get(this.basePath +  "/getVehiclesWithoutLine",{responseType: 'json'});
  }

  getAvailableLines(){
    return this.http.get(this.basePath +  "/getAvailableLines",{responseType: 'json'});
  }

  assign(response){
    console.log(response);
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(this.basePath + "/assign",JSON.stringify(response), {headers, responseType: 'text'});
  }
}
