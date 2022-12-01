/**
 *This class creates the map for the "Need for Java" game.
 *
 *This class creates three lanes which the player can traverse.
 *
 *@author Ariel Wigdorowitz
 *@version 2022.06.19
 */


import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Map
{
    //the map's first lane
    private ArrayList<String> arr1;
    //the map's second lane
    private ArrayList<String> arr2;
    //the map's third lane
    private ArrayList<String> arr3;
    //an instance of the SetDifficulty class
    private SetDifficulty diff;
    //an instance of the Obstalces class
    private Obstacles obsObj;
    //the highway's lenght (based on difficulty)
    private int hwyL;
    //the highway's obstacles (based on difficulty)
    private int hwyO;
    //the player's amount of fuel (based on difficulty)
    private int hwyF;

    public Map()
    {
        diff = new SetDifficulty();
        obsObj = new Obstacles();
        arr1 = new ArrayList<>();
        arr2 = new ArrayList<>();
        arr3 = new ArrayList<>();

        diff.getUserInput();
        hwyL = diff.getHighwayDifficulty();
        hwyO = diff.getObstalceDifficulty();
        hwyF = diff.getFuelDifficulty();
    }

    /**
     *Randomly adds obstalces to the three lanes randomly
     */

    public void addObstacles()
    {
        Randomiser random = new Randomiser();
        Random randObj = new Random();

        for (int i=0;i<=hwyO;i++)
        {
            int randArray = random.randomX();
            String temp = obsObj.nextObstacle();

            if (randArray == 1)
            {
                int y = randObj.nextInt(3,hwyL);
                arr1.set(y, temp);
            }

            if (randArray == 2)
            {
                int y = randObj.nextInt(3,hwyL);
                arr2.set(y, temp);
            }

            if (randArray == 3)
            {
                int y = randObj.nextInt(3,hwyL);
                arr3.set(y, temp);
            }
        }
    }

    /**
     *The display method, prints out the current map (as moved by the player)
     *this display method collates the three "lane display methods" into one easier to use method.
     *@param mover int value which moves the map forward by that amount
     */

    public void display(int mover) //also known as "display"
    {
        mover = mover;

        printLaneOne(mover);
        System.out.println("\n");
        printLaneTwo(mover);
        System.out.println("\n");
        printLaneThree(mover);
        System.out.println("\n");
        System.out.println("---".repeat(40));
    }

    /**
     *Return the player's max fuel
     *@return the player's max fuel
     */
    public int getFuelDiff()
    {
        return hwyF;
    }

    /**
     *Return the highway's total length
     *@return the highway's total length
     */

    public int getHighwayDifficulty ()
    {
        return hwyL;
    }

    /**
     *Returns the string value at the specified index for the first lane of the highway
     *@param any index along the first lane of the highway array
     *@return value at index
     */

    public String getIndex1 (int index)
    {
        if (arr1.size()>= index)
        {
            return arr1.get(index);
        }
        else
        {
            return null;
        }
    }

    /**
     *Returns the string value at the specified index for the second lane of the highway
     *@param any index along the second lane of the highway array
     *@return value at index
     */

    public String getIndex2 (int index)
    {
        if (arr2.size() >= index)
        {
            return arr2.get(index);
        }
        else
        {
            return null;
        }
    }
    /**
     *Returns the string value at the specified index for the third lane of the highway
     *@param any index along the third lane of the highway array
     *@return value at index
     */

    public String getIndex3 (int index)
    {
        if (arr3.size() >= index)
        {
            return arr3.get(index);
        }
        else
        {
            return null;
        }
    }

    /**
     *Return the number of obstalces on the highway
     *@return the number of obstalces on the highway
     */

    public int getObstalceDifficulty()
    {
        return hwyO;
    }

    /**
     *Creates the first lane
     */

    public void laneOne()
    {
        int i=0;
        for (i=0;i<hwyL;i++)
        {
            arr1.add("~");
        }
    }

    /**
     *Creates the second lane
     */

    public void laneTwo()
    {
        int i=0;
        for (i=0;i<hwyL;i++)
        {
            arr2.add("~");
        }
    }

    /**
     *Creates the third lane
     */

    public void laneThree()
    {
        int i=0;
        for (i=0;i<hwyL;i++)
        {
            arr3.add("~");
        }
    }

    /**
     *Swaps the values in each of the highway's three lanes
     *@param indexOne is the player's future position
     *@param indexTwo is the player's current position
     */

    public void moveForward(int indexOne, int indexTwo)
    {
        String item = arr1.get(indexOne);
        arr1.set(indexOne, arr1.get(indexTwo));
        arr1.set(indexTwo, item);

        String item2 = arr2.get(indexOne);
        arr2.set(indexOne, arr2.get(indexTwo));
        arr2.set(indexTwo, item2);

        String item3 = arr3.get(indexOne);
        arr3.set(indexOne, arr3.get(indexTwo));
        arr3.set(indexTwo, item3);
    }

    /**
     *Used for testing purposes, prints out the map in its entirety
     */

    public void printFullLane()
    {
        int i=0;
        for(i=0;i<hwyL;i++)
        {
            System.out.print(arr1.get(i));
            System.out.print(arr2.get(i));
            System.out.print(arr3.get(i));
        }
        System.out.print("\n");
    }

    /**
     *A "lane display method" this method prints out the first lane of the highway (as moved by the player)
     *@param mover int value which moves the map forward by that amount
     *@throws IndexOutOfBoundsException if mover exceeds lane length and prints final piece of the highway
     */

    public void printLaneOne(int mover)
    {
        try
        {
            int i = 0;
            for(i = 0 + mover; i < 10 + mover; i++)
            {
                System.out.print(arr1.get(i));
            }
        }
        catch (IndexOutOfBoundsException ioobe)
        {
            System.out.println("||");
        }
    }

    /**
     *A "lane display method" this method prints out the second lane of the highway (as moved by the player)
     *@param mover int value which moves the map forward by that amount
     *@throws IndexOutOfBoundsException if mover exceeds lane length and prints final piece of the highway
     */

    public void printLaneTwo(int mover)
    {
        try
        {
            int i = 0;
            for(i = 0 + mover; i < 10 + mover; i++)
            {
                System.out.print(arr2.get(i));
            }
        }

        catch (IndexOutOfBoundsException ioobe)
        {
            System.out.println("||");
        }
    }

    /**
     *A "lane display method" this method prints out the third lane of the highway (as moved by the player)
     *@param mover int value which moves the map forward by that amount
     *@throws IndexOutOfBoundsException if mover exceeds lane length and prints final piece of the highway
     */

    public void printLaneThree(int mover)
    {
        try
        {
            int i = 0;
            for(i = 0  + mover;i < 10 + mover; i++)
            {
                System.out.print(arr3.get(i));
            }
        }

        catch (IndexOutOfBoundsException ioobe)
        {
            System.out.println("||");
        }
    }

    /**
     *Sets the string value at the specified index for the first lane of the highway
     *@param index, any index along the first lane of the highway array
     *@param pos, the String value to be set at index
     */

    public void setIndexOne(int index, String pos)
    {
        if (arr1.size() >= index)
        {
            arr1.set(index, pos);
        }
    }

    /**
     *Sets the string value at the specified index for the second lane of the highway
     *@param index, any index along the second lane of the highway array
     *@param pos, the String value to be set at index
     */

    public void setIndexTwo(int index, String pos)
    {
        if (arr2.size() >= index)
        {
            arr2.set(index, pos);
        }
    }

    /**
     *Sets the string value at the specified index for the third lane of the highway
     *@param index, any index along the third lane of the highway array
     *@param pos, the String value to be set at index
     */

    public void setIndexThree(int index, String pos)
    {
        if (arr3.size() >= index)
        {
            arr3.set(index, pos);
        }
    }

    /**
     *Randomly sets the player's starting position
     */

    public void setStart()
    {
        Randomiser random = new Randomiser();
        int i=0;
        int position = random.randomX();
        if (position == 1)
        {
            arr1.set(0,"@");
        }
        else if (position == 2)
        {
            arr2.set(0,"@");
        }

        else if (position == 3)
        {
            arr3.set(0,"@");
        }
    }

}
