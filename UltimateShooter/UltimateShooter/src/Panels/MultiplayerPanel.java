package Panels;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Enums.MenyStatus;
import Multiplayer.multiplayerClient;
import Start.Window;

public class MultiplayerPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Window frame;
	Button back = new Button("Back");
	Button join = new Button("Join");
	Button create = new Button("Create");
	Button refresh = new Button("Refresh");
	Dimension d = new Dimension(400, 400);
	TextField tf = new TextField(" Write Name");
	JList jl = new JList();
	DefaultListModel listModel = new DefaultListModel();
	JScrollPane listScroller;
	multiplayerClient client;

	public MultiplayerPanel(Window _frame) throws UnknownHostException, IOException {
		client = new multiplayerClient();
		
		setuplist();

		updateJList();

		frame = _frame;

		this.setLayout(new BorderLayout());

		add(tf, BorderLayout.NORTH);
		add(jl, BorderLayout.CENTER);
		add(listScroller, BorderLayout.CENTER);

		Panel p = new Panel();
		p.add(create);
		p.add(join);
		p.add(refresh);
		p.add(back);

		add(p, BorderLayout.SOUTH);

		updateJList();

		create.addActionListener(this);
		join.addActionListener(this);
		refresh.addActionListener(this);
		back.addActionListener(this);
	}

	private void setuplist() {
		jl = new JList(listModel);
		jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jl.setLayoutOrientation(JList.VERTICAL);
		listScroller = new JScrollPane(jl);
		listScroller.setPreferredSize(new Dimension(250, 80));
	}

	public void addtoList(String s) {
		listModel.addElement(s);
	}

	public void removelastfromList() {
		listModel.remove(listModel.size());
	}

	public void removefromList(int i) {
		listModel.remove(i);
	}

	public int getelementnumber(String s) {

		for (int i = 0; i < listModel.size(); i++) {
			if (listModel.getElementAt(i) == s) {
				return i;
			}
		}
		return 0;
	}

	public void updateJList() {
		jl = new JList(listModel);
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
		case "Create":
			// create some kinda hostpanel /password or whatever
			try {
				frame.changePanel(MenyStatus.HOSTPANEL);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Refresh":
			// recreate "ta"
			break;
		case "Join":
			try {
				
				client.test();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// join server and game, save name
			break;
		}
	}

}
