package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Boundary.PuzzleView;
import Entity.Model;
import Entity.Tile;

public class moveCursorController implements KeyListener {

	Model model;
	PuzzleView pv; 
 
	public moveCursorController(Model model, PuzzleView pv) {
		this.model = model;
		this.pv = pv;
	}

	public void moveCursorLeft() {
		Tile newSelectedTile = model.getPuzzle().getLeftTile();
		Tile oldSelectedTile = model.getPuzzle().getSelectedTile();
//
//		// the new tile will become the Selected tile and the old slelected tile will
//		// become a not selected
		if(!newSelectedTile.equals(oldSelectedTile)) {
		newSelectedTile.changeSelectedState();
		oldSelectedTile.changeSelectedState();
		}
		pv.repaint();
	}

	public void moveCursorRight() {
		Tile newSelectedTile = model.getPuzzle().getRightTile();
		Tile oldSelectedTile = model.getPuzzle().getSelectedTile();
//
//		// the new tile will become the Selected tile and the old slelected tile will
//		// become a not selected
		if(!newSelectedTile.equals(oldSelectedTile)) {
		newSelectedTile.changeSelectedState();
		oldSelectedTile.changeSelectedState();
		}
		pv.repaint();

	}

	public void moveCursorUp() {
		Tile newSelectedTile = model.getPuzzle().getAboveTile();
		Tile oldSelectedTile = model.getPuzzle().getSelectedTile();
//
//		// the new tile will become the Selected tile and the old slelected tile will
//		// become a not selected
		if(!newSelectedTile.equals(oldSelectedTile)) {
		newSelectedTile.changeSelectedState();
		oldSelectedTile.changeSelectedState();
		}
		pv.repaint();
	}

	public void moveCursorDown() {
		Tile newSelectedTile = model.getPuzzle().getBelowTile();
		Tile oldSelectedTile = model.getPuzzle().getSelectedTile();
//
//		// the new tile will become the Selected tile and the old slelected tile will
//		// become a not selected
		if(!newSelectedTile.equals(oldSelectedTile)) {
		newSelectedTile.changeSelectedState();
		oldSelectedTile.changeSelectedState();
		}
		pv.repaint();
	}
	//disable moving the cursor when the player won or lost
	public boolean disabled() {
		return (model.getPuzzle().hasWon() || model.getPuzzle().hasLost());	
	}

	// written so that you can use either WASD or Arrow Keys
	@Override
	public void keyPressed(KeyEvent e) {
		// use this to register the key press
		if(!disabled()) {//if the player has not won or lost yet
	if ((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_A)) {
				moveCursorLeft();
		}
	else if ((e.getKeyCode() == KeyEvent.VK_RIGHT) || (e.getKeyCode() == KeyEvent.VK_D)) {
				moveCursorRight();
		}
	else if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W)) {
				moveCursorUp();
		}
	else if ((e.getKeyCode() == KeyEvent.VK_DOWN) || (e.getKeyCode() == KeyEvent.VK_S)) {
				moveCursorDown();
		}
		}
	//prints to console the location of the current selected tile
	//System.out.println("This is the row of the selected tile: "+ model.getPuzzle().getSelectedTile().getLoc().getRow() + "\n and this is the column of the selected tile: " + model.getPuzzle().getSelectedTile().getLoc().getCol());
	}


	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
