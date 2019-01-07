import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly basePath = 'http://localhost:8080/user';

  constructor(private http: HttpClient) { 
    this.http = http;
  }

  retVal;

  register(passenger){
    var observer = {
      next(value) {
        console.log("USPESNO REGISTROVAN");
        console.log(value);
        return "Neuspesno";
      },
      error(msg) {
        console.log(msg);
        return "Uspesno";
      }
    }

    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(this.basePath + "/register", JSON.stringify(passenger),
          {headers, responseType : 'text' as 'json'})
          .subscribe(observer);
  }

}
