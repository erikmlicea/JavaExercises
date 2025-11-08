package org.example;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static int factorial(int number) {
        if (number <= 1) {
            return 1;  // caso base
        }
        return number * factorial(number - 1);
    }

    public  static int maxInArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        Arrays.sort(array);
        return array[array.length-1];
    }

    public  static int minInArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        Arrays.sort(array);
        return array[0];
    }

    public static int countVowels(String s) {
        int vowelsCount = 0;
        ArrayList<Character> vowels = new ArrayList<Character>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        for (int i=0; i<s.length(); i++) {
            char tmp = Character.toLowerCase(s.charAt(i));
            if (vowels.contains(tmp)) {
                vowelsCount++;
            }
        }

        return vowelsCount;
    }

    //Walmart test
    public static int minUniqueSum(List<Integer> nums) {
        Set<Integer> usados = new HashSet<>();
        int suma = 0;

        for (int num : nums) {
            // Mientras ya exista, incrementar
            while (usados.contains(num)) {
                num++;
            }
            usados.add(num);
            suma += num;
        }

        return suma;
    }

    //Infosys test
    public static List<Integer> startingWithOnes(List<Integer> array) {
        return array.stream()
                .filter(x -> String.valueOf(x).startsWith("1"))
                .collect(Collectors.toList());
    }

    //Blackrock fun with Anagrams test
    public static List<String> funWithAnagrams(List<String> text) {
        Set<String> seen = new HashSet<>();
        List<String> result = new ArrayList<>();

        for (String word : text) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            if (!seen.contains(key)) {
                seen.add(key);      // Marca este grupo de anagramas
                result.add(word);   // Solo conserva la primera aparición
            }
        }

        Collections.sort(result); // Orden alfabético si lo piden
        return result;
    }

    public static Integer findFirstPrimeNumber(List<Integer> list) {
        return list
                .stream()
                .filter(Main::isPrime)
                .findFirst()
                .orElse(0);
    }

    public static Double meanGreaterThan5(List<Integer> list) {
        return list
                .stream()
                .filter(x -> x>5)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    public static Long countEven(List<Integer> list) {
        return list
                .stream()
                .filter(x -> x%2 == 0)
                .collect(Collectors.counting());
    }

    public static List<String> listToUpperCase(List<String> list) {
        return list
                .stream()
                .map(x -> x.toUpperCase())
                .collect(Collectors.toList());
    }

    public static Integer sumAllElements(List<Integer> list) {
        return list
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static Integer maxNumber(List<Integer> list) {
        return list
                .stream()
                .max(Integer::compare)
                .orElse(0);
    }

    public static List<String> orderByLength(List<String> list) {
        return list
                .stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
    }

    public static List<Integer> removeDuplicated(List<Integer> list) {
        return list
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Integer> retrieveFirst3Elements(List<Integer> list) {
        return list
                .stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List<Integer> retrieveLast3Elements(List<Integer> list) {
        return list
                .stream()
                .skip(list.size() - 3)
                .collect(Collectors.toList());
    }

    public static List<Integer> flatMapList(List<List<Integer>> list) {
        return list
                .stream()
                .flatMap(List::stream)
                .toList();
    }

    public static String concatenateFromStrings(List<String> list) {
        return  list
                .stream()
                .collect(Collectors.joining(", "));
    }

    // Record para almacenar el par de números de la secuencia
    public record Pair(int current, int next) {};

    public static List<Integer> generateFibonacci() {
        return Stream.iterate(new Pair(0, 1), pair -> new Pair(pair.next, pair.current + pair.next))
                .limit(10)
                .map(pair -> pair.current)
                .toList();
    }

    public static List<Integer> arithmeticSequence(Integer firstTerm, Integer ratio) {
        return Stream.iterate(firstTerm, x -> x + ratio)
                .limit(10)
                .toList();
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false; // 0 y 1 no son primos
        }
        if (number == 2) {
            return true; // 2 es primo
        }
        if (number % 2 == 0) {
            return false; // descarta pares
        }

        int maxPossibleDivisor = (int) Math.sqrt(number);
        for (int i = 3; i <= maxPossibleDivisor; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> fist10PrimeNumbers() {
        return Stream.iterate(2, x -> x+1)
                .filter(Main::isPrime)
                .limit(10)
                .toList();
    }

    public record Tuple(Integer a, Integer b) {};

    public static List<List<Integer>> generatePairsWithEvenSum() {
        return IntStream.rangeClosed(1, 5)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(1, 5)
                        .filter(b -> (a+b) % 2 == 0)
                        .mapToObj(b -> List.of(a, b)))
                .toList();
    }

    public static List<String> flatStringText(List<String> list) {
        return list.stream()
                .map(sentence -> sentence.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .toList();
    }

    public static List<String> flatStringLists(List<List<String>> list) {
        return list
                .stream()
                .flatMap(List::stream)
                .toList();
    }

    public static List<Integer> arrayToList(int[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .toList();
    }

    public static void main(String[] args) {
        Flux.just("A", "B", "C")
                .map(String::toLowerCase)
                .subscribe(System.out::println);
        /**
        int[][] matrix = {
                {1, 2, 3},
                {4, 5},
                {6}
        };
        System.out.println(arrayToList(matrix));*/

    }
}
