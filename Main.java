package Calculator;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите математическое выражение:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        System.out.println(calc(input));

    }

    public static String calc(String input) {
        int value1;
        int value2;
        int result;
        String convert;
        input = input.replaceAll(" ", "");
        String[] variables = input.split("[+\\-*/]");

        if (variables.length > 2) {
            throw new RuntimeException("Математическая операция не соотвутсвует ТЗ, должно быть два операнда.");
        }
        if (variables.length == 1) {
            throw new RuntimeException("Выражение, не удовлетворяет требованиям ТЗ, в строке должны производится вычисления: a+b, a-b, a*b, a/b.");
        }
        boolean isRoman1 = variables[0].matches("[IVX]+");
        boolean isRoman2 = variables[1].matches("[IVX]+");

        if ((!isRoman1 && isRoman2) || (isRoman1 && !isRoman2)) {
            throw new RuntimeException("Используются разные системы счисления.");
        }
        if (isRoman1) {
            value1 = romanToArabic(variables[0]);
            value2 = romanToArabic(variables[1]);
        } else {
            value1 = Integer.parseInt(variables[0]);
            value2 = Integer.parseInt(variables[1]);
        }
        if (value1 > 10 || value2 > 10) {
            throw new RuntimeException("Входящие арабские числа не должны быть больше 10.");
        }

        result = calculation(input, value1, value2);
        if (isRoman1) {
            if (result <= 0) {
                throw new RuntimeException("В римской системе нет отрицательных чисел и нуля.");
            }
            convert = arabicToRoman(result);
        } else {
            convert = String.valueOf(result);
        }

        return convert;
    }

    static int calculation(String input, int value1, int value2) {
        if (input.contains("+")) {
            return value1 + value2;
        } else if (input.contains("-")) {
            return value1 - value2;
        } else if (input.contains("*")) {
            return value1 * value2;
        } else if (input.contains("/")) {
            return value1 / value2;
        } else {
            throw new RuntimeException("Неверный знак");
        }
    }

    public static String arabicToRoman(int decimal) {
        String[] arrayOfRom = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        return arrayOfRom[decimal];
    }

    public static int romanToArabic(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new RuntimeException("Входящие римские числа не должны быть больше 10.");
        };
    }
}

