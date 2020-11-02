package io.iskaldvind;

public class Cat extends Animal {

    static int count = 0;

    public Cat(String name) {
        super(name, 200, 0);
        count++;
    }

    @Override
    protected void run(int distance) {
        if (distance > 0 && distance <= this.runLimit) {
            System.out.println(this.name + " runs " + distance + " meters");
        } else {
            System.out.println(this.name + " politely refuses to run " + distance + " meters");
        }
    }

    @Override
    protected void swim(int distance) {
        System.out.println(this.name + " thinks swimming is a bad idea");
    }
}
