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
  validatorRole = false;
  requests = [];

  constructor(private userService:UserService, private authenticationService:AuthenticationService) { }

  ngOnInit() {
    this.currUser = this.authenticationService.getCurrentUser();
    if (this.isValidator()){
      this.validatorRole = true;
      this.getVerificatonRequests();
    }else{
      this.validatorRole = false;
    }
  }

  isValidator(){
    var roles = this.authenticationService.getRoles();
    return roles.includes("VALIDATOR_ROLE");
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

  getVerificatonRequests(){
    this.userService.getVerificationRequests(this.currUser.username).subscribe(success => {
      this.setRequests(success)
    },
    error => { console.log("There are no requests."); })
  }

  setRequests(data){
    console.log(data);
    console.log("ovde");
    this.requests = data;
  }

  accept(data){
    alert("Accepted")!
    var a = this.requests.indexOf(data);
    this.requests.splice(a,1);
    }

  decline(data){
    alert("Declined")!
    var a = this.requests.indexOf(data);
    this.requests.splice(a,1);

  }
}
