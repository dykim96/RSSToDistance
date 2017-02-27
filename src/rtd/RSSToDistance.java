package rtd;

import java.io.*;

public class RSSToDistance {

	public static void main(String[] args) {
		MappingTable masterTable = new MappingTable();
		///input
		//while(all files read)
		//	read file into rssList
		//	MappingTable table = new MappingTable()
		//	for (int rss : rssList)
		//		add to table
		//	for (int i = 0; i < table.getSize(); i++) {
		//		int rss = table.getRSSList().get(i);
		//		int masterRSSIndex = masterTable.findRSS(rss);
		//		if(table.getFrequencyList().get(i) > masterTable.getFrequencyList().get(masterRSSIndex)) {
		//			masterTable.set(masterRSSIndex, rss, table.getDistanceList().get(i), table.getFrequencyList().get(i));
		//		}
		//	}
		
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
            sb.append(table.getRSSList().get(i));
            sb.append(',');
            sb.append(table.getDistanceList().get(i));
            sb.append('\n');
        }
        
        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
	}
}
