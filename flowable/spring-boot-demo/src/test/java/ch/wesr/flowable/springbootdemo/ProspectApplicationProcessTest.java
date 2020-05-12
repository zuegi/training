package ch.wesr.flowable.springbootdemo;


import ch.wesr.flowable.springbootdemo.processor.dto.Prospect;
import org.flowable.engine.FormService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.test.Deployment;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static ch.wesr.flowable.springbootdemo.processor.dto.Prospect.VALID_KUNDE;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProspectApplicationProcessTest extends SpringBootDemoTestConfiguration{

    public static final String PROSPECT_APPLICATION = "prospectApplication";
    public static final String NOT_FOUND_PROSPECT = "René";
    public static final String FOUND_PROSPECT = "Paul";

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private FormService formService;


    @Test
    @Deployment
    void testProcessWithProspectFoundInUmsystem() {
        // given the Map<String, Object> prospectProcessVariables =

        // when
        ProcessInstance prospectApplication = startProcessInstance(FOUND_PROSPECT);

        // then
        assertThat(prospectApplication.getProcessDefinitionKey(), is(PROSPECT_APPLICATION));

        String processInstanceId = prospectApplication.getProcessInstanceId();
        assertThat(processInstanceId, is(notNullValue()));
        // es gibt keinen Task mehr - der Prozess ist abgeschlossen
        Task task = taskService.createTaskQuery().active().singleResult();
        assertThat(task, is(nullValue()));

        // was mich aber viel mehr wunder nimmt, sind die Variablen aus AcsMsKunde
        Map<String, Object> processVariables = prospectApplication.getProcessVariables();
        Object object = processVariables.get("kunde");
        assertThat(object, is(notNullValue()));
        assertTrue(object instanceof Prospect);
        Prospect kunde = (Prospect) object;
        assertThat(kunde.getVorname(), is(VALID_KUNDE.getVorname()));
        assertThat(kunde.getNachname(), is(VALID_KUNDE.getNachname()));
        assertThat(kunde.getStrasse(), is(VALID_KUNDE.getStrasse()));
        assertThat(kunde.getPlz(), is(VALID_KUNDE.getPlz()));
        assertThat(kunde.getOrt(), is(VALID_KUNDE.getOrt()));

        /*
        // und zum Schluss noch die History
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .finished()
                .orderByHistoricActivityInstanceEndTime().asc()
                .list();
        assertThat(list, is(notNullValue()));
        */
    }

    @Test
    @Deployment
    public void testProcessWithProspectNotFoundInUmsystem() {
        // given Map<String, Object> prospectProcessVariables
        Prospect manKunde = manuallyRecordedKunde();

        Map<String, Object> mapManuellerKunde = new HashMap<>();
        mapManuellerKunde.put("kunde", manKunde);

        // when
        ProcessInstance prospectApplication = startProcessInstance(NOT_FOUND_PROSPECT);

        // then
        assertThat(prospectApplication.getProcessDefinitionKey(), is(PROSPECT_APPLICATION));
        String processInstanceId = prospectApplication.getProcessInstanceId();
        assertThat(processInstanceId, is(notNullValue()));
        // der User Task muss aufpoppen
        Task task = taskService.createTaskQuery().active().singleResult();
        assertThat(1L, is(runtimeService.createProcessInstanceQuery().count()));
        assertThat("Fill Prospect manually", equalTo(task.getName()));

        Object object = taskService.getVariable(task.getId(), "kunde");
        assertThat(object, is(notNullValue()));
        assertTrue(object instanceof Prospect);
        Prospect kunde = (Prospect) object;
        kunde.setNachname(manKunde.getNachname());
        kunde.setStrasse(manKunde.getStrasse());
        kunde.setPlz(manKunde.getPlz());
        kunde.setOrt(manKunde.getOrt());

        Map<String, Object> mapKunde = new HashMap<>();
        mapKunde.put("kunde", kunde);

        taskService.setVariables(task.getId(),mapKunde);
        taskService.complete(task.getId());
        // no other tasks are to do
        assertThat(0L, equalTo(runtimeService.createProcessInstanceQuery().count()));

        Map<String, Object> processVariables = prospectApplication.getProcessVariables();
        Object objectKunde = processVariables.get("kunde");
        assertThat(objectKunde, is(notNullValue()));
        assertTrue(objectKunde instanceof Prospect);
        Prospect acsKunde = (Prospect) object;
        assertThat(acsKunde.getVorname(), is(manKunde.getVorname()));
        assertThat(acsKunde.getNachname(), is(manKunde.getNachname()));
        assertThat(acsKunde.getStrasse(), is(manKunde.getStrasse()));
        assertThat(acsKunde.getPlz(), is(manKunde.getPlz()));
        assertThat(acsKunde.getOrt(), is(manKunde.getOrt()));
    }

    private Prospect manuallyRecordedKunde() {
        Prospect prospect = new Prospect();
        prospect.setVorname("René");
        prospect.setNachname("Arnoux");
        prospect.setStrasse("Rue de la gar");
        prospect.setPlz("91788");
        prospect.setOrt("Paris");
        return prospect;
    }

    private ProcessInstance startProcessInstance(String notFoundProspect) {
        Map<String, Object> prospectProcessVariables = new HashMap<String, Object>();
        prospectProcessVariables.put("prospectName", notFoundProspect);
        prospectProcessVariables.put("initiator", "flowfest");
        return runtimeService.startProcessInstanceByKey(PROSPECT_APPLICATION, prospectProcessVariables);
    }
}
