/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 pp. 2, page 256
*Date: February 27, 2015
*Description: The program evaluates user given input and indicates whether the 
* inputted word is spelled the same after the first letter is moved to the end of
* of the word and the word is spelled backwards.
*Input: The user is prompted for a word, stored as a string
*Output: The user is told if the given word is the same after the first letter 
* is moved to the back of the word and the word is spelled backwards
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Prompt user for and set givenWord equal to user given value
*   While givenWord is not equal to "quit"
*   Reset givenWord equal to givenWord as all lowercase letters
*       Set alteredWord equal to the first character of givenWord
*       For integer i set equal to the length of givenWord and reduced by one til i is equal to 1
*           Set alteredWord equal to itself plus the character of givenWord found at index i
*       End For Loop
*       If givenWord is equal to alteredWord
*           Display "The word " + givenWord + " has the backward property."
*       else
*           Display "The word " + givenWord " + does not have the backward property."
*   Prompt user for and set givenWord equal to user given value
*   End while loop
* End
**********************************************************************/
import java.util.Scanner;

public class BackWord {

    public static void main(String[] args) {
        String givenWord;
        String alteredWord;
        Scanner keyb = new Scanner(System.in);
        
        System.out.print("Please enter the word to be evaluated: ");
        givenWord = keyb.next();
        while (!givenWord.equals("quit")){
            givenWord = givenWord.toLowerCase();
            alteredWord = givenWord.substring(0,1);
            for (int i = givenWord.length(); i > 1; i--){
                alteredWord += givenWord.substring(i - 1, i);
            }
            if (alteredWord.equals(givenWord)){
                System.out.println("The word " + givenWord + " has the backwards "
                        + "property.");
            } else {
                System.out.println("The word " + givenWord + " does not have the "
                        + "backwards property.");
            }
            
            System.out.print("Please enter the word to be evaluated: ");
            givenWord = keyb.next();
        }
    }
}
