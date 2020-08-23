import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IHistoricoPrecoCombustivel } from 'app/shared/model/historico-preco-combustivel.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { HistoricoPrecoCombustivelService } from './historico-preco-combustivel.service';
import { HistoricoPrecoCombustivelDeleteDialogComponent } from './historico-preco-combustivel-delete-dialog.component';

@Component({
  selector: 'jhi-historico-preco-combustivel',
  templateUrl: './historico-preco-combustivel.component.html',
})
export class HistoricoPrecoCombustivelComponent implements OnInit, OnDestroy {
  historicoPrecoCombustivels?: IHistoricoPrecoCombustivel[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected historicoPrecoCombustivelService: HistoricoPrecoCombustivelService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.historicoPrecoCombustivelService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IHistoricoPrecoCombustivel[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInHistoricoPrecoCombustivels();
  }

  protected handleNavigation(): void {
    combineLatest(this.activatedRoute.data, this.activatedRoute.queryParamMap, (data: Data, params: ParamMap) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    }).subscribe();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IHistoricoPrecoCombustivel): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInHistoricoPrecoCombustivels(): void {
    this.eventSubscriber = this.eventManager.subscribe('historicoPrecoCombustivelListModification', () => this.loadPage());
  }

  delete(historicoPrecoCombustivel: IHistoricoPrecoCombustivel): void {
    const modalRef = this.modalService.open(HistoricoPrecoCombustivelDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.historicoPrecoCombustivel = historicoPrecoCombustivel;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IHistoricoPrecoCombustivel[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/historico-preco-combustivel'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.historicoPrecoCombustivels = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
}
