package org.example;

import java.util.*;

/*
Создай консольное приложение “Калькулятор”. Приложение должно читать из консоли введенные пользователем строки, числа,
 рифметические операции проводимые между ними и выводить в консоль результат их выполнения.
Реализуй класс Main с методом public static String calc(String input). Метод должен принимать строку с арифметическим
выражением между двумя числами и возвращать строку с результатом их выполнения. Ты можешь добавлять свои импорты,
классы и методы. Добавленные классы не должны иметь модификаторы доступа (public или другие)

 */
public class Main {

    private static final String[] arabics = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static final String[] romans = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private static final TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }
    private static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }
    public static String calc(String input)  throws  NumberFormatException{
        int intResult;
        String[] numbers = input.split("[*,+,\\-,\\/]");

        if (numbers.length!=2 || numbers[0].isBlank() || numbers[1].isBlank()) {
            throw new NumberFormatException("Не удалось определить члены выражения");
        }

        String operation = input.substring(numbers[0].length(), numbers[0].length()+1);

        boolean[] isRoman = new boolean[numbers.length];
        int[] val = new int[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            numbers[i] = numbers[i].trim();
            isRoman[i] = numbers[i].matches("\\D+");
            if (isRoman[0]!=isRoman[i]) {
                throw new NumberFormatException("Разный формат записи чисел");
            }
            String[] someNumbers = isRoman[i] ? romans : arabics;
            val[i]=-1;
            for (int j=0; j<someNumbers.length; j++) {
                if (numbers[i].equals(someNumbers[j])) {
                    val[i] = j;
                    break;
                }
            }
            if (val[i]==-1) throw new NumberFormatException("Введено недопустимое число - " + numbers[i]);
        }

        switch (operation) {
            case "+":
                    intResult = val[0]+val[1];
                    break;
            case "-":
                intResult = val[0]-val[1];
                break;
            case "*":
                intResult = val[0]*val[1];
                break;
            case "/":
                intResult = val[0]/val[1];
                break;
            default:
                throw new NumberFormatException("Неизвестная операция");
        }

        if (intResult<1 && isRoman[0]) throw new NumberFormatException("Результат не может быть записан в римскими цифрами");

        if (isRoman[0]) {
            return toRoman(intResult);
        } else {
            return String.valueOf(intResult);
        }
    }

    public static void main(String[] args) throws  NumberFormatException {
        System.out.println("Калькулятор");

        System.out.println("Вычисление выражений формата \"A ? B\", где A и B это числа из из арабских или римских цифр, а \"?\" это операия \"+\", \"-\", \"*\" или \"/\".");
        System.out.println("Для выхода введите пустую строку");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите выражение: ");
            String expression = scanner.nextLine();
            if (expression.isEmpty()) {
                break;
            }
            System.out.println("Выражение: "+expression);
            try {
                System.out.println("Результат: " + calc(expression));
            }
            catch (Exception e){
                System.out.println("Ошибка вычисления: "+e.getMessage());
            }
        }

        System.out.println("Работа завершена");


    }
}