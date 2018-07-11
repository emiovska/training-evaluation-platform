import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { map } from "../../../node_modules/rxjs/operators";

@Injectable()
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    login(username: string, password: string) {
        console.log("Authentication service ", username," = ", password);
        return this.http.post<any>(`/users/authenticate`, { username, password })
            .pipe(map(user => {
                // successful login if there is a jwt token in the response
                console.log("User token", user.token)
                if (user && user.token) {
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
                return user;
            }));
    }

    logout() {
        localStorage.removeItem('currentUser');
    }
}