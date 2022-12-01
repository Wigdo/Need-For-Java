/**
 *This class sets the difficulty options in the "Need for Java" game.
 *@author Ariel Wigdorowitz
 *@version 2022.06.19
 */

import java.util.Random;

public class Difficulty

{
    public Difficulty ()
    {
    }


    /**
     *The easy fuel set method
     */

    public int setEasyFuel()
    {
        int percentageOfFuel = 100;
        return percentageOfFuel;
    }

    /**
     *The easy highway length set method
     */

    public int setEasyHighway()
    {
        Random randomObj = new Random();
        int highwayLength = randomObj.nextInt(10,15);
        return highwayLength;
    }

    /**
     *The easy objects set method
     */

    public int setEasyObjects()
    {
        int easyObjects = 12;
        return easyObjects;
    }

    /**
     *The medium fuel set method
     */

    public int setMediumFuel()
    {
        int percentageOfFuel = 80;
        return percentageOfFuel;
    }

    /**
     *The medium highway length set method
     */

    public int setMediumHighway()
    {
        Random randomObj = new Random();
        int highwayLength = randomObj.nextInt(15,30);
        return highwayLength;
    }

    /**
     *The medium objects set method
     */

    public int setMediumObjects()

    {
        int mediumObjects = 24;
        return mediumObjects;
    }

    /**
     *The hard fuel set method
     */

    public int setHardFuel()
    {
        int percentageOfFuel = 50;
        return percentageOfFuel;
    }

    /**
     *The hard highway length set method
     */

    public int setHardHighway()
    {
        Random randomObj = new Random();
        int highwayLength = randomObj.nextInt(30,50);
        return highwayLength;
    }

    /**
     *The hard highway objects set method
     */

    public int setHardObjects()

    {
        int hardObjects = 45;
        return hardObjects;
    }

}
