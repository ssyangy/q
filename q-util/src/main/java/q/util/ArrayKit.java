package q.util;

public class ArrayKit {
	/**
	 * An empty immutable <code>String</code> array.
	 */
	public static final String[] EMPTY_STRING_ARRAY = new String[0];

	/**
	 * <p>
	 * Checks if an array of Objects is empty or <code>null</code>.
	 * </p>
	 * 
	 * @param array
	 *            the array to test
	 * @return <code>true</code> if the array is empty or <code>null</code>
	 */
	public static boolean isEmpty(Object[] array) {
		if (array == null || array.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * Checks if an array of Objects is not<code>null</code> and not empty.
	 * </p>
	 * 
	 * @param array
	 *            the array to test
	 * @return <code>true</code> if the array is not null and not empty.
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}

	/**
	 * <p>
	 * Find the last index of the given value within the array.
	 * </p>
	 * 
	 * <p>
	 * This method returns <code>-1</code> if <code>null</code> array input.
	 * </p>
	 * 
	 * @param array
	 *            the array to travers backwords looking for the object, may be <code>null</code>
	 * 
	 * @param valueToFind
	 *            the object to find
	 * 
	 * @return the last index of the value within the array, <code>-1</code> if not found or <code>null</code> array input
	 */
	public static int lastIndexOf(final byte[] array, final byte valueToFind) {
		return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
	}

	/**
	 * <p>
	 * Find the last index of the given value in the array starting at the given index.
	 * </p>
	 * 
	 * <p>
	 * This method returns <code>-1</code> if <code>null</code> array input.
	 * </p>
	 * 
	 * <p>
	 * A negative startIndex will return -1. A startIndex larger than the array length will search from the end of the array.
	 * </p>
	 * 
	 * @param array
	 *            the array to traverse for looking for the object, may be <code>null</code>
	 * @param valueToFind
	 *            the value to find
	 * @param startIndex
	 *            the start index to travers backwards from
	 * @return the last index of the value within the array, <code>-1</code> if not found or <code>null</code> array input
	 */
	public static int lastIndexOf(final byte[] array, final byte valueToFind, int startIndex) {
		if (array == null) {
			return -1;
		}
		if (startIndex < 0) {
			return -1;
		} else if (startIndex >= array.length) {
			startIndex = array.length - 1;
		}
		for (int i = startIndex; i >= 0; i--) {
			if (valueToFind == array[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * <p>
	 * Find the last index of the given array within the array.
	 * </p>
	 * 
	 * <p>
	 * This method returns <code>-1</code> if <code>null</code> array input.
	 * </p>
	 * 
	 * @param array
	 *            the array to travers backwords looking for the object, may be <code>null</code>
	 * 
	 * @param arrayToFind
	 *            the array to find
	 * 
	 * @return the last index of the given array within the array, <code>-1</code> if not found or <code>null</code> array input
	 */
	public static int lastIndexOf(byte[] array, byte[] arrayToFind) {
		return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE);
	}

	/**
	 * <p>
	 * Find the last index of the given array within the array.
	 * </p>
	 * 
	 * <p>
	 * This method returns <code>-1</code> if <code>null</code> array input.
	 * </p>
	 * 
	 * @param array
	 *            the array to travers backwords looking for the object, may be <code>null</code>
	 * 
	 * @param arrayToFind
	 *            the array to find
	 * 
	 * @param startIndex
	 *            the start index to travers backwards from
	 * 
	 * @return the last index of the given array within the array, <code>-1</code> if not found or <code>null</code> array input
	 */
	public static int lastIndexOf(byte[] array, byte[] arrayToFind, int startIndex) {
		if ((array == null) || (arrayToFind == null)) {
			return -1;
		}

		int sourceLength = array.length;
		int targetLength = arrayToFind.length;

		int rightIndex = sourceLength - targetLength;

		if (startIndex < 0) {
			return -1;
		}

		if (startIndex > rightIndex) {
			startIndex = rightIndex;
		}

		if (targetLength == 0) {
			return startIndex;
		}

		int lastIndex = targetLength - 1;
		byte last = arrayToFind[lastIndex];
		int min = targetLength - 1;
		int i = min + startIndex;

		startSearchForLast: while (true) {
			while ((i >= min) && (array[i] != last)) {
				i--;
			}

			if (i < min) {
				return -1;
			}

			int j = i - 1;
			int start = j - (targetLength - 1);
			int k = lastIndex - 1;

			while (j > start) {
				if (array[j--] != arrayToFind[k--]) {
					i--;
					continue startSearchForLast;
				}
			}

			return start + 1;
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Find the index of the given value in the array.
	 * </p>
	 * 
	 * <p>
	 * This method returns <code>-1</code> if <code>null</code> array input.
	 * </p>
	 * 
	 * @param array
	 *            the array to search through for the object, may be <code>null</code>
	 * @param valueToFind
	 *            the value to find
	 * @return the index of the value within the array, <code>-1</code> if not found or <code>null</code> array input
	 */
	public static int indexOf(final byte[] array, final byte valueToFind) {
		return indexOf(array, valueToFind, 0);
	}

	/**
	 * <p>
	 * Find the index of the given value in the array starting at the given index.
	 * </p>
	 * 
	 * <p>
	 * This method returns <code>-1</code> if <code>null</code> array input.
	 * </p>
	 * 
	 * <p>
	 * A negative startIndex is treated as zero. A startIndex larger than the array length will return -1.
	 * </p>
	 * 
	 * @param array
	 *            the array to search through for the object, may be <code>null</code>
	 * @param valueToFind
	 *            the value to find
	 * @param startIndex
	 *            the index to start searching at
	 * @return the index of the value within the array, <code>-1</code> if not found or <code>null</code> array input
	 */
	public static int indexOf(final byte[] array, final byte valueToFind, int startIndex) {
		if (array == null) {
			return -1;
		}
		if (startIndex < 0) {
			startIndex = 0;
		}
		for (int i = startIndex; i < array.length; i++) {
			if (valueToFind == array[i]) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(byte[] array, byte[] arrayToFind) {
		return indexOf(array, arrayToFind, 0);
	}

	public static int indexOf(byte[] array, byte[] arrayToFind, int startIndex) {
		if ((array == null) || (arrayToFind == null)) {
			return -1;
		}

		int sourceLength = array.length;
		int targetLength = arrayToFind.length;

		if (startIndex >= sourceLength) {
			return (targetLength == 0) ? sourceLength : (-1);
		}

		if (startIndex < 0) {
			startIndex = 0;
		}

		if (targetLength == 0) {
			return startIndex;
		}

		byte first = arrayToFind[0];
		int i = startIndex;
		int max = sourceLength - targetLength;

		startSearchForFirst: while (true) {
			while ((i <= max) && (array[i] != first)) {
				i++;
			}

			if (i > max) {
				return -1;
			}

			int j = i + 1;
			int end = (j + targetLength) - 1;
			int k = 1;

			while (j < end) {
				if (array[j++] != arrayToFind[k++]) {
					i++;

					continue startSearchForFirst;
				}
			}

			return i;
		}
	}

}
