package io.iskaldvind.animals;

import io.iskaldvind.tools.Plate;

public class Cat extends Animal {

    public static int count = 0;

    public Cat(String name) {
        super(name, 200, 0, 10);
        count++;
    }

    @Override
    public void run(int distance) {
        if (distance > 0 && distance <= this.runLimit) {
            System.out.println(this.name + " runs " + distance + " meters");
        } else {
            System.out.println(this.name + " politely refuses to run " + distance + " meters");
        }
    }

    public void eat(Plate plate) {
        super.eat(plate, appetite);
    }

    @Override
    public void swim(int distance) {
        System.out.println(this.name + " thinks swimming is a bad idea");
    }
}
