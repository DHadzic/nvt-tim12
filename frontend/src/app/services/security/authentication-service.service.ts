import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtUtilsService } from './jwt-utils.service';
import { map,filter } from 'rxjs/operators';

@Injectable()
export class AuthenticationService {

  private readonly loginPath = 'http://localhost:8080/user/login';

  constructor(private http: HttpClient, private jwtUtilsService: JwtUtilsService) {
  }

  login(name: string, password: string, callback) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    var _this = this;
    
    var observer = {
      next(value) {
        if (value !== "Invalid login") {
          localStorage.setItem('currentUser', JSON.stringify({ 
                                    username: name,
                                    roles: _this.jwtUtilsService.getRoles(value), 
                                    token: value
                                  }));
          callback.handleLogin(true);
        }else{
          console.log("Nothing");
        }
      },
        error(msg) {
          console.log("ERROR SERVICE");
          callback.handleLogin(false);
        }
    }

    this.http.post(this.loginPath, JSON.stringify({ 
      username : name, 
      password : password })
    , { headers , responseType : 'text' as 'json'}).subscribe(observer);
  }

  getToken(): String {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var token = currentUser && currentUser.token;
    return token ? token : "";
  }

  logout(): void {
    localStorage.removeItem('currentUser');
  }

  isLoggedIn(): boolean {
    if (this.getToken() != '') return true;
    else return false;
  }

  getCurrentUser() {
    if (localStorage.currentUser) {
      return JSON.parse(localStorage.currentUser);
    }
    else {
      return undefined;
    }
  }  
}
