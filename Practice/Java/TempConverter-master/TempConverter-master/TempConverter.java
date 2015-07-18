/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Module 2 PP 1
*Date: January 30, 2015
*Description: This program computes the degree Celsius temperature conversion
* given a value as degree Fahrenheit. The output is formatted to display up to 
* five digits to the left of the decimal and rounds to the nearest tenth.
*Input:  User is prompted for the Fahrenheit temperature, type float
*Output: The converted Celsius temperature, type float
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Prompt for and get a Fahrenheit temperature and set to givenTemp
*   Set convertedTemp equal to 5 * (givenTemp - 32) / 9
*   Display "The converted temperature is convertedTemp degrees Celsius!"
* End
**********************************************************************/
import java.util.Scanner;

public class TempConverter
{
    public static void main(String[] args)
    {
        float givenTemp;
        float convertedTemp;
        Scanner keyb = new Scanner(System.in);
        
        System.out.print("This application will convert temperatures entered "
                + "as Fahrenheit in to Celsius.\n");
        System.out.print("Please enter a Fahrenheit temperature as a whole number: ");
        givenTemp = keyb.nextFloat();
        
        convertedTemp = 5 * (givenTemp - 32) / 9;
        
        System.out.printf("The converted temperature is %5.1f degrees Celsius!", convertedTemp);
    }
}
