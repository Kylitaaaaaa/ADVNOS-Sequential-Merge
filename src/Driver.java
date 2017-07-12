
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Driver {
	private static final Random RAND = new Random(42);
	private static final int bound = 100;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int len=10;
		for(int i=0; i<5; i++){
			ArrayList<Integer> list = sortRandom(len);
			System.out.println("# of Elements: "+len);
			//mergeSorter(list);
			sequential(list);
			forkJoin(list,10);
			forkJoinAdapt(list);
			parallel(list);
			len=len*10;
			
		}
		len=len/10;
		len=len+100000;
		for(int i=0; i<14; i++){
			ArrayList<Integer> list = sortRandom(len);
			System.out.println("# of Elements: "+len);
			//mergeSorter(list);
			sequential(list);
			forkJoin(list,10);
			forkJoinAdapt(list);
			parallel(list);
			len=len+100000;
			
		}
		
	}
	
	public static void forkJoin(List<Integer> list, int threshold){
		
		long ta = System.currentTimeMillis();
		long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		int[] numbers = toIntArray(list);
		MergeSortForkJoin fj = new MergeSortForkJoin(numbers, 0, numbers.length-1,threshold);	
		long tb = System.currentTimeMillis();
		long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println(""+(tb-ta));
		
		/*****End of Sequential*****/
	}
	
	public static void forkJoinAdapt(List<Integer> list){
		long ta = System.currentTimeMillis();
		long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		int[] numbers = toIntArray(list);
		MergeSortForkJoin fj = new MergeSortForkJoin(numbers, 0, numbers.length-1, numbers.length/3);	
		long tb = System.currentTimeMillis();
		long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println(""+(tb-ta));
		
		/*****End of Sequential*****/
	}

	public static void sequential(ArrayList<Integer> list){
		/*****Start of Sequential*****/
		long ta = System.currentTimeMillis();
		long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		
		Merge m = new Merge();
		ArrayList<ArrayList<Integer>> t = m.split(list);
		
		//finalList is final sorted
		ArrayList<ArrayList<Integer>> finalList = m.merge(t);
		ArrayList<Integer> finalFinal = new ArrayList<Integer>();

		for(int i=0; i<finalList.get(0).size(); i++){
			finalFinal.add(finalList.get(0).get(i));
		}
		
		long tb = System.currentTimeMillis();
		long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println(""+(tb-ta) );
		
		/*****End of Sequential*****/
	}
	
	public static void parallel(ArrayList<Integer> list){
		/*****Start of Parallel*****/

        long ta = System.currentTimeMillis();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		sortParallel(list);
        long tb = System.currentTimeMillis();
        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		System.out.println(""+(tb-ta) );
		
		/*****End of Parallel*****/
	}

	public static void parallelSlow(ArrayList<Integer> list){
		/*****Start of Parallel*****/

        long ta = System.currentTimeMillis();
        sortParallelSlow(list);
        long tb = System.currentTimeMillis();
        System.out.println("PARALLEL SLOW TIME: "+(tb-ta)+" MS");
		
		/*****End of Parallel*****/
	}
	
	public static ArrayList<Integer> sortRandom(int length){
		ArrayList<Integer> list = createRandomArray(length); 
		
		return list;
	}
	
	public static ArrayList<Integer> getAscending(int length){
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=1; i<=length; i++)
			list.add(i);
		return list;
	}
	
	public static ArrayList<Integer> getDescending(int length){
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=length; i>0; i--){
			list.add(i);
			//System.out.println(i);
		}
		return list;
	}
	
	
	
	public static ArrayList <Integer> createRandomArray(int length) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			list.add(RAND.nextInt(bound));
		}

		return list;
	}
	
	public static void sortParallel(List a){

		MergeSortThreadStopper sort = new MergeSortThreadStopper(a);
		Thread sortT = new Thread(sort);
		sortT.start();
		
		try {
			sortT.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	

	public static void sortParallelSlow(List a){

		MergeSort sort = new MergeSort(a);
		
		while(sort.t.isAlive()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}
	
	public static int[] toIntArray(List<Integer> list){
		  int[] ret = new int[list.size()];
		  for(int i = 0;i < ret.length;i++)
		    ret[i] = list.get(i);
		  return ret;
		}
	

}
