import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHistoricoPrecoCombustivel } from 'app/shared/model/historico-preco-combustivel.model';

type EntityResponseType = HttpResponse<IHistoricoPrecoCombustivel>;
type EntityArrayResponseType = HttpResponse<IHistoricoPrecoCombustivel[]>;

@Injectable({ providedIn: 'root' })
export class HistoricoPrecoCombustivelService {
  public resourceUrl = SERVER_API_URL + 'api/historico-preco-combustivels';

  constructor(protected http: HttpClient) {}

  create(historicoPrecoCombustivel: IHistoricoPrecoCombustivel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historicoPrecoCombustivel);
    return this.http
      .post<IHistoricoPrecoCombustivel>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(historicoPrecoCombustivel: IHistoricoPrecoCombustivel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historicoPrecoCombustivel);
    return this.http
      .put<IHistoricoPrecoCombustivel>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHistoricoPrecoCombustivel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHistoricoPrecoCombustivel[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(historicoPrecoCombustivel: IHistoricoPrecoCombustivel): IHistoricoPrecoCombustivel {
    const copy: IHistoricoPrecoCombustivel = Object.assign({}, historicoPrecoCombustivel, {
      dataColeta:
        historicoPrecoCombustivel.dataColeta && historicoPrecoCombustivel.dataColeta.isValid()
          ? historicoPrecoCombustivel.dataColeta.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataColeta = res.body.dataColeta ? moment(res.body.dataColeta) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((historicoPrecoCombustivel: IHistoricoPrecoCombustivel) => {
        historicoPrecoCombustivel.dataColeta = historicoPrecoCombustivel.dataColeta
          ? moment(historicoPrecoCombustivel.dataColeta)
          : undefined;
      });
    }
    return res;
  }
}
