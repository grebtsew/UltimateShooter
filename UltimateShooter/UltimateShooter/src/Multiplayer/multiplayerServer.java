package Multiplayer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class multiplayerServer implements ActionListener {

	private static final int PORT = 9001;
	private static final int PORT2 = 9002;

	// for server search, Show all available games to join
	private static ArrayList<String> OpenGames = new ArrayList<String>();

	// started games, player 1 vs player 2
	private static ArrayList<String> player1 = new ArrayList<String>();
	private static ArrayList<String> player2 = new ArrayList<String>();

	JFrame frame = new JFrame("Ultimate Shooter Server");

	Button seegames = new Button("Active Games");
	Button seeopengames = new Button("Hosted Games");

	// labels
	Label player = new Label("Connected Players : ");
	Label status = new Label("Server Status : ");
	Label game = new Label("Active Games : ");
	Label port = new Label("Server Port : ");
	Label ip = new Label("Server Ip : ");
	Label p = new Label("9001");
	Label i = new Label("127.0.0.1");

	private final static String newline = "\n";

	TextArea tf = new TextArea();
	JScrollPane listScroller;

	// ints
	int pint = 0;
	int gint = 0;
	private static int activegames = 0;
	private static int activeplayers = 0;

	Panel centerpanel;
	Panel south = new Panel();

	static boolean sbool = false;

	public multiplayerServer() throws IOException {

		fixframe();

		seegames.addActionListener(this);
		seeopengames.addActionListener(this);

		sbool = true;

		ServerSocket listener = new ServerSocket(PORT);

		tf.append("Server started successfully" + newline);

		updateframe();

		try {
			while (true) {
				new Handler(listener.accept()).start();
				
			}
		} finally {
			listener.close();
		}

	}

	private void updateframe() {
		centerpanel.removeAll();
		centerpanel.add(player);
		centerpanel.add(new Label(Integer.toString(pint)));
		centerpanel.add(game);
		centerpanel.add(new Label(Integer.toString(gint)));
		centerpanel.add(port);
		centerpanel.add(p);
		centerpanel.add(ip);
		centerpanel.add(i);
		centerpanel.add(status);
		Label l;
		if (sbool) {
			l = new Label("Online");
			l.setBackground(Color.GREEN);
			l.setAlignment(Label.CENTER);
			centerpanel.add(l);
		} else {
			l = new Label("Offline");
			l.setBackground(Color.RED);
			l.setAlignment(Label.CENTER);
			centerpanel.add(l);
		}

		listScroller = new JScrollPane(tf);
		tf.setEditable(false);

		south.removeAll();
		south.add(tf);
		south.add(listScroller);

	}

	private void fixframe() {

		fixpanels();

		frame.setLayout(new BorderLayout());
		Panel p = new Panel(new BorderLayout());
		p.add(centerpanel, BorderLayout.CENTER);
		Panel p2 = new Panel(new GridLayout(1, 2));
		p2.add(seegames);
		p2.add(seeopengames);
		p.add(p2, BorderLayout.SOUTH);
		frame.add(p, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		frame.setSize(200, 500);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}

	private void fixpanels() {
		centerpanel = new Panel(new GridLayout(5, 2));
		centerpanel.add(player);
		centerpanel.add(new Label(Integer.toString(pint)));
		centerpanel.add(game);
		centerpanel.add(new Label(Integer.toString(gint)));
		centerpanel.add(port);
		centerpanel.add(p);
		centerpanel.add(ip);
		centerpanel.add(i);

		centerpanel.add(status);
		Label l;
		if (sbool) {
			l = new Label("Online");
			l.setBackground(Color.GREEN);
			l.setAlignment(Label.CENTER);
			centerpanel.add(l);
		} else {
			l = new Label("Offline");
			l.setBackground(Color.RED);
			l.setAlignment(Label.CENTER);
			centerpanel.add(l);
		}

		listScroller = new JScrollPane(tf);

		south.add(tf);
		south.add(listScroller);
	}

	public static void main(String[] args) throws IOException {

		new multiplayerServer();

	}

	private static class Handler extends Thread {
		private int game;
		private String playername;
		private String gamename;
		private int score = 0;

		private Socket socket;
		private BufferedReader in;
		private BufferedWriter out;

		public Handler(Socket socket) {
			this.socket = socket;

			run();
		}

		private boolean waitforplayer(int gamenumber) {
			return player2.size() < gamenumber;
		}

		private void addToGameList() {
			if (player1.size() != player2.size()) {
				player2.add(playername);
				game = player2.size();
				// start game
				// out to clients
			} else {
				player1.add(playername);
				activegames++;
				game = player1.size();
			}
			activeplayers++;

		}

		public void run() {
			try {
			
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				String input;
				String parameter;

				boolean gameover = false;

				// input listener loop!!
				while (!gameover) {
					input = in.readLine();

					parameter = input.substring(0);

					// parameters for actions on server
					// in
					// g = gamename //create game
					// p = playername //join and send name
					// s = +point //cleared map
					// m = -point //skipped map
					// w = wait for players //start created game
					// o = gameover //client done
					// c = close game //client closed
					// out
					// g = gamenames
					// p = playername
					// s = +point 
					// m = -point
					

					switch (parameter) {

					case "o":
						gameover = true;
						break;
					case "g":

						input = input.substring(1, input.length());
						gamename = input;

						synchronized (gamename) {
							if (!OpenGames.contains(playername)) {
								OpenGames.add(gamename);
								// update all clients
							} else {
								// kan va värt o ta bort oanvända spel
								// update all clients
							}
						}
						break;
					case "p":

						input = input.substring(1, input.length());
						playername = input;

						if (playername == null) {
							return;
						}
						synchronized (playername) {
							if (!player1.contains(playername)
									|| !player2.contains(playername)) {
								addToGameList();
								break;
							}
						}
						break;

					case "s":
						score++;
						// update all clients
						break;

					case "m":
						score--;
						// update all clients
						break;

					case "w":
						while (waitforplayer(game)) {
						}
						break;

					case "c":
						gameover = true;

						// remove correct game and players then end loop
						break;

					default:
						break;
					}
				}

			} catch (IOException e) {
				System.out.println(e);
			} finally {

				try {
					in.close();
					out.close();
					socket.close();
				} catch (IOException e) {
				}

			}

		}
	}

	private void updateGameList() {

		if (player1.size() == 0) {
			tf.append("No Active Games" + newline);
			return;
		}

		for (int i = 0; i < player2.size(); i++) {
			tf.append("Game " + (i + 1) + " (" + OpenGames.get(i) + ") " + " "
					+ player1.get(i) + " - " + player2.get(i) + newline);

		}
	}

	private void updateOpenGameList() {
		if (OpenGames.size() == 0) {
			tf.append("No Hosted Games" + newline);
			return;
		}

		int i = 1;
		for (String s : OpenGames) {

			tf.append("Game " + i + " - " + s + newline);
			i++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		switch (arg0.getActionCommand()) {
		case "Active Games":
			updateGameList();
			break;
		case "Hosted Games":
			updateOpenGameList();
			break;
		}

	}

}
