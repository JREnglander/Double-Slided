package Boundary;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ExitController;
import Controller.FlipController;
import Controller.ResetController;
import Controller.moveCursorController;
import Entity.Model;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;

public class PuzzleView extends JFrame {

	private JPanel contentPane;
	TilePanel panel;

	Model model;// reference to highest entity class

	/**
	 * Create the frame.
	 */
	public PuzzleView(Model m) {
		this.model = m;
		this.setFocusable(true);
		moveCursorController mcc = new moveCursorController(model, this);
		FlipController fc = new FlipController(model, this);
		ResetController rc = new ResetController(model, this);
		ExitController ec = new ExitController(this);
		this.addKeyListener(mcc);
		this.addKeyListener(fc);
		this.addKeyListener(rc);
		this.addWindowListener(ec);
		setTitle("Double-Slided");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 525);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel = new TilePanel(model);
		panel.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(38, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}
