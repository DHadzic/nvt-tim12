import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private readonly basePath = 'http://localhost:8080/ticket';
 
  constructor(private http: HttpClient) {
    this.http = http;
   }

   create(ticket){
     console.log(ticket);
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    this.http.post(this.basePath + "/create",JSON.stringify(ticket), {headers, responseType: 'text'})
            .subscribe(success => {console.log(success);},
                       error => {console.log(error);})
   }
}
