import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { FileUploader, FileSelectDirective } from 'ng2-file-upload/ng2-file-upload';
import { ToastNotificationService } from '../services/toast-notification.service';

const URL = 'http://localhost:8080/api/upload';

@Component({
  selector: 'app-self-profile',
  templateUrl: './self-profile.component.html',
  styleUrls: ['./self-profile.component.css']
})
export class SelfProfileComponent implements OnInit {

  constructor(private toastNotificationService: ToastNotificationService) { }
  currentUser: User;
  public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'photo' });


  ngOnInit() {
    this.currentUser = this.getCurrentUser();

    //uploader
    this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      this.toastNotificationService.showNotification(`File ${item.file.name} is successfuly upload!`);
    };
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

}
