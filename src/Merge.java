import java.util.*;

public class Merge {
	
	public ArrayList <Integer> genList(ArrayList <Integer> list, int start, int end){
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		
		for(int i=start; i<end; i++)
			tempList.add(list.get(i));
		
		return tempList;
	}
	
	public ArrayList<ArrayList<Integer>> split(ArrayList <Integer> origlist){
		ArrayList<ArrayList<Integer>> templist = new ArrayList<ArrayList<Integer>>();
		int mid = origlist.size()/2;
		
		templist.add(genList(origlist, 0, mid));
		templist.add(genList(origlist, mid, origlist.size()));
		

		

		while(checkIfReadyForMerge(templist)){
			ArrayList<ArrayList<Integer>> templist2 = new ArrayList<ArrayList<Integer>>();
			for(int x = 0; x<templist.size(); x++){
				if(templist.get(x).size() > 1){
					mid = templist.get(x).size() / 2;
					templist2.add(genList(templist.get(x), 0, mid));
					templist2.add(genList(templist.get(x), mid, templist.get(x).size()));
				}
				else{
					templist2.add(templist.get(x));
				    
				}
				
			}
			templist = templist2;
		}
		
		return templist;
	}


	public boolean checkIfReadyForMerge(ArrayList<ArrayList<Integer>> list){
		for(int x = 0; x<list.size(); x++)
			if(list.get(x).size() > 1)
				return true;
		return false;
	}
	
	public ArrayList<ArrayList<Integer>> merge(ArrayList<ArrayList<Integer>> list){
		ArrayList<ArrayList<Integer>> listcopy = list;
		int mergeCount = 0;
		
		while(listcopy.size() > 1){
			mergeCount++;
			ArrayList<ArrayList<Integer>> semifinal = new ArrayList<ArrayList<Integer>>();
			int total = listcopy.size()/2;
			
			for(int x = 0; x<total; x++){
				ArrayList<Integer> templist2 = new ArrayList<Integer>();
	            int i=0;
	            int j=0;
	            int curr = x*2;
	            
	            while(i < listcopy.get(curr).size() && j < listcopy.get(curr+1).size()){
	            	if(listcopy.get(curr+1).get(j) > listcopy.get(curr).get(i)){
	            		templist2.add(listcopy.get(curr).get(i));
	                    i++;
	            	}
	            	else{
	            		templist2.add(listcopy.get(curr+1).get(j));
	            		j++;
	            	}
	            }
	            
	            while(i<listcopy.get(curr).size()){
	            	templist2.add(listcopy.get(curr).get(i));
	                i++;
	            }
	            
	            while(j<listcopy.get(curr+1).size()){
	            	templist2.add(listcopy.get(curr+1).get(j));
	                j++;
	            }

	            semifinal.add(templist2);
			}

			if(listcopy.size()%2 == 1)
				semifinal.add(listcopy.get(listcopy.size()-1));
			
			
			listcopy = semifinal;
			//System.out.println("Merge " + mergeCount);
			//print(listcopy);
		}
		
		return listcopy;
			

	}
		
	public void print(ArrayList<ArrayList<Integer>> templist){
		
		for(int i=0; i<templist.size(); i++){
			for(int j=0; j<templist.get(i).size(); j++)
				System.out.print("\t" + templist.get(i).get(j));
			System.out.println();
		}
		
	}
}
