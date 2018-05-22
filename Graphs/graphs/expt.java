package graphs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class expt {
public static void main(String args[]) {
	long start,end;
	 System.out.println("Question 6");
 	start=System.nanoTime();
     BTree<String, String> st = new BTree<String, String>();
     
     In in9;
     System.out.println("BTree");
     try {
         in9 = new In("C:\\Study_Mac\\Advanced_Computing\\Assignment3\\Graphs\\Graphs\\graphs\\A.txt");
         while (!in9.isEmpty()) {
             String s1 = in9.readLine();
             String counter = "0";
             counter = counter+1;
             st.put(s1,counter);
             //System.out.println(s);
         }
         end=System.nanoTime();
         StdOut.println("Time taken to insert the reads into Btree: " + (end-start));
         StdOut.println("size:    " + st.size());
         StdOut.println("height:  " + st.height());
         start=System.nanoTime();
         StdOut.println(st);

         PrintStream printStreamToFile = new PrintStream(new File("C:\\Study_Mac\\Advanced_Computing\\Assignment3\\Graphs\\Graphs\\graphs\\Btree.txt"));
         System.setOut(printStreamToFile);
         printStreamToFile.println(st);
         System.out.println("Stored in Btree.txt");
         end=System.nanoTime();
         StdOut.println("Time taken for inorder traversal of Btree: " + (end-start));
     }
     
     catch (Exception e) { 
     	//System.out.println(e);
     	}
}
    
}

