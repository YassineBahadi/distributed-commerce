
import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { from, of, switchMap } from 'rxjs';
import { AuthService } from '../services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  const auth = inject(AuthService);

  if (!req.url.startsWith('http://localhost:8080')) {
    return next(req);
  }

  const refresh = auth.isLoggedIn()
    ? from(auth.updateToken())
    : of(false);

  return refresh.pipe(
    switchMap(() => {
      const token = auth.getToken();

      if (!token) {
        return next(req);
      }

      return next(req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      }));
    })
  );
};
