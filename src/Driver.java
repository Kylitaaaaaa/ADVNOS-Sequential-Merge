
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Driver {
	private static final Random RAND = new Random(42);
	private static final int bound = 100;
//	public static void main(String[] args) {
//		Merge m = new Merge();
//		ArrayList<Integer> list = new ArrayList<Integer>(); 
//		list.add(54);
//		list.add(-26);
//		list.add(923);
//		list.add(-2317);
//		list.add(772);
//		list.add(10000);
//		list.add(31);
//		list.add(44232);
//		list.add(5345);
//		list.add(230);
//		list.add(230);
//		list.add(230);
//		list.add(31);
//		//list.add(-1);
//		ArrayList<ArrayList<Integer>> t = m.split(list);
//		
//		System.out.println("After Split: ");
//		
//		for(int i=0; i<t.size(); i++){
//			for(int j=0; j<t.get(i).size(); j++)
//				System.out.print("\t" + t.get(i).get(j));
//			System.out.println();
//		}
//		System.out.println();
//		
//		ArrayList<ArrayList<Integer>> finalList = m.merge(t);
//		
//		System.out.println();
//		System.out.println("Final Answer: ");
//		
//		m.print(finalList);
//		
//	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("here");
		
        long ta = System.currentTimeMillis();
        sortRandom(1000);
        //sortRandom(100);
        long tb = System.currentTimeMillis();
        System.out.println("TIME: "+(tb-ta)+" MS");
		
	}
	
	public static void sortRandom(int length){
		ArrayList<Integer> list = createRandomArray(length); 
		
		for(int i=0; i<list.size(); i++)
			System.out.println(list.get(i));
		System.out.println("DONE PRINTING");
		
		Merge m = new Merge();
		ArrayList<ArrayList<Integer>> t = m.split(list);
		
		System.out.println("After Split: ");
		
		for(int i=0; i<t.size(); i++){
			for(int j=0; j<t.get(i).size(); j++)
				System.out.print("\t" + t.get(i).get(j));
			System.out.println();
		}
		System.out.println();
		
		ArrayList<ArrayList<Integer>> finalList = m.merge(t);
		
		System.out.println();
		System.out.println("Final Answer: ");
		
		m.print(finalList);

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
