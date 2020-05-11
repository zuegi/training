package ch.wesr.flowable.springbootdemo.processor;

import ch.wesr.flowable.springbootdemo.processor.dto.AcsMsKunde;
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
        AcsMsKunde acsMsKunde;
        if (prospectName.equals(AcsMsKunde.PAUL)) {
            log.info("Hallo {}, willkommen an Board", prospectName);
            acsMsKunde = AcsMsKunde.VALID_KUNDE;
        } else {
            log.info("Tja, {}, wir müssen dich wohl manuell erfasse", prospectName);
            acsMsKunde = new AcsMsKunde();
            acsMsKunde.setVorname(prospectName);
        }
        delegateExecution.setVariable("root.prospectName", prospectName);
        delegateExecution.setVariable("kunde", acsMsKunde);
    }

}
