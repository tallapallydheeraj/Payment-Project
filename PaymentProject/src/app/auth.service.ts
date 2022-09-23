import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private login_url="http://localhost:8080/authenticate"
  custid:any;
  customer:any;
  customertype:any;
  bank:any;
  constructor(private http:HttpClient,private router:Router) { }

  loginUser(user:any){
    return this.http.post(this.login_url,user)
  }
  loggedIn(){
    return !!localStorage.getItem('jwt') && !!this.getUser();//!!this.custid;
  }
  logoutUser(){
    localStorage.removeItem('jwt');
    localStorage.removeItem('custid');//
    this.router.navigate(['/login'])
  }
  getToken(){
    return localStorage.getItem('jwt');
  }
  getUser(){
    /*this.http.get("http://localhost:8080/user")
    .subscribe((result:any)=>{
      console.log(result);
      this.custid=result.msg;
      //console.log(this.custid)
      return this.custid;      
    },
    err=>{
      this.logoutUser();
    })*/
    return localStorage.getItem('custid');
  }
  /*clearStorage(){
    if(this.getToken())
      localStorage.removeItem('jwt');
    }*/
  
}
