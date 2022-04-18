import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class Inventory{

    private static LinkedList<Food> foodList;

    public Inventory(LinkedList<Food> listOfFoods){
        this.foodList = listOfFoods;
    }

    public void addFood(Food foodToAdd){
       foodList.add(foodToAdd);
    }

    public static void removeFood(int id){
        /*int i = 0;
        while (i < foodList.size())
            if (foodList[i] == foodToRemove){
                foodList[i] = null;
            }
        }*/
        for (int i = 0; i < foodList.size(); i++){
            Food food = foodList.get(i);

            if (food.getFoodID() == id ){
                foodList.remove(i);
                System.out.println(food.getName() + " with the ID " + food.getFoodID() + " has been removed from the inventory.");
            }
        }
    }

    /*public Food findFood(int content){
        int i = 0;
        while (i < foodList.length){
            if (foodList[i] == content){  // we'll do smth yahan
                return foodList[i];
            }
        }
        return null;


    }*/

    public static LinkedList<Food> getFoodList(){
        return foodList;
    }

}