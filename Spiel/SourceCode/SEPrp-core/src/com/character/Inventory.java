package com.character;

import java.util.LinkedList;
import java.util.List;

import com.objects.Item;

public class Inventory {

	private List<Item> itemList;
	private int gold;
	
	public Inventory(Item...items) {
		itemList = new LinkedList<Item>();
		for(Item i : items)
			itemList.add(i);
	}
	
	public int getGold() {
		return gold;
	}
	
	public boolean decGold(int sum) {
		if(gold - sum > 0) {
			gold -= sum;
			return true;
		}
		return false;
	}
	
	public void addGold(int sum) {
		gold += sum;
	}
	
	public List<Item> getItemList() {
		return itemList;
	}
	
}
