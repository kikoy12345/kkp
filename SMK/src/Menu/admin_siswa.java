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
import java.util.Calendar;
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
public class admin_siswa extends javax.swing.JPanel {

    private Connection con;
    private Statement st;
    private DefaultTableModel tabmode;
    private ResultSet rs;
    private PreparedStatement pst;
    private int dialogButton;
    
    public admin_siswa() {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        st = DB.stm;
        Tabel_Siswa();
        kode_nis();
        
        Locale locale = new Locale ("id", "ID");
        Locale.setDefault(locale); 
        
        Details.setLocationRelativeTo(null);
        Details.setVisible(false);
        Details.pack();
        
        Form_Tambah_Siswa.setLocationRelativeTo(null);
        Form_Tambah_Siswa.setVisible(false);
        Form_Tambah_Siswa.pack();
                
        Form_Ubah_Siswa.setVisible(false);
        Form_Ubah_Siswa.setLocationRelativeTo(null);
        Form_Ubah_Siswa.pack();
        
        tahun();
    }
    public void tahun(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int tahun = (year+1);
        
        String tahun_ajar = year+ "/" +tahun;
    
        tahun_ajaran.setText(tahun_ajar);
        tahun_masuk.setText(tahun_ajar);
    }

    
    public void Tabel_Siswa() {
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        String sql1 = "select * from siswa where tahun = '"+tahun_ajaran.getText()+"'";
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String[]data={a,b,c,d,e};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }    
    }
    public void lihat_semua_data() {
           Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS"};
           tabmode=new DefaultTableModel(null, baris1);
           Tabel_Siswa.setModel(tabmode);
           String sql1 = "select * from siswa";
           try{
               java.sql.Statement stat = con.createStatement();
               ResultSet hasil = stat.executeQuery(sql1);
               while(hasil.next()){
                   String a = hasil.getString("tahun");
                   String b = hasil.getString("nis");  
                   String c = hasil.getString("nama_siswa");  
                   String d = hasil.getString("semester");  
                   String e = hasil.getString("kelas");   
                   String[]data={a,b,c,d,e};
                   tabmode.addRow(data);
               }
           }catch(Exception e){ 
               JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
           }    
       }
    public void cari_nama_atau_tahun_berdasarkan_tahun_ajaran_sekerang(){
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        String sql1 = "select * from siswa where (tahun = '"+cari_nama.getText()+"' OR nis = '"+cari_nama.getText()+"') AND tahun = '"+tahun_ajaran.getText()+"'";
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String[]data={a,b,c,d,e};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }  
    }
    
    public void cari_nama_atau_tahun_tanpa_tahun_ajaran(){
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        String sql1 = "select * from siswa where (tahun = '"+cari_nama.getText()+"' OR nis = '"+cari_nama.getText()+"')";
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String[]data={a,b,c,d,e};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }  
    }
    
    public void cari_kelas_tanpa_tahun_ajaran_berdasarkan_nama_atau_tahun(){
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        String sql1 = "select * from siswa where kelas = '"+fokus_kelas.getSelectedItem()+"' and (tahun = '"+cari_nama.getText()+"' OR nis = '"+cari_nama.getText()+"')";
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String[]data={a,b,c,d,e};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }  
    }
    
    public void cari_kelas_tanpa_tahun_ajaran(){
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        String sql1 = "select * from siswa where kelas = '"+fokus_kelas.getSelectedItem()+"'";
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String[]data={a,b,c,d,e};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }  
    }
    
    private void fokus_kelas(){
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        String sql1 = "select * from siswa where kelas = '"+fokus_kelas.getSelectedItem()+"' and tahun = '"+tahun_ajaran.getText()+"'";
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String[]data={a,b,c,d,e};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }    
    }
    
    
    
    public void Reset(){
        nama_siswa.setText("");
        
        tahun1.setText("");
        nama_siswa1.setText("");
    }
    public void Batal(){
        tahun_masuk.setText("");
        nama_siswa.setText("");
        
        tahun1.setText("");
        nama_siswa1.setText("");
    }
   
    private void kode_nis(){
        DateFormat s = new SimpleDateFormat("yyyyMMdd");
	java.util.Date d = new java.util.Date();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smk","root","");
            st = con.createStatement();
            String sql= "SELECT MAX(right(nis,3)) AS nis FROM siswa";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                if(rs.first() == false){
                    nis.setText("001");
                }else{
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int noLong = no.length();
                    for(int a=0;a<3-noLong;a++){ 
                        no = "0" + no;
                    }nis.setText(s.format(d) + no);
                } 
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "error kode otomatis linen: \n" + e.toString(), "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Form_Tambah_Siswa = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nis = new javax.swing.JTextField();
        nama_siswa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tahun_masuk = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        semester = new javax.swing.JComboBox<>();
        kelas = new javax.swing.JTextField();
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
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Form_Ubah_Siswa = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        nis1 = new javax.swing.JTextField();
        nama_siswa1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        tahun1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        semester1 = new javax.swing.JComboBox<>();
        kelas1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tahun_ajaran = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabel_Siswa = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel6 = new javax.swing.JLabel();
        cari_nama = new javax.swing.JTextField();
        BUTTON_CARI = new javax.swing.JButton();
        fokus_kelas = new javax.swing.JComboBox<>();
        lihat = new javax.swing.JCheckBox();

        Form_Tambah_Siswa.setModal(true);
        Form_Tambah_Siswa.setResizable(false);

        jPanel3.setPreferredSize(new java.awt.Dimension(400, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Tambah Data Siswa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Form_Tambah_Siswa.getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 45));

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
                .addContainerGap(354, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Form_Tambah_Siswa.getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("NIS");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nama Siswa");

        nis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nis.setEnabled(false);

        nama_siswa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Tahun Ajaran");

        tahun_masuk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tahun_masuk.setEnabled(false);
        tahun_masuk.setFocusable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Pilih Semester");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Pilih Kelas");

        semester.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        semester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6" }));
        semester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semesterActionPerformed(evt);
            }
        });

        kelas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kelas.setEnabled(false);
        kelas.setFocusable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nis, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nama_siswa, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(semester, javax.swing.GroupLayout.Alignment.TRAILING, 0, 273, Short.MAX_VALUE)
                    .addComponent(tahun_masuk)
                    .addComponent(kelas))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tahun_masuk)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nis)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nama_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kelas))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        Form_Tambah_Siswa.getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

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
        jLabel11.setText("NIS");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Nama Siswa");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Status Semester");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Status Kelas");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Di Input Oleh");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Pada Tanggal");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Waktu");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("...........................");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("...........................");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("...........................");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("...........................");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("...........................");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("...........................");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("...........................");

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
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel24))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        Details.getContentPane().add(jPanel11, java.awt.BorderLayout.CENTER);

        Form_Ubah_Siswa.setModal(true);
        Form_Ubah_Siswa.setResizable(false);

        jPanel12.setPreferredSize(new java.awt.Dimension(400, 40));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Tambah Data Siswa");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Form_Ubah_Siswa.getContentPane().add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel13.setPreferredSize(new java.awt.Dimension(400, 45));

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton16.setText("Simpan");
        jButton16.setFocusable(false);
        jButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton16.setMaximumSize(new java.awt.Dimension(80, 21));
        jButton16.setMinimumSize(new java.awt.Dimension(80, 21));
        jButton16.setPreferredSize(new java.awt.Dimension(80, 21));
        jButton16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(354, Short.MAX_VALUE)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Form_Ubah_Siswa.getContentPane().add(jPanel13, java.awt.BorderLayout.PAGE_END);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("NIS");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("Nama Siswa");

        nis1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nis1.setEnabled(false);

        nama_siswa1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("Tahun Ajaran");

        tahun1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Pilih Semester");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("Pilih Kelas");

        semester1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        semester1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6" }));

        kelas1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kelas1.setEnabled(false);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nis1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nama_siswa1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(semester1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 280, Short.MAX_VALUE)
                    .addComponent(tahun1)
                    .addComponent(kelas1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tahun1)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nis1)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nama_siswa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semester1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kelas1))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        Form_Ubah_Siswa.getContentPane().add(jPanel14, java.awt.BorderLayout.CENTER);

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(37, 37, 37));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1062, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Data Siswa Tuhun Ajaran");

        tahun_ajaran.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tahun_ajaran.setForeground(new java.awt.Color(204, 204, 204));
        tahun_ajaran.setText("2000");

        jToolBar1.setBackground(new java.awt.Color(37, 37, 37));
        jToolBar1.setFloatable(false);

        jButton1.setBackground(new java.awt.Color(37, 37, 37));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 204, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_add.png"))); // NOI18N
        jButton1.setText("Tambah");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.setMaximumSize(new java.awt.Dimension(80, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(80, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(80, 23));
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
        jButton2.setMaximumSize(new java.awt.Dimension(80, 23));
        jButton2.setMinimumSize(new java.awt.Dimension(80, 23));
        jButton2.setPreferredSize(new java.awt.Dimension(80, 23));
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
        jButton3.setMaximumSize(new java.awt.Dimension(80, 23));
        jButton3.setMinimumSize(new java.awt.Dimension(80, 23));
        jButton3.setPreferredSize(new java.awt.Dimension(80, 23));
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
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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
        jButton6.setMaximumSize(new java.awt.Dimension(80, 23));
        jButton6.setMinimumSize(new java.awt.Dimension(80, 23));
        jButton6.setPreferredSize(new java.awt.Dimension(80, 23));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tahun_ajaran)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 336, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tahun_ajaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(17, 18, 34));

        Tabel_Siswa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tabel_Siswa.setModel(new javax.swing.table.DefaultTableModel(
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
        Tabel_Siswa.setRowHeight(30);
        Tabel_Siswa.setShowVerticalLines(false);
        Tabel_Siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel_SiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabel_Siswa);

        jToolBar2.setBackground(new java.awt.Color(17, 18, 34));
        jToolBar2.setFloatable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Cari NIS / Tahun Ajaran");
        jLabel6.setMaximumSize(new java.awt.Dimension(135, 27));
        jLabel6.setMinimumSize(new java.awt.Dimension(135, 27));
        jLabel6.setPreferredSize(new java.awt.Dimension(135, 27));
        jToolBar2.add(jLabel6);

        cari_nama.setBackground(new java.awt.Color(37, 37, 77));
        cari_nama.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cari_nama.setForeground(new java.awt.Color(204, 204, 204));
        cari_nama.setCaretColor(new java.awt.Color(0, 204, 51));
        cari_nama.setMaximumSize(new java.awt.Dimension(200, 25));
        cari_nama.setMinimumSize(new java.awt.Dimension(200, 25));
        cari_nama.setPreferredSize(new java.awt.Dimension(200, 25));
        jToolBar2.add(cari_nama);

        BUTTON_CARI.setBackground(new java.awt.Color(17, 18, 34));
        BUTTON_CARI.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BUTTON_CARI.setForeground(new java.awt.Color(204, 204, 204));
        BUTTON_CARI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_search.png"))); // NOI18N
        BUTTON_CARI.setText("Cari");
        BUTTON_CARI.setFocusable(false);
        BUTTON_CARI.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BUTTON_CARI.setMaximumSize(new java.awt.Dimension(60, 23));
        BUTTON_CARI.setMinimumSize(new java.awt.Dimension(60, 23));
        BUTTON_CARI.setPreferredSize(new java.awt.Dimension(80, 23));
        BUTTON_CARI.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BUTTON_CARI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUTTON_CARIActionPerformed(evt);
            }
        });
        jToolBar2.add(BUTTON_CARI);

        fokus_kelas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fokus_kelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kelas 10", "Kelas 11", "Kelas 12"}));
        fokus_kelas.setFocusable(false);
        fokus_kelas.setMinimumSize(new java.awt.Dimension(30, 20));
        fokus_kelas.setPreferredSize(new java.awt.Dimension(30, 20));
        fokus_kelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fokus_kelasActionPerformed(evt);
            }
        });
        jToolBar2.add(fokus_kelas);

        lihat.setBackground(new java.awt.Color(17, 18, 34));
        lihat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lihat.setForeground(new java.awt.Color(204, 204, 204));
        lihat.setText("Lihat Semua");
        lihat.setFocusable(false);
        lihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatActionPerformed(evt);
            }
        });
        jToolBar2.add(lihat);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (lihat.isSelected()){
            lihat.setSelected(false);
            
        }
        Tabel_Siswa();
        cari_nama.setText("");
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String nm = nis1.getText();
        if(isNullOrEmpty(nm)){
            JOptionPane.showMessageDialog(null, "klik baris tabel yang ingin dihapus");
        }else{
            int konfirmasi = JOptionPane.showConfirmDialog (null, "yakin anda ingin menghapus ?","Warning",dialogButton);
            if( konfirmasi == JOptionPane.YES_OPTION){
                try{
                    String sql ="delete from siswa where nis='"+nis1.getText()+"'";
                    pst=con.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(this, "data terhapus");
                }catch (Exception e){
                    JOptionPane.showMessageDialog(this, "data gagal di hapus"+e.getMessage());
                }
                Tabel_Siswa();
                Reset();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Form_Ubah_Siswa.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Form_Tambah_Siswa.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BUTTON_CARIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUTTON_CARIActionPerformed
        String kolom = cari_nama.getText();
        if (kolom.isEmpty() && lihat.isSelected()){
            lihat_semua_data();
        } else if (lihat.isSelected()){
            cari_nama_atau_tahun_tanpa_tahun_ajaran();
        } else if (kolom.isEmpty()){
            Tabel_Siswa();
        } else {
            cari_nama_atau_tahun_berdasarkan_tahun_ajaran_sekerang();
        }
       
          
    }//GEN-LAST:event_BUTTON_CARIActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Details.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO siswa(tahun,nis,nama_siswa,semester,kelas) VALUES(?,?,?,?,?)");
            ps.setString(1, tahun_masuk.getText());
            ps.setString(2, nis.getText());
            ps.setString(3, nama_siswa.getText());
            ps.setString(4, (String) semester.getSelectedItem());
            ps.setString(5, kelas.getText());
            ps.executeUpdate();            
            JOptionPane.showMessageDialog(null, "berhasil Berhasil");            
            Tabel_Siswa();            
            Reset();
            kode_nis();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Input"+e.getMessage());
        }
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        try {
            String sql ="UPDATE siswa SET tahun = '"+tahun1.getText()+"',"
                    + "nama_siswa = '"+nama_siswa1.getText()+"',"
                    + "semester = '"+semester1.getSelectedItem()+"',"
                    + "kelas = '"+kelas1.getText()+"'"                   
                    + " WHERE nis = '"+nis1.getText()+"'";
            pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Perubahan Data Berhasil");
            Tabel_Siswa();
            Reset();
            Form_Ubah_Siswa.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void fokus_kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fokus_kelasActionPerformed
        String kolom = cari_nama.getText();
        if (kolom.isEmpty() && lihat.isSelected()){
            cari_kelas_tanpa_tahun_ajaran();
        } else if (kolom.isEmpty()){
            fokus_kelas();
        } else {
            cari_kelas_tanpa_tahun_ajaran_berdasarkan_nama_atau_tahun();
        }
    }//GEN-LAST:event_fokus_kelasActionPerformed

    private void lihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatActionPerformed
        String kolom = cari_nama.getText();
        if (kolom.isEmpty() && lihat.isSelected()){
            lihat_semua_data();
        } else if (kolom.isEmpty()){
            Tabel_Siswa();
        } else if (lihat.isSelected()) {
            cari_nama_atau_tahun_tanpa_tahun_ajaran();
        } else {
            cari_nama_atau_tahun_berdasarkan_tahun_ajaran_sekerang();
        }
    }//GEN-LAST:event_lihatActionPerformed

    private void semesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semesterActionPerformed
        String smstr = (String) semester.getSelectedItem();
        
            
        switch (smstr) {
            case "Semester 1":
                kelas.setText("Kelas 10");
                break;
            case "Semester 2":
                kelas.setText("Kelas 10");
                break;
            case "Semester 3":
                kelas.setText("Kelas 11");
                break;
            case "Semester 4":
                kelas.setText("Kelas 11");
                break;
            case "Semester 5":
                kelas.setText("Kelas 12");
                break;
            case "Semester 6":
                kelas.setText("Kelas 12");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_semesterActionPerformed

    private void Tabel_SiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel_SiswaMouseClicked
        try{
            int row = Tabel_Siswa.getSelectedRow();
            String tbl_klik=(Tabel_Siswa.getModel().getValueAt(row, 1).toString());
            String sql ="select * from siswa where nis='"+tbl_klik+"' ";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                String a = rs.getString("tahun");
                tahun1.setText(a);

                String b = rs.getString("nis");
                nis1.setText(b);

                String c = rs.getString("nama_siswa");
                nama_siswa1.setText(c);

                String d = rs.getString("semester");
                semester1.setSelectedItem(d);

                String e = rs.getString("kelas");
                kelas1.setText(e);

            }
        } catch(Exception e){}

    }//GEN-LAST:event_Tabel_SiswaMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try
        {
            String namaFile = "src/Laporan/LaporanDataSiswa.jasper";
            HashMap param = new HashMap();
            param.put("kelas", fokus_kelas.getSelectedItem());
             param.put("tahun", tahun_ajaran.getText());
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
    private javax.swing.JButton BUTTON_CARI;
    private javax.swing.JDialog Details;
    private javax.swing.JDialog Form_Tambah_Siswa;
    private javax.swing.JDialog Form_Ubah_Siswa;
    private javax.swing.JTable Tabel_Siswa;
    private javax.swing.JTextField cari_nama;
    private javax.swing.JComboBox<String> fokus_kelas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTextField kelas;
    private javax.swing.JTextField kelas1;
    private javax.swing.JCheckBox lihat;
    private javax.swing.JTextField nama_siswa;
    private javax.swing.JTextField nama_siswa1;
    private javax.swing.JTextField nis;
    private javax.swing.JTextField nis1;
    private javax.swing.JComboBox<String> semester;
    private javax.swing.JComboBox<String> semester1;
    private javax.swing.JTextField tahun1;
    private javax.swing.JLabel tahun_ajaran;
    private javax.swing.JTextField tahun_masuk;
    // End of variables declaration//GEN-END:variables
}
