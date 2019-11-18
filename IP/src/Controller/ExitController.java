package Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExitController implements WindowListener{
	
	JFrame app;
	
	public ExitController(JFrame app) {
		this.app = app;
	}
	
	public void showExitDialog() {
		int c = JOptionPane.showConfirmDialog(app,  "Are you sure you want to leave?");
		
		if(c == JOptionPane.OK_OPTION) {
			app.setVisible(false);
			app.dispose();
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		showExitDialog();
	}
	
	//below methods do nothing
	@Override
	public void windowActivated(WindowEvent arg0) {
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}