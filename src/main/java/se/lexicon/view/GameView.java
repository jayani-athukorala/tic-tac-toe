package se.lexicon.view;

import javax.swing.*;
import java.awt.*;

public class GameView {

    public JFrame frame = new JFrame("Tic Tac Toe");

    public CardLayout cardLayout = new CardLayout();
    public JPanel mainPanel = new JPanel(cardLayout);

    // Start panel
    public JButton vsFriendButton = new JButton("Play vs Friend");
    public JButton vsSystemButton = new JButton("Play vs Computer");

    public JPanel startPanel = new JPanel(new GridLayout(2, 1, 10, 10));

    // Game panel
    public JButton[][] cells = new JButton[3][3];
    public JPanel gamePanel = new JPanel(new GridLayout(3, 3));

    public GameView() {

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- Start panel ---
        startPanel.add(vsFriendButton);
        startPanel.add(vsSystemButton);

        // --- Game panel ---
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 cells[i][j] = new JButton("");
                 gamePanel.add(cells[i][j]);
            }
        }

        // Add panels to main panel
        mainPanel.add(startPanel, "START");
        mainPanel.add(gamePanel, "GAME");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Method to switch to game panel
    public void showGamePanel() {
        cardLayout.show(mainPanel, "GAME");
    }
}
