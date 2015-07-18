/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Module 3 PP 4
*Date: February 15, 2015
*Description: The application asks the user to enter a date in a specified format
* and the given date is then parsed out for the respective month, day, and year
* and tested to see if a valid date according to real world date conventions.
*Input: The user is prompted to enter a date in a mm/dd/yyyy format, type string
*Output: The user is told if the date is valid or not and is told why the date 
* is invalid if it is.
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set boolean leapYear = false
*   Prompt user for and set givenDate to user given value
*   If the integer index of the last instance of '/' found in givenDate is less than 3
*       Display "This is an invalid date because you did not enter a value that adheres to required formatting mm/dd/yyyy."
*   else
*       Set givenYear = to the converted integer value of the string after the second instance of '/' found therein
*       If givenYear % 4 is equal to 0 and (givenYear % 100 is not equal to 0 or givenYear % 400 is equal to 0)
*           Set leapYear = true
*       Set givenMonth equal to the sub value of givenDate from index 0 to before the first instance of '/'
*       If givenMonth is either less than 1 or givenMonth is greater than 12
*           Display "This is an invalid date because the month portion of the date is less than 1 (January) or greater than 12 (December)"
*       else
*           Set givenDay equal to the sub value of givenDate from the indexes between the first instance of '/' and the last instance of '/'
*           If givenDay is less than 1
*               Display "This is an invalid date because the given day is less than 1."
*           else if givenMonth is equal to 4 or givenMonth is equal to 6 or givenMonth is equal to 9 or givenMonth is equal to 11 and givenDay is greater than 30
*               Display "This is an invalid date because the given day is greater than 30 for a month that only has 30 days."
*           else if givenMonth is equal to 2 and givenDay is greater than 29 and leapYear is true
*               Display "This is an invalid date because the given day is greater than 29 when the month is February and the year is a leap year."
*           else if givenMonth is equal to 2 and givenDay is greater than 28 and leapYear is false
*               Display "This is an invalid date because the given day is greater than 28 when the month is February and the year is not a leap year."
*           else if givenDay is greater than 31
*               Display "This is an invalid date because there is more than 31 days given, which exceeds the maximum number of days in a month."
*           else
*               Display "The entered date is a valid date."
*           End if conditionals
*       End if conditionals
*   End if conditionals
* End
**********************************************************************/
import java.util.Scanner;

public class Date 
{
    public static void main(String[] args) 
    {
       boolean leapYear = false;
       String givenDate;
       int givenDay;
       int givenMonth;
       int givenYear;
       Scanner keyb = new Scanner(System.in);
       
       System.out.print("This application will tell you if an entered date is "
               + "valid or not.\nPlease enter a date observing the following "
               + "format exactly as shown; mm/dd/yyyy : ");
       givenDate = keyb.next();
       if (givenDate.lastIndexOf("/") < 3)
       {
           System.out.println("This is an invalid date because you did not enter "
                   + "a value that adheres to required formatting mm/dd/yyyy.");
       }
       else
       {
           givenYear = Integer.parseInt(givenDate.substring(givenDate.lastIndexOf("/") + 1));
           if (givenYear % 4 == 0 && (!(givenYear % 100 == 0) || givenYear % 400 == 0))
           {
               leapYear = true;
           }
           givenMonth = Integer.parseInt(givenDate.substring(0, givenDate.indexOf("/")));
           if (givenMonth < 1 || givenMonth > 12)
           {
               System.out.println("This is an invalid date because the month portion "
                       + "of the date is either less than 1 (January) or greater than 12 "
                       + "(December).");
           }
           else
           {
               givenDay = Integer.parseInt(givenDate.substring(givenDate.indexOf("/") + 1
                       , givenDate.lastIndexOf("/")));
               if (givenDay < 1)
               {
                   System.out.println("This is an invalid date because the given "
                           + "day is less than 1.");
               }
               else if ((givenMonth == 4 || givenMonth == 6 || givenMonth == 9 || 
                       givenMonth == 11) && givenDay > 30)
               {
                   System.out.println("This is an invalid date because the given "
                           + "day is greater than 30 for a month that only has 30 "
                           + "days.");
               }
               else if (givenMonth == 2 && givenDay > 29 && leapYear)
               {
                   System.out.println("This is an invalid date because the given "
                           + "day is greater than 29 when the month is February "
                           + "and the year is a leap year.");
               }
               else if (givenMonth == 2 && givenDay > 28 && !(leapYear))
               {
                   System.out.println("This is an invalid date because the given "
                           + "day is greater than 28 when the month is February "
                           + "and the year is not a leap year.");
               }
               else if (givenDay > 31)
               {
                   System.out.println("This is an invalid date because there is "
                           + "more than 31 days given, which exceeds the maximum "
                           + "number of days in a month.");
               }
               else
               {
                   System.out.println("The entered date " + givenDate + " is a "
                           + "valid date.");
               }
           } 
       }
    }  
}
