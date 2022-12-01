/**
 *This class gets user input for the selection of difficulty and then sets the difficulty
 * of the "Need for Java" game.
 *
 *@author Ariel Wigdorowitz
 *@version 2022.06.19
 */

import java.util.*;
public class SetDifficulty

{
    //creates an instance of the Difficulty class
    private Difficulty diffObj;
    //creates an instance of the Input
    private Input input;
    //the user's input selection
    private int x;
    //the fuel difficulty selection
    int a;
    //the highway length difficulty selection
    int b;
    //the obstalce difficulty selection
    int c;

    public SetDifficulty()
    {
        diffObj = new Difficulty();
        input = new Input();
        a = 0;
        b = 0;
        c = 0;
    }
    /**
     *The display method
     *prints the resulting difficulty selections of the user's input
     */
    public void display()
    {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    /**
     *Sets the fuel difficulty
     *@return the value of the fuel difficulty based on the user's selection
     */

    public int getFuelDifficulty()
    {

        if (x == 1)
        {
            a = diffObj.setEasyFuel();

            System.out.println("\nyou have selected EASY");

        }

        else if (x == 2)
        {
            a = diffObj.setMediumFuel();

            System.out.println("\nyou have selected MEDIUM");
        }
        else if (x == 3)
        {
            a = diffObj.setHardFuel();

            System.out.println("\nyou have selected HARDMODE");

        }
        return a;
    }

    /**
     *Sets the highway length difficulty
     *@return the value of the highway length difficulty based on the user's selection
     */

    public int getHighwayDifficulty()
    {
        if (x == 1)
        {
            b = diffObj.setEasyHighway();
        }

        else if (x == 2)
        {
            b = diffObj.setMediumHighway();
        }
        else if (x == 3)
        {
            b = diffObj.setHardHighway();

        }
        return b;
    }

    /**
     *Sets the obstacle difficulty
     *@return the value of the obstacle difficulty based on the user's selection
     */

    public int getObstalceDifficulty()
    {
        if (x == 1)
        {
            c = diffObj.setEasyObjects();
            return c;
        }

        else if (x == 2)
        {
            c = diffObj.setMediumObjects();
            return c;

        }
        else if (x == 3)
        {
            c = diffObj.setHardObjects();
            return c;
        }
        return c;
    }

    /**
     *Asks the user for input and validates the input.
     *@return returns the user's selection as an integer representation
     */

    public int getUserInput()
    {
        do
        {
            try
            {
                System.out.println("\nPlease enter difficulty \n" +
                        "Press 1 for EASY \nPress 2 for MEDIUM \nPress 3 for HARD");
                x = input.acceptIntegerInput();

                if (x <= 0)
                {
                    System.out.println("Input must be a positive number");
                }

            }

            catch (InputMismatchException ime)
            {
                System.out.println("Difficulty has been set to default value, easy");
                x = 1;
            }
        }
        while (x <= 0 || x > 3);
        return x;
    }
}

