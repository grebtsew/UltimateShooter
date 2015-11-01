package Panels;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;

import Enums.BallTypes;
import Enums.GameMode;
import Enums.MenyStatus;
import Start.Window;

public class SettingsPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Window frame;
	JPanel settingpan = new JPanel();
	JPanel gridpanel = new JPanel(new GridLayout(12, 1));
	JPanel gridpanel2 = new JPanel(new GridLayout(2, 2));
	Label gamemode = new Label("GameMode:");
	Label balltype = new Label("Balltypes:");
	Label title = new Label("Settings");
	Button back = new Button("Back");
	Button start = new Button("Start");
	Label level = new Label("Level");
	JSlider l = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
	JSlider t = new JSlider(JSlider.HORIZONTAL, 30, 1000, 250);
	Label time = new Label("Time : (if timer mode / seconds)");
	Dimension d = new Dimension(400, 650);

	JComboBox<GameMode> cb = new JComboBox<GameMode>(
			new DefaultComboBoxModel<>(GameMode.values()));

	JComboBox<BallTypes> cb2 = new JComboBox<BallTypes>(
			new DefaultComboBoxModel<>(BallTypes.values()));

	Label cb33 = new Label("Sound (on/off):");
	Label cb44 = new Label("Music (on/off):");
	JCheckBox cb3 = new JCheckBox();
	JCheckBox cb4 = new JCheckBox();

	public SettingsPanel(Window _frame) {

		frame = _frame;

		makeSettingPanel();

		add(settingpan);

		cb.addActionListener(this);
		start.addActionListener(this);
		back.addActionListener(this);
	}

	public Dimension getSize() {
		return d;
	}

	private void makeSettingPanel() {
		cb.removeItem(GameMode.SOLVER);

		gridpanel2.add(cb33);
		gridpanel2.add(cb3);
		gridpanel2.add(cb44);
		gridpanel2.add(cb4);

		l.setMajorTickSpacing(9);
		l.setMinorTickSpacing(1);
		l.setPaintTicks(true);
		l.setPaintLabels(true);

		t.setMajorTickSpacing(250);
		t.setMinorTickSpacing(50);
		t.setPaintTicks(true);
		t.setPaintLabels(true);

		settingpan.setLayout(new FlowLayout());
		gridpanel.add(title);
		gridpanel.add(gamemode);
		gridpanel.add(cb);
		gridpanel.add(balltype);
		gridpanel.add(cb2);
		gridpanel.add(level);
		gridpanel.add(l);
		gridpanel.add(time);
		gridpanel.add(t);
		gridpanel.add(gridpanel2);
		gridpanel.add(back);
		gridpanel.add(start);
		settingpan.add(gridpanel);

	}
	
	public String getBallType() {

		return cb2.getSelectedItem().toString();
	}

	public String getGameMode() {

		return cb.getSelectedItem().toString();
	}

	public int getTime() {
		return t.getValue();
	}

	public int getLevel() {
		return l.getValue();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Back":
			try {
				frame.changePanel(MenyStatus.MAINPANEL);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Start":
			frame.level = l.getValue();
			frame.time = t.getValue();
			try {
				frame.changePanel(MenyStatus.GAMEPANEL);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}
	}
}
