package Panels;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import Enums.BallTypes;
import Enums.GameMode;
import Enums.MenyStatus;
import Game.Game;
import Start.Window;

public class GamePanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Window frame;

	// GamePanel
	JPanel buttonpanel = new JPanel(new GridLayout(1, 3));
	JPanel gamepanel = new JPanel();
	Game game;
	Button back = new Button("Back");
	Button next = new Button("Next");
	Button meny = new Button("Meny");

	int level = 0;
	int time = 0;
	GameMode gm;
	BallTypes bt;
	Dimension d = new Dimension(317, 410);

	public GamePanel(Window _frame) {

		frame = _frame;

		level = frame.level;
		time = frame.time;
		gm = frame.gm;
		bt = frame.balltype;


		game = new Game(gm, bt, level, time);

		this.setLayout(new BorderLayout());
		makeGamePanel();

		this.add(game, BorderLayout.CENTER);
		this.add(gamepanel, BorderLayout.SOUTH);

		meny.addActionListener(this);
		back.addActionListener(this);
		next.addActionListener(this);
	}

	public Dimension getSize() {
		return d;
	}

	private void makeGamePanel() {
		buttonpanel.add(next);
		buttonpanel.add(back);
		buttonpanel.add(meny);
		gamepanel.add(buttonpanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Back":
			game.back();
			break;
		case "Meny":
			try {
				frame.changePanel(MenyStatus.MAINPANEL);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Next":
			game.next();
			break;
		}
	}

}
