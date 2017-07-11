
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Driver {
	private static final Random RAND = new Random(42);
	private static final int bound = 100;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = sortRandom(10000000);
		parallel(list);
		sequential(list);
		
	}
	
	public static void sequential(ArrayList<Integer> list){
		/*****Start of Sequential*****/
		long ta = System.currentTimeMillis();
		
		Merge m = new Merge();
		ArrayList<ArrayList<Integer>> t = m.split(list);
		
		//finalList is final sorted
		ArrayList<ArrayList<Integer>> finalList = m.merge(t);
		
		long tb = System.currentTimeMillis();
        System.out.println("SEQUENTIAL TIME: "+(tb-ta)+" MS");
		
		/*****End of Sequential*****/
	}
	
	public static void parallel(ArrayList<Integer> list){
		/*****Start of Parallel*****/
        long ta = System.currentTimeMillis();
		MergeSortThreadStopper sort = new MergeSortThreadStopper(list);
		
		while(sort.t.isAlive()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		long tb = System.currentTimeMillis();
        System.out.println("PARALLEL TIME: "+(tb-ta)+" MS");
		
		/*****End of Parallel*****/
	}
	
	public static ArrayList<Integer> sortRandom(int length){
		ArrayList<Integer> list = createRandomArray(length); 
		
		return list;
	}
	
	
	
	public static ArrayList <Integer> createRandomArray(int length) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			list.add(RAND.nextInt(bound));
		}
		System.out.println("doneeeee");
		return list;
	}
	

}
