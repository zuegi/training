package ch.wesr.flowable.springbootdemo;


import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.test.Deployment;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class ProspectApplicationProcessTest extends SpringBootDemoTestConfiguration{

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    private static Map<String, Object> prospectProcessVariables;


    @Test
    @Deployment
    void testSimpleProcess() {
        prospectProcessVariables = new HashMap<String, Object>();
        prospectProcessVariables.put("prospectName", "Paul");
        prospectProcessVariables.put("initiator", "flowfest");
        runtimeService.startProcessInstanceByKey("prospectApplication", prospectProcessVariables);

        Task task = taskService.createTaskQuery().singleResult();
        assertThat("My Task", equalTo(task.getName()));

        taskService.complete(task.getId());
        assertThat(0, equalTo(runtimeService.createProcessInstanceQuery().count()));
    }
}
