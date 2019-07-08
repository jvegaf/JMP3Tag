package org.jvegaf.JMP3Tag.Main;

import java.awt.EventQueue;

import org.jvegaf.JMP3Tag.UI.MainFrame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //printTags(new File("items/Fouk.mp3"));
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
    
//    private static void printTags(File testFile){
//        try {
//            MP3File f = (MP3File)AudioFileIO.read(testFile);
//            Tag tag = f.getID3v2Tag();
//            System.out.println();
//            System.out.println(tag.getFirst(FieldKey.TITLE));
//            System.out.println(tag.getFirst(FieldKey.ARTIST));
//
//        } catch (CannotReadException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TagException e) {
//            e.printStackTrace();
//        } catch (ReadOnlyFileException e) {
//            e.printStackTrace();
//        } catch (InvalidAudioFrameException e) {
//            e.printStackTrace();
//        }
//    }
}
