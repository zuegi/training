package ch.wesr.flowable.springbootdemo.processor;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProspectRequestProcessor implements JavaDelegate {


    public static final String PAUL = "Paul";

    @Override
    public void execute(DelegateExecution delegateExecution) {
        String prospectName = delegateExecution.getVariable("prospectName", String.class);
        log.info("Wir suchen nach dem Prospect: {} ", prospectName);
        if (prospectName.equals(PAUL)) {
            log.info("Hallo {}, willkommen an Board", prospectName);
        } else {
            log.info("Tja, {}, wir müssen dich wohl los werden", prospectName);
        }
        delegateExecution.setVariable("root.prospectName", prospectName);
    }
}
