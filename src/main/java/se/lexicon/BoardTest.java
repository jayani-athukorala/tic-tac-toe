package se.lexicon;

public class BoardTest {
    static void main(String[] args) {
        char[][] board = new char[3][3];

        //Fill board with empty spaces
        for(int i= 0; i <= 2; i++){
            for (int j = 0; j <= 2; j++) {
                board[i][j] = '-';
            }
        }

        //Print empty board
        for (int i=0; i <=2;i++){
            for (int j=0; j<=2;j++){
                IO.print(" "+ board[i][j]+" ");
            }
            IO.println();
        }
    }
}