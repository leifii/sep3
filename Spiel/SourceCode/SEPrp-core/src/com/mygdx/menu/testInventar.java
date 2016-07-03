package com.mygdx.menu;

import java.util.ArrayList;
import java.util.List;

public class testInventar implements IInventar {

	@Override
	public void place(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAllItems() {
		// TODO Auto-generated method stub

		List<String> itemList = new ArrayList<String>();
		itemList.add( "Flugzeug" );
		itemList.add( "Hubschrauber" );
		itemList.add( "Rennrad" );
		
		return itemList;

	}

	@Override
	public void remove(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getStrenghtBoost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIntelligenceBoost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStaminaBoost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDexterityBoost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHealing() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMoney() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean modifyMoney(int delta) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getValueToName(String nameOfItem) {
		// TODO Auto-generated method stub
		return 10;
	}

}
