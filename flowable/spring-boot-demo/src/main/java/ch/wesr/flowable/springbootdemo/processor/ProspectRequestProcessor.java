package ch.wesr.flowable.springbootdemo.processor;

import ch.wesr.flowable.springbootdemo.processor.dto.Prospect;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProspectRequestProcessor implements JavaDelegate {



    /**
     * Das ist ein Request
     * @param delegateExecution
     */
    @Override
    public void execute(DelegateExecution delegateExecution) {
        String prospectName = delegateExecution.getVariable("prospectName", String.class);
        log.info("Wir suchen nach dem Prospect: {} ", prospectName);
        Prospect prospect;
        if (prospectName.equals(Prospect.PAUL)) {
            log.info("Hallo {}, willkommen an Board", prospectName);
            prospect = Prospect.VALID_KUNDE;
        } else {
            log.info("Tja, {}, wir müssen dich wohl manuell erfasse", prospectName);
            prospect = new Prospect();
            prospect.setVorname(prospectName);
        }
        delegateExecution.setVariable("root.prospectName", prospectName);
        delegateExecution.setVariable("kunde", prospect);
    }

}
