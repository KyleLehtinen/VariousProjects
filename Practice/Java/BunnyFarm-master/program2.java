//Kyle Lehtinen - Program 2 - Bunny Farm population calculator

import java.util.Scanner;

public class program2{
    
    public Scanner keyboard;
    
    public static void main(String[] args){
        
    Scanner keyboard = new Scanner(System.in);

    int iCalcChoice,iGivenPop,iNumMonths,iInitialPop,iGivenTime,iFinalPop;
    double dMaxPop,dBirthRate,dDeathRate,dGrowthRate;
    char cAgain,cNew,cShowTable;
    boolean boolShowTable;

    System.out.print("\n-Bunny Farm Population Calculator-\n");

    //Start loop segment for farm
    do{
        System.out.println("\nWe will start by gathering some required"
            + " information about the farm.");

        System.out.print("\nPlease enter the maximum population (or carrying " +
                "capacity) for this farm: ");
        dMaxPop = keyboard.nextDouble();
        while (dMaxPop < 1){
            System.out.print("Sorry, the maximum population must be a posi" +
                    "tive number. \nPlease enter the maximum population: ");
            dMaxPop = keyboard.nextDouble();
        }
        
        //start loop segment for multiple calculations of a farm
        do{
            System.out.print("\nPlease enter the starting population of this "+
                    "farm: ");
            iInitialPop = keyboard.nextInt();
            while (iInitialPop < 1 ){
                System.out.println("Sorry, the starting population of this "+
                        "farm must be less than the carrying capacity ("+dMaxPop
                        +")\nand greater than zero. Please enter a starting"+
                        " population within that range: ");
                iInitialPop = keyboard.nextInt();
            }
            
            System.out.print("\nNext, please enter the birth rate of this far"+
                    "m as a percentage equal to or greater than 0%: ");
            dBirthRate = keyboard.nextDouble();
            while (dBirthRate < 0){
                System.out.print("\nThe birth rate must be greater at least 0%:"+
                        ". \nPlease enter enter a number in that range: ");
                dBirthRate = keyboard.nextDouble();
            }
            //convert from to real value for formula
            dBirthRate = dBirthRate * .01;
            
            System.out.print("\nNext, please enter the expected death rate of "+
                    "this farm as a percentage (between 0% and 100%): ");
            dDeathRate = keyboard.nextDouble();
            while (dDeathRate < 0 || dDeathRate > 100){
                System.out.print("\nThe death rate must be between 0% and 100%"+
                        ". \nPlease enter enter a number in that range: ");
                dDeathRate = keyboard.nextDouble();
            }
            //convert from to real value for formula
            dDeathRate = dDeathRate * .01;
            
            //assign growth rate value based on given birth and death values
            dGrowthRate = dBirthRate - dDeathRate;

            //prompt user to chose calculation type from a menu with error check
            System.out.print("\nPlease choose which calculation you would like"+
                    " to perform with this farm:\n1) Calculate "+
                    "the number of months needed to achieve a given populat"+
                    "ion\n2) Calculate the expected p"+
                    "opulation over a given number of months\nSelect: ");
            iCalcChoice = keyboard.nextInt();
            while (iCalcChoice != 1 && iCalcChoice != 2){
            System.out.print("That is an invalid selection. Please review the "+
                    "choices above and enter 1 or 2 to make your selection.\n"+
                    "Select: ");
            iCalcChoice = keyboard.nextInt();    
            }
            
            //enable/disable table of results
            System.out.print("\nWould you like the results of this calculation"+
                    " displayed in a table? (y/n): ");
            cShowTable = keyboard.next().charAt(0);
            while(cShowTable != 'y' && cShowTable != 'n'){
                System.out.print("That is an invalid selection. Please choose"+
                    " y or n to make your selection: ");
               cShowTable = keyboard.next().charAt(0);
            }
            if (cShowTable == 'y')
                boolShowTable = true;
            else
                boolShowTable = false;
            
            //calc branch
            if (iCalcChoice == 1){
                System.out.print("\nPlease enter the desired bunny population "+
                        "to achieve: ");
                iGivenPop = keyboard.nextInt();
                while(iGivenPop > (int)dMaxPop || iGivenPop <= (int)iInitialPop){
                    System.out.print("That is an invalid selection. Please "+
                        "enter a population that is less than the farms "+
                        "maximum\n population ("+dMaxPop+") and greater than "+
                        "the initial population ("+iInitialPop+"): ");
                    iGivenPop = keyboard.nextInt();
                }
                
                iNumMonths = calcTimeToPop(iGivenPop,dMaxPop,iInitialPop,
                        dGrowthRate,boolShowTable);
                
                System.out.println("It will take "+iNumMonths+" months to "+
                        "achieve a bunny population of "+iGivenPop+".");
            }
            else{
                System.out.print("\nPlease enter the number of months this "+
                "bunny farm will operate: ");
                iGivenTime = keyboard.nextInt();
                while(iGivenTime < 1){
                    System.out.print("\nThat is not a positive number of "+
                    "months. Please enter a positive number for the\nnumber"+
                    " of months this bunny farm will operate: ");
                    iGivenTime = keyboard.nextInt();
                }
                
                iFinalPop = calcPopOverTime(iGivenTime,dMaxPop,iInitialPop,
                        dGrowthRate,boolShowTable);
                System.out.print("\nAfter "+iGivenTime+" months the farm will"+
                        " have "+iFinalPop+" bunnies.\n");
            }
            
            System.out.print("\nWould you like to perform another calculation "+
                    "with this farm? (y/n): ");
            cAgain = keyboard.next().charAt(0);
            while (cAgain != 'y' && cAgain != 'n'){
            System.out.print("Sorry, that is an invalid selection. Please "+
                "enter either y or n to make your selection: ");
            cAgain = keyboard.next().charAt(0);    
            }
        
        }
        while (cAgain != 'n');
        
        System.out.print("\nWould you like to perform a calculation with a new"+
                " farm? (y/n): ");
        cNew = keyboard.next().charAt(0);
        while (cNew != 'y' && cNew != 'n'){
            System.out.print("Sorry, that is an invalid selection. Please "+
                "enter either y or n to make your selection: ");
            cNew = keyboard.next().charAt(0);
        }
    }
    while (cNew != 'n');
    
    System.out.println("\nThank you for using this application. Have a great "+
            "day!");
}
    
    //sub to calculate for time till a given population is reached
    public static int calcTimeToPop(int iGivenPop,double dMaxPop,
        int iInitialPop,double dGrowthRate,boolean boolShowTable){
        
        int iRealPop,iCurrentPop,iMonth;
        double dRelativePop,dNewPop;
       
        iMonth = 0;
        iCurrentPop = iInitialPop;
        
        if(boolShowTable)
            System.out.println("\nMonth\tPopulation");
        
        while(iCurrentPop < iGivenPop){

            if(boolShowTable)
                System.out.println(iMonth+"\t"+iCurrentPop);
            
            dRelativePop = (iCurrentPop/dMaxPop);
            dNewPop = dRelativePop + dGrowthRate * dRelativePop * 
                    (1.0 - dRelativePop);
            iRealPop = (int) Math.round(dNewPop * dMaxPop);
            
            iCurrentPop = iRealPop;
            iMonth++;   
        }
        
        return iMonth;
    }
    
    //sub to calculate the population in given amount of time
    public static int calcPopOverTime(int iGivenTime,double dMaxPop,
        int iInitialPop,double dGrowthRate,boolean boolShowTable){
        
        int iRealPop,iCurrentPop,iMonth;
        double dRelativePop,dNewPop;
        
        iCurrentPop = iInitialPop;
        
        if(boolShowTable)
            System.out.println("\nMonth\tPopulation");
        
        for(int i = 0; i < iGivenTime; i++){
            if(boolShowTable)
                System.out.println(i+"\t"+iCurrentPop);
            
            dRelativePop = (iCurrentPop/dMaxPop);
            dNewPop = dRelativePop + dGrowthRate * dRelativePop * 
                    (1.0 - dRelativePop);
            iRealPop = (int) Math.round(dNewPop * dMaxPop);
            
            iCurrentPop = iRealPop;
        }
        
        return iCurrentPop;
    }
}