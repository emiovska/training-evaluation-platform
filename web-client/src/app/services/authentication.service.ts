import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { map, catchError } from "rxjs/operators";
import { Observable } from "rxjs";

@Injectable()
export class AuthenticationService {
    private baseUrl = 'http://localhost:8080';

    constructor(private http: HttpClient) { }

    login(username: string, password: string){
        console.log("Authentication service ", username, " = ", password);
        return this.http.post<any>(`${this.baseUrl}/sign-in`, { username, password }, {observe: 'response'});
    }

    logout() {
        localStorage.removeItem('currentUser');
    }
}