import { Moment } from 'moment';

export interface IHistoricoPrecoCombustivel {
  id?: number;
  regiaoSigla?: string;
  estadoSigla?: string;
  municipio?: string;
  revenda?: string;
  cnpj?: string;
  produto?: string;
  dataColeta?: Moment;
  valorVenda?: number;
  valorCompra?: number;
  unidade?: string;
  bandeira?: string;
  userLogin?: string;
  userId?: number;
}

export class HistoricoPrecoCombustivel implements IHistoricoPrecoCombustivel {
  constructor(
    public id?: number,
    public regiaoSigla?: string,
    public estadoSigla?: string,
    public municipio?: string,
    public revenda?: string,
    public cnpj?: string,
    public produto?: string,
    public dataColeta?: Moment,
    public valorVenda?: number,
    public valorCompra?: number,
    public unidade?: string,
    public bandeira?: string,
    public userLogin?: string,
    public userId?: number
  ) {}
}
