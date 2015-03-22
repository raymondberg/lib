/**
 * A simple class comprised of various tools used in handling of byte arrays.
 * The most used features include multiplexing, demultiplexing, selecting
 * sub-arrays, and concatenating arrays.
 * 
 * @author Raymond Berg www.rwberg.org
 * @author Shane Gummersheimer stnewl99@hotmail.com
 * 
 * @version 1.1
 *
 * Use is made available under the Creative Commons License. 
 * Feel free to use but leave attribution to original author throughout use.
 */

public class ByteUtil
{
	/**
	 * Split single short into 2 byte values
	 * @param composite
	 *            a short value
	 * @return
	 */
	public static byte[] mux(short composite)
	{
		byte[] returnArray = new byte[2];
		returnArray[1] = (byte) (composite & 0x00FF);
		returnArray[0] = (byte) ((composite >> 8));
		return returnArray;
	}

	/**
	 * Split single int into 4 byte values
	 * @param composite
	 *            an int value
	 * @return 
	 */
	public static byte[] mux(int composite)
	{
		byte[] returnArray = new byte[4];

		returnArray[3] = (byte) (composite & 0x000000FF);
		returnArray[2] = (byte) ((composite >> 8) & 0x000000FF);
		returnArray[1] = (byte) ((composite >> 16) & 0x000000FF);
		returnArray[0] = (byte) ((composite >> 24) & 0x000000FF);
		return returnArray;
	}

	/**
	 * Combine 2 bytes into a single short. 
	 * @param simple1
	 *            greatest 8-bits
	 * @param simple2
	 *            least 8-bits
	 * @return
	 */
	public static short demux(byte simple1, byte simple2)
	{
		short returnVal = (short) ((simple1 & 0x00FF) << 8);
		returnVal += (short) (simple2 & 0x00FF);
		return returnVal;
	}

	/**
	 * Combine 4 bytes into a single int. 
	 * @param simple1
	 *            greatest 8-bits
	 * @param simple2
	 *            greater 8-bits
	 * @param simple3
	 *            lesser 8-bits
	 * @param simple4
	 *            least 8-bits
	 * @return
	 */
	public static int demux(byte simple1, byte simple2, byte simple3,
			byte simple4)
	{
		int returnVal = ((simple1 & 0x000000FF) << 8);
		returnVal = (returnVal + (simple2 & 0x000000FF)) << 8;
		returnVal = (returnVal + (simple3 & 0x000000FF)) << 8;
		returnVal = (returnVal + (simple4 & 0x000000FF));
		return returnVal;
	}

	/**
	 * Simple function to concatenate two byte arrays into a single byte array.
	 * If alpha = {a,l,p,h,a} and beta = {b,e,t,a} then the resulting array
	 * would be {a,l,p,h,a,b,e,t,a}.
	 * 
	 * @param alpha
	 *            any byte array
	 * @param beta
	 *            any byte array
	 * @return Single array concatenating alpha and beta
	 */
	public static byte[] concat(byte[] alpha, byte[] beta)
	{
		byte[] returnArray = new byte[alpha.length + beta.length];
		System.arraycopy(alpha, 0, returnArray, 0, alpha.length);
		System.arraycopy(beta, 0, returnArray, alpha.length, beta.length);
		return returnArray;
	}

	/**
	 * Building on the arraycopy function in System, this function returns a
	 * "subarray" of a larger array. The length parameter determines the 
	 * length of the resulting array. Standard out of bounds exceptions on length 
	 * can be expected.
	 * 
	 * @see {@link ByteUtil#subarray(byte[], int)}
	 * @param array
	 * @param fromIndex
	 *            first element in returned array
	 * @param length
	 *            amount to read from array
	 * @return byte array consisting of elements in range
	 *         [fromIndex,fromIndex+length]
	 */
	public static byte[] subarray(byte[] array, int fromIndex, int length)
	{
		byte[] tempArray = new byte[length];
		System.arraycopy(array, fromIndex, tempArray, 0, tempArray.length);
		return tempArray;
	}

	/**
	 * Building on the arraycopy function in System, this function returns a
	 * "subarray" of a larger array. Function returns subarray starting at 
	 * fromIndex and ending with the final element of the array.
	 * 	 * @see ByteUtil#subarray(byte[], int, int)
	 * @param array
	 * @param fromIndex
	 * @return
	 */
	public static byte[] subarray(byte[] array, int fromIndex)
	{
		byte[] tempArray = new byte[array.length - fromIndex];
		System.arraycopy(array, fromIndex, tempArray, 0, tempArray.length);
		return tempArray;
	}

	/**
	 * Convert byte array into simple hexadecimal format. Bytes are represented
	 * in duplet form and separated by spaces.
	 * 
	 * @see ByteUtil#toHexString(byte[], int)
	 * @param array
	 * @return
	 */
	public static String toHexString(byte[] array)
	{
		String retString = "";
		for (int i = 0; i < array.length; i++)
		{
			retString += toHexString(array[i]) + " ";
		}
		
		return retString.substring(0,retString.length()-1);
	}

	/**
	 * Convert byte array into simple hexadecimal format. Bytes are represented
	 * in duplet form and separated by spaces. This method varies in allowing
	 * selection of a number of bytes per line.
	 * 
	 * @see ByteUtil#toHexString(byte[])
	 * @param array
	 * @param bytesPerLine
	 *            number of
	 * @return string representation of array
	 */
	public static String toHexString(byte[] array, int bytesPerLine)
	{
		String retString = "";
		for (int i = 0; i < array.length; i++)
		{
			if (i % bytesPerLine == 0 && i != 0)
				retString += "\n";
			retString += toHexString(array[i]) + " ";

		}
		return retString.substring(0,retString.length()-1);
	}

	/**
	 * Convert single byte into hexadecimal, duplet string representation.
	 * 
	 * @param value
	 *            a byte value
	 * @return hexadecimal duplet equivelant to byte value
	 */
	public static String toHexString(byte value)
	{
		String temp = (Integer.toHexString(((int) value & 0xff))).toUpperCase();
		if (temp.length() == 1)
			temp = "0" + temp;
		return temp;
	}
	/**
	 * Convert single short into hexadecimal, duplet string representation.
	 * 
	 * @param value
	 *            a short value
	 * @return hexadecimal duplet equivelant to short value
	 */
	public static String toHexString(short value)
	{
		return toHexString(mux(value));
	}
	/**
	 * This method converts String containing hexadecimal duplets into a byte
	 * array. Spaces are optional, but values will be read as duplets so spaces
	 * do not signify separate bytes. If there are an odd number of hexadecimal
	 * characters then the last character will be treated as if it were preceded 
	 * by a zero. 
	 * <p>Example:<br/> 
	 *	00 23 1213 is congruent to 00231213 and will parse to [0,35,18,19]<br/>
	 *	00 23 121 is congruent to 0023121 and will parse to [0,35,18,1]
	 * </p>
	 * <p>Note: Version 1.1 - Shane added the Regular Expression removal of all 
	 * non-hex characters.</p>
	 * @param hexString
	 *            hexadecimal string representation
	 * @return
	 */
	public static byte[] fromHexString(String hexString)
	{
		hexString = hexString.replaceAll("[^0123456789ABCDEF]", "");
		byte[] retArray = new byte[(int) Math
				.ceil(hexString.length() * 1.0 / 2)];
		int retArrayPtr = 0;
		String temp = "";
		for (int i = 0; i < hexString.length(); i++)
		{
			temp += hexString.charAt(i);
			// if it's a duplet or the last character in the string
			if (temp.length() > 1 || i == hexString.length() - 1)
			{
				retArray[retArrayPtr++] = fromHexDuplet(temp);
				temp = "";
			}
		}
		return retArray;
	}

	/**
	 * Convert single hexadecimal duplet into its byte equivalent
	 * @param hexString
	 * @return
	 */
	public static byte fromHexDuplet(String hexString)
	{
		return (byte) Integer.parseInt(hexString, 16);
	}

	public static byte fromNibbles(byte greater, byte lesser)
	{
		return (byte) ((greater << 4)  + (lesser & 0x0f));
	}
	
	public static byte getLeftNibble(byte composite)
	{
		return (byte) (composite >> 4 & 0x0F);
	}
	public static byte getRightNibble(byte composite)
	{
		return (byte) (composite & 0x0F);
	}

	public static byte[] fromDotDecimalOctets(String ipAddress)
	{
		String[] separateValues = ipAddress.split("\\.");
		return fromDecimalStringArray(separateValues);
	}
	
	/**
	 * @param separateValues
	 * @return
	 */
	public static byte[] fromDecimalStringArray(String[] separateValues)
	{
		byte[] byteArray = new byte[separateValues.length];
		
		for (int i = 0; i < byteArray.length; i++)
		{
			byteArray[i] = (byte) Integer.parseInt(separateValues[i]);
		}
		return byteArray;
	}

	public static String getDotDecimalOctets(byte greatest, byte greater, byte lesser, byte least)
	{
		return (0xFF & greatest) + "." + (0xFF & greater) + "." + (0xFF & lesser) + "." + (0xFF & least);
	}
	public static String getDotDecimalOctets(byte[] array)
	{
		byte[] translation = new byte[4];
		int translationPointer = 3;
		int arrayPointer = Math.min(4,array.length)-1;
		while (arrayPointer >= 0)
		{
			translation[translationPointer--] = array[arrayPointer--];
		}
		return getDotDecimalOctets(translation[0],translation[1],translation[2],translation[3]);
	}
	
	public static String getColonHexDuplets(byte greatest, byte greater,
			byte great, byte less, byte lesser, byte least)
	{
		return toHexString(greatest) + ":" + toHexString(greater) + ":"
				+ toHexString(great) + ":" + toHexString(less) + ":"
				+ toHexString(lesser) + ":" + toHexString(least);
	}
	public static String getColonHexDuplets(byte[] array)
	{
		byte[] translation = new byte[6];
		int translationPointer = 5;
		int arrayPointer = Math.min(6,array.length)-1;
		while (arrayPointer > 0)
		{
			translation[translationPointer--] = array[arrayPointer--];
		}
		return getColonHexDuplets(translation[0],translation[1],translation[2],translation[3],translation[4],translation[5]);
	}

	/**
	 * @param payload
	 * @return
	 */
	public static String toAsciiCoded(byte[] payload)
	{
		if(payload == null) return new String();
		char[] retArray = new char[payload.length];
		for (int i = 0; i < retArray.length; i++)
		{
			retArray[i] = (char) (isAsciiCharacter(payload[i])?payload[i]:46);
		}
		return new String(retArray);
	}	
	
	public static boolean isAsciiCharacter(byte val)
	{
		return val > 31 && val < 127;
	}

	/**
	 * @param data
	 * @param data2
	 * @return
	 */
	public static boolean equals(byte[] data, byte[] data2)
	{
		if(data.length != data2.length) return false;
		
		for (int i = 0; i < data2.length; i++)
		{
			if(data[i] != data2[i]) return false;
		}
		return true;
	}
}
