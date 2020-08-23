import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { HistoricoPrecoCombustivelDetailComponent } from 'app/entities/historico-preco-combustivel/historico-preco-combustivel-detail.component';
import { HistoricoPrecoCombustivel } from 'app/shared/model/historico-preco-combustivel.model';

describe('Component Tests', () => {
  describe('HistoricoPrecoCombustivel Management Detail Component', () => {
    let comp: HistoricoPrecoCombustivelDetailComponent;
    let fixture: ComponentFixture<HistoricoPrecoCombustivelDetailComponent>;
    const route = ({ data: of({ historicoPrecoCombustivel: new HistoricoPrecoCombustivel(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [HistoricoPrecoCombustivelDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(HistoricoPrecoCombustivelDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HistoricoPrecoCombustivelDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load historicoPrecoCombustivel on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.historicoPrecoCombustivel).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
