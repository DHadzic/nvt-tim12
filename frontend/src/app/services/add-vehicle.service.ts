import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AddVehicleService {

  private readonly basePath = 'api/addVehicle';
 
  constructor(private http: HttpClient) {
    this.http = http;
   }

   create(vehicle){
     console.log(vehicle);
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(this.basePath + "/create",JSON.stringify(vehicle), {headers, responseType: 'text'});
       
   }

   getLines(){
    return this.http.get(this.basePath +  "/getLines",{responseType: 'json'});
  }

  
  update(vehicle){
    console.log(vehicle);
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.basePath + "/update",JSON.stringify(vehicle), {headers, responseType: 'text'});

   }
}
