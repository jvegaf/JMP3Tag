package org.jvegaf.JMP3Tag.Helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


/**
 *	In the future here will be change filenames with the tag properties
 */

public class FileManager {
	
	private File folder;
	private ArrayList<File> files;
	
	public FileManager(File path) {
		this.folder = path;
		File[] filesArray = folder.listFiles();
		files = new ArrayList<File>(Arrays.asList(filesArray)); 		
	}
	
	public ArrayList<File> getFiles(){
		return this.files;
	}
}
