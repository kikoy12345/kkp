/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tampilan;

import Login.Login_Sistem;
import Menu.admin_guru;
import Menu.admin_jadwal;
import Menu.admin_mapel;
import Menu.admin_siswa;
import Menu.admin_nilai_siswa;
import static com.mysql.jdbc.StringUtils.isNullOrEmpty;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author developer
 */
public class Tampilan_Admin extends javax.swing.JFrame {

    admin_guru guru = new admin_guru();
    admin_siswa siswa = new admin_siswa();
    admin_mapel mapel = new admin_mapel();
    admin_jadwal jadwal = new admin_jadwal();
    admin_nilai_siswa nilai = new admin_nilai_siswa();
    private int dialogButton;
    
    private Connection con;
    private Statement st;
    private DefaultTableModel tabmode;
    private ResultSet rs;
    private PreparedStatement pst;
    
    public Tampilan_Admin() {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        st = DB.stm;
        
        main.add(guru);
        main.add(siswa);
        main.add(mapel);
        main.add(jadwal);
        main.add(nilai);
        guru.setVisible(false);
        siswa.setVisible(false);
        mapel.setVisible(false);
        jadwal.setVisible(false);
        nilai.setVisible(false);
        
        TambahAnggota.setVisible(false);
        TambahAnggota.setLocationRelativeTo(null);
        TambahAnggota.pack();
        kode_nip();
        tabel_admin();
    }
    public void tabel_admin() {
        Object[] baris1 = {"NIP","NAMA "};
        tabmode=new DefaultTableModel(null, baris1);
        tabel_admin.setModel(tabmode);
        String sql1 = "select * from admin";
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("nip");
                String b = hasil.getString("nama_admin");  
                String[]data={a,b};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());
        }
    
    }
private void kode_nip(){
        DateFormat s = new SimpleDateFormat("yyyyMMdd");
	java.util.Date d = new java.util.Date();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smk","root","");
            st = con.createStatement();
            String sql= "SELECT MAX(right(nip,1)) AS nip FROM guru";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                if(rs.first() == false){
                    nip.setText("0001");
                }else{
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int noLong = no.length();
                    for(int a=0;a<4-noLong;a++){ 
                        no = "0" + no;
                    }
                    nip.setText(s.format(d) + no);
                } 
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "error kode otomatis nip: \n" + e.toString(), "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TambahAnggota = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nip = new javax.swing.JTextField();
        nama_admin = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_admin = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        admin = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        sidebar = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        BUtama = new javax.swing.JButton();
        BGuru = new javax.swing.JButton();
        BNilai = new javax.swing.JButton();
        BSiswa = new javax.swing.JButton();
        BMapel = new javax.swing.JButton();
        BJadwal = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        TambahAnggota.setModal(true);
        TambahAnggota.setResizable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Data Pengguna");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("NIP");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Nama Lengkap");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Kata Sandi");

        nip.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nip.setEnabled(false);

        nama_admin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        password.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabel_admin.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_admin.setRowHeight(30);
        tabel_admin.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tabel_admin);

        javax.swing.GroupLayout TambahAnggotaLayout = new javax.swing.GroupLayout(TambahAnggota.getContentPane());
        TambahAnggota.getContentPane().setLayout(TambahAnggotaLayout);
        TambahAnggotaLayout.setHorizontalGroup(
            TambahAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TambahAnggotaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TambahAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(TambahAnggotaLayout.createSequentialGroup()
                        .addGroup(TambahAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(TambahAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nip)
                            .addComponent(nama_admin)
                            .addComponent(password)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TambahAnggotaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        TambahAnggotaLayout.setVerticalGroup(
            TambahAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TambahAnggotaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(TambahAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nip, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TambahAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nama_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TambahAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1340, 700));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(906, 60));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/logo-small1.png"))); // NOI18N

        jToolBar1.setBackground(new java.awt.Color(0, 0, 0));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton6.setBackground(new java.awt.Color(0, 0, 0));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradien_add_user.png"))); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        admin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        admin.setForeground(new java.awt.Color(255, 255, 255));
        admin.setText("Admin");
        admin.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));
        admin.setMaximumSize(new java.awt.Dimension(150, 25));
        admin.setMinimumSize(new java.awt.Dimension(150, 25));
        admin.setPreferredSize(new java.awt.Dimension(150, 25));
        jToolBar1.add(admin);

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_logout.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("SMK CAKRA NUSANTARA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 472, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        sidebar.setBackground(new java.awt.Color(17, 18, 34));
        sidebar.setPreferredSize(new java.awt.Dimension(91, 455));

        jToolBar2.setBackground(new java.awt.Color(17, 18, 34));
        jToolBar2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(102, 102, 102)));
        jToolBar2.setFloatable(false);
        jToolBar2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar2.setRollover(true);

        BUtama.setBackground(new java.awt.Color(17, 18, 34));
        BUtama.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BUtama.setForeground(new java.awt.Color(255, 255, 255));
        BUtama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_home.png"))); // NOI18N
        BUtama.setText("Utama");
        BUtama.setFocusable(false);
        BUtama.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BUtama.setMaximumSize(new java.awt.Dimension(90, 60));
        BUtama.setMinimumSize(new java.awt.Dimension(90, 60));
        BUtama.setPreferredSize(new java.awt.Dimension(90, 60));
        BUtama.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BUtama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUtamaActionPerformed(evt);
            }
        });
        jToolBar2.add(BUtama);

        BGuru.setBackground(new java.awt.Color(17, 18, 34));
        BGuru.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BGuru.setForeground(new java.awt.Color(255, 255, 255));
        BGuru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_user.png"))); // NOI18N
        BGuru.setText("Guru");
        BGuru.setFocusable(false);
        BGuru.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BGuru.setMaximumSize(new java.awt.Dimension(90, 60));
        BGuru.setMinimumSize(new java.awt.Dimension(90, 60));
        BGuru.setPreferredSize(new java.awt.Dimension(90, 60));
        BGuru.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BGuru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuruActionPerformed(evt);
            }
        });
        jToolBar2.add(BGuru);

        BNilai.setBackground(new java.awt.Color(17, 18, 34));
        BNilai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BNilai.setForeground(new java.awt.Color(255, 255, 255));
        BNilai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_chart.png"))); // NOI18N
        BNilai.setText("Nilai Siswa");
        BNilai.setFocusable(false);
        BNilai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BNilai.setMaximumSize(new java.awt.Dimension(90, 60));
        BNilai.setMinimumSize(new java.awt.Dimension(90, 60));
        BNilai.setPreferredSize(new java.awt.Dimension(90, 60));
        BNilai.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BNilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNilaiActionPerformed(evt);
            }
        });
        jToolBar2.add(BNilai);

        BSiswa.setBackground(new java.awt.Color(17, 18, 34));
        BSiswa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BSiswa.setForeground(new java.awt.Color(255, 255, 255));
        BSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_people.png"))); // NOI18N
        BSiswa.setText("Siswa");
        BSiswa.setFocusable(false);
        BSiswa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BSiswa.setMaximumSize(new java.awt.Dimension(90, 60));
        BSiswa.setMinimumSize(new java.awt.Dimension(90, 60));
        BSiswa.setPreferredSize(new java.awt.Dimension(90, 60));
        BSiswa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSiswaActionPerformed(evt);
            }
        });
        jToolBar2.add(BSiswa);

        BMapel.setBackground(new java.awt.Color(17, 18, 34));
        BMapel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BMapel.setForeground(new java.awt.Color(255, 255, 255));
        BMapel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_book.png"))); // NOI18N
        BMapel.setText("Mapel");
        BMapel.setFocusable(false);
        BMapel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BMapel.setMaximumSize(new java.awt.Dimension(90, 60));
        BMapel.setMinimumSize(new java.awt.Dimension(90, 60));
        BMapel.setPreferredSize(new java.awt.Dimension(90, 60));
        BMapel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BMapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMapelActionPerformed(evt);
            }
        });
        jToolBar2.add(BMapel);

        BJadwal.setBackground(new java.awt.Color(17, 18, 34));
        BJadwal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BJadwal.setForeground(new java.awt.Color(255, 255, 255));
        BJadwal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradien_calendar.png"))); // NOI18N
        BJadwal.setText("Jadwal");
        BJadwal.setFocusable(false);
        BJadwal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BJadwal.setMaximumSize(new java.awt.Dimension(90, 60));
        BJadwal.setMinimumSize(new java.awt.Dimension(90, 60));
        BJadwal.setPreferredSize(new java.awt.Dimension(90, 60));
        BJadwal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BJadwalActionPerformed(evt);
            }
        });
        jToolBar2.add(BJadwal);

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );

        getContentPane().add(sidebar, java.awt.BorderLayout.LINE_START);

        main.setLayout(new java.awt.CardLayout());

        home.setBackground(new java.awt.Color(17, 18, 34));
        home.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(17, 18, 34));

        jLabel2.setBackground(new java.awt.Color(17, 18, 34));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/logo.png"))); // NOI18N

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SMK CAKRA NUSANTARA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        home.add(jPanel2, new java.awt.GridBagConstraints());

        main.add(home, "card2");

        getContentPane().add(main, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BUtamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUtamaActionPerformed
        home.setVisible(true);
        guru.setVisible(false);
        siswa.setVisible(false);
        mapel.setVisible(false);
        jadwal.setVisible(false);
        nilai.setVisible(false);
        
        BUtama.setForeground(new Color(255, 53, 148));
        BNilai.setForeground(new Color(204,204,204));
        BGuru.setForeground(new Color(204,204,204));
        BSiswa.setForeground(new Color(204,204,204));
        BJadwal.setForeground(new Color(204,204,204));
        BMapel.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_BUtamaActionPerformed

    private void BGuruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuruActionPerformed
        home.setVisible(false);
        guru.setVisible(true);
        siswa.setVisible(false);
        mapel.setVisible(false);
        jadwal.setVisible(false);
        nilai.setVisible(false);
        
        BGuru.setForeground(new Color(255, 53, 148));
        BNilai.setForeground(new Color(204,204,204));
        BUtama.setForeground(new Color(204,204,204));
        BSiswa.setForeground(new Color(204,204,204));
        BJadwal.setForeground(new Color(204,204,204));
        BMapel.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_BGuruActionPerformed

    private void BSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSiswaActionPerformed
        home.setVisible(false);
        guru.setVisible(false);
        siswa.setVisible(true);
        siswa.Tabel_Siswa();
        siswa.tahun();
        siswa.Tabel_Siswa();
        mapel.setVisible(false);
        jadwal.setVisible(false);
        nilai.setVisible(false);
        
        BSiswa.setForeground(new Color(255, 53, 148));
        BNilai.setForeground(new Color(204,204,204));
        BUtama.setForeground(new Color(204,204,204));
        BGuru.setForeground(new Color(204,204,204));
        BJadwal.setForeground(new Color(204,204,204));
        BMapel.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_BSiswaActionPerformed

    private void BMapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMapelActionPerformed
        home.setVisible(false);
        guru.setVisible(false);
        siswa.setVisible(false);
        mapel.setVisible(true);
        jadwal.setVisible(false);
        nilai.setVisible(false);
        BMapel.setForeground(new Color(255, 53, 148));
        BNilai.setForeground(new Color(204,204,204));
        BUtama.setForeground(new Color(204,204,204));
        BGuru.setForeground(new Color(204,204,204));
        BJadwal.setForeground(new Color(204,204,204));
        BSiswa.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_BMapelActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int konfirmasi = JOptionPane.showConfirmDialog (null, "yakin anda ingin keluar ?","Warning",dialogButton);
        if( konfirmasi == JOptionPane.YES_OPTION){
            try {
                this.setVisible(false);
                Login_Sistem login = new Login_Sistem();
                login.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void BJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BJadwalActionPerformed
        home.setVisible(false);
        guru.setVisible(false);
        siswa.setVisible(false);
        mapel.setVisible(false);
        jadwal.setVisible(true);
        jadwal.tahun();
        jadwal.Tabel_Jadwal();
        nilai.setVisible(false);
        BJadwal.setForeground(new Color(255, 53, 148));
        BNilai.setForeground(new Color(204,204,204));
        BUtama.setForeground(new Color(204,204,204));
        BGuru.setForeground(new Color(204,204,204));
        BSiswa.setForeground(new Color(204,204,204));
        BMapel.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_BJadwalActionPerformed

    private void BNilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNilaiActionPerformed
        home.setVisible(false);
        guru.setVisible(false);
        siswa.setVisible(false);
        
        mapel.setVisible(false);
        jadwal.setVisible(false);
        nilai.setVisible(true);
        nilai.tahun();
        nilai.Tabel_Siswa();
        
        
        BNilai.setForeground(new Color(255, 53, 148));
        BGuru.setForeground(new Color(204,204,204));
        BUtama.setForeground(new Color(204,204,204));
        BSiswa.setForeground(new Color(204,204,204));
        BJadwal.setForeground(new Color(204,204,204));
        BMapel.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_BNilaiActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        TambahAnggota.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String a = nama_admin.getText();
        String b = password.getText();
        String c = "admin";
        if(isNullOrEmpty(a)&& isNullOrEmpty(b)){
            JOptionPane.showMessageDialog(null, "Silahkan input nama guru dan kata sandi !");
        }else{
            try{
                PreparedStatement ps = con.prepareStatement("INSERT INTO admin(nip,nama_admin,password,sebagai) VALUES(?,?,?,?)");
                ps.setString(1, nip.getText());
                ps.setString(2, nama_admin.getText());
                ps.setString(3, password.getText());
                ps.setString(4, c);
                ps.executeUpdate();


                JOptionPane.showMessageDialog(null, "berhasil Berhasil");  
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Gagal Input"+e.getMessage());
            }
        }
        nama_admin.setText("");
        password.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tampilan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tampilan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tampilan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tampilan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tampilan_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BGuru;
    private javax.swing.JButton BJadwal;
    private javax.swing.JButton BMapel;
    private javax.swing.JButton BNilai;
    private javax.swing.JButton BSiswa;
    private javax.swing.JButton BUtama;
    private javax.swing.JDialog TambahAnggota;
    public javax.swing.JLabel admin;
    private javax.swing.JPanel home;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JPanel main;
    private javax.swing.JTextField nama_admin;
    private javax.swing.JTextField nip;
    private javax.swing.JPasswordField password;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTable tabel_admin;
    // End of variables declaration//GEN-END:variables
}
