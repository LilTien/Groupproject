package sellerInterface.backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Phone {
    private String model; 
    private String variant;
    private float price;
    private String color;
    private String condition;
    private int stock;

    // for Trade in or sell with condition
    Phone(String model, String variant ,String color, String condition, float price){
        this.model = model;
        this.variant = variant;
        this.price = price;
        this.color = color;
        this.condition = condition;
    }
    // without condition
    Phone(String model, String variant, String color,float price ){
        this.model = model;
        this.variant = variant;
        this.price = price;
        this.color = color;
        this.condition = "NEW";
    }
    //default 
    Phone(){
        this.model = null;
        this.variant = null;
        this.price = 0.00f;
        this.color = null;
        this.condition = null;
    }
    //getter
    public String getModel(){
        return this.model;
    }
    public String getVariant(){
        return this.variant;
    }
    public String getColor(){
        return this.color;
    }
    public float getPrice(){
        return this.price;
    }
    public String getCondition(){
        return this.condition;
    }
    //to string with condition
    public String toString(){
        return "Model : "+this.model + "\nVariant : "+this.variant+"Color : "+this.color + "Condition : "+condition + "Price : RM"+this.price;
    }

    public static void showPhoneData() throws IOException{
        //take data from phoneData.txt and show them
        FileReader fileRead;
        BufferedReader bufferRead;

        try{
            fileRead = new FileReader("Z:\\visual code\\java\\Groupproject\\Main.java\\phoneData.txt");
            bufferRead = new BufferedReader(fileRead);
        }
        catch(Exception e){
            System.err.println("DATABASE NOT FOUND");
            System.err.println("PLEASE MAKE THE DATABASE FIRST !!!");
            addData();
            return;
            
        }
        System.out.println("Data Base Found !!!");
        System.out.println("\n|NO|\tMODEL\t\t|VARIANT|PRICE\t|\tCOLOR\t|CONDITION\t|STOCK\t");
        System.out.println("=================================================================================");
        String data = bufferRead.readLine();
        int i =0;
        while (data != null) {
            i++;
            StringTokenizer stringToken = new StringTokenizer(data, ",");
            //yang ni untuk primary key
            //stringToken.nextToken();
            System.out.printf("|%d ", i);
            //model
            System.out.printf("|%s", stringToken.nextToken());
            //variant
            System.out.printf("|%sGB\t", stringToken.nextToken());
            //price
            System.out.printf("|RM%s\t", stringToken.nextToken());
            //color
            System.out.printf("|%s\t", stringToken.nextToken());
            //condition
            System.out.printf("|%s\t", stringToken.nextToken());
            System.out.printf("|%s\t", stringToken.nextToken());
            System.out.println();
            data = bufferRead.readLine();
        }
        fileRead.close();
        bufferRead.close();
        System.out.println("================================================================================");
    }
    public static void findData() throws IOException{

        //read whether the database have or not
        try{
            File file = new File("Z:\\visual code\\java\\Groupproject\\Main.java\\phoneData.txt");
        }catch(Exception e){
            System.err.println("Data Base Not Found");
            System.err.println("PLEASE MAKE THE DATABASE FIRST");
            //addData();
            return;
        }
        //take keyword from user
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter keyword : ");
        String findString = userInput.nextLine();
        //Untuk split kan space yang user masuk so akan masuk ke array 0 and array 1
        String [] keyword = findString.split("\\s+");
        checkPhoneinDatabase(keyword,true);
    }
    public static boolean checkPhoneinDatabase(String [] keyword, boolean isDisplay)throws IOException{
        FileReader fileRead = new FileReader("Z:\\visual code\\java\\Groupproject\\Main.java\\phoneData.txt");
        BufferedReader bufferRead = new BufferedReader(fileRead);

        String data = bufferRead.readLine();
        boolean isExist = false;
        int totalData =0;
        if(isDisplay){
            System.out.println("\n|NO|\tMODEL\t\t|VARIANT\t|PRICE\t    |\tCOLOR\t|\tCONDITION|\tSTOCK");
            System.out.println("=====================================================================================================");
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
                    StringTokenizer stringToken = new StringTokenizer(data,",");
                    //stringToken.nextToken();
                    System.out.printf("|%d ", totalData);
                    //model
                    System.out.printf("|%s  ", stringToken.nextToken());
                    //variant
                    System.out.printf(" |%sGB   \t", stringToken.nextToken());
                    //price
                    System.out.printf("|RM%s  ", stringToken.nextToken());
                    //color
                    System.out.printf("|\t%s\t", stringToken.nextToken());
                    //condition
                    System.out.printf("|\t%s   ", stringToken.nextToken());
                    //STOCK
                    System.out.printf("|\t%s   ", stringToken.nextToken());
                    System.out.println();
                    
                }
            }

            data = bufferRead.readLine();
        }
        if(isDisplay){
            System.out.println("====================================================================================================");
        }
        bufferRead.close();
        fileRead.close();
        return isExist;
    }
    public static void addData() throws IOException{
        //true mean it will append to exist file instead of overwrite it
        FileWriter fileWrite = new FileWriter("Z:\\visual code\\java\\Groupproject\\Main.java\\phoneData.txt",true);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        Scanner userInput = new Scanner (System.in);
        String model, variant, color, condition;
        float price;
        int stock;
        //take input from user
        //"\n|NO|\tMODEL\t\t|VARIANT\t|PRICE\t    |\tCOLOR\t|\tCONDITION|"
        System.out.println("Enter phone model : ");
        model = userInput.nextLine();
        System.out.println("Enter phone variant : ");
        variant = userInput.nextLine();
        System.out.println("Enter phone Price : ");
        price = userInput.nextFloat();
        userInput.nextLine();
        System.out.println("Enter phone Color : ");
        color = userInput.nextLine();
        System.out.println("Enter phone Condition : ");
        condition = userInput.nextLine();
        System.out.println("Enter Stock : ");
        stock = userInput.nextInt();

        //check phone in database
        String [] keywords = {model+","+variant+","+price+","+color+","+condition};
        //System.out.println(Arrays.toString(keywords));

        boolean isExist = checkPhoneinDatabase(keywords, false);
        System.out.println(isExist);

        //write phone data in database
        if(!isExist){
            //Iphone 15 Pro Max,64,5990.00,Purple,NEW
            System.out.println("\nData that you enter is : ");
            System.out.println("=============================");
            System.out.println("Phone Model : " + model);
            System.out.println("Phone Variant : "+variant);
            System.out.println("Phone Price : "+ price);
            System.out.println("Phone Color : "+ color);
            System.out.println("Phone Condition : " + condition);

            boolean isAdd = getYesorNo("Do you want to add the data ? ");

            if (isAdd){
                bufferWrite.write(model+","+variant+","+price+","+color+","+condition+","+stock);
                bufferWrite.newLine();
                bufferWrite.flush();
            }

        }else{
            System.out.println("The data already have ");
            checkPhoneinDatabase(keywords, true);
        }

        bufferWrite.close();
        fileWrite.close();
    }
    //update stock
    public static void updateData() throws IOException{
        //take database original
        File dataBase = new File("Z:\\visual code\\java\\Groupproject\\Main.java\\phoneData.txt");
        FileReader fileRead = new FileReader(dataBase);
        BufferedReader bufferRead = new BufferedReader(fileRead);

        //make temporary database 
        File tempDB = new File("tempDb.txt");
        FileWriter fileWrite = new FileWriter(tempDB);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        //show all data
        System.out.println("List Phone");
        showPhoneData();

        //take user input
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter number data you want to update: ");
        int updateNum = userInput.nextInt();

        //show data that want to update
        String data = bufferRead.readLine();
        int entryCount =0;
        while (data != null){
            entryCount ++;

            StringTokenizer st = new StringTokenizer(data, ",");

            //show entry count == updateNum
            if(updateNum == entryCount){
                System.out.println("\nData you want to update is : ");
                System.out.println("=====================================");
                System.out.println("Phone Model : "+ st.nextToken());
                System.out.println("Phone Variant : "+ st.nextToken());
                System.out.println("Phone Price : "+ st.nextToken());
                System.out.println("Phone Color : "+ st.nextToken());
                System.out.println("Phone Condition : "+ st.nextToken());
                System.out.println("Phone Stock : "+ st.nextToken());

                //update data

                //take input from user
                //Iphone 15 Pro Max,64,5990.00,Purple,NEW,80
                String  [] fieldData = {"model", "variant", "price", "color","condition","stock"};
                String [] tempData = new String[6];

                //refresh token
                st= new StringTokenizer(data, ",");
                String originalData;
                for(int i =0; i < fieldData.length; i++){
                    originalData = st.nextToken();
                    //sampai ke stock
                    if(fieldData[i].equalsIgnoreCase("stock")){
                        boolean isUpdate = getYesorNo("Do you want to edit the " + fieldData[i]);
                        if(isUpdate){
                            userInput = new Scanner(System.in);
                            System.out.println("\nEnter "+fieldData[i]+" new data : ");
                            tempData[i]= userInput.nextLine();
                        }
                        else{
                            tempData[i] = originalData;
                        }
                        //sampai ke price
                    }else if(fieldData[i].equalsIgnoreCase("price")){
                        boolean isUpdate = getYesorNo("Do you want to edit the "+fieldData[i]);
                        if(isUpdate){
                            userInput = new Scanner(System.in);
                            System.out.print("Enter "+fieldData[i] +" new data : ");
                            tempData[i] = userInput.nextLine();
                        }
                        else{
                        tempData[i]= originalData;
                        }
                    }else{
                        tempData[i]= originalData;
                    }
                }
                //display new data
                //Iphone 15 Pro Max,64,5990.00,Purple,NEW,80
                st = new StringTokenizer(data, ",");
                System.out.println("\nNEW DATA");
                System.out.println("===================================");
                System.out.println("Phone Model        :" + tempData[0]);
                System.out.println("Phone Variant      :" + tempData[1]);
                System.out.println("Phone Price        :" + tempData[2]);
                System.out.println("Phone Color        :" + tempData[3]);
                System.out.println("Phone Condition    :" + tempData[4]);
                System.out.println("Phone Stock        :" + tempData[5]);

                boolean isUpdate = getYesorNo("Are you sure you want to update the data");

                if(isUpdate){
                    String tmodel = tempData[0];
                    String tvariant = tempData[1];
                    String tprice = tempData[2];
                    String tcolor = tempData[3];
                    String tcondition = tempData[4];
                    String tstock = tempData[5];

                    //write data in data base
                    bufferWrite.write(tmodel+","+tvariant+","+tprice+","+tcolor+","+tcondition+","+tstock);
                }
                else{
                    bufferWrite.write(data);
                }
            }else{
                bufferWrite.write(data);
            }
            bufferWrite.newLine();
            data = bufferRead.readLine();
        }

        //write data to file
        bufferWrite.flush();
        bufferRead.close();
        bufferWrite.close();
        fileRead.close();
        fileWrite.close();

        //delete original database
        dataBase.delete();
        //rename temporary file
        tempDB.renameTo(dataBase);

    }
    public static void deletePhoneData () throws IOException{
        //take database original
        //just read the file
        File dataBase = new File ("Z:\\visual code\\java\\Groupproject\\Main.java\\phoneData.txt");
        FileReader fileRead = new FileReader(dataBase);
        BufferedReader bufferRead = new BufferedReader(fileRead);

        //make temporary database 
        File tempData = new File("tempdb.txt");
        FileWriter fileWrite = new FileWriter(tempData);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        //show data
        System.out.println("List Phone ");
        showPhoneData();
        //take use input for delete data
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter no you want to delete : ");
        int deleteNum = userInput.nextInt();

        //loop to read every row data and skip data selected
        boolean isFound = false;
        int entryCounts = 0;

        String data = bufferRead.readLine();
        while (data != null) {
            entryCounts ++;
            boolean isDelete = false;
            StringTokenizer st = new StringTokenizer(data, ",");

            //show data that use want to delete
            if(deleteNum == entryCounts){
                System.out.println("Data you want to delete : ");
                System.out.println("=================================");
                System.out.println("Phone Model : "+ st.nextToken());
                System.out.println("Phone Variant : "+ st.nextToken());
                System.out.println("Phone Price : "+ st.nextToken());
                System.out.println("Phone Color : "+ st.nextToken());
                System.out.println("Phone Condition : "+ st.nextToken());
                System.out.println("Phone Stock : "+ st.nextToken());

                isDelete = getYesorNo("Do you want to delete :");
                isFound = true;
                
            }
            if(isDelete){
                //skip transfer data original to temporary 
                System.out.println("Data already deleted");
            }else{
                //transfer original data to temporary
                bufferWrite.write(data);
                bufferWrite.newLine();
            }

            data = bufferRead.readLine();
        }
        if(!isFound){
            System.err.println("DATA IS NOT FOUND");
        }

        //write data at file
        bufferWrite.flush();
        bufferWrite.close();
        bufferRead.close();
        fileRead.close();
        fileWrite.close();
        System.gc();
        //delete original data
        dataBase.delete();
        //rename temporary file to database
        tempData.renameTo(dataBase);

    }
    public static boolean getYesorNo(String massage){
        Scanner userInput = new Scanner(System.in);
        System.out.println("\n"+massage+"y/n");
        String userChoice = userInput.next();
        while(!userChoice.equalsIgnoreCase("y") && !userChoice.equalsIgnoreCase("n")){
            System.err.println("YOUR CHOICE IS NOT Y / N ");
            System.out.println("\n"+massage+"(Y/N)");
            userChoice = userInput.next();
        }
        return userChoice.equalsIgnoreCase("y");
    }
    
    
}
