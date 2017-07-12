import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MergeSortForkJoin extends RecursiveAction {
    private int SEQUENTIAL_THRESHOLD = 6;
	private int[] numbers;
	private int startPos, endPos;
    int[] result;

    public MergeSortForkJoin(int[] numbers, int startPos, int endPos, int thresh) {
		super();
		this.numbers = numbers;
		this.startPos = startPos;
		this.endPos = endPos;
		this.SEQUENTIAL_THRESHOLD = thresh;
		this.result = new int[size()];

	}
    private void merge(MergeSortForkJoin left, MergeSortForkJoin right) {
        int i=0, leftPos=0, rightPos=0, leftSize = left.size(), rightSize = right.size();
        while (leftPos < leftSize && rightPos < rightSize)
            result[i++] = (left.result[leftPos] <= right.result[rightPos])
                    ? left.result[leftPos++]
                    : right.result[rightPos++];
        while (leftPos < leftSize)
            result[i++] = left.result[leftPos++];
        while (rightPos < rightSize)
            result[i++] = right.result[rightPos++];
    }
 
    public int size() {
        return endPos-startPos;
    }
 
    protected void compute() {
        if (size() < SEQUENTIAL_THRESHOLD) {
        	sort(numbers);
        }
        else {
            int midpoint = size() / 2;
            MergeSortForkJoin left = new MergeSortForkJoin(numbers, startPos, startPos+midpoint, SEQUENTIAL_THRESHOLD);
            MergeSortForkJoin right = new MergeSortForkJoin(numbers, startPos+midpoint, endPos, SEQUENTIAL_THRESHOLD);
            invokeAll(left, right);
            merge(left, right);
        }
    }
    
    public void sort(int[] nos){
        System.arraycopy(numbers, startPos, result, 0, size());
        Arrays.sort(result, 0, size());
    	
    }
    
}