package ch.wesr.flowable.springbootdemo;

import ch.wesr.flowable.springbootdemo.processor.dto.ProspectDto;
import lombok.extern.slf4j.Slf4j;
import org.flowable.cmmn.api.CmmnRuntimeService;
import org.flowable.cmmn.api.runtime.CaseInstance;
import org.flowable.cmmn.api.runtime.PlanItemInstance;
import org.flowable.cmmn.api.runtime.PlanItemInstanceQuery;
import org.flowable.cmmn.engine.test.CmmnDeployment;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class OnBoardingCaseTest extends SpringBootDemoTestConfiguration {

    @Autowired
    private CmmnRuntimeService cmmnRuntimeService;

    @Autowired
    private TaskService taskService;


    @Test
    @CmmnDeployment
    public void onBoardingMitPaulAusAcsMs() {
        // given
        Map<String, Object> map = new HashMap<>();
        map.put("prospectName", "Paul");
        map.put("initiator", "flowfest");

        // then case wird gestartet und die Prospect Application / Prozess sofort ausgeführt
        CaseInstance caseInstance = cmmnRuntimeService.createCaseInstanceBuilder()
                .caseDefinitionKey("onBoardingTM")
                .variables(map)
                .start();

        Assertions.assertNotNull(caseInstance);

        // das verstehe ich schon nicht, die case Variablens sind hier leer
        Map<String, Object> caseVariables = caseInstance.getCaseVariables();
        log.info("caseVariables: {}"); // <- leer
        // also muss man die Variablen so abholen
        Map<String, Object> variables = cmmnRuntimeService.getVariables(caseInstance.getId());
        log.info("caseVariables über cmmnRuntimeService: {}", variables);

        List<Task> list = taskService.createTaskQuery().active().list();
        assertThat(list, hasSize(2));
//        Task fillProspectManuallyTask = list.stream()
//                .filter(task -> task.getName().equals("Fill Prospect manually")).map(task -> task).collect(Collectors.toList()).get(0);
        Task enhanceWithSwiftKeyTask = list.stream()
                .filter(task -> task.getTaskDefinitionKey().equals("enhanceWithSwiftKey")).map(task -> task).collect(Collectors.toList()).get(0);

        Map<String, Object> enhanceWithSwiftTaskVariables = taskService.getVariables(enhanceWithSwiftKeyTask.getId());
        log.info("Enhance Swift Task Variables: {}", enhanceWithSwiftTaskVariables.toString());

        seeWhatHappens(caseInstance);

    }

    private void seeWhatHappens(CaseInstance caseInstance) {

        List<PlanItemInstance> planItemInstances = cmmnRuntimeService.createPlanItemInstanceQuery()
                .caseInstanceId(caseInstance.getId())
                .orderByName().asc()
                .list();

        for (PlanItemInstance planItemInstance : planItemInstances) {
            log.info("name: {}", planItemInstance.getName());
        }

    }
}
