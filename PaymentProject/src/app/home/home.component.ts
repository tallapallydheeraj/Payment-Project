import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  arr:any=[["RBI-2026 -6.5%",150],["RBI-2045 -8%",125],["EUR USD-FUT",10]]
  single = [
    
  ];
  
  constructor() {
    
   }

  ngOnInit(): void {
    this.single=this.arr.map((element:any) => {
      return {name:element[0],value:element[1]};
     });
     
     console.log(this.single)
  }

}
