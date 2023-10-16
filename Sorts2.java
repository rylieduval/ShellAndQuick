import java.util.Random;

public class Sorts2
{
    static Random random = new Random();
    
    public static void main(String[] args)
    {
        //test the speed of quick sort
        testQuickSort();
        //test the speed of shell sort
        testShellSort();
    }
    
    public static void testQuickSort()
    {
        //create and fill a random array
        int[] myArray = new int[10000000];
        myArray = fillRandomArray(10000000);

        //see how long it takes to sort array using quick sort
        long start = System.nanoTime();
        quickSort(myArray, 0, myArray.length - 1);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;  
        System.out.println("Sorting a random array took quick sort " + timeElapsed + " ns to complete");
    }
    
    public static void testShellSort()
    {
        //the gap values for an array of size 10000000
        int gaps[] = {8388607, 4194303, 2097151, 1048575, 524287, 262143, 131071, 65535, 32767, 16383, 8191, 4095, 2047, 1023, 511, 255, 127, 63, 31, 15, 7, 3, 1};
        int[] myArray = new int[10000000];
        myArray = fillRandomArray(10000000);


        //see how long it take to sort array using shell sort
        long start2 = System.nanoTime();
        ShellSort(myArray, gaps );
        long finish2 = System.nanoTime();
        long timeElapsed2 = finish2 - start2;  
        System.out.println("Sorting a random array took shell sort " + timeElapsed2 + " ns to complete");
    }

    //fill the array with random integers
    static int[] fillRandomArray(int size)
    {
        int[] randomArray = new int[size];
    
        for (int i = 0; i < size; i++) 
        {
            randomArray[i] = random.nextInt(10000000); 
        }
        
        return randomArray;
    }

    
    public static void InsertionSortInterleaved(int[] arr, int startIndex, int gap) 
    {
        for(int i = startIndex + gap; i < arr.length; i = i + gap) 
        {
            int current = i;
            while (current - gap >= 0 && arr[current] < arr[current - gap]) 
            {
                //swapping
                int temp = arr[current];
                arr[current] = arr[current - gap];
                arr[current - gap] = temp;
                current = current - gap;
            }
        }
    }
    
    public static void ShellSort(int[] arr, int[] gaps) 
    {
        for(int i = 0; i < gaps.length; i++) {
            int gap = gaps[i];
            for (int j = 0; j < gap; j++) {
                //call back to function
                InsertionSortInterleaved(arr, j, gap);
            }
        }
    }
    
     static int partition(int[] arr, int lowIndex, int highIndex)
    {
            //determine the pivot
        
            int low = lowIndex;
            int mid = low;
            int high = highIndex;

            int pivot = arr[(low + high) / 2];

            while (mid < high)
            {
                int temp = arr[mid];
                if (temp < pivot)
                {
                    //swap
                    arr[mid] = arr[low];
                    arr[low] = temp;
                    //increment low & mid index 
                    low++;
                    mid++;
                } 
                else if (temp == pivot) 
                {
                    mid++;
                } 
                else 
                //swapping
                {
                    arr[mid] = arr[high - 1];
                    arr[high - 1] = temp;
                    high--;
                }
            }
            //the last spot in the left segment.
            return high;
        }
    
    public static void quickSort(int arr[], int begin, int end) 
    {
        if (begin < end) 
        {
            int partitionIndex = partition(arr, begin, end);
            //sort the left part
            quickSort(arr, begin, partitionIndex-1);
            //sort the right part
            quickSort(arr, partitionIndex+1, end);
        }
    }
}

