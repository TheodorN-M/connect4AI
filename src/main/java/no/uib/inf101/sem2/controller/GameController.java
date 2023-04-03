package no.uib.inf101.sem2.controller;

import java.awt.event.MouseEvent;

import javax.swing.Timer;

import java.awt.event.ActionEvent;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.view.ConnectView;



public class GameController implements java.awt.event.MouseListener {
    private final ControllableModel model;
    private final ConnectView view;
    private Timer timer;


    public GameController(ControllableModel model, ConnectView view){
        view.setFocusable(true);
        this.model = model;
        this.view = view;
        view.addMouseListener(this);
        this.timer = new Timer(model.dropTimer(), this::clockTick);
        timer.start();


    }

    

    public void clockTick(ActionEvent event){
        if (model.getGameState() == GameState.ACTIVE_GAME){
            model.clockTick();
            view.repaint();
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        model.placePiece(getColFromCoordinate(e.getX()));
        view.repaint();

    }

    private int getColFromCoordinate(int x){
        int col = (x * model.getDimension().cols() / view.getWidth());
        return col;

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }



    @Override
    public void mouseReleased(MouseEvent e) {
    }



    @Override
    public void mouseEntered(MouseEvent e) {
    }



    @Override
    public void mouseExited(MouseEvent e) {
    }

}
