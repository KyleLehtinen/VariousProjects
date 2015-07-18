/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Program 6 p. 255
*Date: February 27, 2015
*Description: This program takes user inputs for test scores and processes the score
* to ultimately provide a break down of grades based on typical ranges for letter 
* grades A through F until the user enters a negative value to act as a call to 
* end processing and display the results. The program also tracks the number of 
* inputs processed and the average score, the maximum score, and the minimum score
*Input:  The user enters a test score percentage, stored as an integer
*Output: A break down of the number of each letter grade is displayed along with
* the number of scores processed, the average score, the max score, and the min score
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set and finalize aGrade equal to 90
*   Set and finalize bGrade equal to 80
*   Set and finalize cGrade equal to 70
*   Set and finalize dGrade equal to 60
*   Set gradeCount equal to 0
*   Set scoreTotal equal to 0
*   Set min equal to 100
*   Set max equal to 0
*   Set aCount equal to 0
*   Set bCount equal to 0
*   Set cCount equal to 0
*   Set dCount equal to 0
*   Set fCount equal to 0
*   Prompt user for and set testScore equal to user given value
*   While testScore is not less than 0
*       Increase gradeCount by one
*       Reset scoreTotal equal to scoreTotal + testScore
*       If testScore is greater than max
*           Reset max equal to testScore
*       If testScore is less than min
*           Reset min equal to testScore
*       If testScore is greater than or equal to aGrade
*           Increase aCount by 1 unit
*       else if testScore is greater than or equal to bGrade
*           Increase bCount by 1 unit
*       else if testScore is greater than or equal to cGrade
*           Increase cCount by 1 unit
*       else if testScore is greater than or equal to dGrade
*           Increase dCount by 1 unit
*       else 
*           Increase fCount by 1 unit
*       End if conditions
*       Prompt user for and set testScore equal to user given value
*   End while loop  
*   Set avg equal to scoreTotal / gradeCount 
*   Display "The total number of grades: " + gradeCount
*   Display "Number of A's = " + aCount
*   Display "Number of B's = " + bCount
*   Display "Number of C's = " + cCount
*   Display "Number of D's = " + dCount
*   Display "Number of F's = " + fCount
*   Display "Average of all grades: " = avg
*   Display "Minimum grade: " + min
*   Display "Maximum grade: " + max
* End
**********************************************************************/
import java.util.Scanner;

public class ScoreProcessor {
    public static void main(String[] args){
        Scanner keyb = new Scanner(System.in);
        final int aGrade = 90;
        final int bGrade = 80;
        final int cGrade = 70;
        final int dGrade = 60;
        int gradeCount = 0;
        int scoreTotal = 0;
        int min = 100;
        int max = 0;
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;
        int dCount = 0;
        int fCount = 0;
        int testScore;
        int avg;
        
        System.out.println("Please enter the next test score: ");
        testScore = keyb.nextInt();
        
        while (!(testScore < 0)){
            gradeCount++;
            scoreTotal += testScore;
            if (testScore > max){
                max = testScore;
            }
            if (testScore < min){
                min = testScore;
            }
            
            if (testScore >= aGrade){
                aCount++;
            } else if (testScore >= bGrade){
                bCount++;
            } else if (testScore >= cGrade){
                cCount++;
            } else if (testScore >= dGrade){
                dCount++;
            } else{
                fCount++;
            }
            
            System.out.println("Please enter the next test score: ");
            testScore = keyb.nextInt();
        }
        
        avg = scoreTotal / gradeCount;
        
        System.out.println("The total number of grades: " + gradeCount);
        System.out.println("Number of A's = " + aCount);
        System.out.println("Number of B's = " + bCount);
        System.out.println("Number of C's = " + cCount);
        System.out.println("Number of D's = " + dCount);
        System.out.println("Number of F's = " + fCount);
        System.out.println("Average score of all grades: " + avg);
        System.out.println("Minimum grade: " + min);
        System.out.println("Maximum grade: " + max);
    }
}