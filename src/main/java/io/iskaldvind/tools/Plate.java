package io.iskaldvind.tools;

public class Plate {

    private int food;

    public Plate(int amount) {
        this.food = amount;
        System.out.println("Make plate with " + amount + " food");
    }

    public void addFood(int amount) {
        this.food += amount;
        System.out.println("Added " + amount + " food to plate");
    }

    public boolean takeFood(int amount) {
        if (this.food < amount) return false;
        this.food -= amount;
        return true;
    }
}
