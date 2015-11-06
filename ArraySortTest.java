import static org.junit.Assert.*;
import java.util.*;
//import org.junit.BeforeClass;
import org.junit.*;
import org.junit.Test;
import org.junit.BeforeClass;

public class ArraySortTest {
	
	//needed global variables/constants for the test
	int NUM_OF_ARRAYS = 300; //number of arrays tested
	int MAX_SIZE = 1000; //maximum  size of an array
	Random rnd = new Random(); //random generator
	int initArrays[][] = new int[NUM_OF_ARRAYS][]; //our starter arrays
	int sortedArrays1[][] = new int[NUM_OF_ARRAYS][]; //our sorted arrays
	int sortedArrays2[][] = new int[NUM_OF_ARRAYS][]; //second array used to re-run sort method 2nd time
	
	//set up the arrays before testing can be done
	@Before
	public void setUp() throws Exception
	{
		//we have one case for null array
		initArrays[0] = null;
		sortedArrays1[0] = null;
		sortedArrays2[0] = null;
		//we have one case f for arrays with zero elements
		initArrays[1] = new int[0];
		sortedArrays1[1] = new int[0];
		sortedArrays2[1] = new int[0];
		//we have one case for array with one element
		initArrays[2] = new int[] {0};
		sortedArrays1[2] = new int[] {0};
		sortedArrays2[2] = new int[] {0};

		
		//randomize the rest with random numbers
		for (int i = 3; i < NUM_OF_ARRAYS; i++)
		{
			//randomize the size of the array
			int length = rnd.nextInt(MAX_SIZE)+1;
			//initialize the arrays
			initArrays[i] = new int[length];
			sortedArrays1[i] = new int[length];
			sortedArrays2[i] = new int[length];
			//start randomizing the values
			for(int j = 0; j< length; j++)
			{
				initArrays[i][j] = rnd.nextInt();
				sortedArrays1[i][j] = initArrays[i][j];				
			}
			
			
			//we sort the arrays!
			Arrays.sort(sortedArrays1[i]);
		
		
			//we sort the sorted array again to check property 3
			//first, do a hard copy 
			for (int j = 0; j< length; j++)
			{
				sortedArrays2[i][j] = sortedArrays1[i][j];
			}
			//then sort
			Arrays.sort(sortedArrays2[i]);

		}
	}
	
	//helper function to check the ascending order of a single array
	//return true if the array's items is in ascending order
	private boolean checkAscending (int[] a)
	{
		int i = 0;
		//special case for null arrays
		if (a == null) return true;
		//special case for array with 0 or 1 member
		else if (a.length <= 1) return true;
		//normal cases
		else
		{
			while ((i< a.length-1)&&(a[i]<=a[i+1]))
			{
				i++;
			}
			if (i >= a.length-1) return true;
			else return false;
		}
	}
	
	//helper function to check if 2 arrays are the same
	//return true if the 2 arrays are the same
	private boolean checkSameArray(int[] a, int[] b)
	{
		//special case for null arrays
		if ((a == null) &&(b == null)) return true;
		//special case for arrays with 0 member
		else if ((a.length == 0)&& (b.length == 0)) return true;
		//normal cases
		else
		{			
			//first check the length
			if (a.length == b.length)
			{
				//then check each element in array
				int i = 0;
				while ((i<a.length)&&(a[i] == b[i])) i++;
				if (i>=a.length) return true;
				else return false;
			}
			else return false;
		}
	}
	
	//Property 1 : sorted arrays should have the same size as before being sorted
	@Test
	public void testSize() {
		boolean result = true;
		for (int i = 0 ; i < NUM_OF_ARRAYS; i++)
		{
			//special case for null arrays
			if ((initArrays[i] == null) && (sortedArrays1[i] == null))
				continue;
			//check the size of all the arrays
			else if (initArrays[i].length != sortedArrays1[i].length)
			{
				result = false;
				break;
			}
		}
		assertTrue(result);
	}
	

	//property 2 : the sorted arrays elements are in ascending order 
	@Test 
	public void testOrder()
	{
		boolean result = true;
		for (int i = 0; i< NUM_OF_ARRAYS; i++)
		{
			//check the order of the arrays
			if (!checkAscending(sortedArrays1[i]))
			{
				result = false;
				break;
			}
		}
		assertTrue(result);
	}

	//property 3 : running sorting method again should not change the output array
	@Test
	public void testIdempotent()
	{
		boolean result = true;
		for (int i = 0; i< NUM_OF_ARRAYS; i++)
		{
			//check if 2 arrays are the same
			if (!checkSameArray(sortedArrays1[i],sortedArrays2[i]))
			{
				result = false;
				break;
			}
		}
		assertTrue(result);
	}
}