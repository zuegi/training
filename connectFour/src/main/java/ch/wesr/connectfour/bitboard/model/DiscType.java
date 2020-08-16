package ch.wesr.connectfour.bitboard.model;

public enum DiscType {
    X, O;

    public DiscType opponent() {
        return this.equals(DiscType.X) ? DiscType.O : DiscType.X;
    }
}
