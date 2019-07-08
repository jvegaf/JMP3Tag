package org.jvegaf.JMP3Tag.Model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TracksTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	List<Track> collection;
	String[] columnNames;
	

	public TracksTableModel(TracksRepository tRep) {
		super();
		this.collection = tRep.getCollection();
		this.columnNames = tRep.getProperties();
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		return this.collection.size();
	}

	@Override
	public Object getValueAt(int column, int row) {
		Track t = collection.get(row);
		switch (column) {
		case 0:
			return t.getTitle();
		case 1:
			return t.getArtist();
		case 2:
			return t.getAlbum();
		case 3:
			return t.getGenre();
		case 4:
			return t.getYear().toString();
		case 5:
			return String.valueOf(t.getBpm());
		case 6:
			return "not yet";
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public boolean isCellEditable(int column, int row) {
		return false;
	}
	
	

}
