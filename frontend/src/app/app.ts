
import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AuthService } from './services/auth.service';

interface Product {
  id: string;
  name: string;
  price: number;
  quantity: number;
  status: string;
  categoryId: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  styleUrl: './app.css',

  template: `
    <div class="dashboard">

      <!-- HEADER -->
      <header class="header">
        <div class="brand">
          <div class="brand-logo">D</div>
          <div>
            <h2>Commerce<span>Hub</span></h2>
            <small>Distributed Commerce Platform</small>
          </div>
        </div>

        <div class="header-actions">
          @if (!auth.isLoggedIn()) {
            <button class="btn btn-primary" (click)="auth.login()">
              <span>↪</span> Se connecter
            </button>
          } @else {
            <div class="user-info">
              <div class="avatar">YB</div>
              <span>Administrator</span>
            </div>
            <button class="btn btn-outline" (click)="auth.logout()">
              Déconnexion
            </button>
          }
        </div>
      </header>

      <!-- MAIN -->
      <main class="container">

        <section class="welcome">
          <div>
            <span class="eyebrow">ESPACE DE GESTION</span>
            <h1>Bienvenue sur votre Dashboard</h1>
            <p>Gérez votre catalogue et consultez les informations de votre inventaire.</p>
          </div>
          <div class="welcome-icon">📊</div>
        </section>

        @if (!auth.isLoggedIn()) {

          <section class="auth-card">
            <div class="auth-icon">🔐</div>
            <h2>Authentification requise</h2>
            <p>Connectez-vous pour accéder aux informations de votre plateforme.</p>
            <button class="btn btn-primary" (click)="auth.login()">
              Se connecter à la plateforme
            </button>
          </section>

        } @else {

          <!-- PRODUCTS SECTION -->
          <section class="content-card">

            <div class="section-header">
              <div>
                <span class="eyebrow">INVENTAIRE</span>
                <h2>Catalogue des produits</h2>
                <p>Consultez les produits enregistrés dans votre inventaire.</p>
              </div>

              <button class="btn btn-primary" (click)="getProducts()">
                <span>↻</span> Afficher les produits
              </button>
            </div>

            @if (products) {

              <div class="stats">
                <div class="stat">
                  <div class="stat-icon blue">▦</div>
                  <div>
                    <small>Total produits</small>
                    <h3>{{ products.length }}</h3>
                  </div>
                </div>

                <div class="stat">
                  <div class="stat-icon green">✓</div>
                  <div>
                    <small>Disponibles</small>
                    <h3>{{ availableCount }}</h3>
                  </div>
                </div>

                <div class="stat">
                  <div class="stat-icon orange">!</div>
                  <div>
                    <small>Rupture de stock</small>
                    <h3>{{ outOfStockCount }}</h3>
                  </div>
                </div>
              </div>

              @if (products.length > 0) {

                <div class="table-wrapper">
                  <table>
                    <thead>
                    <tr>
                      <th>PRODUIT</th>
                      <th>IDENTIFIANT</th>
                      <th>CATÉGORIE</th>
                      <th>PRIX</th>
                      <th>QUANTITÉ</th>
                      <th>STATUT</th>
                    </tr>
                    </thead>

                    <tbody>
                      @for (product of products; track product.id) {
                        <tr>
                          <td>
                            <div class="product-name">
                              <div class="product-icon">📦</div>
                              <strong>{{ product.name }}</strong>
                            </div>
                          </td>

                          <td>
                            <span class="product-id">
                              {{ product.id }}
                            </span>
                          </td>

                          <td>
                            <span class="category">
                              {{ product.categoryId }}
                            </span>
                          </td>

                          <td class="price">
                            {{ product.price | number:'1.2-2' }} MAD
                          </td>

                          <td>
                            <strong>{{ product.quantity }}</strong>
                          </td>

                          <td>
                            <span class="status"
                                  [class.available]="product.status === 'AVAILABLE'"
                                  [class.unavailable]="product.status === 'OUT_OF_STOCK'">
                              <span class="status-dot"></span>
                              {{ product.status }}
                            </span>
                          </td>
                        </tr>
                      }
                    </tbody>
                  </table>
                </div>

              } @else {
                <div class="empty-state">
                  <div>📦</div>
                  <h3>Aucun produit disponible</h3>
                  <p>Votre inventaire ne contient actuellement aucun produit.</p>
                </div>
              }

            } @else {

              <div class="empty-state">
                <div>🛍️</div>
                <h3>Votre catalogue vous attend</h3>
                <p>Cliquez sur « Afficher les produits » pour charger votre inventaire.</p>
              </div>

            }

          </section>
        }

        <footer>
          <span>© 2026 Distributed Commerce Platform</span>
          <span>Powered by Angular & Spring Boot</span>
        </footer>

      </main>
    </div>
  `
})
export class App {

  auth = inject(AuthService);
  private http = inject(HttpClient);

  products: Product[] | null = null;

  get availableCount(): number {
    return this.products?.filter(
      p => p.status === 'AVAILABLE'
    ).length ?? 0;
  }

  get outOfStockCount(): number {
    return this.products?.filter(
      p => p.status === 'OUT_OF_STOCK'
    ).length ?? 0;
  }

  getProducts() {
    this.http.get<Product[]>(
      'http://localhost:8080/inventory-service/query/products'
    ).subscribe({
      next: data => {
        this.products = data;
      },
      error: error => {
        console.error('Erreur lors du chargement des produits :', error);
      }
    });
  }
}
