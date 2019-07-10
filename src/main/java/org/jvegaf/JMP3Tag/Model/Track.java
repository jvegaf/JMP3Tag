package org.jvegaf.JMP3Tag.Model;

import java.time.Year;

import javax.swing.ImageIcon;

public class Track {

	private String path;
	private String title;
	private String artist;
	private String album;
	private String genre;
	private Year year;
	private int bpm;
	private ImageIcon cover;
	private String fileName;
	
	
	public String getPath() {
		return this.path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	public int getBpm() {
		return bpm;
	}
	public void setBpm(int bpm) {
		this.bpm = bpm;
	}
	public ImageIcon getCover() {
		return cover;
	}
	public void setCover(ImageIcon cover) {
		this.cover = cover;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String fn) {
		this.fileName = fn;
	}
	
	public Track(){}
	
	public Track(String path, String filename) {
		this.path = path;
		this.fileName = filename;
	}
	
	public Track(String path, String title, String artist, String album, String genre, Year year, int bpm, ImageIcon cover, String filename) {
		super();
		this.path = path;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
		this.year = year;
		this.bpm = bpm;
		this.cover = cover;
		this.fileName = filename;
	}

	@Override
	public String toString() {
		return "Track [path=" + path + ", title=" + title + ", artist=" + artist + ", album=" + album + ", genre="
				+ genre + ", year=" + year + ", bpm=" + bpm + ", cover=" + cover + ", fileName=" + fileName + "]";
	}
	
	
	
	
}
