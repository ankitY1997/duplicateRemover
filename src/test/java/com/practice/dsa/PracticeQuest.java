package com.practice.dsa;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class PracticeQuest {
	
	
	public static void main(String [] args)
	{
		/*Scanner sc=new Scanner(System.in);
	    System.out.println("declared size of array");
		int declare_Size=sc.nextInt();
	    int [] arr=new int[declare_Size];
	  for(int i=0;i<arr.length;i++)
	  {
		  System.out.println("entered"+1+"array");
		  arr[i]=sc.nextInt();
	  }
	  //  int [] arr= {2,6,9,-5};
	   System.out.println("enter the leastIndex");
	   int leastIndex=sc.nextInt();
	    //int leastIndex=2;
	    System.out.println("enter the max Index");
	    int maxIndex=sc.nextInt();
	   // int maxIndex=3;
	   int count=0;
	   int[] actualArray=new int[0];
	   int arrayIndex=0;
		for (int i = 0; i < arr.length; i++) {
			if (!((i >= leastIndex-1) && (i <= maxIndex-1))) {
				count++;
			}
			if((arr.length)-1==i)
			{
				System.out.println("count is "+count);
				actualArray=new int[count];
				System.out.println(actualArray.length);
				for(int j=0;j<arr.length;j++)
				{
					if (!((j >= leastIndex-1) && (j <= maxIndex-1))) {
						actualArray[arrayIndex]=arr[j];
						arrayIndex++;
					}
				}
				
			}
			
	    }
		boolean flag=false;
		int maxValue=actualArray[0];
		if(actualArray.length>1)
		{
		 maxValue=actualArray[1];
		}
		int divisibleCount=0;
		int maxDivisibleValue=0;
		int k=0;
		for (int i = 0; i < actualArray.length; i++) {
			if (actualArray[i] > maxValue) {
				maxValue = actualArray[i];
			}

			if ((actualArray.length) - 1 == i) {
                 System.out.println("maxVlaue is "+maxValue);
				for (int j = 1; j <= maxValue ; j++) {
					divisibleCount = 0;
					k=0;
					while (k < actualArray.length) {
						if (actualArray[k] % j == 0) {
							divisibleCount++;
							k++;
						} else {
							break;
						}
						if (divisibleCount == actualArray.length) {
							maxDivisibleValue = j;
						
						}

					}

				}
			}

		}
	
		System.out.println("maximum divisible value =>"+maxDivisibleValue);*/
		
		greatestCommonDivisible();
		
		
	}
	
	
	public static void greatestCommonDivisible()
	{

		long[] arr = { 2, 6, 9,4,8 ,12,18};
		int l = 1;
		int r = 4;
		List<Long> actual_list = new ArrayList<Long>();
		for (int i = 0; i < arr.length; i++) {
			if (!(i >= l - 1 && i <= r - 1)) {
				actual_list.add(arr[i]);
			}
		}
		BigInteger gcd = null;
		for (int i = 0; i < actual_list.size(); i++) {
			if(i!=actual_list.size()-1) {
				BigInteger b1 = new BigInteger(String.valueOf(actual_list.get(i)));
				BigInteger b2 = new BigInteger(String.valueOf(actual_list.get(i + 1)));
				gcd = b1.gcd(b2);
			}
		}
		System.out.println(gcd);
	}
		
		
	}


