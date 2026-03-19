package com.tictactoe.view;

import javax.swing.*;
import java.awt.*;

public class GameView {

    public JFrame frame = new JFrame("Tic Tac Toe");

    public CardLayout cardLayout = new CardLayout();
    public JPanel mainPanel = new JPanel(cardLayout);

    // Start panel
    public JButton vsFriendButton = new JButton("Play vs Friend");
    public JButton vsSystemButton = new JButton("Play vs Computer");
    public JPanel startPanel = new JPanel();
    public JPanel startButtonPanel = new JPanel();

    // Game panel
    public JButton[][] cells = new JButton[3][3];
    public JPanel gamePanel = new JPanel(new BorderLayout());
    public JPanel boardPanel = new JPanel(new GridLayout(3,3,5,5));
    public JLabel turnLabel = new JLabel("Turn", SwingConstants.CENTER);

    public GameView() {

        frame.setSize(400,450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen

        // --- Start Panel ---
        startPanel.setLayout(new BorderLayout());
        startButtonPanel.setLayout(new BoxLayout(startButtonPanel, BoxLayout.Y_AXIS));

        vsFriendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        vsSystemButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        vsFriendButton.setMaximumSize(new Dimension(200,50));
        vsSystemButton.setMaximumSize(new Dimension(200,50));

        vsFriendButton.setFont(new Font("Arial", Font.BOLD, 18));
        vsSystemButton.setFont(new Font("Arial", Font.BOLD, 18));

        startButtonPanel.add(Box.createVerticalGlue());
        startButtonPanel.add(vsFriendButton);
        startButtonPanel.add(Box.createRigidArea(new Dimension(0,20)));
        startButtonPanel.add(vsSystemButton);
        startButtonPanel.add(Box.createVerticalGlue());

        startPanel.add(startButtonPanel, BorderLayout.CENTER);

        // --- Board Panel ---
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                cells[i][j] = new JButton("");
                cells[i][j].setFont(new Font("Arial",Font.BOLD,40));
                boardPanel.add(cells[i][j]);
            }
        }

        turnLabel.setFont(new Font("Arial", Font.BOLD, 24));
        turnLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gamePanel.add(turnLabel, BorderLayout.NORTH);
        gamePanel.add(boardPanel, BorderLayout.CENTER);

        mainPanel.add(startPanel,"START");
        mainPanel.add(gamePanel,"GAME");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void showGamePanel(){
        cardLayout.show(mainPanel,"GAME");
    }

    public void showStartPanel(){
        cardLayout.show(mainPanel,"START");
    }

    public void resetBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                cells[i][j].setText("");
            }
        }
    }
}