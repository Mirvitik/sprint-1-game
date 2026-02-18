import java.util.Random;
import java.util.Scanner;

public class Main {
    static boolean taskMonster() {
        Random r = new Random();
        int x = r.nextInt(100);
        int y = r.nextInt(100);
        int trueAnswer = x - y;
        System.out.println("Реши пример: " + x + " - " + y + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        if (trueAnswer == ans) {
            System.out.println("Верно! Ты победил монстра");
            return false;
        }
        System.out.println("Ты проиграл эту битву!");
        return true;
    }

    public static void main(String[] args) {
        int step = 0;
        int personX;
        int personY;

        int personLive = 3;

        int sizeBoard = 5;
        personX = 1 + sizeBoard / 2;
        personY = 1 + sizeBoard / 2;
        String person = "Гг";
        String monster = "Мм";
        String castle = "З ";

        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— ".repeat(sizeBoard) + "+";
        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }
        }

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        Random r = new Random();
        for (int i = 0; i <= countMonster; i++) {
            board[r.nextInt(sizeBoard - 1)][r.nextInt(sizeBoard)] = monster;
        }

        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;
        board[castleY][castleX] = castle;
        System.out.println("Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        switch (answer) {
            case "ДА":
                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);

                int maxStep = 2;
                while (true) {
                    board[personY - 1][personX - 1] = person;
                    for (String[] raw : board) {
                        System.out.println(wall);
                        for (String col : raw) {
                            System.out.print(leftBlock + col + " ");
                        }
                        System.out.println(rightBlock);
                    }
                    System.out.println(wall);

                    System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку;");
                    System.out.println("Координаты персонажа - (x: " + personX + ", y: " + personY + "))");

                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    System.out.println(x + ", " + y);
                    if ((x == personX && y == personY) || !(0 <= x && x <= sizeBoard && 0 <= y && y <= sizeBoard)) {
                        System.out.println("Неккоректный ход");
                    } else if (Boolean.logicalXor(Math.abs(x - personX) == 1, Math.abs(y - personY) == 1)) {
                        if (board[y - 1][x - 1].equals("  ")) {
                            board[personY - 1][personX - 1] = "  ";
                            personX = x;
                            personY = y;
                            step++;
                            System.out.println("Ход корректный; Новые координаты: " + personX + ", " + personY +
                                    "\nХод номер: " + step);
                        } else if (board[y - 1][x - 1].equals(castle)) {
                            System.out.println("Вы прошли игру!");
                            break;
                        } else {
                            if (taskMonster()) {
                                personLive -= 1;
                            }
                            if (personLive <= 0) {
                                System.out.println("Потрачено. Игра закончиалсь.");
                            } else if (personLive == 1) {
                                System.out.println("Вы наткнулись на монстра. Ваши координаты: " + personX + ", " + personY +
                                        "\nУ вас осталось: " + personLive + " жизнь");
                            } else {
                                System.out.println("Вы наткнулись на монстра. Ваши координаты: " + personX + ", " + personY +
                                        "\nУ вас осталось: " + personLive + " жизней");
                            }
                        }
                        System.out.println("Ход корректный; Новые координаты: " +
                                personX + ", " + personY + "\nХод номер: " + step);
                    } else {
                        System.out.println("Координаты не изменены");
                    }
                    if (personLive <= 0) {
                        System.out.println("Закончились жизни. Итоги:\n" + "Сделано " + step + " шагов\n" + "Последняя координата " + personX + "," + personY);
                        break;
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