package graphs;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
public class File_Sorting {
	public static void main(String[] args) throws IOException {
        BufferedReader  reader = null;
        PrintWriter outputStream = null;
        ArrayList<String> rows = new ArrayList<String>();
        
        String str = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\B.txt";
        String str1 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Graphs\\\\Graphs\\\\graphs\\\\BS.txt";

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
      
       // }
        
/*
        String str3 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Memory Management\\\\Memory Management\\\\memoryManagement\\\\A.txt";
        String str4 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Memory Management\\\\Memory Management\\\\memoryManagement\\\\AS.txt";

        try {
        	@SuppressWarnings("resource")
			BufferedReader reader1  = new BufferedReader(new FileReader(str3));
        	@SuppressWarnings("resource")
			PrintWriter outputStream1 = new PrintWriter(new FileWriter(str4));

            String file1;
            while ((file1 = reader1 .readLine()) != null) {
                rows.add(file1);
            }
            Collections.sort(rows);
            String[] strArr= rows.toArray(new String[0]);
            for (String cur : strArr)
                outputStream1.println(cur);
        }        
         finally {
            if (reader  != null) {
                reader.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
      
        }       
        
        String str5 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Memory Management\\\\Memory Management\\\\memoryManagement\\\\B.txt";
        String str6 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Memory Management\\\\Memory Management\\\\memoryManagement\\\\BS.txt";

        try {
            	reader  = null;
            	outputStream = null;
            reader  = new BufferedReader(new FileReader(str5));
            outputStream = new PrintWriter(new FileWriter(str6));

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
            if (reader  != null) {
                reader.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
      
        }           
        
        String str7 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Memory Management\\\\Memory Management\\\\memoryManagement\\\\C.txt";
        String str8 = "C:\\\\Study_Mac\\\\Advanced_Computing\\\\Assignment3\\\\Memory Management\\\\Memory Management\\\\memoryManagement\\\\CS.txt";

        try {
        	    reader  = null;
            	outputStream = null;
            reader  = new BufferedReader(new FileReader(str7));
            outputStream = new PrintWriter(new FileWriter(str8));

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
            if (reader  != null) {
                reader.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
      
        }              
        
    */    
	}
}
