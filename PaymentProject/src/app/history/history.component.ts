import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  transactions:any=[]
  constructor(private auth:AuthService, private http:HttpClient,
    private router:Router) { }

  ngOnInit(): void {
    this.http.get("http://localhost:8080/transaction/"+this.auth.getUser())//this.auth.custid)
    .subscribe(result=>{
      this.transactions=result;
      console.log(result)
    },
    err=>{
      this.auth.logoutUser();//
      console.log(err);
    })
  }
  gotoDashboard(){
    this.router.navigate(['/dashboard'])
  }
  transfer(){
    this.router.navigate(['/transfer'])
  }

}
