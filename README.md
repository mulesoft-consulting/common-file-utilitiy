# common-file-utilitiy
# Introduction
This is a Java service to split large file into  multiple part/smaller files. In the future, the code will be enhanced to rejoin the splitted files to recreate the original file.

# Use case
When uploading large files (50MB or  more) to Amazon S3. It is recommended to use multi-part upload when one will upload smaller chunk of files to S3, instead of one large file in one go. This will help to reduce the memory footprint of the Mule application.

# How to use the code?
1. Import the Mule project to Anypoint Studio
2. Refer to the common-split-large-file-main-flow to see how one can call the splitFile method in the SplitLargeFile class

    # Input to the splitFile method:
    	String filePath  <-- the absolute path to the 'large' file to be splitted
    
    	int fileSplitSize <-- the size of individual smaller files in MB

    # Output of the splitFile method:
    	If no file is found, return  -1
	
	If file size is less than the fileSplitSize, return 0
	
	If the file is successfully splitted, return number of smaller files it produced 

# To test the code:
Create some test files and change the below line in the main method accordingly.

	String FilePath = "/Users/chiew.lee/test-files/large-test-file-923MB.json";

# Note
When running on CloudHub, leverage the /tmp folder. 

In all cases, remember to delete the files accordingly to preserve disk space.
