import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { HistoricoPrecoCombustivelComponent } from './historico-preco-combustivel.component';
import { HistoricoPrecoCombustivelDetailComponent } from './historico-preco-combustivel-detail.component';
import { HistoricoPrecoCombustivelUpdateComponent } from './historico-preco-combustivel-update.component';
import { HistoricoPrecoCombustivelDeleteDialogComponent } from './historico-preco-combustivel-delete-dialog.component';
import { historicoPrecoCombustivelRoute } from './historico-preco-combustivel.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(historicoPrecoCombustivelRoute)],
  declarations: [
    HistoricoPrecoCombustivelComponent,
    HistoricoPrecoCombustivelDetailComponent,
    HistoricoPrecoCombustivelUpdateComponent,
    HistoricoPrecoCombustivelDeleteDialogComponent,
  ],
  entryComponents: [HistoricoPrecoCombustivelDeleteDialogComponent],
})
export class AppHistoricoPrecoCombustivelModule {}
