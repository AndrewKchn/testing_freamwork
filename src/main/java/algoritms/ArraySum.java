package algoritms;

import java.util.*;

public class ArraySum {
    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        int[] array = {4, 3, 2, 1, 1, 2, 3};
        int sum = 6;
        Map<Integer, Integer> map = new HashMap<>();
        printArray(array);
//        indexOf(sum, array, 0, array.length - 1);
        findIndexesOfElement(sum, array, 0, array.length - 1);
    }

    private static void printArray(int[] array) {
        System.out.print("[");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.print("]\n");
    }

    static List<List<Integer>> findIndexesOfElement(int sum, int[] array, int startIndex, int lastIndex) {
        List<List<Integer>> storage = new ArrayList<>();
        List<List<Integer>> listWithElement = indexOf(sum, array, startIndex, lastIndex);
        if (!listWithElement.isEmpty()) storage.addAll(listWithElement);

        System.out.printf("===> Now, looking for possibles sum of [%s]\n", sum);
        for (int i = startIndex; i <= lastIndex; i++) {
            System.out.println("Number " + array[i] + " with index " + i);
            if (array[i] < sum) {
                List<List<Integer>> listWithSums = findIndexesOfElement(sum - array[i], array, i + 1, lastIndex);
                if (!listWithSums.isEmpty()) {
                    for (int n = 0; n < listWithSums.size(); n++) {
                        listWithSums.get(n).set(i, array[i]);
                    }
                    storage.addAll(listWithSums);
                }
            }
        }
        System.out.println("Target sum is " + sum);
//        printArray(array);
        printStorage(storage);
        return storage;
    }

    static void printStorage(List<List<Integer>> storage) {
        storage.forEach(System.out::println);
    }

    static List<List<Integer>> indexOf(int el, int[] array, int startIndex, int lastIndex) {
        System.out.println("indexOf: Looking for " + el);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = startIndex; i <= lastIndex; i++) {
            System.out.println("indexOf: >>Inner iteration " + i);
            if (array[i] == el) {
                List<Integer> list = createEmptyList(array.length);
                System.out.printf("indexOf: Hooray, index of [%s] - %s\n", el, i);
                list.set(i, el);
                result.add(list);
            }
        }
        System.out.printf("==== Possible arrays with [%s]\n", el);
        printStorage(result);
        return result;
    }

    static List<Integer> createEmptyList(int capacity) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            result.add(i, 0);
        }
        return result;
    }
}


//        private static void doSomething ( int target, List<Integer > integers){
//            if (integers.contains(target)) {
//                System.out.printf("Uray, index of[%s] - %s\n", target, integers.indexOf(target));
//            } else if (integers.size() == 1) {
//                System.out.println("Not found");
//            } else {
//                List<Integer> result = new ArrayList();
//                result.add(integers.get(0));
//                System.out.println(result);
//                int newTarget = target - integers.get(0);
//                System.out.println("Looking for - " + newTarget);
//                doSomething(newTarget, integers.subList(1, integers.size()));
//            }
//        }
//    }
