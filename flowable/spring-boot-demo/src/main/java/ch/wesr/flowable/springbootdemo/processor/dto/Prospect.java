package ch.wesr.flowable.springbootdemo.processor.dto;


public class Prospect extends AbstractKunde {

    public static final String PAUL = "Paul";
    public static final Prospect VALID_KUNDE = erstelleKunde(PAUL);

    public static Prospect erstelleKunde(String prospectName) {
        Prospect prospect = new Prospect();
        prospect.setVorname(prospectName);
        prospect.setNachname("Allen");
        prospect.setOrt("Mountain View");
        prospect.setPlz("80008");
        prospect.setStrasse("Palo Alto Strasse");
        return prospect;
    }
}
