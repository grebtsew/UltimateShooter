package Game;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import Enums.GameMode;

public class GameFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Game game;
	Button back = new Button("Back");
	Button next = new Button ("Next");
	Button meny = new Button("Menu");
	public GameFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		 game = new Game(GameMode.TIMER,null,4,60);
		 
		back.addActionListener(this);
		next.addActionListener(this);
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(game, BorderLayout.CENTER);
		
		JPanel p2 = new JPanel(new GridLayout(1,2));
		p2.add(next);
		p2.add(back);
		
		p.add(p2, BorderLayout.SOUTH);
		
		add(p);
		setVisible(true);
		setSize(400,400);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "Back"){
		game.back();
		} else if(arg0.getActionCommand() == "Next" ){
			game.next();
		} else if(arg0.getActionCommand() == "Menu"){
			// return to menu, remember mapnumber
		}
		
	}
	

}
