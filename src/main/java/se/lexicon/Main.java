package se.lexicon;

import se.lexicon.model.GameBoard;
import se.lexicon.controller.GameController;
import se.lexicon.view.GameView;

public class Main {
    static void main() {
        GameView view = new GameView();
        GameBoard model = new GameBoard();
        new GameController(view, model);
    }
}
