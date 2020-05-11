package ch.wesr.flowable.springbootdemo;


import ch.wesr.flowable.springbootdemo.processor.dto.AbstractKunde;
import ch.wesr.flowable.springbootdemo.processor.dto.AcsMsKunde;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.test.Deployment;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.wesr.flowable.springbootdemo.processor.dto.AcsMsKunde.VALID_KUNDE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProspectApplicationProcessTest extends SpringBootDemoTestConfiguration{

    public static final String PROSPECT_APPLICATION = "prospectApplication";
    public static final String NOT_FOUND_PROSPECT = "Rene";
    public static final String FOUND_PROSPECT = "Paul";

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;


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
        assertTrue(object instanceof AcsMsKunde);
        AcsMsKunde kunde = (AcsMsKunde) object;
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
        AcsMsKunde manKunde = manuallyRecordedKunde();

        // when
        ProcessInstance prospectApplication = startProcessInstance(NOT_FOUND_PROSPECT);

        // then
        assertThat(prospectApplication.getProcessDefinitionKey(), is(PROSPECT_APPLICATION));
        String processInstanceId = prospectApplication.getProcessInstanceId();
        assertThat(processInstanceId, is(notNullValue()));
        // der User Task muss aufpoppen
        Task task = taskService.createTaskQuery().active().singleResult();
        assertThat("Fill Prospect manually", equalTo(task.getName()));

        // wie kann ich dem Test die User Task Form Variablen übergeben und danach kunde abfragen

        taskService.complete(task.getId());
        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("count: " +count);
        assertThat(0L, equalTo(count));

        Map<String, Object> processVariables = prospectApplication.getProcessVariables();
        Object object = processVariables.get("kunde");
        assertThat(object, is(notNullValue()));
        assertTrue(object instanceof AcsMsKunde);
        AcsMsKunde kunde = (AcsMsKunde) object;
        assertThat(kunde.getVorname(), is(manKunde.getVorname()));
        assertThat(kunde.getNachname(), is(manKunde.getNachname()));
        assertThat(kunde.getStrasse(), is(manKunde.getStrasse()));
        assertThat(kunde.getPlz(), is(manKunde.getPlz()));
        assertThat(kunde.getOrt(), is(manKunde.getOrt()));
    }

    private AcsMsKunde manuallyRecordedKunde() {
        AcsMsKunde acsMsKunde = new AcsMsKunde();
        acsMsKunde.setVorname("René");
        acsMsKunde.setNachname("Arnoux");
        acsMsKunde.setStrasse("Rue de la gar");
        acsMsKunde.setPlz("91788");
        acsMsKunde.setOrt("Paris");
        return acsMsKunde;
    }

    private ProcessInstance startProcessInstance(String notFoundProspect) {
        Map<String, Object> prospectProcessVariables = new HashMap<String, Object>();
        prospectProcessVariables.put("prospectName", notFoundProspect);
        prospectProcessVariables.put("initiator", "flowfest");
        return runtimeService.startProcessInstanceByKey(PROSPECT_APPLICATION, prospectProcessVariables);
    }
}
