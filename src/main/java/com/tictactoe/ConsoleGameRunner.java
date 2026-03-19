package com.tictactoe;

import com.tictactoe.model.GameBoard;

import java.util.Scanner;

public class ConsoleGameRunner {

    static Scanner scanner = new Scanner(System.in);

    static void main() {

        boolean isNewGame = true;
        int currentPlayer = 1;
        String[] players = new String[2];

        IO.println("Welcome to toc-tac-toe Game! Let's Start...");
        IO.println("===========================================");
        //Let the user choose game mode
        IO.println("1 -> Two Players");
        IO.println("2 -> Play with Computer");
        IO.print("Choose game mode (1/2) : ");
        int choice = validateMode(1);

        int numPlayers = 2;
        char[] player = new char[2];

        while(currentPlayer <= numPlayers){
            if(currentPlayer == 2 && choice == 2){
                players[1] = "System";
                currentPlayer += 1;
                break;
            }
            IO.print("Enter Name of Player " +currentPlayer+" :");
            players[currentPlayer-1] = scanner.nextLine();
            currentPlayer += 1;
        }

        currentPlayer = 1;
        while (isNewGame){
            player[currentPlayer-1] = 'X';
            player[3-currentPlayer-1] = 'O';

            int step = 1;
            GameBoard model = new GameBoard();
//            boolean isPlay = true;
            while(true) {
                int rowNumber, columnNumber;
                if(currentPlayer == 2 && choice == 2){
                    IO.println("Player " + players[currentPlayer - 1] + " Its My turn!");
                    model.setPlayer(currentPlayer);
                    int[] move = model.getBestMove(player[currentPlayer-1], player[3-currentPlayer-1]);
                    rowNumber = move[0];
                    columnNumber = move[1];
                    try {
                        Thread.sleep(2000); // 2000 ms = 2 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace(); // Handle exception if needed
                    }
                }
                else {
                    IO.println("Player " + players[currentPlayer - 1] + " Its Your turn!");
                    rowNumber = getMove("Enter row (0-2) : ");
                    columnNumber = getMove("Enter column (0-2) : ");
                }
                if (model.isFilledPosition(rowNumber, columnNumber)) {
                    IO.println("Position already filled!, Re-enter values!");
                    continue;
                }
                model.setValueOnBoard(player[currentPlayer - 1], rowNumber, columnNumber);
                char[][] board = model.displayBoard();

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        IO.print(" " + board[i][j] + " ");
                    }
                    IO.println();
                }

                if (step >= 5) {
                    int winner = model.isWin();
                    if (winner != '-') {
                        IO.println(players[currentPlayer-1] + " Wins!");
                        isNewGame = askReplay();
                        currentPlayer = 3 - currentPlayer;
                        break;
                    } else if (step ==9) {
                        IO.println("Game is a draw!");
                        isNewGame = askReplay();
                        currentPlayer = 3 - currentPlayer;
                        break;
                    }
                }
                currentPlayer = 3 - currentPlayer;
                step++;
            }
        }
    }

    private static int getMove(String prompt){
        IO.print(prompt);
        return validateMode(0);
    }

    private static boolean askReplay(){
        IO.print("Do you want to play again (y/n) :");
        char isPlay = validateYesNo();
        if (isPlay == 'n') {
            IO.println("Exiting the Game! Come again..");
            IO.println("===========Bye bye============");
            return false;
        }else{
            IO.println("You have started a new game!..");
        }
        return true;
    }

    static int validateMode(int min){
        int option;
        while (true) {
            try {
                option = scanner.nextInt();
                scanner.nextLine();
                if (option < min || option > 2) {
                    throw new IllegalArgumentException();
                }
                return option; // only return if valid
            } catch (Exception e) {
                scanner.nextLine(); // clear invalid input from buffer
                IO.print("Invalid Value! Please enter " + min + " or " + 2 + " : ");
            }
        }
    }

    static char validateYesNo() {
        while (true) {
            String input = scanner.next().trim().toLowerCase();
            if (input.equals("y") || input.equals("n")) {
                return input.charAt(0); // convert valid string to char
            } else {
                IO.print("Invalid! Please enter y or n: ");
            }
        }
    }

}