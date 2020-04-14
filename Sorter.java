public class Sorter {
	int reads = 0;
	int writes = 0;
	int calls = 0;
	
	public void reset() {
		reads = 0;
		writes = 0;
		calls = 0;
	}
	
	public int getReads() {
		return reads;
	}
	
	public int getWrites() {
		return writes;
	}
	
	public int getCalls() {
		return calls;
	}
	
	public void doMergeSort(int[] sortMe) {
		calls++;
		int[] newArray =  mergeSort(sortMe, 0, sortMe.length);
		reads += sortMe.length;
		writes += sortMe.length;
		
		for (int i = 0; i < sortMe.length; i++) {
			sortMe[i] = newArray[i];
		}
	}
	
	private int[] mergeSort(int[] sortMe, int left, int right) {
		if (right - left <= 1) {
			return new int[] {sortMe[left]};
		}
		
		int mid = (left+right) / 2;
		calls += 2;
		int[] a = mergeSort(sortMe, left, mid);
		int[] b = mergeSort(sortMe, mid, right);
		int[] newArray = new int[a.length + b.length];
		int currentA = 0;
		int currentB = 0;
		int currentIndex = 0;
		System.out.println((a.length + b.length) + " " + (right - left));
		
		while (currentA != a.length || currentB != b.length) {
			writes++;
			reads++;
			if (currentA == a.length) {
				newArray[currentIndex] = b[currentB];
				currentB++;
			} else if (currentB == b.length) {
				newArray[currentIndex] = a[currentA];
				currentA++;
			} else if (a[currentA] <= b[currentB]) {
				newArray[currentIndex] = a[currentA];
				currentA++;
			} else {
				newArray[currentIndex] = b[currentB];
				currentB++;
			}
			currentIndex++;
		}
		
		return newArray;
	}
	
	public void doQuickSort(int[] sortMe) {
		calls++;
		quickSort(sortMe, 0, sortMe.length);
	}
	
	private void quickSort(int[] sortMe, int left, int right) {
		if (left < right) {
			calls += 2;
			int pivotLocation = partition(sortMe, left, right);
			quickSort(sortMe, left, pivotLocation);
			quickSort(sortMe, pivotLocation+1, right);
		}
	}
	
	private int partition(int[] sortMe, int left, int right) {
		reads++;
		int pivot = sortMe[left];
		int leftBound = left+1;
		
		for (int i = left+1; i < right; i++) {
			reads++;
			if (sortMe[i] < pivot) {
				reads += 2;
				writes += 2;
				int temp = sortMe[i];
				sortMe[i] = sortMe[leftBound];
				sortMe[leftBound] = temp;
				leftBound++;
			}
		}
		
		reads++;
		writes += 2;
		sortMe[left] = sortMe[leftBound-1];
		sortMe[leftBound-1] = pivot;
		return leftBound-1;
	}
}
