package de.SEPL.ServerClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.mygdx.game.Author;

@Author(name = "Dominikus Häckel")


/* Dies ist der Server für das Auktionhaus. Er muss serperat gestartet werden,
 * und das bevor das Spiel gestartet wird.
 * Wird der Server im späteren Verlauf des Projekts auf einen zweiten PC ausgelagert,
 * muss die IP-Adresse entsprechend im Clienten angepasst werden.*/

public class FileServer {

	public static void main(String[] args) {

		try (ServerSocket server = new ServerSocket(5554);) {

			System.out.println("Server gestartet.");

			while (true) {

				// Server in accept setzen
				System.out.println("Warten auf Kontaktversuch des Clienten.");
				Socket client = server.accept();

				// Streams des Clienten öffnen
				InputStream in = client.getInputStream();
				BufferedReader inputstreamBuffer = new BufferedReader(new InputStreamReader(in));

				// command ist String der ersten Zeile des übertragenen Streams
				// und enthält Anweisung, was zu tun ist
				String command = inputstreamBuffer.readLine();
				switch (command) {
				case ("getContent"):
					sendContent(client);
					break;
				case ("deleteItem"):
					deleteItemFromHouse(inputstreamBuffer);
					break;
				case ("pasteItem"):
					pasteItemInHouse(inputstreamBuffer);
					break;
				case ("shutdown"):
					shutdown(server);
					return;
				default:
					System.out.println("SwitchDefault");
				}
				client.close();

			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendContent(Socket client) {

		try (OutputStream out = client.getOutputStream();) {

			FileReader fileReader = new FileReader("test.txt");
			BufferedReader fileReaderBuffer = new BufferedReader(fileReader);

			PrintWriter writer = new PrintWriter(out);

			// Zeilenweises auslesen der Datei und weitergabe an OutputStream
			String tempString = null;
			while ((tempString = fileReaderBuffer.readLine()) != null) {
				writer.write(tempString + "\n");
			}
			writer.flush();
			fileReaderBuffer.close();

			System.out.println("Content des Hauses gesendet.");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void deleteItemFromHouse(BufferedReader inputStreamBuffer) {

		File tempFile = new File("tempFile.txt");
		File houseFile = new File("test.txt");

		try {
			tempFile.createNewFile();
			FileWriter tempFileWriter = new FileWriter(tempFile, true);
			FileReader fileReader = new FileReader("test.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String searchFor = inputStreamBuffer.readLine();
			System.out.println(searchFor);
			String oneLine = bufferedReader.readLine();

			while (oneLine != null) {
				if (testOfContain(searchFor, oneLine) == false) {
					tempFileWriter.append(oneLine + "\n");
				}
				oneLine = bufferedReader.readLine();
			}

			houseFile.delete();
			tempFile.renameTo(houseFile);

			tempFileWriter.close();
			fileReader.close();
			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void pasteItemInHouse(BufferedReader inputStreamBuffer) {

		try {
			FileWriter writer = new FileWriter("test.txt", true);
			writer.append("\n" + inputStreamBuffer.readLine());
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException f) {
			f.printStackTrace();
		}
		System.out.println("Item eingefügt.");

	}

	public static void shutdown(ServerSocket server) {
		try {
			server.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Server ausgeschaltet.");

	}

	public static boolean testOfContain(String searchFor, String content) {
		return content.contains(searchFor);
	}

}
