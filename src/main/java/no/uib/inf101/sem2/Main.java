package no.uib.inf101.sem2;

import javax.swing.JFrame;
import no.uib.inf101.sem2.controller.GameController;
import no.uib.inf101.sem2.model.ConnectBoard;
import no.uib.inf101.sem2.model.ConnectModel;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.view.ConnectView;

public class Main {
  public static void main(String[] args) {
    ConnectBoard board = new ConnectBoard(6, 7);
    ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE); // GameState.AI_ACTIVE
    ConnectView view = new ConnectView(model);
    

    new GameController(model, view);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Connect4 INF101");
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
