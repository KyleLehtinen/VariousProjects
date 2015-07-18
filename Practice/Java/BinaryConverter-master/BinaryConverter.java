/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Module 2 PP 6
*Date: January 30, 2015
*Description: A program that converts a four bit binary number entered by the 
* user in to a decimal value.
*Input:  The user is prompted to enter a four digit binary number, stored in
* binary type string
*Output: The decimal equivalent, value, as an integer.
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Prompt user for and set a four digit binary number to binary
*   Set substr1 to a substring of binary between index 0 and 1
*   Set substr2 to a substring of binary between index 1 and 2
*   Set substr3 to a substring of binary between index 2 and 3
*   Set substr4 to a substring of binary between index 3 and 4
*   Set value equal to the integer value of substr1 * 8 + substr2 * 4 + 
*       substr3 * 2 + substr4 * 1
*   Display "The converted decimal is " + value
* End
**********************************************************************/
import java.util.Scanner;

public class BinaryConverter 
{
    public static void main(String[] args) 
    {
        String binary;
        String substr1;
        String substr2;
        String substr3;
        String substr4;
        int value;
        Scanner keyb = new Scanner(System.in);
        
        System.out.print("Please enter a 4 digit binary number: ");
        binary = keyb.nextLine();
        
        substr1 = binary.substring(0,1);
        substr2 = binary.substring(1,2);
        substr3 = binary.substring(2,3);
        substr4 = binary.substring(3,4);

        value = ((Integer.parseInt(substr1) * 8) + (Integer.parseInt(substr2) * 4)
                + (Integer.parseInt(substr3) * 2) + (Integer.parseInt(substr4) * 1));
        
        System.out.print("The converted decimal is " + value);
    }
}
