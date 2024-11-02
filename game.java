package PROJEKTY.chess;
import java.util.Scanner;

public class game {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    static Scanner scanner = new Scanner(System.in);

    public static String coordinates = "";
    public static String moveCoordinates = "";

    public static void displayBoard(char[] board) { // WYSWIETLANIE SZACHOWNICY
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            if (i % 8 == 0) {
                System.out.print(ANSI_GREEN + " " + (8 - i / 8) + "  " + ANSI_RESET);
            }
            System.out.print(board[i] + " ");
            if ((i + 1) % 8 == 0) {
                System.out.println();
            }
        }
        System.out.print(ANSI_GREEN + "    a b c d e f g h " + ANSI_RESET);
    }

    public static void takeCoordinates(char[] board, boolean isWhiteTurn) { // POBIERANIE WSPOLRZEDNYCH

        System.out.print("\nPiece coordinates: ");
        coordinates = scanner.nextLine();

        System.out.print("Move coordinates: ");
        moveCoordinates = scanner.nextLine();

        checkCoordinates(coordinates, moveCoordinates, board, isWhiteTurn);

    }

    public static boolean isWhiteTurn(int trueCoordinates, char[] board, boolean isWhiteTurn) {
        if (isWhiteTurn == true && Character.isUpperCase(board[trueCoordinates]) == true) {
            return true;
        } else if (isWhiteTurn == false && Character.isLowerCase(board[trueCoordinates]) == true) {
            return true;
        }
        return false;
    }

    public static void checkCoordinates(String coordinates, String moveCoordinates, char[] board, boolean isWhiteTurn) { // ZMIANA NOTACJI SZACHOWEJ NA INDEKSY TABLICY I SPRAWDZANIE CZYM
        char column = coordinates.charAt(0); // I GDZIE CHCCE SIE PORUSDZYC GRACZ
        int row = coordinates.charAt(1) - '0';
        int trueCoordinates = (column - 33) - 8 * row;

        char moveColumn = moveCoordinates.charAt(0);
        int moveRow = moveCoordinates.charAt(1) - '0';
        int trueMoveCoordinates = (moveColumn - 33) - 8 * moveRow;

        if(checkMoves.temporarMove(trueCoordinates, trueMoveCoordinates, board, isWhiteTurn) == true){
   
        }else{
            System.out.println("Invalid move!");
            takeCoordinates(board, isWhiteTurn);
        }
    }
}