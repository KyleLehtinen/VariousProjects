import java.util.Scanner;
import java.io.*;

/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Date Class
*Date: April 28, 2015
*Description: Loads a list of names and gives the user options to select to view the data
**********************************************************************/
public class NameApp {
	public static final String d1 = "1900-1909";
	public static final String d2 = "1910-1919";
	public static final String d3 = "1920-1929";
	public static final String d4 = "1930-1939";
	public static final String d5 = "1940-1949";
	public static final String d6 = "1950-1959";
	public static final String d7 = "1960-1969";
	public static final String d8 = "1970-1979";
	public static final String d9 = "1980-1989";
	public static final String d10 = "1990-1999";
	public static final String d11 = "2000-2005";
	public static final int pMax = 999; 
	public static final int nameCount = 4429;
	
	public static Scanner keyb = new Scanner(System.in);
	
    /**********************************************************************
    *Method: main
    *Description: Creates an array of Name objects from a txt file and shows the user a 
    * menu for selection, gets user selection and executes appropriate operations off this 
    * input. Operations include:
    * 	a - Prompt for a name and show a complete histogram graph for that name
    * 	b - Prompt for two names and a decade and display the histogram line for both names in 
    * 		that decade.
    * 	c - Prompt for a decade and display the top ten names (male/female) for that decade 
    * 		(20 result total).
    * 	d - Create an output file that lists all anomalies of input names file and exit application
    *Input: Menu selection, type char. Various inputs as needed by supporting methods
    *Output: Various output based on user selection
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    *  Set boolean stillRunning equal to true
    *  Create list as an array of names and set value equal to result of readNamesFile method
    *  Do the following while stillRunning is true
    *  Begin
    *  	Set selection equal to result of getSelection method
    *  	Switch to following operation depending on value of selection
    *  	Begin
    *  	 If case is 'a' run displayHistogram method
    *  	 If case is 'b' run compareTwoNames method
    *  	 If case is 'c' run displayTopTenNames method
    *  	 If case is 'd' run writeAnomaliesToFile method and reset stillRunning = false
    *  	End switch 
    *  End loop
    *  Display "Terminating... but first the anomalies in the data file:"
        	   "Anomalies written to anomalies.txt"
    * End
    **********************************************************************/ 
	public static void main(String[] args){
        char selection;
        boolean stillRunning = true;
        Name[] list = readNamesFile();

        do{
            selection = getSelection();
            switch(selection){
                case 'a':
                	displayHistogram(list);
                break;
                case 'b':
                	compareTwoNames(list);
                break;
                case 'c':
                	displayTopTenNames(list);
                break;
                case 'd':
                	writeAnomaliesToFile(list);
                	stillRunning = false;
                break;	
            }
        } while (stillRunning);
        
        System.out.println("\nTerminating... but first the anomalies in the data file:\n\n"
        		+ "Anomalies written to C:\\NamesTemp\\anomalies.txt");
    }
	
    /**********************************************************************
    *Method: getSelection
    *Description: Displays a menu and prompts user to make a selection from the menu, validates
    *	and returns input based off menu in displayMenu method
    *Input: User input for menu option, type char
    *Output: Returns validated user selection based off menu in displayMenu method
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    *  Run displayMenu method
    *  Prompt user for and set select equal to user specified value
    *  Reset select equal to value of select as lower case letter
    *  Repeat while select is not a character and select not equal to a,b,c, or d
    *  Begin
    *  	Display "Invalid input. Try again..."
    *  	Reset select equal to user specified value
    *  	Reset select equal to value of select as lower case letter
    *  End loop
    *  Return value of select
    * End
    **********************************************************************/ 
    public static char getSelection(){
        char select;
        
        displayMenu();
            select = keyb.next().charAt(0);
            select = Character.toLowerCase(select);
            while(!(Character.isLetter(select)) || (select != 'a' && select != 'b'
                    && select != 'c' && select != 'd')){
                System.out.println("Invalid input. Try again...");
                displayMenu();
                select = keyb.next().charAt(0);
                select = Character.toLowerCase(select);
            }
        return select;  
    }
    
    /**********************************************************************
    *Method: displayMenu
    *Description: Shows a menu that shows options for the user to use
    *Input: None
    *Output: None
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    *  Display "Enter the character corresponding to your selection:"
                "a - Print histogram for a name"
                "b - Compare two names in a decace"
                "c - Print top ten names for a decade"
                "d - Quit (display file anomalies)"
                "Your selection: "
    * End
    **********************************************************************/ 
    public static void displayMenu(){
        System.out.print("\nEnter the character corresponding to your selection:\n"
                + "\t\ta - Print histogram for a name\n\t\tb - Compare two names"
                + " in a decace\n\t\tc - Print top ten names for a decade\n\t\t"
                + "d - Quit (display file anomalies)\n\tYour selection: ");  
    }
    
    /**********************************************************************
    *Method: readNamesFile
    *Description: Reads contents of text file into an array of Name objects and sets their values
    *	based off data from text file
    *Input: Text file, retrieved from C:\temp\names.txt
    *Output: Array of 4429 Name objects
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    *  Set result equal to array of Name size 4429
    *  Try the following
    *  Begin
    *  	Set source equal to a File with contents of text file at C:\temp\names.txt
    *  	Set reader equal to Scanner object that takes value of source
    *  	Repeat 4429 times
    *  	Begin
    *  	 Set temp equal to the next value in reader
    *  	 Set iTemp equal to a new array of integers 11 long
    *  	 Repeat 11 times
    *  	 Begin
    *     Set iTemp at the next index equal to the next int value of reader
    *    End loop
    *    Set the value of result at current index equal to a new name object with parameters temp
    *    		and iTemp
    *    
    *  	End loop
    *  End
    *  If error caught
    *  Begin
    *  	Display "The file "names.txt" was not found. Please place this file in C:\temp\ directory."
    *  End
    *  Return value of result
    * End
    **********************************************************************/ 
    public static Name[] readNamesFile(){
    	String temp;
        int[] iTemp;
        int i;
        int j;
    	Name[] result = new Name[4429];
        
        try{
            File source = new File("C:\\temp\\names.txt");
            Scanner reader = new Scanner(source);
            
            for(i = 0; i < 4429; i++){
                temp = reader.next();
                iTemp = new int[11];
  
                for(j = 0; j < 11; j++)
                    iTemp[j] = reader.nextInt();
                
                result[i] = new Name(temp,iTemp);   
            }
            reader.close();
        }
        catch (IOException ioe){
            System.out.println("The file \"names.txt\" was not found. Please "
                    + "place this file in C:\\temp\\ directory.");
        }
        
        return result;
    }
    
    /**********************************************************************
    *Method: displayHistogram
    *Description: Displays the full histogram for a user selected name across all 11 decades
    *Input: None
    *Output: Full historgram for selected name, type String
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    *  Drop to next line
    *  Set searchName equal to value returned from requestName method
    *  Set nameIndex equal to value returned from searchName method with searchName variable
    *  If nameIndex is equal to -1
    *  Begin
    *  	Display "The name, " + searchName + ", was not found!"
    *  End
    *  Else
    *  Begin
    *  	Display histogram for user selected name
    *  End if conditions
    *  Drop to next line
    * End
    **********************************************************************/ 
    public static void displayHistogram(Name[] list){
        String searchName;
        int nameIndex;
    	
        System.out.println();
        
    	searchName = requestName();
    	nameIndex = searchName(searchName,list);
    	
    	if(nameIndex == -1){
    		System.out.println("The name, " + searchName + ", was not found!");
    	} else {
    		System.out.println("Histogram for name, " + list[nameIndex].getName());
        	System.out.println(list[nameIndex].getHistorgram());
    	}
    	System.out.println();
    }
    
    /**********************************************************************
    *Method: requestName
    *Description: Prompts user for a name and returns the user input
    *Input: User entered name as type String
    *Output: User entered name, type String
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    *  Display "Enter a name: "
    *  Set result equal to user specified value
    *  Return result
    * End
    **********************************************************************/ 
    public static String requestName(){
    	String result;
        
        System.out.print("\nEnter a name: ");
        result = keyb.next();

        return result;
    } 
    
    /**********************************************************************
    *Method: searchName
    *Description: Searches list array for given name parameter
    *Input: String for name, array of Names
    *Output: Result, type int
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    *  Set result equal to -1
    *  Repeat as many times as the length of list
    *  Begin
    *  	If the value of parameter name is equal to the result of getName at index
    *  	Begin
    *  	 Set result equal to index
    *  	End 
    *  End if conditions
    *  Return result
    * End
    **********************************************************************/   
    public static int searchName(String name, Name[] list){
        int i;
        int result = -1;
        
        for(i = 0; i < list.length && result == -1; i++){
            if(name.equalsIgnoreCase(list[i].getName()))
                result = i;
        }
        
        return result;
    }

    
    /**********************************************************************
    *Method: compareTwoNames
    *Description: Prompts user for two names and the decade to compare 
    *Input: array of Names, user entered values for requestName, searchName, getDecade method calls
    *Output: Displays histogram line comparison between two names user selects
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    * 	 Set name1 equal to value returned from requestName
    * 	 Set name1Idx equal to value returned from searchName
    * 	 Reset name1 equal to self with first letter capitalized
    * 	 If name1Idx is equal to -1
    * 	 Begin
    * 	 	Display "The name " + name1 + ", was not found!"
    * 	 End
    * 	 Else
    * 	 Begin
    * 	 	Set name2 equal to value returned from requestName
    * 		Set namd2Idx equal to value returned from searchName
    * 		Reset name2 equal to self with first letter capitalized
    * 		If name2Idx is equal to -1
    * 		Begin
    * 			Display "The second name, " + name2 + ", was not found!"
    * 		End
    * 		Else
    * 		Begin
    * 			Set decade equal to value returned from getDecade
    * 			Display comparison of name1 and name2 histogram lines for the
    * 			 selected decade
    * 		End if condition
    * 	 End if condition
    * End
    **********************************************************************/
    public static void compareTwoNames(Name[] list){
    	int name1Idx;
    	int name2Idx;
    	int decade;
    	String name1;
    	String name2;
    	
    	
    	name1 = requestName();
    	name1Idx = searchName(name1,list);
    	name1 = Character.toUpperCase(name1.charAt(0)) + name1.substring(1);
    	if(name1Idx == -1){
    		System.out.println("\nThe name, " + name1 + ", was not found!");
    	} else {
    		name2 = requestName();
    		name2Idx = searchName(name2,list);
    		name2 = Character.toUpperCase(name2.charAt(0)) + name2.substring(1);
    		if(name2Idx == -1){
        		System.out.println("\nThe second name, " + name2 + ", was not found!");
        	} else {
        		decade = getDecade(list);
        		System.out.println("\nData for " + name1 + "\n" + list[name1Idx].getHistoLine(decade)
        				+ "\nData for " + name2 + "\n" + list[name2Idx].getHistoLine(decade));
        	}
    	}
    }
    
    /**********************************************************************
    *Method: getDecade
    *Description: Prompts user to select a decade range and returns selection
    *Input: array of names
    *Output: Selected decade as int
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    * 	Set result equal to 1
    * 	Display list of decade ranges for user to select
    * 	Do following while result remains less than 1 or greater than 11
    * 	Begin
    * 		Repeat while keyb's tokaen is not an int
    * 		Begin
    * 			Display prompt for user to enter a valid integer value
    * 		End loop
    * 		Reset result equal to user given value
    * 		If result is less than 1 or greater than 11
    * 		Begin
    * 			Display prompt for user to enter an integer between 1 and 11
    * 		End if condition
    * 	End do loop
    * 	Return value of result
    * End
    **********************************************************************/
    public static int getDecade(Name[] list){
    	int result = 1;
    	
    	System.out.println("Enter the number corresponding to your decade:\n\t1 - " + d1
    			+ "\n\t2 - " + d2 + "\n\t3 - " + d3 +"\n\t4 - " + d4 + "\n\t5 - " + d5 + "\n\t"
    			+ "6 - " + d6 + "\n\t7 - " + d7 + "\n\t8 - " + d8 + "\n\t9 - " + d9 + "\n\t"
    			+ "10 - " + d10 + "\n\t11 - " + d11 + "\nEnter a decade: ");
    	do{
    		while(!keyb.hasNextInt()){
    			System.out.print("That is not an integer. Please try again.\nEnter a decade: ");
    			keyb.next();
    		}
    		result = keyb.nextInt();
    		if(result < 1 || result > 11)
    			System.out.print("\nEnter an integer between 1 and 11!\nEnter a decade: ");
    	} while (result < 1 || result > 11);

    	return result;
    }

    /**********************************************************************
    *Method: displayTopTenNames
    *Description: Requests user to select a decade, then after shows the top ten names by rank 1
    * through 10
    *Input: array of name, input to getDecade method
    *Output: List of top ten names in selected decade
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    * 	Set decade equal to value returned from getDecade
    * 	Display "Ten most popular names (male and female) during the decade " + convertDecade + "were:"
    * 	Display results of buildTopTen
    * End
    **********************************************************************/
    public static void displayTopTenNames(Name[] list){
    	int decade = getDecade(list);
    	System.out.print("\nTen most popular names (male and female) during the decade "
    			+ convertDecade(decade) + " were:\n");
    	System.out.println(buildTopTen(decade,list));
    }
    
    /**********************************************************************
    *Method: convertDecade
    *Description: Converts decade selection to actual year range for display purposes.
    *Input: user specified decade range as int
    *Output: Corresponding year range as String
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    * 	Set result equal to empty
    * 	Switch to following operation depending on value of decade
    * 	Begin
    * 		If case is 1 reset result equal to d1
    * 		If case is 2 reset result equal to d2
    * 		If case is 3 reset result equal to d3
    * 		If case is 4 reset result equal to d4
    * 		If case is 5 reset result equal to d5
    * 		If case is 6 reset result equal to d6
    * 		If case is 7 reset result equal to d7
    * 		If case is 8 reset result equal to d8
    * 		If case is 9 reset result equal to d9
    * 		If case is 10 reset result equal to d10
    * 		If case is 11 reset result equal to d11
    * 	End
    * 	Return result
    * End
    **********************************************************************/
    public static String convertDecade(int decade){
    	
    	String result = "";
    												
    	switch(decade){
    		case 1:
    			result = d1;
    		break;
    		case 2:
    			result = d2;
    		break;
    		case 3:
    			result = d3;
    		break;
    		case 4:
    			result = d4;
    		break;
    		case 5:
    			result = d5;
    		break;
    		case 6:
    			result = d6;
    		break;
    		case 7:
    			result = d7;
    		break;
    		case 8:
    			result = d8;
    		break;
    		case 9:
    			result = d9;
    		break;
    		case 10:
    			result = d10;
    		break;
    		case 11:
    			result = d11;
    		break;
    	}
    	
    	return result;
    }

    /**********************************************************************
    *Method: buildTopTen
    *Description: 
    *Input: 
    *Output: 
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    * End
    **********************************************************************/ 
    public static String buildTopTen(int decade, Name[] list){
    	String[] ttRank = new String[20];
    	int rankThreshold = 1;
    	int bestRank = 999;
    	int i;
    	int rankPlace = 1;
    	int lastPop;
    	int newPop = 0;
    	int listIdx = 0;
    	String lastPick = "";
    	String result = "";
    	
    	while(ttRank[ttRank.length - 1] == null){
    		bestRank = 999;
    		lastPop = newPop;

	    	for(i = 0; i < list.length - 1; i++){
	    		if(list[i].getPop(decade) < bestRank && list[i].getPop(decade) >= rankThreshold
	    			&& !(list[i].getName().equals(lastPick))){
	    				bestRank = list[i].getPop(decade);
		    			lastPick = list[i].getName();
		    			newPop = list[i].getPop(decade);
	    			}
	    		}

	    	if(newPop == lastPop)
	    		rankThreshold++;
	    	else{
	    		ttRank[listIdx] = lastPick;
	    		listIdx++;
	    	}
    	}
    	
    	for(i = 1; i < ttRank.length; i+= 2){
    		result += ttRank[i - 1] + "(" + rankPlace + ") \t" + ttRank[i] + "(" + rankPlace + ")\n";
    		rankPlace++;
    	}
    	return result;
    }
    
    /**********************************************************************
    *Method: writeAnomaliesToFile
    *Description: Scans through names file looking for anomalies and compiling all found to 
    *	a text file called anomalies.txt
    *Input: array of names
    *Output: text file called anomalies.txt containing all formatting errors
    * --------------------------------------------------------------
    * Pseudocode for this solution:
    * Begin
    * 	Set and finalize filePath equal to "C:\NamesTemp\"
    * 	Set and finalize fileName equal to value of filePath + "anomalies.txt"
    * 	Create, set, and finalize f as new File with value of fileName as parameter
    * 	Set tempIdx equal to 0
    * 	Set tempDecade equal to 0
    * 	Set errorCount equal to 0
    * 	Set matchCount equal to 0
    * 	Create f's directory if they do not exist
    * 	Try the following
    * 	Begin
    * 		Create and set log as a new PrintWriter for File f
    * 		Try the following
    * 		Begin
    * 			Repeat the following a 1000 times
    * 			Begin
    * 				Repeat the following 11 times
    * 				Begin
    * 					Repeat as many times as value of nameCount
    * 					Begin
    * 						If list at current index's population at current decade
    * 					 	  is equal to current rank
    * 						Begin
    * 							Increase matchCount by 1
    * 							Reset tempIdx to current index
    * 							Reset tempDecade to current decade
    * 						End if condition
    * 						if matchCount is equal to 2
    * 						Begin
    * 							Reset matchCount equal to 0
    * 						End 
    * 						Else
    * 						Begin
    * 							Increase errorCount by 1
    * 							If matchCount is equal to 0
    * 							Begin
    * 								Use log to "no names" message to anomalies.txt with decade
    * 							  	  and rank values
    * 							End
    * 							Else
    * 							Begin
    * 								Use log to print "One name" message to anomalies.txt with
    * 							  	  decade, name, and rank values
    * 							End if conditions
    * 							Reset matchCount equal to 0
    * 						End if conditions
    * 					End
    * 				End
    * 			End
    * 			Close log variable
    * 		End if exception caught
    * 		Begin
    * 			Display "the file could not be edited"
    * 		End
    * 	End if exception caught
    * 	Begin
    * 		Display "File could not be created"
    * 	End
    * End
    **********************************************************************/
    public static void writeAnomaliesToFile(Name[] list){
    	final String filePath = "C:\\NamesTemp\\";
    	final String fileName = filePath + "anomalies.txt";
    	final File f = new File(fileName);
    	int tempIdx = 0;
    	int tempDecade = 0;
    	int errorCount = 0;
    	int matchCount = 0;
    	
    	f.getParentFile().mkdirs();
    	try{
    		f.createNewFile();
    		try{
        		PrintWriter log = new PrintWriter(f);
        		
        		for(int i = 1; i < 1000; i++){ //choose rank
        			for(int k = 1; k < 12; k++){ //choose decade
        				for(int j = 0; j < nameCount; j++){ //
        					if(list[j].getPop(k) == i){
        						matchCount++;
        						tempIdx = j;
        						tempDecade = k;
        					}
        				}
        				if(matchCount == 2)
            				matchCount = 0;
            			else{
            				errorCount++;
            				if(matchCount == 0){
            					log.println(errorCount + " - No names for " + convertDecade(k)
            							+ ", rank " + i + ".");
            				} else {
            					log.println(errorCount + " - One name (" + list[tempIdx].getName()
            							+ ") for " + convertDecade(tempDecade) + ", rank " + i + ".");
            				}
            				matchCount = 0;
            			}
        			}
        		}
        		
        		log.close();
        	} catch(FileNotFoundException e){
        		System.out.println("The File could not be edited.");
        	}
    	} catch (IOException e){
    		System.out.println("File could not be created.");
    	}
    	
    }
}
