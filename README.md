# XMLScraper

Author: Georgios Kokkinos
Date started: 31/03/2025
Date finished MVP: 02/04/2025

## The problem

At work, we have hundreds of XML scripts used to control an instrument. In order to understand how that instrument works inside out, in detail, I should go through every XML and understand its functions. The good thing, is that these XMLs are well commented, so I could just read the comments and more rarely read the code. But it is very time consuming because I need to remember which XMLs I have already read and also make notes in excel. 

## The task 
I would like to build a tool which locates every xml in a directory and collects some details such as title and date and then collates the comments of that xml and outputs a csv file with all information in the right order

There is an example script in the folder for examination. 

## Implemented solution
This Java application processes XML files containing comments, extracts specific parts (the file name, general description, and step-by-step instructions), and exports the collected data into a CSV file using a custom delimiter.

The project consists of four main classes:

### FileLocator

#### Purpose:

Recursively scans a specified directory (and its subdirectories) to locate all files of a given extension (e.g., .xml).

#### Key Methods:
1) getAllFilePaths(): Returns an ArrayList of all files in the directory tree.
2) getSpecificFilePaths(): Filters the above list to return only files with the specified extension.
3) printFilePaths() and printFileCount(): Utility methods to display file details.

### FileHandler

#### Purpose:
1) Processes each XML file to extract its comments.
2) Reads the entire file, inserts the file name as the first comment, and then identifies all XML comments.

#### Key Methods:
1) getXMLtoString(File file): Reads an XML file and converts its content to a String.
2) identifyAllComments(String xmlString): Extracts all XML comments from the file content.
3) getAllCommentsFromFile(File file): Returns an ArrayList of comments from a single file.
4) getAllCommentsFromAllFiles(): Processes all files and returns an ArrayList of ArrayLists, where each inner list represents the comments from one file.
5) Utility methods to print file contents or comments.

### CSVExporter

#### Purpose:

Formats the extracted data into rows and writes it to a CSV file using a custom delimiter (in this case, the dollar sign $).

#### Key Methods:
1) exportToCSV(ArrayList<ArrayList<String>> data): Writes the header row and then each row of data to the output CSV file.
2) Helper methods (e.g., getScriptName(), getGeneralDescription(), getAllSteps()) that extract and clean the desired fields from the comments.

### Main

#### Purpose:
1) Serves as the entry point of the application.
2) Orchestrates the overall process by:
3) Locating XML files using FileLocator.
4) Extracting comments from each XML file using FileHandler.
5) Exporting the collected data to a CSV file using CSVExporter.
