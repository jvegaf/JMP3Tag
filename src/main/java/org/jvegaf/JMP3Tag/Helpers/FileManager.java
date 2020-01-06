package org.jvegaf.JMP3Tag.Helpers;

import java.io.File;
import java.io.FilenameFilter;
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
		files = new ArrayList<File>(Arrays.asList(folder.listFiles(new FilenameFilter() {
      
      @Override
      public boolean accept(File dir, String name) {
        
        return name.toLowerCase().endsWith(".mp3");
      
      }
    }))); 		
	}
	
	public ArrayList<File> getFiles(){
		return this.files;
	}
}
