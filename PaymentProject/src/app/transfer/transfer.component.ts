import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {

  transaction: any;
  messagetypes:any;
  flag:any=true;
  customertype:any;
  transfertypes:any;
  customer:any={}
  sametype:any=true;
  transfertosameaccount:any=false;
  constructor(private service:AuthService,private http:HttpClient,
    private router:Router, private toastr:ToastrService) { 
    
    this.transaction={
      "transactionid": 0,
      "customer": this.service.customer,
      
      "currency": {
        "currencycode": "INR",
        "currencyname": "Indian Rupees",
        "conversionrate": 1
      },
      "senderBIC": this.service.bank,
      "receiverBIC": {
        "bankname": "",
        "bic": ""
      },
      "receiveraccountholdernumber": "",
      "receiveraccountholdername": "",
      "transfertypecode": {
        "transfertypecode": "",
        "transfertypedescription": ""
      },
      "message": {
        "messagecode": "REPA"
        
      },
      "currencyamount": 1,
      "inramount": 0,
      "transferfees": 0
      
    }
    
        this.http.get("http://localhost:8080/message")
        .subscribe((result:any)=>{
          this.messagetypes = result.map((item: any) => {
          return { name: item.instruction, code: item.messagecode };
        });
         console.log(result)
        },
        err=>{
          console.log(err);
          
        })
    
      }
  
  

  ngOnInit(): void {
    this.http.get('http://localhost:8080/customer/'+this.service.getUser())//this.auth.custid)
    .subscribe((result: any) => {
      this.customer=result;
      this.customertype=this.customer.customertype;
      this.service.bank=this.customer.bic;
       this.service.customertype=result.customertype;
      this.service.customer=this.customer;
    },
    err=>{
      console.log(err);
      //this.service.clearStorage();
    })
    
    //this.customertype=this.service.customertype;
    
  }
  transfer()
  {
      this.transaction.transferfees=this.transaction.inramount*0.0025
      //this.transaction.message.instruction=this.messagetypes.
      if(this.customertype=='B'){
      this.transaction.transfertypecode.transfertypedescription="Bank Transfer";
      this.transaction.transfertypecode.transfertypecode="B";
      }
      else{
      this.transaction.transfertypecode.transfertypedescription="Customer Transfer";
      this.transaction.transfertypecode.transfertypecode="C"
  }
  this.http.post("http://localhost:8080/transaction",this.transaction)
  .subscribe((result:any)=>{
    console.log(result);
   if(result.status==1)
   {
     alert("Transaction Successful")
     this.toastr.success('Transaction Successful','SUCCESS')
     this.router.navigate(["/dashboard"]);
   }
   else if(result.status==-3){
     //alert(result.msg);
     this.toastr.error(result.msg,'WARNING')
   }
   else if(result.status==-1){
      //alert("Invalid Transaction due to Insufficient fund in your account");
      this.toastr.info('Insufficient funds in your account','INFO')
   }
  },
  err=>{
    console.log(err)
  }
  )
}
  fetch()
  {
    let num="";
    num=this.transaction.receiveraccountholdernumber;
    
    
    this.http.get("http://localhost:8080/receiver/"+num)
    .subscribe((result:any)=>{
      this.transaction.receiverBIC = result.bic;
      //this.transaction.receiveraccountholdername = result.accountholdername;
      if(this.transaction.receiveraccountholdernumber==this.transaction.customer.customerid){
        //result.customer.customerid
        this.transfertosameaccount=true;
        this.toastr.info("You can't transfer to your own account",'INFO',{
          progressBar : true,
          progressAnimation : 'increasing',
          timeOut :2000
        })
      }
      else
        this.transfertosameaccount=false;
      if(result.customertype!=this.customertype)
        this.sametype=false;
      else
      this.sametype=true;
      this.transaction.receiverbankname=result.bic.bankname;
     console.log(result);
     
     this.flag=true
    },
    err=>{
      console.log(err);
      this.transaction.receiverBIC.bic = "";
      this.transaction.receiveraccountholdername = "";
      this.transaction.receiverBIC.bankname="";
      this.flag=false
      
    })
    console.log(this.transaction.receiveraccountholdernumber);

  }
 
}
