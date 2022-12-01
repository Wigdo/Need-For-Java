/**
 *This class is the main class of the "Need for Java" game.
 *This class connects the user's input with the Map class as well as performing methods which start the game.
 *
 *@author Ariel Wigdorowitz
 *@version 2022.06.19
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Game
{
    //An instance of the Vehicle class
    private Vehicle vehicleObj;
    //an instance of the Map class
    private Map map;
    //an instance of the Input class
    private Input input;
    //an instance of the Score class
    private Score score;
    //an ArrayList storing vehicle selection options
    private ArrayList<Vehicle> vArray;
    //an instance of the IO class
    private IO io;
    //an instance of the Validation class
    private Validation validObj;
    //the player's current fuel
    private float currentFuel;
    //the player's current health
    private double currentHealth;
    //the player's vehicle's boost speed
    private int boostSpeed;
    //the number of highway sections the player has traversed
    private int count;
    //the player's name
    private String newName;
    private float maxFuel;
    //the player's maximum fuel
    public Game ()
    {
        input = new Input();
        validObj = new Validation();
        //welcomeMessage();
        inputName();
        io = new IO();
        currentFuel = 0;
        currentHealth = 0;
        score = new Score(0,0);
        setMap();
        findVehicle();
        getStartingStats();
        System.out.println("\nHighway length is: " + map.getHighwayDifficulty() + "\n");
        map.display(0);
        movement();
    }

    /**
     *Checks whether the user has died
     */

    public void deathCheck()
    {
        if (currentHealth <= 0)
        {
            System.out.println("Sorry " + newName + ", you have died, game over");
            io.writeFile("Sorry " + newName + ", you have died, better luck next time! You travelled a distance of " + count + " units.");
            System.exit(0);
        }

        else if (currentFuel <= 0)
        {
            System.out.println("You have run out of fuel, game over");
            io.writeFile("Sorry " + newName + ", you have run out of fuel! You travelled a distance of " + count + " units.");
            System.exit(0);
        }
    }

    /**
     *Calls the IO class, reads the vehicles.txt file and creates an ArrayList of the vehicles listed within it.
     */

    public void findVehicle()
    {
        IO io = new IO();
        vArray = new ArrayList<>();

        String[] input = io.readFile().split("\n");
        int[] numberInput = new int[3];

        for (int i = 0; i < input.length; i++)
        {
            String[] vehicleNames = input[i].split(",");

            try
            {
                numberInput[0] =  Integer.parseInt(vehicleNames[1]);
                numberInput[1] =  Integer.parseInt(vehicleNames[2]);
                numberInput[2] =  Integer.parseInt(vehicleNames[3]);

                Vehicle vehicle = new Vehicle(vehicleNames[0],numberInput[1],numberInput[0],numberInput[2]);
                vArray.add(vehicle);
            }

            catch (Exception e)
            {
                System.out.println("could not parse integer");
            }
        }
    }

    /**
     *Checks to ensure that on fuel pickup, fuel never exceeds MaxFuel.
     */
    public void fuelCheck ()

    {
        if (currentFuel > maxFuel)
        {
            currentFuel = maxFuel;
        }
    }

    /**
     *Checks which lane the "@" String is in.
     *@return current lane the "@" String is in.
     */

    public int getPos (int pos)
    {
        int lane = 0;
        pos = pos;
        String a = map.getIndex1(pos);
        String b = map.getIndex2(pos);
        String c = map.getIndex3(pos);

        if(a == "@")
        {
            lane = 1;
        }

        else if (b == "@")

        {
            lane = 2;
        }

        else if (c == "@")
        {
            lane = 3;
        }
        return lane;
    }

    /**
     *Receives user's vehicle selection and sets the starting values of that vehicle selection in the Score class.
     */

    public void getStartingStats()
    {
        int fuelDiff = map.getFuelDiff();
        int vehicleS = 0;
        Validation validObj = new Validation();

        do
        {
            try
            {
                System.out.println("Please select your vehicle:\n" +
                        "Press 1 for Motorcycle \nPress 2 for Car\nPress 3 for Garbage Truck\nPress 4 for GoKart");
                vehicleS = input.acceptIntegerInput();

            }

            catch (Exception e)
            {
                System.out.println("Please enter a number");
                System.out.println("Default vehicle has been selected, Car");
                vehicleS = 2;
            }
        }
        while (vehicleS <= 0 || vehicleS > 4);

        Vehicle thisVehicle = vArray.get(vehicleS - 1);

        boostSpeed = thisVehicle.getBoostSpeed();
        int startHealth = thisVehicle.getHealth();
        maxFuel = thisVehicle.getMaxFuel();

        System.out.println(thisVehicle.display());

        maxFuel = (fuelDiff / 100f) * maxFuel;

        score.setCurrentFuel(maxFuel);
        score.setCurrentHealth(startHealth);
        System.out.println(score.display() + " Your max fuel: " + maxFuel);

        currentFuel = maxFuel;
        currentHealth = startHealth;
    }

    /**
     *Receives user's name.
     *@return the user's name.
     */

    /*public void welcomeMessage ()
    {
        System.out.println

                (
                "_  _            _    __             _
                                | \| |___ ___ __| |  / _|___ _ _    (_)__ ___ ____ _
                                    | .` / -_) -_) _` | |  _/ _ \ '_|   | / _` \ V / _` |
            |_|\_\___\___\__,_| |_| \___/_|    _/ \__,_|\_/\__,_|
                                   |__/

            "
                        );
    }
    */

    public String inputName()
    {
        do
        {
            System.out.println("\nPlease enter your name");

            newName = input.acceptStringInput();

            if (validObj.isBlank(newName) == true)
            {
                System.out.println("Your name should not be blank\n");
            }

            if (validObj.isWithinRange(newName) == false)
            {
                System.out.println("Please enter a name between 3 and 12 characters long");
            }
        }
        while (validObj.isBlank(newName) == true || validObj.isWithinRange(newName) == false);
        return newName;
    }


    /**
     *The main method, creates an instance of the Game class.
     */

    public static void main (String[] args)
    {
        Game gameOn = new Game();
    }

    /**
     *Receives user input for their next movement (move forward, swerve up, swerve down or boost) checks whether user's movement
     *is valid and amend's the user's score by updating the Score class.
     */
    public void movement ()
    {

        int nextInput = 0;
        int i = 0;
        count = 0;
        try {
            for (i = 0; i < map.getHighwayDifficulty(); i++)
            {
                deathCheck();
                System.out.println("Press 1 to move forward. \nPress 2 to swerve up. \nPress 3 to swerve down. \nPress 4 to boost.\n"
                        + "---".repeat(40) + "\n");

                try
                {
                    nextInput = input.acceptIntegerInput();
                }

                catch (InputMismatchException e)
                {
                    System.out.println("You have entered an invalid value, game exiting...");
                    break;
                }

                if (nextInput == 1)
                {
                    map.moveForward(i + 1, i);
                    map.display(i + 1);
                    currentFuel = currentFuel - 1;
                    score.setCurrentFuel(currentFuel);
                    count++;

                    if (map.getIndex1(i) == "F" && getPos(i + 1) == 1)
                    {
                        currentFuel = currentFuel + 10;
                        fuelCheck();
                        score.setCurrentFuel(currentFuel);
                        score.display();
                    }

                    else if (map.getIndex1(i) == "R" && getPos(i + 1) == 1)
                    {
                        currentHealth = currentHealth - 20;
                        score.setCurrentHealth(currentHealth);
                        score.display();
                    }

                    else if (map.getIndex1(i) == "T" && getPos(i + 1) == 1)
                    {
                        currentHealth = currentHealth - 45;
                        score.setCurrentHealth(currentHealth);
                        score.display();
                    }

                    else if (map.getIndex1(i) == "O" && getPos(i + 1) == 1)
                    {
                        currentHealth = currentHealth - 60;
                        score.setCurrentHealth(currentHealth);
                        score.display();
                    }


                    if (map.getIndex2(i) == "F" && getPos(i + 1) == 2)
                    {
                        currentFuel = currentFuel + 10;
                        fuelCheck();
                        score.setCurrentFuel(currentFuel);
                        score.display();
                    }

                    else if (map.getIndex2(i) == "R" && getPos(i + 1) == 2)
                    {
                        currentHealth = currentHealth - 20;
                        score.setCurrentHealth(currentHealth);
                        score.display();
                    }

                    else if (map.getIndex2(i) == "T" && getPos(i + 1) == 2)
                    {
                        currentHealth = currentHealth - 45;
                        score.setCurrentHealth(currentHealth);
                        score.display();
                    }

                    else if (map.getIndex2(i) == "O" && getPos(i + 1) == 2)
                    {
                        currentHealth = currentHealth - 60;
                        score.setCurrentHealth(currentHealth);
                        score.display();
                    }


                    if (map.getIndex3(i) == "F" && getPos(i + 1) == 3)
                    {
                        currentFuel = currentFuel + 10;
                        fuelCheck();
                        score.setCurrentFuel(currentFuel);
                        score.display();
                    }

                    else if (map.getIndex3(i) == "R" && getPos(i + 1) == 3)
                    {
                        currentHealth = currentHealth - 20;
                        score.setCurrentHealth(currentHealth);
                        score.display();
                    }

                    else if (map.getIndex3(i) == "T" && getPos(i + 1) == 3)
                    {
                        currentHealth = currentHealth - 45;
                        score.setCurrentHealth(currentHealth);
                        score.display();
                    }

                    else if (map.getIndex3(i) == "O" && getPos(i + 1) == 3)
                    {
                        currentHealth = currentHealth - 60;
                        score.setCurrentHealth(currentHealth);
                        score.display();
                    }

                    System.out.println(score.display());
                }

                if (nextInput == 2)
                {

                    if (map.getIndex2(i) == "@")
                    {
                        String temp = map.getIndex1(i+1);
                        map.setIndexOne(i, "@");
                        map.setIndexTwo(i, map.getIndex1(i-1));
                        map.moveForward(i + 1, i);
                        map.display(i + 1);
                        currentFuel = currentFuel - 2;
                        score.setCurrentFuel(currentFuel);
                        count ++;

                        if (temp == "F")
                        {
                            currentFuel = currentFuel + 10;
                            fuelCheck();
                            score.setCurrentFuel(currentFuel);
                            System.out.println(score.display());
                        }

                        else if (temp == "R")
                        {
                            currentHealth = currentHealth - 20;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else if (temp == "T")
                        {
                            currentHealth = currentHealth - 45;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else if (temp == "O")
                        {
                            currentHealth = currentHealth - 60;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else
                        {
                            System.out.println(score.display());
                        }
                    }

                    else if (map.getIndex3(i) == "@")
                    {
                        String temp = map.getIndex2(i+1);
                        map.setIndexTwo(i, "@");
                        map.setIndexThree(i, "~");
                        map.moveForward(i + 1, i);
                        map.display(i + 1);
                        currentFuel = currentFuel - 2;
                        score.setCurrentFuel(currentFuel);
                        count ++;

                        if (temp == "F")
                        {
                            currentFuel = currentFuel + 10;
                            fuelCheck();
                            score.setCurrentFuel(currentFuel);
                            System.out.println(score.display());
                        }

                        else if (temp == "R")
                        {
                            currentHealth = currentHealth - 20;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else if (temp == "T")
                        {
                            currentHealth = currentHealth - 45;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else if (temp == "O")
                        {
                            currentHealth = currentHealth - 60;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else
                        {
                            System.out.println(score.display());
                        }
                    }

                    else
                    {
                        System.out.println("It is not possible to swerve up from this lane.");
                        i--;
                        map.display(i);
                    }
                }

                if (nextInput == 3)
                {
                    if (map.getIndex1(i) == "@")
                    {
                        String temp = map.getIndex2(i+1);
                        map.setIndexTwo(i, "@");
                        map.setIndexOne(i, "~");
                        map.moveForward(i + 1, i);
                        map.display(i + 1);
                        currentFuel = currentFuel - 2;
                        score.setCurrentFuel(currentFuel);
                        count ++;

                        if (temp == "F")
                        {
                            currentFuel = currentFuel + 10;
                            fuelCheck();
                            score.setCurrentFuel(currentFuel);
                            System.out.println(score.display());
                        }

                        else if (temp == "R")
                        {
                            currentHealth = currentHealth - 20;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else if (temp == "T")
                        {
                            currentHealth = currentHealth - 45;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else if (temp == "O")
                        {
                            currentHealth = currentHealth - 60;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else
                        {
                            System.out.println(score.display());
                        }

                    }

                    else if (map.getIndex2(i) == "@")
                    {   String temp = map.getIndex3(i+1);
                        map.setIndexThree(i, "@");
                        map.setIndexTwo(i, "~");
                        map.moveForward(i + 1, i);
                        map.display(i + 1);
                        currentFuel = currentFuel - 2;
                        score.setCurrentFuel(currentFuel);
                        count ++;

                        if (temp == "F")
                        {
                            currentFuel = currentFuel + 10;
                            fuelCheck();
                            score.setCurrentFuel(currentFuel);
                            System.out.println(score.display());
                        }

                        else if (temp == "R")
                        {
                            currentHealth = currentHealth - 20;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else if (temp == "T")
                        {
                            currentHealth = currentHealth - 45;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else if (temp == "O")
                        {
                            currentHealth = currentHealth - 60;
                            score.setCurrentHealth(currentHealth);
                            System.out.println(score.display());
                        }

                        else
                        {
                            System.out.println(score.display());
                        }

                    }

                    else
                    {
                        System.out.println("It is not possible to swerve down from this lane.");
                        i--;
                        map.display(i);
                    }
                }

                if (nextInput == 4)
                {
                    map.moveForward(i + boostSpeed, i);
                    map.display(i + boostSpeed);
                    count += boostSpeed;
                    i += boostSpeed - 1;
                    currentFuel = currentFuel - (boostSpeed * 3);
                    score.setCurrentFuel(currentFuel);
                    System.out.println(score.display());
                }

                else
                {
                    System.out.println("Please make a valid selection");
                }
            }
        }

        catch (Exception e)
        {
            System.out.println ("Congratulations " + newName + ", You have escaped !");
            io.writeFile("Congratulations " + newName + ", you have escaped ! You have travelled a distance of " + map.getHighwayDifficulty() + " units!");
        }
    }

    /**
     *Sets the game's map by calling an instance of the Map class.
     */

    public void setMap()

    {
        map = new Map();
        map.laneOne();
        map.laneTwo();
        map.laneThree();
        map.setStart();
        map.addObstacles();
    }

}
