package io.iskaldvind;

public class Main {

    public static void main(String[] args) {

        Dog dog1 = new Dog("Woolfy");
        dog1.run(100);
        Dog dog2 = new Dog("Meatball");
        dog2.swim(5);
        Dog dog3 = new Dog("Muhtar");
        dog3.swim(1000);
        System.out.println(Dog.count + " dogs");

        Cat cat1 = new Cat("Barsik");
        cat1.run(150);
        Cat cat2 = new Cat("Tom");
        cat2.swim(100);
        System.out.println(Cat.count + " cats");

        System.out.println(Animal.count + " animals");
    }
}
