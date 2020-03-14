import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { LoginService} from '../../services/login.service';
import { UserI } from  '../../interfaces/UserI';
import { User } from '../../services/http-client.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private loginService: LoginService) { }

  ngOnInit() {
  }

  user: UserI;
  errorMessage = '';
  email = '';
  password = '';
  repeatpassword = '';
  newdoctor: User = new User("","","","","","","","");
  userType = '';

  login() {
    let ok = true;
    if(this.email=="" || this.password=="") {
      this.errorMessage = 'Fill the fields!';
      ok = false;
    }
    else {
      if(this.userType == '1')
        this.loginService.getDoctorLogin(this.email, this.password).subscribe(
          data => {
            this.user = data;
            if(this.user)
                this.goTo('doctor');
            else
              this.errorMessage = 'Email or password invalid!';
            });
        else if(this.userType == '3')
          this.loginService.getPatientLogin(this.email, this.password).subscribe(
            data => {
              this.user = data;
              if(this.user)
                this.goTo('patient');
              else
                this.errorMessage = 'Email or password invalid!';
              });
        else if(this.userType == '2')
          this.loginService.getCaregiverLogin(this.email, this.password).subscribe(
            data => {
              this.user = data;
              if(this.user)
                this.goTo('caregiver');
              else
                this.errorMessage = 'Email or password invalid!';
            });
        else
          this.errorMessage = 'Select user type';
    }

  }

  addDoctor() {
    let ok: boolean = true;
    if(this.newdoctor.gender == "" || this.newdoctor.name == "" || this.newdoctor.password == ""
  || this.newdoctor.address == "" || this.newdoctor.email == "" || this.newdoctor.birthDate == "") {
      ok = false;
      alert("Fill the mandatory fields!");
    }
    if(this.newdoctor.password != this.repeatpassword) {
      ok = false;
      alert("Passwords doesn`t match!");
    }
    console.log(ok, this.newdoctor);
    if(ok) {
      this.loginService.getDoctorByEmail(this.newdoctor.email).subscribe(
        data => {
          if(!data)
            this.loginService.createDoctor(this.newdoctor).subscribe(
              data => {
                  if(data) {
                    alert("Now login");
                  }
                  else
                   alert("Add doctor fail");
              }
            );
          else
            alert("This email is already used");
        }
      );
    }
  }

  goTo(path: string){
    this.router.navigate([path]);
  }
}
