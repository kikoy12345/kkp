
package Tampilan;

import Login.Login_Sistem;
import Menu.guru_guru1;
import Menu.guru_jadwal;
import Menu.guru_nilai;
import Menu.guru_siswa;
import Menu.guru_wali_kelas;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.koneksi;

public class Tampilan_Guru extends javax.swing.JFrame {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    private int dialogButton;
    
    guru_nilai nilai = new guru_nilai();
    guru_guru1 data_guru = new guru_guru1();
    guru_wali_kelas wali = new guru_wali_kelas();
    guru_jadwal jadwal = new guru_jadwal();
    guru_siswa siswa = new guru_siswa();
    public Tampilan_Guru() {
        initComponents();
                
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        st = DB.stm;
        
        Akun_Guru();
        
        main.add(nilai);
        main.add(data_guru);
        main.add(wali);
        main.add(jadwal);
        main.add(siswa);
        nilai.setVisible(false);
        data_guru.setVisible(false);
        wali.setVisible(false);
        jadwal.setVisible(false);
        siswa.setVisible(false);
                
        account.setLocationRelativeTo(null);
        account.pack();
    }
    
   
    public void Akun_Guru() {
        try{
            String nip = akun_nip.getText();
            String sql ="select guru.nama_guru, guru.kode_mapel, mapel.mapel from guru, mapel where nip = '"+nip+"' and guru.kode_mapel=mapel.kode_mapel";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                String a = rs.getString("nama_guru");
                akun_nama.setText(a);
                
                String b = rs.getString("kode_mapel");
                akun_kodemapel.setText(b);
                
                String c = rs.getString("mapel");
                akun_mapel.setText(c);                
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "error" +e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        account = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        akun_nama = new javax.swing.JLabel();
        akun_kodemapel = new javax.swing.JLabel();
        akun_mapel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        akun_nip = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        new_password = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        nav = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton5 = new javax.swing.JButton();
        guru = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        BGuru = new javax.swing.JButton();
        BInput = new javax.swing.JButton();
        BWali = new javax.swing.JButton();
        BSiswa = new javax.swing.JButton();
        BJadwal = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        account.setModal(true);
        account.setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Data Akun");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("NIP");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Nama");

        akun_nama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        akun_nama.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        akun_kodemapel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        akun_kodemapel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        akun_mapel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        akun_mapel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Mata Pelajaran");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Kode Mapel");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Tutup");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        akun_nip.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        akun_nip.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Ganti Kata Sandi");

        jButton2.setText("Selesai");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout accountLayout = new javax.swing.GroupLayout(account.getContentPane());
        account.getContentPane().setLayout(accountLayout);
        accountLayout.setHorizontalGroup(
            accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(accountLayout.createSequentialGroup()
                        .addGroup(accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(accountLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(akun_nip, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(accountLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(akun_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(accountLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(akun_kodemapel, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(accountLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(akun_mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(accountLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(new_password, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        accountLayout.setVerticalGroup(
            accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(akun_nip, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(akun_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(akun_kodemapel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(akun_mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGroup(accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(new_password, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 700));

        nav.setBackground(new java.awt.Color(0, 0, 0));
        nav.setPreferredSize(new java.awt.Dimension(820, 60));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/logo-small1.png"))); // NOI18N

        jToolBar1.setBackground(new java.awt.Color(0, 0, 0));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/iconakun.png"))); // NOI18N
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        guru.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        guru.setForeground(new java.awt.Color(255, 255, 255));
        guru.setText("Guru");
        guru.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));
        guru.setIconTextGap(10);
        guru.setMaximumSize(new java.awt.Dimension(26, 27));
        guru.setMinimumSize(new java.awt.Dimension(26, 27));
        guru.setPreferredSize(new java.awt.Dimension(130, 27));
        guru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guruMouseClicked(evt);
            }
        });
        jToolBar1.add(guru);

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("SMK CAKRA NUSANTARA");

        javax.swing.GroupLayout navLayout = new javax.swing.GroupLayout(nav);
        nav.setLayout(navLayout);
        navLayout.setHorizontalGroup(
            navLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 552, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        navLayout.setVerticalGroup(
            navLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(nav, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(17, 18, 34));
        jPanel1.setPreferredSize(new java.awt.Dimension(92, 93));

        jToolBar2.setBackground(new java.awt.Color(17, 18, 34));
        jToolBar2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(102, 102, 102)));
        jToolBar2.setFloatable(false);
        jToolBar2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar2.setRollover(true);

        BGuru.setBackground(new java.awt.Color(17, 18, 34));
        BGuru.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BGuru.setForeground(new java.awt.Color(255, 255, 255));
        BGuru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_user.png"))); // NOI18N
        BGuru.setText("Data Guru");
        BGuru.setFocusable(false);
        BGuru.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BGuru.setIconTextGap(10);
        BGuru.setMaximumSize(new java.awt.Dimension(90, 70));
        BGuru.setMinimumSize(new java.awt.Dimension(90, 90));
        BGuru.setPreferredSize(new java.awt.Dimension(130, 40));
        BGuru.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BGuru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuruActionPerformed(evt);
            }
        });
        jToolBar2.add(BGuru);

        BInput.setBackground(new java.awt.Color(17, 18, 34));
        BInput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BInput.setForeground(new java.awt.Color(255, 255, 255));
        BInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_chart.png"))); // NOI18N
        BInput.setText("Input Nilai");
        BInput.setFocusable(false);
        BInput.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BInput.setIconTextGap(10);
        BInput.setMaximumSize(new java.awt.Dimension(90, 70));
        BInput.setMinimumSize(new java.awt.Dimension(90, 90));
        BInput.setPreferredSize(new java.awt.Dimension(130, 40));
        BInput.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInputActionPerformed(evt);
            }
        });
        jToolBar2.add(BInput);

        BWali.setBackground(new java.awt.Color(17, 18, 34));
        BWali.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BWali.setForeground(new java.awt.Color(255, 255, 255));
        BWali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_group.png"))); // NOI18N
        BWali.setText("Wali Kelas");
        BWali.setFocusable(false);
        BWali.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BWali.setIconTextGap(10);
        BWali.setMaximumSize(new java.awt.Dimension(90, 70));
        BWali.setMinimumSize(new java.awt.Dimension(90, 90));
        BWali.setPreferredSize(new java.awt.Dimension(130, 40));
        BWali.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BWali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BWaliActionPerformed(evt);
            }
        });
        jToolBar2.add(BWali);

        BSiswa.setBackground(new java.awt.Color(17, 18, 34));
        BSiswa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BSiswa.setForeground(new java.awt.Color(255, 255, 255));
        BSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_people.png"))); // NOI18N
        BSiswa.setText("Data Siswa");
        BSiswa.setFocusable(false);
        BSiswa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BSiswa.setIconTextGap(10);
        BSiswa.setMaximumSize(new java.awt.Dimension(90, 70));
        BSiswa.setMinimumSize(new java.awt.Dimension(90, 90));
        BSiswa.setPreferredSize(new java.awt.Dimension(130, 40));
        BSiswa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSiswaActionPerformed(evt);
            }
        });
        jToolBar2.add(BSiswa);

        BJadwal.setBackground(new java.awt.Color(17, 18, 34));
        BJadwal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BJadwal.setForeground(new java.awt.Color(255, 255, 255));
        BJadwal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradien_calendar.png"))); // NOI18N
        BJadwal.setText("Jadwal");
        BJadwal.setFocusable(false);
        BJadwal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BJadwal.setIconTextGap(10);
        BJadwal.setMaximumSize(new java.awt.Dimension(90, 70));
        BJadwal.setMinimumSize(new java.awt.Dimension(90, 90));
        BJadwal.setPreferredSize(new java.awt.Dimension(130, 40));
        BJadwal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BJadwalActionPerformed(evt);
            }
        });
        jToolBar2.add(BJadwal);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        main.setLayout(new java.awt.CardLayout());

        home.setBackground(new java.awt.Color(17, 18, 34));
        home.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(17, 18, 34));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("SMK CAKRA NUSANTARA");

        jLabel11.setBackground(new java.awt.Color(17, 18, 34));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        home.add(jPanel2, new java.awt.GridBagConstraints());

        main.add(home, "card2");

        getContentPane().add(main, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void BInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInputActionPerformed
        Akun_Guru();
        home.setVisible(false);
        nilai.setVisible(true);
        wali.setVisible(false);
        data_guru.setVisible(false);
        jadwal.setVisible(false);
        siswa.setVisible(false);
        nilai.nip.setText(this.akun_nip.getText());
        nilai.kode_mapel.setText(this.akun_kodemapel.getText());
        nilai.nip1.setText(this.akun_nip.getText());
        nilai.kode_mapel1.setText(this.akun_kodemapel.getText());
        nilai.kelas();
        nilai.tabel_nilai();
        
        BGuru.setForeground(new Color(204,204,204));
        BInput.setForeground(new Color(255,53,148));
        BWali.setForeground(new Color(204,204,204));
        BSiswa.setForeground(new Color(204,204,204));
        BJadwal.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_BInputActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        account.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BWaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BWaliActionPerformed
        Akun_Guru();
        home.setVisible(false);
        nilai.setVisible(false);
        wali.setVisible(true);        
        data_guru.setVisible(false);
        jadwal.setVisible(false);
        siswa.setVisible(false);
        wali.nip.setText(this.akun_nip.getText());
        wali.kode_mapel.setText(this.akun_kodemapel.getText());
        wali.Tabel_Wali();
        
        BGuru.setForeground(new Color(204,204,204));
        BWali.setForeground(new Color(255,53,148));
        BInput.setForeground(new Color(204,204,204));
        BSiswa.setForeground(new Color(204,204,204));
        BJadwal.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_BWaliActionPerformed

    private void BSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSiswaActionPerformed
        Akun_Guru();
        home.setVisible(false);
        nilai.setVisible(false);
        wali.setVisible(false);        
        data_guru.setVisible(false);
        jadwal.setVisible(false);
        siswa.setVisible(true);
        siswa.nip.setText(this.akun_nip.getText());
        siswa.kode_mapel.setText(this.akun_kodemapel.getText());
        siswa.kelas();
        siswa.tabel_siswa();
        
        BGuru.setForeground(new Color(204,204,204));
        BSiswa.setForeground(new Color(255,53,148));
        BWali.setForeground(new Color(204,204,204));
        BInput.setForeground(new Color(204,204,204));
        BJadwal.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_BSiswaActionPerformed

    private void BGuruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuruActionPerformed
        Akun_Guru();
        home.setVisible(false);
        nilai.setVisible(false);
        wali.setVisible(false);
        jadwal.setVisible(false);  
        siswa.setVisible(false);      
        data_guru.setVisible(true);
        data_guru.Tabel_Guru();
        
        BGuru.setForeground(new Color(255, 53, 148));
        BInput.setForeground(new Color(204,204,204));
        BWali.setForeground(new Color(204,204,204));
        BSiswa.setForeground(new Color(204,204,204));
        BJadwal.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_BGuruActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String sql ="UPDATE guru SET password = '"+new_password.getText()+"'"
            + " WHERE nip = '"+akun_nip.getText()+"'";
            pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Kata Sandi Berhasil Diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BJadwalActionPerformed
        Akun_Guru();
        home.setVisible(false);
        nilai.setVisible(false);
        wali.setVisible(false);
        data_guru.setVisible(false);
        jadwal.setVisible(true);
        siswa.setVisible(false);
        jadwal.nip.setText(this.akun_nip.getText());
        jadwal.kode_mapel.setText(this.akun_kodemapel.getText());
        jadwal.Tabel_Jadwal();
        
        BGuru.setForeground(new Color(204,204,204));
        BJadwal.setForeground(new Color(255,53,148));
        BWali.setForeground(new Color(204,204,204));
        BSiswa.setForeground(new Color(204,204,204));
        BInput.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_BJadwalActionPerformed

    private void guruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guruMouseClicked
        
    }//GEN-LAST:event_guruMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Akun_Guru();        
        account.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Tampilan_Guru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tampilan_Guru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tampilan_Guru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tampilan_Guru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tampilan_Guru().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BGuru;
    private javax.swing.JButton BInput;
    private javax.swing.JButton BJadwal;
    private javax.swing.JButton BSiswa;
    private javax.swing.JButton BWali;
    private javax.swing.JDialog account;
    public javax.swing.JLabel akun_kodemapel;
    public javax.swing.JLabel akun_mapel;
    public javax.swing.JLabel akun_nama;
    public javax.swing.JLabel akun_nip;
    public javax.swing.JLabel guru;
    private javax.swing.JPanel home;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JPanel main;
    private javax.swing.JPanel nav;
    private javax.swing.JPasswordField new_password;
    // End of variables declaration//GEN-END:variables
}
