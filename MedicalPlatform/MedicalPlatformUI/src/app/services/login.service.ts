import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { UserI } from './../interfaces/UserI';
import { User } from './http-client.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  getDoctorLogin(email: string, password: string) {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(email + ':' + password) });
    let requestUrl = 'server/doctors/login';
    var requestBody = {email: email, password: password};
    return this.http.post<UserI>(requestUrl, requestBody).pipe(
      map(
        data => {
          if(data) {
            sessionStorage.setItem('id', data.id);
            sessionStorage.setItem('email', email);
            sessionStorage.setItem('role', data.role);
            let authString = 'Basic ' + btoa(email + ':' + password);
            sessionStorage.setItem('basicauth', authString);
          }
          return data;
        }
      )
    );
  }

  getPatientLogin(email: string, password: string) {
    let requestUrl = 'server/patients/login';
    var requestBody = {email: email, password: password};
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(email + ':' + password) });
    return this.http.post<UserI>(requestUrl, requestBody).pipe(
      map(
        data => {
          if(data) {
            sessionStorage.setItem('id', data.id);
            sessionStorage.setItem('email', email);
            sessionStorage.setItem('role', data.role);
            let authString = 'Basic ' + btoa(email + ':' + password);
            sessionStorage.setItem('basicauth', authString);
          }
          return data;
        }
      )
    );
  }

  getCaregiverLogin(email: string, password: string) {
    let requestUrl = 'server/caregivers/login';
    var requestBody = {email: email, password: password};
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(email + ':' + password) });
    return this.http.post<UserI>(requestUrl, requestBody).pipe(
      map(
        data => {
          if(data) {
            sessionStorage.setItem('id', data.id);
            sessionStorage.setItem('email', email);
            sessionStorage.setItem('role', data.role);
            let authString = 'Basic ' + btoa(email + ':' + password);
            sessionStorage.setItem('basicauth', authString);
          }
          return data;
        }
      )
    );
  }

  getDoctorByEmail(email: string) {
    let requestUrl = 'server/doctors/' + email;
    return this.http.get<User>(requestUrl);
  }

  isAuthenticated(): boolean {
    let user = sessionStorage.getItem('email');
    return !(user === null)
  }

  createDoctor(doctor) {
    let requestUrl = 'server/doctors';
    return this.http.post<User>(requestUrl, doctor);
  }

}
