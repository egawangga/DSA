package huffmannCode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GUIHuffman extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	
	public GUIHuffman() {
        initComponents();
    }

    private void initComponents() {

        Title = new javax.swing.JLabel();
        Title_File = new javax.swing.JLabel();
        Directory = new javax.swing.JTextField();
        BrowseFile = new javax.swing.JButton();
        Title_IsiData = new javax.swing.JLabel();
        ScrollDataAsli = new javax.swing.JScrollPane();
        DataAsli = new javax.swing.JTextArea();
        Title_IsiDataHuffman = new javax.swing.JLabel();
        ScrollDataHuffman = new javax.swing.JScrollPane();
        DataHuffman = new javax.swing.JTextArea();
        UkuranFileAsli = new javax.swing.JLabel();
        UkuranFileHuffman = new javax.swing.JLabel();
        Efisiensi = new javax.swing.JLabel();
        FileChooser = new javax.swing.JFileChooser();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Title.setFont(new java.awt.Font("Tahoma", 1, 24));
        Title.setText("Huffman Code");
        Title_File.setFont(new java.awt.Font("Tahoma", 0, 14));
        Title_File.setText("File :");
        BrowseFile.setText("Browse");
        BrowseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseFileActionPerformed(evt);
            }
        });

        Title_IsiData.setText("Isi Data :");

        DataAsli.setColumns(20);
        DataAsli.setRows(5);
        DataAsli.setWrapStyleWord(true);
        DataAsli.setLineWrap(true);
        DataAsli.setEditable(false);
        ScrollDataAsli.setViewportView(DataAsli);

        Title_IsiDataHuffman.setText("Isi Data : ( Setelah Kompresi )");

        DataHuffman.setColumns(20);
        DataHuffman.setRows(5);
        DataHuffman.setWrapStyleWord(true);
        DataHuffman.setLineWrap(true);
        DataHuffman.setEditable(false);
        
        ScrollDataHuffman.setViewportView(DataHuffman);

        UkuranFileAsli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UkuranFileAsli.setText("Ukuran File :");

        UkuranFileHuffman.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UkuranFileHuffman.setText("Ukuran File : ");

        Efisiensi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Efisiensi.setText("% Efisiensi Compression : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Title_File)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Directory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BrowseFile))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(Title)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ScrollDataAsli, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Title_IsiData)
                            .addComponent(UkuranFileAsli))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UkuranFileHuffman)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Title_IsiDataHuffman)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ScrollDataHuffman, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Efisiensi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Title_File)
                    .addComponent(Directory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrowseFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Title_IsiData)
                    .addComponent(Title_IsiDataHuffman))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ScrollDataAsli, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(ScrollDataHuffman))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UkuranFileAsli)
                    .addComponent(UkuranFileHuffman))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Efisiensi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BrowseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseFileActionPerformed
        if (FileChooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
        	FileReader file;
        	try {
        		
        		Directory.setText(FileChooser.getSelectedFile().getAbsolutePath());
        		
        		file = new FileReader(FileChooser.getSelectedFile().getAbsolutePath());
        		DataAsli.read(file, null);
        		file.close();
        		String Data = DataAsli.getText().toString();
        		
        		int[] refs = HuffmanCode.buildCharFreqs(Data);
        		Tree tree = HuffmanCode.buildTree(refs);
        		HuffmanCode.code(tree, new StringBuffer());
        		String DataR = HuffmanCode.cetak(Data);
        		DataHuffman.setText(DataR);
        		
        		UkuranFileAsli.setText("Ukuran file : " + Data.length() * 8 + " bits");
        		UkuranFileHuffman.setText("Ukuran file : " + DataR.length() + " bits");
        		Efisiensi.setText("% Efisiensi Compression : " + HuffmanCode.generateEficiency() + " %");
        		
        	} catch (FileNotFoundException ex) {
        		System.out.println("File not found!");
        	} catch (IOException ex) {
        		System.out.println("IOException!");
        	}
        }
    }//GEN-LAST:event_BrowseFileActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIHuffman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BrowseFile;
    private javax.swing.JTextArea DataAsli;
    private javax.swing.JTextArea DataHuffman;
    private javax.swing.JTextField Directory;
    private javax.swing.JLabel Efisiensi;
    private javax.swing.JScrollPane ScrollDataAsli;
    private javax.swing.JScrollPane ScrollDataHuffman;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel Title_File;
    private javax.swing.JLabel Title_IsiData;
    private javax.swing.JLabel Title_IsiDataHuffman;
    private javax.swing.JLabel UkuranFileAsli;
    private javax.swing.JLabel UkuranFileHuffman;
    private javax.swing.JFileChooser FileChooser;
    // End of variables declaration//GEN-END:variables
}
