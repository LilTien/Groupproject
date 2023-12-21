package sellerInterface.backend;

import java.util.Date;
public class Repair
{
    
    //attributes
    private int problem;
    private String time;
    //private double getPrice;
    
    //normal
    Repair (int problem, String time)
    {
        this.problem = problem;
        this.time = time;
        //this.getPrice = getPrice;
    }
    
    //setter
    public void setProblem(int problem){this.problem = problem;}
    public void setTime(String time){this.time = time;}
    //public void setGetPrice(double getPrice){this.getPrice = getPrice;}
    public void setRepair(int problem, String time)
    {
        this.problem = problem;
        this.time = time;
    }
    
    //getter
    public int getProblem(){return this.problem;}
    public String getTime(){return this.time;}
    //public double getGetPrice(){return this.getPrice;}    
    
    public String toString(){
        return ("Problem : " + problem + "\ntime : " + time);
    }
    //copy
    Repair (Repair repair){
        this.problem = repair.problem;
        this.time = repair.time;
        //this.getPrice = repair.getPrice;
    }

}
