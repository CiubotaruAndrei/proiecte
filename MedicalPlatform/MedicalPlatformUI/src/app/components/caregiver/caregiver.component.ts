import { Component, OnInit } from '@angular/core';
import { HttpClientService, User } from '../../services/http-client.service';
import { WebSocketAPIService } from '../../services/web-socket-api.service';

@Component({
  selector: 'app-caregiver',
  templateUrl: './caregiver.component.html',
  styleUrls: ['./caregiver.component.css']
})
export class CaregiverComponent implements OnInit {

  constructor(private httpService: HttpClientService, private webSocketAPI: WebSocketAPIService) { }

  alerts=[];
  rec: String[]=[];

  ngOnInit() {
    this.httpService.getCaregiverById(sessionStorage.getItem('id')).subscribe(
      data => {
        this.caregiver = data;
      }
    );
    this.httpService.getCaregiverPatients(sessionStorage.getItem('id')).subscribe(
      data => {
        console.log(data);
        this.patients = data;
      }
    );
    this.webSocketAPI._connect();
  }

  getRecommendations(id: string) {
    this.httpService.getRecommendations(id).subscribe(
      data => {
        console.log(data);
        if(data.length == 1 && data[0] == '')
          this.rec[id] = ['Doesn`t have recommendations'];
        else 
          this.rec[id] = data;
      }
    )
  }

  patients: User[] = [];
  caregiver: User;
  name: string;

  sendMessage(){
    this.webSocketAPI.send(this.name);
  }


  logOut() {
    this.httpService.logOut();
    this.webSocketAPI._disconnect();
  }



}
