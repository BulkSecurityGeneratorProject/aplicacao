import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AplicacaoSharedModule } from 'app/shared';
import {
    PreferencesComponent,
    PreferencesDetailComponent,
    PreferencesUpdateComponent,
    PreferencesDeletePopupComponent,
    PreferencesDeleteDialogComponent,
    preferencesRoute,
    preferencesPopupRoute
} from './';

const ENTITY_STATES = [...preferencesRoute, ...preferencesPopupRoute];

@NgModule({
    imports: [AplicacaoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PreferencesComponent,
        PreferencesDetailComponent,
        PreferencesUpdateComponent,
        PreferencesDeleteDialogComponent,
        PreferencesDeletePopupComponent
    ],
    entryComponents: [PreferencesComponent, PreferencesUpdateComponent, PreferencesDeleteDialogComponent, PreferencesDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AplicacaoPreferencesModule {}
