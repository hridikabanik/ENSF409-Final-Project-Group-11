/*
 * Client.java
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


public class Client {
	private int[] clientID = new int[5];
	private String[] client = new String[5];
	
	private static int[] wheat = new int[5];
	private static int[] fruitVeggies = new int[5];
	private static int[] protein = new int[5];
	private static int[] other = new int[5];
	private static int[] calories = new int[5];

	public Client(String[][] var){
		int id = 1;
		for(int i = 0; i< var.length; i++){
			int j = 0;
			
			clientID[id] = Integer.parseInt(var[i][j]);j++;
			client[id] = var[i][j];j++;
			wheat[id] = Integer.parseInt(var[i][j]);j++;
			fruitVeggies[id] = Integer.parseInt(var[i][j]);j++;
			protein[id] = Integer.parseInt(var[i][j]);j++;
			other[id] = Integer.parseInt(var[i][j]);j++;
			calories[id] = Integer.parseInt(var[i][j]);j++;
			id++;

		}

	}
	
	
	public void setWheat(int id, int w){
		wheat[id] = w;
	}
	
	public void setFruitVeggies(int id, int fV){
		fruitVeggies[id] = fV;
	}
	
	public void setProten(int id, int p){
		protein[id] = p;
	}
	
	public void setOther(int id, int o){
		other[id] = o;
	}
	
	public void setCalories(int id, int c){
		calories[id] = c;
	}
	
	public static int getWheat(int id){
		return wheat[id];
	}
	
	public static int getFruitVeggies(int id){
		return fruitVeggies[id];
	}
	
	public static int getProtein(int id){
		return protein[id];
	}
	
	public static int getOther(int id){
		return other[id];
	}
	
	public static int getCalories(int id){
		return calories[id];
	}
	
}

