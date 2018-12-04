import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AplicacaoSharedModule } from 'app/shared';
import {
    OpportunitiesComponent,
    OpportunitiesDetailComponent,
    OpportunitiesUpdateComponent,
    OpportunitiesDeletePopupComponent,
    OpportunitiesDeleteDialogComponent,
    opportunitiesRoute,
    opportunitiesPopupRoute
} from './';

const ENTITY_STATES = [...opportunitiesRoute, ...opportunitiesPopupRoute];

@NgModule({
    imports: [AplicacaoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OpportunitiesComponent,
        OpportunitiesDetailComponent,
        OpportunitiesUpdateComponent,
        OpportunitiesDeleteDialogComponent,
        OpportunitiesDeletePopupComponent
    ],
    entryComponents: [
        OpportunitiesComponent,
        OpportunitiesUpdateComponent,
        OpportunitiesDeleteDialogComponent,
        OpportunitiesDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AplicacaoOpportunitiesModule {}
