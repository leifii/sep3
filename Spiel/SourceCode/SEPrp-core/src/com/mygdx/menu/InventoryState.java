package com.mygdx.menu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.character.Attributes;
import com.mygdx.game.Author;
import com.objects.Equipment;
import com.objects.Item;
import com.objects.ItemType;

@Author(name = "Sabiha Can")


public class InventoryState extends State {

	private Stage stage;
	private PlayState playstate;
	private SpriteBatch hudBatch;
	private ShapeRenderer sr;
	
	private List<Image> items;
	private Map<ItemType, Image> equipment;
	private List<String> attributes;
	private BitmapFont font;
	private Image playerIcon;
	
	private Item hoverItem;
	
	private int xRow = 7, yRow = 3, size = 64;
	private float offsetX = Gdx.graphics.getWidth() * 0.6f, offsetY = Gdx.graphics.getWidth() * 0.2f;
	private float centerX = Gdx.graphics.getWidth() * 0.65f, centerY = Gdx.graphics.getHeight() * 0.55f;
	private int equipOffset = 100;
	

	
	protected InventoryState(GameStateManager gsm, PlayState playstate) {
		super(gsm);

		this.playstate = playstate;
		
		stage = new Stage(new ScreenViewport());
	    Gdx.input.setInputProcessor(stage);
		
	    sr = new ShapeRenderer();
		hudBatch = new SpriteBatch();
		font = new BitmapFont();
		hoverItem = null;
		
		attributes = new LinkedList<>();
		attributes.add("STR");
		attributes.add("INT");
		attributes.add("DEX");
		attributes.add("STA");
		attributes.add("ATK");
		attributes.add("DEF");
		attributes.add("AS");
		attributes.add("MS");

		TextureRegion t = PlayState.getInstance().getPlayer().getDefaultTexture();
		playerIcon = new Image(new TextureRegionDrawable(t));
		playerIcon.setScale(equipOffset/t.getRegionWidth());
		playerIcon.setPosition(centerX - 20, centerY - playerIcon.getHeight() / 2);
		
		//init Images
		items = new LinkedList<>();
		equipment = new HashMap<>();
		
		Image helmImage = new Image();
		helmImage.setBounds(centerX, centerY + equipOffset, size, size);
		equipment.put(ItemType.Helm, helmImage);
		
		Image waffeImage = new Image();
		waffeImage.setBounds(centerX - equipOffset, centerY, size, size);
		equipment.put(ItemType.Waffe, waffeImage);
		
		Image schildImage = new Image();
		schildImage.setBounds(centerX + equipOffset, centerY, size, size);
		equipment.put(ItemType.Schild, schildImage);
		
		Image armorImage = new Image();
		armorImage.setBounds(centerX, centerY, size, size);
		equipment.put(ItemType.Brustpanzer, armorImage);
		
		Image schuhImage = new Image();
		schuhImage.setBounds(centerX, centerY - equipOffset/2, size, size);
		equipment.put(ItemType.Schuhe, schuhImage);

		for(Entry<ItemType, Image> e : equipment.entrySet())
			e.getValue().addListener(new ImageClick(this));
		
		for(int y = 0; y < yRow; y++) {
			for(int x = 0; x < xRow; x++) {
				Image im = new Image();
				im.setPosition(offsetX + x * size, offsetY - y * size);
				im.setSize(size, size);
				stage.addActor(im);
				items.add(im);
				im.addListener(new ImageClick(this));
			}
		}
		
		updateImages();
	}
	
	protected void updateImages() {
		//reset
		for(Image im : items) {
			im.setDrawable(null);
			im.setUserObject(null);
		}
		
		for(Image im : equipment.values()) {
			im.setDrawable(null);
			im.setUserObject(null);
		}
		
		//init
		int imageIndex = 0;
		for(Item i : PlayState.getInstance().getPlayer().getInventory().getItemList()) {
			if(imageIndex < items.size()) {				
				Image im = items.get(imageIndex);
				im.setDrawable(new TextureRegionDrawable(i.getTextureRegion()));
				im.setUserObject(i);
				imageIndex++;
			} else {
				System.out.println("too many items");
				break;
			}
		}
		
		for(Entry<ItemType, Equipment> set : 
				PlayState.getInstance().getPlayer().getInventory().getEquipmentMap().entrySet()) {
			Image im = equipment.get(set.getKey());
			if(set.getValue() != null) {
				im.setDrawable(new TextureRegionDrawable(set.getValue().getTextureRegion()));
				im.setUserObject(set.getValue());	
			}
		}
	}
	


	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
//		if (Gdx.input.isKeyPressed(Keys.O)) {
//			gsm.push(playstate);
//		}
	}

	@Override
	public void update(float dt) {
//		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		//für halbtransparente Hintergrund
		Gdx.gl.glEnable(GL11.GL_BLEND);
		Gdx.gl.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
		sr.begin(ShapeType.Filled);
		
		sr.setColor(0, 0, 0, 0.7f);
		sr.rect(Gdx.graphics.getWidth() / 2, 0, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
		
		
		if(hoverItem != null)
			sr.rect(0,  0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.2f);
		
		sr.setColor(1, 1, 1, 1);
		
		sr.end();
		Gdx.gl.glDisable(GL11.GL_BLEND);
		
		render();
		
		
	}
	
	public void render() {
		hudBatch.begin();
		stage.act();
		
//		Player
		playerIcon.draw(hudBatch, 0.7f);

//		Items
		for(Image i : items) {
			boolean equipped = PlayState.getInstance().getPlayer().getInventory().isItemEquipped(i.getUserObject());
			i.draw(hudBatch, equipped ? 0.45f : 1);
			hudBatch.setColor(1,1,1,1);
		}
		
		for(Entry<ItemType, Image> i : equipment.entrySet())
			i.getValue().draw(hudBatch, 1);
		
//		Attributes
		Attributes normal = PlayState.getInstance().getPlayer().getAttributes();
		Attributes boost = PlayState.getInstance().getPlayer().getEnhancedAttributes();
		
		for(int i = 0; i < attributes.size(); i++) {
			String key = attributes.get(i);
			font.setColor(Color.WHITE);
			font.draw(hudBatch, key, centerX + 2 * equipOffset, centerY  + equipOffset - i * 20);
			font.draw(hudBatch, normal.getValueAsString(key), centerX + 2 * equipOffset + 50, centerY + equipOffset - i * 20);
			if(Float.parseFloat(normal.getValueAsString(key)) < Float.parseFloat(boost.getValueAsString(key)))
				font.setColor(Color.GREEN);
			else if(Float.parseFloat(normal.getValueAsString(key)) > Float.parseFloat(boost.getValueAsString(key)))
				font.setColor(Color.RED);
			font.draw(hudBatch, boost.getValueAsString(key), centerX + 2 * equipOffset + 100, centerY + equipOffset - i * 20);
		}
		
		//Gold, EXP
		font.setColor(Color.WHITE);
		int exp = playstate.getPlayer().getExp();
		int neededExp = playstate.getPlayer().getNeededexp();
		font.draw(hudBatch, "Level " + playstate.getPlayer().getLevel(), centerX + 3 * equipOffset + 100, centerY + equipOffset);
		font.draw(hudBatch, exp + " / "+ neededExp + " EXP", centerX + 3 * equipOffset + 100, centerY + equipOffset - 20);
		font.draw(hudBatch, PlayState.getInstance().getPlayer().getInventory().getMoney() + " Gold", centerX + 3 * equipOffset + 100, centerY + equipOffset - 70);
		
		//HP
		font.draw(hudBatch, playstate.getPlayer().getCurrentHP() + " / " + playstate.getPlayer().getMaxHP()+ " HP", centerX + 3 * equipOffset + 100, centerY + equipOffset - 40);

		if(hoverItem != null) {
			hudBatch.draw(hoverItem.getTextureRegion(), Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.1f, size, size);
			font.draw(hudBatch, hoverItem.getNAME() + " " + hoverItem.getPriceAsString(), Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.15f + 20);
			font.draw(hudBatch, hoverItem.getType().toString(), Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.15f);
			font.draw(hudBatch, hoverItem.getDescription(), Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.15f - 30);
		}
		
		hudBatch.end();
	

		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	class ImageClick extends ClickListener {
		private InventoryState state;
		private ImageClick(InventoryState state) {
			this.state = state;
		}
		
		public void clicked(InputEvent event, float x, float y) {
			Item i = (Item) event.getTarget().getUserObject();
			if(event.getTarget().getUserObject() != null)
				PlayState.getInstance().getPlayer().getInventory().useItem(i);
			state.updateImages();
		}
		
		public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
			hoverItem = (Item) event.getTarget().getUserObject();
		}
		
		public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
			hoverItem = null;
		}
		
	}

	
}
