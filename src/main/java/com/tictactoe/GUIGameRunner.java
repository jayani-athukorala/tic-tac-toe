package com.tictactoe;

import com.tictactoe.model.GameBoard;
import com.tictactoe.controller.GameController;
import com.tictactoe.view.GameView;

public class GUIGameRunner {
    static void main() {
        GameView view = new GameView();
        GameBoard model = new GameBoard();
        new GameController(view, model);
    }
}
