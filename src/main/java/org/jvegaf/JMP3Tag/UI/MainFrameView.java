package org.jvegaf.JMP3Tag.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;

import org.jvegaf.JMP3Tag.Controllers.MainFrameController;
import org.jvegaf.JMP3Tag.Helpers.BetterJTable;
import org.jvegaf.JMP3Tag.Model.TracksRepository;
import org.jvegaf.JMP3Tag.Model.TracksTableModel;

public class MainFrameView extends JFrame {

  private static final int MIN_HEIGHT = 800;
  private static final int MIN_WIDTH = 1200;
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
    setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
    /* for show in full screen */
    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());
    setSize(xSize, ySize);

    if (System.getProperty("os.name").contains("Mac")) {
      System.setProperty("apple.laf.useScreenMenuBar", "true");
      // set the name of the application menu item
      System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Music Manager");
    }

    JMenuBar menuBar = getMainMenu();

    setJMenuBar(menuBar);

    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
    setContentPane(contentPane);
    contentPane.setLayout(new BorderLayout(0, 0));

    JSplitPane splitPane = new JSplitPane();
    splitPane.setDividerLocation(200);
    splitPane.setDividerSize(0);
    contentPane.add(splitPane, BorderLayout.CENTER);

    JPanel playlistPanel = new JPanel();
    playlistPanel.setMinimumSize(new Dimension(200, splitPane.getSize().height));
    splitPane.setLeftComponent(playlistPanel);

    mainTable = new BetterJTable(ttm);
    mainTable.setRowHeight(22);

    JScrollPane scrollPane = BetterJTable.createStripedJScrollPane(mainTable);

    splitPane.setRightComponent(scrollPane);

    JPanel toolbarPanel = new JPanel();
    toolbarPanel.setBorder(new EmptyBorder(0, 0, 80, 0));
    toolbarPanel.setMinimumSize(new Dimension(MIN_WIDTH, 80));
    contentPane.add(toolbarPanel, BorderLayout.NORTH);

  }

  private JMenuBar getMainMenu() {
    JMenuBar menuBar = new JMenuBar();

    JMenu mnFile = new JMenu("File");
    menuBar.add(mnFile);

    JMenuItem mntmOpenFile = new JMenuItem("Open File");
    mnFile.add(mntmOpenFile);
    mntmOpenFile.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        openFile();
      }
    });

    JMenuItem mntmOpenFolder = new JMenuItem("Open Folder");
    mnFile.add(mntmOpenFolder);
    mntmOpenFolder.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        openFolder();
      }
    });

    JMenuItem mntmSave = new JMenuItem("Save");
    mnFile.add(mntmSave);
    // TODO: make save method

    JMenuItem mntmExit = new JMenuItem("Exit");
    mntmExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    mnFile.add(mntmExit);
    return menuBar;
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

  private void openFolder() {
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

  private void openFile() {
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
}
