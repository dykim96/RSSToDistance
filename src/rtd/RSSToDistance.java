package rtd;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RSSToDistance {
	
	// global constants
	static int NUMBER_OF_FILES = 11; // TODO
	static int ROUND = 1; //specifies what # the rss should round to  
	static int RSS_SAMPLES_PER_FT = 11; // each ft will only have this many different rss values at most
	
	public static void main(String[] args) throws IOException {
		MappingTable masterTable = new MappingTable();

		/* 
		 * iterates through all the files and reads each file
		 * and adds its rss and distance and tracks the freq it occurs
		 */
		MappingTable table = new MappingTable();
		int dist = 1; // TODO what distance are we starting at
        for (int i = 0; i < NUMBER_OF_FILES; ++i)
        {
            File data_file = new File("MAR_7_1sttry_" + dist + "ft" + ".txt"); // TODO append correct name
            Reader file_reader = new FileReader(data_file);
            BufferedReader reader = new BufferedReader(file_reader);

            ArrayList<Integer> rsslist = new ArrayList<Integer>();	// holds all the original rss values without removing any
            ArrayList<Integer> rss_roundedlist = new ArrayList<Integer>();	// holds all the rss values after rounding
            Set<Integer> rss_roundedset = new HashSet<Integer>(); // holds all the rss values after rounding and combining
            
            String line;
            while ((line = reader.readLine()) != null)    
            {
            	String[] splitted = line.split("\\s+");
            	for (String str : splitted)
            	{
            		int rss  = (int) Math.rint(Double.parseDouble(str)); 
            		
//            		if (rss < 700) // temporarrily put this in becasue this data set has bad values
//            			continue;
            		
            		// add all the rss values without rounding (except for cutting off decimals
            		rsslist.add(rss);         
            		rss_roundedlist.add(rss);            		
            		rss_roundedset.add(rss);
            		
//                    int rss  = (int) Math.rint(Double.parseDouble(str));              
//                    rss = (rss + (ROUND - 1)) / ROUND * ROUND; // round to nearest "ROUND"
//                    table.add(rss, dist);     	
            	}           
            }          
            
            while (rss_roundedset.size() > RSS_SAMPLES_PER_FT) // rounded list has too many unique rss values so make ROUND higher
            {            	
            	rss_roundedlist.clear(); // clear the rounded list
            	rss_roundedset.clear(); // clear the set
            	++ROUND; // increment ROUND
            	
            	// iterate through rss list
            	for (int rss : rsslist)
            	{
                	rss = (rss + (ROUND - 1)) / ROUND * ROUND; // round to nearest "ROUND"
                	rss_roundedlist.add(rss);
                	rss_roundedset.add(rss); // so no duplicates exists
            	}
            }
            
            // adds all the rss from the rounded list to our table
            for (int rss : rss_roundedlist)
            {
                table.add(rss, dist);     	
            }
            
        	// System.out.println("DEBUG: dist = " + dist + ". ROUND = " + ROUND);
        	ROUND = 1; // reset ROUND
            dist++; // TODO currently assumes each different test is 1 ft apart
        }   
        
        // iterate through the table's data and goes through each rss value and add the highest freq to masterTable
		for (int i = 0; i < table.getSize(); i++) 
		{
			int rss = table.getRSS(i);
			int masterRSSIndex = masterTable.findRSS(rss);
			
			if (masterRSSIndex == -1)
			{ masterTable.add(table.getData(i)); }
			else if (table.getFreq(i) > masterTable.getFreq(masterRSSIndex)) 
			{ masterTable.set(masterRSSIndex, rss, table.getDist(i), table.getFreq(i)); }
		}
		
		///output csv file
		try { writeCSV(masterTable); }
		catch(Exception ex){}
	}
	
	private void readFiles(){
		
	}
	
	private static void writeCSV(MappingTable table) throws Exception{
        PrintWriter pw = new PrintWriter(new File("test.csv"));
        StringBuilder sb = new StringBuilder();	
        
        // version 1
        sb.append("RSS");
        sb.append(',');
        sb.append("Distance");
        sb.append(',');
        sb.append("Frequency");
        sb.append('\n');
        for(int i = 0; i < table.getSize(); i++){
            sb.append(table.getRSS(i));
            sb.append(',');
            sb.append(table.getDist(i));
            sb.append(',');
            sb.append(table.getFreq(i));
            sb.append('\n');
        }
        
        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
	}
}
