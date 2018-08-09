import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { User } from "../models/user";

@Injectable()
export class UserService {
    private baseUrl = 'http://localhost:8080';

    constructor(private http: HttpClient) { }

    register(user: User) {
        return this.http.post(`${this.baseUrl}/users/sign-up`, user);
    }

    getAllUsers() {
        return this.http.get(`${this.baseUrl}/users/`);
    }

    getById(id: string) {
        return this.http.get(`${this.baseUrl}/users/${id}`);
    }

    getByUsername(username: string, token: string) {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': token
            })
        };

        return this.http.get(`${this.baseUrl}/users/byUsername/${username}`, httpOptions);
    }

    retrieveUserPicture(username: string) {
        return this.http.post(`${this.baseUrl}/users/retrieve/${username}`, {});
    }
}