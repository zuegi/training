package ch.wesr.flowable.springbootdemo.web;

import ch.wesr.flowable.springbootdemo.service.ProspectService;
import ch.wesr.flowable.springbootdemo.web.ui.TaskRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/prospect")
public class ProspectResource {

    @Autowired
    ProspectService prospectService;

    @GetMapping("/{name}")
    public ResponseEntity<String> prospect(@PathVariable String name) {
        ProcessInstance processInstance = prospectService.startProcess(name);
//        return ResponseEntity.ok(prospectService.startProcess(name));
        return ResponseEntity.ok(processInstance.getId());
    }

    @GetMapping("/process")
    public ResponseEntity<List<Execution>> processList() {
        return ResponseEntity.ok(prospectService.listActiveProcesses());
    }

    @GetMapping("/task/{assignee}")
    public ResponseEntity<List<TaskRepresentation>> taskList(@PathVariable String assignee) {
        log.info("assignee: {}", assignee);
        List<Task> tasks = prospectService.listTasksFor(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return ResponseEntity.ok(dtos);
//        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/history/{processInstanceId}")
    public ResponseEntity<List<HistoricActivityInstance>> history(@PathVariable String processInstanceId) {
        return ResponseEntity.ok(prospectService.history(processInstanceId));
    }





}
