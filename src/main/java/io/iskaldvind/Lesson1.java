package io.iskaldvind;

public class Lesson1 {

    public static void main(String[] args) {
        System.out.println("Lesson 1");

        int i = 1;
        double d = 0.1;
        boolean b = true;
        long l = 1000;
        float f = 10.5F;
        String s = "Hello";
        byte bt = 10;

        System.out.println("ABCD for a=4, b=3, c=2, d=1 ==> " + abcd(4, 3, 2, 1));

        System.out.println("inRange for a=4, b=16 ==> " + inRange(4, 16));

        checkSign(0);

        System.out.println("isNegative for number=-10 ==> " + isNegative(-10));

        sayHello("Alex");

        checkYear(2300);
    }

    public static int abcd(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    public static boolean inRange(int a, int b) {
        int min = 10;
        int max = 20;
        int sum = a + b;
        return sum >= min && sum <= max;
    }

    public static void checkSign(int number) {
        String sign = number < 0 ? "negative" : "positive";
        System.out.println("checkSign for number=" + number + " ==> " + sign);
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }

    public static void sayHello(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public static void checkYear(int year) {
        boolean isBig = year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
        String size = isBig ? "big" : "small";
        System.out.println("checkYear for year=" + year + " ==> " + size);
    }
}
