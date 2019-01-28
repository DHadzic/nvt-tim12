import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DeleteVehicleService {

  private readonly basePath = 'api/addVehicle';
 
  constructor(private http: HttpClient) {
    this.http = http;
   }

   getVehicles(){
    return this.http.get(this.basePath +  "/getAllVehicles",{responseType: 'json'});
   }

   delete(w){
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.basePath + "/delete",JSON.stringify(w), {headers, responseType: 'text'});

   }
}
