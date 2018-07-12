import { HttpInterceptor, HttpRequest, HttpResponse, HttpHandler, HttpEvent, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Observable, of, throwError } from 'rxjs';
import { mergeMap, materialize, delay, dematerialize } from 'rxjs/operators';
import { Injectable } from "@angular/core";
import { User } from "../models/user";

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {
    constructor() { }

    isGET(req: HttpRequest<any>): boolean {
        return req.method === 'GET'
    }

    isPOST(req: HttpRequest<any>): boolean {
        return req.method === 'POST'
    }

    getUsers(): User[] {
        return JSON.parse(localStorage.getItem('users')) || [];
    }

    checkIsDuplicateUser(users: User[], newUser: User) {
        return users.filter(user => { return user.username === newUser.username; }).length;
    }

    registerNewUser(users: User[], newUser: User) {
        newUser.id = users.length + 1;
        users.push(newUser);
        localStorage.setItem('users', JSON.stringify(users));
    }

    checkAuthorizationHeader(req: HttpRequest<any>): boolean {
        return req.headers.get('Authorization') === 'Bearer fake-jwt-token';
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let users: User[] = this.getUsers();

        return of(null).pipe(mergeMap(() => {

            // authenticate
            if (request.url.endsWith('/users/authenticate') && this.isPOST(request)) {
                console.log("Fake backend = Authenticate user ...");
                let filteredUsers = users.filter(user => {
                    return user.username === request.body.username && user.password === request.body.password;
                });

                if (filteredUsers.length) {
                    let user = filteredUsers[0];
                    const { id, username, firstName, lastName } = user;
                    let body = {
                        id, username, firstName, lastName, token: 'fake-jwt-token'
                    };
                    return of(new HttpResponse({ status: 200, body }));
                } else {
                    return throwError({ message: 'Username or password is incorreect!' });
                }
            }

            // register user
            if (request.url.endsWith('/users/register') && this.isPOST(request)) {
                // get new user object from post body
                let newUser = request.body;
                // validation
                if (this.checkIsDuplicateUser(users, newUser)) {
                    return throwError({ message: 'Username "' + newUser.username + '" is already taken' });
                }
                this.registerNewUser(users, newUser);

                // respond 200 OK
                return of(new HttpResponse({ status: 200 }));
            }

            // get users
            if (request.url.endsWith('/users') && this.isGET(request)) {
                // check for fake auth token in header and return users if valid, this security is implemented server side in a real application
                if (!this.checkAuthorizationHeader(request)) {
                    // return 401 not authorised if token is null or invalid
                    return throwError({ error: { message: 'Unauthorised' } });
                }
                return of(new HttpResponse({ status: 200, body: users }));
            }

            // get user by id
            if (request.url.match(/\/users\/\d+$/) && this.isGET(request)) {
                if (!this.checkAuthorizationHeader(request)) {
                    // return 401 not authorised if token is null or invalid
                    return throwError({ error: { message: 'Unauthorised' } });
                }

                // find user by id in users array
                let urlParts = request.url.split('/');
                let id = parseInt(urlParts[urlParts.length - 1]);
                let matchedUsers = users.filter(user => { return user.id === id; });
                let user = matchedUsers.length ? matchedUsers[0] : null;

                return of(new HttpResponse({ status: 200, body: user }));
            }

            // pass through any requests not handled above
            return next.handle(request);
        }))
            // call materialize and dematerialize to ensure delay even if an error is thrown
            .pipe(materialize())
            .pipe(delay(500))
            .pipe(dematerialize());
    }
}

export let fakeBackendProvider = {
    provide: HTTP_INTERCEPTORS,
    useClass: FakeBackendInterceptor,
    multi: true
}