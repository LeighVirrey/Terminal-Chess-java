package chess;
import java.util.Scanner;


public class checkMoves{

    static Scanner scanner = new Scanner(System.in);
    static int whiteKingMoved = 0;
    static int blackKingMoved = 0;
    static char promotion = ' ';
    static int[] whiteEnPassant = {' ', ' '};
    static int[] blackEnPassant = {' ', ' '};


    public static boolean temporarMove(int trueCoordinates, int trueMoveCoordinates, char[] board, boolean isWhiteTurn){
        char[] temp = board.clone();

        if((isWhiteTurn == false && temp[trueCoordinates] == 'p') || (isWhiteTurn == true && temp[trueCoordinates] == 'P')){
            if(Pawn(trueCoordinates, trueMoveCoordinates, temp) == true){
                if(temp[trueCoordinates] == 'P' && trueMoveCoordinates <= 7){   //PROMOCJA PIONA
                    System.out.println("Promote to: ");
                    promotion = scanner.next().charAt(0);
                    if(Character.isLowerCase(promotion)){
                        promotion = Character.toUpperCase(promotion);
                    }
                    temp[trueMoveCoordinates] = promotion;
                    temp[trueCoordinates] = ' ';
                    if(!kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn)){
                        board[trueMoveCoordinates] = promotion;
                        board[trueCoordinates] = ' ';
                        clearBlackEnPassant();
                        return true;
                    }
                }else if(temp[trueCoordinates] == 'p' && trueMoveCoordinates >= 56){
                    System.out.println("Promote to: ");
                    promotion = scanner.next().charAt(0);
                    if(Character.isUpperCase(promotion)){
                        promotion = Character.toLowerCase(promotion);
                    }
                    temp[trueMoveCoordinates] = promotion;
                    temp[trueCoordinates] = ' ';
                    if(!kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn)){
                        board[trueMoveCoordinates] = promotion;
                        board[trueCoordinates] = ' ';
                        clearWhiteEnPassant();
                        return true;
                    }
                }else{
                    makeTemporarMove(trueCoordinates, trueMoveCoordinates, temp);
                    if(!kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn)){
                        makeMove(trueCoordinates, trueMoveCoordinates, board, isWhiteTurn);
                        return true;
                    }
                }
                
                }
            }else if((isWhiteTurn == false && temp[trueCoordinates] == 'r') || (isWhiteTurn == true && temp[trueCoordinates] == 'R')){
                if(Vertical(trueCoordinates, trueMoveCoordinates, temp) == true || Horizontal(trueCoordinates, trueMoveCoordinates, temp) == true){
                    makeTemporarMove(trueCoordinates, trueMoveCoordinates, temp);
                    if(!kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn)){
                        makeMove(trueCoordinates, trueMoveCoordinates, board, isWhiteTurn);
                        return true;
                    }
                }
            }else if((isWhiteTurn == false && temp[trueCoordinates] == 'n') || (isWhiteTurn == true && temp[trueCoordinates] == 'N')){
                if(Knight(trueCoordinates, trueMoveCoordinates, temp) == true){
                    makeTemporarMove(trueCoordinates, trueMoveCoordinates, temp);
                    if(!kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn)){
                        makeMove(trueCoordinates, trueMoveCoordinates, board, isWhiteTurn);
                        return true;
                    }
                }
            }else if((isWhiteTurn == false && temp[trueCoordinates] == 'b') || (isWhiteTurn == true && temp[trueCoordinates] == 'B')){
                if(Diagonal(trueCoordinates, trueMoveCoordinates, temp) == true){
                    makeTemporarMove(trueCoordinates, trueMoveCoordinates, temp);
                    if(!kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn)){
                        makeMove(trueCoordinates, trueMoveCoordinates, board, isWhiteTurn);
                        return true;
                    }
                }
            }else if((isWhiteTurn == false && temp[trueCoordinates] == 'q') || (isWhiteTurn == true && temp[trueCoordinates] == 'Q')){
                if(Diagonal(trueCoordinates, trueMoveCoordinates, temp) == true || Vertical(trueCoordinates, trueMoveCoordinates, temp) == true || Horizontal(trueCoordinates, trueMoveCoordinates, temp) == true){
                    makeTemporarMove(trueCoordinates, trueMoveCoordinates, temp);
                    if(!kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn)){
                        makeMove(trueCoordinates, trueMoveCoordinates, board, isWhiteTurn);
                        return true;
                    }
                }
            }else if((isWhiteTurn == false && temp[trueCoordinates] == 'k') || (isWhiteTurn == true && temp[trueCoordinates] == 'K')){
                if(King(trueCoordinates, trueMoveCoordinates, temp) == 1){
                    makeTemporarMove(trueCoordinates, trueMoveCoordinates, temp);
                    if(!kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn)){
                        makeMove(trueCoordinates, trueMoveCoordinates, board, isWhiteTurn);
                        if(board[trueMoveCoordinates] == 'k'){
                            blackKingMoved++;
                        }else if(board[trueMoveCoordinates] == 'K'){
                            whiteKingMoved++;
                        }
                        return true;
                    }
                }else if(King(trueCoordinates, trueMoveCoordinates, board) == 2 && !kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn) && //KROTKA ROSZADA
                       !kingChecked(trueCoordinates+1, trueCoordinates, temp, isWhiteTurn) &&
                       !kingChecked(trueCoordinates+2, trueCoordinates, temp, isWhiteTurn)){
                            temp[trueCoordinates+1] = temp[trueCoordinates+3];
                            temp[trueCoordinates+3] = ' ';
                            temp[trueMoveCoordinates] = temp[trueCoordinates];
                            temp[trueCoordinates] = ' ';
                    if(!kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn) &&
                       !kingChecked(trueCoordinates+1, trueCoordinates, temp, isWhiteTurn) &&
                       !kingChecked(trueCoordinates+2, trueCoordinates, temp, isWhiteTurn)){
                            board[trueCoordinates+1] = board[trueCoordinates+3];
                            board[trueCoordinates+3] = ' ';
                            board[trueMoveCoordinates] = board[trueCoordinates];
                            board[trueCoordinates] = ' ';
                            if(board[trueMoveCoordinates] == 'k'){
                                blackKingMoved++;
                                clearBlackEnPassant();
                            }else if(board[trueMoveCoordinates] == 'K'){
                                whiteKingMoved++;
                                clearWhiteEnPassant();
                            }
                            return true;
                    }
                }else if(King(trueCoordinates, trueMoveCoordinates, board) == 3 && !kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn) && //DLUGA ROSZADA
                       !kingChecked(trueCoordinates-1, trueCoordinates, temp, isWhiteTurn) &&
                       !kingChecked(trueCoordinates-2, trueCoordinates, temp, isWhiteTurn)){
                            temp[trueCoordinates-1] = temp[trueCoordinates-4];
                            temp[trueCoordinates-4] = ' ';
                            temp[trueMoveCoordinates] = temp[trueCoordinates];
                            temp[trueCoordinates] = ' ';
                    if(!kingChecked(trueCoordinates, trueMoveCoordinates, temp, isWhiteTurn) &&
                       !kingChecked(trueCoordinates-1, trueCoordinates, temp, isWhiteTurn) &&
                       !kingChecked(trueCoordinates-2, trueCoordinates, temp, isWhiteTurn)) {
                            board[trueCoordinates-1] = board[trueCoordinates-4];
                            board[trueCoordinates-4] = ' ';
                            board[trueMoveCoordinates] = board[trueCoordinates];
                            board[trueCoordinates] = ' ';
                            if(board[trueMoveCoordinates] == 'k'){
                                blackKingMoved++;
                                clearBlackEnPassant();
                            }else if(board[trueMoveCoordinates] == 'K'){
                                whiteKingMoved++;
                                clearWhiteEnPassant();
                            }
                            return true;
                    }
                }
            }
    
            return false;
        }

        public static void makeMove(int trueCoordinates, int trueMoveCoordinates, char[] board, boolean isWhiteTurn){
            board[trueMoveCoordinates] = board[trueCoordinates];
            board[trueCoordinates] = ' ';
            if(isWhiteTurn == true){
                clearWhiteEnPassant();
            }else{
                clearBlackEnPassant();
            }
        }

        public static void makeTemporarMove(int trueCoordinates, int trueMoveCoordinates, char[] board){
            board[trueMoveCoordinates] = board[trueCoordinates];
            board[trueCoordinates] = ' ';
        }

        public static boolean kingChecked(int trueCoordinates, int trueMoveCoordinates, char[] board, boolean isWhiteTurn){

            int kingPosition = 0;
            for(int i=0; i<64; i++){
                if((isWhiteTurn == true && board[i] == 'K') || (isWhiteTurn == false && board[i] == 'k')){
                    kingPosition = i;
                    break;
                }
            } 

            for(int j=0; j<64; j++){
                if(isWhiteTurn == true){
                    if(board[j] == 'p' && Pawn(j, kingPosition, board) == true){
                        System.out.println("CHECK!");
                        return true;
                    }else if(board[j] == 'r' && (Vertical(j, kingPosition, board) == true || Horizontal(j, kingPosition, board) == true)){
                        System.out.println("CHECK!");
                        return true;
                    }else if(board[j] == 'n' && Knight(j, kingPosition, board) == true){
                        System.out.println("CHECK!");
                        return true;
                    }else if(board[j] == 'b' && Diagonal(j, kingPosition, board) == true){
                        System.out.println("CHECK!");
                        return true;
                    }else if(board[j] == 'q' && (Diagonal(j, kingPosition, board) == true || Vertical(j, kingPosition, board) == true || Horizontal(j, kingPosition, board) == true)){
                        System.out.println("CHECK!");
                        return true;
                    }
                }else{
                    if(board[j] == 'P' && Pawn(j, kingPosition, board) == true){
                        System.out.println("CHECK!");
                        return true;
                    }else if(board[j] == 'R' && (Vertical(j, kingPosition, board) == true || Horizontal(j, kingPosition, board) == true)){
                        System.out.println("CHECK!");
                        return true;
                    }else if(board[j] == 'N' && Knight(j, kingPosition, board) == true){
                        System.out.println("CHECK!");
                        return true;
                    }else if(board[j] == 'B' && Diagonal(j, kingPosition, board) == true){
                        System.out.println("CHECK!");
                        return true;
                    }else if(board[j] == 'Q' && (Diagonal(j, kingPosition, board) == true || Vertical(j, kingPosition, board) == true || Horizontal(j, kingPosition, board) == true)){
                        System.out.println("CHECK!");
                        return true;
                    }
                }
            }
            return false;
        }

    public static boolean Pawn(int trueCoordinates, int trueMoveCoordinates, char[] board){    //SPRAWDZENIE CZY RUCH PIONEM JEST PORPWANY
        if(board[trueCoordinates] == 'p'){
            if(board[trueMoveCoordinates] == ' ' && (trueCoordinates+8 == trueMoveCoordinates)){          
                return true;
            }else if((trueCoordinates >=8 && trueCoordinates <= 15) && (trueCoordinates+16 == trueMoveCoordinates) && board[trueMoveCoordinates] == ' ' && board[trueMoveCoordinates-8] == ' '){
                whiteEnPassant[0] = 1;
                whiteEnPassant[1] = trueMoveCoordinates;
                return true;
            }else if((trueCoordinates+7 == trueMoveCoordinates && board[trueMoveCoordinates] != ' ' && Character.isUpperCase(board[trueMoveCoordinates])) 
                  || (trueCoordinates+9 == trueMoveCoordinates && board[trueMoveCoordinates] != ' ' && Character.isUpperCase(board[trueMoveCoordinates]))){
                return true;
            }else if(blackEnPassant[0] == 1 && trueMoveCoordinates == blackEnPassant[1]+8){
                board[blackEnPassant[1]] = ' ';
                return true;

            }
        }else if(board[trueCoordinates] == 'P'){
            if(board[trueMoveCoordinates] == ' ' && (trueCoordinates-8 == trueMoveCoordinates)){          
                return true;
            }else if((trueCoordinates >=48 && trueCoordinates <= 55) && (trueCoordinates-16 == trueMoveCoordinates) && board[trueMoveCoordinates] == ' ' && board[trueMoveCoordinates+8] == ' '){
                blackEnPassant[0] = 1;
                blackEnPassant[1] = trueMoveCoordinates;
                return true;
            }else if((trueCoordinates-7 == trueMoveCoordinates && board[trueMoveCoordinates] != ' ' && Character.isLowerCase(board[trueMoveCoordinates])) 
                  || (trueCoordinates-9 == trueMoveCoordinates && board[trueMoveCoordinates] != ' ' && Character.isLowerCase(board[trueMoveCoordinates]))){
                return true;     
            }else if(whiteEnPassant[0] == 1 && trueMoveCoordinates == whiteEnPassant[1]-8){
                board[whiteEnPassant[1]] = ' ';
                return true;

            }
        }
        return false;
    }

    public static void clearWhiteEnPassant(){
        whiteEnPassant[0] = 0;
        whiteEnPassant[1] = 0;
    }

    public static void clearBlackEnPassant(){
        blackEnPassant[0] = 0;
        blackEnPassant[1] = 0;
    }
    

    public static boolean Vertical(int trueCoordinates, int trueMoveCoordinates, char[] board){ //SPRAWDZENIE CZY RUCH JEST POPRAWNY W PIONIE
        if (trueCoordinates % 8 == trueMoveCoordinates % 8) {
            if (trueCoordinates < trueMoveCoordinates) {                                         //SPRAWDZENIE CZY NA DRODZE FIGURY NIE MA INNEJ FIGURY
                for (int i = trueCoordinates + 8; i < trueMoveCoordinates; i += 8) {
                    if (board[i] != ' ') {
                        return false;
                    }
                }
            } else if (trueCoordinates > trueMoveCoordinates) {
                for (int i = trueCoordinates - 8; i > trueMoveCoordinates; i -= 8) {
                    if (board[i] != ' ') {
                        return false;
                    }
                }
            }

            if (board[trueMoveCoordinates] == ' ' || Character.isLowerCase(board[trueCoordinates]) != Character.isLowerCase(board[trueMoveCoordinates])) {
                return true;
            }
        }
        return false;
    }

    public static boolean Horizontal(int trueCoordinates, int trueMoveCoordinates, char[] board){ //SPRAWDZENIE CZY RUCH JEST POPRAWNY W POZIOMIE
        if(trueCoordinates / 8 == trueMoveCoordinates / 8){
            if(trueCoordinates < trueMoveCoordinates){                                          //SPRAWDZENIE CZY NA DRODZE FIGURY NIE MA INNEJ FIGURY
                for(int i = trueCoordinates+1; i<trueMoveCoordinates; i++){
                    if(board[i] != ' '){
                        return false;
                    }
                }
            }else if(trueCoordinates > trueMoveCoordinates){
                for(int i = trueCoordinates-1; i>trueMoveCoordinates; i--){
                    if(board[i] != ' '){
                        return false;
                    }
                }
            }

           if(board[trueMoveCoordinates] == ' ' || Character.isLowerCase(board[trueCoordinates]) != Character.isLowerCase(board[trueMoveCoordinates])){
               return true;
           }
        }
        return false;
    }

    public static boolean Diagonal(int trueCoordinates, int trueMoveCoordinates, char[] board) {
        int rowDiff = Math.abs(trueCoordinates / 8 - trueMoveCoordinates / 8);
        int colDiff = Math.abs(trueCoordinates % 8 - trueMoveCoordinates % 8);
    
                                                                                                                  // SPRAWDA CZY RUCH JEST POPRAWNY PO SKOSIE
        if (rowDiff == colDiff) {
            int rowDirection = (trueMoveCoordinates / 8 > trueCoordinates / 8) ? 1 : -1;
            int colDirection = (trueMoveCoordinates % 8 > trueCoordinates % 8) ? 1 : -1;
    
            int currentRow = trueCoordinates / 8 + rowDirection;
            int currentCol = trueCoordinates % 8 + colDirection;
    
                                                                                                                 // PRZEJDZIE PO SKOSIE I SPRAWDZI CZY NA DRODZE NIE MA INNEJ FIGURY
            while (currentRow != trueMoveCoordinates / 8 || currentCol != trueMoveCoordinates % 8) {
                int currentPos = currentRow * 8 + currentCol;
                if (board[currentPos] != ' ') {
                    return false;
                }
                currentRow += rowDirection;
                currentCol += colDirection;
            }
    
                                                                                                                    // POZWOLI NA RUCH JESLI NA KONCU SKOSU JEST PUSTE POLE LUB FIGURA PRZECIWNIKA
            if (board[trueMoveCoordinates] == ' ' || Character.isLowerCase(board[trueCoordinates]) != Character.isLowerCase(board[trueMoveCoordinates])) {
                return true;
            }
        }
        return false;
    
    }

    public static boolean Knight(int trueCoordinates, int trueMoveCoordinates, char[] board){
        int[] possibleMoves = {6, 10, 15, 17, -6, -10, -15, -17};
        int currentRow = trueCoordinates / 8;
        int currentCol = trueCoordinates % 8;
    
        for (int move : possibleMoves) {
            int newCoordinates = trueCoordinates + move;
            int newRow = newCoordinates / 8;
            int newCol = newCoordinates % 8;

            if (newCoordinates >= 0 && newCoordinates < 64) {
                if (Math.abs(currentRow - newRow) == 2 && Math.abs(currentCol - newCol) == 1 ||
                    Math.abs(currentRow - newRow) == 1 && Math.abs(currentCol - newCol) == 2) {
                    if (newCoordinates == trueMoveCoordinates && (board[newCoordinates] == ' ' || Character.isLowerCase(board[trueCoordinates]) != Character.isLowerCase(board[newCoordinates]))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int King(int trueCoordinates, int trueMoveCoordinates, char[] board){
        if(trueCoordinates+1 == trueMoveCoordinates || trueCoordinates-1 == trueMoveCoordinates || trueCoordinates+8 == trueMoveCoordinates || trueCoordinates-8 == trueMoveCoordinates ||
           trueCoordinates+7 == trueMoveCoordinates || trueCoordinates-7 == trueMoveCoordinates || trueCoordinates+9 == trueMoveCoordinates || trueCoordinates-9 == trueMoveCoordinates){
            if(board[trueMoveCoordinates] == ' ' || Character.isLowerCase(board[trueCoordinates]) != Character.isLowerCase(board[trueMoveCoordinates])){
                return 1;
            }
        }else if((board[trueCoordinates] == 'k' && trueCoordinates == 4 && trueMoveCoordinates == 6 && board[5] == ' ' && board[6] == ' ' && board[7] == 'r') && blackKingMoved == 0){
            return 2;
        }else if((board[trueCoordinates] == 'k' && trueCoordinates == 4 && trueMoveCoordinates == 2 && board[3] == ' ' && board[2] == ' ' && board[1] == ' ' && board[0] == 'r') && blackKingMoved == 0){
            return 3;
        }else if((board[trueCoordinates] == 'K' && trueCoordinates == 60 && trueMoveCoordinates == 62 && board[61] == ' ' && board[62] == ' ' && board[63] == 'R') && whiteKingMoved == 0){
            return 2;
        }else if((board[trueCoordinates] == 'K' && trueCoordinates == 60 && trueMoveCoordinates == 58 && board[59] == ' ' && board[58] == ' ' && board[57] == ' ' && board[56] == 'R') && whiteKingMoved == 0){
            return 3;
        }
        return 0;
    }
}
