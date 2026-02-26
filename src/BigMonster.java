import java.util.Scanner;

public class BigMonster extends Monster {
    protected String image = "\uD83D\uDC79";

    BigMonster(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public boolean taskMonster(int difficultGame) {
        System.out.println("Напишите данную строку наоборот:");
        difficultGame++;
        String test = "";
        for (int i = 0; i < difficultGame; ++i) {
            char x = (char) (r.nextInt(42) + 48);
            test = test + x;
        }
        System.out.println(test);
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine().strip();
        String newStr = "";
        for (int i = answer.length() - 1; i >= 0; i--) {
            newStr = newStr + answer.charAt(i);
        }
        if (newStr.equals(test)) {
            System.out.println("Верно! Ты победил монстра");
            return false;
        }
        System.out.println("Ты проиграл эту битву!");
        return true;
    }

}
