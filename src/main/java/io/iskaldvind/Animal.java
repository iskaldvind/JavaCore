package io.iskaldvind;

abstract class Animal {

    static int count = 0;
    protected final int runLimit;
    protected final int swimLimit;
    protected final String name;

    public Animal(String name, int runLimit, int swimLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
        count++;
    }

    protected abstract void run(int distance);
    protected abstract void swim(int distance);
}
