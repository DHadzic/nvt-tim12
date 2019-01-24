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

  register(passenger){
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(this.basePath + "/register", JSON.stringify(passenger),
          {headers, responseType : 'text' as 'json'});
  }

  checkVerification(passenger){
    return this.http.get('api/user/checkVerification/' + passenger.username, {responseType: 'text'});
  }

  uploadDocumentImage(username, image){
    return this.http.post('api/user/saveUserDocument/'+ username, image, {responseType: 'text'});
  }
}
