package org.jvegaf.JMP3Tag.UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(buttonsPanel, BorderLayout.NORTH);
		buttonsPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton btnOpenFolder = new JButton("Open Folder");
		btnOpenFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Select a Mp3 Folder");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				switch (fc.showOpenDialog(null)) {
				case JFileChooser.APPROVE_OPTION:
					File selectedFolder = new File(fc.getSelectedFile().toString());
					System.out.println("directory ? " +selectedFolder.isDirectory());
					System.out.println("route: " + selectedFolder.getAbsolutePath());
					break;
				default:
					System.out.println("no selection");
					break;
				}
			}
		});
		btnOpenFolder.setIcon(new ImageIcon("C:\\Users\\josev\\Google Drive\\Desarrollo\\Java\\JMp3Tag\\icons\\open.png"));
		btnOpenFolder.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOpenFolder.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttonsPanel.add(btnOpenFolder);
		
		JButton btnOpenFile = new JButton("Open File");
		buttonsPanel.add(btnOpenFile);
		
		JButton btnSaveAll = new JButton("Save All");
		btnSaveAll.setIcon(new ImageIcon("C:\\Users\\josev\\Google Drive\\Desarrollo\\Java\\eclipse-workspace\\JMP3Tag\\icons\\save64.png"));
		btnSaveAll.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSaveAll.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttonsPanel.add(btnSaveAll);
		
		JTable mainTable = new JTable();
		mainTable.setModel(new TracksTableModel(this.repo));
		
		JScrollPane scrollPane = new JScrollPane(mainTable);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
	}
}
