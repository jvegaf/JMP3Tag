package org.jvegaf.JMP3Tag.UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import org.jvegaf.JMP3Tag.Controllers.MainFrameController;
import org.jvegaf.JMP3Tag.Model.TracksRepository;
import org.jvegaf.JMP3Tag.Model.TracksTableModel;

public class MainFrameView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFrameController mfc;
	private JPanel contentPane;
	private TracksRepository repo;
	private TracksTableModel ttm;
	
	/**
	 * Create the frame.
	 * @param mainFrameController 
	 * @param tr 
	 */
	public MainFrameView(MainFrameController mainFrameController, TracksRepository tr) {
		this.mfc = mainFrameController;
		this.repo = tr;
		this.ttm = new TracksTableModel(repo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/* for show in full screen */
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		setSize(xSize,ySize);
		//setBounds(100, 100, 800, 600);
		
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
					mfc.manageFolder(fc.getSelectedFile());
					ttm.fireTableDataChanged();
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
		btnOpenFile.setIcon(new ImageIcon("C:\\Users\\josev\\Google Drive\\Desarrollo\\Java\\JMp3Tag\\icons\\open.png"));
		btnOpenFile.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOpenFile.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttonsPanel.add(btnOpenFile);
		btnOpenFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Select a Mp3 File");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				//TODO: filter for only accept mp3 files
				switch (fc.showOpenDialog(null)) {
				case JFileChooser.APPROVE_OPTION:
					mfc.manageFile(fc.getSelectedFile());
					ttm.fireTableDataChanged();
					break;
				default:
					System.out.println("no selection");
					break;
				}
			}
		});
		
		JButton btnSaveAll = new JButton("Save All");
		btnSaveAll.setIcon(new ImageIcon("C:\\Users\\josev\\Google Drive\\Desarrollo\\Java\\eclipse-workspace\\JMP3Tag\\icons\\save32.png"));
		btnSaveAll.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSaveAll.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttonsPanel.add(btnSaveAll);
		
		JButton btnClearList = new JButton("Clear List");
		btnClearList.setIcon(new ImageIcon("C:\\Users\\josev\\Google Drive\\Desarrollo\\Java\\eclipse-workspace\\JMP3Tag\\icons\\clear.png"));
		btnClearList.setHorizontalTextPosition(SwingConstants.CENTER);
		btnClearList.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttonsPanel.add(btnClearList);
		
		JTable mainTable = new JTable();
		mainTable.setModel(ttm);
		
		// set Font To table
        mainTable.setFont(new Font("", 0, 14));
        
        // set height to the table rows
        mainTable.setRowHeight(20);
        
        mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mainTable.getColumnModel().getColumn(0).setPreferredWidth(325);
        mainTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        mainTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        mainTable.getColumnModel().getColumn(3).setPreferredWidth(60);
        mainTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        mainTable.getColumnModel().getColumn(5).setPreferredWidth(40);
        mainTable.getColumnModel().getColumn(6).setPreferredWidth(40);
        mainTable.getColumnModel().getColumn(7).setPreferredWidth(320);
        //ttm.fireTableStructureChanged();
		
		JScrollPane scrollPane = new JScrollPane(mainTable);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
	}
}
