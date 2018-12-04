import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AplicacaoSharedModule } from 'app/shared';
import {
    PlacesComponent,
    PlacesDetailComponent,
    PlacesUpdateComponent,
    PlacesDeletePopupComponent,
    PlacesDeleteDialogComponent,
    placesRoute,
    placesPopupRoute
} from './';

const ENTITY_STATES = [...placesRoute, ...placesPopupRoute];

@NgModule({
    imports: [AplicacaoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [PlacesComponent, PlacesDetailComponent, PlacesUpdateComponent, PlacesDeleteDialogComponent, PlacesDeletePopupComponent],
    entryComponents: [PlacesComponent, PlacesUpdateComponent, PlacesDeleteDialogComponent, PlacesDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AplicacaoPlacesModule {}
