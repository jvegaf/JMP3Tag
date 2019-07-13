package org.jvegaf.JMP3Tag.Controllers;

import java.io.File;
import java.util.ArrayList;

import org.jvegaf.JMP3Tag.Helpers.FileManager;
import org.jvegaf.JMP3Tag.Model.Track;
import org.jvegaf.JMP3Tag.Model.TracksRepository;
import org.jvegaf.JMP3Tag.UI.MainFrameView;

public class MainFrameController {

	private ArrayList<File> files;
	private TracksRepository tr;
	
	public MainFrameController() {
		tr = new TracksRepository();
	}
	
	public void showView() {
		MainFrameView frame = new MainFrameView(this, tr);
		frame.setVisible(true);			
	}
	
	public void manageFolder(File folder) {
		FileManager fm = new FileManager(folder);
		this.files = fm.getFiles();
		for (File file : files) {
			tr.addTrack(file);
		}
		
	}

	public void manageFile(File selectedFile) {
		tr.addTrack(new Track(selectedFile.getAbsolutePath(), selectedFile.getName()));
	}
	
	
	
}
