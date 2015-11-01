package Panels;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import Enums.MenyStatus;
import Start.Window;

public class CreditPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button back = new Button("Back");
	Label l = new Label("Creator : Daniel Westberg");
	Dimension d = new Dimension(400,400);
Window frame;
	
	public CreditPanel(Window _frame) {
		frame = _frame;
		
		this.add(l);
		this.add(back);
		
		
		back.addActionListener(this);
	}
	public Dimension getSize(){
		return d;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()){
		case "Back": 
			try {
				frame.changePanel(MenyStatus.MAINPANEL);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		}
		
	}

}
