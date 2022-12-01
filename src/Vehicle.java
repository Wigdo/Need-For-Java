/**
 *This class holds the various vehicle options in the "Need for Java" game.
 *
 *This class holds the values name, maxFuel, boostSpeed and the health of each vehicle.
 *
 *@author Ariel Wigdorowitz
 *@version 2022.06.19
 */

public class Vehicle
{
    //holds the vehicle's boost speed
    private int boostSpeed;
    //holds the vehicle's health
    private int health;
    //holds the vehicle's name
    private String name;
    //holds the vehicle's max fuel capacity
    private int maxFuel;

    public Vehicle ()
    {
        name = "Car";
        maxFuel = 120;
        boostSpeed = 3;
        health = 50;
    }

    public Vehicle (String name, int maxFuel, int boostSpeed, int health)
    {
        this.name = name;
        this.maxFuel = maxFuel;
        this.boostSpeed = boostSpeed;
        this.health = health;
    }

    /**
     *The display method
     *@return returns the selected vehicle
     */

    public String display ()
    {
        return ("You have selected: " + name + "\nBoost Speed = " + boostSpeed + "\nMax Health = " + health);
    }

    /**
     *The boost speed get method
     *@return returns the vehicle's boost speed
     */

    public int getBoostSpeed()
    {
        return boostSpeed;
    }

    /**
     *The name get method
     *@return returns the vehicle's name
     */

    public String getName()
    {
        return name;
    }

    /**
     *The health get method
     *@return returns the vehicle's maximum health
     */

    public int getHealth()
    {
        return health;
    }

    /**
     *The fuel set method
     */

    public int getMaxFuel()
    {
        return maxFuel;
    }

    /**
     *The boost speed set method
     */

    public void setNewBoostSpeed(int boostSpeed)
    {
        this.boostSpeed = boostSpeed;
    }

    /**
     *The health set method
     */

    public void setNewHealth(int health)
    {
        this.health = health;
    }

    /**
     *The name set method
     */

    public void setNewName(String name)
    {
        this.name = name;
    }

    /**
     *The fuel set method
     */

    public void setNewMaxFuel(int maxFuel)
    {
        this.maxFuel = maxFuel;
    }
}
