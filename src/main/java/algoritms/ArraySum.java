package algoritms;

import java.util.*;

public class ArraySum {
    public static void main(String[] args) {
        List<Integer> listOfDigits = List.of(8, 7, 6, 8, 7, 9, 8);
        int sum = 15;
        System.out.println(listOfDigits);
        System.out.println("=======================");
        List<List<Integer>> results = possibleResults(sum, listOfDigits, 0, listOfDigits.size() - 1);
        printStorage(results);
    }

    static void printStorage(List<List<Integer>> storage) {
        storage.forEach(System.out::println);
    }

    static List<List<Integer>> possibleResults(int targetSum, List<Integer> list, int startIndex, int lastIndex) {
        List<List<Integer>> storage = new ArrayList<>();
        List<List<Integer>> oneElementResultsList = resultsWithOneElement(targetSum, list, startIndex, lastIndex);
        if (!oneElementResultsList.isEmpty()) storage.addAll(oneElementResultsList);
        for (int i = startIndex; i <= lastIndex; i++) {
            if (list.get(i) < targetSum) {
                List<List<Integer>> listWithSums = possibleResults(targetSum - list.get(i), list, i + 1, lastIndex);
                if (!listWithSums.isEmpty()) {
                    for (List<Integer> listWithSum : listWithSums) {
                        listWithSum.set(i, list.get(i));
                    }
                    storage.addAll(listWithSums);
                }
            }
        }
        return storage;
    }


    static List<List<Integer>> resultsWithOneElement(int el, List<Integer> list, int startIndex, int lastIndex) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = startIndex; i <= lastIndex; i++) {
            if (list.get(i) == el) {
                List<Integer> tempList = createEmptyResultsList(list.size());
                tempList.set(i, el);
                result.add(tempList);
            }
        }
        return result;
    }

    static List<Integer> createEmptyResultsList(int capacity) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            result.add(i, 0);
        }
        return result;
    }
}
