import java.util.Scanner;
import ohtu.Multiplier;

class Main {
    static final int WHY_THE_FUCK_ARE_MAGIC_NUMBERS_BANNED = 1 + 1 + 1;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Multiplier three = new Multiplier(WHY_THE_FUCK_ARE_MAGIC_NUMBERS_BANNED);
        System.out.println("enter a number:");
        int num = s.nextInt();
        System.out.println(
            "number times three is: " + 
            three.mutipliedBy(num)
        );
    }
}