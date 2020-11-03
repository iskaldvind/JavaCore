package io.iskaldvind;

import io.iskaldvind.animals.*;
import io.iskaldvind.tools.Plate;


public class Main {

    public static void main(String[] args) {

        Cat[] cats = {
                new Cat("Barsik"),
                new Cat("Murzik"),
                new Cat("Tom"),
                new Cat("Vaska"),
                new Cat("Pushok")};

        Plate plate = new Plate(35);

        feedCats(cats, plate);
        plate.addFood(20);
        feedCats(cats, plate);
    }

    private static void feedCats(Cat[] cats, Plate plate) {
        for(Cat cat: cats) {
            cat.eat(plate);
        }

        for (Cat cat: cats) {
            cat.logSatiety();
        }
    }
}
