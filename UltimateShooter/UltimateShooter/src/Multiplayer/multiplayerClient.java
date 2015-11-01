package Multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class multiplayerClient implements Runnable {

	BufferedReader in;
	PrintWriter out;

	public multiplayerClient() throws UnknownHostException, IOException {

		// port 9001
		// ChatClient client = new ChatClient();
		// client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// client.frame.setVisible(true);
		// client.run();
		try {
			Socket s = new Socket("127.0.0.1", 9001);
		} catch (ConnectException e) {
			System.out.println("no connection");
		}
	}

	public void creategame(String gamename){
		// send g + gamename
		// send p + playername
		// send w and w8
	}
	
	public String getGames(){
		return "hej";
	}
	
	/**
	 * Prompt for and return the address of the server.
	 */
	private String getServerAddress() {
		return "127.0.0.1";
	}

	/**
	 * Prompt for and return the desired screen name.
	 */
	private String getName() {
		return "name";
	}

	/**
	 * Connects to the server then enters the processing loop.
	 */
	@Override
	public void run() {
		// Make connection and initialize streams
		String serverAddress = getServerAddress();
		Socket socket;
		try {
			socket = new Socket(serverAddress, 9001);

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			// Process all messages from server, according to the protocol.
			while (true) {
				String line = in.readLine();

			}

		} catch (UnknownHostException e) {

			
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void test() throws UnknownHostException, IOException {
		String serverAddress = getServerAddress();

		Socket socket;
		try {
			socket = new Socket(serverAddress, 9001);

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			// Process all messages from server, according to the protocol.
			out.println("test");
			System.out.println("kommer hit");
		} catch (ConnectException e) {
			System.out.println("no connection");

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
