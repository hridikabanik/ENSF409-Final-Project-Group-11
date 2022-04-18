import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Hamper{
    
    private int maleAdult;
    private int femaleAdult;
    private int childUnder;
    private int childOver;
    private static int totalWheat;
    private static int totalFV;
    private static int totalProtein;
    private static int totalOthers;
    private static int totalCalories;

    private LinkedList<LinkedList<Food>> hamperCombinations;
    private LinkedList<Food> finalHamper;

    
    private static int[] caloriesArrayNon0;
    
    private static int[] caloriesArrayNon0ID;
   
   
    private StringBuilder hamper;
    private String finalHamperStr = new String();
    
    
    
    
    

    public Hamper(int males, int females, int childOver, int childUnder){
        this.maleAdult = males;
        this.femaleAdult = females;
        this.childUnder = childUnder;
        this.childOver = childOver;

        calcTWG();
        calcFV();
        calcProtein();
        calcOther();
        calcCalories();
        hamperFunctionalities();
        
    }

    public Hamper(int twg, int fv, int p, int o, int c){
        totalWheat = twg;
        totalFV = fv;
        totalProtein = p;
        totalOthers = o;
        totalCalories = c;
        hamperFunctionalities();
        
    }

    public void hamperFunctionalities(){
        
     listMaker();
        combinations();
        if (hamperCombinations.size()!=0){
            int i = bestCombo();
            finalHamperStr = createHamper(i);
        }
        else{
            System.out.println("shahzu is a dummy");
        }
       

     
    }

    public static void setTWG(int TWG){
        totalWheat = TWG;
    }

    public static void setTFV(int TFV){
        totalFV = TFV;
    }

    public void setTP(int TP){
        totalProtein = TP;
    }

    public void setTO(int TO){
        totalOthers = TO;
    }

    public void setTC(int TC){
        totalCalories = TC;
    }

    public int getTWG(){
        return totalWheat;
    }

    public int getFV(){
        return totalFV;
    }

    public int getTP(){
        return totalProtein;
    }

    public int getTO(){
        return totalOthers;
    }

    public int getTC(){
        return totalCalories;
    }
    
    public String getHamper(){
        return finalHamperStr;

    }



    public int calcTWG(){
        totalWheat = Client.getWheat(1) * maleAdult + Client.getWheat(2) * femaleAdult + Client.getWheat(3) * childOver + Client.getWheat(4) * childUnder;
        return totalWheat;
    }

    public int calcFV(){
        totalFV = Client.getFruitVeggies(1) * maleAdult + Client.getFruitVeggies(2) * femaleAdult + Client.getFruitVeggies(3) * childOver + Client.getFruitVeggies(4) * childUnder;
        return totalFV;
    }

    public int calcProtein(){
        totalProtein = Client.getProtein(1) * maleAdult + Client.getProtein(2) * femaleAdult + Client.getProtein(3) * childOver + Client.getProtein(4) * childUnder;
        return totalProtein;
    }

    public int calcOther(){
        totalOthers = Client.getOther(1) * maleAdult + Client.getOther(2) * femaleAdult + Client.getOther(3) * childOver + Client.getOther(4) * childUnder;
        return totalOthers;
    }

    public int calcCalories(){
        totalCalories = Client.getCalories(1) * maleAdult + Client.getCalories(2) * femaleAdult + Client.getCalories(3) * childOver + Client.getCalories(4) * childUnder;
        return totalCalories;  
    }

    public String createHamper(int index){
        
        hamper = new StringBuilder();
       
        finalHamper = hamperCombinations.get(index);

        for (int j = 0; j < finalHamper.size(); j++){
            Food food = finalHamper.get(j);
            int id = food.getFoodID();
            hamper.append(food.getFoodID()+"       "+food.getName()+ '\n');
            System.out.println(food.getName() + " with the ID " + food.getFoodID() + " has been removed from the inventory.");
            Inventory.removeFood(id);
         }
         

         return hamper.toString();
    }

    public int bestCombo(){
        int index =0;
        
        int min=100000000;

        for (int i = 0; i < hamperCombinations.size(); i++){
            LinkedList<Food> hamp = hamperCombinations.get(i);
        
            int total =0;
            for (int j = 0; j < hamp.size(); j++){
               Food food = hamp.get(j);
               total+=food.getTotalCalorie();
            }
            
            if (total < min ){
                min = total;
                index = i;
            }
        } 

        return index;
    }
    public void combinations(){
        LinkedList<Food> tempFoodList = Inventory.getFoodList();
       
       hamperCombinations = new LinkedList<LinkedList<Food>>();

       

        while (!tempFoodList.isEmpty()){
           
            int beginningSize = hamperCombinations.size();
            LinkedList<Food> hamperTest = new LinkedList<Food>();
            int tempWC =0;
            int tempP =0;
            int tempFV =0;
            int tempO =0;
            int tempC =0;
            for (int i = 0; i < tempFoodList.size(); i++){
                

                Food foodTest = tempFoodList.get(i);

                if (tempWC >= totalWheat && tempP  >= totalProtein && tempFV  >= totalFV && tempO  >= totalOthers&& tempC  >= totalCalories ){
                    hamperCombinations.add(hamperTest);
                //    System.out.println("leaves?");
                    break;
                }

                else if (tempWC >= totalWheat && tempP  >= totalProtein && tempFV  >= totalFV && tempO  >= totalOthers){
                    
                    int temp = totalCalories - tempC;
                    int foodIndex = getClosest(caloriesArrayNon0, temp);
                    int id = caloriesArrayNon0ID[foodIndex];
                    
                    for (int k = 0; k < tempFoodList.size(); k++){
                        
                       
                        Food food = tempFoodList.get(k);
                        int testIndex = food.getFoodID();
                        if (testIndex == id ){
                            
                            caloriesArrayNon0ID[foodIndex] = 1000000000;
                            caloriesArrayNon0[foodIndex] = 1000000000;
                            
                            tempWC += food.getGrainC();
                            tempWC += food.getGrainC();
                            tempP += food.getProteinC();
                            tempFV += food.getFruitVegC();
                            tempO += food.getOtherC();
                            tempC += food.getCalorie();
                            
                            hamperTest.add(food);
                        
                            
                            break;
                        }
                        caloriesArrayNon0ID[foodIndex] = 1000000000;
                        caloriesArrayNon0[foodIndex] = 1000000000;
                    }

                    
                }

                else if(tempC <= totalCalories && (tempWC <= totalWheat || tempP  <= totalProtein || tempFV  <= totalFV || tempO  <= totalOthers)){
            
                    
                    int foodIndex = getMin(caloriesArrayNon0);
                    int id = caloriesArrayNon0ID[foodIndex];
                    
                    for (int k = 0; k < tempFoodList.size(); k++){
                        
                        
                        Food food = tempFoodList.get(k);
                        int testIndex = food.getFoodID();
                      
                        if (testIndex == id ){
                           
                            caloriesArrayNon0ID[foodIndex] = 1000000000;
                            caloriesArrayNon0[foodIndex] = 1000000000;
                            
                            tempWC += food.getGrainC();
                            tempWC += food.getGrainC();
                            tempP += food.getProteinC();
                            tempFV += food.getFruitVegC();
                            tempO += food.getOtherC();
                            tempC += food.getCalorie();
                            
                            hamperTest.add(food);
                        
                            
                            break;
                        }
                        caloriesArrayNon0ID[foodIndex] = 1000000000;
                        caloriesArrayNon0[foodIndex] = 1000000000;
                        
                        

                    }
            
                }
                
                else{
                    

                }
            }
            int endSize = hamperCombinations.size();
     
            

            if (beginningSize == endSize){break;}
        }
        
       
    }

    public void listMaker(){

        

        LinkedList<Food> f = Inventory.getFoodList();
        int[] arr =  new int[Inventory.getFoodList().size()];
        int[] arr2 =  new int[Inventory.getFoodList().size()];
        
       
        caloriesArrayNon0 = ArrayMaker(f, arr, arr2, "calories", "c", "1");
        caloriesArrayNon0ID = ArrayMaker(f,arr, arr2, "calories", "id", "1");
        
       

    }

    public int[] ArrayMaker(LinkedList<Food> f, int[] array, int[] id, String str, String str1, String str2){
        int j = 0;
        int[] tempFood = new int[Inventory.getFoodList().size()];
        int[] tempID = new int[Inventory.getFoodList().size()];
        
        if (str.equals("grain")){
            for (int i = 0; i < f.size(); i++){
                Food l = f.get(i);
            
                if(str2.equals("1")){
                    if  (l.getGrainC() != 0){
                        tempFood[j] = l.getGrainC();
                        tempID[j] = l.getFoodID();
                        
                        j++;
                    }
                        
                }
                else{
                    if  (l.getGrainC() == 0){
                        tempFood[j] = l.getGrainC();
                        tempID[j] = l.getFoodID();
                        
                        j++;
                    }

                }
                
            }

        }

        else if (str.equals("FV")){
            for (int i = 0; i < f.size(); i++){
                Food l = f.get(i);
            
                if(str2.equals("1")){
                    if  (l.getFruitVegC() != 0){
                        tempFood[j] = l.getFruitVegC();
                        tempID[j] = l.getFoodID();
                        
                        j++;
                    }
                        
                }
                else{
                    if  (l.getFruitVegC() == 0){
                        tempFood[j] = l.getFruitVegC();
                        tempID[j] = l.getFoodID();
                        
                        j++;
                    }

                }
                
            }
        }

        else if (str.equals("protein")){
            for (int i = 0; i < f.size(); i++){
                Food l = f.get(i);
            
                if(str2.equals("1")){
                    if  (l.getProteinC() != 0){
                        tempFood[j] = l.getProteinC();
                        tempID[j] = l.getFoodID();
                        
                        j++;
                    }
                        
                }
                else{
                    if  (l.getProteinC() == 0){
                        tempFood[j] = l.getProteinC();
                        tempID[j] = l.getFoodID();
                        
                        j++;
                    }

                }
                
            }
        }

        else if (str.equals("others")){
            for (int i = 0; i < f.size(); i++){
                Food l = f.get(i);
            
                if(str2.equals("1")){
                    if  (l.getOtherC() != 0){
                        tempFood[j] = l.getOtherC();
                        tempID[j] = l.getFoodID();
                        
                        j++;
                    }
                        
                }
                else{
                    if  (l.getOtherC() == 0){
                        tempFood[j] = l.getOtherC();
                        tempID[j] = l.getFoodID();
                        
                        j++;
                    }

                }
                
            }
        }

        else if (str.equals("calories")){
            for (int i = 0; i < f.size(); i++){
                Food l = f.get(i);
            
                if(str2.equals("1")){
                    if  (l.getCalorie() != 0){
                        tempFood[j] = l.getCalorie();
                        tempID[j] = l.getFoodID();
                        
                        j++;
                    }
                        
                }
                else{
                    if  (l.getCalorie() == 0){
                        tempFood[j] = l.getCalorie();
                        tempID[j] = l.getFoodID();
                        
                        j++;
                    }

                }
                
            }
        }
        else{
            System.out.println("Wrong food type.");
        }

        if(str1.equals("c")){

            array = new int[j];
            array = Arrays.copyOfRange(tempFood, 0, j);
            return array;
        }
        
        else{
            id = new int[j];
            id = Arrays.copyOfRange(tempID, 0, j);
            return id;
        }

    }


    public int getMin(int[] array){
        int min = array[0];
        int index =0;
        for (int i = 0; i < array.length; i++){
            if (array[i]<min){
                min = array[i];
                index = i;
            }
        
        }
        


        return index;

    }

    public int getClosest(int[] array, int target){
        
        int index =0;
        int temp=0;
        int diff =0;
        int newDiff;
        

        for (int i = 0; i < array.length; i++){
            if(i==0) {
                diff =  Math.abs(target - array[i]) ;
                index = i;
            }

            else{
                newDiff =  Math.abs( target - array[i]);
                if (newDiff<diff){
                    index = i;
                }
            
            }
        }
        //System.out.println("indeexxxxxxxxxxxxxxxxxxxxxx       " + index + "            " + target);
        return index;

    }

    

}


    

    






























