import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm:FormGroup;
  user:any;
  constructor(private auth:AuthService,private router:Router,
      private toastr:ToastrService) {
    this.user={
      username:'',
      password:''
    };
    this.loginForm= new FormGroup({
      username: new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required])
  })
   }
   apiResult={
    success:false,
    error:false
  }
  ngOnInit(): void {
  }
  login(){
    let payload={
      "username": this.user.username,
      "password": this.user.password
    }
    this.auth.loginUser(payload)
    .subscribe((res:any)=>{
      localStorage.setItem('custid',this.user.username);//this.auth.custid=this.user.username;
      
      localStorage.setItem('jwt',res.jwt);
      this.auth.custid=this.auth.getUser();
      this.router.navigate(['/dashboard']);
      this.apiResult.success=true;
      this.apiResult.error =false;
    }, err => {
      this.toastr.error('Invalid Credentials','Error')
      this.apiResult.success=false;
      this.apiResult.error =true;
    })
  }

}
