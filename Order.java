import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/*
 * Order.java
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


public class Order {
	private int familyNum;
	private int totalCalories;
	private int numberOfItems;
	private String orderDetails;
	private String[] orders;
	private int numberOfHampers = 1;

	
	public Order(String request, String[] orders) throws FileNotFoundException{
		 orderDetails= request;
		 this.orders = orders;
		 createOrderFile();
	}
	
	public void createOrderFile() throws FileNotFoundException{
		PrintStream printToFile = null;
    	try {
    		printToFile = new PrintStream(new FileOutputStream("Order.txt"));
			
		} catch (FileNotFoundException e) {e.printStackTrace();}

		for(int i = 0; i < orders.length; i++){
			printToFile.println("Hamper " + numberOfHampers);
			printToFile.println(orders[i]);
			numberOfHampers++;
		}
	}


	
	public String returnFinalOrder(){
		return "shahzusuxd";
	}
}

