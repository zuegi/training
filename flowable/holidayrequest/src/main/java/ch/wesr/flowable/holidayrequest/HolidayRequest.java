package ch.wesr.flowable.holidayrequest;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HolidayRequest {

    public static final String HOLIDAY_REQUEST_BPMN_20_XML = "holiday-request.bpmn20.xml";
    private static RepositoryService repositoryService;

    public static void main(String[] args) {
        ProcessEngine processEngine = configureProcessEngine();
        Deployment deployment =deploy(processEngine, HOLIDAY_REQUEST_BPMN_20_XML);
        checkProcessDefinition(deployment);

        RuntimeService runtimeService  = processEngine.getRuntimeService();
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("holidayRequest", initializeProcessVariable());
        // hier stehe ich https://flowable.com/open-source/docs/bpmn/ch02-GettingStarted/#sidetrack-transactionality
    }

    private static Map<String, Object> initializeProcessVariable() {
        Scanner scanner= new Scanner(System.in);

        System.out.println("Who are you?");
        String employee = scanner.nextLine();

        System.out.println("How many holidays do you want to request?");
        Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());

        System.out.println("Why do you need them?");
        String description = scanner.nextLine();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", employee);
        variables.put("nrOfHolidays", nrOfHolidays);
        variables.put("description", description);
        return variables;
    }

    private static void checkProcessDefinition(Deployment deployment) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());
    }

    private static ProcessEngine configureProcessEngine() {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        return cfg.buildProcessEngine();
    }

    private static Deployment deploy(ProcessEngine processEngine, String file) {
        repositoryService = processEngine.getRepositoryService();
        return repositoryService.createDeployment()
                .addClasspathResource(file)
                .deploy();
    }
}
