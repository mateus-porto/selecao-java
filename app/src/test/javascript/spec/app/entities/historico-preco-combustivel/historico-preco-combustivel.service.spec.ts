import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { HistoricoPrecoCombustivelService } from 'app/entities/historico-preco-combustivel/historico-preco-combustivel.service';
import { IHistoricoPrecoCombustivel, HistoricoPrecoCombustivel } from 'app/shared/model/historico-preco-combustivel.model';

describe('Service Tests', () => {
  describe('HistoricoPrecoCombustivel Service', () => {
    let injector: TestBed;
    let service: HistoricoPrecoCombustivelService;
    let httpMock: HttpTestingController;
    let elemDefault: IHistoricoPrecoCombustivel;
    let expectedResult: IHistoricoPrecoCombustivel | IHistoricoPrecoCombustivel[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(HistoricoPrecoCombustivelService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new HistoricoPrecoCombustivel(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataColeta: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a HistoricoPrecoCombustivel', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataColeta: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataColeta: currentDate,
          },
          returnedFromService
        );

        service.create(new HistoricoPrecoCombustivel()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a HistoricoPrecoCombustivel', () => {
        const returnedFromService = Object.assign(
          {
            regiaoSigla: 'BBBBBB',
            estadoSigla: 'BBBBBB',
            municipio: 'BBBBBB',
            revenda: 'BBBBBB',
            cnpj: 'BBBBBB',
            produto: 'BBBBBB',
            dataColeta: currentDate.format(DATE_FORMAT),
            valorVenda: 1,
            valorCompra: 1,
            unidade: 'BBBBBB',
            bandeira: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataColeta: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of HistoricoPrecoCombustivel', () => {
        const returnedFromService = Object.assign(
          {
            regiaoSigla: 'BBBBBB',
            estadoSigla: 'BBBBBB',
            municipio: 'BBBBBB',
            revenda: 'BBBBBB',
            cnpj: 'BBBBBB',
            produto: 'BBBBBB',
            dataColeta: currentDate.format(DATE_FORMAT),
            valorVenda: 1,
            valorCompra: 1,
            unidade: 'BBBBBB',
            bandeira: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataColeta: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a HistoricoPrecoCombustivel', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
