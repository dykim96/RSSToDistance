package rtd;

import java.util.*;

public class MappingTable {
	
	/*
	 * Private class that contains all necessary components for a data value
	 */
	private class Data
	{
		private int rss;
		private int dist;
		private int freq;
		
		private Data (int rss, int dist, int freq)
		{
			set(rss, dist, freq);
		}
		
		/* since none of these values can take in a negative,
		 * if a negative # is given, it won't be set
		 */		
		private void set(int rss, int dist, int freq)
		{
			if (rss >= 0)
				this.rss = rss;
			if (dist >= 0)
				this.dist = dist;
			if (freq >= 0)
				this.freq = freq;
		}
		
		private void addFreq(int freq)
		{
			this.freq += freq;
		}
		
		public int getRSS()
		{ return rss; }
		
		private int getDist()
		{ return dist; }
		
		private int getFreq()
		{ return freq; }
	}
	
	private List<Data> dataList;
	
	public MappingTable() {
		dataList = new ArrayList<Data>();
	}
	
	/*
	 * Add new/existing RSS value and distance to the table.
	 * 
	 * If RSS value and distance are new,
	 * They will be added
	 * 
	 * If RSS value and distance already exist in the table,
	 * Corresponding frequency will be incremented by 1
	 * 
	 * OUTPUT: none
	 */
	public void add(int rss, int dist) {
		
		int index = findRSS(rss);
		Data data = new Data(rss, dist, 1);
		
		if (index == -1) // doesnt exist, so just add it
		{
			dataList.add(data);
		}
		else // exists so modify existing
		{
			data.addFreq(dataList.get(index).getFreq());
			dataList.set(index, data);
		}
	}
	
	public void add(Data data)
	{
		dataList.add(data);
	}
	
	public int getRSS(int index)
	{
		return dataList.get(index).getRSS();
	}
	
	public int getDist(int index)
	{
		return dataList.get(index).getDist();
	}
	
	public int getFreq(int index)
	{
		return dataList.get(index).getFreq();
	}
	
	public Data getData(int index)
	{
		return dataList.get(index);
	}
	
	/*
	public void set(int rss, int distance, int frequency) {
		int index = add(rss, distance);
		if(distanceList.get(index) != distance){
			distanceList.set(index, distance);
			frequencyList.set(index, 0);
		}
	}*/
	
	/*	
	 * Sets the dataList at index to a new Data object containing the rss, dist, freq
	 */
	public void set(int index, int rss, int distance, int frequency) {
		dataList.set(index, new Data(rss, distance, frequency));
	}
	
	/*
	 * Search for RSS value in rssList
	 * returns index of RSS value if it exists
	 * else returns -1
	 */
	public int findRSS(int rss) {
		
		for (int i = 0; i < dataList.size(); ++i)
		{
			Data data = dataList.get(i);
			if (data.getRSS() == rss)
				return i;
		}
		
		return -1;
	}

/*	public List<Integer> getRSSList() {
		return rssList;
	}
	
	public List<Integer> getDistanceList() {
		return distanceList;
	}

	public List<Integer> getFrequencyList() {
		return frequencyList;
	}*/
	
	public int getSize() {
		return dataList.size();
	}

}
