package ctester.gui;

import ctester.gui.components.TestItemUI;
import constants.Constants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author dma@logossmartcard.com
 */
public class MainUI extends javax.swing.JFrame 
{    
    /**
     * Creates new form MainUI
     */
    public MainUI() 
    {
        initComponents();        
        setupToolbar();
        setupTestsListLayout();
        setupOutputLayout();
        setupCommandsToolbar();
    }

    /**
     * Sets up the toolbar buttons.
     */
    private void setupToolbar() 
    {
        Image img;
        try 
        {
            img = ImageIO.read(this.getClass().getResource(Constants.RESOURCES_PATH + Constants.NEW_TEST_ICON));
            img = img.getScaledInstance(Constants.ICON_WIDTH, Constants.ICON_HEIGHT, java.awt.Image.SCALE_SMOOTH);
            mAddButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(this.getClass().getResource(Constants.RESOURCES_PATH + Constants.COMPILE_ICON));
            img = img.getScaledInstance(Constants.ICON_WIDTH, Constants.ICON_HEIGHT, java.awt.Image.SCALE_SMOOTH);
            mCompileButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(this.getClass().getResource(Constants.RESOURCES_PATH + Constants.RUN_ICON));
            img = img.getScaledInstance(Constants.ICON_WIDTH, Constants.ICON_HEIGHT, java.awt.Image.SCALE_SMOOTH);
            mRunButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(this.getClass().getResource(Constants.RESOURCES_PATH + Constants.REMOVE_TEST_ICON));
            img = img.getScaledInstance(Constants.ICON_WIDTH, Constants.ICON_HEIGHT, java.awt.Image.SCALE_SMOOTH);
            mRemoveButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(this.getClass().getResource(Constants.RESOURCES_PATH + Constants.SETTINGS_ICON));
            img = img.getScaledInstance(Constants.ICON_WIDTH, Constants.ICON_HEIGHT, java.awt.Image.SCALE_SMOOTH);
            mSettingsButton.setIcon(new ImageIcon(img));

        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Sets up the container panel to list the tests.
     */
    private void setupTestsListLayout()
    {
        // Make the ScrollPane transparent.
        mTestsScrollPane.setOpaque(false);
        mTestsScrollPane.getViewport().setOpaque(false);
        // Set the correct layout for the tests.
        mTestsListPanel.setLayout(new BoxLayout(mTestsListPanel, BoxLayout.Y_AXIS));
        
        mSplitPane.setUI(new BasicSplitPaneUI() 
        {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() 
            {
                return new BasicSplitPaneDivider(this) 
                {                
                    public void setBorder(Border b) {}

                    @Override
                    public void paint(Graphics g) 
                    {
                        super.paint(g);
                        
                        g.setColor(new Color(51, 51, 51));
                        g.fillRect(0, 0, getSize().width, getSize().height);
                    }
                };
            }
        });
        
        mSplitPane.setBorder(null);
        
        // TODO remove this
        for (int i = 0; i < 13; ++i)
        {
            mTestsListPanel.add(new TestItemUI());            
        }
    }
    
    /**
     * Sets up the output panel.
     */
    private void setupOutputLayout()
    {
        // Make the ScrollPane transparent.
        mOutputScrollPane.setOpaque(false);
        mOutputScrollPane.getViewport().setOpaque(false);
        
        mOutputArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        // TODO remove this
        mOutputArea.setText("ResetCard\n\nI: 80 20 11 00 00\nO: 90 00\n\n// Virginize\nI: 80 2E 00 00 0A 94 55 22 E4 F0 01 97 01 02 04\nO: 6B 02");
    }
    
    /**
     * Sets up the commands toolbar.
     */
    private void setupCommandsToolbar()
    {
        try 
        {
            mCommandEdittext.setBorder(BorderFactory.createCompoundBorder(
                    mCommandEdittext.getBorder()
                    , BorderFactory.createEmptyBorder(0, 8, 0, 8)));
                        
            Image img = ImageIO.read(this.getClass().getResource(Constants.RESOURCES_PATH + Constants.ROUND_GREEN_ICON));
            img = img.getScaledInstance(58, 58, java.awt.Image.SCALE_SMOOTH);
            mRoundCommands.setIcon(new ImageIcon(img));
                        
            img = ImageIO.read(this.getClass().getResource(Constants.RESOURCES_PATH + Constants.PLUS_ICON));
            img = img.getScaledInstance(42, 42, java.awt.Image.SCALE_SMOOTH);
            mRoundCommandsIcon.setIcon(new ImageIcon(img));
            
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mSplitPane = new javax.swing.JSplitPane();
        mRightPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        mOutputScrollPane = new javax.swing.JScrollPane();
        mOutputArea = new javax.swing.JTextArea();
        mResetButton = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        mSendButton = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        mCommandEdittext = new javax.swing.JTextField();
        mRightToolbarLowerPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        mRoundCommandsIcon = new javax.swing.JLabel();
        mRoundCommands = new javax.swing.JLabel();
        mLeftPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        mCompileButton = new javax.swing.JButton();
        mAddButton = new javax.swing.JButton();
        mRunButton = new javax.swing.JButton();
        mSettingsButton = new javax.swing.JButton();
        mRemoveButton = new javax.swing.JButton();
        mTestsScrollPane = new javax.swing.JScrollPane();
        mTestsListPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mSelectAllCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CTester");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Candara Light", 0, 12)); // NOI18N
        setLocation(new java.awt.Point(100, 100));
        setName("mainFrame"); // NOI18N

        mSplitPane.setBorder(null);
        mSplitPane.setDividerLocation(500);
        mSplitPane.setDividerSize(5);
        mSplitPane.setForeground(new java.awt.Color(60, 63, 65));
        mSplitPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        mRightPanel.setBackground(new java.awt.Color(58, 58, 79));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Output");

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        mOutputScrollPane.setBorder(null);
        mOutputScrollPane.setOpaque(false);

        mOutputArea.setEditable(false);
        mOutputArea.setColumns(20);
        mOutputArea.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 12)); // NOI18N
        mOutputArea.setForeground(new java.awt.Color(204, 204, 204));
        mOutputArea.setLineWrap(true);
        mOutputArea.setRows(5);
        mOutputArea.setOpaque(false);
        mOutputScrollPane.setViewportView(mOutputArea);

        mResetButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(94, 237, 181), 2, true));
        mResetButton.setToolTipText("Reset the card");
        mResetButton.setOpaque(false);
        mResetButton.setLayout(new java.awt.GridBagLayout());

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Reset");
        mResetButton.add(jLabel3, new java.awt.GridBagConstraints());

        jPanel4.setOpaque(false);

        mSendButton.setBackground(new java.awt.Color(94, 237, 181));
        mSendButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(94, 237, 181), 2, true));
        mSendButton.setToolTipText("Send command");

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Send");
        mSendButton.add(jLabel4);

        mCommandEdittext.setBackground(new java.awt.Color(30, 40, 57));
        mCommandEdittext.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 12)); // NOI18N
        mCommandEdittext.setForeground(new java.awt.Color(204, 204, 204));
        mCommandEdittext.setText("Command");
        mCommandEdittext.setToolTipText("Enter command");
        mCommandEdittext.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 40, 57), 2, true));
        mCommandEdittext.setCaretColor(new java.awt.Color(204, 204, 204));
        mCommandEdittext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCommandEdittextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mCommandEdittext, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mSendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mSendButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(mCommandEdittext, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        mRightToolbarLowerPanel.setOpaque(false);
        mRightToolbarLowerPanel.setPreferredSize(new java.awt.Dimension(140, 140));
        mRightToolbarLowerPanel.setLayout(new java.awt.GridBagLayout());

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(58, 58));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mRoundCommandsIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mRoundCommandsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/plus-black.png"))); // NOI18N
        mRoundCommandsIcon.setToolTipText("Show more commands");
        mRoundCommandsIcon.setAlignmentX(0.5F);
        mRoundCommandsIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mRoundCommandsIcon.setPreferredSize(new java.awt.Dimension(58, 58));
        jPanel3.add(mRoundCommandsIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        mRoundCommands.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mRoundCommands.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/round-green.png"))); // NOI18N
        mRoundCommands.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mRoundCommands.setPreferredSize(new java.awt.Dimension(58, 58));
        jPanel3.add(mRoundCommands, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        mRightToolbarLowerPanel.add(jPanel3, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout mRightPanelLayout = new javax.swing.GroupLayout(mRightPanel);
        mRightPanel.setLayout(mRightPanelLayout);
        mRightPanelLayout.setHorizontalGroup(
            mRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mRightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(mRightPanelLayout.createSequentialGroup()
                        .addComponent(mResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mRightPanelLayout.createSequentialGroup()
                        .addGroup(mRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(mOutputScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mRightToolbarLowerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        mRightPanelLayout.setVerticalGroup(
            mRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mRightPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(mRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mOutputScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addGroup(mRightPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(mRightToolbarLowerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        mSplitPane.setRightComponent(mRightPanel);

        mLeftPanel.setBackground(new java.awt.Color(30, 40, 57));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        mCompileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/code-download-white.png"))); // NOI18N
        mCompileButton.setToolTipText("Compile selected tests");
        mCompileButton.setBorderPainted(false);
        mCompileButton.setContentAreaFilled(false);
        mCompileButton.setFocusPainted(false);
        mCompileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        mAddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/file-add-white.png"))); // NOI18N
        mAddButton.setToolTipText("Add new tests");
        mAddButton.setBorderPainted(false);
        mAddButton.setContentAreaFilled(false);
        mAddButton.setFocusPainted(false);
        mAddButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        mRunButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/arrow-right-white.png"))); // NOI18N
        mRunButton.setToolTipText("Run selected tests");
        mRunButton.setBorderPainted(false);
        mRunButton.setContentAreaFilled(false);
        mRunButton.setFocusPainted(false);
        mRunButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        mSettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/settings-white.png"))); // NOI18N
        mSettingsButton.setToolTipText("Settings");
        mSettingsButton.setBorderPainted(false);
        mSettingsButton.setContentAreaFilled(false);
        mSettingsButton.setFocusPainted(false);
        mSettingsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        mRemoveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close-white.png"))); // NOI18N
        mRemoveButton.setToolTipText("Remove selected tests");
        mRemoveButton.setBorderPainted(false);
        mRemoveButton.setContentAreaFilled(false);
        mRemoveButton.setFocusPainted(false);
        mRemoveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mCompileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mRunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mSettingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mCompileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mRunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mSettingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mTestsScrollPane.setBorder(null);
        mTestsScrollPane.setOpaque(false);

        mTestsListPanel.setBackground(new java.awt.Color(30, 40, 57));
        mTestsListPanel.setOpaque(false);

        javax.swing.GroupLayout mTestsListPanelLayout = new javax.swing.GroupLayout(mTestsListPanel);
        mTestsListPanel.setLayout(mTestsListPanelLayout);
        mTestsListPanelLayout.setHorizontalGroup(
            mTestsListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
        );
        mTestsListPanelLayout.setVerticalGroup(
            mTestsListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
        );

        mTestsScrollPane.setViewportView(mTestsListPanel);

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Tests");

        mSelectAllCheckBox.setToolTipText("Select all");
        mSelectAllCheckBox.setContentAreaFilled(false);
        mSelectAllCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSelectAllCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mLeftPanelLayout = new javax.swing.GroupLayout(mLeftPanel);
        mLeftPanel.setLayout(mLeftPanelLayout);
        mLeftPanelLayout.setHorizontalGroup(
            mLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mLeftPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mTestsScrollPane)
                    .addGroup(mLeftPanelLayout.createSequentialGroup()
                        .addGroup(mLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mSelectAllCheckBox))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mLeftPanelLayout.setVerticalGroup(
            mLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mLeftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(mSelectAllCheckBox)
                .addGap(18, 18, 18)
                .addComponent(mTestsScrollPane)
                .addContainerGap())
        );

        mSplitPane.setLeftComponent(mLeftPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mSplitPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mSplitPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mSelectAllCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSelectAllCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mSelectAllCheckBoxActionPerformed

    private void mCommandEdittextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCommandEdittextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mCommandEdittextActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
            {
                if ("Darcula".equals(info.getName())) 
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton mAddButton;
    private javax.swing.JTextField mCommandEdittext;
    private javax.swing.JButton mCompileButton;
    private javax.swing.JPanel mLeftPanel;
    private javax.swing.JTextArea mOutputArea;
    private javax.swing.JScrollPane mOutputScrollPane;
    private javax.swing.JButton mRemoveButton;
    private javax.swing.JPanel mResetButton;
    private javax.swing.JPanel mRightPanel;
    private javax.swing.JPanel mRightToolbarLowerPanel;
    private javax.swing.JLabel mRoundCommands;
    private javax.swing.JLabel mRoundCommandsIcon;
    private javax.swing.JButton mRunButton;
    private javax.swing.JCheckBox mSelectAllCheckBox;
    private javax.swing.JPanel mSendButton;
    private javax.swing.JButton mSettingsButton;
    private javax.swing.JSplitPane mSplitPane;
    private javax.swing.JPanel mTestsListPanel;
    private javax.swing.JScrollPane mTestsScrollPane;
    // End of variables declaration//GEN-END:variables
}
