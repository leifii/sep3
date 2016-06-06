package PTest;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException {
	ServerSocket socket = new ServerSocket (1025);
	Socket connection= socket.accept();
	
	InputStream in = connection.getInputStream();
	System.out.println("Bitte geben sie etwas ein:");
	Scanner scanner= new Scanner(in);
	
	scanner.close();
	socket.close();
	}

}
