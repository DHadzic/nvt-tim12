import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthenticationService } from 'src/app/services/security/authentication-service.service';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.scss']
})
export class VerifyComponent implements OnInit {

  selectedFile = null;
  currUser = null;

  constructor(private userService:UserService, private authenticationService:AuthenticationService) { }

  ngOnInit() {
    this.currUser = this.authenticationService.getCurrentUser();
  }

  onFileSelected(event){
    this.selectedFile = event.target.files[0];
  }

  onUpload(){
    console.log(this.selectedFile);
    this.userService.uploadDocumentImage(this.currUser.username, this.selectedFile).subscribe(success => {
      console.log('uspeh');
    })
  }
}
