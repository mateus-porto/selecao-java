import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IHistoricoPrecoCombustivel, HistoricoPrecoCombustivel } from 'app/shared/model/historico-preco-combustivel.model';
import { HistoricoPrecoCombustivelService } from './historico-preco-combustivel.service';
import { HistoricoPrecoCombustivelComponent } from './historico-preco-combustivel.component';
import { HistoricoPrecoCombustivelDetailComponent } from './historico-preco-combustivel-detail.component';
import { HistoricoPrecoCombustivelUpdateComponent } from './historico-preco-combustivel-update.component';

@Injectable({ providedIn: 'root' })
export class HistoricoPrecoCombustivelResolve implements Resolve<IHistoricoPrecoCombustivel> {
  constructor(private service: HistoricoPrecoCombustivelService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IHistoricoPrecoCombustivel> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((historicoPrecoCombustivel: HttpResponse<HistoricoPrecoCombustivel>) => {
          if (historicoPrecoCombustivel.body) {
            return of(historicoPrecoCombustivel.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new HistoricoPrecoCombustivel());
  }
}

export const historicoPrecoCombustivelRoute: Routes = [
  {
    path: '',
    component: HistoricoPrecoCombustivelComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'appApp.historicoPrecoCombustivel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: HistoricoPrecoCombustivelDetailComponent,
    resolve: {
      historicoPrecoCombustivel: HistoricoPrecoCombustivelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.historicoPrecoCombustivel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: HistoricoPrecoCombustivelUpdateComponent,
    resolve: {
      historicoPrecoCombustivel: HistoricoPrecoCombustivelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.historicoPrecoCombustivel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: HistoricoPrecoCombustivelUpdateComponent,
    resolve: {
      historicoPrecoCombustivel: HistoricoPrecoCombustivelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.historicoPrecoCombustivel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
