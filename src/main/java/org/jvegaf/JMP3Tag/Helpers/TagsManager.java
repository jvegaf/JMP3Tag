package org.jvegaf.JMP3Tag.Helpers;

import java.io.File;
import java.io.IOException;
import java.time.Year;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v24Frames;
import org.jvegaf.JMP3Tag.Model.Track;

public class TagsManager {

	private MP3File f;
	private AbstractID3v2Tag tag;
	private File file;
	
	public TagsManager(File file) {
		this.file = file;
		try {
			f = (MP3File)AudioFileIO.read(file);
			tag = f.getID3v2Tag();
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			e.printStackTrace();
		}
	}
	
	private String getTitle() {
		return tag.getFirst(ID3v24Frames.FRAME_ID_TITLE);
	}
	
	private String getArtist() {
		return tag.getFirst(ID3v24Frames.FRAME_ID_ARTIST);
	}
	
	private String getAlbum() {
		return tag.getFirst(ID3v24Frames.FRAME_ID_ALBUM);
	}
	
	private String getGenre() {
		return tag.getFirst(ID3v24Frames.FRAME_ID_GENRE);
	}
	
	private Year getYear() {
		String _year = tag.getFirst(ID3v24Frames.FRAME_ID_YEAR);
		if (_year != null && _year != "" && _year.length() == 4) {
			return Year.parse((CharSequence)_year);			
		}
		return null;
	}
	
	private int getBPM() {
		String _bpm = tag.getFirst(ID3v24Frames.FRAME_ID_BPM);
		if (_bpm != null && _bpm != "") {
			return Integer.parseInt(_bpm);			
		}
		return 0;
	}
	

	public void printTags(Track t) {
		System.out.println();
		System.out.println(f.toString());
		
		
		if (f.hasID3v2Tag()) { 
			System.out.println("title: " + tag.getFirst(ID3v24Frames.FRAME_ID_TITLE));
			System.out.println("artist: " + tag.getFirst(ID3v24Frames.FRAME_ID_ARTIST));
			System.out.println("bpm: " + tag.getFirst(ID3v24Frames.FRAME_ID_BPM));
		}
	}

	public Track getTrackWithTags() {
		Track t;
		if (f.hasID3v2Tag()) {
			t = new Track(this.file.getAbsolutePath(), 
					this.getTitle(), 
					this.getArtist(), 
					this.getAlbum(), 
					this.getGenre(), 
					this.getYear(), 
					this.getBPM(), 
					null, 
					this.file.getName());
		} else {
			t = new Track(this.file.getAbsolutePath(), this.file.getName());
		}
		return t;			
	}
}
