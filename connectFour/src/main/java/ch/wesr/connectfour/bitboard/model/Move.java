package ch.wesr.connectfour.bitboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Move {

    private DiscType discType;
    private int column;

}
