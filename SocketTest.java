import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket sSocket = new ServerSocket(1337);
		System.out.println("Ich akzeptiere gerade!");
		Socket socket = sSocket.accept();
		System.out.println("Ich bin verbunden!");
		sSocket.close();
		
		System.out.println(socket.getInputStream().read());
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		WunderschoenesObjekt empfangen = (WunderschoenesObjekt) ois.readObject();
		System.out.println(empfangen.toString());
		
		int[] andererName = (int[]) ois.readObject();
		for(int integer : andererName) {
			System.out.println(integer);
		}
		
		byte[] read = new byte[2];
		socket.getInputStream().read(read);
		for(byte by : read) {
			System.out.println(by);
		}
		
		System.out.println(socket.getInputStream().read());
		socket.close();
		System.out.println("Habe fertig!");

 	}

}
