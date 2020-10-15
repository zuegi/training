package ch.wesr.connectfour.bitboard;

import ch.wesr.connectfour.bitboard.model.BitBoard;
import ch.wesr.connectfour.bitboard.model.DiscType;

public class BitBoardHelper {

    private static DiscType o = DiscType.O;
    private static DiscType x = DiscType.X;

    public static final Long COLUMN_0_POSITION_0 = 1L;
    public static final Long COLUMN_0_POSITION_1 = 2L;
    public static final Long COLUMN_0_POSITION_2 = 4L;
    public static final Long COLUMN_0_POSITION_3 = 8L;
    public static final Long COLUMN_0_POSITION_4 = 16L;
    public static final Long COLUMN_0_POSITION_5 = 32L;
    public static final Long COLUMN_0_POSITION_6 = 64L;
    public static final Long COLUMN_1_POSITION_7 = 128L;
    public static final Long COLUMN_1_POSITION_8 = 256L;
    public static final Long COLUMN_1_POSITION_9 = 512L;
    public static final Long COLUMN_1_POSITION_10= 1024L;
    public static final Long COLUMN_1_POSITION_11= 2048L;
    public static final Long COLUMN_1_POSITION_12= 4096L;
    public static final Long COLUMN_1_POSITION_13= 8192L;
    public static final Long COLUMN_2_POSITION_14= 16384L;
    public static final Long COLUMN_2_POSITION_15= 32768L;
    public static final Long COLUMN_2_POSITION_16= 65536L;
    public static final Long COLUMN_2_POSITION_17= 131072L;
    public static final Long COLUMN_2_POSITION_18= 262144L;
    public static final Long COLUMN_2_POSITION_19= 524288L;
    public static final Long COLUMN_2_POSITION_20= 1048576L;
    public static final Long COLUMN_3_POSITION_21= 2097152L;
    public static final Long COLUMN_3_POSITION_22= 4194304L;
    public static final Long COLUMN_3_POSITION_23= 8388608L;
    public static final Long COLUMN_3_POSITION_24= 16777216L;
    public static final Long COLUMN_3_POSITION_25= 33554432L;
    public static final Long COLUMN_3_POSITION_26= 67108864L;
    public static final Long COLUMN_3_POSITION_27= 134217728L;
    public static final Long COLUMN_4_POSITION_28= 268435456L;
    public static final Long COLUMN_4_POSITION_29= 536870912L;
    public static final Long COLUMN_4_POSITION_30= 1073741824L;
    public static final Long COLUMN_4_POSITION_31= 2147483648L;
    public static final Long COLUMN_4_POSITION_32= 4294967296L;
    public static final Long COLUMN_4_POSITION_33= 8589934592L;
    public static final Long COLUMN_4_POSITION_34= 17179869184L;
    public static final Long COLUMN_5_POSITION_35= 34359738368L;
    public static final Long COLUMN_5_POSITION_36= 68719476736L;
    public static final Long COLUMN_5_POSITION_37= 137438953472L;
    public static final Long COLUMN_5_POSITION_38= 274877906944L;
    public static final Long COLUMN_5_POSITION_39= 549755813888L;
    public static final Long COLUMN_5_POSITION_40= 1099511627776L;
    public static final Long COLUMN_5_POSITION_41= 2199023255552L;
    public static final Long COLUMN_6_POSITION_42= 4398046511104L;
    public static final Long COLUMN_6_POSITION_43= 8796093022208L;
    public static final Long COLUMN_6_POSITION_44= 17592186044416L;
    public static final Long COLUMN_6_POSITION_45= 35184372088832L;
    public static final Long COLUMN_6_POSITION_46= 70368744177664L;
    public static final Long COLUMN_6_POSITION_47= 140737488355328L;
    public static final Long COLUMN_6_POSITION_48= 281474976710656L;
    public static final Long POSITION_49= 562949953421312L;




    public static void nextMoveCouldWinOnColumn4(BitBoard bitBoard) {
        // frist row
        bitBoard.makeMove(x, 0);
        bitBoard.makeMove(o, 1);
        bitBoard.makeMove(x, 2);
        bitBoard.makeMove(o, 3);
        bitBoard.makeMove(x, 4);
        bitBoard.makeMove(o, 5);
        bitBoard.makeMove(x, 6);
        // given second row
        bitBoard.makeMove(o, 0);
        bitBoard.makeMove(x, 1);
        bitBoard.makeMove(o, 2);
        bitBoard.makeMove(x, 3);
        bitBoard.makeMove(o, 4);
        bitBoard.makeMove(x, 5);
        bitBoard.makeMove(o, 6);
        // given third row
        bitBoard.makeMove(x, 6);
        bitBoard.makeMove(o, 5);
        bitBoard.makeMove(x, 4);
        bitBoard.makeMove(o, 3);
//        bitBoard.makeMove(x, 2);
//        bitBoard.makeMove(o, 1);
//        bitBoard.makeMove(x, 0);

    }

}
