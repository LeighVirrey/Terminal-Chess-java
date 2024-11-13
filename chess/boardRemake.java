/**
 * @author lvirrey
 * @createdOn 11/12/2024 at 4:47 PM
 * @projectName Terminal-Chess-java
 * @packageName chess;
 */
package chess;

import java.util.Random;

public class boardRemake {

    public static char[] setupStandardBoard() {
        return new char[]{
                'r','n','b','q','k','b','n','r',
                'p','p','p','p','p','p','p','p',
                ' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',
                'P','P','P','P','P','P','P','P',
                'R','N','B','Q','K','B','N','R'
        };
    }

    public static char[] setupChess960Board() {
        //different way of making the board. Char Array of 64, fill that empty.
        char[] board = new char[64];
        for (int i = 0; i < 64; i++) {
            board[i] = ' ';
        }

        Random rand = new Random();
        char[] pieces = {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};
        boolean[] used = new boolean[8];

        // Place bishops on different color squares
        // This ensures the bishops are on opposite colors.
        // This is ensured by making sure the seconds bishops position is on a bound that would be 1+ from the first bishops position. Making either even or odd.
        int b1 = rand.nextInt(4) * 2;
        int b2 = rand.nextInt(4) * 2 + 1;
        board[b1] = 'b';
        board[b2] = 'b';
        used[b1] = true;
        used[b2] = true;

        // Place the rooks and king
        // This follows the rules of castling, where the king needs to be in between the rooks.
        //although I don't really think castling would work as the game logic doesn't account for this kind of setup.
        int r1, r2, k;
        boolean isKingBetweenRooks = false;

        do{
            do{
                r1 = rand.nextInt(8);
            }while(used[r1]);


            do{
                r2 = rand.nextInt(8);
            }while(used[r2]);


            do{
                k = rand.nextInt(8);
            }while(used[k]);


            if((Math.abs(r1 - k) > 1 && Math.abs(r2 - k) > 1) && ((r1 < k && k < r2) || (r2 < k && k < r1))){
                isKingBetweenRooks = true;
                board[k] = 'k';
                used[k] = true;
                board[r2] = 'r';
                used[r2] = true;
                board[r1] = 'r';
                used[r1] = true;
            }
        }while (!isKingBetweenRooks);

        // Place the queen
        int q;
        do {
            q = rand.nextInt(8);
        } while (used[q]);
        board[q] = 'q';
        used[q] = true;

        // Place the knights
        int n1, n2;
        do {
            n1 = rand.nextInt(8);
        } while (used[n1]);
        board[n1] = 'n';
        used[n1] = true;

        do {
            n2 = rand.nextInt(8);
        } while (used[n2]);
        board[n2] = 'n';
        used[n2] = true;

        // Place pawns
        for (int i = 8; i < 16; i++) {
            board[i] = 'p';
        }
        for (int i = 48; i < 56; i++) {
            board[i] = 'P';
        }

        // Mirror the pieces for the white side
        // this is a little backwards since white is supposed to be set up first and black is what mirrors it. Don't care though.
        for (int i = 0; i < 8; i++) {
            board[56 + i] = Character.toUpperCase(board[i]);
        }

        return board;
    }
}
