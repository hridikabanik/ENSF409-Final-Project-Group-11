import java.util.LinkedList;

/*
 * Food.java
 * 
 * Copyright 2022 muteeba jamal <muteeba@muteebas-MacBook-Air.local>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */


public class Food {
	LinkedList<Object[]> foodList = new LinkedList<Object[]>();
	
	int itemID;
	String name;
	int grainC;
	int fruitVegC;
	int proteinC;
	int otherC;
	int calorie;
	
	public Food(String itemID, String name, String grainC, String fruitVegC, String proteinC, String otherC, String calorie){
		this.itemID = Integer.parseInt(itemID);
		this.name = name;
		this.grainC = Integer.parseInt(grainC);
		this.fruitVegC = Integer.parseInt(fruitVegC);
		this.proteinC = Integer.parseInt(proteinC);
		this.otherC = Integer.parseInt(otherC);
		this.calorie = Integer.parseInt(calorie);
	}
	
	public void setGrainC(int g){
		grainC = g;
	}
	
	public void setFruitVegC(int fV){
		fruitVegC = fV;
	}
	
	public void setProtenC(int p){
		proteinC = p;
	}
	
	public void setOtherC(int o){
		otherC = o;
	}
	
	public void setCalorie(int c){
		calorie = c;
	}

	public String getName(){
		return name;
	}

	public int getFoodID(){
		return itemID;
	}
	
	public int getGrainC(){
		return grainC;
	}
	
	public int getFruitVegC(){
		return fruitVegC;
	}
	
	public int getProteinC(){
		return proteinC;
	}
	
	public int getOtherC(){
		return otherC;
	}
	
	public int getCalorie(){
		return calorie;
	}

	public int getTotalCalorie(){
		int total = grainC+fruitVegC+proteinC+otherC+calorie;
		
		return total;
	}
}

