import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { HistoricoPrecoCombustivelUpdateComponent } from 'app/entities/historico-preco-combustivel/historico-preco-combustivel-update.component';
import { HistoricoPrecoCombustivelService } from 'app/entities/historico-preco-combustivel/historico-preco-combustivel.service';
import { HistoricoPrecoCombustivel } from 'app/shared/model/historico-preco-combustivel.model';

describe('Component Tests', () => {
  describe('HistoricoPrecoCombustivel Management Update Component', () => {
    let comp: HistoricoPrecoCombustivelUpdateComponent;
    let fixture: ComponentFixture<HistoricoPrecoCombustivelUpdateComponent>;
    let service: HistoricoPrecoCombustivelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [HistoricoPrecoCombustivelUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(HistoricoPrecoCombustivelUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HistoricoPrecoCombustivelUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HistoricoPrecoCombustivelService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new HistoricoPrecoCombustivel(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new HistoricoPrecoCombustivel();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
