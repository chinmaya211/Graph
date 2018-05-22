package graphs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class File_Merge {
	@SuppressWarnings("resource")
	private static void merge(In[] streams) throws IOException { 
		
		BufferedWriter writer = null;
    	writer = new BufferedWriter( new FileWriter("C:\\Study_Mac\\Advanced_Computing\\Assignment3\\Graphs\\Graphs\\graphs\\Chip-seq-reads-1M-sorted.txt"));    	
        int N = streams.length; 
        IndexMinPQ<String> pq = new IndexMinPQ<String>(N); 
        for (int i = 0; i < N; i++) 
            if (!streams[i].isEmpty()) 
                pq.insert(i, streams[i].readString()); 

        // Extract and print min and read next from its stream. 
        while (!pq.isEmpty()) {
            //StdOut.print(pq.minKey() + " "); 
        	//System.out.printf("%n");
        	writer.write(pq.minKey() + " ");     	
            int i = pq.delMin(); 
            if (!streams[i].isEmpty()) 
                pq.insert(i, streams[i].readString()); 
        }
        StdOut.println();
    } 
	public static void main(String args[]) throws IOException
	{
	 String[] fnames = {"C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\AS.txt","C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\BS.txt","C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\CS.txt","C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\DS.txt"};		
	 int N = fnames.length; 
     In[] streams = new In[N]; 
     for (int i = 0; i < N; i++) 
         streams[i] = new In(fnames[i]); 
     merge(streams); 
     System.out.println("Question 5");
     System.out.println("Chip-seq-reads-1M-sorted.txt is created");
     System.out.println();
 	 System.out.println("Question 6");
 	long start,end;
 	start=System.nanoTime();
     BTree<String, String> st = new BTree<String, String>();
     
     In in;
     System.out.println("BTree");
     try {
         in = new In("C:\\Study_Mac\\Advanced_Computing\\Assignment3\\Graphs\\Graphs\\graphs\\RNA-seq-reads-1M.txt");
         while (!in.isEmpty()) {
             String s = in.readLine();
             String counter = "0";
             counter = counter+1;
             st.put(s,counter);
             //System.out.println(s);
         }
         end=System.nanoTime();
         StdOut.println("Time taken to insert the reads into Btree: " + (end-start));
         StdOut.println("size:    " + st.size());
         StdOut.println("height:  " + st.height());
         StdOut.println(st);
         
     }
     
     catch (Exception e) { 
     	//System.out.println(e);
     	}
     //System.out.println(); 
	}
    

}
