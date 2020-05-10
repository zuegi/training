package ch.wesr.flowable.springbootdemo.service;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProspectService {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    private static Map<String, Object> prospectProcessVariables;

    @Transactional
    public ProcessInstance startProcess(String name) {
        prospectProcessVariables = new HashMap<String, Object>();
        prospectProcessVariables.put("prospectName", name);
        prospectProcessVariables.put("initiator", "flowfest");
        return runtimeService.startProcessInstanceByKey("prospectApplication", prospectProcessVariables);
    }

    @Transactional
    public List<Task> listTasksFor(String assignee) {
//        return taskService.createTaskQuery().taskUnassigned().list();
//        return taskService.createTaskQuery().taskAssignee(assignee).list();
        return taskService.createTaskQuery().active().list();
    }

    @Transactional
    public List<HistoricActivityInstance> history(String processInstanceID) {
        List<HistoricActivityInstance> activities =
                historyService.createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstanceID)
                        .finished()
                        .orderByHistoricActivityInstanceEndTime().asc()
                        .list();

        for (HistoricActivityInstance activity : activities) {
            System.out.println(activity.getActivityId() + " took "
                    + activity.getDurationInMillis() + " milliseconds");
        }
        return activities;
    }

    public List<Execution> listActiveProcesses() {
        return runtimeService.createExecutionQuery().startedBy("flowfest").list();
    }
}
