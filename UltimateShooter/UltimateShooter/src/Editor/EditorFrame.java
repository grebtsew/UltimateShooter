/**
 * 
 */
package Editor;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import Enums.Balls;

/**
 * @author daniel for testing only
 */
public class EditorFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Editor editor = new Editor(null);

	Panel p = new Panel(new BorderLayout());
	Panel p2 = new Panel(new GridLayout(5, 1));
	Button clear = new Button("Clear");
	Button save = new Button("Save");
	Button solve = new Button("Solve");
	boolean solveable = false;
	Panel p4 = new Panel(new GridLayout(1, 3));
	Button autocreate = new Button("AutoCreate");
	TextField tf = new TextField();
	TextField tf2 = new TextField();

	Label l = new Label("untested");
	int level = 0;

	JComboBox<Balls> cb = new JComboBox<Balls>(new DefaultComboBoxModel<>(
			Balls.values()));

	public EditorFrame() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		editor.focusedball = (Balls) cb.getSelectedItem();

		p.add(editor, BorderLayout.CENTER);

		Panel p3 = new Panel(new GridLayout(1, 2));
		p3.add(solve);
		p3.add(l);

		p4.add(tf);
		p4.add(autocreate);
		p4.add(tf2);

		p2.add(clear);
		p2.add(save);
		p2.add(p3);
		p2.add(cb);
		p2.add(p4);

		p.add(p2, BorderLayout.EAST);

		this.add(p);

		autocreate.addActionListener(this);
		clear.addActionListener(this);
		cb.addActionListener(this);
		save.addActionListener(this);
		solve.addActionListener(this);

		setVisible(true);
		setSize(400, 400);

	}

	private void changelabel() {
		if (solveable) {
			l.setText("Solveable");
		} else {
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == "Save") {
			editor.save();
		}
		if (arg0.getActionCommand() == "Solve") {
			solveable = editor.solve();
			this.changelabel();
		}
		if (arg0.getSource() == cb) {
			editor.setfocusball((Balls) cb.getSelectedItem());
		}
		if (arg0.getSource() == clear) {
			editor.clear();
		}
		if (arg0.getSource() == autocreate) {
			if (isInteger(tf.getText()) && isInteger(tf2.getText())) {

				editor.autocreate(Integer.parseInt((tf.getText())),
						Integer.parseInt(tf2.getText()), false);
			}
		}
	}

}
