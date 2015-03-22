/**
 * A simple class comprised of various tools used in handling of boolean arrays and values.
 * 
 * @author Raymond Berg www.rwberg.org
 * 
 * Use is made available under the Creative Commons License. 
 * Feel free to use but leave attribution to original author throughout use.
 */

/**
 * @author Raymond
 *
 */
public class BooleanUtil
{
	/**
	 * Simple function to concatenate two boolean arrays into a single boolean array.
	 * @param alpha
	 *            any boolean array
	 * @param beta
	 *            any boolean array
	 * @return Single array concatenating alpha and beta
	 */
	public static boolean[] concat(boolean[] alpha, boolean[] beta)
	{
		boolean[] returnArray = new boolean[alpha.length + beta.length];
		System.arraycopy(alpha, 0, returnArray, 0, alpha.length);
		System.arraycopy(beta, 0, returnArray, alpha.length, beta.length);
		return returnArray;
	}
	
	public static boolean[] getBits(byte composite)
	{
		boolean[] retArray = new boolean[8];
		for (int i = 7; i >= 0; i--)
		{
			retArray[7-i] = ((composite >> i & 0x1) == 1);
		}
		return retArray;
	}
	
	public static boolean[] getBits(int composite)
	{
		boolean[] retArray = new boolean[32];
		for (int i = 31; i > 0; i--)
		{
			retArray[31-i] = ((composite >> i & 0x1) == 1);
		}
		return retArray;
	}
	public static boolean[] getBits(int composite, int length)
	{
		boolean[] retArray = new boolean[length];
		for (int i = 0; i < length; i++)
		{
			retArray[length-1-i] = ((composite >> i & 0x1) == 1);
		}
		return retArray;
	}
	public static boolean[] getBits(byte[] byteArray)
	{
		boolean[] retArray = new boolean[0];
		for (int i = 0; i < byteArray.length; i++)
		{
			retArray = concat(retArray,getBits(byteArray[i]));
		}
		return retArray;
	}
	
	
	
	public static int fromBits(boolean[] bitArray)
	{
		int retVal = 0;
		for (int i = 0; i < bitArray.length; i++)
		{
			retVal = retVal * 2;
			if(bitArray[i]) retVal++;
		}
		return retVal; 
	}
	/**
	 * Building on the arraycopy function in System, this function returns a
	 * "subarray" of a larger array. The length parameter determines the 
	 * length of the resulting array. Standard out of bounds exceptions on length 
	 * can be expected.
	 * 
	 * @see {@link booleanUtil#subarray(boolean[], int)}
	 * @param array
	 * @param fromIndex
	 *            first element in returned array
	 * @param length
	 *            amount to read from array
	 * @return boolean array consisting of elements in range
	 *         [fromIndex,fromIndex+length]
	 */
	public static boolean[] subarray(boolean[] array, int fromIndex, int length)
	{
		boolean[] tempArray = new boolean[length];
		try{
				System.arraycopy(array, fromIndex, tempArray, 0, tempArray.length);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("Array of length:" +array.length);
			System.out.println("From index with length:" + fromIndex + "," +length);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");		
		}
		return tempArray;
	}

	/**
	 * Building on the arraycopy function in System, this function returns a
	 * "subarray" of a larger array. Function returns subarray starting at 
	 * fromIndex and ending with the final element of the array.
	 * 	 * @see booleanUtil#subarray(boolean[], int, int)
	 * @param array
	 * @param fromIndex
	 * @return
	 */
	public static boolean[] subarray(boolean[] array, int fromIndex)
	{
		boolean[] tempArray = new boolean[array.length - fromIndex];
		System.arraycopy(array, fromIndex, tempArray, 0, tempArray.length);
		return tempArray;
	}
	
	public static String toBinaryString(boolean[] array)
	{
		char[] rawArr = new char[array.length];
		for (int i = 0; i < rawArr.length; i++)
		{
			rawArr[i] = toBinaryChar(array[i]);
		}
		return new String(rawArr);
	}
	
	public static char toBinaryChar(boolean bit)
	{
		return bit?'1':'0';
	}
}
