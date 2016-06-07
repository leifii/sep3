import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class SocketTest2 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 1337);
		System.out.println("Server gefunden!");
		
		socket.getOutputStream().write(66);
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		WunderschoenesObjekt wso = new WunderschoenesObjekt();
		wso.setEigenschaft(99);
		wso.setEigenschaft2("Luftballons");
		oos.writeObject(wso);
		
		int[] numbers = {1,2,3};
		oos.writeObject(numbers);
		
		byte[] toSend = {44,33};
		socket.getOutputStream().write(toSend);
		
		Scanner sc = new Scanner(System.in);
		socket.getOutputStream().write(sc.nextByte());
		socket.close();
		sc.close();
		System.out.println("Verbindung getrennt!");

	}

}
