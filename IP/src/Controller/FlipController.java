package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Boundary.PuzzleView;
import Entity.Model;
import Entity.MoveCounter;
import Entity.Tile;

public class FlipController implements KeyListener {

	Model model;
	PuzzleView pv;

	public FlipController(Model model, PuzzleView pv) {
		this.model = model;
		this.pv = pv;
	}

	public void flip() {
		// for increasing the move counter every time a tile is flipped
		MoveCounter mc = model.getPuzzle().getMoveCounter();

		Tile selectedTile = model.getPuzzle().getSelectedTile();
		Tile emptyTile = model.getPuzzle().getEmptyTile();

		// flip the tile
		if (model.getPuzzle().isMovable()) {
			selectedTile.flip();

			if (model.getPuzzle().isSelectedLeftOfEmpty()) {
				selectedTile.getLoc().shiftRight();
				emptyTile.getLoc().shiftLeft();
				// System.out.println("the selected tile should now be to the right of the empty
				// tile");

				// change the entry in the array
				model.getPuzzle().replaceTile(selectedTile, emptyTile);
			}

			else if (model.getPuzzle().isSelectedRightOfEmpty()) {
				selectedTile.getLoc().shiftLeft();
				emptyTile.getLoc().shiftRight();
				// System.out.println("the selected tile should now be to the left of the empty
				// tile");

				// change the entry in the array
				model.getPuzzle().replaceTile(selectedTile, emptyTile);
			}

			else if (model.getPuzzle().isSelectedAboveEmpty()) {
				selectedTile.getLoc().shiftDown();
				emptyTile.getLoc().shiftUp();
				// System.out.println("the selected tile should now be below of the empty
				// tile");

				// change the entry in the array
				model.getPuzzle().replaceTile(selectedTile, emptyTile);
			}

			else if (model.getPuzzle().isSelectedBelowEmpty()) {
				selectedTile.getLoc().shiftUp();
				emptyTile.getLoc().shiftDown();
				// System.out.println("the selected tile should now be above of the empty
				// tile");

				// change the entry in the array
				model.getPuzzle().replaceTile(selectedTile, emptyTile);
			}

			model.getPuzzle().replaceTile(selectedTile, emptyTile);
			// if the player has not lost or won yet
			if (!Lost() && !Won()) {
				selectedTile.changeSelectedState();// makes selected unselected
				emptyTile.changeSelectedState();// makes empty selected
			}
			// end criteria conditional
			else {
				// change the selected tile to the empty tile
				if (Won()) {
					model.getPuzzle().Won();
					model.getPuzzle().stopGame();
				} else if (Lost()) {
					model.getPuzzle().Lost();
					model.getPuzzle().stopGame();
				}
			}
			// System.out.println("Row of Selected Tile: "+ selectedTile.getLoc().getRow() +
			// "\n Col of Selected Tile: "+ selectedTile.getLoc().getCol());

			mc.IncrementMoveCounter();
		} else {
			// do nothing
		}
		// REFRESH
		pv.repaint();
	}

	public boolean Lost() {
		boolean rt = false;
		// if there are 4 of any one number visible then that is a loss
		// if there are 4 ones
		// if tiles 1,2 are not flipped and tiles 5,6 are flipped
		if ((!model.getPuzzle().getTileByKey(1).isFlipped() && !model.getPuzzle().getTileByKey(2).isFlipped())
				&& (model.getPuzzle().getTileByKey(5).isFlipped() && model.getPuzzle().getTileByKey(6).isFlipped())) {
			//this is for making all the tiles selected so that they all have red borders
			//for graphics
			for(int i = 1; i < 10; i++) {
					if (!model.getPuzzle().getTileByKey(i).isSelected()) {
						model.getPuzzle().getTileByKey(i).changeSelectedState();
				}
				rt = true;
			}
		}
		// if there are 4 fours
		//if 1,2 are flipped and 5,6 are not flipped
		if ((model.getPuzzle().getTileByKey(1).isFlipped() && model.getPuzzle().getTileByKey(2).isFlipped())
				&& (!model.getPuzzle().getTileByKey(5).isFlipped() && !model.getPuzzle().getTileByKey(6).isFlipped())) {
			//this is for making all the tiles selected so that they all have red borders
			//for graphics
			for(int i = 1; i < 10; i++) {
					if (!model.getPuzzle().getTileByKey(i).isSelected()) {
						model.getPuzzle().getTileByKey(i).changeSelectedState();
				}
				rt = true;
			}
		}
		if ((!model.getPuzzle().getTileByKey(4).isFlipped() && !model.getPuzzle().getTileByKey(8).isFlipped())
				&& (model.getPuzzle().getTileByKey(3).isFlipped() && model.getPuzzle().getTileByKey(9).isFlipped())) {
			//this is for making all the tiles selected so that they all have red borders
			//for graphics
			for(int i = 1; i < 10; i++) {
					if (!model.getPuzzle().getTileByKey(i).isSelected()) {
						model.getPuzzle().getTileByKey(i).changeSelectedState();
				}
				rt = true;
			}
		}
		if ((model.getPuzzle().getTileByKey(4).isFlipped() && model.getPuzzle().getTileByKey(8).isFlipped())
				&& (!model.getPuzzle().getTileByKey(3).isFlipped() && !model.getPuzzle().getTileByKey(9).isFlipped())) {
			//this is for making all the tiles selected so that they all have red borders
			//for graphics
			for(int i = 1; i < 10; i++) {
					if (!model.getPuzzle().getTileByKey(i).isSelected()) {
						model.getPuzzle().getTileByKey(i).changeSelectedState();
				}
				rt = true;
			}
		}
		return rt;
	}

	public boolean Won() {
		// for tile in top left corner
		// correct tile must be grey and showing
		//then check every tile from there
		if (!model.getPuzzle().getTile(0, 0).isFlipped() && model.getPuzzle().getTile(0, 0).flipped().equals("1")) {
			if (!model.getPuzzle().getTile(0, 1).isFlipped() && model.getPuzzle().getTile(0, 1).flipped().equals("2")) {
				if (!model.getPuzzle().getTile(0, 2).isFlipped()
						&& model.getPuzzle().getTile(0, 2).flipped().equals("3")) {
					if (model.getPuzzle().getTile(1, 0).isFlipped()
							&& model.getPuzzle().getTile(1, 0).flipped().equals("4")) {
						if (model.getPuzzle().getTile(1, 1).isEmpty()) {
							if (!model.getPuzzle().getTile(1, 2).isFlipped()
									&& model.getPuzzle().getTile(1, 2).flipped().equals("4")) {
								if (model.getPuzzle().getTile(2, 0).isFlipped()
										&& model.getPuzzle().getTile(2, 0).flipped().equals("3")) {
									if (model.getPuzzle().getTile(2, 1).isFlipped()
											&& model.getPuzzle().getTile(2, 1).flipped().equals("2")) {
										if (model.getPuzzle().getTile(2, 2).isFlipped()
												&& model.getPuzzle().getTile(2, 2).flipped().equals("1")) {
											for (int i = 0; i < model.getPuzzle().getTiles().length; i++) {
												for (int j = 0; j < model.getPuzzle().getTiles()[i].length; j++) {
													if (!model.getPuzzle().getTile(i, j).isSelected()) {
														model.getPuzzle().getTile(i, j).changeSelectedState();
													}
												}
											}
											return true;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			flip();
		}
	}
	// ignore below
	// not used
	@Override
	public void keyReleased(KeyEvent arg0) {
		// do nothing
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// do nothing
	}
}
