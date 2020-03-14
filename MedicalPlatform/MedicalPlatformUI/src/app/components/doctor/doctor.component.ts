import { Component, OnInit } from '@angular/core';
import { HttpClientService, User, Medication, MedicationPlan, IntakeMed } from '../../services/http-client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit {

  constructor(private httpService: HttpClientService, private router: Router) {
  }
  
  ngOnInit() {
    this.httpService.getCaregivers().subscribe(
      data => {
        this.caregivers = data;
      }
    );
    this.httpService.getPatients().subscribe(
      data => {
        this.patients = data;
      }
    );
    this.httpService.getMedications().subscribe(
      data => {
        this.medications = data;
      }
    );
}

  patientOp: string = "";
  caregiverOp: string = "";
  medicationOp: string = "";
  patient: User = new User("","","","","","","","");
  newcaregiver: User = new User("","","","","","","","");
  newmedication: Medication = new Medication("","","","");
  medicationplan: MedicationPlan = new MedicationPlan("","","");
  caregivers: User[] = [];
  patients: User[] = [];
  medications: Medication[] = [];
  caregiverId: string = "";
  patientUpdate: string = "";
  caregiverUpdate: string = "";
  medicationUpdate: string = "";
  patientPlan: string = "";
  intakeMedId: string = "";
  intakemed: IntakeMed = new IntakeMed("","","","");

  addPatient() {
    let ok: boolean = true;
    if(this.patient.gender == "" || this.patient.name == "" || this.patient.password == ""
  || this.patient.address == "" || this.patient.email == "" || this.patient.birthDate == ""
  || this.patient.medicalRecord == "" || this.caregiverId == "") {
      ok = false;
      alert("Fill the mandatory fields!");
    }
    console.log("my input:", this.caregiverId);
    console.log(this.patient);
    if(ok) {
      this.httpService.getPatientByEmail(this.patient.email).subscribe(
        data => {
          if(!data)
            this.httpService.createPatient(this.caregiverId, this.patient).subscribe(
                data => {
                  if(data)
                    alert("Add patient success");
                  else
                    alert("Add patient fail");
              }
            );
          else
            alert("This email is already used");
        }
      );
    }
  }

  updatePatient() {
    let ok: boolean = true;
    if(this.patientUpdate == "") {
      ok = false;
      alert("Select patient for update!");
    }

    if(ok) {
      this.httpService.updatePatient(this.patientUpdate, this.patient, this.caregiverId).subscribe(
        data => {
          if(!data)
            alert("Update fail");
          else {
            console.log(data);
            alert("Update successfull");
          }
        }
      );
    }
  }

  deletePatient(id: string) {
    this.httpService.deletePatient(id).subscribe();
  }

  patientOperation() {
    if(this.patientOp == "1")
      this.addPatient();
    else if(this.patientOp == "2")
      this.updatePatient();
    else {
      alert("Set opration for patient");
    }
  }

  addCaregiver() {
    let ok: boolean = true;
    if(this.newcaregiver.gender == "" || this.newcaregiver.name == "" || this.newcaregiver.password == ""
  || this.newcaregiver.address == "" || this.newcaregiver.email == "" || this.newcaregiver.birthDate == "") {
      ok = false;
      alert("Fill the mandatory fields!");
    }
    console.log(ok, this.newcaregiver);
    if(ok) {
      this.httpService.getCaregiverByEmail(this.newcaregiver.email).subscribe(
        data => {
          if(!data)
            this.httpService.createCaregiver(this.newcaregiver).subscribe(
              data => {
                  if(data)
                    alert("Add caregiver success");
                  else
                   alert("Add caregiver fail");
              }
            );
          else
            alert("This email is already used");
        }
      );
    }
  }

  updateCaregiver() {
    let ok: boolean = true;
    if(this.caregiverUpdate == "") {
      ok = false;
      alert("Select caregiver for update!");
    }

    if(ok) {
      this.httpService.updateCaregiver(this.caregiverUpdate, this.newcaregiver).subscribe(
        data => {
          if(!data)
            alert("Update fail");
          else {
            console.log(data);
            alert("Update successfull");
          }
        }
      );
    }
  }

  deleteCaregiver(id: string) {
    this.httpService.deleteCaregiver(id).subscribe();
  }

  caregiverOperation() {
    if(this.caregiverOp == "1")
      this.addCaregiver();
    else if(this.caregiverOp == "2")
      this.updateCaregiver();
    else {
      alert("Set opration for caregiver");
    }
  }

  addMedication() {
    let ok: boolean = true;
    if(this.newmedication.name == "" || this.newmedication.dosage == "" || this.newmedication.sideEffects == "") {
      ok = false;
      alert("Fill the mandatory fields!");
    }

    if(ok) {
      this.httpService.getMedByName(this.newmedication.name).subscribe(
        data => {
          if(!data) {
            this.httpService.createMedication(this.newmedication).subscribe(
              data => {
                if(data)
                  alert("Add medication success");
                else
                 alert("Add medication fail");
              }
            );
          }
          else
            alert("Medication is in database");
        }
      );

    }
  }

  updateMedication() {
    let ok: boolean = true;
    if(this.medicationUpdate == "") {
      ok = false;
      alert("Select medication for update!");
    }

    if(ok) {
      this.httpService.updateMedication(this.medicationUpdate, this.newmedication).subscribe(
        data => {
          if(!data)
            alert("Update fail");
          else {
            console.log(data);
            alert("Update successfull");
          }
        }
      );
    }
  }

  medicationOperation() {
    if(this.medicationOp == "1")
      this.addMedication();
    else if(this.medicationOp == "2")
      this.updateMedication();
    else {
      alert("Set opration for medication");
    }
  }

  deleteMedication(id: string) {
    this.httpService.deleteMedication(id).subscribe();
  }

  addMedicationPlan() {
    let ok: boolean = true;
    if(this.patientPlan == "" || this.medicationplan.start == "" || this.medicationplan.end == "") {
      ok = false;
      alert("Fill the mandatory fields!");
    }
    console.log(ok, this.medicationplan);
    if(ok) {
      this.httpService.createMedicationPlan(this.patientPlan, this.medicationplan).subscribe(
        data => {
          console.log(data);
          if(data) {
            sessionStorage.setItem('medPlanId', data.id);
            alert('Medication plan create successfull');
          }
          else
            alert('Medication plan create fail');
        }
      );
    }
  }

  addIntake() {
    console.log('medication plan id: ' + sessionStorage.getItem('medPlanId'));
    let ok: boolean = true;
    if(sessionStorage.getItem('medPlanId') == "") {
      ok = false;
      alert("First add a prescription");
    }
    if(this.intakeMedId == "" || this.intakemed.intakeMoments == "") {
      ok = false;
      alert("Fill the mandatory fields!");
    }

    if(ok) {
      this.httpService.createIntakeMed(this.intakeMedId, sessionStorage.getItem('medPlanId'), this.intakemed).subscribe(
        data => {
          console.log(data);
          if(data)
            alert("Add intake medication success");
          else
            alert("Add intake medication fail");
        }
      );
    }
  }

  getHistory(id: string) {
    sessionStorage.setItem('patientId', id);
    window.open('/history', '_blank');
    //this.router.navigate(['history']);
  }

  onItemChange(value){
   //console.log(" Value is : ", value );
   //console.log(this.patientOp);
 }

 logOut() {
   this.httpService.logOut();
 }

}
