package KommunikationPC;


import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

		public static void main(String[] args) throws UnknownHostException, IOException, EOFException {
			Socket socket = new Socket("192.168.2.161", 18415);
			System.out.println("Server gefunden!");
			
			socket.getOutputStream().write(66);
			
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Bitte geben sie etwas ein:");
		
			
			socket.getOutputStream().write(sc.nextByte());
			socket.close();
			sc.close();
			System.out.println("Verbindung getrennt!");

		}
}
