import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'historico-preco-combustivel',
        loadChildren: () =>
          import('./historico-preco-combustivel/historico-preco-combustivel.module').then(m => m.AppHistoricoPrecoCombustivelModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class AppEntityModule {}
