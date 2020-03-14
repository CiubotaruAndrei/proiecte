import { Component, OnInit } from '@angular/core';
import { HttpClientService, Activity } from '../../services/http-client.service';
import { $ } from 'protractor';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  charts: any[];
  chartsOptions: any[];
  patient: string;
  date: string;
  textarea: string;
  infoActivity = '';
  infoMeds = '';
  infoUnusual = '';
  showTable = false;
  showChart = false;
  showTable2 = false;
  

  constructor(private httpService: HttpClientService) { }

  col: any[]=[];
  labels: any[]=[];
  values: any[]=[];
  dailyMeds: any[]=[];
  unusualActivities: Activity[]=[];
  
  ngOnInit() {
    this.httpService.getPatientById(sessionStorage.getItem('id')).subscribe(
      data => {
        this.patient = data.name;
      }
    );
  }

  chartType: string = 'pie';

  chartLabels = this.labels;

  chartDatasets: Array<any> = [
    { data: this.values, label: 'History dataset' }
  ];

  chartColors: Array<any> = [
    {
      backgroundColor: ['#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360', '#ebe534', '#0a25a1', '#e314dc'],
      hoverBackgroundColor: ['#FF5A5E', '#5AD3D1', '#FFC870', '#A8B3C5', '#616774', '#e6e15e', '#4961d1', '#e675e2'],
      borderWidth: 1,
    }
  ];

  chartOptions: any = {
    responsive: true
  };

  chartClicked(e: any): void { }
  chartHovered(e: any): void { }

  getHistory() {
    console.log(this.date);
    let id = sessionStorage.getItem('patientId');
    this.httpService.getActvityHistory(id, this.date).subscribe(
      data=>{
        //console.log(data);
        while(this.values.length > 0) {
          this.values.pop();
          this.labels.pop();
      }
        for (var i = 0; i < data.length; i++) {
          if(data[i].duration!==0) {
            //this.col.push([data[i].name, data[i].duration]);
            this.labels.push(data[i].name);
            this.values.push(data[i].duration);
          }
      }
      //console.log(this.col);
      console.log(this.labels);
      console.log(this.values);
        if(this.values.length == 0) {
          this.infoActivity = 'Patient doesn`t have activities for this day!!!';
          this.showChart = false;
        }
        else {
          this.showChart = true;
          this.infoActivity = '';
        }
      });

      this.httpService.getDailyMeds(id, this.date).subscribe(
        data => {
          console.log(data);
          this.dailyMeds = data;
          if(data.length == 0) {
             this.infoMeds = 'Patient doesn`t have medication plan for this day!!!';
             this.showTable = false;
          }
          else {
            this.showTable = true;
            this.infoMeds = '';
          }
        }
      )

      this.httpService.getUnusualActivities(id, this.date).subscribe(
        data =>{
          console.log(data);
          this.unusualActivities = data;
          if(data.length == 0) {
            this.infoUnusual = 'Patient doesn`t have unusual activities for this day!!!';
            this.showTable2 = false;
         }
         else {
           this.showTable2 = true;
           this.infoUnusual = '';
         }
        }
      )
      
  }

  addRecommendation(id: string) {
    this.httpService.addRecommendation(id, this.textarea).subscribe(
      data => {
        console.log(data);
        if(data == '200') {
          alert('Success'); 
        }
        else {
          alert('Fail');
        }
      }
    )
  }

}
