package org.jvegaf.JMP3Tag.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jvegaf.JMP3Tag.Helpers.TagsManager;

public class TracksRepository {

	private List<Track> collection;
	private String[] properties = { "Title", "Artist", "Album", "Genre", "Year", "BPM", "Cover", "File Name"};
	private TagsManager tm;
	
	/**
	 * getters
	 */
	
	public List<Track> getCollection() {
		return collection;
	}

	public String[] getProperties() {
		return properties;
	}

	/**
	 * Constructor
	 */
	public TracksRepository() {
		this.collection = new ArrayList<Track>();
	}
	
	/**
	 * methods
	 */
	
	public void addTrack(Track t) {
		this.collection.add(t);
	}
	
	public void addTrack(File file) {
		tm = new TagsManager(file);
		Track t = tm.getTrackWithTags();
		this.collection.add(t);
		tm = null;
	}
	
	public void removeTrack(Track t){
		for (int i = 0; i < collection.size(); i++) {
			if (this.collection.get(i).equals(t)) {
				this.collection.remove(i);
				break;
			}
		}
	}
	
	public void updateTrack(Track t, Track nt) {
		for (int i = 0; i < collection.size(); i++) {
			if(collection.get(i).equals(t)) {
				collection.remove(i);
				collection.add(i, nt);
				break;
			}
		}
	}
}
