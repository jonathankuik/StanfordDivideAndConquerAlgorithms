import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class inversionCounter {
	
	private static int[] readFile(){
		try {
			File file = new File("arrayList3.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			int x=0;
			int[] output_array = new int[100000];
			for (x=0; x<100000; x++){
				line = bufferedReader.readLine();
				output_array[x] = Integer.parseInt(line);
			}
			
			fileReader.close();
			
			return output_array;
			
		} catch (IOException e) {
			int[] blank_array = {0};
			return blank_array;
		}
		
	}

	private static long merge_arrays(int[] x, int[] y, int[]input){
		int[] merged = new int[x.length + y.length];
		int i;
		int x_counter = 0;
		int y_counter = 0;
		int inversion_counter = 0;
		
		for(i=0; i<merged.length; i++){
			if (x_counter == x.length){
				merged[i] = y[y_counter];
				y_counter++;
			} else if (y_counter >= y.length){
				merged[i] = x[x_counter];
				x_counter++;
			} else if (x[x_counter]<y[y_counter]){
				merged[i] = x[x_counter];
				x_counter++;
			} else if (x[x_counter] == y[y_counter]) {
				merged[i] = x[x_counter];
				x_counter++;
				inversion_counter += x.length - x_counter - 1;
			} else {
				
				merged[i] = y[y_counter];
				y_counter++;
				inversion_counter += x.length - x_counter;
			}
		}
		
		i = 0;
		while (i<merged.length){
			input[i] = merged[i];
			i++;
		}
		
		return inversion_counter;
		
	}
	
	
	private static long sort(int[] x){
		int a,b;
		int midPoint;
		long leftArrayCount, rightArrayCount, splitInversions;
		int[] leftArray, rightArray;
		
		if (x.length < 2){
			return 0;
		} else {
			//split the array
				midPoint = x.length/2;
				leftArray = Arrays.copyOfRange(x, 0, midPoint);
				rightArray = Arrays.copyOfRange(x, midPoint, x.length);
				//Make this a return so that I can then merge it back together
				leftArrayCount = sort(leftArray);
				rightArrayCount = sort(rightArray);
				splitInversions = merge_arrays(leftArray, rightArray, x);
				return leftArrayCount + rightArrayCount + splitInversions;
			} 
								
	}
	
	private static int[] randomIntArray(int length, int max_digit){
		int[] randomArray = new int[length];
		Random rand = new Random();
		int i;
		
		for (i=0; i<length; i++){
			randomArray[i] = rand.nextInt(max_digit) + 1;
		}
		return randomArray;
	}
	
	public static void main(String[] args) {
		
		int[] x =  { 4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18, 46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54 };
		
		
		int[] a = readFile();
		System.out.println(a[0]);
		System.out.println(a[99999]);
		
		long z;
		z = sort(a);
		
		System.out.println(z);
		
		/*
		int[] x,returnArray;
		int i;
		x = randomIntArray(2000000, 1000);
		
		long startTime = System.currentTimeMillis();

		x = sort(x);

		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		*/

		

	}
}
