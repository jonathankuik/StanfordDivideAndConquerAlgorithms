package quickSort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class lastPositionPartition {
	
	private static int[] readFile(){
		try {
			File file = new File("arrayList.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			int x=0;
			int[] output_array = new int[10000];
			for (x=0; x<10000; x++){
				line = bufferedReader.readLine();
				//System.out.println(x + " " + line);
				output_array[x] = Integer.parseInt(line);
			}
			
			fileReader.close();
			
			return output_array;
			
		} catch (IOException e) {
			int[] blank_array = {0};
			return blank_array;
		}
		
	}

	private static int firstPositionPartition(int[] x, int left, int right, int count){
		
		int i,j, first, last, pivot, temp;
		
		
		
		if (right - left < 1){
			return 0;
		}
		else{
			first = x[left];
			last = x[right];
			x[left] = last;
			x[right] = first;
			count = right - left;
			pivot = x[left];
			i = left+1;
			for(j=left+1; j<=right; j++){
				if (x[j]<pivot){
					//swap x[j] with x[i]
					temp = x[j];
					x[j] = x[i];
					x[i] = temp;
					i++;
				} 
			}
			//swap pivot with the spot left of i
			temp = x[i-1];
			x[i-1] = pivot;
			x[left] = temp;
		}
			
		count += firstPositionPartition(x, left, (i-2), count);
		count += firstPositionPartition(x, i, right, count);
		return count;
		
	}
	
	/*
	private static int[] naivePartition(int[] x){
		int i,j, pivot;
		pivot = x[0];
		int[] returnArray = new int[x.length];
		for (i=1; i<x.length; i++){
			if (x[i] < pivot){
				
			}
		}
	}
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] x = readFile();
		int left = 0;
		int right = x.length-1;
		int count = 0;
		count = firstPositionPartition(x, left, right, count);
		
		System.out.println(count);
		
		int counter;
		for (counter=0; counter<x.length; counter++){
			System.out.print(x[counter] + " ");
		}
	}

}
