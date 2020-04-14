public class Searcher {
	
	public Searcher() {}
	public int doBinarySearch(int[] array, int value) {
		int index = binarySearch(array, 0, array.length, value);
		if (array[index] != value) {
			return -1;
		} else {
			index--;
			while (index >= 0 && array[index] == value) {
				index--;
			}
			index++;
			return index;
		}
	}
	private int binarySearch(int[] array, int left, int right, int value) {
		if (left >= right) {
			return array[left];
		}
		int mid = (left + right)/2;
		if (mid == left || mid == right) {return mid;}
		if (array[mid] == value) {
			return mid;
		} else if (array[mid] < value) {
			return binarySearch(array, mid, right, value);
		} else {
			return binarySearch(array, left, mid, value);
		}
	}
}
