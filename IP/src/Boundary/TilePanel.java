package Boundary;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Entity.Model;
import Entity.MoveCounter;
import Entity.Tile;

public class TilePanel extends JPanel {

	// have to create actual rectangles that represent the tiles and the cursor
	// rectx and recty are temporary because there will be a location attribute in
	// the tile

	// apoligies for the magic numbers

	Model model;

	/**
	 * Create the panel.
	 */
	public TilePanel(Model model) {
		this.model = model;
	}

	@Override
	public void paintComponent(Graphics g) {
		MoveCounter mc = model.getPuzzle().getMoveCounter();
		g.drawString("Moves: " + mc.getNumMoves(), (this.getWidth() / 3) + 10, this.getHeight());

		for (int i = 0; i < model.getPuzzle().getTiles().length; i++) {
			for (int j = 0; j < model.getPuzzle().getTiles()[i].length; j++) {
				Tile tt = model.getPuzzle().getTile(i, j);
				// System.out.println(model.getPuzzle().getTiles().size()); test to make sure
				// that the for loop was called

				// all the magic number variables
				int rectSideLength = 100;
				int startingLocation = 20;
				int rectx = startingLocation * SeparateTiles((tt.getLoc().getCol()));
				int recty = startingLocation * SeparateTiles((tt.getLoc().getRow()));
				int insideRectSideLength = 99;
				int stringPositioningLength = 50;
				int insideRectOffset = 1;
				Color borderColor = Color.GREEN;
				//changing the border color to red if the player has lost
				if(model.getPuzzle().hasLost()) {
					borderColor = Color.RED;
				}

				// this is the main drawing part
				
					if (tt.isSelected()) {
						g.setColor(borderColor);
						g.drawRect(rectx, recty, rectSideLength, rectSideLength);

						if (!tt.isEmpty()) {
							if (!tt.isFlipped()) {
								g.setColor(Color.LIGHT_GRAY);
								g.fillRect(rectx + insideRectOffset, recty + insideRectOffset, insideRectSideLength,
										insideRectSideLength);
								g.setColor(Color.BLACK);
								g.drawString(tt.flipped(), rectx + stringPositioningLength,
										recty + stringPositioningLength);
							} else if (tt.isFlipped()) {
								g.setColor(Color.BLACK);
								g.fillRect(rectx + insideRectOffset, recty + insideRectOffset, insideRectSideLength,
										insideRectSideLength);
								g.setColor(Color.WHITE);
								g.drawString(tt.flipped(), rectx + stringPositioningLength,
										recty + stringPositioningLength);
							}
						} else {
							g.setColor(Color.WHITE);
							g.fillRect(rectx + insideRectOffset, recty + insideRectOffset, insideRectSideLength,
									insideRectSideLength);
						}

					} else if (!tt.isSelected()) {
						g.setColor(Color.LIGHT_GRAY);
						g.drawRect(rectx, recty, rectSideLength, rectSideLength);
						if (!tt.isEmpty()) {
							if (!tt.isFlipped()) {
								g.setColor(Color.LIGHT_GRAY);
								g.fillRect(rectx + insideRectOffset, recty + insideRectOffset, insideRectSideLength,
										insideRectSideLength);
								g.setColor(Color.BLACK);
								g.drawString(tt.flipped(), rectx + stringPositioningLength,
										recty + stringPositioningLength);
							} else if (tt.isFlipped()) {
								g.setColor(Color.BLACK);
								g.fillRect(rectx + insideRectOffset, recty + insideRectOffset, insideRectSideLength,
										insideRectSideLength);
								g.setColor(Color.WHITE);
								g.drawString(tt.flipped(), rectx + stringPositioningLength,
										recty + stringPositioningLength);
							}

						} else {
							g.setColor(Color.WHITE);
							g.fillRect(rectx + insideRectOffset, recty + insideRectOffset, insideRectSideLength,
									insideRectSideLength);
						}
					}
					if(model.getPuzzle().hasWon()) {
						g.setColor(Color.BLACK);
						g.drawString("You Won!", 140, 160);
						g.drawString("Press R to Reset.", 125, 175);
					}
					if(model.getPuzzle().hasLost()) {
						//this loop is for visibility
						if(model.getPuzzle().getTile(1, 1).isFlipped()) {
						g.setColor(Color.WHITE);
						}
						else g.setColor(Color.BLACK);
						g.drawString("You Lost!", 145, 155);
						g.drawString("Press R to Reset.", 125, 185);
					}
				}
		}
	}

	// made change here
	public int SeparateTiles(int coordinate) {
		int multipleToSeparateTiles = 6;
		return ((coordinate) * multipleToSeparateTiles);
	}
}
