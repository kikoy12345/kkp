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
public class guru_nilai extends javax.swing.JPanel {

    private Connection con;
    private Statement st;
    private DefaultTableModel tabmode;
    private ResultSet rs;
    private PreparedStatement pst;
    private int dialogButton;
    
    
    public guru_nilai() {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        st = DB.stm;
        tabel_nilai();
        tahun();
        
        Locale locale = new Locale ("id", "ID");
        Locale.setDefault(locale); 
        data_siswa.setLocationRelativeTo(null);
        data_siswa.pack();
        Form_Tambah_Nilai.setLocationRelativeTo(null);
        Form_Tambah_Nilai.pack();        
        Form_Ubah_Nilai.setLocationRelativeTo(null);
        Form_Ubah_Nilai.pack();    
        tabel_siswa();
        kelas();
        semester();
        
    }
    
    public void kelas(){
                   
        fokus.removeAllItems();
        kelas_combo.removeAllItems();
        try { 
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smk", "root", "");
             Statement statement = con.createStatement();
             String query = "select jadwal.kelas from jadwal, siswa where siswa.kelas=jadwal.kelas and jadwal.kode_mapel = '"+kode_mapel.getText()+"' and siswa.tahun = '"+tahun_ajaran.getText()+"' group by siswa.kelas";
             rs = statement.executeQuery(query);
             while (rs.next()){       
                fokus.addItem(rs.getString("kelas"));
                kelas_combo.addItem(rs.getString("kelas"));
             }
        }catch (Exception e){}
    }
    
    public void semester(){
                   
        semester.removeAllItems();
        try { 
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smk", "root", "");
             Statement statement = con.createStatement();
             String query = "select siswa.semester from siswa where nis='"+nis.getText()+"'";
             rs = statement.executeQuery(query);
             while (rs.next()){       
                semester.addItem(rs.getString("semester"));
             }
        }catch (Exception e){}
    }
  
     public void tahun(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int tahun = (year+1);
        
        String tahun_ajar = year+ "/" +tahun;
    
        tahun_ajaran.setText(tahun_ajar);        
        
        int month = Calendar.getInstance().get(Calendar.MONTH);
        
        bulan_semester.setText(String.valueOf(month));
        String smstr = (String) bulan_semester.getText();
        
        
    }
   
     
     
     
     public void tabel_siswa() {        
        Object[] baris = {"NIS","NAMA SISWA","KELAS","SEMESTER"};
        tabmode=new DefaultTableModel(null, baris);
        tabel_data_siswa.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql = "select siswa.nis, siswa.nama_siswa, jadwal.kelas, siswa.semester from jadwal, siswa where siswa.kelas = jadwal.kelas and siswa.tahun= '"+tahun_ajaran.getText()+"' and jadwal.kode_mapel='"+kode_mapel.getText()+"' and siswa.kelas='"+fokus.getSelectedItem()+"' group by siswa.nis";
       
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nis");
                String b = hasil.getString("nama_siswa");
                String c = hasil.getString("kelas");
                String d = hasil.getString("semester");
                String[]data={a,b,c,d};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }  
    }
     public void fokus_kelas() {        
        Object[] baris = {"NIS","NAMA SISWA","KELAS","SEMESTER"};
        tabmode=new DefaultTableModel(null, baris);
        tabel_data_siswa.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql = "select siswa.nis, siswa.nama_siswa, jadwal.kelas , siswa.semester from jadwal, siswa where siswa.kelas = jadwal.kelas and siswa.tahun= '"+tahun_ajaran.getText()+"' and jadwal.kode_mapel='"+kode_mapel.getText()+"' and siswa.kelas='"+kelas_combo.getSelectedItem()+"' group by siswa.nis";
       
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nis");
                String b = hasil.getString("nama_siswa");
                String c = hasil.getString("kelas");
                String d = hasil.getString("semester");
                String[]data={a,b,c,d};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }  
    }
     
    public void tabel_nilai() {
        
        Object[] baris = {"NIS","NAMA SISWA","KELAS","SEMESTER","NILAI HARIAN","NILAI TUGAS","NILAI PAT","NILAI PTS","NILAI AKHIR"};
        tabmode=new DefaultTableModel(null, baris);
        tabel_nilai.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql = "select siswa.nis, siswa.nama_siswa, jadwal.kelas, siswa.semester, nilai.nilai_harian,nilai.nilai_tugas,nilai.nilai_pat,nilai.nilai_pas, nilai.nilai_akhir from jadwal, siswa LEFT JOIN nilai ON nilai.nis = siswa.nis where siswa.kelas = jadwal.kelas and jadwal.tahun= '"+tahun_ajaran.getText()+"' and nilai.kode_mapel='"+kode_mapel.getText()+"' and siswa.kelas='"+fokus.getSelectedItem()+"' group by siswa.nis";
        
        
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nis");
                String b = hasil.getString("nama_siswa");
                String c = hasil.getString("kelas");
                String d = hasil.getString("semester");
                String e = hasil.getString("nilai_harian");  
                String f = hasil.getString("nilai_tugas"); 
                String g = hasil.getString("nilai_pat"); 
                String h = hasil.getString("nilai_pas");
                String i = hasil.getString("nilai_akhir");
                String[]data={a,b,c,d,e,f,g,h,i};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }  
    }
    
    public void fokus_nilai() {
        Object[] baris1 = {"NIS","NAMA SISWA","KELAS","SEMESTER","NILAI HARIAN","NILAI TUGAS","NILAI PAT","NILAI PTS","NILAI AKHIR"};
        tabmode=new DefaultTableModel(null, baris1);
        tabel_nilai.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql1 = "select siswa.nis, siswa.nama_siswa, jadwal.kelas, siswa.semester, nilai.nilai_harian,nilai.nilai_tugas,nilai.nilai_pat,nilai.nilai_pas, nilai.nilai_akhir from jadwal, siswa LEFT JOIN nilai ON nilai.nis = siswa.nis where siswa.kelas = jadwal.kelas and siswa.tahun= '"+tahun_ajaran.getText()+"' and nilai.kode_mapel='"+kode_mapel.getText()+"' and siswa.kelas='"+fokus.getSelectedItem()+"' group by siswa.nis";
        
        
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("nis");
                String b = hasil.getString("nama_siswa");
                String c = hasil.getString("kelas");
                String d = hasil.getString("semester");
                String e = hasil.getString("nilai_harian");  
                String f = hasil.getString("nilai_tugas"); 
                String g = hasil.getString("nilai_pat"); 
                String h = hasil.getString("nilai_pas");
                String i = hasil.getString("nilai_akhir");
                String[]data={a,b,c,d,e,f,g,h,i};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Form_Tambah_Nilai = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nis = new javax.swing.JTextField();
        nip = new javax.swing.JTextField();
        kode_mapel = new javax.swing.JTextField();
        nilai_harian = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        nilai_tugas = new javax.swing.JTextField();
        nilai_pat = new javax.swing.JTextField();
        nilai_pas = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        output_name = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        semester = new javax.swing.JComboBox<>();
        nilai_akhir = new javax.swing.JTextField();
        Form_Ubah_Nilai = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        nis1 = new javax.swing.JTextField();
        nip1 = new javax.swing.JTextField();
        kode_mapel1 = new javax.swing.JTextField();
        nilai_harian1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        nilai_tugas1 = new javax.swing.JTextField();
        nilai_pat1 = new javax.swing.JTextField();
        nilai_pas1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        semester1 = new javax.swing.JTextField();
        output_name1 = new javax.swing.JTextField();
        nilai_akhir1 = new javax.swing.JTextField();
        data_siswa = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_data_siswa = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        kelas_combo = new javax.swing.JComboBox<>();
        bulan_semester = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tahun_ajaran = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        fokus = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_nilai = new javax.swing.JTable();

        Form_Tambah_Nilai.setModal(true);
        Form_Tambah_Nilai.setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Tambah Data Nilai Siswa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Form_Tambah_Nilai.getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 50));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(51, 51, 51));
        jButton5.setText("Simpan");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setMaximumSize(new java.awt.Dimension(80, 21));
        jButton5.setMinimumSize(new java.awt.Dimension(80, 21));
        jButton5.setPreferredSize(new java.awt.Dimension(80, 21));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(338, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        Form_Tambah_Nilai.getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("NIP");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("NIS");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Kode Mata Plejaran");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Nilai Harian");

        nis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        nip.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nip.setEnabled(false);

        kode_mapel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kode_mapel.setEnabled(false);

        nilai_harian.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Nilai Tugas");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Nilai PAT");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Nilai PAS");

        nilai_tugas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        nilai_pat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        nilai_pas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nilai_pas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nilai_pasKeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Nilai Akhir");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Nama");

        output_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        output_name.setForeground(new java.awt.Color(0, 0, 51));
        output_name.setEnabled(false);
        output_name.setFocusable(false);
        output_name.setRequestFocusEnabled(false);

        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Pilih Semeter");

        semester.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        semester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6" }));
        semester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semesterActionPerformed(evt);
            }
        });

        nilai_akhir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nilai_akhir.setForeground(new java.awt.Color(0, 0, 51));
        nilai_akhir.setEnabled(false);
        nilai_akhir.setFocusable(false);
        nilai_akhir.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(output_name))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(nis, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(kode_mapel)
                            .addComponent(nip)
                            .addComponent(semester, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nilai_harian)
                            .addComponent(nilai_tugas)
                            .addComponent(nilai_pat)
                            .addComponent(nilai_pas)
                            .addComponent(nilai_akhir))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(output_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nis, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kode_mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semester, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilai_harian, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nilai_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilai_pat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilai_pas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nilai_akhir)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        Form_Tambah_Nilai.getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        Form_Ubah_Nilai.setResizable(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel6.setPreferredSize(new java.awt.Dimension(400, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Ubah Data Nilai Siswa");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Form_Ubah_Nilai.getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel7.setPreferredSize(new java.awt.Dimension(400, 50));

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton8.setText("Selesai");
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(310, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Form_Ubah_Nilai.getContentPane().add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("NIP");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("NIS");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Kode Mata Plejaran");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Nilai Harian");

        nis1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nis1.setEnabled(false);

        nip1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nip1.setEnabled(false);

        kode_mapel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kode_mapel1.setEnabled(false);

        nilai_harian1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Nilai Tugas");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Nilai PAT");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Nilai PAS");

        nilai_tugas1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        nilai_pat1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        nilai_pas1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nilai_pas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nilai_pas1KeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Niai Akhir");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Nama");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Semester");

        semester1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        semester1.setEnabled(false);

        output_name1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        output_name1.setEnabled(false);

        nilai_akhir1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nilai_akhir1.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nip1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(kode_mapel1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nilai_tugas1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nilai_pat1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nilai_pas1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(nilai_akhir1)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nis1)
                            .addComponent(output_name1)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nilai_harian1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(semester1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(output_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nis1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nip1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(kode_mapel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semester1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilai_harian1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilai_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilai_pat1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilai_pas1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nilai_akhir1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        Form_Ubah_Nilai.getContentPane().add(jPanel8, java.awt.BorderLayout.CENTER);

        data_siswa.setModal(true);
        data_siswa.setResizable(false);

        tabel_data_siswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_data_siswa.setRowHeight(30);
        tabel_data_siswa.setShowVerticalLines(false);
        tabel_data_siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_data_siswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_data_siswa);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Daftar Siswa");

        kelas_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        kelas_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kelas_comboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout data_siswaLayout = new javax.swing.GroupLayout(data_siswa.getContentPane());
        data_siswa.getContentPane().setLayout(data_siswaLayout);
        data_siswaLayout.setHorizontalGroup(
            data_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_siswaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(data_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addGroup(data_siswaLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(kelas_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        data_siswaLayout.setVerticalGroup(
            data_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_siswaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(data_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(kelas_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addContainerGap())
        );

        bulan_semester.setText("jLabel15");

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(37, 37, 37));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Data Nilai Siswa Tahun Ajaran");

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
        jButton1.setMaximumSize(new java.awt.Dimension(90, 25));
        jButton1.setMinimumSize(new java.awt.Dimension(90, 25));
        jButton1.setPreferredSize(new java.awt.Dimension(90, 25));
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
        jButton2.setMaximumSize(new java.awt.Dimension(90, 25));
        jButton2.setMinimumSize(new java.awt.Dimension(90, 25));
        jButton2.setPreferredSize(new java.awt.Dimension(90, 25));
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
        jButton3.setMaximumSize(new java.awt.Dimension(90, 25));
        jButton3.setMinimumSize(new java.awt.Dimension(90, 25));
        jButton3.setPreferredSize(new java.awt.Dimension(90, 25));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jButton11.setBackground(new java.awt.Color(37, 37, 37));
        jButton11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(204, 204, 204));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_reresh.png"))); // NOI18N
        jButton11.setText("Segarkan");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton11.setMaximumSize(new java.awt.Dimension(90, 25));
        jButton11.setMinimumSize(new java.awt.Dimension(90, 25));
        jButton11.setPreferredSize(new java.awt.Dimension(90, 25));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tahun_ajaran, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(17, 18, 34));

        fokus.setBackground(new java.awt.Color(17, 18, 34));
        fokus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fokus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        fokus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fokusActionPerformed(evt);
            }
        });

        tabel_nilai.setBackground(new java.awt.Color(236, 236, 236));
        tabel_nilai.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_nilai.setRowHeight(30);
        tabel_nilai.setShowVerticalLines(false);
        tabel_nilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_nilaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_nilai);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(fokus, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fokus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String a = nis.getText();
        if(isNullOrEmpty(a)){
            JOptionPane.showMessageDialog(null, "error !");
        }else{
            try{
            String sql = "select * from nilai where nis='"+nis.getText()+"' and kode_mapel='"+kode_mapel.getText()+"' and semester='"+semester.getSelectedItem()+"'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                if(nis.getText().equals(rs.getString("nis")) && kode_mapel.getText().equals(rs.getString("kode_mapel"))){
                           JOptionPane.showMessageDialog(null, "Maaf data sudah ada");                
                    } 
                }else{
                    PreparedStatement ps = con.prepareStatement("INSERT INTO nilai(tahun,nis, nip, kode_mapel, semester, nilai_harian, nilai_tugas, nilai_pat, nilai_pas, nilai_akhir) VALUES(?,?,?,?,?,?,?,?,?,?)");
                        ps.setString(1, tahun_ajaran.getText());
                        ps.setString(2, nis.getText());
                        ps.setString(3, nip.getText());
                        ps.setString(4, kode_mapel.getText());                
                        ps.setString(5, (String) semester.getSelectedItem());
                        ps.setString(6, nilai_harian.getText());
                        ps.setString(7, nilai_tugas.getText());
                        ps.setString(8, nilai_pat.getText());
                        ps.setString(9, nilai_pas.getText());
                        ps.setString(10, nilai_akhir.getText());
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data berhasil ditambah");            
                        fokus_nilai();                        
                }
                   
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Gagal Input"+e.getMessage());
            }
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            String sql ="UPDATE nilai SET nis = '"+nis1.getText()+"',"
            + "nilai_harian = '"+nilai_harian1.getText()+"',"
            + "nilai_tugas = '"+nilai_tugas1.getText()+"',"
            + "nilai_pat = '"+nilai_pat1.getText()+"',"
            + "nilai_pas = '"+nilai_pas1.getText()+"',"
            + "nilai_akhir = '"+nilai_akhir1.getText()+"'"
            + " WHERE nis = '"+nis1.getText()+"'";
            pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Perubahan Data Berhasil");
            
                tabel_nilai();
            
            Form_Ubah_Nilai.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void nilai_pasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nilai_pasKeyReleased
        String nilai_h = nilai_harian.getText();
        String nilai_t = nilai_tugas.getText();
        String nilai_pt = nilai_pat.getText();
        String nilai_ps = nilai_pas.getText();
        
        int a = Integer.parseInt(nilai_h);        
        int b = Integer.parseInt(nilai_t);        
        int c = Integer.parseInt(nilai_pt);        
        int d = Integer.parseInt(nilai_ps);
        
        float hasil = (a + b + c + d) / 4;
        
        nilai_akhir.setText(String.valueOf(hasil));
        
    }//GEN-LAST:event_nilai_pasKeyReleased

    private void nilai_pas1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nilai_pas1KeyReleased
        String nilai_h = nilai_harian1.getText();
        String nilai_t = nilai_tugas1.getText();
        String nilai_pt = nilai_pat1.getText();
        String nilai_ps = nilai_pas1.getText();
        
        int a = Integer.parseInt(nilai_h);        
        int b = Integer.parseInt(nilai_t);        
        int c = Integer.parseInt(nilai_pt);        
        int d = Integer.parseInt(nilai_ps);
        
        float hasil = (a + b + c + d) / 4;
        
        nilai_akhir1.setText(String.valueOf(hasil));
    }//GEN-LAST:event_nilai_pas1KeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        tabel_nilai();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Form_Ubah_Nilai.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Form_Tambah_Nilai.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void fokusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fokusActionPerformed
        fokus_nilai();
    }//GEN-LAST:event_fokusActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        tabel_siswa();
        data_siswa.setVisible(true);
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void tabel_data_siswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_data_siswaMouseClicked
        try{
            int row = tabel_data_siswa.getSelectedRow();
            String tbl_klik=(tabel_data_siswa.getModel().getValueAt(row, 0).toString());
            String sql ="select * from siswa where nis='"+tbl_klik+"' ";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                
                String b = rs.getString("nis");
                nis.setText(b);
                String c = rs.getString("nama_siswa");
                output_name.setText(c);
                
                String d = rs.getString("semester");
                semester.setSelectedItem(d);
                
            }
        } catch(Exception e){}
        data_siswa.dispose();
        semester();
    }//GEN-LAST:event_tabel_data_siswaMouseClicked

    private void kelas_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kelas_comboActionPerformed
        fokus_kelas();
    }//GEN-LAST:event_kelas_comboActionPerformed

    private void semesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semesterActionPerformed
        
    }//GEN-LAST:event_semesterActionPerformed

    private void tabel_nilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_nilaiMouseClicked
        try{
            int row = tabel_nilai.getSelectedRow();
            String tbl_klik=(tabel_nilai.getModel().getValueAt(row, 0).toString());
            String sql ="select siswa.nama_siswa, nilai.nis, nilai.semester, nilai.nilai_harian, nilai.nilai_tugas, nilai.nilai_pat, nilai.nilai_pas, nilai.nilai_akhir from siswa, nilai where siswa.nis=nilai.nis and nilai.kode_mapel='"+kode_mapel.getText()+"' and siswa.nis='"+tbl_klik+"' ";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                
                String b = rs.getString("nis");
                nis1.setText(b);
                String c = rs.getString("nama_siswa");
                output_name1.setText(c);
                String d = rs.getString("semester");
                semester1.setText(d);
                String e = rs.getString("nilai_harian");
                nilai_harian1.setText(e);
                String f = rs.getString("nilai_tugas");
                nilai_tugas1.setText(f);
                String g = rs.getString("nilai_pat");
                nilai_pat1.setText(g);
                String h = rs.getString("nilai_pas");
                nilai_pas1.setText(h);
                String i = rs.getString("nilai_akhir");
                nilai_akhir1.setText(i);
            }
        } catch(Exception e){}
    }//GEN-LAST:event_tabel_nilaiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Form_Tambah_Nilai;
    private javax.swing.JDialog Form_Ubah_Nilai;
    private javax.swing.JLabel bulan_semester;
    private javax.swing.JDialog data_siswa;
    private javax.swing.JComboBox<String> fokus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox<String> kelas_combo;
    public javax.swing.JTextField kode_mapel;
    public javax.swing.JTextField kode_mapel1;
    private javax.swing.JTextField nilai_akhir;
    private javax.swing.JTextField nilai_akhir1;
    private javax.swing.JTextField nilai_harian;
    private javax.swing.JTextField nilai_harian1;
    private javax.swing.JTextField nilai_pas;
    private javax.swing.JTextField nilai_pas1;
    private javax.swing.JTextField nilai_pat;
    private javax.swing.JTextField nilai_pat1;
    private javax.swing.JTextField nilai_tugas;
    private javax.swing.JTextField nilai_tugas1;
    public javax.swing.JTextField nip;
    public javax.swing.JTextField nip1;
    private javax.swing.JTextField nis;
    private javax.swing.JTextField nis1;
    private javax.swing.JTextField output_name;
    private javax.swing.JTextField output_name1;
    private javax.swing.JComboBox<String> semester;
    public javax.swing.JTextField semester1;
    private javax.swing.JTable tabel_data_siswa;
    private javax.swing.JTable tabel_nilai;
    private javax.swing.JLabel tahun_ajaran;
    // End of variables declaration//GEN-END:variables
}
