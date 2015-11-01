package Start;

import java.awt.Dimension;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import Enums.BallTypes;
import Enums.GameMode;
import Enums.MenyStatus;
import Panels.CreditPanel;
import Panels.EditorPanel;
import Panels.GamePanel;
import Panels.HighscorePanel;
import Panels.HostPanel;
import Panels.MainPanel;
import Panels.MultiplayerPanel;
import Panels.SettingsPanel;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MenyStatus status = MenyStatus.MAINPANEL;
	public int level = 0;
	public int time = 0;
	public GameMode gm = GameMode.NORMAL;
	SettingsPanel setting;
	public BallTypes balltype;

	public Window() {
		// default
		this.setTitle("Ultimate Shooter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainPanel meny = new MainPanel(this);
		add(meny);

		setSize(meny.getSize());
		setVisible(true);

	}

	private void getGameMode(String s) {
		switch (s) {
		case "NORMAL":
			gm = GameMode.NORMAL;
			break;
		case "EDITORTEST":
			gm = GameMode.EDITORTEST;
			break;
		case "TIMER":
			gm = GameMode.TIMER;
			break;
		case "SOLVER":
			gm = GameMode.SOLVER;
		}
	}

	private void getBallType(String s) {

		switch (s) {

		case "CLASSIC":
			balltype = BallTypes.CLASSIC;
			break;
		case "FANTASY":
			balltype = BallTypes.FANTASY;
		}
	}

	public void changePanel(MenyStatus status) throws UnknownHostException,
			IOException {

		Dimension d = new Dimension();
		this.getContentPane().removeAll();

		switch (status) {
		case GAMEPANEL:
			getBallType(setting.getBallType());
			getGameMode(setting.getGameMode());
			GamePanel game = new GamePanel(this);
			add(game);
			d = game.getSize();
			break;

		case EDITORPANEL:
			EditorPanel edit = new EditorPanel(this);
			add(edit);
			d = edit.getSize();
			break;

		case SETTINGPANEL:
			setting = new SettingsPanel(this);
			add(setting);
			d = setting.getSize();
			break;

		case MULTIPLAYERPANEL:
			MultiplayerPanel multi = new MultiplayerPanel(this);
			add(multi);
			d = multi.getSize();
			break;

		case MAINPANEL:
			MainPanel meny = new MainPanel(this);
			add(meny);
			d = meny.getSize();
			break;

		case HIGHSCOREPANEL:
			HighscorePanel hisco = new HighscorePanel(this);
			add(hisco);
			d = hisco.getSize();
			break;

		case CREDITPANEL:
			CreditPanel cred = new CreditPanel(this);
			add(cred);
			d = cred.getSize();
			break;

		case HOSTPANEL:
		//	HostPanel hp = new HostPanel(this);
	//		add(hp);
		//	d = hp.getSize();
			break;

		default:
			break;
		}

		// pack();
		setSize(d);
		this.revalidate();
		this.repaint();
	}

}
