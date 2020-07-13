/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import static com.mysql.jdbc.StringUtils.isNullOrEmpty;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author developer
 */
public class admin_guru extends javax.swing.JPanel {

    private Connection con;
    private Statement st;
    private DefaultTableModel tabmode;
    private ResultSet rs;
    private PreparedStatement pst;
    private int dialogButton;
    
    public admin_guru() {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        st = DB.stm;
        
        Locale locale = new Locale ("id", "ID");
        Locale.setDefault(locale); 

        Tabel_Guru();
        Tabel_Mapel();
        kode_nip();
        setting();
        Details.setLocationRelativeTo(null);
        Details.setVisible(false);
        Details.pack();
        
        Data_Mapel.setLocationRelativeTo(null);
        Data_Mapel.setVisible(false);
        Data_Mapel.pack();
        
        Form_Tambah_Guru.setLocationRelativeTo(null);
        Form_Tambah_Guru.setVisible(false);
        Form_Tambah_Guru.pack();
        
        Form_Ubah_Guru.setLocationRelativeTo(null);
        Form_Ubah_Guru.setVisible(false);
        Form_Ubah_Guru.pack();
        
        Tambahan.setVisible(false);
        Tambahan.setLocationRelativeTo(null);
        Tambahan.pack();
        
        password();
        aktif();
    }
    private void aktif(){
        String nip2 = id_nip.getText();
        String walikelas = (String) pilih_kelas.getSelectedItem();
        if (nip2.isEmpty() && walikelas.isEmpty()){
            Button_Save.setEnabled(true);
            Button_Update.setEnabled(false);
            Button_Delete.setEnabled(false);
        } else if ( walikelas.isEmpty()){
            Button_Save.setEnabled(false);
            Button_Update.setEnabled(true);
            Button_Delete.setEnabled(true);
        } else if (!nip2.isEmpty() && !walikelas.isEmpty()) {
            Button_Save.setEnabled(false);
            Button_Update.setEnabled(true);
            Button_Delete.setEnabled(true);
        }
    }
    private void password(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");  
        LocalDateTime now = LocalDateTime.now();  
        katasandi.setText(dtf.format(now));
    }
    public void setting(){
        try{
            String sql ="select * from advance where nip = '"+nip1.getText()+"'";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                String a = rs.getString("nip");
                id_nip.setText(a);
                String b = rs.getString("wali_kelas");
                pilih_kelas.setSelectedItem(b);
            }
        } catch(Exception e){}
    }
    public void Tabel_Guru() {
        Object[] baris1 = {"NIP","NAMA GURU","MATA PELAJARAN","WALI KELAS","KATA SANDI"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Guru.setModel(tabmode);
        String sql1 = "select * from guru LEFT JOIN wali ON guru.nip=wali.nip, mapel where guru.kode_mapel=mapel.kode_mapel";
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("nip");
                String b = hasil.getString("nama_guru");  
                String c = hasil.getString("mapel");  
                String d = hasil.getString("wali_kelas"); 
                String f = hasil.getString("password"); 
                String[]data={a,b,c,d,f};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());
        }
    
    }
    public void Tabel_Mapel() {
        Object[] baris1 = {"KODE MAPEL","NAMA MATA PELAJARAN"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Mapel.setModel(tabmode);
        String sql1 = "select * from mapel";
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("kode_mapel");
                String b = hasil.getString("mapel");  
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

        Form_Tambah_Guru = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nip = new javax.swing.JTextField();
        nama_guru = new javax.swing.JTextField();
        kode_mapel = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        katasandi = new javax.swing.JLabel();
        Form_Ubah_Guru = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nip1 = new javax.swing.JTextField();
        nama_guru1 = new javax.swing.JTextField();
        kode_mapel1 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        Details = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        d_nip = new javax.swing.JLabel();
        d_nama_guru = new javax.swing.JLabel();
        d_kode_mapel = new javax.swing.JLabel();
        d_mapel = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        d_wali_kelas = new javax.swing.JLabel();
        Data_Mapel = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel_Mapel = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        Tambahan = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        Button_Save = new javax.swing.JButton();
        Button_Delete = new javax.swing.JButton();
        Button_Update = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        pilih_kelas = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        id_nip = new javax.swing.JLabel();
        Advance = new javax.swing.JPopupMenu();
        Ubah_Data = new javax.swing.JMenuItem();
        Tambah_Perintah = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        cari_nama = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabel_Guru = new javax.swing.JTable();

        Form_Tambah_Guru.setModal(true);
        Form_Tambah_Guru.setResizable(false);

        jPanel3.setPreferredSize(new java.awt.Dimension(400, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Tambah Data Guru");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Form_Tambah_Guru.getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 50));

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton8.setText("Simpan");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setMaximumSize(new java.awt.Dimension(80, 21));
        jButton8.setMinimumSize(new java.awt.Dimension(80, 21));
        jButton8.setPreferredSize(new java.awt.Dimension(80, 21));
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(315, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        Form_Tambah_Guru.getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("NIP");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nama Guru");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Kode Mata Pelajaran");

        nip.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nip.setEnabled(false);

        nama_guru.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        kode_mapel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Kata Sandi");

        katasandi.setText("-");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nip)
                    .addComponent(nama_guru)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(kode_mapel, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(katasandi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(nip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nama_guru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(kode_mapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(katasandi))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        Form_Tambah_Guru.getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        Form_Ubah_Guru.setModal(true);
        Form_Ubah_Guru.setResizable(false);

        jPanel6.setPreferredSize(new java.awt.Dimension(400, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Ubah Data Guru");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Form_Ubah_Guru.getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel7.setPreferredSize(new java.awt.Dimension(400, 50));

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton11.setText("Selesai");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setMaximumSize(new java.awt.Dimension(80, 21));
        jButton11.setMinimumSize(new java.awt.Dimension(80, 21));
        jButton11.setPreferredSize(new java.awt.Dimension(80, 21));
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton25.setText("Tambahan");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Form_Ubah_Guru.getContentPane().add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("NIP");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Nama Guru");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Kode Mata Pelajaran");

        nip1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nip1.setEnabled(false);

        nama_guru1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        kode_mapel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nip1)
                    .addComponent(nama_guru1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(kode_mapel1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(nip1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(nama_guru1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(kode_mapel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        Form_Ubah_Guru.getContentPane().add(jPanel8, java.awt.BorderLayout.CENTER);

        Details.setModal(true);
        Details.setResizable(false);

        jPanel9.setPreferredSize(new java.awt.Dimension(519, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Details Data Guru");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Details.getContentPane().add(jPanel9, java.awt.BorderLayout.PAGE_START);

        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel10.setPreferredSize(new java.awt.Dimension(519, 50));

        jButton14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton14.setText("Tutup");
        jButton14.setFocusable(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setMaximumSize(new java.awt.Dimension(80, 21));
        jButton14.setMinimumSize(new java.awt.Dimension(80, 21));
        jButton14.setPreferredSize(new java.awt.Dimension(80, 21));
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(429, Short.MAX_VALUE)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        Details.getContentPane().add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("NIP");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Nama Guru");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Kode Mata Pelajaran");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Mata Pelajaran");

        d_nip.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        d_nip.setText("...........................");

        d_nama_guru.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        d_nama_guru.setText("...........................");

        d_kode_mapel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        d_kode_mapel.setText("...........................");

        d_mapel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        d_mapel.setText("...........................");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Wali Kelas");

        d_wali_kelas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        d_wali_kelas.setText("...........................");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(d_nip, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(d_nama_guru, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(d_kode_mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(d_mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(d_wali_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(d_nip))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(d_nama_guru))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(d_kode_mapel))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(d_mapel))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(d_wali_kelas))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        Details.getContentPane().add(jPanel11, java.awt.BorderLayout.CENTER);

        Data_Mapel.setModal(true);
        Data_Mapel.setResizable(false);
        Data_Mapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Data_MapelMouseClicked(evt);
            }
        });

        Tabel_Mapel.setModel(new javax.swing.table.DefaultTableModel(
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
        Tabel_Mapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel_MapelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabel_Mapel);

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton16.setText("Tutup");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Data_MapelLayout = new javax.swing.GroupLayout(Data_Mapel.getContentPane());
        Data_Mapel.getContentPane().setLayout(Data_MapelLayout);
        Data_MapelLayout.setHorizontalGroup(
            Data_MapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_MapelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Data_MapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Data_MapelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton16)))
                .addContainerGap())
        );
        Data_MapelLayout.setVerticalGroup(
            Data_MapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Data_MapelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Tambahan.setMinimumSize(new java.awt.Dimension(400, 200));
        Tambahan.setModal(true);
        Tambahan.setResizable(false);

        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel13.setPreferredSize(new java.awt.Dimension(400, 50));

        Button_Save.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Button_Save.setText("Simpan");
        Button_Save.setFocusable(false);
        Button_Save.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Save.setMaximumSize(new java.awt.Dimension(80, 21));
        Button_Save.setMinimumSize(new java.awt.Dimension(80, 21));
        Button_Save.setPreferredSize(new java.awt.Dimension(80, 21));
        Button_Save.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SaveActionPerformed(evt);
            }
        });

        Button_Delete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Button_Delete.setText("Hapus");
        Button_Delete.setFocusable(false);
        Button_Delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Delete.setMaximumSize(new java.awt.Dimension(80, 21));
        Button_Delete.setMinimumSize(new java.awt.Dimension(80, 21));
        Button_Delete.setPreferredSize(new java.awt.Dimension(80, 21));
        Button_Delete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_DeleteActionPerformed(evt);
            }
        });

        Button_Update.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Button_Update.setText("Selesai");
        Button_Update.setFocusable(false);
        Button_Update.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Update.setMaximumSize(new java.awt.Dimension(80, 21));
        Button_Update.setMinimumSize(new java.awt.Dimension(80, 21));
        Button_Update.setPreferredSize(new java.awt.Dimension(80, 21));
        Button_Update.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_UpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 84, Short.MAX_VALUE)
                .addComponent(Button_Save, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_Delete, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(Button_Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button_Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Wali Kelas");

        pilih_kelas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pilih_kelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"", "Kelas 10", "Kelas 11", "Kelas 12"}));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Tambah Tugas Guru");

        id_nip.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout TambahanLayout = new javax.swing.GroupLayout(Tambahan.getContentPane());
        Tambahan.getContentPane().setLayout(TambahanLayout);
        TambahanLayout.setHorizontalGroup(
            TambahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TambahanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TambahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TambahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pilih_kelas, 0, 145, Short.MAX_VALUE)
                    .addComponent(id_nip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
        );
        TambahanLayout.setVerticalGroup(
            TambahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TambahanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TambahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(id_nip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(TambahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pilih_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        Ubah_Data.setText("Ubah");
        Ubah_Data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ubah_DataActionPerformed(evt);
            }
        });
        Advance.add(Ubah_Data);

        Tambah_Perintah.setText("Advance");
        Tambah_Perintah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tambah_PerintahActionPerformed(evt);
            }
        });
        Advance.add(Tambah_Perintah);

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(37, 37, 37));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1062, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Data Guru");

        jToolBar1.setBackground(new java.awt.Color(37, 37, 37));
        jToolBar1.setFloatable(false);

        cari_nama.setBackground(new java.awt.Color(37, 37, 77));
        cari_nama.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cari_nama.setForeground(new java.awt.Color(204, 204, 204));
        cari_nama.setCaretColor(new java.awt.Color(0, 204, 51));
        cari_nama.setMaximumSize(new java.awt.Dimension(200, 27));
        cari_nama.setMinimumSize(new java.awt.Dimension(200, 27));
        cari_nama.setPreferredSize(new java.awt.Dimension(200, 27));
        jToolBar1.add(cari_nama);

        jButton5.setBackground(new java.awt.Color(37, 37, 37));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 204, 204));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_search.png"))); // NOI18N
        jButton5.setText("Cari");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton5.setMaximumSize(new java.awt.Dimension(60, 23));
        jButton5.setMinimumSize(new java.awt.Dimension(60, 23));
        jButton5.setPreferredSize(new java.awt.Dimension(60, 23));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton1.setBackground(new java.awt.Color(37, 37, 37));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 204, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_add.png"))); // NOI18N
        jButton1.setText("Tambah");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.setMaximumSize(new java.awt.Dimension(80, 27));
        jButton1.setMinimumSize(new java.awt.Dimension(80, 27));
        jButton1.setPreferredSize(new java.awt.Dimension(80, 27));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setBackground(new java.awt.Color(37, 37, 37));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 204, 204));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_edit.png"))); // NOI18N
        jButton2.setText("Ubah");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.setMaximumSize(new java.awt.Dimension(80, 27));
        jButton2.setMinimumSize(new java.awt.Dimension(80, 27));
        jButton2.setPreferredSize(new java.awt.Dimension(80, 27));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setBackground(new java.awt.Color(37, 37, 37));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(204, 204, 204));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_delete.png"))); // NOI18N
        jButton3.setText("Hapus");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.setMaximumSize(new java.awt.Dimension(80, 27));
        jButton3.setMinimumSize(new java.awt.Dimension(80, 27));
        jButton3.setPreferredSize(new java.awt.Dimension(80, 27));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setBackground(new java.awt.Color(37, 37, 37));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(204, 204, 204));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_print.png"))); // NOI18N
        jButton4.setText("Cetak");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton4.setMaximumSize(new java.awt.Dimension(80, 27));
        jButton4.setMinimumSize(new java.awt.Dimension(80, 27));
        jButton4.setPreferredSize(new java.awt.Dimension(80, 27));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton6.setBackground(new java.awt.Color(37, 37, 37));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(204, 204, 204));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_reresh.png"))); // NOI18N
        jButton6.setText("Segarkan");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton6.setMaximumSize(new java.awt.Dimension(80, 27));
        jButton6.setMinimumSize(new java.awt.Dimension(80, 27));
        jButton6.setPreferredSize(new java.awt.Dimension(80, 27));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(17, 18, 34));

        Tabel_Guru.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tabel_Guru.setModel(new javax.swing.table.DefaultTableModel(
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
        Tabel_Guru.setRowHeight(30);
        Tabel_Guru.setShowVerticalLines(false);
        Tabel_Guru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel_GuruMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Tabel_GuruMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(Tabel_Guru);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Tabel_Guru();
        id_nip.setText("");
        cari_nama.setText("");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String nm = nip1.getText();
        if(isNullOrEmpty(nm)){
            JOptionPane.showMessageDialog(null, "klik baris tabel yang ingin dihapus");
        }else{
            int konfirmasi = JOptionPane.showConfirmDialog (null, "yakin anda ingin menghapus ?","Warning",dialogButton);
            if( konfirmasi == JOptionPane.YES_OPTION){
                try{
                    String sql ="delete from guru where nip='"+nip1.getText()+"'";
                    pst=con.prepareStatement(sql); 
                    pst.execute();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(this, "data gagal di hapus"+e.getMessage());
                }
                try{
                    String sql2 ="delete from wali where nip='"+nip1.getText()+"'";
                    pst=con.prepareStatement(sql2); 
                    pst.execute();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(this, "data gagal di hapus"+e.getMessage());
                }
                Tabel_Guru();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String nm = nip1.getText();
        if(isNullOrEmpty(nm)){
            JOptionPane.showMessageDialog(null, "klik baris tabel yang ingin diubah");
        }else{
            Form_Ubah_Guru.setVisible(true);
            id_nip.setText("");
            setting();
        }        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Form_Tambah_Guru.setVisible(true);
        kode_nip();
        password();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       Object[] baris1 = {"NIP","NAMA GURU","MATA PELAJARAN","WALI KELAS","KATA SANDI"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Guru.setModel(tabmode);
        String sql1 = "select * from guru LEFT JOIN wali ON guru.nip=wali.nip, mapel where guru.kode_mapel=mapel.kode_mapel and guru.nip = '"+cari_nama.getText()+"'";
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("nip");
                String b = hasil.getString("nama_guru");  
                String c = hasil.getString("mapel");  
                String d = hasil.getString("wali_kelas"); 
                String f = hasil.getString("password"); 
                String[]data={a,b,c,d,f};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Details.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String a = nama_guru.getText();
        String b = kode_mapel.getText();
        String c = "guru";
        if(isNullOrEmpty(a)&& isNullOrEmpty(b)){
            JOptionPane.showMessageDialog(null, "Silahkan input nama guru dan kode mata pelajarn !");
        }else{
            try{
                PreparedStatement ps = con.prepareStatement("INSERT INTO guru(nip,nama_guru,kode_mapel, sebagai, password) VALUES(?,?,?,?,?)");
                ps.setString(1, nip.getText());
                ps.setString(2, nama_guru.getText());
                ps.setString(3, kode_mapel.getText());
                ps.setString(4, c);
                ps.setString(5, katasandi.getText());
                ps.executeUpdate();


                JOptionPane.showMessageDialog(null, "berhasil Berhasil");            
                Tabel_Guru(); 
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Gagal Input"+e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        Data_Mapel.dispose();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Data_Mapel.setVisible(true);
        Tabel_Mapel();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        Data_Mapel.setVisible(true);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void Data_MapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Data_MapelMouseClicked
        
    }//GEN-LAST:event_Data_MapelMouseClicked

    private void Tabel_MapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel_MapelMouseClicked
        try{
            int row = Tabel_Mapel.getSelectedRow();
            String tbl_klik=(Tabel_Mapel.getModel().getValueAt(row, 0).toString());
            String sql ="select * from mapel where kode_mapel='"+tbl_klik+"' ";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                String a = rs.getString("kode_mapel");
                kode_mapel.setText(a);
                
                String b = rs.getString("kode_mapel");
                kode_mapel1.setText(b);
                
            }
        } catch(Exception e){}
    }//GEN-LAST:event_Tabel_MapelMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       try {
            String sql ="UPDATE guru SET nama_guru = '"+nama_guru1.getText()+"',"
            + "kode_mapel = '"+kode_mapel1.getText()+"'"
            + " WHERE nip = '"+nip1.getText()+"'";
            pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Perubahan Data Berhasil");
            Tabel_Guru();
            Form_Ubah_Guru.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void Tabel_GuruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel_GuruMouseClicked
        try{
            int row = Tabel_Guru.getSelectedRow();
            String tbl_klik=(Tabel_Guru.getModel().getValueAt(row, 0).toString());
            String sql ="select * from guru where nip='"+tbl_klik+"' ";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                String a = rs.getString("nip");
                nip1.setText(a);
                
                String b = rs.getString("nama_guru");
                nama_guru1.setText(b);
                
                String c = rs.getString("kode_mapel");
                kode_mapel1.setText(c);                
                
                
            }
        } catch(Exception e){}
    }//GEN-LAST:event_Tabel_GuruMouseClicked

    private void Button_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SaveActionPerformed
        
        try{
        String sql = "select * from wali where wali_kelas='"+pilih_kelas.getSelectedItem()+"'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                if(nip.getText().equals(rs.getString("wali_kelas"))){
                      JOptionPane.showMessageDialog(null, "Maaf data tidak bisa ditambah");                    
                }
            } else{
                    PreparedStatement ps = con.prepareStatement("INSERT INTO wali(nip,wali_kelas) VALUES(?,?)");
                    ps.setString(1, nip1.getText());
                    ps.setString(2, (String) pilih_kelas.getSelectedItem());
                    ps.executeUpdate();

                    Tambahan.dispose();
                    Form_Ubah_Guru.dispose();
                    JOptionPane.showMessageDialog(null, "berhasil Berhasil");            
                    Tabel_Guru();   
                }
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Tidak terhubung ke server " +e.getMessage());
            }

    }//GEN-LAST:event_Button_SaveActionPerformed

    private void Button_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_DeleteActionPerformed
        
            int konfirmasi = JOptionPane.showConfirmDialog (null, "yakin anda ingin menghapus ?","Warning",dialogButton);
            if( konfirmasi == JOptionPane.YES_OPTION){
                try{
                    String sql ="delete from wali where nip='"+nip1.getText()+"'";
                    pst=con.prepareStatement(sql);
                    pst.execute();
                    Form_Ubah_Guru.dispose();
                    Tambahan.dispose();
                    JOptionPane.showMessageDialog(this, "data terhapus");
                }catch (Exception e){
                    JOptionPane.showMessageDialog(this, "data gagal di hapus"+e.getMessage());
                }
                Tabel_Guru();
            }
        
    }//GEN-LAST:event_Button_DeleteActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        try{
            String sql ="select * from wali where nip = '"+nip1.getText()+"'";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                String a = rs.getString("nip");
                id_nip.setText(a);
                String b = rs.getString("wali_kelas");
                pilih_kelas.setSelectedItem(b);
            }
            aktif();
        } catch(Exception e){}
        
        Tambahan.setVisible(true);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void Tabel_GuruMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel_GuruMouseReleased
        if(evt.isPopupTrigger()){
            Advance.show(this, evt.getX(),evt.getY());
        }
    }//GEN-LAST:event_Tabel_GuruMouseReleased

    private void Tambah_PerintahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tambah_PerintahActionPerformed
        Tambahan.setVisible(true);
    }//GEN-LAST:event_Tambah_PerintahActionPerformed

    private void Ubah_DataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ubah_DataActionPerformed
        Form_Ubah_Guru.setVisible(true);
    }//GEN-LAST:event_Ubah_DataActionPerformed

    private void Button_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_UpdateActionPerformed
        try {
            String sql ="UPDATE wali SET wali_kelas = '"+pilih_kelas.getSelectedItem()+"'"
           + " WHERE nip = '"+nip1.getText()+"'";
            pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Perubahan Data Berhasil");
            Tabel_Guru();
            Form_Ubah_Guru.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        Tambahan.dispose();
        //id_nip.setText("");
        aktif();
    }//GEN-LAST:event_Button_UpdateActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try
        {
            String namaFile = "src/Laporan/LaporanDataGuru.jasper";
            HashMap param = new HashMap();
            File lapFile = new File(namaFile);
            JasperReport jsreport = (JasperReport) JRLoader.loadObjectFromFile(lapFile.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jsreport, param, con);
            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        }
        catch (JRException ex)
        {
            Logger.getLogger(admin_guru.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu Advance;
    private javax.swing.JButton Button_Delete;
    private javax.swing.JButton Button_Save;
    private javax.swing.JButton Button_Update;
    private javax.swing.JDialog Data_Mapel;
    private javax.swing.JDialog Details;
    private javax.swing.JDialog Form_Tambah_Guru;
    private javax.swing.JDialog Form_Ubah_Guru;
    private javax.swing.JTable Tabel_Guru;
    private javax.swing.JTable Tabel_Mapel;
    private javax.swing.JMenuItem Tambah_Perintah;
    private javax.swing.JDialog Tambahan;
    private javax.swing.JMenuItem Ubah_Data;
    private javax.swing.JTextField cari_nama;
    private javax.swing.JLabel d_kode_mapel;
    private javax.swing.JLabel d_mapel;
    private javax.swing.JLabel d_nama_guru;
    private javax.swing.JLabel d_nip;
    private javax.swing.JLabel d_wali_kelas;
    private javax.swing.JLabel id_nip;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel katasandi;
    private javax.swing.JTextField kode_mapel;
    private javax.swing.JTextField kode_mapel1;
    private javax.swing.JTextField nama_guru;
    private javax.swing.JTextField nama_guru1;
    private javax.swing.JTextField nip;
    private javax.swing.JTextField nip1;
    private javax.swing.JComboBox<String> pilih_kelas;
    // End of variables declaration//GEN-END:variables
}
