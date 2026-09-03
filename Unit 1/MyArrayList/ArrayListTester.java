import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;

public class ArrayListTester {

    public static void main(String[] args) throws FileNotFoundException {
        MyArrayList<String> test1 = new MyArrayList<String>();
        MyArrayList<Integer> test2 = new MyArrayList<Integer>();
        MyArrayList<Boolean> test3 = new MyArrayList<Boolean>();
        MyArrayList<String> test4 = new MyArrayList<String>(3);

        try {
            test1.set(0, "bums");
        } catch (Exception e) {
            System.out.println("Sorry alex bummed");
        }

        fillStringArray(test1);
        fillIntArray(test2);
        fillBoolArray(test3);
        if (test4.isEmpty()) {
            System.out.println("lol");
        }
        System.out.println(test1.get(2));

        try {
            test1.get(100000);
        } catch (Exception e) {
            System.out.println("Sorry this is out of bounds");
        }

        System.out.println(test1.size());
        System.out.println(test2.size());
        System.out.println(test3.size());
        for (int i = 0; i < 10; i++) {
            test4.add("bum bum bum sahur");
        }
        System.out.println(test4.toString());

        MyArrayList<String> list = new MyArrayList<String>();
        int numThings = 10000000;
        for (int i = 0; i < numThings; i++) {
            list.add("Hi");
        }
        System.out.println("done formulating list");
        String bums = list.toString();
        System.out.println("done toStringing");
    }

    public static void fillStringArray(MyArrayList<String> stringList) throws FileNotFoundException {
        Scanner breedReader = new Scanner(new File("breedsMAL.txt"));
        while (breedReader.hasNext()) {
            stringList.add(breedReader.nextLine());
        }
        breedReader.close();
        Random rng = new Random();
        int seed = rng.nextInt(stringList.size());
        stringList.add(seed, null);
    }

    public static void fillIntArray(MyArrayList<Integer> intList) {
        Random rng = new Random();
        int size = rng.nextInt(1000);
        for (int i = 0; i < size; i++) {
            int number = rng.nextInt(-1000, 1000);
            intList.add(number);
        }
    }

    public static void fillBoolArray(MyArrayList<Boolean> boolList) {
        Random rng = new Random();
        int size = rng.nextInt(1000);
        for (int i = 0; i < size; i++) {
            int number = rng.nextInt(100);
            if (number > 50) {
                boolList.add(true);
            } else {
                boolList.add(false);
            }
        }
    }
}
