package de.SEPL.ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.mygdx.game.Author;

@Author(name = "Dominikus Häckel")

public class FileClient implements IAuktionshausClient {

	static BufferedReader reader;
	static Socket client;
	static PrintWriter writer;

	public FileClient() {

	}

	public void sendIt(String wrapped) {
		try {
			client = new Socket("localhost", 5554);

			// Streams
			OutputStream out = client.getOutputStream();
			writer = new PrintWriter(out);

			InputStream in = client.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			// -----------

			writer.write(wrapped + "\n");
			writer.flush();

		} catch (ConnectException f) {
			System.out.println("Auktionshaus zur Zeit out of Order.");
		} catch (UnknownHostException g) {
			System.out.println("Adresse des Autkionshauses nicht bekannt.");
		} catch (IOException h) {
			System.out.println("Hier ist wohl ein Fehler aufgelaufen.");
		}
	}

	public void shutDown() {
		sendIt("shutdown\n");
	}

	public String[] getContent() {
		String items = "";

		sendIt("getContent\n");

		// InputStream zeilenweise auslesen
		String tempString = null;
		try {

			while ((tempString = reader.readLine()) != null) {
				items = items + "-" + tempString;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException f) {
			System.out.println("Auktionshaus zur Zeit geschlossen.");
		}

		String[] tempAr = items.split("-");
		String[] itemAr = new String[tempAr.length - 1];
		for (int i = 0; i < itemAr.length; i++){
			itemAr[i] = tempAr[i+1];
		}

		return itemAr;
	}

	public void deleteItem(String name) {
		String wrapped = "deleteItem\n" + name;
		sendIt(wrapped);
	}

	public void pasteItem(String name) {
		String wrapped = "pasteItem\n" + name;
		sendIt(wrapped);
	}

}
