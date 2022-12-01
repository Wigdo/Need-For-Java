/**
 *This class keeps score in the "Need for Java" game.
 *
 *This class holds the value of the variables fuel and health.
 *
 *@author Ariel Wigdorowitz
 *@version 2022.06.19
 */

public class Score
{
    //the player's current fuel level
    private float fuel;
    //the player's current health level
    private double health;

    public Score ()
    {
        fuel = 50;
        health = 50;
    }

    public Score (float fuel, double health)
    {
        this.fuel = fuel;
        this.health = health;
    }

    /**
     *The display method
     *@return returns the current score
     */

    public String display ()
    {
        return ("Your current fuel is : " + fuel + "\nYour current health is: " + health);
    }

    /**
     *The fuel get method
     *@return returns the current fuel
     */

    public float getFuel()
    {
        return fuel;
    }

    /**
     *The health get method
     *@return returns the current health
     */

    public double getHealth()
    {
        return health;
    }

    /**
     *The fuel set method
     */

    public void setCurrentFuel(float fuel)
    {
        this.fuel = fuel;
    }

    /**
     *The health set method
     */

    public void setCurrentHealth(double health)
    {
        this.health = health;
    }
}
