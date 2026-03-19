package com.tictactoe.controller;

import com.tictactoe.model.GameBoard;
import com.tictactoe.view.GameView;

import javax.swing.*;

public class GameController {

    private final GameView view;
    private GameBoard board;

    private String player1; // Human
    private String player2; // Computer

    private char currentPlayer = 'X';
    private char firstPlayer = 'X'; // Track starter

    private boolean vsComputer;

    public GameController(GameView view, GameBoard gameBoard){
        this.view = view;
        this.board = gameBoard;

        initStartButtons();
        initCells();
    }

    private void initStartButtons(){

        // Two players
        view.vsFriendButton.addActionListener(_ -> {
            JTextField name1 = new JTextField();
            JTextField name2 = new JTextField();
            Object[] message = {
                    "Player 1 Name:", name1,
                    "Player 2 Name:", name2
            };
            int option = JOptionPane.showConfirmDialog(view.frame,message,"Enter Player Names",JOptionPane.OK_CANCEL_OPTION);
            if(option == JOptionPane.OK_OPTION){
                player1 = name1.getText().isEmpty()?"Player 1":name1.getText();
                player2 = name2.getText().isEmpty()?"Player 2":name2.getText();
                vsComputer = false;
                firstPlayer = 'X'; // Player1 starts first
                startGame();
            }
        });

        // Play vs Computer
        view.vsSystemButton.addActionListener(_ -> {
            String name = JOptionPane.showInputDialog(view.frame,"Enter your name:","Player Name",JOptionPane.PLAIN_MESSAGE);
            player1 = (name==null || name.isEmpty())?"Player":name;
            player2 = "Computer";
            vsComputer = true;
            firstPlayer = 'X'; // Human always starts first for the first match
            startGame();
        });
    }

    private void startGame(){
        board = new GameBoard();
        view.resetBoard();

        currentPlayer = firstPlayer;
        updateTurnLabel();
        view.showGamePanel();

        // If computer is the starting player, make its first move immediately
        if(vsComputer && currentPlayer == 'O'){
            computerMove();
        }
    }

    private void initCells(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int row = i;
                int col = j;
                view.cells[i][j].addActionListener(_ -> handleMove(row,col));
            }
        }
    }

    private void handleMove(int row,int col){
        if(!view.cells[row][col].getText().isEmpty()) return;

        view.cells[row][col].setText(String.valueOf(currentPlayer));
        board.setValueOnBoard(currentPlayer,row,col);

        int winner = board.isWin();

        if(winner != '-') {
            String name = currentPlayer=='X'?player1:player2;
            switchStarterAfterMatch(); // alternate starter
            endGame(name + " wins!");
            return;
        }

        if(isBoardFull()) {
            switchStarterAfterMatch(); // alternate starter
            endGame("Game Draw!");
            return;
        }

        switchPlayer();
        updateTurnLabel();

        // If vs Computer and it's computer's turn, make move automatically
        if(vsComputer && currentPlayer == 'O'){
            computerMove();
        }
    }

    private void computerMove(){
        int[] move = board.getBestMove('O','X');

        // Delay 2 seconds (2000 ms) before making the move
        new javax.swing.Timer(2000, e -> handleMove(move[0], move[1])).start();
    }

    private void switchPlayer(){
        currentPlayer = currentPlayer=='X'?'O':'X';
    }

    private void updateTurnLabel(){
        String name = currentPlayer=='X'?player1:player2;
        view.turnLabel.setText("Turn: " + name + " ("+currentPlayer+")");
    }

    private boolean isBoardFull(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(view.cells[i][j].getText().isEmpty()) return false;
            }
        }
        return true;
    }

    private void switchStarterAfterMatch(){
        // Alternate starter for next match
        firstPlayer = (firstPlayer=='X')?'O':'X';
    }

    private void endGame(String message){
        int option = JOptionPane.showConfirmDialog(
                view.frame,
                message + "\nContinue?",
                "Game Over",
                JOptionPane.YES_NO_OPTION
        );

        if(option==JOptionPane.YES_OPTION){
            startGame(); // replay with alternating starter
        } else {
            view.showStartPanel();
        }
    }
}