import java.util.Random;

public class Person {
    private int x, y;
    private String image = "\uD83E\uDDD9\u200D";
    private int live = 3;
    Random r = new Random();

    Person(int sizeBoard) {
        y = sizeBoard - 1;
        x = r.nextInt(sizeBoard);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLive() {
        return live;
    }

    public String getImage() {
        return image;
    }


    public boolean moveCorrect(int x, int y) {
        return ((Math.abs(x - this.x) == 1 && Math.abs(y - this.y) == 0) || (Math.abs(x - this.x) == 0 && Math.abs(y - this.y) == 1));
    }

    void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void downLive() {
        live--;
    }
}