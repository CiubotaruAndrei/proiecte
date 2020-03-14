import { Component, OnInit } from '@angular/core';
import { HttpClientService, User, Medication } from '../../services/http-client.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  constructor(private httpService: HttpClientService) {

  }

  ngOnInit() {
    this.httpService.getPatientById(sessionStorage.getItem('id')).subscribe(
      data => {
        console.log(data);
        this.patient = data;
        if(this.patient.gender === "male")
          this.avatarPath = "/../../assets/img/male.png";
        else
          this.avatarPath = "/../../assets/img/female.png";
      }
    );
  }

  patient: User = new User("","","","","","","","");
  avatarPath: String;

  logOut() {
    this.httpService.logOut();
  }

}
