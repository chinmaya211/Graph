package graphs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class File_Partition {

	 public static void splitFile(File f) throws IOException {
	      //  int partCounter = 1;//I like to name parts from 001, 002, 003, ...
	                            //you can change it to 0 if you want 000, 001, ...

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

	    public static void main(String[] args) throws IOException {
	        splitFile(new File("C:\\Study_Mac\\Advanced_Computing\\Assignment3\\Graphs\\Graphs\\graphs\\RNA-seq-reads-1M.txt"));
	       	        
	    }
	    
	
	}
