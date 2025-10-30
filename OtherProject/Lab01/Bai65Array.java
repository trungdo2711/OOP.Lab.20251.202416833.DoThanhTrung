import java.util.Scanner;

public class Bai65Array {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int size = scanner.nextInt();
        int[] array = new int[size];
        for(int i = 0; i < size; i++){
            System.out.println("Enter the value: ");
            int value = scanner.nextInt();
            array[i] = value;
        }
        sortArray(array, 0, size - 1);
        for(int i = 0; i < size; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println("The sum of the array is " + calculateSum(array));
        System.out.println("The average value of the array is " + calculateAverage(array));
        scanner.close();
    }
    public static int calculateSum(int array[]){
        int sum = 0;
        for(int i = 0; i < array.length; i++){
            sum += array[i];
        }
        return sum;
    }
    public static double calculateAverage(int array[]){
        int sum = 0;
        for(int i = 0; i < array.length; i++){
            sum += array[i];
        }
        return (double) sum / array.length;
    }
    public static void sortArray(int[] array, int left, int right){
        if(left < right){
            int mid = (left + right) / 2;
            sortArray(array, left, mid);
            sortArray(array, mid + 1, right);
            merge(array,left,mid,right);
        }
    }
    public static void merge(int[] array, int left, int mid, int right){
        int size1 = mid - left + 1;
        int size2 = right - mid;

        int[] L = new int[size1];
        int[] R = new int[size2];

        for(int i = 0; i < size1; i++){
            L[i] = array[left + i];
        }
        for(int j = 0; j < size2; j++){
            R[j] = array[mid + 1 + j];
        }
        int i = 0, j = 0;
        int k = left;

        while(i < size1 && j < size2){
            if(L[i] <= R[j]){
                array[k] = L[i];
                i++;
            }else{
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while(i < size1){
            array[k] = L[i];
            i++;
            k++;
        }
        while(j < size2){
            array[k] = R[j];
            j++;
            k++;
        }
    }
}