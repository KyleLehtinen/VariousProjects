/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Module 2 PP 3
*Date: January 30, 2015
*Description: This program takes prompts the user to enter a sentence, saves it
* as a string value, then uses a series of string variable alterations to 
* ultimately move the first word of the sentence to the end and capitalize the
* first letter of the new sentence.
*Input:  The user is prompted to enter a sentence, stored as type String
*Output: The altered sentence, type String
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Prompt user for and set input to a user entered sentence
*   Set index to equal the value where the first space occurs
*   Set String movedToEnd to equal the substring of input at 0 and the value of index
*   Set String altered to equal the substring of input starting at the value of index + 1
*   Reset altered to equal altered with substring at index 0 and 1 changed to capital
*       plus the substring of altered starting at index 1 plus an empty space plus the 
*       value of movedToEnd
*   Display "The sentence has been altered to say: " plus altered
* End
**********************************************************************/
import java.util.Scanner;

public class Rephraser 
{
    public static void main(String[] args) 
    {
        String input;
        String movedToEnd;
        String altered;
        int index;
        Scanner keyb = new Scanner(System.in);
        
        System.out.println("Please enter a sentence to be rephrased: ");
        input = keyb.nextLine();
        
        index = input.indexOf(' ');
        movedToEnd = input.substring(0,index);
        altered = input.substring(index + 1);
        altered = altered.substring(0,1).toUpperCase() + altered.substring(1)
                + ' ' + movedToEnd;
        System.out.println("The sentence has been altered to say:");
        System.out.print(altered);
    }
}
