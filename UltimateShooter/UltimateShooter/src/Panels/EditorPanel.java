package Panels;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Editor.Editor;
import Enums.BallTypes;
import Enums.Balls;
import Enums.MenyStatus;
import Start.Window;

public class EditorPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// EditorPanel
	JPanel solvepanel = new JPanel(new GridLayout(1, 4));
	JPanel mainpanel = new JPanel(new BorderLayout());
	JPanel eastpanel = new JPanel(new GridLayout(9, 1));
	JPanel editorpanel = new JPanel();
	JPanel autopanel = new JPanel(new GridLayout(1, 5));

	Label autosolve = new Label("AutoSolve:");
	JCheckBox autosolvecb = new JCheckBox();

	Editor editor = new Editor(this);

	Label asave = new Label("AutoSave");

	boolean saveallmaps = false;

	Button open = new Button("Open");
	Button clear = new Button("Clear");
	Button save = new Button("Save");
	Button solve = new Button("Solve");
	Button autocreate = new Button("AutoCreate");
	Button meny = new Button("Meny");
	Button removeall = new Button("Remove maps");

	Dimension d = new Dimension(715, 400);

	JCheckBox jcb = new JCheckBox();

	TextField tf = new TextField();
	TextField tf2 = new TextField();

	Label l = new Label("untested", Label.CENTER);
	int level = 0;
	public boolean solveable = false;

	JComboBox<Balls> cb = new JComboBox<Balls>(new DefaultComboBoxModel<>(
			Balls.values()));

	JComboBox<BallTypes> cb2 = new JComboBox<BallTypes>(
			new DefaultComboBoxModel<>(BallTypes.values()));

	Window frame;
	Button back = new Button("Back");

	public EditorPanel(Window _frame) {

		frame = _frame;

		// set up editorpanel
		makeEditorPanel();

		setLayout(new BorderLayout());
		add(editor, BorderLayout.CENTER);
		add(editorpanel, BorderLayout.EAST);

		autosolvecb.addActionListener(this);
		open.addActionListener(this);
		jcb.addActionListener(this);
		removeall.addActionListener(this);
		meny.addActionListener(this);
		autocreate.addActionListener(this);
		clear.addActionListener(this);
		cb.addActionListener(this);
		save.addActionListener(this);
		solve.addActionListener(this);
		back.addActionListener(this);

	}

	public boolean getAutoSolve() {
		return autosolvecb.isSelected();
	}

	public Dimension getSize() {
		return d;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jcb) {
			if (jcb.isSelected()) {
				saveallmaps = true;
			} else {
				saveallmaps = false;
			}

		} else if (e.getSource() == autosolvecb) {

			if (autosolvecb.isSelected()) {
				editor.autosolve = true;
			} else {
				editor.autosolve = false;
			}
		}

		switch (e.getActionCommand()) {

		case "Open":
			editor.open();
			break;
		case "Remove maps":
			editor.removeMaps();
			break;
		case "Meny":
			try {
				frame.changePanel(MenyStatus.MAINPANEL);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Save":
			editor.save();
			break;
		case "Solve":
			solveable = editor.solve();
			this.changelabel();
			break;
		case "Exit":
			System.exit(0);
			break;
		case "Play":
			break;
		case "comboBoxChanged":
			editor.setfocusball((Balls) cb.getSelectedItem());
			break;
		case "Clear":
			editor.clear();
			break;
		case "AutoCreate":
			if (isInteger(tf.getText()) && isInteger(tf2.getText())) {
				if (cb2.getSelectedItem() == BallTypes.FANTASY) {
					editor.fantasy = true;
				} else {
					editor.fantasy = false;
				}
				editor.autocreate(Integer.parseInt((tf.getText())),
						Integer.parseInt(tf2.getText()), saveallmaps);
			}
			break;
		}
	}

	private void makeEditorPanel() {

		tf.setText("Amount");
		tf2.setText("Level");
		editor.focusedball = (Balls) cb.getSelectedItem();

		solvepanel.add(autosolve);
		solvepanel.add(autosolvecb);
		solvepanel.add(solve);
		solvepanel.add(l);

		autopanel.add(asave);
		autopanel.add(jcb);
		autopanel.add(tf);
		autopanel.add(autocreate);
		autopanel.add(tf2);

		eastpanel.add(open);
		eastpanel.add(clear);
		eastpanel.add(save);
		eastpanel.add(cb);
		eastpanel.add(cb2);
		eastpanel.add(solvepanel);
		eastpanel.add(autopanel);
		eastpanel.add(removeall);
		eastpanel.add(meny);

		mainpanel.add(editor, BorderLayout.CENTER);
		mainpanel.add(eastpanel, BorderLayout.EAST);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Editor");
		eastpanel.setBorder(title);

		editorpanel.add(mainpanel);

	}

	public void changelabel() {
		if (solveable) {
			l.setBackground(Color.GREEN);
			l.setText("Solveable");

		} else {
			l.setBackground(Color.RED);
			l.setText("UnSolveable");

		}
	}

	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.out.println("no integer");
			return false;

		}
		// only got here if we didn't return false
		return true;
	}

}
