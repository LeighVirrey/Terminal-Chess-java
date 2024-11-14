package chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class chessTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void testChoicesWork(int choices) {
        char[] board;
        int gameMenu = choices;
        if (gameMenu == 1) {
            board = boardRemake.setupStandardBoard();
        } else {
            board = boardRemake.setupChess960Board();
        }
        assertNotNull(board);
    }

    @Test
    void testStandardBoardSetup() {
        char[] board = boardRemake.setupStandardBoard();
        assertEquals(64, board.length);
        assertEquals('r', board[0]);
        assertEquals('R', board[63]);
        assertEquals('k', board[4]);
        assertEquals('K', board[60]);
    }

    @Test
    void testChess960BoardSetup() {
        char[] board = boardRemake.setupChess960Board();
        assertEquals(64, board.length);
        assertTrue(new String(board).contains("b"));
        char[] regularBoard = boardRemake.setupStandardBoard();
        assertNotEquals(new String(regularBoard), new String(board));
    }

    @Test
    void testChess960BoardKingBetweenRooks() {
        char[] board = boardRemake.setupChess960Board();
        assertTrue(new String(board).indexOf("r") < new String(board).indexOf("b") && new String(board).indexOf("k") < new String(board).lastIndexOf("r"));
    }
}