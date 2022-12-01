/**
 *This class obtains input from the user.
 *@author Ariel Wigdorowitz
 *@version 2022.06.19
 */

import java.util.Scanner;

public class Input
{
    //an instance of the Scanner class.
    private Scanner sc;

    public Input ()
    {
        sc = new Scanner(System.in);
    }

    /**
     *returns a double from the user
     *@return returns a double input from the user
     */

    public double acceptDoubleInput ()
    {
        double d = sc.nextDouble();
        return d;
    }

    /**
     *returns an integer from the user
     *@return returns an integer input from the user
     */

    public int acceptIntegerInput()
    {
        int i = sc.nextInt();
        return i;
    }

    /**
     *returns a String input from the user
     *@return returns a String input from the user
     */
    public String acceptStringInput()
    {
        String x = sc.nextLine();
        return x;
    }
}
