package de.SEPL.ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
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

		} catch (IOException e) {
			e.printStackTrace();
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

			tempString = reader.readLine();
			while ((tempString = reader.readLine()) != null) {
				items = items + "-" + tempString;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] itemAr = items.split("-");

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
