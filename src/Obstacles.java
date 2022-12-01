/**
 *This class creates the obstacles for the "Need for Java" game.
 *
 *This class sets the probablity of each obstacle being created.
 *
 *@author Ariel Wigdorowitz
 *@version 2022.06.19
 */

import java.util.Random;

public class Obstacles
{
    // an instance of the Random class
    private Random randObj;
    //an instance of the Difficulty class
    private Difficulty diffObj;

    public Obstacles ()
    {
        randObj = new Random();
        diffObj = new Difficulty();
        setObjects();
        nextObstacle();
    }

    /**
     *Sets the probability of each obstacle being created
     */

    public String setObjects()
    {

        int randomiser = randObj.nextInt(100);

        if (randomiser < 40)
        {
            return "R";
        }
        else if (randomiser < 70)
        {
            return "F";
        }

        else if (randomiser < 80 )
        {
            return "T";
        }

        else
        {
            return "O";
        }
    }

    /**
     *gets the next obstacle created
     *@return an obstacle
     */

    public String nextObstacle()

    {
        String temp = "temp";

        temp = setObjects();

        return temp;
    }
}
