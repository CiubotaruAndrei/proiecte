import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
import { DoctorComponent } from './components/doctor/doctor.component';
import { PatientComponent } from './components/patient/patient.component';
import { CaregiverComponent } from './components/caregiver/caregiver.component';
import { AuthGuardService  as AuthGuard } from './services/auth-guard.service';
import { RoleGuardService as RoleGuard } from './services/role-guard.service';
import { HistoryComponent } from './components/history/history.component';


const routes: Routes = [
  {
    path:'',
    component: LoginComponent
  },
  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'doctor',
    component: DoctorComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: {
      expectedRole: 'doctor'
    }
  },
  {
    path:'patient',
    component: PatientComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: {
      expectedRole: 'patient'
    }
  },
  {
    path:'caregiver',
    component: CaregiverComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: {
      expectedRole: 'caregiver'
    }
  },
  {
    path:'history',
    component: HistoryComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
