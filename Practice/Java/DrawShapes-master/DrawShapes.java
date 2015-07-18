/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Drawing Shapes
*Date: February 27, 2015
*Description: The program provides the user a selection of different shapes to be
* drawn on the screen and then draws those shapes on the screen of a given size
* and character composition as selected by the user.
*Input: 
* The user enters a letter choice for selection, stored as type char.
* The user specifies the character type to print, stored as type char.
* The user specifies the number of lines or maximum character count depending on 
*   the selected shape, stored as type int.
*Output: 
* Shapes are printed to the screen according to the user's selection of shape type, 
* character composition, and number of lines/characters selected. If the user makes
* an invalid entry an error message appears informing the user.
* --------------------------------------------------------------
* Pseudocode for this solution:
 Begin
*   Display "Please select what shape you would like to create from the options below (a,b,c,d,e):"
*           "a. triangle\nb. upside down triangle\nc. parallelogram\nd. diamond\ne. quit\nSelection: "
*   Set selection equal to user given value
*   While selection is not a, A, b, B, c, C, d, D, e, E
*       Display "Invalid selection! Please select a, b, c, d, or e: "
*       Reset selection equal to user given value
*   End while loop
*   while selection is not e or E
*	Prompt for and set charToPrint to user given value
*       If selection is equal to a or A
*           Prompt user for and set lines to user given value
*           For integer i equals 0, while i is less than or equal to lines, increment i by 1
*               For integer j equals 0, while j is less than i, increment j by 1
*                   Display charToPrint
*               End for loop
*		Drop to new line
*           End for loop
*       Else if selection is equal to b or B
*           Prompt user for and set lines to user given value
*           For integer i equals lines, while i is greater than or equal to 0, reduce i by 1
*               For integer j equals i, while j is greater than 0, reduce j by 1
*                   Display charToPrint
*		    Display charToPrint
*               End for loop
*		Drop to new line
*           End for loop
*       Else if selection is equal to c or C
*           Prompt user for and set lines to user given value
*           For integer i = 0, while i is less than or equal to lines, increment i by 1
*		For integer j = 0, while j is less than lines - 1, increment j by 1
*		    Display " "
*		    Display " "
*		End for loop
*		For integer j = 0, while j is less than i, increment j by 1
*		    Display charToPrint
*		    Display charToPring
*		End for loop
*		Drop to a new line
*	    End for loop
*	    For integer i = 1, while i is less than or equal to lines, increment i by 1
*		For integer j = 0, while j is less than lines - 1, increment j by 1
*		    Display charToPrint
*		    Display charToPrint
*		End for loop
*		Drop to a new line
*	    End for loop
*	Else
*           Prompt user for and set lines to user given value
*           If lines % 2 does not equal 0
*               Set k equal to 1
*               Set l equal to lines - 2
*               For integer i = 0, while i is less than lines, increment i by 1
*                   If the value of k is less than or equal to lines
*                       For integer j = the value of lines - k / 2, while j is greater than 0, reduce j by 1
*                           Display " "
*                       End for loop
*                       For integer j = k, while j is greater than 0, reduce j by 1
*                           Display charToPrint
*                       End for loop
*                       Drop to a new line
*                       Reset k = to k + 2
*                   Else
*                   For integer j = to the value of lines - 1 / 2, while j is greater than 0, reduce j by 1
*                           Display " "
*                       End for loop
*                       For j = l, while j is greater than 0, reduce j by 1
*                           Display charToPrint
*                       End for loop
*                       Drop to a new line
*                       Reset l = to l minus 2
*                   End if condition
*              End for loop
*           End if condition
*       End if condition
*       Display "Please select what shape you would like to create from the options below (a,b,c,d,e):"
*           "a. triangle\nb. upside down triangle\nc. parallelogram\nd. diamond\ne. quit\nSelection: "
*       Set selection equal to user given value
*       While selection is not a, A, b, B, c, C, d, D, e, E
*           Display "Invalid selection! Please select a, b, c, d, or e: "
*           Reset selection equal to user given value
*       End while loop
*   End while loop
*   Display "Have a good one!"
* End
**********************************************************************/
import java.util.Scanner;

public class DrawShapes {
    public static void main(String[] args) {
        int i;
        int j;
        int k;
        int l;
        int lines;
        char charToPrint;
        char selection;
        Scanner keyb = new Scanner(System.in);
        
        System.out.print("Please select what shape you would like to create from "
                + "the options below (a,b,c,d,e):\na. triangle\nb. upside down triangle\n"
                + "c. parallelogram\nd. diamond\ne. quit\nSelection: ");
        selection = keyb.next().charAt(0);
        while (selection != 'a' && selection != 'A' && selection != 'b' && selection != 'B' 
                && selection != 'c' && selection != 'C' && selection != 'd' && selection != 'D' 
                && selection != 'e' && selection != 'E'){
            System.out.print("That is an invalid selection, please choose a, b, "
                    + "c, d, or e: ");
            selection = keyb.next().charAt(0);
        }
        while (selection != 'e' && selection != 'E'){
            System.out.print("\nPlease enter the desired character to compose the shape "
                    + "with: ");
            charToPrint = keyb.next().charAt(0);
            if (selection == 'a' || selection == 'A'){
                System.out.print("\nPlease enter the desired height of the triangle: ");
                lines = keyb.nextInt();
                for (i = 0; i <= lines; i++){
                    for (j = 0; j < i; j++){
                        System.out.print(charToPrint);
                    }
                    System.out.println();
                }
            } else if (selection == 'b' || selection == 'B'){
                System.out.print("\nPlease enter the desired height of the inverted "
                        + "triangle: ");
                lines = keyb.nextInt();
                for (i = lines; i >= 0; i--){
                    for (j = i; j > 0; j--){
                        System.out.print(charToPrint);
                        System.out.print(charToPrint);
                    }
                    System.out.println();
                }
            } else if (selection == 'c' || selection == 'C'){
                System.out.print("\nPlease enter the desired height of the parallelogram "
                        + "in lines: ");
                lines = keyb.nextInt();
                for (i = 0; i <= lines; i++){
                    for (j = 0; j < (lines - i); j++){
                        System.out.print(" ");
                        System.out.print(" ");
                    }
                    for (j = 0; j < i; j++){
                        System.out.print(charToPrint);
                        System.out.print(charToPrint);
                    }       

                    System.out.println();
                }
                for (i = 1; i <= lines; i++){
                    for (j = 0; j < (lines - i); j++){
                        System.out.print(charToPrint);
                        System.out.print(charToPrint);
                    }
                    System.out.println();
                }
            } else {
                System.out.print("\nPlease enter the desired number of characters "
                        + "per line: ");
                lines = keyb.nextInt();
                if (!(lines % 2 == 0)){
                    k = 1;
                    l = lines - 2;
                    for (i = 0; i < lines; i++){
                        if (k <= lines){    
                            for (j = ((lines - k) / 2); j > 0; j--){
                                System.out.print(" ");
                            }
                            for (j = k; j > 0; j--){
                                System.out.print(charToPrint);
                            }
                            System.out.println();
                            k += 2;
                        } else {
                            for (j = ((lines - l) / 2); j > 0; j--){
                                System.out.print(" ");
                            }
                            for (j = l; j > 0; j--){
                                System.out.print(charToPrint);
                            }
                            System.out.println();
                            l -= 2;
                        }
                    }
                }
            } 
            
            System.out.print("\nPlease select what shape you would like to create from "
                + "the options below (a,b,c,d,e):\na. triangle\nb. upside down triangle\n"
                + "c. parallelogram\nd. diamond\ne. quit\nSelection: ");
            selection = keyb.next().charAt(0);
            while (selection != 'a' && selection != 'A' && selection != 'b' && selection != 'B' 
                && selection != 'c' && selection != 'C' && selection != 'd' && selection != 'D' 
                && selection != 'e' && selection != 'E'){
                System.out.print("That is an invalid selection, please choose a, b, "
                    + "c, d, or e: ");
            selection = keyb.next().charAt(0);
            }
        }
        System.out.println("Have a good one!");
    } 
}
