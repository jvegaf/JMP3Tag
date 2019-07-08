package org.jvegaf.JMP3Tag.UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jvegaf.JMP3Tag.Model.TracksRepository;
import org.jvegaf.JMP3Tag.Model.TracksTableModel;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TracksRepository repo;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		repo = new TracksRepository();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenFile = new JMenuItem("Open File");
		mnFile.add(mntmOpenFile);
		
		JMenuItem mntmOpenFolder = new JMenuItem("Open Folder");
		mnFile.add(mntmOpenFolder);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(buttonsPanel, BorderLayout.NORTH);
		buttonsPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton btnOpenFolder = new JButton("Open Folder");
		btnOpenFolder.setIcon(new ImageIcon("C:\\Users\\josev\\Google Drive\\Desarrollo\\Java\\JMp3Tag\\icons\\open.png"));
		btnOpenFolder.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOpenFolder.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttonsPanel.add(btnOpenFolder);
		
		JButton btnOpenFile = new JButton("Open File");
		buttonsPanel.add(btnOpenFile);
		
		JButton btnSaveAll = new JButton("Save All");
		buttonsPanel.add(btnSaveAll);
		
		JTable mainTable = new JTable();
		mainTable.setModel(new TracksTableModel(this.repo));
		
		JScrollPane scrollPane = new JScrollPane(mainTable);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
	}
}
