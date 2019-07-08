package org.jvegaf.JMP3Tag.Model;

import java.time.Year;

import javax.swing.ImageIcon;

public class Track {

	private String title;
	private String artist;
	private String album;
	private String genre;
	private Year year;
	private int bpm;
	private ImageIcon cover;
	
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
	
	public Track(){}
	
	public Track(String title, String artist, String album, String genre, Year year, int bpm, ImageIcon cover) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
		this.year = year;
		this.bpm = bpm;
		this.cover = cover;
	}
	
	@Override
	public String toString() {
		return "Track [" + (title != null ? "title=" + title + ", " : "")
				+ (artist != null ? "artist=" + artist + ", " : "") + (album != null ? "album=" + album + ", " : "")
				+ (genre != null ? "genre=" + genre + ", " : "") + (year != null ? "year=" + year + ", " : "") + "bpm="
				+ bpm + "]";
	}
	
	
	
}
