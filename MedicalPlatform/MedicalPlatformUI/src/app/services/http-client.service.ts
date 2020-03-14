import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { RouterModule, Router } from '@angular/router';


export class User {
  constructor(
    public id: string,
    public name: string,
    public email: string,
    public password: string,
    public gender: string,
    public birthDate: string,
    public address: string,
    public medicalRecord: string
  ) { }
}

export class Medication {
  constructor(
    public id: string,
    public name: string,
    public dosage: string,
    public sideEffects: string
  ) { }
}

export class MedicationPlan {
  constructor(
    public id: string,
    public start: string,
    public end: string
  ) { }
}

export class IntakeMed {
  constructor(
    public id: string,
    public medPlanId: string,
    public medId: string,
    public intakeMoments: string
  ) { }
}

export class Activity {
  constructor(
    public id: number,
    public name: string,
    public duration: number,
    public unusual: string
  ) { }
}

export class DailyMed {
  constructor(
    public name: string,
    public time: string,
    public taken: string
  ) { }
}


@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private http: HttpClient, private router: Router) { }

  createPatient(caregiverId, patient) {
    let requestUrl = 'server/patients/' + sessionStorage.getItem('id') + '/' + caregiverId;
    return this.http.post<User>(requestUrl, patient);
  }

  getPatients() {
    let requestUrl = 'server/patients';
    return this.http.get<User[]>(requestUrl);
  }

  deletePatient(id: string) {
    let requestUrl = 'server/patients/' + id;
    return this.http.delete(requestUrl);
  }

  updatePatient(id: string, patient, idCaregiver: string) {
    let requestUrl = 'server/patients/' + id + '/' + idCaregiver;
    return this.http.put<User>(requestUrl, patient);
  }

  createCaregiver(caregiver) {
    let requestUrl = 'server/caregivers';
    return this.http.post<User>(requestUrl, caregiver);
  }

  getCaregivers() {
    let requestUrl = 'server/caregivers';
    return this.http.get<User[]>(requestUrl);
  }

  deleteCaregiver(id: string) {
    let requestUrl = 'server/caregivers/' + id;
    return this.http.delete(requestUrl);
  }

  updateCaregiver(id: string, caregiver) {
    let requestUrl = 'server/caregivers/' + id;
    return this.http.put<User>(requestUrl, caregiver);
  }

  createMedication(medication) {
    let requestUrl = 'server/medications';
    return this.http.post<Medication>(requestUrl, medication);
  }

  getMedications() {
    let requestUrl = 'server/medications';
    return this.http.get<Medication[]>(requestUrl);
  }

  getMedByName(name: string) {
    let requestUrl = 'server/medications/name/' + name;
    return this.http.get<Medication>(requestUrl);
  }

  deleteMedication(id: string) {
    let requestUrl = 'server/medications/' + id;
    return this.http.delete(requestUrl);
  }

  updateMedication(id: string, medication) {
    let requestUrl = 'server/medications/' + id;
    return this.http.put<Medication>(requestUrl, medication);
  }

  createMedicationPlan(patient: string, medicationplan) {
    let requestUrl = 'server/medicationplans/' + patient;
    return this.http.post<MedicationPlan>(requestUrl, medicationplan);
  }

  getPatientByEmail(email: string) {
    let requestUrl = 'server/patients/email/' + email;
    return this.http.get<User>(requestUrl);
  }

  getCaregiverByEmail(email: string) {
    let requestUrl = 'server/caregivers/email/' + email;
    return this.http.get<User>(requestUrl);
  }

  getCaregiverPatients(id: string) {
    let requestUrl = 'server/caregivers/' + id + '/patients';
    return this.http.get<User[]>(requestUrl);
  }

  createIntakeMed(medId: string, medPlanId: string, intake) {
    let requestUrl = 'server/intake/' + medId + '/' + medPlanId;
    return this.http.post<IntakeMed>(requestUrl, intake);
  }

  getCaregiverById(id: string) {
    let requestUrl = 'server/caregivers/' + id;
    return this.http.get<User>(requestUrl);
  }

  getPatientById(id: string) {
    let requestUrl = 'server/patients/' + id;
    return this.http.get<User>(requestUrl);
  }

  getMedicationById(id: string) {
    let requestUrl = 'server/medications/' + id;
    return this.http.get<Medication>(requestUrl);
  }

  logOut() {
    sessionStorage.clear();
    this.router.navigate(['login']);
  }

  getActvityHistory(id: string, date: string) {
    let requestUrl = 'soap/patient/history/' + id + '/' + date;
    return this.http.get<Activity[]>(requestUrl); 
  }

  getDailyMeds(id: string, date: string) {
    let requestUrl = 'soap/patient/dailymeds/' + id + '/' + date;
    return this.http.get<DailyMed[]>(requestUrl);
  }

  getUnusualActivities(id: string, date: string) {
    let requestUrl = 'soap/patient/unusual/' + id + '/' + date;
    return this.http.get<Activity[]>(requestUrl);
  }

  addRecommendation(id: string, text: string) {
    let requestUrl = 'soap/patient/addRecommendation';
    return this.http.put<String>(requestUrl, {'id': id, 'text': text});
  }

  getRecommendations(id: string) {
    let requestUrl = 'server/caregivers/rec/' + id;
    return this.http.get<String[]>(requestUrl);
  }
}
