package org.jvegaf.JMP3Tag.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;

import org.jvegaf.JMP3Tag.Controllers.MainFrameController;
import org.jvegaf.JMP3Tag.Helpers.BetterJTable;
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
  private JTable mainTable;

  /**
   * Create the frame.
   * 
   * @param mainFrameController
   * @param tr
   */
  public MainFrameView(MainFrameController mainFrameController, TracksRepository tr) {
    this.mfc = mainFrameController;
    this.repo = tr;
    this.ttm = new TracksTableModel(repo);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(1200, 800));
    /* for show in full screen */
    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());
    setSize(xSize, ySize);

    if (System.getProperty("os.name").contains("Mac")) {
      System.setProperty("apple.laf.useScreenMenuBar", "true");
    }

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
    buttonsPanel.setBorder(new EmptyBorder(0, 0, 70, 0));
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
          tweakColumns(mainTable);
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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 Files", "mp3");
        fc.setFileFilter(filter);
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
//    btnSaveAll.setIcon(new ImageIcon(
//        "C:\\Users\\josev\\Google Drive\\Desarrollo\\Java\\eclipse-workspace\\JMP3Tag\\icons\\save32.png"));
    btnSaveAll.setHorizontalTextPosition(SwingConstants.CENTER);
    btnSaveAll.setVerticalTextPosition(SwingConstants.BOTTOM);
    buttonsPanel.add(btnSaveAll);

    JButton btnClearList = new JButton("Clear List");
//    btnClearList.setIcon(new ImageIcon(
//        "C:\\Users\\josev\\Google Drive\\Desarrollo\\Java\\eclipse-workspace\\JMP3Tag\\icons\\clear.png"));
    btnClearList.setHorizontalTextPosition(SwingConstants.CENTER);
    btnClearList.setVerticalTextPosition(SwingConstants.BOTTOM);
    buttonsPanel.add(btnClearList);

    JSplitPane splitPane = new JSplitPane();
    splitPane.setDividerLocation(200);
    splitPane.setDividerSize(0);
    contentPane.add(splitPane, BorderLayout.CENTER);

    JPanel playlistPanel = new JPanel();
    playlistPanel.setMinimumSize(new Dimension(200, splitPane.getSize().height));
    splitPane.setLeftComponent(playlistPanel);

    mainTable = new BetterJTable(ttm);

    // set height to the table rows
//    mainTable.setRowHeight(20);
//
//    mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//    mainTable.getColumnModel().getColumn(0).setPreferredWidth(325);
//    mainTable.getColumnModel().getColumn(1).setPreferredWidth(300);
//    mainTable.getColumnModel().getColumn(2).setPreferredWidth(200);
//    mainTable.getColumnModel().getColumn(3).setPreferredWidth(60);
//    mainTable.getColumnModel().getColumn(4).setPreferredWidth(60);
//    mainTable.getColumnModel().getColumn(5).setPreferredWidth(40);
//    mainTable.getColumnModel().getColumn(6).setPreferredWidth(40);
//    mainTable.getColumnModel().getColumn(7).setPreferredWidth(320);
    // ttm.fireTableStructureChanged();

    // JScrollPane scrollPane = new JScrollPane(mainTable);
    JScrollPane scrollPane = BetterJTable.createStripedJScrollPane(mainTable);

    splitPane.setRightComponent(scrollPane);

  }

  private void tweakColumns(JTable table) {
    Enumeration<TableColumn> columns = table.getColumnModel().getColumns();

    int required = 0;
    while (columns.hasMoreElements()) {
      TableColumn column = columns.nextElement();
      int width = (int) table.getTableHeader().getDefaultRenderer()
          .getTableCellRendererComponent(table, column.getIdentifier(), false, false, -1, column.getModelIndex())
          .getPreferredSize().getWidth();
      required += width;
    }

    JViewport viewport = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, table);
    int viewportWidth = viewport.getWidth();
    table.setAutoResizeMode(required < viewportWidth ? JTable.AUTO_RESIZE_ALL_COLUMNS : JTable.AUTO_RESIZE_OFF);
  }
}
