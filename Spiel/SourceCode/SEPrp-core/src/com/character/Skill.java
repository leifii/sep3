package com.character;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.menu.PlayState;


public class Skill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int lvl;
	private int dmg;
	int dmgfaktor;
	private float cdnow;
	float cd;
	int cdfaktor;

	public AnimationDirection direction;

	
	Sprite hallo;

	
	private Body body;
	private int radius;
	private TiledMapTileLayer[] collisionLayer;
	
	
	private TextureRegion [][] a;

	private transient Texture bild;
	private float lifeTime;
	private float lifeTimer;

	private float x;
	private float y;

	protected float dx; 	
	protected float dy;
	
	protected float speed;
	
	protected int button;			//auf welcher taste der skill liegt
	private int position;
	protected int width;
	protected int height;
	
	protected boolean aktiviert;
	protected boolean look;
	
	protected Character c;
	com.grafiken.Character ic = new com.grafiken.Character();

	private boolean remove;
	private boolean alive;

	//protected boolean locked;
	protected boolean buff;
	protected int helpNr;
	

	public Skill(float x, float y, int lvl, int dmg, int dmgfaktor, int cd, int cdfaktor, float speed, float lifeTime, 
			Texture bild, boolean buff, int button, int helpNr, Character c, int radius, TiledMapTileLayer[] collisionLayer /*boolean locked*/) {
		this.setX(x);
		this.setY(y);
		this.lvl = lvl;
		this.setDmg(dmg);
		this.dmgfaktor = dmgfaktor;
		this.cd = cd;
		this.cdfaktor = cdfaktor;
		this.speed = speed;
		this.lifeTime = lifeTime;
		this.bild = bild;
		this.buff = buff;
		this.button = button;
		this.helpNr = helpNr;
		this.c = c;
		remove = false;
		setCdnow(0);
		hallo = new Sprite(bild);
		aktiviert = false;
		this.setRadius(radius);
		this.collisionLayer=collisionLayer;
		a = ic.getGegnerAnimation(0);
	}

	public float gethitcd() {
		return getCdnow();
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void update(float dt, float xx, float yy) {
		handleInput(xx, yy);
		setCdnow(getCdnow() - dt);					//cd nach Benutzung reduzieren
		
		lifeTimer += dt;
		if (lifeTimer > lifeTime) {
			remove = true;
			setAlive(false);
			
		}
		

			
		if (isAlive() == true && buff == false){			//falls kein Buff, dann bewegen
			setX(getX() + dx * dt * speed);
			setY(getY() + dy * dt * speed);
		}
		else if(isAlive() == true && buff == true){			//falls Buff, dann auf Spielerposition halten
			if (c instanceof Schuetze && button == 3){		//schuetzen falle soll liegen bleiben
				if (aktiviert == true) {
				setX(c.getPosition().x);
				setY(c.getPosition().y);
				aktiviert = false;
				}
			}
			else {
			setX(c.getPosition().x);
			setY(c.getPosition().y);
			}
		}

		if (c instanceof Krieger && button == 4 && alive == false){		//dmgfaktor nach cd wieder runtersetzen (4.skill)
			setDmgFaktor(1);
		}
		if (c instanceof Magier && button == 2 && alive == false && aktiviert == true){	//hp vom schild wieder entfernen
			c.currentHP -= getDmg();
			aktiviert = false;
		}
			


		
		if(body!=null)
			body.setTransform(getX()+16, getY()+16, 0);

			
		if(isCellBlocked(x,y))
			alive=false;
	}

	

	public void handleInput(float x, float y) {
		if (getCdnow() < 0.1) {				//falls skill benutzbar
			if (button == 0){
				if (Gdx.input.isKeyPressed(Keys.SPACE)){
					this.setX(x);
					this.setY(y);
					setCdnow(cd);
					lifeTimer = 0;
					setAlive(true);
					if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND) {   //richtung anpassen
						dx = 0 * speed;
						dy = -300 * speed;
					} else if (direction == AnimationDirection.WEST_WALK || direction == AnimationDirection.WEST_STAND) {
						dx = -300 * speed;
						dy = 0 * speed;
					} else if (direction == AnimationDirection.EAST_WALK || direction == AnimationDirection.EAST_STAND) {
						dx = 300 * speed;
						dy = 0 * speed;
					} else if (direction == AnimationDirection.NORTH_WALK || direction == AnimationDirection.NORTH_STAND) {
						dx = 0 * speed;
						dy = 300 * speed;
					}
				}
			}
			if (button == 1){
			if (Gdx.input.isKeyPressed(Keys.NUM_1)) {	
				this.setX(x);
				this.setY(y);
				setCdnow(cd);
				lifeTimer = 0;
				setAlive(true);
				if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND) {   //richtung anpassen
					dx = 0 * speed;
					dy = -300 * speed;
				} else if (direction == AnimationDirection.WEST_WALK || direction == AnimationDirection.WEST_STAND) {
					dx = -300 * speed;
					dy = 0 * speed;
				} else if (direction == AnimationDirection.EAST_WALK || direction == AnimationDirection.EAST_STAND) {
					dx = 300 * speed;
					dy = 0 * speed;
				} else if (direction == AnimationDirection.NORTH_WALK || direction == AnimationDirection.NORTH_STAND) {
					dx = 0 * speed;
					dy = 300 * speed;

				} }
				}

			
			if (button == 2){						//auf taste 2 sind alle buffs
				if (Gdx.input.isKeyPressed(Keys.NUM_2)) {
					this.setX(x);
					this.setY(y);
					setCdnow(cd);
					lifeTimer = 0;
					setAlive(true);
					if(c instanceof Krieger || c instanceof Magier){		//instant heal, bei mage schild
						c.heal(getDmg());
						aktiviert = true;									
					}
					
				}
			}
			if (button == 3){
				if (Gdx.input.isKeyPressed(Keys.NUM_3)) {
					this.setX(x);
					this.setY(y);
					setCdnow(cd);
					lifeTimer = 0;
					setAlive(true);
					if (c instanceof Schuetze ){
						aktiviert = true;
					}
					if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND) {   //richtung anpassen
						dx = 0 * speed;
						dy = -300 * speed;
					} else if (direction == AnimationDirection.WEST_WALK || direction == AnimationDirection.WEST_STAND) {
						dx = -300 * speed;
						dy = 0 * speed;
					} else if (direction == AnimationDirection.EAST_WALK || direction == AnimationDirection.EAST_STAND) {
						dx = 300 * speed;
						dy = 0 * speed;
					} else if (direction == AnimationDirection.NORTH_WALK || direction == AnimationDirection.NORTH_STAND) {
						dx = 0 * speed;
						dy = 300 * speed;
					} 
				}
			}
			if (button == 4){
				if (Gdx.input.isKeyPressed(Keys.NUM_4)) {
					this.setX(x);
					this.setY(y);
					setCdnow(cd);
					lifeTimer = 0;
					setAlive(true);
					if(c instanceof Krieger){		//heal + höherer dmgFaktor
						c.heal(getDmg());
						setDmgFaktor(2);
					}
					if (c instanceof Schurke){
						if (helpNr == 1){	//south
							dx = 0 * speed;
							dy = -300 * speed;
						}
						if (helpNr == 2){	//west
							dx = -300 * speed;
							dy = 0 * speed;
						}
						if (helpNr == 3){	//east
							dx = 300 * speed;
							dy = 0 * speed;
						}
						if (helpNr == 4){	//north
							dx = 0 * speed;
							dy = 300 * speed;
						}
					}
					else {
					if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND) {   //richtung anpassen
//						if (c instanceof Schuetze){
//							System.out.println("skill 4 wird benutzt runter");
//							hallo.setRotation(90);
//						}
						dx = 0 * speed;
						dy = -300 * speed;
					} else if (direction == AnimationDirection.WEST_WALK || direction == AnimationDirection.WEST_STAND) {
//						if (c instanceof Schuetze){
//							hallo.setRotation(90);
//							System.out.println("skill 4 wird benutzt links");
//						}
						dx = -300 * speed;
						dy = 0 * speed;
					} else if (direction == AnimationDirection.EAST_WALK || direction == AnimationDirection.EAST_STAND) {
//						if (c instanceof Schuetze){
//							hallo.setRotation(90);
//							System.out.println("skill 4 wird benutzt rechts");
//						}
						dx = 300 * speed;
						dy = 0 * speed;
					} else if (direction == AnimationDirection.NORTH_WALK || direction == AnimationDirection.NORTH_STAND) {
//						if (c instanceof Schuetze){
//							hallo.setRotation(90);
//							System.out.println("skill 4 wird benutzt hoch");
//						}
						dx = 0 * speed;
						dy = 300 * speed;
					} }
				}
			}
			if (button == 0){
				
			}
			}
			else remove = true;
		}

	

	public void draw(SpriteBatch s) {

//		if (alive == true) {
//			if (c instanceof Schuetze){
//				if (Gdx.input.isKeyJustPressed(Keys.NUM_4)){
//					hallo.setRotation(90);
//					s.draw(hallo, x, y);
//				}
//			}
//			else 
//				s.draw(hallo, x, y);
			//s.draw(bild, x, y);

		if (isAlive() == true) {
			if (c instanceof Schuetze && button == 4){
				//hallo.setScale(1000, 1000);
				//hallo.setSize(1000, 1000);
				System.out.println("skill 4 schuetze");
			}
			if (c instanceof Krieger && button == 0){
				if (direction == AnimationDirection.NORTH_WALK || direction == AnimationDirection.NORTH_STAND){
					s.draw(a[0][0], getX(), getY());
				}
				if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND){
					s.draw(a[1][1], getX(), getY());
				}
				if (direction == AnimationDirection.EAST_WALK || direction == AnimationDirection.EAST_STAND){
					s.draw(a[0][1], getX(), getY());
				}
				if (direction == AnimationDirection.WEST_WALK || direction == AnimationDirection.WEST_STAND){
					s.draw(a[1][0], getX(), getY());
				}
			}
			else s.draw(hallo, getX(), getY());
		}
	}

	public void upgrade() {
		lvl += 1;
		setDmg(getDmg() + (getDmg()/4)); //* dmgfaktor;	würde beim krieger in berserker zu stärkerem skill up führen
		cd -= (cd/8) * cdfaktor;
	}

	public AnimationDirection direction(Character c) {
		return direction = c.getRichtung();
	}
	
//	public void buffed(Character c){
//		if(buff == true && isAlive() == true){
//			if(c instanceof Krieger){
//				System.out.println("buff");
//			}
//		}
//	}
	
	public void setDmgFaktor(int dmgfaktor){
		this.dmgfaktor = dmgfaktor;
	}

	private boolean isCellBlocked(float x, float y) {
		Cell cell;
		boolean blocked = false;
		for (int i = 0; i < collisionLayer.length; i++) {
			cell = collisionLayer[i].getCell((int) (x / collisionLayer[i].getTileWidth()),
					(int) (y / collisionLayer[i].getTileHeight()));
			blocked = blocked || (cell != null && cell.getTile() != null
					&& cell.getTile().getProperties().containsKey("blocked"));
		}
		return blocked;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public float getCdnow() {
		return cdnow;
	}

	public void setCdnow(float cdnow) {
		this.cdnow = cdnow;
	}
}
