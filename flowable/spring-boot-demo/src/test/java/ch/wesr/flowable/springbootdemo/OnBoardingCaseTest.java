package ch.wesr.flowable.springbootdemo;

import org.flowable.cmmn.api.CmmnRuntimeService;
import org.flowable.cmmn.api.runtime.CaseInstance;
import org.flowable.cmmn.api.runtime.PlanItemInstance;
import org.flowable.cmmn.engine.test.CmmnDeployment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnBoardingCaseTest extends SpringBootDemoTestConfiguration {

    @Autowired
    private CmmnRuntimeService cmmnRuntimeService;

    @Test
    @CmmnDeployment
    public void onBoarding() {
        Map<String, Object> map = new HashMap<>();
        map.put("prospectName", "René");
        map.put("initiator", "flowfest");

        CaseInstance caseInstance = cmmnRuntimeService.createCaseInstanceBuilder()
                .caseDefinitionKey("onBoardingTM")
                .variables(map)
                .start();

        Assertions.assertNotNull(caseInstance);
    }

    private void seeWhatHappens(CaseInstance caseInstance) {

        List<PlanItemInstance> planItemInstances = cmmnRuntimeService.createPlanItemInstanceQuery()
                .caseInstanceId(caseInstance.getId())
                .orderByName().asc()
                .list();

        for (PlanItemInstance planItemInstance : planItemInstances) {
            System.out.println(planItemInstance.getName());
        }

    }
}
