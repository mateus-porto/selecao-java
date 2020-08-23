import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IHistoricoPrecoCombustivel, HistoricoPrecoCombustivel } from 'app/shared/model/historico-preco-combustivel.model';
import { HistoricoPrecoCombustivelService } from './historico-preco-combustivel.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-historico-preco-combustivel-update',
  templateUrl: './historico-preco-combustivel-update.component.html',
})
export class HistoricoPrecoCombustivelUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  dataColetaDp: any;

  editForm = this.fb.group({
    id: [],
    regiaoSigla: [],
    estadoSigla: [],
    municipio: [],
    revenda: [],
    cnpj: [],
    produto: [],
    dataColeta: [],
    valorVenda: [],
    valorCompra: [],
    unidade: [],
    bandeira: [],
    userId: [],
  });

  constructor(
    protected historicoPrecoCombustivelService: HistoricoPrecoCombustivelService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historicoPrecoCombustivel }) => {
      this.updateForm(historicoPrecoCombustivel);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(historicoPrecoCombustivel: IHistoricoPrecoCombustivel): void {
    this.editForm.patchValue({
      id: historicoPrecoCombustivel.id,
      regiaoSigla: historicoPrecoCombustivel.regiaoSigla,
      estadoSigla: historicoPrecoCombustivel.estadoSigla,
      municipio: historicoPrecoCombustivel.municipio,
      revenda: historicoPrecoCombustivel.revenda,
      cnpj: historicoPrecoCombustivel.cnpj,
      produto: historicoPrecoCombustivel.produto,
      dataColeta: historicoPrecoCombustivel.dataColeta,
      valorVenda: historicoPrecoCombustivel.valorVenda,
      valorCompra: historicoPrecoCombustivel.valorCompra,
      unidade: historicoPrecoCombustivel.unidade,
      bandeira: historicoPrecoCombustivel.bandeira,
      userId: historicoPrecoCombustivel.userId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const historicoPrecoCombustivel = this.createFromForm();
    if (historicoPrecoCombustivel.id !== undefined) {
      this.subscribeToSaveResponse(this.historicoPrecoCombustivelService.update(historicoPrecoCombustivel));
    } else {
      this.subscribeToSaveResponse(this.historicoPrecoCombustivelService.create(historicoPrecoCombustivel));
    }
  }

  private createFromForm(): IHistoricoPrecoCombustivel {
    return {
      ...new HistoricoPrecoCombustivel(),
      id: this.editForm.get(['id'])!.value,
      regiaoSigla: this.editForm.get(['regiaoSigla'])!.value,
      estadoSigla: this.editForm.get(['estadoSigla'])!.value,
      municipio: this.editForm.get(['municipio'])!.value,
      revenda: this.editForm.get(['revenda'])!.value,
      cnpj: this.editForm.get(['cnpj'])!.value,
      produto: this.editForm.get(['produto'])!.value,
      dataColeta: this.editForm.get(['dataColeta'])!.value,
      valorVenda: this.editForm.get(['valorVenda'])!.value,
      valorCompra: this.editForm.get(['valorCompra'])!.value,
      unidade: this.editForm.get(['unidade'])!.value,
      bandeira: this.editForm.get(['bandeira'])!.value,
      userId: this.editForm.get(['userId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistoricoPrecoCombustivel>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
