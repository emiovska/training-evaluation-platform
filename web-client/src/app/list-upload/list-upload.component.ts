import { Component, OnInit } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs';
import { UploadFileService } from '../services/uploader.service';

@Component({
  selector: 'list-upload',
  templateUrl: './list-upload.component.html',
  styleUrls: ['./list-upload.component.css']
})
export class ListUploadComponent implements OnInit {

  showFile = false;
  fileUploads: Observable<string[]>;
 
  constructor(private uploadService: UploadFileService) { }
 
  ngOnInit() {
  }
 
  showFiles(enable: boolean) {
    this.showFile = enable;
 
    if (enable) {
      this.fileUploads = this.uploadService.getFiles();
    }
  }
}
