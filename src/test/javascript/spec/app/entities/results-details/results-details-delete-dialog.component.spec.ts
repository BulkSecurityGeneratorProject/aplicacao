/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AplicacaoTestModule } from '../../../test.module';
import { ResultsDetailsDeleteDialogComponent } from 'app/entities/results-details/results-details-delete-dialog.component';
import { ResultsDetailsService } from 'app/entities/results-details/results-details.service';

describe('Component Tests', () => {
    describe('ResultsDetails Management Delete Component', () => {
        let comp: ResultsDetailsDeleteDialogComponent;
        let fixture: ComponentFixture<ResultsDetailsDeleteDialogComponent>;
        let service: ResultsDetailsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AplicacaoTestModule],
                declarations: [ResultsDetailsDeleteDialogComponent]
            })
                .overrideTemplate(ResultsDetailsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ResultsDetailsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResultsDetailsService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
