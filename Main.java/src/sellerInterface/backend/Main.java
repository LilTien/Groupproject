package sellerInterface.backend;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        
        //Seller Interface 
        String userChoice;
        Scanner userInput = new Scanner(System.in);
        boolean isContinue = true;

        while(isContinue){
            System.out.println("----------------");
            System.out.println("SELLER PAGE");
            System.out.println("----------------");

            System.out.println("1.\tView Phone");
            System.out.println("2.\tFind Phone");
            System.out.println("3.\tAdd Phone");
            System.out.println("4.\tEdit Phone Data");
            System.out.println("5.\tDelete Phone");

            System.out.print("\nEnter your choice : ");
            userChoice = userInput.next();

            Phone phone1 = new Phone();

            switch (userChoice) {
                case "1":
                    System.out.println("\n______________");
                    System.out.println("List All Phone");
                    System.out.println("______________");
                    
                    phone1.showPhoneData();
                    break;
                case "2":
                    System.out.println("\n______________");
                    System.out.println("Find A Phone");
                    System.out.println("______________");
                    //FIND DATA
                    phone1.findData();
                    break;
                case "3":
                    System.out.println("\n______________");
                    System.out.println("Add Phone");
                    System.out.println("______________");
                    phone1.addData();
                    break;
                case "4":
                    System.out.println("\n______________");
                    System.out.println("Edit Phone Data");
                    System.out.println("______________");
                    phone1.updateData();
                    break;
                case "5" : 
                    System.out.println("\n______________");
                    System.out.println("Delete Phone on list");
                    System.out.println("______________");
                    phone1.deletePhoneData();
                    break;
                default:
                    System.err.println("INPUT IS NOT FOUND");
                    break;
            }
            isContinue = phone1.getYesorNo("Do you want to Continue : ");
        }
        
    }
    
}
