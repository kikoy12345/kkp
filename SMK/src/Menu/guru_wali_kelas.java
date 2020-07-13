/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;


public class guru_wali_kelas extends javax.swing.JPanel {

    private Connection con;
    private Statement st;
    private DefaultTableModel tabmode;
    private ResultSet rs;
    private PreparedStatement pst;
    private int dialogButton;
    
    
    public guru_wali_kelas() {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        st = DB.stm;
        Tabel_Wali();
        tahun();
   
    }
    
    public void tahun(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int tahun = (year+1);
        
        String tahun_ajar = year+ "/" +tahun;
    
        tahun_ajaran.setText(tahun_ajar);
    }
    public void Tabel_Wali() {
        Object[] baris1 = {"NIS","NAMA SISWA","KELAS"};
        tabmode=new DefaultTableModel(null, baris1);
        tabel_wali.setModel(tabmode);
        String sql1 = "select siswa.nis, siswa.nama_siswa, siswa.kelas from siswa, wali where siswa.kelas=wali.wali_kelas and siswa.tahun='"+tahun_ajaran.getText()+"' and nip='"+nip.getText()+"'";
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("nis");
                String b = hasil.getString("nama_siswa");  
                String c = hasil.getString("kelas");  
                String[]data={a,b,c};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());
        }
    
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nis = new javax.swing.JLabel();
        Detail = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_detail = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        nis_siswa = new javax.swing.JLabel();
        nama_siswa = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tahun_ajaran = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton11 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_wali = new javax.swing.JTable();

        kode_mapel.setText("jLabel2");

        nip.setText("jLabel2");

        nis.setText("jLabel2");

        tabel_detail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabel_detail);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("NIS");

        nis_siswa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nis_siswa.setText("nis");
        nis_siswa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        nama_siswa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nama_siswa.setText("nama");
        nama_siswa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nama Siswa");

        javax.swing.GroupLayout DetailLayout = new javax.swing.GroupLayout(Detail.getContentPane());
        Detail.getContentPane().setLayout(DetailLayout);
        DetailLayout.setHorizontalGroup(
            DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                    .addGroup(DetailLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nis_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nama_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        DetailLayout.setVerticalGroup(
            DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nama_siswa, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                    .addGroup(DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nis_siswa, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(37, 37, 37));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Data Wali Kelas Siswa Tahun Ajaran");

        tahun_ajaran.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tahun_ajaran.setForeground(new java.awt.Color(204, 204, 204));
        tahun_ajaran.setText("2000");

        jToolBar1.setBackground(new java.awt.Color(37, 37, 37));
        jToolBar1.setFloatable(false);
        jToolBar1.setForeground(new java.awt.Color(204, 204, 204));

        jButton11.setBackground(new java.awt.Color(37, 37, 37));
        jButton11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(204, 204, 204));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_reresh.png"))); // NOI18N
        jButton11.setText("Segarkan");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton11.setMaximumSize(new java.awt.Dimension(100, 27));
        jButton11.setMinimumSize(new java.awt.Dimension(100, 27));
        jButton11.setPreferredSize(new java.awt.Dimension(100, 27));
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tahun_ajaran)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 572, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tahun_ajaran, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(17, 18, 34));

        tabel_wali.setBackground(new java.awt.Color(236, 236, 236));
        tabel_wali.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabel_wali.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_wali.setRowHeight(30);
        tabel_wali.setShowVerticalLines(false);
        tabel_wali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_waliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_wali);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        Tabel_Wali();

    }//GEN-LAST:event_jButton11ActionPerformed

    private void tabel_waliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_waliMouseClicked
        try{
            int row = tabel_wali.getSelectedRow();
            String tbl_klik=(tabel_wali.getModel().getValueAt(row, 0).toString());
            String sql ="select * from siswa where nis='"+tbl_klik+"' ";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                String a = rs.getString("nis");
                nis.setText(a);
            }
        } catch(Exception e){}
    }//GEN-LAST:event_tabel_waliMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Detail;
    private javax.swing.JButton jButton11;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    public final javax.swing.JLabel kode_mapel = new javax.swing.JLabel();
    private javax.swing.JLabel nama_siswa;
    public final javax.swing.JLabel nip = new javax.swing.JLabel();
    private javax.swing.JLabel nis;
    private javax.swing.JLabel nis_siswa;
    private javax.swing.JTable tabel_detail;
    private javax.swing.JTable tabel_wali;
    private javax.swing.JLabel tahun_ajaran;
    // End of variables declaration//GEN-END:variables

    public void setText(String guru) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
