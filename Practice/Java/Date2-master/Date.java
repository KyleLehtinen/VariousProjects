/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Date Class
*Date: April 2, 2015
*Description: This class contains methods to instantiate a calendar date, get 
* parts of the date, reset parts of the date, and measure days in a date, etc.
**********************************************************************/
public class Date {
    private int month;
    private int day;
    private int year;
    public static final int JAN = 1;
    public static final int FEB = 2;
    public static final int MAR = 3;
    public static final int APR = 4;
    public static final int MAY = 5;
    public static final int JUN = 6;
    public static final int JUL = 7;
    public static final int AUG = 8;
    public static final int SEP = 9;
    public static final int OCT = 10;
    public static final int NOV = 11;
    public static final int DEC = 12;
    
/**********************************************************************
*Method: Date (empty constructor)
*Description: Creates new instance of a Date object with default values
*Input: None
*Output: None
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set month = JAN
*   Set day = 1
*   Set year = 2000
* End
**********************************************************************/
    public Date(){
        month = JAN;
        day = 1;
        year = 2000;
    }
    
/**********************************************************************
*Method: Date (Parameter Constructor)
*Description: Creates new Date object with specified day, month, and year
*Input: integer values for month, day and year
*Output: none
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If the user given value for year is valid
*       Set year = y
*   End if condition
*   If the user given value for month is valid
*       Set month = m
*   End if condition
*   If the user given value for day is valid
*       Set day = d
*   End if condition
* End
**********************************************************************/
    public Date(int m, int d, int y){
        
        if (validateYear(y))
            year = y;
        
        if(validateMonth(m,d,y))
            month = m;
        
        if (validateDay(m,d,y))
            day = d;
    }

/**********************************************************************
*Method: Date (Object Constructor)
*Description: Creates new Date object based on the values of a given Date object
*Input: User given Date object
*Output: None
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set this month, day, and year to values of given Date d
* End
**********************************************************************/
    public Date(Date d){
        this(d.getMonth(),d.getDay(),d.getYear());
    }
    
/**********************************************************************
*Method: getMonth
*Description: Returns month value
*Input: None
*Output: Value of month
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Return value of month
* End
**********************************************************************/    
    public int getMonth(){
        return month;
    }    

/**********************************************************************
*Method: getDay
*Description: Returns value of day
*Input: none
*Output: Value of day
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Return value of day
* End
**********************************************************************/
    public int getDay(){
        return day;
    }

/**********************************************************************
*Method: getYear
*Description: Returns value of year
*Input: none
*Output: Value of year
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Return value of year
* End
**********************************************************************/
    public int getYear(){
        return year;
    }
    
/**********************************************************************
*Method: setMonth
*Description: Resets value of month to user specified value
*Input: New month value m, type int
*Output: none
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If user provided value m is a valid month
*       Reset month = m
* End
**********************************************************************/    
    public void setMonth(int m){
        if(validateMonth(m,day,year))
            month = m;
    }
    
/**********************************************************************
*Method: setDay
*Description: Resets value of day to user specified value
*Input: New day value d, type int
*Output: none
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If user provided value d is a valid day
*       Reset day = d
* End
**********************************************************************/
    public void setDay(int d){
        if (validateDay(month,d,year))
            day = d;
    }
    
/**********************************************************************
*Method: setYear
*Description: Resets value of year to user specified value
*Input: New year value y, type int
*Output: none
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If user provided value y is a valid year
*       Reset year = y
* End
**********************************************************************/
    public void setYear(int y){
        if (validateYear(y))
            year = y;
    }

/**********************************************************************
*Method: toString
*Description: Outputs month, day, year in set format for display output
*Input: none
*Output: Formatted string
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set monthName = "JAN"
*   if the month value is equal to FEB
*       Reset monthName = "FEB"
*   else if the month value is equal to FEB
*       Reset monthName = "FEB"
*   else if the month value is equal to MAR
*       Reset monthName = "MAR"
*   else if the month value is equal to APR
*       Reset monthName = "APR"
*   else if the month value is equal to MAY
*       Reset monthName = "MAY"
*   else if the month value is equal to JUN
*       Reset monthName = "JUN"
*   else if the month value is equal to JUL
*       Reset monthName = "JUL"
*   else if the month value is equal to AUG
*       Reset monthName = "AUG"
*   else if the month value is equal to SEP
*       Reset monthName = "SEP"
*   else if the month value is equal to OCT
*       Reset monthName = "OCT"
*   else if the month value is equal to NOV
*       Reset monthName = "NOV"
*   else if the month value is equal to DEC
*       Reset monthName = "DEC"
*   
*   Return formatted string in form on MMM-DD-YYYY
* End
**********************************************************************/
    public String toString(){
        String monthName = "JAN";

        if (month == FEB)
            monthName = "FEB";
        else if (month == MAR)
            monthName = "MAR";
        else if (month == APR)
            monthName = "APR";
        else if (month == MAY)
            monthName = "MAY";
        else if (month == JUN)
            monthName = "JUN";
        else if (month == JUL)
            monthName = "JUL";
        else if (month == AUG)
            monthName = "AUG";
        else if (month == SEP)
            monthName = "SEP";
        else if (month == OCT)
            monthName = "OCT";
        else if (month == NOV)
            monthName = "NOV";
        else if (month == DEC)
            monthName = "DEC";
        
        return String.format("%s-%02d-%04d",monthName,day,year);
    }
    
/**********************************************************************
*Method: equals
*Description: Checks to see if user given Date object is equal exactly to this 
*   Date object
*Input: User given Date object
*Output: True if equal, false if not equal
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If this Date object's day is equal to user given Date object's day
*     and this Date object's month is equal to user given Date object's month
*     and this Date object's year is equal to user given Date object's year
*       Return true
*   else
*       return false
* End
**********************************************************************/
    public boolean equals(Date other){
        if (this.day == other.day && 
            this.month == other.month && 
            this.year == other.year)
            return true;
        else
            return false;
    }
    
/**********************************************************************
*Method: getDayNumber
*Description: Returns the day number of the current date
*Input: none
*Output: Day number of the year specified in this date instance, type int
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set result = 0
*   Repeat as many times as the value of month - 1
*   Begin
*       If the month is 4, 6, 9, or 11
*           Reset result equal to result plus 30
*       else if month is 2
*       Begin
*           If the year is a leap year
*               Reset result equal to result plus 29
*           else
*               Reset result equal to result plus 28
*       End
*       Else
*           Reset result equal to result plus 31
*       End if conditions
*   End loop
*   Reset result equal to result plus value of day in this instance
* End
**********************************************************************/
    public int getDayNumber(){
        int result = 0;
        
        for (int i = 1; i < month; i++){
            if (i == 4 || i == 6 || i == 9 || i == 11)
                result = result + 30;
            else if (i == 2){
                if(checkLeapYear(year))
                    result = result + 29;
                else
                    result = result + 28;
            }
            else
                result = result + 31;
        }
        
        result = result + day;
        
        return result;
    }

/**********************************************************************
*Method: addDays
*Description: Add a positive or negative number of days to the date instance to 
* change the date to a future or earlier date.
*Input: Number of days increase or decrease by, type int
*Output: None
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set goal = number of days from year zero for this instance plus value of amt
*   If amt is less than 0
*   Begin
*       Set nYear = 0
*       Set nMonth = 0
*       Set nDay = 1
*       Set totalDays = 1
*   End
*   Else
*   Begin
*       Set nYear equal to this instances year
*       Set nMonth equal to this instances month
*       Set nDay equal to this instances day
*       Set totalDays equal to number of days from year zero to this instances date
*   End
*   End If conditions
*   Repeat while value of totalDays is less than goal
*   Begin
*       If nYear is leap year and sum of goal - totalDays is greater than 365 OR
*         nYear is not a leap year and sum of goal - totalDays is greater or equal
*         to 365
*       Begin
*           If nYear is a leap year
*               Reset totalDays equal to self + 366
*           Else
*               Reset totalDays equal to self + 365
*           End if conditions
*           Increment nYear by 1
*       End
*       Else if sum of goal - totalDays is greater than 31
*       Begin
*           If nMonth is 4, 6, 9, or 11
*               Reset totalDays equal to self plus 30
*           Else if nMonth is 2
*           Begin
*               If nYear is a leap year
*                   Reset totalDays equal to self plus 29
*               Else
*                   Reset totalDays equal to self plus 28
*           End
*           Else
*               Reset totalDays equal to self plus 31
*           End if conditions
*           Increment nMonth by 1
*           If nMonth is greater than 12
*           Begin
*               Reset nMonth equal to 1
*               Increment nYear by 1
*           End if condition
*       End if condition
*       Else
*       Begin
*           Repeat while int i is greater than zero starting with sum of goal minus
*             totalDays and decrementing i by 1 with each pass
*           Begin
*               Increment nDay by 1
*               If nMonth is equal to 2
*               Begin
*                   If nYear is a leap year
*                   Begin
*                       If nDay is greater than 29
*                       Begin
*                           Reset nDay equal to 1
*                           Increment nMonth by 1
*                       End if condition
*                   End
*                   Else
*                   Begin
*                       If nDay is greater than 28
*                       Begin
*                           Reset nDay equal to 1
*                           Increment nMonth by 1
*                       End if condition
*                   End if condition
*               End
*               Else if nMonth is equal to 4, 6, 9, or 11
*               Begin
*                   If nDay is greater than 30
*                   Begin
*                       Reset nDay equal to 1
*                       Increment nMonth by 1
*                   End if condition
*               End
*               Else
*               Begin
*                   If nDay is greater than 31
*                   Begin
*                       Reset nDay equal to 1
*                       Increment nMonth by 1
*                       If nMonth is greater than 12
*                       Begin
*                           Reset nMonth equal to 1
*                           Increment nYear by 1
*                       End if condition
*                   End if Condition
*               End if Condition
*           End loop
*       Reset totalDay equal to self plus sum of goal minus totalDays
*     End if condition
*   End loop
*   Reset year = nYear
*   Reset month = nMonth
*   Reset day = nDay
* End
**********************************************************************/
    public void addDays(int amt){
       int nYear;
       int nMonth;
       int nDay;
       int totalDays;
       int goal = this.daysFromZero() + amt;
       
       if (amt < 0){
           nYear = 0;
           nMonth = 1;
           nDay = 1;
           totalDays = 1;
       } else{
           nYear = year;
           nMonth = month;
           nDay = day;
           totalDays = this.daysFromZero();
       }
       
       while(totalDays < goal){
           if((checkLeapYear(nYear) && ((goal - totalDays) > 365) ||
                   (!checkLeapYear(nYear) && (goal - totalDays) >= 365))){
               if(checkLeapYear(nYear))
                   totalDays += 366;
               else
                   totalDays += 365;
               nYear++;
           } else if ((goal - totalDays) > 31){
               if (nMonth == 4 || nMonth == 6 || nMonth == 9 || nMonth == 11)
                   totalDays += 30;
                else if (nMonth == 2){
                   if(checkLeapYear(nYear))
                       totalDays += 29;
                   else
                       totalDays += 28;
               }else
                   totalDays += 31;
               nMonth++;
               if(nMonth > 12){
                   nMonth = 1;
                   nYear++;
               }
           } else{
               for(int i = (goal - totalDays); i > 0; i--){
                   nDay++;
                   if(nMonth == 2){
                       if(checkLeapYear(nYear)){
                           if(nDay > 29){
                               nDay = 1;
                               nMonth++;
                           }
                       } else{
                           if(nDay > 28){
                               nDay = 1;
                               nMonth++;
                           }
                       }
                   } else if(nMonth == 4 || nMonth == 6 || nMonth == 9 || nMonth == 11){
                       if(nDay > 30){
                           nDay = 1;
                           nMonth++;
                       }
                   } else{
                       if(nDay > 31){
                           nDay = 1;
                           nMonth++;
                           if(nMonth > 12){
                               nMonth = 1;
                               nYear++;
                           }
                       }
                   }
               }
               totalDays += goal - totalDays;
           }
       }
       
       year = nYear;
       month = nMonth;
       day = nDay;
    }
    
/**********************************************************************
*Method: compareTo
*Description: Compares current Date instance values
*Input: User given Date object
*Output: 1 if current date instance is greater, 0 if equal, -1 if less than given
* date instance
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If given value is equal to this instance
*       Return 0
*   Else If given value's year is greater than this instance's year
*       Return 1
*   Else if given value's year is less than this instance's year
*       Return -1
*   Else
*   Begin
*       If given value's month is greater than this instance's month
*           Return 1
*       Else if given value's month is less than this instance's month
*           Return -1
*       Else
*       Begin
*           If given value's day is greater than this instance's day
*               Return 1
*           Else 
*               Return -1
*       End
*   End
* End   
**********************************************************************/
    public int compareTo(Date other){
        if(other.equals(this))
            return 0;
        else if(year > other.getYear())
            return 1;
        else if (year < other.getYear())
            return -1;
        else{
            if(month > other.getMonth())
                return 1;
            else if (month < other.getMonth())
                return -1;
            else{
                if(day > other.getDay())
                    return 1;
                else
                    return -1;
            }
        }
    }
    
/**********************************************************************
*Method: getDaysApart
*Description: returns the number of days apart between the current date instance
* and a given date instance
*Input: Date instance to measure against
*Output: Number of days apart from current date instance to given date instance, int
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If the current date is equal to the other date
*       Set result equal to 0
*   Else if the current date is less than the other date
*       Set result equal to the other date's total number of days from year 0 minus 
*        this date's total number of days from year 0
*   Else
*       Set result equal to this date's total number of days from year 0 minus the 
*        other date's total number of days from year 0
*   End if conditions
*   Return the result
* End
**********************************************************************/
    public int getDaysApart(Date other){
       int result;
       
       if(this.equals(other))
           result = 0;
       else if(this.compareTo(other) == -1)
           result = other.daysFromZero() - this.daysFromZero();
       else
           result = this.daysFromZero() - other.daysFromZero();
       
       return result;
    }
    
/**********************************************************************
*Method: daysFromZero
*Description: Returns total number of days since calendar date 01-01-0000
*Input: None
*Output: Number of days from 01-01-0000 to the given date, int
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set result = 0
*   Repeat the following as many times as the current instance year
*       If the current number is a leap year
*           Reset result equal to itself plus 366
*       Else
*           Reset result equal to itself plus 365
*   End loop
*   Reset result equal to itself plus number of days in current date year
*   Return result
* End
**********************************************************************/   
    private int daysFromZero(){
        int result = 0;
        
        for(int i = 0; i < year; i++){
            if(checkLeapYear(i))
                result += 366;
            else
                result += 365;
        }
        
        result += this.getDayNumber();
        
        return result;
    }
    
/**********************************************************************
*Method: validateYear
*Description: Verifies that the user provided year value is a valid year
*Input: User specified value for year
*Output: Illegal argument error if not valid, boolean true value if valid
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   if given year is less than 0
*       End program and throw argument exception stating "The year must be a 
*        positive value ( > 0)"
*   else
*       Return true
* End
**********************************************************************/    
    private boolean validateYear(int y){
        if (year < 0)
            throw new IllegalArgumentException("The year must be a positive value"
                    + " ( > 0)");
        else
            return true;
    }

/**********************************************************************
*Method: validateMonth
*Description: Verifies that the user given month value is valid in context of 
*  the instances day and year
*Input: New month value, and existing day and year values, int
*Output: Illegal argument error if not valid, boolean value if valid in context
*           of date instance
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If user specified month is greater than 12 or less than 1
*       End program and throw argument exception stating "Month must be between 
*         1 and 12 inclusive
*   If the user specified month is valid in context of this instances day value
*       Return true response
*   else
*       Return false response
* End
**********************************************************************/    
    private boolean validateMonth(int m, int d, int y){
        
        if(m > 12 || m < 1)
            throw new IllegalArgumentException("Month must be between 1 and 12"
                    + " inclusive.");
        if(validateDay(m,d,y))
            return true;
        else
            return false;
    }
    
/**********************************************************************
*Method: validateDay
*Description: Checks user specified day to see if it is valid both in itself and 
* in context of month and year values of the instance
*Input: user specified day, month, and year values
*Output: Illegal argument errors if invalid, boolean value if valid
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If user given day is less than 1
*       End program and show error stating "The given day is less than 1."
*   Else if given month is equal to 4, 6, 9, or 11 AND user given day is greater than 30
*       End program and show error stating "The given day is greater than 30 for 
*        a month that only has 30 days."
*   Else if given month is equal to 2 AND given day is greater than 29 AND the year value
*    is a leap year
*       End program and show error stating "The given day is greater than 29 when 
*        the month is February and the year is a leap year."
*   Else if given month is equal to 2 AND given day is greater than 28 AND the year value
*    is not a leap year
*       End program and show error stating "The given day is greater than 28 when 
*        the month is February and the year is not a leap year."
*   Else if given day is greater than 31
*       End program and show error stating "There is more than 31 days given."
*   Else
*       return true value
* End
**********************************************************************/
    private boolean validateDay(int m, int d, int y){

        if (d < 1)
            throw new IllegalArgumentException("The given day is less than 1.");
        else if ((m == 4 || m == 6 || m == 9 || m == 11) && d > 30)
            throw new IllegalArgumentException("The given day is greater than 30"
                    + " for a month that only has 30 days.");
        else if (m == 2 && d > 29 && checkLeapYear(y))
            throw new IllegalArgumentException("The given day is greater than 29"
                    + " when the month is February and the year is a leap year.");
        else if (m == 2 && d > 28 && !(checkLeapYear(y)))
            throw new IllegalArgumentException("The given day is greater than 28"
                    + " when the month is February and the year is not a leap year.");
        else if (d > 31)
            throw new IllegalArgumentException("There is more than 31 days given.");
        else
            return true;
    } 

/**********************************************************************
*Method: checkLeapYear
*Description: Checks given year value to see if it qualifies as a leap year
*Input: User given year value, int
*Output: boolean value if is or is not a leap year
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If 0 remains when dividing the year value by 4 AND the remainder of dividing the
*    year value by 100 or 400 is NOT 0
*       Return true value
*   Else
*       Return false value
* End
**********************************************************************/
    private boolean checkLeapYear(int y){
        if (y % 4 == 0 && (!(y % 100 == 0) || y % 400 == 0))
            return true;
        else
            return false;
    }
}