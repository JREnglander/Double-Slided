package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Boundary.PuzzleView;
import Entity.Model;

public class ResetController implements KeyListener{
	
	Model m;
	PuzzleView pv;
	
	public ResetController(Model m, PuzzleView pv) {
		this.m = m;
		this.pv = pv;
	}

	public void resetPuzzle() {
		// behind the scenes stuff
		m.getPuzzle().replaceTile(m.getPuzzle().getTile(0, 0), m.getPuzzle().getTileByKey(1));
		m.getPuzzle().getTileByKey(1).resetFlipState();
		
		m.getPuzzle().replaceTile(m.getPuzzle().getTile(0, 1), m.getPuzzle().getTileByKey(2));
		m.getPuzzle().getTileByKey(2).resetFlipState();
		
		m.getPuzzle().replaceTile(m.getPuzzle().getTile(0, 2), m.getPuzzle().getTileByKey(3));
		m.getPuzzle().getTileByKey(3).resetFlipState();
		
		m.getPuzzle().replaceTile(m.getPuzzle().getTile(1, 0), m.getPuzzle().getTileByKey(4));
		m.getPuzzle().getTileByKey(4).resetFlipState();
		
		m.getPuzzle().replaceTile(m.getPuzzle().getTile(1, 1), m.getPuzzle().getTileByKey(5));
		m.getPuzzle().getTileByKey(5).resetFlipState();
		
		m.getPuzzle().replaceTile(m.getPuzzle().getTile(1, 2), m.getPuzzle().getTileByKey(6));
		m.getPuzzle().getTileByKey(6).resetFlipState();
		
		m.getPuzzle().replaceTile(m.getPuzzle().getTile(2, 0), m.getPuzzle().getTileByKey(7));
		m.getPuzzle().getTileByKey(7).resetFlipState();
		
		m.getPuzzle().replaceTile(m.getPuzzle().getTile(2, 1), m.getPuzzle().getTileByKey(8));
		m.getPuzzle().getTileByKey(8).resetFlipState();

		m.getPuzzle().replaceTile(m.getPuzzle().getTile(2, 2), m.getPuzzle().getTileByKey(9));
		m.getPuzzle().getTileByKey(9).resetFlipState();
		
		if(!m.getPuzzle().getTileByKey(7).isEmpty()) {
			m.getPuzzle().getTileByKey(7).makeFull();//make the tile empty again
			}
		
		m.getPuzzle().resetToEndState();//make the win and lose things false again!!
		
		m.getPuzzle().getMoveCounter().resetNumMoves();//puts number of moves back at zero
		//graphics stuff
		pv.repaint();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_R) {
			resetPuzzle();
		}
	}
	//did not use
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}

}
