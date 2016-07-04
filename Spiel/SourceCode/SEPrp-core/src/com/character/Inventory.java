package com.character;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mygdx.game.Author;
import com.mygdx.menu.IInventar;
import com.objects.Equipment;
import com.objects.EquipmentType;
import com.objects.Item;
import com.objects.ItemType;
import com.objects.Trank;
import com.objects.Trank.TrankType;

@Author(name = "Dilara Güler, Sabiha Can")

public class Inventory implements IInventar {

	
	private List<Item> itemList;
	private Map<ItemType, Equipment> equipment;
	private int gold;
	
	
	private Attributes attributeBoost;
	private final Character owner;
	private final int maxITEMS = 21;
	
	public Inventory(Character owner, Item...items) {
		this.owner= owner;
		itemList = new LinkedList<>();
		for(Item i : items)
			add(i);
		
		equipment = new HashMap<>();
		equipment.put(ItemType.Helm, null);
		equipment.put(ItemType.Waffe, null);
		equipment.put(ItemType.Handschuh, null);
		equipment.put(ItemType.Brustpanzer, null);
		equipment.put(ItemType.Schuhe, null);
		
		updateAttributeBoost();
	}
	
	public void useItem(Item i) {
		if(i.getType().isEquipable()) {
			if(equipment.containsKey(i.getType())) {				
				Equipment e = equipment.get(i.getType()) == i ? null : (Equipment) i;
				equipment.put(i.getType(), e);	
				updateAttributeBoost();
			}
		} else if(i.getType() == ItemType.Trank) {
			owner.heal(((Trank) i).getHeal());
			itemList.remove(i);
		}
	}
	
	public boolean isItemEquipped(Object o) {
		if(o == null || !(o instanceof Equipment))
			return false;
		Equipment e = (Equipment) o;
		return equipment.containsKey(e.getType()) && equipment.containsValue(e);
	}

	
	public boolean decGold(int sum) {
		if(gold - sum > 0) {
			gold -= sum;
			return true;
		}
		return false;
	}
	
	public boolean addGold(int sum) {
		gold += sum;
		return true;
	}
	
	public List<Item> getItemList() {
		return itemList;
	}

	@Override
	public void add(Item i) {
		if(i != null) {
			if(itemList.size() < maxITEMS)
				itemList.add(i);
			else 
				System.out.println("Inventory full");
		}
	}

	@Override
	public List<String> getAllItems() {
		List<String> itemStringList = new LinkedList<>();
		for(Item i: itemList)
			itemStringList.add(i.getNAME());
		return itemStringList;
	}

	@Override
	public void remove(String name) {
		Iterator<Item> i = itemList.iterator();
		while(i.hasNext()) {
			if(i.next().getNAME().compareTo(name) == 0)
				i.remove();
		}	
	}
	
	public Attributes updateAttributeBoost() {
		 Attributes boost = new Attributes(0,0,0,0,0,0,0,0);
		 
		 for(Entry<ItemType, Equipment> e : equipment.entrySet()) {
			 if(e.getValue() != null)
				 boost.addAttributeValues(e.getValue().getEquipmentType().getAttributes());
		 }
		 return attributeBoost = boost;
	}
	
	public Attributes getAttributeBoost() {
		return attributeBoost;
	}

	@Override
	public int getStrenghtBoost() {
		return getAttributeBoost().getSTR();
	}

	@Override
	public int getIntelligenceBoost() {
		return getAttributeBoost().getINT();
	}

	@Override
	public int getStaminaBoost() {
		return getAttributeBoost().getSTA();
	}

	@Override
	public int getDexterityBoost() {
		return getAttributeBoost().getDEX();
	}


	@Override
	public int getMoney() {
		return gold;
	}

	@Override
	public boolean modifyMoney(int delta) {
		if(delta < 0)
			return decGold(delta);
		return addGold(delta);
	}
	
	public Map<ItemType, Equipment> getEquipmentMap() {
		return equipment;
	}

	@Override
	public int getValueToName(String nameOfItem) {
		for(Item i : itemList)
			if(i.getNAME().compareTo(nameOfItem) == 0)
				return i.getValue();
		System.out.println(nameOfItem + " nicht im Inventar gefunden");
		return -1;
	}
	
	public void doSomething() {
		boolean buy = true;
		if(buy) {
			place("Lederrüstung");							//wieso so
			add(new Equipment(EquipmentType.Lederrüstung)); //und nicht so? Ist viel sauberer
			
		}
	}
	
	public void place(String itemName) {
		EquipmentType e = EquipmentType.valueOf(itemName);
		if(e != null)
			add(new Equipment(e));
		
		Trank t = TrankType.getTrank(itemName);
		if(t != null)
			add(t);
	}
	
}
