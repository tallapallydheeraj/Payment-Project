import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-navitems',
  templateUrl: './navitems.component.html',
  styleUrls: ['./navitems.component.css']
})
export class NavitemsComponent implements OnInit {

  constructor(private auth:AuthService) { }

  ngOnInit(): void {
  }
  logout(){
    this.auth.logoutUser();
  }
  loggedIn(){
    return this.auth.loggedIn()
  }

}
