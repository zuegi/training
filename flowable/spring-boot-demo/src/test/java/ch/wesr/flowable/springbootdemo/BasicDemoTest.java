package ch.wesr.flowable.springbootdemo;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.idm.api.IdmIdentityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicDemoTest extends SpringBootDemoTestConfiguration {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IdmIdentityService idmIdentityService;

    @Autowired
    private HistoryService historyService;

    @Test
    public void launchProcessDefinition() {
        assertThat(idmIdentityService, is(notNullValue()));
        assertThat(repositoryService, is(notNullValue()));
        assertThat(historyService, is(notNullValue()));
    }
}
