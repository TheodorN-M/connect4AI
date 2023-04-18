package no.uib.inf101.sem2.controller;

import java.awt.event.MouseEvent;

import javax.swing.Timer;

import java.awt.event.ActionEvent;

import no.uib.inf101.sem2.midi.Song;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.ai.Ai;
import no.uib.inf101.sem2.model.piece.Turn;
import no.uib.inf101.sem2.view.ConnectView;

public class GameController implements java.awt.event.MouseListener {
    private final ControllableModel model;
    private final ConnectView view;
    private Timer timer;
    private final Song song = new Song();

    public GameController(ControllableModel model, ConnectView view) {
        view.setFocusable(true);
        this.model = model;
        this.view = view;
        view.addMouseListener(this);
        this.timer = new Timer(model.dropTimer(), this::clockTick);
        timer.start();
        song.run();

    }

    public void clockTick(ActionEvent event) {
        if (model.getGameState() == GameState.ACTIVE_GAME || model.getGameState() == GameState.AI_ACTIVE) {
            model.clockTick();
            view.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private int getColFromCoordinate(int x) {
        return (x * model.getDimension().cols() / view.getWidth());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if ((model.getTurn() == Turn.RED && model.getGameState() == GameState.AI_ACTIVE) ||
        model.getGameState() == GameState.ACTIVE_GAME){

            model.placePiece(getColFromCoordinate(e.getX()));
            view.repaint();
        }
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
