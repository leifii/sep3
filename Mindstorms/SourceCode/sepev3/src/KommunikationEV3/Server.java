package KommunikationEV3;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{


		public static void main(String[] args) throws IOException, ClassNotFoundException {
			ServerSocket sSocket = new ServerSocket(18415);
			System.out.println("Ich akzeptiere gerade!");
			Socket socket = sSocket.accept();
			System.out.println("Ich bin verbunden!");
			sSocket.close();
			
			System.out.println(socket.getInputStream().read());
			
			
			byte[] read = new byte[2];
			socket.getInputStream().read(read);
			for(byte by : read) {
				System.out.println(by);
			
			
			System.out.println(socket.getInputStream().read());
			socket.close();
			System.out.println("Habe fertig!");

	 	}

	}
