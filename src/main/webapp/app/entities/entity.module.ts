import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AplicacaoOpportunitiesModule } from './opportunities/opportunities.module';
import { AplicacaoRequestsModule } from './requests/requests.module';
import { AplicacaoPlacesModule } from './places/places.module';
import { AplicacaoBenefitsModule } from './benefits/benefits.module';
import { AplicacaoCustomersModule } from './customers/customers.module';
import { AplicacaoPortalModule } from './portal/portal.module';
import { AplicacaoTemplatesModule } from './templates/templates.module';
import { AplicacaoResourcesModule } from './resources/resources.module';
import { AplicacaoCandidatesModule } from './candidates/candidates.module';
import { AplicacaoPreferencesModule } from './preferences/preferences.module';
import { AplicacaoStatusCandidatesModule } from './status-candidates/status-candidates.module';
import { AplicacaoResultsModule } from './results/results.module';
import { AplicacaoResultsDetailsModule } from './results-details/results-details.module';
import { AplicacaoMatchingsJobModule } from './matchings-job/matchings-job.module';
import { AplicacaoMatchingsModule } from './matchings/matchings.module';
import { AplicacaoQuestionsModule } from './questions/questions.module';
import { AplicacaoAnswersModule } from './answers/answers.module';
import { AplicacaoCustomizationModule } from './customization/customization.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        AplicacaoOpportunitiesModule,
        AplicacaoRequestsModule,
        AplicacaoPlacesModule,
        AplicacaoBenefitsModule,
        AplicacaoCustomersModule,
        AplicacaoPortalModule,
        AplicacaoTemplatesModule,
        AplicacaoResourcesModule,
        AplicacaoCandidatesModule,
        AplicacaoPreferencesModule,
        AplicacaoStatusCandidatesModule,
        AplicacaoResultsModule,
        AplicacaoResultsDetailsModule,
        AplicacaoMatchingsJobModule,
        AplicacaoMatchingsModule,
        AplicacaoQuestionsModule,
        AplicacaoAnswersModule,
        AplicacaoCustomizationModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AplicacaoEntityModule {}
