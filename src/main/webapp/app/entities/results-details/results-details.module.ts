import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AplicacaoSharedModule } from 'app/shared';
import {
    ResultsDetailsComponent,
    ResultsDetailsDetailComponent,
    ResultsDetailsUpdateComponent,
    ResultsDetailsDeletePopupComponent,
    ResultsDetailsDeleteDialogComponent,
    resultsDetailsRoute,
    resultsDetailsPopupRoute
} from './';

const ENTITY_STATES = [...resultsDetailsRoute, ...resultsDetailsPopupRoute];

@NgModule({
    imports: [AplicacaoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResultsDetailsComponent,
        ResultsDetailsDetailComponent,
        ResultsDetailsUpdateComponent,
        ResultsDetailsDeleteDialogComponent,
        ResultsDetailsDeletePopupComponent
    ],
    entryComponents: [
        ResultsDetailsComponent,
        ResultsDetailsUpdateComponent,
        ResultsDetailsDeleteDialogComponent,
        ResultsDetailsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AplicacaoResultsDetailsModule {}
