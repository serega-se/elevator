
import entity.Cabin;

import java.util.Scanner;

/**
 * Основной класс приложения
 */
public class Main {

    public static void main(String[] args) {

        try {
            //Создаем лифт, запускаем его работу в отдельном потоке
            Cabin cabin = new Cabin(1, 9);
            Thread cabinTread = new Thread(cabin);
            cabinTread.start();

            //Выполняем последовательные вызовы лифта,
            //посредствам запроса номера этажа в окне терминала
            Scanner inputNumber = new Scanner(System.in);

            do {
                System.out.print("Введите этаж для вызова лифта:");
                if(inputNumber.hasNextInt()){
                    cabin.addElevatorCall(inputNumber.nextInt());
                }else{
                    System.out.printf("Некорректное значение для номера этажа :%s%n", inputNumber.next());
                }
            }while (inputNumber.hasNext());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
