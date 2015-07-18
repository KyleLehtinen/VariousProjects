/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Date Class
*Date: April 28, 2015
*Description: This class contains methods to instantiate a name object with
* popularity rank, accessor to return variables, get histogram graph, etc.
**********************************************************************/
public class Name {
    private String name;
    private int[] popularityRanks;
    final int MAX_RANK_VAL = 999;
    
/**********************************************************************
*Method: Name (parameterized)
*Description: Creates new name object given name and array of popularity indexes
*Input: name, type string and int array of popularity ranks
*Output: None
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set name equal to given name
*   Set popularityRanks equal to given array of popularityRanks
* End
**********************************************************************/
    public Name(String name, int[] popularityRanks){
        this.name = name;
        this.popularityRanks = popularityRanks;
    }
    
    /**********************************************************************
*Method: getName
*Description: Return value of name variable
*Input: none
*Output: Name as String
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Return value of name
* End
**********************************************************************/
    public String getName(){
        return name;
    }
    
/**********************************************************************
*Method: getPop
*Description: Returns the popularity rank given a decade
*Input: Decade value, as int
*Output: Popularity ranking as int or -1 if invalid
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If decade is less than 1 or greater than 11
*   Begin
*       Set result equal to -1
*   End
*   Else
*   Begin
*       Set result equal to popularityRanks at position decade minus 1
*   End if condition
*   Return result
* End
**********************************************************************/    
    public int getPop(int decade){
        int result;
        
        if(decade < 1 || decade > 11)
            result = -1;
        else
            result = popularityRanks[decade - 1]; 
        
        return result;
    }
    
/**********************************************************************
*Method: getHistoLine
*Description: Returns histogram line representing popularity given the decade to 
* retrieve
*Input: Decade value, int 
*Output: String containing popularity rank and line of '*' to denote popularity rank
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set dots equal to ""
*   If decade is less than 1 or greater than 11
*   Begin
*       Set result equal to null
*   End
*   Else Begin
*       Set result equal to value of popularityRank at given decade + ": "
*       If the popularity rank at the given decade is not 0
*       Begin
*           Reset dots equal to "*"
*           Repeat while int i is greater than zero
*           Begin
*               Reset dots equal to own value + "*"
*           End loop
*       Reset result equal to formatted string of results + dots
*       End if conditions
*   End if conditions
*   Return result
* End
**********************************************************************/    
    public String getHistoLine(int decade){
        String result;
        String dots = "";
        
        if (decade < 1 || decade > 11)
            result = null;
        else {
            result = Integer.toString(this.getPop(decade)) + ": ";
            if(this.getPop(decade) != 0){
                dots = "*";
                for(int i = (MAX_RANK_VAL - this.getPop(decade)); i > 0; i -= 10)
                    dots += "*"; 
            }
            result = String.format("%5s%s",result,dots);
        }
        
        return result;
    }
    
/**********************************************************************
*Method: getHistogram
*Description: Return complete histogram for given name instance
*Input: None
*Output: result comprised of complete histogram data for name instance, String
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set result equal to ""
*   Repeat 11 times
*   Begin
*       Reset result equal to own value + returned value of getHistoLine at index i
*   End loop
*   Return result
* End
**********************************************************************/    
    public String getHistorgram(){
        String result = "";
        
        for(int i = 0; i < 11; i++)
            result += this.getHistoLine(i + 1) + "\n";
        
        return result;
    }
}
