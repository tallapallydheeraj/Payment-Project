import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  customer:any={};
  bank:any={};
  constructor(private auth:AuthService,private router:Router,
    private http:HttpClient) { }

  ngOnInit(): void {
    console.log(typeof(this.auth.getUser()))
    this.http.get('http://localhost:8080/customer/'+this.auth.getUser())//this.auth.custid)//
    .subscribe((result: any) => {
      this.customer = result;
      this.bank=result.bic;
       this.auth.bank=this.bank;
       this.auth.customertype=result.customertype;
      this.auth.customer=this.customer;
      console.log(result);
    },err=>{
      //router.navigate(['/login'])
      this.auth.logoutUser();//
      console.log(err);
    })
  }

}
