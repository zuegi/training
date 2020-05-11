package ch.wesr.flowable.springbootdemo.processor.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AcsMsKunde extends AbstractKunde {

    public static final String PAUL = "Paul";
    public static final AcsMsKunde VALID_KUNDE = erstelleKunde(PAUL);

    public static AcsMsKunde erstelleKunde(String prospectName) {
        AcsMsKunde acsMsKunde = new AcsMsKunde();
        acsMsKunde.setVorname(prospectName);
        acsMsKunde.setNachname("Allen");
        acsMsKunde.setOrt("Mountain View");
        acsMsKunde.setPlz("80008");
        acsMsKunde.setStrasse("Palo Alto Strasse");
        return acsMsKunde;
    }
}
