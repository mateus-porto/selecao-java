import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistoricoPrecoCombustivel } from 'app/shared/model/historico-preco-combustivel.model';

@Component({
  selector: 'jhi-historico-preco-combustivel-detail',
  templateUrl: './historico-preco-combustivel-detail.component.html',
})
export class HistoricoPrecoCombustivelDetailComponent implements OnInit {
  historicoPrecoCombustivel: IHistoricoPrecoCombustivel | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historicoPrecoCombustivel }) => (this.historicoPrecoCombustivel = historicoPrecoCombustivel));
  }

  previousState(): void {
    window.history.back();
  }
}
