
package travelagencysystem;


public class TestClass {
    
    public static void main(String[] args) {
        
        //ToString()
        TravelAgency ta = new TravelAgency("Overseas","Korea","Seoul","Normal");
        System.out.println(ta.toString());
        
        
        //ADD
        TravelAgency ta2 = new TravelAgency("Overseas","Korea","Daegu", 2, 4, "adult", 1499, "Peak");
        
        if(ta2.checkRepeat() == true){
            System.err.println("Data already exists!");
        }else{
            ta2.add();
        }

        //DELETE
        ta2.delete();
        
        //UPDATE
        TravelAgency ta3 = new TravelAgency(10,11,"Overseas","Korea","Daegu", 2, 4, "adult", 1499, "Peak");
        ta3.update();
    }  
}
