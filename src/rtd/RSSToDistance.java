package rtd;

import java.io.*;

public class RSSToDistance {
	
	static int NUMBER_OF_FILES = 1; // TODO

	public static void main(String[] args) throws IOException {
		MappingTable masterTable = new MappingTable();

		/* 
		 * iterates through all the files and reads each file
		 * and adds its rss and distance and tracks the freq it occurs
		 */
		MappingTable table = new MappingTable();
        for (int i = 0; i < NUMBER_OF_FILES; ++i)
        {
            int dist = i; // TODO
            File data_file = new File("1.txt"); // TODO find filename
            Reader file_reader = new FileReader(data_file);
            BufferedReader reader = new BufferedReader(file_reader);

            String line;
            while ((line = reader.readLine()) != null)    
            {
                int rss  = Integer.parseInt(line);
                table.add(rss, dist);                
            }            
        }   
        
        // iterate through the table's data and goes through each rss value and add the highest freq to masterTable
		for (int i = 0; i < table.getSize(); i++) 
		{
			int rss = table.getRSS(i);
			int masterRSSIndex = masterTable.findRSS(rss);
			
			if (masterRSSIndex == -1) // doesnt exists
			{
				masterTable.add(table.getData(i));
			}
			else if (table.getFreq(i) > masterTable.getFreq(masterRSSIndex)) 
			{
				masterTable.set(masterRSSIndex, rss, table.getDist(i), table.getFreq(i));
			}
		}
		
		///output csv file
		try {
			writeCSV(masterTable);
		}
		catch(Exception ex){}
	}
	
	private void readFiles(){
		
	}
	
	private static void writeCSV(MappingTable table) throws Exception{
        PrintWriter pw = new PrintWriter(new File("test.csv"));
        StringBuilder sb = new StringBuilder();	
        
        sb.append("RSS");
        sb.append(',');
        sb.append("Distance");
        sb.append('\n');
        for(int i = 0; i < table.getSize(); i++){
            sb.append(table.getRSS(i));
            sb.append(',');
            sb.append(table.getDist(i));
            sb.append('\n');
        }
        
        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
	}
}
