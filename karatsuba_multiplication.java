package kat_mult;


import java.math.BigInteger;
import java.util.*;

public class karatsuba_multiplication{
	
	public static int getDigitCount(BigInteger number) {
		  double factor = Math.log(2) / Math.log(10);
		  int digitCount = (int) (factor * number.bitLength() + 1);
		  if (BigInteger.TEN.pow(digitCount - 1).compareTo(number) > 0) {
		    return digitCount - 1;
		  }
		  return digitCount;
		}
	
	private static BigInteger kat(BigInteger x, BigInteger y){

		
		if (x.bitLength() < 2 && y.bitLength()<2){
				return x.multiply(y);
			} else{		
				int m;
				if (x.bitLength()>x.bitLength()){
					m = x.bitLength();
				} else{
					m = y.bitLength();
				}
				
				
				int m2 = (int) Math.ceil((double)m/2.0);
				
				System.out.println("This is x in binary " + x.toString(2));
				System.out.println("This is y in binary " + y.toString(2));
				
				BigInteger high1 = x.shiftRight(m2);
				System.out.println("This is the left side of the number " + high1.toString(2));
				System.out.println("This is what we subtract " + high1.shiftLeft(m2).toString(2));
				BigInteger low1 = x.subtract(high1.shiftLeft(m2));
				System.out.println("This is the right side of the number " + low1.toString(2));
				BigInteger high2 = y.shiftRight(m2);
				BigInteger low2 = y.subtract(high2.shiftLeft(m2));
				
				BigInteger z0 = kat(low1,low2);
				BigInteger z2 = kat(high1,high2);	
				BigInteger z1 = kat(low1.add(high1),low2.add(high2));
				
				
				return z2.shiftLeft(m2*2).add(z1.subtract(z2).subtract(z0).shiftLeft(m2)).add(z0);
			
			}
			
		}
		
	 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long i,j;
		String x,y;
		
		i = 1234;
		j = 1234;
		
		//System.out.println(i*j);
		x = "3141592653589793238462643383279502884197169399375105820974944592";
		y = "2718281828459045235360287471352662497757247093699959574966967627";
		
		BigInteger a = new BigInteger(Long.toString(i));
		BigInteger b = new BigInteger(Long.toString(j));
		
		//System.out.println(a.multiply(b));
		
		System.out.println(i*j);
		System.out.println(kat(a,b).toString());
	
		/*
		Random rand = new Random();
		
		while (true){
			i = rand.nextInt(89) + 10;
			j = rand.nextInt(99);
			System.out.print(i + " " + j + " ");
			x = i*j;
			y = kat(i,j);
			if (x==y){
				System.out.println("OK");
			} else {
				return;
			}
			
		} */
		
		
		
	}

}

