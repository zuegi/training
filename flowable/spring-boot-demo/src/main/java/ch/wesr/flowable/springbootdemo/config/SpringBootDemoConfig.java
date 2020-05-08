package ch.wesr.flowable.springbootdemo.config;

import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.Privilege;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SpringBootDemoConfig {

    @Autowired
    private IdmIdentityService idmIdentityService;

    @PostConstruct
    public void createFirstUsers() {

        createUserIfNotExists("flowfest");
        createUserIfNotExists("flowfest-actuator");
        createUserIfNotExists("flowfest-rest");

        if (idmIdentityService.createPrivilegeQuery().privilegeName("ROLE_REST").count() == 0) {
            Privilege restPrivilege = idmIdentityService.createPrivilege("ROLE_REST");
            idmIdentityService.addUserPrivilegeMapping(restPrivilege.getId(), "flowfest-rest");
        }

        if (idmIdentityService.createPrivilegeQuery().privilegeName("ROLE_ACTUATOR").count() == 0) {
            Privilege restPrivilege = idmIdentityService.createPrivilege("ROLE_ACTUATOR");
            idmIdentityService.addUserPrivilegeMapping(restPrivilege.getId(), "flowfest-actuator");
        }

    }

    private void createUserIfNotExists(String username) {
        if (idmIdentityService.createUserQuery().userId(username).count() == 0) {
            User user = idmIdentityService.newUser(username);
            user.setPassword("test");
            idmIdentityService.saveUser(user);
        }
    }
}
