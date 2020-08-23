import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHistoricoPrecoCombustivel } from 'app/shared/model/historico-preco-combustivel.model';
import { HistoricoPrecoCombustivelService } from './historico-preco-combustivel.service';

@Component({
  templateUrl: './historico-preco-combustivel-delete-dialog.component.html',
})
export class HistoricoPrecoCombustivelDeleteDialogComponent {
  historicoPrecoCombustivel?: IHistoricoPrecoCombustivel;

  constructor(
    protected historicoPrecoCombustivelService: HistoricoPrecoCombustivelService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.historicoPrecoCombustivelService.delete(id).subscribe(() => {
      this.eventManager.broadcast('historicoPrecoCombustivelListModification');
      this.activeModal.close();
    });
  }
}
