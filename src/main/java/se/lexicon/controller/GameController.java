package se.lexicon.controller;

import se.lexicon.model.GameBoard;
import se.lexicon.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {

    private final GameView view;
    int partnerType;

    public GameController(GameView view, GameBoard model){
        this.view = view;

        view.vsFriendButton.addActionListener(this);
        view.vsSystemButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == view.vsFriendButton){
            partnerType = 1;
            view.showGamePanel();
        }

        if(e.getSource() == view.vsSystemButton){
            partnerType = 2;
            view.showGamePanel();
        }

    }
}
