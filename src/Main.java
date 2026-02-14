import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int step = 0;
        int personX;
        int personY;
        int personLive = 3;
        int sizeBoard = 5;
        personX = 1 + sizeBoard / 2;
        personY = 1 + sizeBoard / 2;
        String person = "\uD83E\uDDDF\u200D";
        String monster = "\uD83E\uDDD9\u200D";
        String gamingField = "+ —— + —— + —— +\n"
                + "|    |    |    |\n"
                + "+ —— + —— + —— +\n"
                + "|    | " + monster + " |    |\n"
                + "+ —— + —— + —— +\n"
                + "| " + person + " |    |    |\n"
                + "+ —— + —— + —— +";
        boolean running = true;
        System.out.println("Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        switch (answer) {
            case "ДА":
                while (running) {
                    System.out.println(gamingField);
                    System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку;");
                    System.out.println("Координаты персонажа - (x: " + personX + ", y: " + personY + "))");

                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    if (x == personX && y == personY) {
                        System.out.println("Неккоректный ход");
                    } else if (Boolean.logicalXor(Math.abs(x - personX) == 1, Math.abs(y - personY) == 1)) {
                        personX = x;
                        personY = y;
                        step += 1;
                        System.out.println("Ход корректный; Новые координаты: " +
                                personX + ", " + personY + "\nХод номер: " + step);
                    } else {
                        System.out.println("Координаты не изменены");
                    }
                }
                break;
            case "НЕТ":
                System.out.println("Жаль, приходи еще!");
                break;
            default:
                System.out.println("Данные введены некорректно");
        }
    }
}