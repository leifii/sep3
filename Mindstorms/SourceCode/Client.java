package PTest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main (String [] args)throws IOException{
	Scanner eingabe= new Scanner(System.in);
	
	Socket socket= new Socket("localhost", 1025);
	OutputStream out= socket.getOutputStream();
	PrintWriter writer= new PrintWriter(out);
	System.out.println("Was soll an den Server gesendet werden?");
	
	String a= eingabe.nextLine();
	writer.write(a);
	writer.flush();
	
	eingabe.close();
	socket.close();
	}
	}
