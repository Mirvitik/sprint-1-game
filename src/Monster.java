import java.util.Random;
import java.util.Scanner;

public class Monster {
    protected String image = "\uD83E\uDDDF\u200D";
    private final int x, y;
    Random r = new Random();

    Monster(int sizeBoard) {
        this.y = r.nextInt(sizeBoard);
        this.x = r.nextInt(sizeBoard);
    }

    public String getImage() {
        return image;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean conflictPerson(int perX, int perY) {
        return (perY == this.y && perX == this.x);
    }


    public boolean taskMonster(int difficultGame) {
        int x = r.nextInt(100 * difficultGame);
        int y = r.nextInt(100 * difficultGame);
        int trueAnswer = x - y;
        System.out.println("Реши пример: " + x + " - " + y + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        if (trueAnswer == ans) {
            System.out.println("Верно! Ты победил монстра");
            return true;
        }
        System.out.println("Ты проиграл эту битву!");
        return false;
    }
}
