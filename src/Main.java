import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int step = 0;

        int sizeBoard = 5;

        Person person = new Person(sizeBoard);
        String castle = "\uD83C\uDFF0";
        String nothing = "  ";

        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— ".repeat(sizeBoard) + "+";
        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = nothing;
            }
        }

        Random r = new Random();

        board[person.getY()][person.getX()] = person.getImage();

        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;
        board[castleY][castleX] = castle;

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 5;

        Monster[] arrMonster = new Monster[countMonster];
        int count = 0;
        Monster el;
        while (count < countMonster) {
            if (count % 3 == 0) {
                el = new BigMonster(sizeBoard);
            } else {
                el = new Monster(sizeBoard);
            }
            if (board[el.getY()][el.getX()].equals(nothing)) {
                board[el.getY()][el.getX()] = el.getImage();
                arrMonster[count] = el;
                count++;
            }

        }


        System.out.println("Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        switch (answer) {
            case "ДА":
                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = sc.nextInt();
                while ((difficultGame > 5) || (difficultGame < 1)) {
                    System.out.println("Вбейте число от 1 до 5 включительно:");
                    difficultGame = sc.nextInt();
                }
                System.out.println("Выбранная сложность:\t" + difficultGame);

                while (true) {
                    board[person.getY()][person.getX()] = person.getImage();
                    for (String[] raw : board) {
                        System.out.println(wall);
                        for (String col : raw) {
                            System.out.print(leftBlock + col + " ");
                        }
                        System.out.println(rightBlock);
                    }
                    System.out.println(wall);

                    System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку)");
                    System.out.println("Координаты персонажа - (x: " + (person.getX() + 1) + ", y: " + (person.getY() + 1) + "))");

                    int x = sc.nextInt() - 1;
                    int y = sc.nextInt() - 1;
                    if ((x == person.getX() && y == person.getY()) || !(0 <= x && x < sizeBoard && 0 <= y && y < sizeBoard)) {
                        System.out.println("Неккоректный ход");
                    } else if (person.moveCorrect(x, y)) {
                        if (board[y][x].equals("  ")) {
                            board[person.getY()][person.getX()] = "  ";
                            person.move(x, y);
                            step++;
                        } else if (board[y][x].equals(castle)) {
                            System.out.println("Вы прошли игру!");
                            System.out.println("Итоги:\n" + "Сделано " + step + " шагов\n" + "Последняя координата " + (person.getX() + 1) + "," + (person.getY() + 1));
                            break;
                        } else {
                            for (Monster monst : arrMonster) {
                                if (monst.conflictPerson(x, y)) {
                                    if (monst.taskMonster(difficultGame)) {
                                        board[person.getY()][person.getX()] = "  ";
                                        person.move(x, y);
                                        step++;
                                        System.out.println("Ура, Вы победили монстра!");
                                    } else {
                                        person.downLive();
                                    }
                                    break;
                                }
                            }
                            if (person.getLive() <= 0) {
                                System.out.println("Потрачено. Игра закончиалсь.");
                                System.out.println("Закончились жизни. Итоги:\n" + "Сделано " + step + " шагов\n" + "Последняя координата " + (person.getX() + 1) + "," + (person.getY() + 1));
                                System.exit(0);
                            } else if (person.getLive() == 1) {
                                System.out.println("Вы наткнулись на монстра. Ваши координаты: " + (person.getX() + 1) + ", " + (person.getY() + 1) +
                                        "\nУ вас осталось: " + person.getLive() + " жизнь");
                            } else {
                                System.out.println("Вы наткнулись на монстра. Ваши координаты: " + (person.getX() + 1) + ", " + (person.getY() + 1) +
                                        "\nУ вас осталось: " + person.getLive() + " жизней");
                            }
                        }
                        System.out.println("Ход корректный; Новые координаты: " +
                                (person.getX() + 1) + ", " + (person.getY() + 1) + "\nХод номер: " + step);
                    } else {
                        System.out.println("Координаты не изменены");
                    }
                    if (person.getLive() <= 0) {
                        System.out.println("Закончились жизни. Итоги:\n" + "Сделано " + step + " шагов\n" + "Последняя координата " + (person.getX() + 1) + "," + (person.getY() + 1));
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