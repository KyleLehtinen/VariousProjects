/*Kyle Lehtinen - an application that reads and displays data from a file 
containing a list of the 25 top selling video games ever released
*/
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

class dArray{
    public String sCountry;
    public String sGameName;
    public int iYear;
    public double dSold;
};

public class program3{
    
    public static Scanner keyb;
    public static void main(String[] args){
       
       keyb = new Scanner(System.in);
       int select,nRecord;
       
       dArray[] thisArray = new dArray[50];
       
       System.out.println("Welcome to the Data Reader Application!\nIn this "
               + "application we will look at a data file of the top 25 best "
               + "selling video game titles\never released.\n");
       
       nRecord = loadFile(thisArray);
       if(nRecord != 0)
           System.out.println("Data file loaded successfully!");
      
       do{
           displayMenu();
           select = keyb.nextInt();
           while(select < 1 && select > 4){
                System.out.print("\nSorry, you have made an invalid selection."+
                    " Please enter a selection between 1 and 5.");
                System.out.print("\nSelection: ");
                select = keyb.nextInt();
            }
           if (select == 1)
               displayData(nRecord,thisArray);
           else if (select == 2)
               searchData(nRecord, thisArray);
           else if (select == 3)
               calcAverageByYearRange(nRecord, thisArray);  
           else if(select == 4)
               displayHistogram(nRecord, thisArray);

       }
       while (select != 5);
       
       System.out.println("\nThank you for using Data Reader. Have a great day!");
       
    }
    
    //display main menu
    public static void displayMenu(){    
        System.out.print("\nPlease make a selection from the list of options"
                + " below:\n1. Display the list of data in a table."
                + "\n2. Search for a record in the table."
                + "\n3. Calculate the number of total games sold over a given "
                + "range of years."
                + "\n4. Display a histogram for the number of best selling"
                + " game titles released over a given "
                + "\n   range of years.\n5. Quit.");
        
        System.out.print("\nSelection: ");
    }
    
    //load data file to array
    public static int loadFile(dArray[] thisArray){
        int count = 0;
        try{
            File file = new File ("C:\\temp\\data.txt");
            Scanner inFile = new Scanner(file);
            do{
                thisArray[count] = new dArray();
                thisArray[count].sGameName = inFile.next();
                thisArray[count].iYear = inFile.nextInt();
                thisArray[count].dSold = inFile.nextDouble();
                thisArray[count].sCountry = inFile.next();
                count++;
            }
            while (!thisArray[count-1].sGameName.equals("EOF"));
            count--;
        }
        catch (IOException ioe){
            System.out.println("Cannot find file.");
            count = 0;
        }
        return count;
    }
    
    //display data in columns
    public static void displayData(int nRecord, dArray[] thisArray){
        int iHold = 0;
        System.out.println("\n-GameName-\t           -YearReleased-    "
                + "-NumberSold-\t-Region-");
        for(int ix = 0; ix < nRecord; ix++){
            System.out.printf("%-25s\t",thisArray[ix].sGameName);
            System.out.printf("%-4d\t\t",thisArray[ix].iYear);
            System.out.printf("%3.2f\t\t",thisArray[ix].dSold);
            System.out.printf("%-20s",thisArray[ix].sCountry);
            System.out.print("\n");
            iHold++;
            if(iHold%15 == 0 || iHold == nRecord){
                holdScreen(); 
                System.out.print("\n");  
            }
        }
    }
    
    //hold screen for input.
    public static void holdScreen(){
        String Wait;
        System.out.print("Enter any letter and press enter to continue:");
        Wait = keyb.next();
    }
    
    //search data array for given record
    public static void searchData(int nRecord, dArray[] thisArray){
        int ix = 0;
        String sSearch;
        boolean match = false;
        
        System.out.print("\nEnter the name of the video game you want to search"
                + " for: ");
        sSearch = keyb.next();

        while (!match && ix != nRecord){
            if(thisArray[ix].sGameName.equals(sSearch)){
                match = true;
                System.out.println("\n-GameName-\t           -YearReleased-    "
                + "-NumberSold-\t-Region-");
                System.out.printf("%-25s\t",thisArray[ix].sGameName);
                System.out.printf("%-4d\t\t",thisArray[ix].iYear);
                System.out.printf("%3.2f\t\t",thisArray[ix].dSold);
                System.out.printf("%-20s",thisArray[ix].sCountry);
                System.out.print("\n");
            }
            ix++;
        }
        if (!match)
            System.out.println("Sorry, that entry was not found.");     
    }
     
    //Calculate number of games sold over given range of years
    public static void calcAverageByYearRange(int nRecords, dArray[] thisArray){
        int iYearLow, iYearHigh, iRangeCount = 0;
        double dTotal = 0;
        float fAverage;
        
        System.out.print("\nPlease enter the starting year for this "
                + "calculation: ");
        iYearLow = keyb.nextInt();
        
        System.out.print("\nPlease enter the ending year for this calculation: ");
        iYearHigh = keyb.nextInt();
        while (iYearHigh < iYearLow){
            System.out.print("\nThe ending year in the range must be higher than"
                    + " the starting year!\nPlease enter a year higher than "
                    +iYearLow+": ");
            iYearHigh = keyb.nextInt();
        }
        //check array for years within the given range and total up numbers sold
        //specific to record setting games
        for (int ix = 0; ix < nRecords; ix++){
            if (thisArray[ix].iYear >= iYearLow && 
                    thisArray[ix].iYear <= iYearHigh ){
                iRangeCount++;
                dTotal += thisArray[ix].dSold;
            }
        }
        //flow control for whether or not data is found in given range
        if(dTotal == 0){
            System.out.print("No data was found between "+iYearLow+" and "
                    +iYearHigh+".\n");
        }
        else{
            fAverage = (float) dTotal / iRangeCount;
            System.out.printf("\nThe average number of record setting games sold"
                    + " between "+iYearLow+" and "+iYearHigh+" is %4.2f "
                    + "million copies.\n", fAverage);
        }
    }
    
    //display histogram for data spaced via a decade range calced from 
    //max,min divided by 10
    public static void displayHistogram(int nRecords, dArray[] thisArray){
        double min, max, dDecadeCalc;
        int iDecadeRange, iRunningDecade, temp;
        int[] decadeIndex;
        
        min = calcMinFor4(nRecords, thisArray);
        max = calcMaxFor4(nRecords, thisArray);
        
        dDecadeCalc = ((max - min)/10.0) + 1.0;
        iDecadeRange = (int) dDecadeCalc;
        
        decadeIndex = histogramDecade(nRecords, thisArray, min, iDecadeRange);
        iRunningDecade = (int) min;
        
        System.out.print("\nDecade Range : Count of Occurences");
        for (int ix = 0; ix < nRecords; ix++){
            if (!(iRunningDecade >= max)){
                System.out.print("\n"+iRunningDecade+" to "
                        +((iRunningDecade+iDecadeRange)-1)+"\t  "
                        +decadeIndex[ix]+" ");
                if(decadeIndex[ix] > 0){
                    for(int dx=0;dx<decadeIndex[ix];dx++){
                        System.out.print("*");
                    }
                }
                iRunningDecade += iDecadeRange;
            }
        }
        System.out.print("\n");
        holdScreen();
    }
    
    public static double calcMinFor4(int nRecords, dArray[] thisArray){
        double min;
        min = thisArray[0].iYear;
        for (int ix = 0; ix < nRecords; ix++){
            if(thisArray[ix].iYear < min)
                min = thisArray[ix].iYear;
        }
        return min;
    }
    
    public static double calcMaxFor4(int nRecords, dArray[] thisArray){
        double max;
        max = thisArray[0].iYear;
        for (int ix = 0; ix < nRecords; ix++){
            if (thisArray[ix].iYear > max)
                max = thisArray[ix].iYear;
        }
        return max;
    }
    
    public static int[] histogramDecade(int nRecords, dArray[] thisArray, 
           double min, int iDecadeRange){
        
        int[] indexArray = new int[nRecords];
        int temp = 0;
        for (int ix = 0; ix < nRecords; ix++)
        {
           temp = (int)((thisArray[ix].iYear - min))/iDecadeRange;
           indexArray[temp]++;
        }
        
        return indexArray;
    }
    /*
    public static int[] histogramDecade(int nRecords, dArray[] thisArray, 
           double min, double max, int iDecadeRange){
        int iDecadeCount = 0,dRunningDecade;
        int[] iDecadeIndex = new int[nRecords];

        dRunningDecade = (int) min;

        for (int ix = 0; ix < nRecords; ix++){
            for (int dx = 0; dx < nRecords; dx++){
                if (thisArray[dx].iYear >= dRunningDecade && thisArray[dx].iYear
                        < (dRunningDecade + iDecadeRange))
                            iDecadeCount++;   
            }
            iDecadeIndex[ix] = iDecadeCount;
            dRunningDecade += iDecadeRange;
            iDecadeCount = 0;
        }

        return iDecadeIndex;
    }
    */
}