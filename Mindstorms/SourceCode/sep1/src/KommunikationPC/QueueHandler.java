/** @author ${Tristan}
 * 
 */
package KommunikationPC;

import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueHandler  {
	
	int anz;
	int anzahlClients;
	
    ConcurrentLinkedQueue<Byte> queue1;
	ConcurrentLinkedQueue<Byte> queue2;
	ConcurrentLinkedQueue<Byte> queue3;
	ConcurrentLinkedQueue<Byte> queue4;
	QueueHandler queueHandler;
	
	
	
	byte spielstart=001, anfangPause=002, endePause=003, sepmanNord=004, sepmanOst=005, sepmanSued=006, sepmanWest=007,
		 dGeist1=11, dGeist2=12, dGeist3=13, aGeist1=21, aGeist2=22, aGeist3=23, spielEnde=123,
		 powerUp06=-06, powerUp25=-25, powerUp32=-32, powerUp51=-51, endePowerUp=100;
	
	
	
	
	
	public void setQueueHandler(QueueHandler q){
		this.queueHandler = q;
	}
	
	public QueueHandler getQueueHandler(){
		return queueHandler;
		
	}
	
	
	
	
	public QueueHandler(ConcurrentLinkedQueue<Byte> q1) {                              //Konstruktor des neuen
		
			this.queue1 = q1;
		    this.anzahlClients=1;
		}
	
	public QueueHandler(ConcurrentLinkedQueue<Byte> q1, ConcurrentLinkedQueue<Byte>q2){
		
			this.queue1 = q1;
			this.queue2 = q2;
			this.anzahlClients = 2;
		}
	
	public QueueHandler(ConcurrentLinkedQueue<Byte> q1, ConcurrentLinkedQueue<Byte>q2, ConcurrentLinkedQueue<Byte>q3){
		
			this.queue1 = q1;
			this.queue2 = q2;
			this.queue3 = q3;
			this.anzahlClients = 3;
		}

	public QueueHandler(ConcurrentLinkedQueue<Byte> q1, ConcurrentLinkedQueue<Byte>q2, ConcurrentLinkedQueue<Byte>q3, ConcurrentLinkedQueue<Byte>q4){
	
			this.queue1 = q1;
			this.queue2 = q2;
			this.queue3 = q3;
			this.queue4 = q4;
			this.anzahlClients = 4;
		}
	
	
	public void addToQueue(Byte b){                               //Fügt der Queue ein neues Byte hinzu, dass an den Roboter geschickt werden soll
		
		if(anzahlClients == 1){
			queue1.add(b);
		}
		if(anzahlClients==2){
			queue1.add(b);
			queue2.add(b);
		}
		if(anzahlClients==3){
			queue1.add(b);
			queue2.add(b);
			queue3.add(b);
		}
		if(anzahlClients==4){
			queue1.add(b);
			queue2.add(b);
			queue3.add(b);
			queue4.add(b);
		}
	}
	
	public byte getNextQueued(int clientNr){                       //Entnimmt der Queue ein Element nach FIFO-Prinzip
		                                                           
		Byte b = null;
		if(clientNr==1){
			b = queue1.poll();
		}
		else if(clientNr==2){
			b = queue2.poll();
		}
		else if(clientNr==3){
			b = queue3.poll();
		}
		else if(clientNr==4){
		    b = queue4.poll();
		}
		
//		System.out.println("Something has been pulled: "+b);        //just for testing >_>
		if(b!=null){
		return b;
		}
		else{
			return b = 0;
			}
	}
	
	
	public void spielStart(){
		queueHandler.addToQueue(spielstart);
	}
	
	public void anfangPause(){
		queueHandler.addToQueue(anfangPause);
	}
	
	public void endePause(){
		queueHandler.addToQueue(endePause);
	}
	
	public void norden(){
		queueHandler.addToQueue(sepmanNord);
	}
	
	public void osten(){
		queueHandler.addToQueue(sepmanOst);	
	}
	
	public void sueden(){
		queueHandler.addToQueue(sepmanSued);
	}
	
	public void westen(){
		queueHandler.addToQueue(sepmanWest);
	}
	
	public void deaktiviereGeist(int x){
		if(x==1){
			queueHandler.addToQueue(dGeist1);
		}
		if(x==2){
			queueHandler.addToQueue(dGeist2);
		}
		if(x==3){
			queueHandler.addToQueue(dGeist3);
		}
	}
	
	public void aktiviereGeist(int x){
		if(x==1){
			queueHandler.addToQueue(aGeist1);
			
		}
		if(x==2){
			queueHandler.addToQueue(aGeist2);
			
		}
		if(x==3){
			queueHandler.addToQueue(aGeist3);
			
		}
	}
	
	public void startPowerUp(int x){
		if(x==06){
			queueHandler.addToQueue(powerUp06);
		}
		if(x==25){
			queueHandler.addToQueue(powerUp25);
		}
		if(x==32){
			queueHandler.addToQueue(powerUp32);
		}
		if(x==51){
			queueHandler.addToQueue(powerUp51);
		}
	}
	
	public void endePowerUp(){
		
		queueHandler.addToQueue(endePowerUp);
	}
	
	public void spielEnde(){
		queueHandler.addToQueue(spielEnde);
	}
	
	
	
	
	
	
	
	
	
	
	public boolean isQueueEmpty(){
		
		if(queue1.isEmpty()){
			return true;
		}
		return false;
	}
	
	
	public Byte firstByte(ConcurrentLinkedQueue<Byte> q){             //just for testing purposes - peeks first byte of queue
		
		return q.peek();
		
	}
	
	public void add1(){                                               //just for testing purposes >_<
		Byte b = 1;
		if(anzahlClients == 1){
			queue1.add(b);
		}
		if(anzahlClients==2){
			queue1.add(b);
			queue2.add(b);
		}
		if(anzahlClients==3){
			queue1.add(b);
			queue2.add(b);
			queue3.add(b);
		}
		if(anzahlClients==3){
			queue1.add(b);
			queue2.add(b);
			queue3.add(b);
			queue4.add(b);
		}
	}
}
