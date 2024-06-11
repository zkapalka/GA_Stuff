import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrayAndArrayListLab {


    public static void main(String[] args) {
        //Calling Exercise 1
        System.out.println("Exercise 1: ");
        printArray();

        System.out.println();
        //Calling Exercise 2
        System.out.println("Exercise 2: " + findElementInArray(9));

        //Calling Exercise 3
        System.out.println();
        System.out.println("Exercise 3:");
        countOccurences(5);

        //Calling Exercise 4
        System.out.println();
        System.out.println("Exercise 4: ");
        findMaxValueInArrayList(new ArrayList<>(Arrays.asList(3, 4, 5, 6, 1, 6, 1337)));

        //Calling Exercise 5
        System.out.println();
        System.out.println("Exercise 5: ");
        reverseArrayList(new ArrayList<>(Arrays.asList(3, 4, 5, 6, 1, 6, 1337)));

        //Calling Exercise 6
        int[] testArray = {1, 2, 46, 7, 9};
        reverseArray(testArray);
        System.out.println();
        System.out.println("Exercise 6: " + Arrays.toString(testArray));

        //Calling Exercise 7
        int [] mergeTest1 = { 1, 3 , 5, 7 , 10};
        int [] mergeTest2 = { 400, 30, 22, 1337, 1000000};
        mergeArray(mergeTest1, mergeTest2);
        System.out.println();
        System.out.println("Exercise 7: " + Arrays.toString(mergeArray(mergeTest1, mergeTest2)));
    }

    //Exercise 1
    public static void printArray() {
        int[] testArray1 = {1, 2, 3, 4, 5, 6, 7, 8, 1337};

        System.out.println("Array: ");
        for (int x : testArray1) {
            System.out.println(x);
        }
    }

    //Exercise 2
    public static boolean findElementInArray(int n1) {
        int[] testArray2 = {1, 2, 3, 4, 5, 6, 7, 8, 1337};

        for (int number : testArray2) {
            if (number == n1) {
                return true;
            }
        }
        return false;
    }

    //Exercise 3
    public static int countOccurences(int n1) {
        int[] testArray3 = {1, 2, 3, 4, 5, 6, 7, 8, 1337};
        int counter = 0;

        for (int number : testArray3) {
            if (number == n1) {
                counter++;
            }
        }

        System.out.println("This integer " + n1 + " appears " + counter + " times in the array");
        return counter;
    }

    //Exercise 4
    public static int findMaxValueInArrayList(ArrayList<Integer> arr1) {
        if (arr1.isEmpty()) {
            throw new IllegalArgumentException("ArrayList is empty");
        }

        int maxValue = Collections.max(arr1);
        System.out.println("The max value in the array is " + maxValue);
        return maxValue;
    }

    //Exercise 5
    public static ArrayList<Integer> reverseArrayList(ArrayList<Integer> arr1) {
        if (arr1.isEmpty()) {
            throw new IllegalArgumentException("ArrayList is empty");
        }

        ArrayList<Integer> reversedArr1 = new ArrayList<>(arr1);
        Collections.reverse(reversedArr1);

        System.out.println("Reversed ArrayList: " + reversedArr1);
        return reversedArr1;

    }

    //Exercise 6
    public static int[] reverseArray(int[] arr2) {
        if (arr2.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int left = 0;
        int right = arr2.length - 1;

        while (left < right) {
            // Swap elements at left and right indices
            int temp = arr2[left];
            arr2[left] = arr2[right];
            arr2[right] = temp;

            // Move the pointers towards the center
            left++;
            right--;
        }
        return arr2;
    }

    //Exercise 7 BONUS
    public static int[] mergeArray(int[] arrOne, int[] arrTwo) {
        //Create an new array to merge the two arrays
        int [] newMergedArray = new int[arrOne.length + arrTwo.length];
        System.arraycopy(arrOne, 0, newMergedArray, 0, arrOne.length);
        System.arraycopy(arrTwo, 0, newMergedArray, arrOne.length, arrTwo.length);

        //Sort the new array
        Arrays.sort(newMergedArray);

        return newMergedArray;
    }






}
