package ch.wesr.flowable.springbootdemo.web;

import ch.wesr.flowable.springbootdemo.service.DeploymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("/api/deployment")
public class DeploymentResource {

    @Autowired
    DeploymentService deploymentService;

    @PostMapping("/deploy")
    public void deployBarFile() throws IOException {
        deploymentService.deploy();
    }

//    @GetMapping("/list")
//    public ResponseEntity<List<Deployment>> listDeployments() {
//        return ResponseEntity.ok(deploymentService.list());
//    }


}
