import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AplicacaoSharedModule } from 'app/shared';
import {
    StatusCandidatesComponent,
    StatusCandidatesDetailComponent,
    StatusCandidatesUpdateComponent,
    StatusCandidatesDeletePopupComponent,
    StatusCandidatesDeleteDialogComponent,
    statusCandidatesRoute,
    statusCandidatesPopupRoute
} from './';

const ENTITY_STATES = [...statusCandidatesRoute, ...statusCandidatesPopupRoute];

@NgModule({
    imports: [AplicacaoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StatusCandidatesComponent,
        StatusCandidatesDetailComponent,
        StatusCandidatesUpdateComponent,
        StatusCandidatesDeleteDialogComponent,
        StatusCandidatesDeletePopupComponent
    ],
    entryComponents: [
        StatusCandidatesComponent,
        StatusCandidatesUpdateComponent,
        StatusCandidatesDeleteDialogComponent,
        StatusCandidatesDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AplicacaoStatusCandidatesModule {}
