package graphs;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Assignment3 {

	 public static void splitFile(File f) throws IOException {
	        int sizeOfFiles = 1024 * 1031 * 9;// 9MB
	        byte[] buffer = new byte[sizeOfFiles];

	        String fileName = f.getName();

	        //try-with-resources to ensure closing stream
	        try (FileInputStream fis = new FileInputStream(f);
	             BufferedInputStream bis = new BufferedInputStream(fis)) {
               Character file_name  = 'A';
	            int bytesAmount = 0;
	            while ((bytesAmount = bis.read(buffer)) > 0) {
	                //write each chunk of data into separate file with different number in name
	                String filePartName = String.format(file_name++  + ".txt", fileName);
	                File newFile = new File(f.getParent(), filePartName);
	                try (FileOutputStream out = new FileOutputStream(newFile)) {
	                    out.write(buffer, 0, bytesAmount);
	                }
	            }
	        }
	    }
	 
	 
	 @SuppressWarnings({ "unused", "resource" })
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
	 public static void main(String[] args) throws IOException {
		 long start,end;
	        //In in = new In(args[0]);

		 
		 System.out.println("Question 1"); 
		    start = System.nanoTime();
		    
	    	In in = new In("largeDG.txt");	    	
	        Digraph G = new Digraph(in);
	        DepthFirstOrder dfs1 = new DepthFirstOrder(G);
	      
	        dfs1.dfs(G,1);
	        end = System.nanoTime();
	        System.out.println("Time taken to create DFS in nanoseconds: " + (end-start)); 
	       
	        start = System.nanoTime();
	        StdOut.print("Preorder:  ");
	        dfs1.pre();
	        for (int v : dfs1.pre()) {
	            StdOut.print("");
	        } 
	        end = System.nanoTime();
	        StdOut.println();
	        StdOut.println("Time taken by DFS for Preorder in nanoseconds: " + (end-start));

	        
	        StdOut.print("Postorder: ");
	        start = System.nanoTime();
	        dfs1.post();
	       for (int v : dfs1.post()) {
	            StdOut.print("");
	        } 
	        StdOut.println();

	        end = System.nanoTime();
	        StdOut.println("Time taken by DFS for Postorder in nanoseconds: " + (end-start));	       	     
	          
	        StdOut.println("Question 2(a)");
	        StdOut.println("DijkstraSP:");	       	     
	        start = System.nanoTime();   
	        In in1 = new In("largeEWG.txt");
	        EdgeWeightedDigraph G1 = new EdgeWeightedDigraph(in1);
	        //int s = Integer.parseInt(args[1]);
	        int s = 1;

	        // compute shortest paths
	        DijkstraSP sp = new DijkstraSP(G1, s);

	        // print shortest path
	        for (int t = 0; t < G1.V(); t++) {
	            if (sp.hasPathTo(t)) {
	                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
	                if (sp.hasPathTo(t)) {
	                    for (DirectedEdge e : sp.pathTo(t)) {
	                        StdOut.print(e + "   ");
	                    }
	                }
	               // StdOut.println();
	            }
	            //else {
	             //   StdOut.printf("%d to %d         no path\n", s, t);
	            //}
	        }
	        end = System.nanoTime();
	        StdOut.println();
	        StdOut.println("Time taken by DijkstraSP in nanoseconds: " + (end-start));
	        
	        StdOut.println("Question 2(b)");
	        start = System.nanoTime();
	        In in2 = new In("largeEWG.txt");
	        EdgeWeightedGraph G2 = new EdgeWeightedGraph(in2);
	        KruskalMST mst = new KruskalMST(G2);
	        //for (Edge e : mst.edges()) {
	            //StdOut.println(e);
	            //StdOut.println("");
	       // }
	        end = System.nanoTime();
	        StdOut.println("Time taken by Minimum Spanning Tree in nanoseconds: " + (end-start));
		   
	        StdOut.println("Question 3");
	        StdOut.println("Finding all connected components ");	        
	        start = System.nanoTime();
	        SymbolGraph sg1 = new SymbolGraph("movies.txt", "/");
	        Graph G7 = sg1.G();
	        CC cc = new CC(G7);
	        // number of connected components
	        int M = cc.count();
	        StdOut.println(M + " components");

	        // compute list of vertices in each connected component
	        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
	        for (int i = 0; i < M; i++) {
	            components[i] = new Queue<Integer>();
	        }
	        for (int v = 0; v < G7.V(); v++) {
	            components[cc.id(v)].enqueue(v);
	            StdOut.print("");
	        }
	        end = System.nanoTime();
	        StdOut.print("time taken by DFS to find all the components in nanoseconds: " + (end-start));
	        // print results
	        //for (int i = 0; i < M; i++) {
	           // for (int v : components[i]) {
	            //    StdOut.print("");
	           // }
	           // StdOut.println();
	       // }
	        StdOut.println();
	        
	        
	        StdOut.println("Question 4");
	        StdOut.println("Searching actors: ");	
	        SymbolGraph sg = new SymbolGraph("movies.txt", "/");
	        Graph G8 = sg.G();
	        String actor = "DiCaprio, Leonardo";
			StdOut.println();
			StdOut.println("Movies Starred by "+actor+" : ");
			for (int v : G8.adj(sg.index(actor))) {
				StdOut.println("   " + sg.name(v));
			}
			
			List<String> Julia_Roberts = new ArrayList<>();
			actor = "Roberts, Julia (I)";
			StdOut.println();
			StdOut.println("Movies Starred by "+actor+" : ");
			for (int v : G8.adj(sg.index(actor))) {
				StdOut.println("   " + sg.name(v));
				Julia_Roberts.add(sg.name(v));
			}
			
			List<String> Julia_And_Hugh = new ArrayList<>();
			actor = "Grant, Hugh (I)";
			StdOut.println();
			StdOut.println("Movies Starred by "+actor+" : ");
			for (int v : G8.adj(sg.index(actor))) {
				StdOut.println("   " + sg.name(v));
				if(Julia_Roberts.contains(sg.name(v)))
					Julia_And_Hugh.add(sg.name(v));
			}
			
			actor = "Roberts, Julia (I) & Grant, Hugh (I)";
			StdOut.println();
			StdOut.println("Movies Starred by "+actor+" : ");
			for(String movies : Julia_And_Hugh) {
				StdOut.println("   " + movies);
			}
			
			StdOut.println();        
	        
	        StdOut.println("Question 5");
	        start=System.nanoTime();
	        StdOut.println("Splitting the file into 4 equal files: ");
	       
	        try{  
	        	  // Reading file and getting no. of files to be generated  
	        	  String inputfile = "C:\\Study_Mac\\Advanced_Computing\\Assignment3\\Graphs\\Graphs\\graphs\\RNA-seq-reads-1M.txt"; //  Source File Name.  
	        	  double nol = 250000.0; //  No. of lines to be split and saved in each output file.  
	        	  File file = new File(inputfile);  
	        	  Scanner scanner = new Scanner(file);  
	        	  int count = 0;  
	        	  while (scanner.hasNextLine())   
	        	  {  
	        	   scanner.nextLine();  
	        	   count++;  
	        	  }  
	        	  System.out.println("Lines in the file: " + count);     // Displays no. of lines in the input file.  

	        	  double temp = (count/nol);  
	        	  int temp1=(int)temp;  
	        	  int nof=0;  
	        	  if(temp1==temp)  
	        	  {  
	        	   nof=temp1;  
	        	  }  
	        	  else  
	        	  {  
	        	   nof=temp1+1;  
	        	  }  
	        	  System.out.println("No. of files to be generated :"+nof); // Displays no. of files to be generated.  

	        	  FileInputStream fstream = new FileInputStream(inputfile); DataInputStream in10 = new DataInputStream(fstream);  

	        	  BufferedReader br3 = new BufferedReader(new InputStreamReader(in10)); String strLine;  

	        	  String[] s1 = {"A","B","C","D"};
	        	  for (int j=1;j<=nof;j++)  
	        	  {  
	        	   FileWriter fstream1 = new FileWriter("C:\\Study_Mac\\Advanced_Computing\\Assignment3\\Graphs\\Graphs\\graphs\\"+s1[j-1]+".txt");     // Destination File Location  
	        	   BufferedWriter out = new BufferedWriter(fstream1);   
	        	   for (int i=1;i<=nol;i++)  
	        	   {  
	        	    strLine = br3.readLine();   
	        	    if (strLine!= null)  
	        	    {  
	        	     out.write(strLine);   
	        	     if(i!=nol)  
	        	     {  
	        	      out.newLine();  
	        	     }  
	        	    }  
	        	   }  
	        	   out.close();  
	        	  }  
	        	  in.close();  
	        	  }catch (Exception e)  
	        	  {  
	        	   System.err.println("Error: " + e.getMessage());  
	        	  }  
	        
	        StdOut.println("Sorting the data in these 4 files: ");
	        BufferedReader  reader = null;
	        PrintWriter outputStream = null;
	        ArrayList<String> rows = new ArrayList<String>();
	        
	        String str = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\A.txt";
	        String str1 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\AS.txt";

	        try {
	            reader  = new BufferedReader(new FileReader(str));
	            outputStream = new PrintWriter(new FileWriter(str1));

	            String file;
	            while ((file = reader .readLine()) != null) {
	                rows.add(file);
	            }
	            Collections.sort(rows);
	            String[] strArr= rows.toArray(new String[0]);
	            for (String cur : strArr)
	                outputStream.println(cur);
	        }        
	        	 finally {
	            //if (reader  != null) {
	                reader.close();
	           // }
	           // if (outputStream != null) {
	                outputStream.close();
	            }
	        StdOut.println("AS.txt is created");
	        
	        
	        
	        BufferedReader  reader1 = null;
	        PrintWriter outputStream1 = null;
	        ArrayList<String> rows1 = new ArrayList<String>();
	        
	        String str2 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\B.txt";
	        String str3 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\BS.txt";

	        try {
	            reader1  = new BufferedReader(new FileReader(str2));
	            outputStream1 = new PrintWriter(new FileWriter(str3));

	            String file;
	            while ((file = reader1 .readLine()) != null) {
	                rows1.add(file);
	            }
	            Collections.sort(rows1);
	            String[] strArr= rows1.toArray(new String[0]);
	            for (String cur : strArr)
	                outputStream1.println(cur);
	        }        
	        	 finally {
	            //if (reader  != null) {
	                reader1.close();
	           // }
	           // if (outputStream != null) {
	                outputStream1.close();
	            }
	        StdOut.println("BS.txt is created");
	        
	        BufferedReader  reader2 = null;
	        PrintWriter outputStream2 = null;
	        ArrayList<String> rows2 = new ArrayList<String>();
	        
	        String str4 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\C.txt";
	        String str5 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\CS.txt";

	        try {
	            reader2  = new BufferedReader(new FileReader(str4));
	            outputStream2 = new PrintWriter(new FileWriter(str5));

	            String file;
	            while ((file = reader2 .readLine()) != null) {
	                rows2.add(file);
	            }
	            Collections.sort(rows2);
	            String[] strArr= rows2.toArray(new String[0]);
	            for (String cur : strArr)
	                outputStream2.println(cur);
	        }        
	        	 finally {
	            //if (reader  != null) {
	                reader2.close();
	           // }
	           // if (outputStream != null) {
	                outputStream2.close();
	            }
	        StdOut.println("CS.txt is created");
	        
	        
	        
	        BufferedReader  reader4 = null;
	        PrintWriter outputStream4 = null;
	        ArrayList<String> rows4 = new ArrayList<String>();
	        
	        String str8 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\D.txt";
	        String str9 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\DS.txt";

	        try {
	            reader4  = new BufferedReader(new FileReader(str8));
	            outputStream4 = new PrintWriter(new FileWriter(str9));

	            String file;
	            while ((file = reader4 .readLine()) != null) {
	                rows4.add(file);
	            }
	            Collections.sort(rows4);
	            String[] strArr= rows4.toArray(new String[0]);
	            for (String cur : strArr)
	                outputStream4.println(cur);
	        }        
	        	 finally {
	            //if (reader  != null) {
	                reader4.close();
	           // }
	           // if (outputStream != null) {
	                outputStream4.close();
	            }
	        
	        StdOut.println("DS.txt is created");
	        
	        System.out.println("Merging the 4 files into one file with sorted data");
	        String[] fnames = {"C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\AS.txt","C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\BS.txt","C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\CS.txt","C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\DS.txt"};		
	   	    int N = fnames.length; 
	        In[] streams = new In[N]; 
	        for (int i = 0; i < N; i++) 
	        streams[i] = new In(fnames[i]); 
	        merge(streams); 
	        System.out.println("Merging Process has been completed.");
	        System.out.println("Chip-seq-reads-1M-sorted.txt is created");
	        System.out.println();
	        end=System.nanoTime();
	        System.out.println("Time taken to complete the memory management operation in nanoseconds is:" + (end-start));

	        
	    	 System.out.println("Question 6");
	    	start=System.nanoTime();
	        BTree<String, String> st = new BTree<String, String>();
	        
	        In in9;
	        System.out.println("BTree");
	        try {
	            in9 = new In("C:\\Study_Mac\\Advanced_Computing\\Assignment3\\Graphs\\Graphs\\graphs\\RNA-seq-reads-1M.txt");
	            while (!in9.isEmpty()) {
	                String s1 = in9.readLine();
	                String counter = "0";
	                counter = counter+1;
	                st.put(s1,counter);
	                //System.out.println(s);
	            }
	            end=System.nanoTime();
	            StdOut.println("Time taken to insert the reads into Btree in nanoseconds: " + (end-start));
	            StdOut.println("size:    " + st.size());
	            StdOut.println("height:  " + st.height());
	            start=System.nanoTime();
	            StdOut.println(st);
	            PrintStream printStreamToFile = new PrintStream(new File("C:\\Study_Mac\\Advanced_Computing\\Assignment3\\Graphs\\Graphs\\graphs\\Btree.txt"));
	            System.setOut(printStreamToFile);
	            printStreamToFile.println(st);
	            end=System.nanoTime();
	            StdOut.println("Time taken for inorder traversal of Btree in nanoseconds: " + (end-start));
	        }
	        
	        catch (Exception e) { 
	        	//System.out.println(e);
	        	}
	    }	
}
