package Panels;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import Enums.MenyStatus;
import Start.Window;

public class MainPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Window frame;
	Button back = new Button("Back");

	// frame makepanel
	JPanel menypanel = new JPanel(new BorderLayout());
	JPanel flowpanel = new JPanel(new GridLayout(7, 1));
	Button exit = new Button("Exit");
	Button play = new Button("Play");
	Button edit = new Button("Editor");
	Button credit = new Button("Credits");
	Button multi = new Button("Multiplayer");
	Button highscore = new Button("Highscore");
	Label title = new Label("Ultimate Shooter");
	Dimension d = new Dimension(400, 400);

	public MainPanel(Window _frame) {

		frame = _frame;

		makeMenyPanel();

		this.add(menypanel);

		setVisible(true);

		highscore.addActionListener(this);
		credit.addActionListener(this);
		back.addActionListener(this);
		play.addActionListener(this);
		edit.addActionListener(this);
		exit.addActionListener(this);
		multi.addActionListener(this);
	}

	private void makeMenyPanel() {

		flowpanel.add(title);
		flowpanel.add(play);
		flowpanel.add(edit);
		flowpanel.add(multi);
		flowpanel.add(highscore);
		flowpanel.add(credit);
		flowpanel.add(exit);

		menypanel.add(flowpanel, BorderLayout.CENTER);
	}

	public Dimension getSize(){
		return d;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		switch (e.getActionCommand()) {
		case "Exit":
			System.exit(0);
			break;
		case "Play":
				frame.changePanel(MenyStatus.SETTINGPANEL);	
			break;
		case "Editor":
			frame.changePanel(MenyStatus.EDITORPANEL);
			break;
		case "Highscore":
			frame.changePanel(MenyStatus.HIGHSCOREPANEL);
			break;
		case "Multiplayer":
			frame.changePanel(MenyStatus.MULTIPLAYERPANEL);
			break;
		case "Credits":
			frame.changePanel(MenyStatus.CREDITPANEL);
			break;
		}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}