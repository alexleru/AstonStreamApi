package ru.alexleru;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("Первоначальный список");
        transactions
                .stream()
                .forEach(System.out::println);

        var HW1 = "1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).";

        System.out.println("\n" + HW1);
        transactions
                .stream()
                .filter(it -> it.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);

        var HW2 = "2. Вывести список неповторяющихся городов, в которых работают трейдеры.";
        System.out.println("\n" + HW2);

        transactions
                .stream()
                .map(it -> it.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);


        var HW3 = "3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.";
        System.out.println("\n" + HW3);
        transactions
                .stream()
                .filter(it -> it.getTrader().getCity().equals("Cambridge"))
                .map(it -> it.getTrader().getName())
                .distinct()
                .sorted()
                .forEach(System.out::println);

        var HW4 = "4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном\n" +
                "порядке.";
        System.out.println("\n" + HW4);
        var finalString = transactions
                .stream()
                .filter(it -> it.getTrader().getCity().equals("Cambridge"))
                .map(it -> it.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(finalString);

        var HW5 = "5. Выяснить, существует ли хоть один трейдер из Милана?";
        System.out.println("\n" + HW5);
        var result = transactions
                .stream()
                .anyMatch(it -> it.getTrader().getCity().equals("Milan"));
        System.out.println(result ? "Да" : "Нет");

        var HW6 = "6. Вывести суммы всех транзакций трейдеров из Кембриджа.";
        System.out.println("\n" + HW6);

        var resultSumIntStream = transactions
                .stream()
                .mapToInt(Transaction::getValue)
                .sum();
        var resultSumReduce = transactions
                .stream()
                .filter(it -> it.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(0, (sum, element) -> sum + element);
        System.out.println(resultSumReduce);

        var HW7 = "7. Какова максимальная сумма среди всех транзакций?";
        System.out.println("\n" + HW7);
        var resultMaxValueIntStream = transactions
                .stream()
                .mapToInt(Transaction::getValue)
                .max()
                .getAsInt();

        var resultMaxValue = transactions
                .stream()
                .map(Transaction::getValue)
                .max(Comparator.comparingInt(it -> it))
                .get();

        System.out.println(resultMaxValueIntStream);

        var HW8 = "8. Найти транзакцию с минимальной суммой.";
        System.out.println("\n" + HW8);

        var minTransaction = transactions
                .stream()
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .reduce((f, s) -> f)
                .get();

//        transactions
//                .stream()
//                .sorted(Comparator.comparingInt(Transaction::getValue))
//                .limit(1)
//                .forEach(System.out::println);


        System.out.println(minTransaction);


    }
}