/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Module 2 PP 5
*Date: January 30, 2015
*Description: This program takes a given cent amount and returns the smallest
* number of coins needed to equal that amount. The input of any cent amount is 
* entered, the conversion is run, and a number of each time of coin is outputted.
*Input:  User is prompted for cent amount, type integer
*Output: The number of each type of coin used to achieve the input value in the
* smallest number of coins is displayed, quarters, dimes, nickels, pennies, all
* type integer.
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set quarter, dime, nickel, and penny integers to 0
*   Prompt for and get givenCentValue from user
*   quarter = givenCentValue / 25
*   givenCentValue = givenCentValue % 25
*   dime = givenCentValue / 10
*   givenCentValue = givenCentValue % 10
*   nickel = givenCentValue / 5
*   givenCentValue = givenCentValue % 5
*   penny = givenCentValue
*   Display "You will need " + quarter + " quarters, " + dime + " dimes, 
*       " + nickel + " nickels, and " + penny + " pennies."
* End
**********************************************************************/
import java.util.Scanner;

public class ChangeMaker 
{
    public static void main(String[] args)
    {
        int quarter;
        int dime;
        int nickel;
        int penny;
        int givenCentValue;
        Scanner keyb = new Scanner(System.in);
        
        System.out.println("This application converts a given value of cents in "
                + "to the fewest number of coins needed for that value.");
        System.out.print("Please enter the initial cent amount to be converted: ");
        givenCentValue = keyb.nextInt();
        
        quarter = givenCentValue / 25;
        givenCentValue = givenCentValue % 25;
        dime = givenCentValue / 10;
        givenCentValue = givenCentValue % 10;
        nickel = givenCentValue / 5;
        givenCentValue = givenCentValue % 5;
        penny = givenCentValue;
        
        System.out.println("You will need " + quarter + " quarters, " + dime 
                + " dimes, " + nickel + " nickels, and " + penny + " pennies.");
        System.out.print("Thank you, and have a great day!");
    }
}
