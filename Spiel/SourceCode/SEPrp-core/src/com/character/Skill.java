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
import com.gegnerkoordination.Endboss;
import com.gegnerkoordination.SchleimEndgegner;
import com.gegnerkoordination.Skelett;
import com.gegnerkoordination.OrkEndgegner;
import com.gegnerkoordination.SkelettEndgegner;
import com.mygdx.game.Author;

@Author(name = "Bardia Asemi-Soloot")

public class Skill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int lvl;		//stufe die der skill hat, startet bei 0, ab 1 freigeschaltet
	private int dmg;
	//int dmgfaktor;
	private float cdnow;
	private float cd;
	int cdfaktor;

	public AnimationDirection direction;
	protected AnimationDirection richtung;

	// Sprite hallo;

	private Body body;
	private int radius;
	private TiledMapTileLayer[] collisionLayer;

	private TextureRegion[][] a;

	private transient Texture bild;
	private float lifeTime;
	private float lifeTimer;

	private float x;
	private float y;

	protected float dx;
	protected float dy;

	protected float speed;
					
	private int button;			//auf welcher taste der skill liegt

	protected float zaehler;
	
	private int position;
	protected int width;
	protected int height;

	protected boolean aktiviert;
	protected boolean look;

	protected Character c;
	com.grafiken.Character ic = new com.grafiken.Character();

	private boolean remove;
	private boolean alive;


	protected boolean locked;

	protected boolean buff;
	protected boolean skillup;
	protected int helpNr;


	public Skill(float x, float y, int lvl, int dmg, int cd, int cdfaktor, float speed, float lifeTime, 
			Texture bild, boolean buff, int button, int helpNr, Character c, int radius, TiledMapTileLayer[] collisionLayer) {

		this.setX(x);
		this.setY(y);
		this.lvl = lvl;
		
		if(c instanceof Krieger){
			float d;
			d = dmg * (c.attributes.getSTR()/10);
			this.setDmg((int)d);
		}		
		if(c instanceof Schurke){
			float d;
		d = dmg * (c.attributes.getDEX()/10);
		this.setDmg((int)d);
		}
		if(c instanceof Schuetze){
			float d;
		d = dmg * (c.attributes.getDEX()/10);
		this.setDmg((int)d);
		}
		if(c instanceof Magier){
			float d;
		d = dmg * (c.attributes.getINT()/10);
		this.setDmg((int)d);
		}
		
		
		this.setCd(cd);
		this.cdfaktor = cdfaktor;
		this.speed = speed;
		this.lifeTime = lifeTime;
		this.bild = bild;
		this.buff = buff;
		this.setButton(button);
		this.helpNr = helpNr;
		this.c = c;
		remove = false;
		setCdnow(0);
		// hallo = new Sprite(bild);
		aktiviert = false;
		this.setRadius(radius);
		this.setCollisionLayer(collisionLayer);
		a = ic.getGegnerAnimation(0);

		skillup = false;
//		if (button == 0)
//			locked = false;
//		else locked = true;
		
		zaehler = 0;			//drehung der axt

	}

	public float gethitcd() {
		return getCdnow();
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void update(float dt, float xx, float yy) {
		//dmgfaktor = c.getdmgFaktor();
		

		if (zaehler == 360) // bei kompletter drehung der axt auf 0 wieder  setzen
			zaehler = 0;
		zaehler += 10; // axt drehen
		handleInput(xx, yy);
		setCdnow(getCdnow() - dt); // cd nach Benutzung reduzieren
		dmg = getDmg() * c.getdmgFaktor();

		lifeTimer += dt;
		if (lifeTimer > lifeTime) {
			remove = true;
			setAlive(false);

		}
		
		// für Endgegner
		if ((c instanceof SchleimEndgegner || c instanceof SkelettEndgegner || c instanceof OrkEndgegner) && getButton() == 1) {
			if (helpNr == 1) { // south
				dx = 0 * speed;
				dy = -300 * speed;
			}
			if (helpNr == 2) { // west
				dx = -300 * speed;
				dy = 0 * speed;
			}
			if (helpNr == 3) { // east
				dx = 300 * speed;
				dy = 0 * speed;
			}
			if (helpNr == 4) { // north
				dx = 0 * speed;
				dy = 300 * speed;
			}
		}
		// für letzten Endboss
		if(c instanceof Endboss){
			
		}
		
		if (isAlive() == true && buff == false) { // falls kein Buff, dann
													// bewegen
			setX(getX() + dx * dt * speed);
			setY(getY() + dy * dt * speed);
		} else if (isAlive() == true && buff == true) { // falls Buff, dann auf
														// Spielerposition
														// halten
			if (c instanceof Schuetze && getButton() == 3) { // schuetzen falle soll
														// liegen bleiben
				if (aktiviert == true) {
					setX(c.getPosition().x);
					setY(c.getPosition().y);
					aktiviert = false;
				}
			} else {
				setX(c.getPosition().x);
				setY(c.getPosition().y);
			}
		}

		if (c instanceof Krieger && getButton() == 4 && alive == false) { // dmgfakto nach cd wieder runtersetzen (4.skill)
			c.setdmgFaktor(1);
		}
		if (c instanceof Magier && getButton() == 2 && alive == false && aktiviert == true) { // hp vom schild wieder entfernen
			c.currentHP -= getDmg();
			aktiviert = false;
		}
		if (c instanceof Schurke && getButton() == 2 && alive == false && aktiviert == true){	//tempo vom schurken runtersetzen
			c.attributes.setMS(2.5f);
			aktiviert = false;
		}

		if (body != null)
			body.setTransform(getX() + 16, getY() + 16, 0);

		if (isCellBlocked(x + 16, y + 16))
			alive = false;
	}

	public void activateProjectile(float x, float y) {
		if (getCdnow() < 0.1) {
			this.setX(x);
			this.setY(y);
			setCdnow(getCd());
			lifeTimer = 0;
			richtung = direction;
			setAlive(true);
			if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND) { // richtung
																												// anpassen
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

	public void handleInput(float x, float y) {

		if (getCdnow() < 0.1 && lvl >= 1 && skillup == false) {				//falls skill benutzbar
			if (getButton() == 0){
				if (Gdx.input.isKeyPressed(Keys.SPACE)){
					this.setX(x);
					this.setY(y);
					setCdnow(getCd());
					lifeTimer = 0;
					richtung = direction;
					setAlive(true);
					if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND) { // richtung anpassen
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
			if (getButton() == 1) {
				if (Gdx.input.isKeyPressed(Keys.NUM_1)) {
					if (c instanceof SkelettEndgegner) {			//pfeile fliegen in alle richtungen
						if (helpNr == 1) { // south
							dx = 0 * speed;
							dy = -300 * speed;
						}
						if (helpNr == 2) { // west
							dx = -300 * speed;
							dy = 0 * speed;
						}
						if (helpNr == 3) { // east
							dx = 300 * speed;
							dy = 0 * speed;
						}
						if (helpNr == 4) { // north
							dx = 0 * speed;
							dy = 300 * speed;
						} }
						else activateProjectile(x, y);
				}
			}

			if (getButton() == 2) { // auf taste 2 sind alle buffs
				if (Gdx.input.isKeyPressed(Keys.NUM_2)) {
					this.setX(x);
					this.setY(y);
					setCdnow(getCd());
					lifeTimer = 0;
					richtung = direction;
					setAlive(true);
					if (c instanceof Krieger || c instanceof Magier) { // instant heal bei mage schild											
						c.heal(getDmg());
						aktiviert = true;
					}
					if (c instanceof Schurke){
						c.attributes.setMS(3);
						aktiviert = true;
					}

				}
			}
			if (getButton() == 3) {
				if (Gdx.input.isKeyPressed(Keys.NUM_3)) {
					activateProjectile(x, y);
				}
			}
			if (getButton() == 4) {
				if (Gdx.input.isKeyPressed(Keys.NUM_4)) {
					this.setX(x);
					this.setY(y);
					setCdnow(getCd());
					lifeTimer = 0;
					richtung = direction;
					setAlive(true);
					if (c instanceof Krieger) { // heal + höherer dmgFaktor
						c.heal(getDmg());
						c.setdmgFaktor(2);
					}
					if (c instanceof Schurke) {
						if (helpNr == 1) { // south
							dx = 0 * speed;
							dy = -300 * speed;
						}
						if (helpNr == 2) { // west
							dx = -300 * speed;
							dy = 0 * speed;
						}
						if (helpNr == 3) { // east
							dx = 300 * speed;
							dy = 0 * speed;
						}
						if (helpNr == 4) { // north
							dx = 0 * speed;
							dy = 300 * speed;
						}
					} else {
						if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND) { // richtung anpassen
							dx = 0 * speed;
							dy = -300 * speed;
						} else if (direction == AnimationDirection.WEST_WALK
								|| direction == AnimationDirection.WEST_STAND) {
							dx = -300 * speed;
							dy = 0 * speed;
						} else if (direction == AnimationDirection.EAST_WALK
								|| direction == AnimationDirection.EAST_STAND) {
							dx = 300 * speed;
							dy = 0 * speed;
						} else if (direction == AnimationDirection.NORTH_WALK
								|| direction == AnimationDirection.NORTH_STAND) {
							dx = 0 * speed;
							dy = 300 * speed;
						}
					}
				}
			}

		} else
			remove = true;
	}

	public void draw(SpriteBatch s) {

		// if (alive == true) {
		// if (c instanceof Schuetze){
		// if (Gdx.input.isKeyJustPressed(Keys.NUM_4)){
		// hallo.setRotation(90);
		// s.draw(hallo, x, y);
		// }
		// }
		// else
		// s.draw(hallo, x, y);
		// s.draw(bild, x, y);

		if (isAlive() == true) {

			if ((c instanceof Schuetze || c instanceof Skelett) && !(c instanceof SkelettEndgegner)) {
				if (getButton() == 0 || getButton() == 1 || getButton() == 4) {
					System.out.println(getButton());
					if (richtung == AnimationDirection.NORTH_WALK || richtung == AnimationDirection.NORTH_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 90, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
					if (richtung == AnimationDirection.SOUTH_WALK || richtung == AnimationDirection.SOUTH_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 270, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
					if (richtung == AnimationDirection.EAST_WALK || richtung == AnimationDirection.EAST_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 0, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
					if (richtung == AnimationDirection.WEST_WALK || richtung == AnimationDirection.WEST_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 180, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
				} else
					s.draw(bild, x, y);
			} else if (c instanceof Krieger) {
				if (getButton() == 0) {
					if (direction == AnimationDirection.NORTH_WALK || direction == AnimationDirection.NORTH_STAND) {
						s.draw(a[0][0], getX(), getY());
					}
					if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND) {
						s.draw(a[3][0], getX()-7, getY()-20);
					}
					if (direction == AnimationDirection.EAST_WALK || direction == AnimationDirection.EAST_STAND) {
						s.draw(a[2][0], getX(), getY()-10);
					}
					if (direction == AnimationDirection.WEST_WALK || direction == AnimationDirection.WEST_STAND) {
						s.draw(a[1][0], getX()-10, getY()-15);
					}
				}
				if (getButton() == 1 || getButton() == 3) {
					s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
							(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) zaehler, 1,
							1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
				}

				else
					s.draw(bild, getX(), getY());

			} else if (c instanceof Schurke) {
				if (getButton() == 0 || getButton() == 1 || getButton() == 3) {
					if (richtung == AnimationDirection.NORTH_WALK || richtung == AnimationDirection.NORTH_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 0, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
					if (richtung == AnimationDirection.SOUTH_WALK || richtung == AnimationDirection.SOUTH_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 180, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
					if (richtung == AnimationDirection.EAST_WALK || richtung == AnimationDirection.EAST_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 270, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
					if (richtung == AnimationDirection.WEST_WALK || richtung == AnimationDirection.WEST_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 90, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
				} else if (getButton() == 4) {
					if (helpNr == 1)
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 180, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					if (helpNr == 2)
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 90, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					if (helpNr == 3)
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 270, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					if (helpNr == 4)
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 0, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
				} else
					s.draw(bild, getX(), getY());
				
			} else if(c instanceof OrkEndgegner || c instanceof SkelettEndgegner){
				if(getButton()==1){
					if (helpNr == 1)
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 270, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					if (helpNr == 2)
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 180, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					if (helpNr == 3)
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 0, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					if (helpNr == 4)
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 90, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
				}
				if(getButton()==0 || getButton()==3){
					if (richtung == AnimationDirection.NORTH_WALK || richtung == AnimationDirection.NORTH_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 90, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
					if (richtung == AnimationDirection.SOUTH_WALK || richtung == AnimationDirection.SOUTH_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 270, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
					if (richtung == AnimationDirection.EAST_WALK || richtung == AnimationDirection.EAST_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 0, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
					if (richtung == AnimationDirection.WEST_WALK || richtung == AnimationDirection.WEST_STAND) {
						s.draw(bild, x, y, (float) bild.getWidth() / 2, (float) bild.getHeight() / 2,
								(float) bild.getWidth(), (float) bild.getHeight(), (float) 1, (float) 1, (float) 180, 1,
								1, (int) bild.getWidth(), (int) bild.getHeight(), false, false);
					}
				}
				} else
				s.draw(bild, getX(), getY());

		}

	}

	public void lvlup() {
		lvl += 1;
		setDmg(getDmg() + (getDmg() / 4)); // * dmgfaktor; würde beim krieger in
											// berserker zu stärkerem skill up
											// führen
		setCd(getCd() - (getCd() / 8) * cdfaktor);
		
	}
	
	
	
	

	public AnimationDirection direction(Character c) {
		return direction = c.getRichtung();
	}

	// public void buffed(Character c){
	// if(buff == true && isAlive() == true){
	// if(c instanceof Krieger){
	// System.out.println("buff");
	// }
	// }
	// }

//	public void setDmgFaktor(int dmgfaktor) {
//		this.dmgfaktor = dmgfaktor;
//	}

	private boolean isCellBlocked(float x, float y) {
		Cell cell;
		boolean blocked = false;
		for (int i = 0; i < getCollisionLayer().length; i++) {
			if(getCollisionLayer()[i]!=null){
				if (x < getCollisionLayer()[i].getWidth() * getCollisionLayer()[i].getTileWidth() &&
						y < getCollisionLayer()[i].getHeight() * getCollisionLayer()[i].getTileHeight() &&
						x >= 0 && y >= 0){
					cell = getCollisionLayer()[i].getCell((int) (x / getCollisionLayer()[i].getTileWidth()),
							(int) (y / getCollisionLayer()[i].getTileHeight()));
					blocked = blocked || (cell != null && cell.getTile() != null
							&& cell.getTile().getProperties().containsKey("blocked"));
				}
				else blocked = true;
			}
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

	public Rolle getCasterRolle() {
		return c.getRolle();
	}

	public TiledMapTileLayer[] getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer[] collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	public float getCd() {
		return cd;
	}

	public void setCd(float cd) {
		this.cd = cd;
	}
	
	public void setskillup(boolean su){
		skillup = su;
	}
	
	public boolean getskillup(){
		return skillup;
	}

	public int getButton() {
		return button;
	}

	public void setButton(int button) {
		this.button = button;
	}
	
	public int getlvl() {
		return lvl;
	}
	
	public void setlvl(int lvl) {
		this.lvl = lvl;
	}
}
