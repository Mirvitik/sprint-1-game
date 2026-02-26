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
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < difficultGame; ++i) {
            char x = (char) (r.nextInt(42) + 48);
            b.append(x);
        }
        String test = b.toString();
        System.out.println(test);
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine().strip();
        StringBuilder b2 = new StringBuilder();
        for (int i = answer.length() - 1; i >= 0; i--) {
            b2.append(answer.charAt(i));
        }
        String newStr = b2.toString();
        if (newStr.equals(test)) {
            System.out.println("Верно! Ты победил монстра");
            return true;
        }
        System.out.println("Ты проиграл эту битву!");
        return false;
    }

}
