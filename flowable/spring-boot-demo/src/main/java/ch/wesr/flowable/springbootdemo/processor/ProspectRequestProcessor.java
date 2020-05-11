package ch.wesr.flowable.springbootdemo.processor;

import ch.wesr.flowable.springbootdemo.processor.dto.AcsMsKunde;
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
        AcsMsKunde acsMsKunde;
        if (prospectName.equals(PAUL)) {
            log.info("Hallo {}, willkommen an Board", prospectName);
            acsMsKunde = this.erstelleKunde(prospectName);
        } else {
            log.info("Tja, {}, wir müssen dich wohl los werden", prospectName);
            acsMsKunde = new AcsMsKunde();
        }
        delegateExecution.setVariable("root.prospectName", prospectName);
        delegateExecution.setVariable("kunde", acsMsKunde);
    }


    private AcsMsKunde erstelleKunde(String prospectName) {
        AcsMsKunde acsMsKunde = new AcsMsKunde();
        acsMsKunde.setVorname(prospectName);
        acsMsKunde.setNachname("Allen");
        acsMsKunde.setOrt("Mountain View");
        acsMsKunde.setPlz("80008");
        acsMsKunde.setStrasse("Palo Alto Strasse");
        return acsMsKunde;
    }
}
