import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private keycloak = new Keycloak({
    url: 'http://localhost:8085',
    realm: 'commerce',
    clientId: 'commerce-client'
  });

  async init() {
    await this.keycloak.init({
      onLoad: 'check-sso',
      pkceMethod: 'S256'
    });
  }

  login() {
    return this.keycloak.login();
  }

  logout() {
    return this.keycloak.logout({
      redirectUri: window.location.origin
    });
  }

  isLoggedIn() {
    return this.keycloak.authenticated;
  }

  getToken() {
    return this.keycloak.token;
  }

  async updateToken() {
    return this.keycloak.updateToken(30);
  }
}
