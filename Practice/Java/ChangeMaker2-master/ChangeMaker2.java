/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Module 3 PP 1
*Date: February 14, 2015
*Description: This program determines the change to return from a vending machine
* when an item is purchased. Item cost in the vending machine is no more than
* one dollar but no less than 25, and increases in five cent increments. The input 
* of the item's price is entered, the conversion for the change is run, and a 
* number of each type of coin to return is outputted.
*Input:  User is prompted for item price and error checked to assure entered value
* is greater than 25 cents but less than one dollar and in five cent increments, 
* stored as type integer
*Output: The number of each type of coin needed for the change of the input value 
* is displayed, quarters, dimes, and nickels, all of type integer.
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set integer constants for dollar = 100, quarter = 25, dime = 10, and 
*       nickel = 5
*   Set integer values for nQuarter, nDime, and nNickel to 0
*   Prompt for and set itemPrice to the item purchase price given by the user 
*   If itemPrice is greater than 100
*       Display "The item's price is greater than one dollar and is thus invalid."
*   else if the itemPrice is less than 25
*       Display "The item's price is less than twenty-five cents and thus invalid."
*   else if the itemPrice is not divisible by 5
*       Display "The item's price is not a five cent incremental price and is thus invalid.
*   else
*       Set remainder equal to dollar - itemPrice
*       Set nQuarter equal to remainder / quarter
*       Set remainder = remainder % quarter
*       Set nDime equal to remainder / dime
*       Set remainder = remainder % dime
*       Set nNickel equal to remainder / nickel
*       Display "After purchase you will receive " + nQuarter + " quarter, " 
*           + nDime + " dime, and " + nNickel + " nickel."
*   End if conditions
* End
**********************************************************************/
import java.util.Scanner;

public class ChangeMaker2 
{
    public static void main(String[] args)
    {
        final int dollar = 100;
        final int quarter = 25;
        final int dime = 10;
        final int nickel = 5;
        int nQuarter = 0;
        int nDime = 0;
        int nNickel = 0;
        int itemPrice;
        int remainder;
        Scanner keyb = new Scanner(System.in);
        
        System.out.print("Please enter the cent price of the item being purchased: ");
        itemPrice = keyb.nextInt();
        if (itemPrice > 100)
        {
            System.out.println("The item's price is greater than one dollar and "
                + "is thus invalid.");
            
        } 
        else if (itemPrice < 25)
        {
            System.out.println("The item's price is less than twenty-five cents "
                + "and thus invalid.");
        } 
        else if (!(itemPrice % 5 == 0))
        {
            System.out.println("The item's price is not a five cent incremmental "
                + "price and is thus invalid.");
        } 
        else 
        {
            remainder = dollar - itemPrice;
            nQuarter = remainder / quarter;
            remainder = remainder % quarter;
            nDime = remainder / dime;
            remainder = remainder % dime;
            nNickel = remainder / nickel;
            System.out.println("After purchase you will receive " + nQuarter + 
                    " quarter, " + nDime + " dime, and " + nNickel + " nickel.");
            System.out.println("Thank you, and have a great day!");
        }
    }
}
