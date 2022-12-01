/**
 *This class tests/validates users input for String inputs.
 *
 *@author Ariel Wigdorowitz
 *@version 2022.06.19
 */

import java.util.Scanner;

public class Validation
{
    private String input;

    public Validation ()
    {
        input = "";
        isBlank(input);
        isWithinRange(input);
    }

    /**
     *Tests whether the user's input is blank
     *@return a boolean true if user's input is blank.
     */

    public boolean isBlank (String newInput)
    {
        input = newInput;
        boolean b = true;

        if (input.equals(""))
        {
            b = true;
        }
        else
        {
            b = false;
        }
        return b;
    }

    /**
     *Tests whether the user's input is between 3 and 12 characters in length.
     *@return returns a boolean true if user's input is outside of range.
     */

    public boolean isWithinRange (String newInput)
    {
        input = newInput;
        boolean b = true;
        if (input.length() < 3 || input.length() > 12 )
        {
            b = false;
        }
        else
        {
            b = true;
        }
        return b;
    }
}
