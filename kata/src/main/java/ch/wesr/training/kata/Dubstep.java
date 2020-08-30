package ch.wesr.training.kata;

public class Dubstep {
    public static String SongDecoder (String song)
    {
        return song.replaceAll("(WUB)+", " ").trim();
    }
}
