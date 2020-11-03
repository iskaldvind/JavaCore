package io.iskaldvind.animals;

import io.iskaldvind.tools.Plate;

public class Dog extends Animal {

    public static int count = 0;

    public Dog(String name) {
        super(name, 500, 10, 50);
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
        if (distance > 0 && distance <= this.swimLimit) {
            System.out.println(this.name + " swims " + distance + " meters");
        } else {
            System.out.println(this.name + " politely refuses to swim " + distance + " meters");
        }
    }
}
