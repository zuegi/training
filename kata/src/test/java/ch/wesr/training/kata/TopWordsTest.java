package ch.wesr.training.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TopWordsTest {

    public static final String STRING = "adevx'qps urluu hrgzlue_cmuebxst dhadp " +
            "nhfzrpod mbyatjmlod uqcyzpf adevx'qps " +
            "fd'asphk:fd'asphk mbyatjmlod/dhadp fmmuwrddox?mbyatjmlod-adevx'qps.klkyzz,ferwnalet fd'asphk " +
            "cmuebxst?wfonsvt;cmuebxst:klkyzz:adevx'qps dhadp urluu wfonsvt mbyatjmlod,rytagtxjcg ekmhskvye " +
            "nhfzrpod_mmfevcca;adevx'qps ferwnalet fd'asphk cmuebxst!adevx'qps ifqepm-ferwnalet rytagtxjcg-hrgzlue " +
            "klkyzz_klkyzz nhfzrpod uqcyzpf_cmuebxst:cmuebxst ferwnalet:adevx'qps,wfonsvt uqcyzpf pkbtozt.uqcyzpf/cmuebxst " +
            "nhfzrpod mbyatjmlod wfonsvt-fmmuwrddox hrgzlue;mmfevcca mmfevcca:cmuebxst/uqcyzpf-klkyzz:uqcyzpf-wfonsvt.ferwnalet;cmuebxst:ekmhskvye.mbyatjmlod?fd'asphk " +
            "c'alvdaeyp!uqcyzpf.dhadp ekmhskvye dhadp uqcyzpf klkyzz mbyatjmlod;mbyatjmlod dhadp mmfevcca:klkyzz c'alvdaeyp " +
            "adevx'qps ferwnalet_cmuebxst/mmfevcca mmfevcca klkyzz dhadp c'alvdaeyp,ekmhskvye dhadp ekmhskvye:c'alvdaeyp!ferwnalet.cmuebxst;adevx'qps " +
            "rytagtxjcg adevx'qps mmfevcca uqcyzpf/hrgzlue!fd'asphk hrgzlue ferwnalet_nhfzrpod hrgzlue.klkyzz.cmuebxst " +
            "adevx'qps klkyzz.nhfzrpod;fd'asphk adevx'qps fmmuwrddox mbyatjmlod:rytagtxjcg dhadp:hrgzlue?fd'asphk?c'alvdaeyp " +
            "fmmuwrddox_adevx'qps-mbyatjmlod nhfzrpod fmmuwrddox.fd'asphk;mbyatjmlod?uqcyzpf fd'asphk.cmuebxst;fmmuwrddox-mbyatjmlod!uqcyzpf " +
            "nhfzrpod nhfzrpod wfonsvt-klkyzz wfonsvt nhfzrpod mmfevcca,hrgzlue;fd'asphk?mbyatjmlod,nhfzrpod/uqcyzpf?ferwnalet " +
            "fmmuwrddox wfonsvt fmmuwrddox ekmhskvye/c'alvdaeyp ekmhskvye nhfzrpod_ferwnalet adevx'qps fmmuwrddox ekmhskvye klkyzz " +
            "mbyatjmlod adevx'qps fd'asphk.fymof fymof adevx'qps fd'asphk.rytagtxjcg_uqcyzpf adevx'qps_nhfzrpod:dhadp_uqcyzpf hrgzlue;fd'asphk " +
            "adevx'qps cmuebxst mmfevcca fd'asphk-klkyzz_urluu,ferwnalet pkbtozt:dhadp ekmhskvye nhfzrpod adevx'qps_fmmuwrddox-c'alvdaeyp!c'alvdaeyp " +
            "fmmuwrddox/adevx'qps dhadp fd'asphk,hrgzlue ekmhskvye hrgzlue fd'asphk urluu,uqcyzpf cmuebxst ekmhskvye.fd'asphk " +
            "rytagtxjcg/fd'asphk_mbyatjmlod dhadp:fmmuwrddox pkbtozt adevx'qps!nhfzrpod-hrgzlue ekmhskvye.cmuebxst nhfzrpod c'alvdaeyp " +
            "adevx'qps/ekmhskvye ferwnalet nhfzrpod mbyatjmlod:klkyzz:mbyatjmlod-mmfevcca!fd'asphk nhfzrpod wfonsvt,klkyzz " +
            "urluu,mbyatjmlod?ekmhskvye-adevx'qps mbyatjmlod adevx'qps adevx'qps:nhfzrpod_ferwnalet fmmuwrddox_cmuebxst dhadp " +
            "nhfzrpod wfonsvt,hrgzlue?nhfzrpod fmmuwrddox mbyatjmlod mmfevcca c'alvdaeyp ferwnalet wfonsvt;ekmhskvye-mmfevcca " +
            "mbyatjmlod;fmmuwrddox adevx'qps fd'asphk hrgzlue rytagtxjcg mmfevcca mmfevcca!adevx'qps ekmhskvye!mmfevcca/adevx'qps,cmuebxst " +
            "nhfzrpod klkyzz nhfzrpod cmuebxst ekmhskvye.fd'asphk uqcyzpf.mmfevcca,klkyzz uqcyzpf:c'alvdaeyp!adevx'qps:ifqepm hrgzlue:mmfevcca " +
            "nhfzrpod_nhfzrpod-c'alvdaeyp ";

    @Test
    void testPattern() {
        Pattern.compile("[a-zA-Z]+[\'[a-zA-Z]]?").splitAsStream("  //wont won't won't ").forEach(System.out::println);
    }

    @Test
    void test() {
        Pattern.compile("(\\s)").splitAsStream("  //wont won't won't ").forEach(System.out::println);
    }

    @Test
    void test2() {

        Pattern.compile("(\\s)").splitAsStream("  //wont won't won't ").forEach(System.out::println);
    }

    @Test
    public void sampleTests() {
        assertEquals(Arrays.asList("e", "d", "a"),    TopWords.top3("a a a  b  c c  d d d d  e e e e e"));
        assertEquals(Arrays.asList("e", "ddd", "aa"), TopWords.top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"));
        assertEquals(Arrays.asList("won't", "wont"),  TopWords.top3("  //wont won't won't "));
//        assertEquals(Arrays.asList("won't", "wont", "dont"),  TopWords.top3("  //wont won't won't dont' "));
        assertEquals(Arrays.asList("e"),              TopWords.top3("  , e   .. "));
        assertEquals(Arrays.asList(),                 TopWords.top3("  ...  "));
        assertEquals(Arrays.asList(),                 TopWords.top3("  '  "));
        assertEquals(Arrays.asList(),                 TopWords.top3("  '''  "));
        assertEquals(Arrays.asList("a", "of", "on"),  TopWords.top3(Stream
                .of("In a village of La Mancha, the name of which I have no desire to call to",
                        "mind, there lived not long since one of those gentlemen that keep a lance",
                        "in the lance-rack, an old buckler, a lean hack, and a greyhound for",
                        "coursing. An olla of rather more beef than mutton, a salad on most",
                        "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra",
                        "on Sundays, made away with three-quarters of his income.")
                .collect(Collectors.joining("\n")) ));

        assertEquals(Arrays.asList("ohqxo", "aibctpqeia", "vzho"), TopWords.top3("AIBCtpQEiA ohqXO vZHo"));
        assertEquals(Arrays.asList("advhwp", "tzaffgz", "rbqia"), TopWords.top3("tzaffgz, rbqia advhwp, "));

    }

    @Test
    void remove() {
        String regex = "[^a-zA-Z\\d\\s]'";
        Stream.of(STRING.split(regex))
                .forEach(System.out::println);
    }

    @Test
    void bigOne() {
        assertEquals(Arrays.asList("adevx'qps", "nhfzrpod", "fd'asphk"), TopWords.top3(STRING));
    }
}
