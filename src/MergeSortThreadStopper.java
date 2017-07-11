import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MergeSortThreadStopper implements Runnable{
	
	Thread t;
	List<Integer> toSort;
	List<Integer> list;
	
	public MergeSortThreadStopper(List<Integer> toSort) {
		super();
		this.toSort = toSort;
		list = new ArrayList<Integer>();
		t = new Thread(this);
		t.start();
	}

	public Thread getT() {
		return t;
	}

	public void setT(Thread t) {
		this.t = t;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(Thread.activeCount() > 5){

			//get midpoint
			int mid = toSort.size()/2;
			
			//start thread 1
			MergeSortThreadStopper thread1 = new MergeSortThreadStopper(toSort.subList(0, mid));
			
			//start thread 2
			MergeSortThreadStopper thread2 = new MergeSortThreadStopper(toSort.subList(mid, toSort.size()));
			
			//while thread 1 & 2 not terminated -> keep waiting
			while(thread1.t.isAlive() || thread2.t.isAlive()){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//merge lists of thread 1 & 2
			merge(thread1.list, thread2.list);
			//end
		}else{
			list = sequential((ArrayList<Integer>) toSort);
		}

	}
	
	
	private void merge(List<Integer> list1, List<Integer> list2){
		List<Integer> tempList = new ArrayList<Integer>();
		
		int index1=0, index2=0;
		
		while(index1 != list1.size() || index2 != list2.size()){
			if(index1==list1.size()){
				tempList.add(list2.get(index2));
				index2++;
			}else if(index2==list2.size()){
				tempList.add(list1.get(index1));
				index1++;
			}else if(list1.get(index1) < list2.get(index2)){
				tempList.add(list1.get(index1));
				index1++;
			}else if(list1.get(index1) > list2.get(index2)){
				tempList.add(list2.get(index2));
				index2++;		
			}
		}
		
		list = tempList;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}
	
	public static List<Integer> sequential(ArrayList<Integer> list){
		Merge m = new Merge();
		ArrayList<ArrayList<Integer>> t = m.split(list);
		
		//finalList is final sorted
		ArrayList<ArrayList<Integer>> finalList = m.merge(t);
		ArrayList<Integer> finalFinal = new ArrayList<Integer>();

		for(int i=0; i<finalList.get(0).size(); i++){
			finalFinal.add(finalList.get(0).get(i));
		}
		
		return finalFinal;

	}
	
}
