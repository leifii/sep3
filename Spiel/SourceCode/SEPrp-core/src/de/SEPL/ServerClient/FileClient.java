package de.SEPL.ServerClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket; 

public class FileClient {

	static BufferedReader reader;
	static Socket client;
	static PrintWriter writer;

	public static void sendIt(String wrapped) {
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

	public static void shutDown() {
		sendIt("shutdown\n");
	}

	public static String[] getContent() {
		String[] items = new String[256];
		int i = 0;

		sendIt("getContent\n");

		// InputStream zeilenweise auslesen
		String tempString = null;
		try {

			while ((tempString = reader.readLine()) != null) {
				items[i] = tempString;
				tempString = reader.readLine();
				i++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		return items;
	}

	public static void deleteItem(String art, String name, double seltenheit, int staerke, int intelligenz, int stamina,
			int geschicklichkeit, int wert) {
		String wrapped = "deleteItem\n" + art + "," + name + "," + seltenheit + "," + staerke + "," + intelligenz + "," + stamina + ","
				+ geschicklichkeit + "," + wert;

		sendIt(wrapped);
	}

	public static void pasteItem(String art, String name, double seltenheit, int staerke, int intelligenz, int stamina,
			int geschicklichkeit, int wert) {

		String wrapped = "pasteItem\n" + art + "," + name + "," + seltenheit + "," + staerke + "," + intelligenz + "," + stamina + ","
				+ geschicklichkeit + "," + wert;

		sendIt(wrapped);
	}

}
