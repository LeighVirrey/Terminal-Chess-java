package chess;

import java.util.Scanner;

public class chess {
     public static void main(String[] args){
         Scanner scanner = new Scanner(System.in);
         char[] board;
         boolean isGameEnded = false;
         boolean isWhiteTurn = true;
         System.out.println("Choose game mode: 1 for Standard, 2 for Chess960");
         int gameMode = scanner.nextInt();
         if(gameMode == 1){
             board = boardRemake.setupStandardBoard();
         } else {
             board = boardRemake.setupChess960Board();
         }
      while(isGameEnded == false){
        if(isWhiteTurn == true){
            System.out.println("White's turn");
            game.displayBoard(board);
            game.takeCoordinates(board, isWhiteTurn);
            isWhiteTurn = false;
        }else{
            System.out.println("Black's turn");
            game.displayBoard(board);
            game.takeCoordinates(board, isWhiteTurn);
            isWhiteTurn = true;
        }

      }
    }
}
