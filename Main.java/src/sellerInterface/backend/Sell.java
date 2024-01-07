package sellerInterface.backend;

/*import static sellerInterface.backend.Phone.color;
import static sellerInterface.backend.Phone.model;
import static sellerInterface.backend.Phone.stock;
import static sellerInterface.backend.Phone.variant;*/

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Sell extends Phone{
    /*
    private String model; 
    private String variant;
    private float price;
    private String color;
    private int condition;
     */
    //private int condition;
    
    private static double sellPrice;
    //default constructor
    Sell(){
        super(null,null,null,null);
    }
    //normal
    //String model, String variant, String color,String condition
    Sell(String model, String variant,String color,String condition){
        super(model, variant, color,condition);
    }
    //model only for search price
    Sell(String model, String variant){
        super(model,variant,null,null);
    }

    public static void findPhonePrice(boolean showData) throws IOException{
        //read whether the database have or not
        try{
            File file = new File("Z:\\visual code\\java\\Groupproject\\Main.java\\sellPrice.txt");
        }catch(Exception e){
            System.err.println("Data Base Not Found");
            System.err.println("PLEASE MAKE THE DATABASE FIRST");
            //addData();
            return;
        }
        //take keyword from user
        Scanner userInput = new Scanner(System.in);
        String m,v;
        //System.out.print("Enter phone model: ");
        m = getModel();
        //System.out.print("Enter variant: ");
        v = getVariant();
        String findString = m+" "+v;
        //Untuk split kan space yang user masuk so akan masuk ke array 0 and array 1
        String [] keyword = findString.split("\\s+");
        checkPhonePriceinDatabase(keyword,showData);
        
    }
    public static boolean checkPhonePriceinDatabase(String [] keyword, boolean isDisplay)throws IOException{
        FileReader fileRead = new FileReader("Z:\\visual code\\java\\Groupproject\\Main.java\\sellPrice.txt");
        BufferedReader bufferRead = new BufferedReader(fileRead);

        String data = bufferRead.readLine();
        boolean isExist = false;
        int totalData =0;
        if(isDisplay){
            System.out.println("\n|NO|\tMODEL\t\t|VARIANT\t|PRICE\t    |");
            System.out.println("=========================================");
        }
        while(data != null){
            //check keyword inside the line
            isExist = true;
            for(String keywords: keyword){
                isExist = isExist && data.toLowerCase().contains(keywords.toLowerCase());
            }
            if(isExist){
                if(isDisplay){
                    totalData++;
                    StringTokenizer stringToken = new StringTokenizer(data,";");
                    //stringToken.nextToken();
                    System.out.printf("|%d ", totalData);
                    //model
                    System.out.printf("|%s  ", stringToken.nextToken());
                    //variant
                    System.out.printf(" |%sGB   \t", stringToken.nextToken());
                    //price
                    System.out.printf("|RM%s  ", stringToken.nextToken());
                    System.out.println();
                    break; 
                }else{
                    Scanner scan = new Scanner(data);
                    scan.useDelimiter(";");
                    for(int i =0; i < 2; i++){
                        scan.next();
                    }
                    double price = scan.nextDouble();
                    //System.out.println(price);
                    sellPrice(price);
                    break;
                }
            }

            data = bufferRead.readLine();
        }
        if(isDisplay){
            System.out.println("=========================================");
        }
        bufferRead.close();
        fileRead.close();
        return isExist;
    }
    public static void sellPrice(double price) throws IOException{
        double endPrice ;
        Integer condition = Integer.valueOf(getCondition());
        //System.out.println(condition);
        endPrice = price-(1000-(condition*100));
        System.out.println("Total Price : RM" + endPrice);
        boolean approve = getYesorNo("Approve: ");
        if(approve){
            try{
                FileWriter write = new FileWriter("Z:\\visual code\\java\\Groupproject\\Main.java\\phoneData.txt",true);
                BufferedWriter buffWrite = new BufferedWriter(write);

                String [] keywords = {getModel()+","+getVariant()+","+endPrice+","+getColor()+","+condition};

                boolean isExist = checkPhoneinDatabase(keywords, false);

                if(!isExist){
                    System.out.println("\nData that you enter is : ");
                    System.out.println("=============================");
                    System.out.println("Phone Model : " + getModel());
                    System.out.println("Phone Variant : "+getVariant());
                    System.out.println("Phone Price : "+ endPrice);
                    System.out.println("Phone Color : "+ getColor());
                    System.out.println("Phone Condition : " + condition);

                    boolean isAdd = getYesorNo("Do you want to add the data ? ");

                    if (isAdd){
                        buffWrite.write(getModel()+","+getVariant()+","+price+","+getColor()+","+condition+"/10,"+1);
                        buffWrite.newLine();
                        buffWrite.flush();
                    }
                }
                else{
                    System.out.println("The data is already there you should update the stock");
                }
                buffWrite.close();
                write.close();
            }catch(Exception e){
                System.err.println("Error");
            }
        }
    }
}
