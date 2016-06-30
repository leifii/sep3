/**
 * @author ${user}
 *
 * 
 */
package KommunikationPC;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Clientinit {
	
	String ip1, ip2, ip3, ip4;
	String modus1, modus2, modus3, modus4;
	int port1, port2, port3, port4;
	int clientNr1, clientNr2, clientNr3, clientNr4;
	
	Client client1;
	Client client2;
	Client client3;
	Client client4;
	
	ConcurrentLinkedQueue<Byte> queue1;
	ConcurrentLinkedQueue<Byte> queue2;
	ConcurrentLinkedQueue<Byte> queue3;
	ConcurrentLinkedQueue<Byte> queue4;
	
	QueueHandler queueHandler;
	
	
	public Clientinit()throws IOException{
		
	}
	
	public void clientInit(String ip1, String modus1, int port1, int clientNr1){
		queue1 = new ConcurrentLinkedQueue<Byte>();
		queueHandler = new QueueHandler(queue1);
		setQueueHandler(queueHandler);
		
		Thread client1 = new Thread(new Client(ip1, modus1, port1, clientNr1, queueHandler));
		client1.start();
		
		
		
		
	}
	
	public void clientInit(String ip1, String ip2, String modus1, String modus2, int port1, int port2, int clientNr1, int clientNr2){
		queue1 = new ConcurrentLinkedQueue<Byte>();
		queue2 = new ConcurrentLinkedQueue<Byte>();
		queueHandler = new QueueHandler(queue1, queue2);
		setQueueHandler(queueHandler);
		
		Thread client1 = new Thread(new Client(ip1, modus1, port1, clientNr1, queueHandler));
		Thread client2 = new Thread(new Client(ip2, modus2, port2, clientNr2, queueHandler));
		client1.start(); 
		client2.start();
		
		
		
		
		
	}
	
	public void clientInit(String ip1, String ip2, String ip3, String modus1, String modus2, String modus3, 
						   int port1, int port2, int port3, int clientNr1, int clientNr2, int clientNr3){
		
		queue1 = new ConcurrentLinkedQueue<Byte>();
		queue2 = new ConcurrentLinkedQueue<Byte>();
		queue3 = new ConcurrentLinkedQueue<Byte>();
		queueHandler = new QueueHandler(queue1, queue2, queue3);
		setQueueHandler(queueHandler);
		
		Thread client1 = new Thread(new Client(ip1, modus1, port1, clientNr1, queueHandler));
		Thread client2 = new Thread(new Client(ip2, modus2, port2, clientNr2, queueHandler));
		Thread client3 = new Thread(new Client(ip3, modus3, port3, clientNr3, queueHandler));
		client1.start(); 
		client2.start();
		client3.start();
		
		
	}
	
	public void clientInit(String ip1, String ip2, String ip3,String ip4, String modus1, String modus2, String modus3, String modus4,
			   int port1, int port2, int port3, int port4, int clientNr1, int clientNr2, int clientNr3, int clientNr4){

		queue1 = new ConcurrentLinkedQueue<Byte>();
		queue2 = new ConcurrentLinkedQueue<Byte>();
		queue3 = new ConcurrentLinkedQueue<Byte>();
		queue4 = new ConcurrentLinkedQueue<Byte>();
		queueHandler = new QueueHandler(queue1, queue2, queue3, queue4);
		setQueueHandler(queueHandler);

		Thread client1 = new Thread(new Client(ip1, modus1, port1, clientNr1, queueHandler));
		Thread client2 = new Thread(new Client(ip2, modus2, port2, clientNr2, queueHandler));
		Thread client3 = new Thread(new Client(ip3, modus3, port3, clientNr3, queueHandler));
		Thread client4 = new Thread(new Client(ip4, modus4, port4, clientNr4, queueHandler));
		
		client1.start(); 
		client2.start();
		client3.start();
		client4.start();
		
		
		

}
	
	public void setQueueHandler(QueueHandler q){
		this.queueHandler = q;
	}
	
	public QueueHandler getQueueHandler(){
		return queueHandler;
		
	}
	



	

}
