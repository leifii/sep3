package test;

public class skillangriff extends skill{

	
	protected float x;
	protected float y;
	
	protected float dx;	 //richtung in die der skill sich bewegt
	protected float dy;
	
	protected float speed;
	
	protected int width;
	protected int height;
	
	private float lifeTime;
	private float lifeTimer;
	
	public boolean remove;
	
	
	
	
	public skillangriff(float x, float y, float speed, int width, int height, float lifeTime, float lifeTimer){
		
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.lifeTime = lifeTime;
		this.lifeTimer = lifeTimer;
		
		dx = speed;  // multipliziert mit einem faktor
		dy = speed;
		
	}
	
	
	
	
	public void update(float dt){
		x += dx * dt;
		y += dy * dt;
		
		lifeTimer += dt;
		if(lifeTimer > lifeTime)
			remove = true;
	}
	
	
	
	
	
	
	
	
}
