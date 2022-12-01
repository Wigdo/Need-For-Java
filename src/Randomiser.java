/**
 *This class creates a random number between 1 and 3.
 *
 *@author Ariel Wigdorowitz
 *@version 2022.06.19
 */

import java.util.Random;

public class Randomiser
{
    public Random randomObj;

    public Randomiser()
    {
        // an instance of the Random class.
        randomObj = new Random();

        randomX();
    }

    /**
     *Randomly selects a number between 1 and 3 (inclusive)
     *@return returns a random integer between 1 and 3 (inclusive).
     */

    public int randomX()

    {
        int startingPo = randomObj.nextInt(1,4);
        int x = 0;
        if (startingPo == 1)
        {
            x = 1;
        }

        else if (startingPo == 2)
        {
            x = 2;
        }

        else if (startingPo == 3)
        {
            x = 3;
        }
        return x;
    }
}
