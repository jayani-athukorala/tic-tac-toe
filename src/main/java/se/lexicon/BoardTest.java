package se.lexicon;

import se.lexicon.model.GameBoard;
import java.util.Scanner;

public class BoardTest {
    static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isNewGame = true;
        int currentPlayer = 1;
        String[] players = new String[2];

        IO.println("Welcome to toc-tac-toe Game! Let's Start...");
        IO.println("===========================================");
        //Let the user choose game mode
        IO.println("1 -> Two Players");
        IO.println("2 -> Play with Computer");
        IO.print("Choose game mode (1/2) : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
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
            boolean isPlay = true;
            while(isPlay) {
                int rowNumber, columnNumber;
                if(currentPlayer == 2 && choice == 2){
                    IO.println("Player " + players[currentPlayer - 1] + " Its My turn!");
                    model.setPlayer(currentPlayer);
                    int[] move = model.getBestMove(player[currentPlayer-1], player[3-currentPlayer-1]);
                    rowNumber = move[0];
                    columnNumber = move[1];
                }
                else {
                    IO.println("Player " + players[currentPlayer - 1] + " Its Your turn!");
                    rowNumber = getMove("Enter row (0-2) : ", scanner);
                    columnNumber = getMove("Enter column (0-2) : ", scanner);
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
                        isNewGame = askReplay(scanner);
                        currentPlayer = 3 - currentPlayer;
                        break;
                    } else if (step ==9) {
                        IO.println("Game is a draw!");
                        isNewGame = askReplay(scanner);
                        currentPlayer = 3 - currentPlayer;
                        break;
                    }
                }
                currentPlayer = 3 - currentPlayer;
                step++;
            }
        }
    }

    private static int getMove(String prompt, Scanner scanner){
        IO.print(prompt);
        return scanner.nextInt();
    }
    private static boolean askReplay(Scanner scanner){
        IO.print("Do you want to play again (y/n) :");
        String replay = scanner.next();
        if (replay.charAt(0) == 'n') {
            IO.println("Exiting the Game! Come again..");
            IO.println("===========Bye bye============");
            return false;
        }else{
            IO.println("You have started a new game!..");
        }
        return true;
    }
}