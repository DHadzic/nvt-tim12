import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtUtilsService } from './jwt-utils.service';
import { map } from 'rxjs/operators';

@Injectable()
export class AuthenticationService {

  private readonly loginPath = 'http://localhost:8080/user/login';

  constructor(private http: HttpClient, private jwtUtilsService: JwtUtilsService) {
  }

  login(name: string, password: string): Observable<boolean> {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    const mapData = map((res: any) => {
      let token = res && res['token'];
      if (token) {
        localStorage.setItem('currentUser', JSON.stringify({ 
                                  username: name,
                                  roles:this.jwtUtilsService.getRoles(token), 
                                  token: token 
                                }));
        return true;
      }
      else {
        return false;
      }
    });


    return mapData(this.http.post(this.loginPath, JSON.stringify({ 
                                  username : name, 
                                  password : password })
                                , { headers }));
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
