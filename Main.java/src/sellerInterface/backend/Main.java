package sellerInterface.backend;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        
        //Seller Interface 
        String userChoice;
        Scanner userInput = new Scanner(System.in);
        boolean isContinue = true;
        int buyerOrSeller, dbOrSell;
        Phone phone1 = new Phone();

        while(isContinue){

            System.out.println("1.Seller");
            System.out.println("2.Buyer");
            System.out.print("Your Option : ");
            buyerOrSeller = userInput.nextInt();

            switch (buyerOrSeller) {
                case 1:
                    System.out.println("\n1.Access Database");
                    System.out.println("2.Sell Phone");
                    dbOrSell = userInput.nextInt();
                    switch (dbOrSell) {
                        case 1:
                        System.out.println("----------------");
                        System.out.println("STORE DATABASE");
                        System.out.println("----------------");

                        System.out.println("1.\tView Phone");
                        System.out.println("2.\tFind Phone");
                        System.out.println("3.\tAdd Phone");
                        System.out.println("4.\tEdit Phone Data");
                        System.out.println("5.\tDelete Phone");

                        System.out.print("\nEnter your choice : ");
                        userChoice = userInput.next();

                        //String model, String variant, String color, String condition
                        
                        Sell cust1 = new Sell("Iphone 13","256","Black","4");

                        switch (userChoice) {
                            case "1":
                                System.out.println("\n______________");
                                System.out.println("List All Phone");
                                System.out.println("______________");
                                Phone.showPhoneData();
                                break;
                            case "2":
                                System.out.println("\n______________");
                                System.out.println("Find A Phone");
                                System.out.println("______________");
                                //FIND DATA
                                Phone.findData();
                                break;
                            case "3":
                                System.out.println("\n______________");
                                System.out.println("Add Phone");
                                System.out.println("______________");
                                Phone.addData();
                                break;
                            case "4":
                                System.out.println("\n______________");
                                System.out.println("Edit Phone Data");
                                System.out.println("______________");
                                Phone.updateData();
                                break;
                            case "5" : 
                                System.out.println("\n______________");
                                System.out.println("Delete Phone on list");
                                System.out.println("______________");
                                Phone.deletePhoneData();
                                break;
                            default:
                                System.err.println("INPUT IS NOT FOUND");
                                break;
                            
                        } 
                            break;
                        case 2:
                            int FindOrSell;
                            System.out.println("----------------");
                            System.out.println("SELL PHONE");
                            System.out.println("----------------\n");
                            System.out.println("1.Find phone Price");
                            System.out.println("2.Sell Phone");
                            FindOrSell = userInput.nextInt();
                            String model,variant, color, condition;
                            int cond =5;
                            userInput.nextLine();
                            switch (FindOrSell) {
                                case 1:
                                    System.out.println("=================");
                                    System.out.println("FIND PHONE PRICE");
                                    System.out.println("=================\n");
                                    System.out.print("Enter phone model : ");
                                    model = userInput.nextLine();
                                    System.out.println("Enter phone Variant : ");
                                    variant = userInput.nextLine();
                                    Sell find = new Sell(model,variant);
                                    find.findPhonePrice(true);
                                    break;
                                case 2:
                                    System.out.println("=================");
                                    System.out.println("SELL PHONE");
                                    System.out.println("=================\n");
                                    System.out.print("Enter phone model : ");
                                    model = userInput.nextLine();
                                    System.out.println("Enter Variant: ");
                                    variant = userInput.nextLine();
                                    System.out.println("Enter Color: ");
                                    color = userInput.nextLine();
                                    while(true){
                                        System.out.print("Enter condition : ");
                                        cond = userInput.nextInt();
                                        if(cond <= 10 && cond >=1){
                                            break;
                                        }
                                    }
                                    condition = String.valueOf(cond);
                                    Sell sell = new Sell(model, variant,color,condition);
                                    sell.findPhonePrice(false);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            System.out.println("WRONG INPUT");
                            break;
                    }
                    break;
                case 2: 
                    System.out.println("----------------");
                    System.out.println("SELL PHONE");
                    System.out.println("----------------\n");
                    break;
                default:
                System.out.println("WRONG INPUT");
                    break;
            }
            isContinue = Phone.getYesorNo("Do you want to Continue : ");

            
            
        }
        
    }
    
}
