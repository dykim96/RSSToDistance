package rtd;

import java.util.*;

public class MappingTable {
	private List<Integer> rssList;
	private List<Integer> distanceList;
	private List<Integer> frequencyList;
	private int size;
	
	public MappingTable() {
		rssList = new ArrayList<Integer>();
		distanceList = new ArrayList<Integer>();
		frequencyList = new ArrayList<Integer>();
		size = 0;
	}
	
	/*
	 * Add new/existing RSS value and distance to the table.
	 * 
	 * If RSS value and distance are new,
	 * They will be added in manner that rssList is sorted in ascending order
	 * and corresponding frequency will be initialized to 0
	 * 
	 * If RSS value and distance already exist in the table,
	 * Corresponding frequency will be incremented by 1
	 * 
	 * OUTPUT: index of added RSS value and distance in the lists
	 */
	public int add(int rss, int distance) {
		int index = findRSS(rss);
		if(index >= 0) {
			if(distanceList.get(index) == distance)
				frequencyList.set(index, frequencyList.get(index) + 1);
		}
		else {// add in ascending RSS list order
			if(size == 0 || rssList.get(size - 1) < rss){
				rssList.add(rss);
				distanceList.add(distance);
				frequencyList.add(0);
				index = size;
			}
			else if(rssList.get(0) > rss){
				rssList.add(0, rss);
				distanceList.add(0, distance);
				frequencyList.add(0, 0);
				index = 0;
			}
			else{
				int lo = 1;
				int hi = size - 1;
				while(lo <= hi) {
					int mid = lo + (hi + lo)/2;
					if(rss > rssList.get(mid - 1) && rss < rssList.get(mid)){
						index = mid;
						break;
					}
					else if(rss > rssList.get(mid))
						lo = mid;
					else
						hi = mid;
				}
				rssList.add(index, rss);
				distanceList.add(index, distance);
				frequencyList.add(index, 0);
			}
			size++;
		}
		
		return index;
	}
	
	/*
	 * Search for the RSS value in the rssList
	 * and replaces the distance in distanceList with input.
	 * 
	 * If a such RSS value does not exist,
	 * method adds the RSS value and distance into the lists.
	 */
	public void set(int rss, int distance, int frequency) {
		int index = add(rss, distance);
		if(distanceList.get(index) != distance){
			distanceList.set(index, distance);
			frequencyList.set(index, 0);
		}
	}
	
	public void set(int index, int rss, int distance, int frequency) {
		rssList.set(index, rss);
		distanceList.set(index, distance);
		frequencyList.set(index, frequency);
	}
	
	/*
	 * Search for RSS value in rssList
	 * returns index of RSS value if it exists
	 * else returns -1
	 */
	public int findRSS(int rss) {
		if(size == 0)
			return -1;

		int lo = 0;
		int hi = size - 1;
		while(lo <= hi) {
			int mid = lo + (hi + lo)/2;
			if(rss < rssList.get(mid))
				hi = mid;
			else if(rss > rssList.get(mid))
				lo = mid;
			else
				return mid;
		}
		
		return -1;
	}

	public List<Integer> getRSSList() {
		return rssList;
	}
	
	public List<Integer> getDistanceList() {
		return distanceList;
	}

	public List<Integer> getFrequencyList() {
		return frequencyList;
	}
	
	public int getSize() {
		return size;
	}

}
