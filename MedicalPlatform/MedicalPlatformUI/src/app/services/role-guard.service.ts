import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot } from '@angular/router';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot) {
    const expectedRole = route.data.expectedRole;
    if (this.loginService.isAuthenticated() && sessionStorage.getItem('role') === expectedRole)
      return true;

      this.router.navigate([sessionStorage.getItem('role')]);//404
      return false;
  }

}
