/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Module 3 , My Grade Determination
*Date: February 15, 2015
*Description: This application calculates a student's grade in their CSC110 class
* given point values for the three primary grading criteria; exam points, homework
* points, and assignment points. Given this information the student is given a 
* letter grade and told why they received that grade.
*Input: Exam points, homework points, and assignment points, all entered as integers
*Output: Total points entered by the user (integer), the final letter grade and
* an explanation for the given letter grade (type String)
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set constant integer value for aGrade to 900
*   Set constant integer value for bGrade to 800
*   Set constant integer value for cGrade to 700
*   Set constant integer value for dGrade to 600
*   Set constant integer value for minExamPts to 240
*   Prompt user for and set givenExamPts (integer) to user given value
*   If the value of givenExamPts is less than 0
*       Display "This is an invalid entry, points entered must be non-negative integers
*   else if givenExamPts is less than 240
*       Display "Your grade is an F because you must accrue a minimum of 240 exam points
*                   in order to pass."
*   else
*       Prompt user for and set givenHWPts (integer) to user given value
*       If the value of givenHWPts is less than 0
*           Display "This is an invalid entry, points entered must be non-negative integers
*       else
*           Prompt user for and set givenAssignPts (integer) to user given value
*           If the value of givenAssignPts is less than 0
*               Display "This is an invalid entry, points entered must be non-negative integers."
*           else
*               Set totalPoints (integer) equal to givenExamPts + givenHWPts + givenAssignPts
*               If totalPoints is greater than or equal to aGrade
*                   Display "Your grade is an A because you have accrued more than 900 points
*                               for the semester and at least 240 exam points."
*               else if totalPoint is greater than or equal to bGrade
*                   Display "Your grade is a B because you have accrued more than 800 points
*                               but less than 900 points and at least 240 exam points for the semester."
*               else if totalPoint is greater than or equal to cGrade
*                   Display "Your grade is a C because you have accrued more than 700 points
*                               but less than 800 points and at least 240 exam points for the semester."
*               else if totalPoint is greater than or equal to dGrade
*                   Display "Your grade is a D because you have accrued more than 600 points
*                               but less than 700 points and at least 240 exam points for the semester."
*               else 
*                   Display "Your grade is a F because you have accrued less than 599 points for the semester."
*               End if conditional
*           End if conditional
*       End if conditional
*   End if conditional
* End
**********************************************************************/
import java.util.Scanner;

public class MyGrade 
{
    public static void main(String[] args) 
    {
        final int aGrade = 900;
        final int bGrade = 800;
        final int cGrade = 700;
        final int dGrade = 600;
        final int minExamPts = 240;
        int givenExamPts;
        int givenHWPts;
        int givenAssignPts;
        int totalPoints;
        Scanner keyb = new Scanner(System.in);
        
        System.out.print("Please enter the number of exam points earned: ");
        givenExamPts = keyb.nextInt();
        
        if (givenExamPts < 0)
        {
            System.out.println("This is an invalid entry, points entered must be "
                    + "non-negative.");
        }
        else if (givenExamPts < minExamPts)
        {
            System.out.println("Your grade is an F because you must accrue a minimum "
                    + "of 240 exam points in order to pass.");
        }
        else
        {
            System.out.print("Please enter the number of Homework points earned: ");
            givenHWPts = keyb.nextInt();
            if (givenHWPts < 0)
            {
                System.out.println("This is an invalid entry, points entered must be "
                    + "non-negative.");
            }
            else
            {
                System.out.print("Please enter the number of Assignment points earned: ");
                givenAssignPts = keyb.nextInt();
                if (givenAssignPts < 0)
                {
                    System.out.println("This is an invalid entry, points entered must be "
                    + "non-negative.");
                }
                else
                {
                    totalPoints = givenExamPts + givenHWPts + givenAssignPts;
                    if (totalPoints >= aGrade)
                    {
                        System.out.println("Your grade is an A because you have accrued "
                                + "more than 900 points for the semester and at "
                                + "least 240 exam points.");
                    }
                    else if (totalPoints >= bGrade)
                    {
                        System.out.println("Your grade is a B because you have accrued "
                                + "more than 800 points but less than 900 points and at "
                                + "least 240 exam points for the semester.");
                    }
                    else if (totalPoints >= cGrade)
                    {
                        System.out.println("Your grade is a C because you have accrued "
                                + "more than 700 points but less than 800 points and at "
                                + "least 240 exam points for the semester.");
                    }
                    else if (totalPoints >= dGrade)
                    {
                        System.out.println("Your grade is a D because you have accrued "
                                + "more than 600 points but less than 700 points and at "
                                + "least 240 exam points for the semester.");
                    }
                    else
                    {
                        System.out.println("Your grade is a F because you have accrued "
                                + "less than 599 points for the semester.");
                    }
                }
            }
        }
    }
}
