package ch.wesr.training.kata;

public class Main {

    public static final String STAEDELI_LOEREN_MUEHLE = "Städeli, Lören, Mühle";

    public static void main(String[] args) {
        System.out.println(STAEDELI_LOEREN_MUEHLE);

        java.io.PrintStream ps = new java.io.PrintStream(System.out);
        ps.println(STAEDELI_LOEREN_MUEHLE);
    }
}
