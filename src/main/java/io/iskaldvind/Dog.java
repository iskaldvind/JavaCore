package io.iskaldvind;

public class Dog extends Animal {

    static int count = 0;

    public Dog(String name) {
        super(name, 500, 10);
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
        if (distance > 0 && distance <= this.swimLimit) {
            System.out.println(this.name + " swims " + distance + " meters");
        } else {
            System.out.println(this.name + " politely refuses to swim " + distance + " meters");
        }
    }
}
