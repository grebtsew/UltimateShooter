package Panels;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Multiplayer.multiplayerClient;
import Start.Window;

public class HostPanel extends JPanel implements ActionListener {

	Window frame;
	
	Button create = new Button("Create");
	
	
	//labels
	JLabel gameName = new JLabel("Enter Game Name");
	JLabel gameStatus = new JLabel("");
	
	multiplayerClient mc;
	
	TextField tf = new TextField();
	
	private static final long serialVersionUID = 1L;

	public HostPanel(Window _frame, multiplayerClient _mc){
		
		mc = _mc;
		
		frame = _frame;
				
		fixpanels();
	
		this.setSize(400,400);
		create.addActionListener(this);
		
	}
	
	private void fixpanels(){
		Panel p = new Panel(new BorderLayout());
		Panel p2 = new Panel(new GridLayout(1,2));
		Panel p3 = new Panel(new GridLayout(2,1));
		p2.add(gameName);
		p2.add(tf);
		p.add(p2, BorderLayout.CENTER);
		p3.add(create);
		p3.add(gameStatus);
		p.add(p3, BorderLayout.SOUTH);
		this.removeAll();
		add(p);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		create.setEnabled(false);
		gameStatus.setText("Waiting for players...");
		//if(gamefull) then start game;
		
		//mc.creategame();
		try {
			new multiplayerClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
