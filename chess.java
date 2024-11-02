package chess;

public class chess {
     public static void main(String[] args){

      char[] board = {'r','n','b','q','k','b','n','r',
                      'p','p','p','p','p','p','p','p',
                      ' ',' ',' ',' ',' ',' ',' ',' ',
                      ' ',' ',' ',' ',' ',' ',' ',' ',
                      ' ',' ',' ',' ',' ',' ',' ',' ',
                      ' ',' ',' ',' ',' ',' ',' ',' ',
                      'P','P','P','P','P','P','P','P',
                      'R','N','B','Q','K','B','N','R'};

      boolean isGameEnded = false;
      boolean isWhiteTurn = true;

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
