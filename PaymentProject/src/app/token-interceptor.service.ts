import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private auth:AuthService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(req.url.includes('/authenticate'))
      return next.handle(req);
    let tokenizedreq= req.clone({
      setHeaders:{
        Authorization : 'Bearer '+this.auth.getToken()
      }
    })
    return next.handle(tokenizedreq)
  }
}
