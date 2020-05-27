package ch.wesr.flowable.springbootdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Slf4j
@Service
public class DeploymentService {

//    @Autowired
//    RepositoryService repositoryService;

    @Autowired
    ResourceLoader resourceLoader;

    public void deploy() throws IOException {
//        Resource resource = resourceLoader.getResource("classpath:assets/OnBoarding.bar");
//        String barFileName = resource.getFilename();
//        log.info("File to be deployed: {}", barFileName);
//        ZipInputStream inputStream = new ZipInputStream(new FileInputStream(resource.getFile()));
//
//        repositoryService.createDeployment()
//                .name("OnBoarding.bar")
//                .addZipInputStream(inputStream)
//                .deploy();


//        final InputStream inputStream = new ClassPathResource("/assets/OnBoarding.bar").getInputStream();
//        repositoryService
//                .createDeployment()
//                .addZipInputStream(new ZipInputStream(inputStream))
//                .name("OnBoarding")
//                .deploy();
    }

//    public List<Deployment> list() {
//        List<Deployment> list = repositoryService.createDeploymentQuery().list();
//        return list;
//    }
}
