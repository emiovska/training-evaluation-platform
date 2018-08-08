import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UploaderService } from '../services/uploader.service';



@Component({
  selector: 'app-self-profile',
  templateUrl: './self-profile.component.html',
  styleUrls: ['./self-profile.component.css']
})
export class SelfProfileComponent implements OnInit {

  currentUser: User;
  constructor(private uploadService: UploaderService) { }

  ngOnInit() {
    this.currentUser = this.getCurrentUser();

    this.uploadService.uploadImage(this.currentUser.username, this.currentUser.token);
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

}
