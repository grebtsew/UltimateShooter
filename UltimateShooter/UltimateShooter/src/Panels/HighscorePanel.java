package Panels;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import Enums.MenyStatus;
import HighScore.HSComp;
import Start.Window;

public class HighscorePanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Window frame;

	Button back = new Button("Back");
	Dimension d = new Dimension(400, 400);
	HSComp hsc = new HSComp();
	Button reset = new Button("Reset");
	

	public HighscorePanel(Window _frame) {
		frame = _frame;
		this.setLayout(new BorderLayout());

		this.add(hsc, BorderLayout.CENTER);

		Panel p = new Panel(new GridLayout(1,2));
		p.add(reset);
		p.add(back);
		this.add(p, BorderLayout.SOUTH);

		reset.addActionListener(this);
		back.addActionListener(this);
	}

	public Dimension getSize() {
		return d;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "Back":
			try {
				frame.changePanel(MenyStatus.MAINPANEL);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "Reset":
			hsc.clearhighscore();
			break;
		}

	}

}
