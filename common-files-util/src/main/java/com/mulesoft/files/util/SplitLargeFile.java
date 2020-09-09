package com.mulesoft.files.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SplitLargeFile {

	public SplitLargeFile() {

	}

	public static void main(String args[]) {
		
		//String FilePath = "/Users/chiew.lee/test-files/small-file.json";
		String FilePath = "/Users/chiew.lee/test-files/large-test-file-923MB.json";
		try {
			//split the large file into multiple 10MB smaller files
			System.out.println(splitFile(FilePath,10));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}

	public static int splitFile(String filePath, int fileSplitSize) throws Exception {

		/*
		 * If no file is found, return  -1
		 * If file size is less than the fileSplitSize, return 0
		 * If the file is successfully splitted, return number of smaller files it produced 
		*/
		int noOfSmallerFiles = 0;
		File filename = new File(filePath);
		long splitFileSize = 0, bytefileSize = 0;
		try {
			if (filename.exists()) {

				bytefileSize = fileSplitSize;

				splitFileSize = bytefileSize * 1024 * 1024;

				noOfSmallerFiles = split(filePath, (long) splitFileSize);

			} else {
				System.out.println("File Not Found - "+filePath);
				noOfSmallerFiles = -1;
			}
		} finally {
			filename = null;
			filePath = null;
			
		}

		if (noOfSmallerFiles > 1) {
			System.out.println("File is splitted successfully into "+noOfSmallerFiles+" smaller files.");
		}

		return noOfSmallerFiles;

	}

	public static int split(String FilePath, long splitLen) throws IOException {
		long length = 0;
		int count = 1, data;

		File fileName = new File(FilePath);
		long fileLength = fileName.length();
		if(fileLength > splitLen) {
			InputStream inFile = new BufferedInputStream(new FileInputStream(fileName));
			
			data = inFile.read();
			
			while (data != -1) {
				fileName = new File(FilePath + "." + String.format("%03d", count));

				OutputStream outFile = new BufferedOutputStream(new FileOutputStream(fileName));
				while (data != -1 && length < splitLen) {
					outFile.write(data);
					length++;
					data = inFile.read();
				}

				length = 0;
				outFile.close();
				count++;
			}
			inFile.close();
		}else {
			
			System.out.println("File size -  "+fileLength+" is < "+splitLen);
		}
		return count-1;
	}

}
