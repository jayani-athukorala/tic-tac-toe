package com.tictactoe.model;

public class GameBoard {

    int currentPlayer;
    public char[][] board = new char[3][3];

    public GameBoard(){
        reset();
    }

    public void reset() {
        board = new char[3][3];
        for (int i=0;i<3;i++)
            for (int j=0;j<3;j++)
                board[i][j] = '-';
    }

    public void setPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isFilledPosition(int rowValue, int columnValue){
        return board[rowValue][columnValue] != '-';
    }

    public void setValueOnBoard(char currentPlayerSymbol, int rowValue, int columnValue){
        board[rowValue][columnValue] = currentPlayerSymbol;
    }

    public int[] getBestMove(char systemSymbol, char playerSymbol){
        int rowValue = 0, columnValue = 0;
        //Check for winning move
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (board[i][j] == '-') {
                    board[i][j] = systemSymbol;
                    if (this.isWin() == systemSymbol){
                        board[i][j] = '-';
                        return new int[]{i,j};
                    }
                    board[i][j] = '-';
                }
            }
        }
        //Block the opponent
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (board[i][j] == '-') {
                    board[i][j] = playerSymbol;
                    if (this.isWin() == playerSymbol){
                        board[i][j] = '-';
                        return new int[]{i,j};
                    }
                    board[i][j] = '-';
                }
            }
        }

        // Take center
        if (board[1][1] == '-') return new int[]{1,1};

        // Take sides
        int[][] sides = {{0,1},{1,0},{1,2},{2,1}};
        for (int[] s : sides) {
            if (board[s[0]][s[1]] == '-') return s;
        }

        // Take corners
        int[][] corners = {{0,0},{0,2},{2,0},{2,2}};
        for (int[] c : corners) {
            if (board[c[0]][c[1]] == '-') return c;
        }

        return new int[]{rowValue, columnValue};
    }

    public char isWin(){

        for(int i=0; i<3; i++){
            //Check rows
            if(board[i][0] != '-' && board[i][0] == board[i][1] && board[i][0] == board[i][2]){
                return board[i][0];
            }
            //Check columns
            else if (board[0][i] != '-' && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return board[0][i];
            }
        }
        //Check diagonals
        if((board[0][0] !='-' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) ||
                (board[0][2] !='-' && board[0][2] == board[1][1] && board[0][2] == board[2][0])){
            return board[1][1];
        }
        return '-';
    }

    //This function will print the current status of the board
    public char[][] displayBoard() {
        return board;
    }
}
