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


public class admin_nilai_siswa extends javax.swing.JPanel {

    private Connection con;
    private Statement st;
    private DefaultTableModel tabmode;
    private ResultSet rs;
    private PreparedStatement pst;
    private int dialogButton;
    
    public admin_nilai_siswa() {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        st = DB.stm;
        Tabel_Siswa();
        Tabel_Mapel();
        
        Locale locale = new Locale ("id", "ID");
        Locale.setDefault(locale); 
        
        
        Data_Mapel.setLocationRelativeTo(null);
        Data_Mapel.setVisible(false);
        Data_Mapel.pack();
        
        tahun();
        tahun_ajar();
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
    public void tahun(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int tahun = (year+1);
        
        String tahun_ajar = year+ "/" +tahun;
    
        tahun_ajaran.setText(tahun_ajar);
    }
    public void Tabel_Siswa() {
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS","NILAI HARIAN", "NILAI TUGAS", "NILAI PAT", "NILAI PTS","NILAI ALHIR","MATA PELAJARAN"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, siswa.kelas, nilai.nilai_harian, nilai.nilai_tugas, nilai.nilai_pat, nilai.nilai_pas, nilai.nilai_akhir, mapel.mapel from siswa LEFT JOIN nilai ON nilai.nis = siswa.nis LEFT JOIN mapel ON mapel.kode_mapel = nilai.kode_mapel where siswa.tahun = '"+tahun_ajaran.getText()+"'";
        
        
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String f = hasil.getString("nilai_harian");  
                String g = hasil.getString("nilai_tugas"); 
                String h = hasil.getString("nilai_pat"); 
                String i = hasil.getString("nilai_pas");
                String j = hasil.getString("nilai_akhir");
                String k = hasil.getString("mapel");   
                String[]data={a,b,c,d,e,f,g,h,i,j,k};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }    
    }
 
    public void fokus_kelas() {
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS","NILAI HARIAN", "NILAI TUGAS", "NILAI PAT", "NILAI PTS","NILAI ALHIR","MATA PELAJARAN"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, siswa.kelas, nilai.nilai_harian, nilai.nilai_tugas, nilai.nilai_pat, nilai.nilai_pas, nilai.nilai_akhir, mapel.mapel from siswa LEFT JOIN nilai ON nilai.nis = siswa.nis LEFT JOIN mapel ON mapel.kode_mapel = nilai.kode_mapel where siswa.kelas = '"+fokus_kelas.getSelectedItem()+"' and siswa.tahun = '"+tahun_ajaran.getText()+"'";
        
        
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String f = hasil.getString("nilai_harian");  
                String g = hasil.getString("nilai_tugas"); 
                String h = hasil.getString("nilai_pat"); 
                String i = hasil.getString("nilai_pas");
                String j = hasil.getString("nilai_akhir");
                String k = hasil.getString("mapel");   
                String[]data={a,b,c,d,e,f,g,h,i,j,k};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "Maaf data beluma ada "+e.getMessage());
        }    
    }
    
    public void cari_nis_atau_tahun_fokus_kelas() {
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS","NILAI HARIAN", "NILAI TUGAS", "NILAI PAT", "NILAI PTS","NILAI ALHIR","MATA PELAJARAN"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, siswa.kelas, nilai.nilai_harian, nilai.nilai_tugas, nilai.nilai_pat, nilai.nilai_pas, nilai.nilai_akhir, mapel.mapel from siswa LEFT JOIN nilai ON nilai.nis = siswa.nis LEFT JOIN mapel ON mapel.kode_mapel = nilai.kode_mapel where siswa.kelas = '"+fokus_kelas.getSelectedItem()+"' and (siswa.tahun = '"+cari_nama.getText()+"' OR nilai.nis = '"+cari_nama.getText()+"')";
        
        
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String f = hasil.getString("nilai_harian");  
                String g = hasil.getString("nilai_tugas"); 
                String h = hasil.getString("nilai_pat"); 
                String i = hasil.getString("nilai_pas");
                String j = hasil.getString("nilai_akhir");
                String k = hasil.getString("mapel");   
                String[]data={a,b,c,d,e,f,g,h,i,j,k};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "Maaf data beluma ada "+e.getMessage());
        }    
    }
    
    public void fokus_kelas_tanpa_tahun() {
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS","NILAI HARIAN", "NILAI TUGAS", "NILAI PAT", "NILAI PTS","NILAI ALHIR","MATA PELAJARAN"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, siswa.kelas, nilai.nilai_harian, nilai.nilai_tugas, nilai.nilai_pat, nilai.nilai_pas, nilai.nilai_akhir, mapel.mapel from siswa LEFT JOIN nilai ON nilai.nis = siswa.nis LEFT JOIN mapel ON mapel.kode_mapel = nilai.kode_mapel where siswa.kelas = '"+fokus_kelas.getSelectedItem()+"'";
        
        
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String f = hasil.getString("nilai_harian");  
                String g = hasil.getString("nilai_tugas"); 
                String h = hasil.getString("nilai_pat"); 
                String i = hasil.getString("nilai_pas");
                String j = hasil.getString("nilai_akhir");
                String k = hasil.getString("mapel");   
                String[]data={a,b,c,d,e,f,g,h,i,j,k};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "Maaf data beluma ada "+e.getMessage());
        }    
    }
    
    public void data_seluruh_siswa() {
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS","NILAI HARIAN", "NILAI TUGAS", "NILAI PAT", "NILAI PTS","NILAI ALHIR","MATA PELAJARAN"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, siswa.kelas, nilai.nilai_harian, nilai.nilai_tugas, nilai.nilai_pat, nilai.nilai_pas, nilai.nilai_akhir, mapel.mapel from siswa LEFT JOIN nilai ON nilai.nis = siswa.nis LEFT JOIN mapel ON mapel.kode_mapel = nilai.kode_mapel";
        
        
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String f = hasil.getString("nilai_harian");  
                String g = hasil.getString("nilai_tugas"); 
                String h = hasil.getString("nilai_pat"); 
                String i = hasil.getString("nilai_pas");
                String j = hasil.getString("nilai_akhir");
                String k = hasil.getString("mapel");   
                String[]data={a,b,c,d,e,f,g,h,i,j,k};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }    
    }
    public void cari_nis_atau_tahun_berdasarkan_kelas_dan_tahun_ajaran_sekarang() {
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS","NILAI HARIAN", "NILAI TUGAS", "NILAI PAT", "NILAI PTS","NILAI ALHIR","MATA PELAJARAN"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, siswa.kelas, nilai.nilai_harian, nilai.nilai_tugas, nilai.nilai_pat, nilai.nilai_pas, nilai.nilai_akhir, mapel.mapel from siswa LEFT JOIN nilai ON nilai.nis = siswa.nis LEFT JOIN mapel ON mapel.kode_mapel = nilai.kode_mapel where siswa.kelas = '"+fokus_kelas.getSelectedItem()+"' and siswa.tahun = '"+tahun_ajaran.getText()+"' and (siswa.tahun = '"+cari_nama.getText()+"' OR nilai.nis = '"+cari_nama.getText()+"')";
        
        
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String f = hasil.getString("nilai_harian");  
                String g = hasil.getString("nilai_tugas"); 
                String h = hasil.getString("nilai_pat"); 
                String i = hasil.getString("nilai_pas");
                String j = hasil.getString("nilai_akhir");
                String k = hasil.getString("mapel");   
                String[]data={a,b,c,d,e,f,g,h,i,j,k};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "Maaf data beluma ada "+e.getMessage());
        }    
    }
    
    public void cari_nis_tahun_ajaran_sekarang() {
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS","NILAI HARIAN", "NILAI TUGAS", "NILAI PAT", "NILAI PTS","NILAI ALHIR","MATA PELAJARAN"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, siswa.kelas, nilai.nilai_harian, nilai.nilai_tugas, nilai.nilai_pat, nilai.nilai_pas, nilai.nilai_akhir, mapel.mapel from siswa LEFT JOIN nilai ON nilai.nis = siswa.nis LEFT JOIN mapel ON mapel.kode_mapel = nilai.kode_mapel where siswa.tahun = '"+tahun_ajaran.getText()+"' and (siswa.tahun = '"+cari_nama.getText()+"' OR nilai.nis = '"+cari_nama.getText()+"')";
        
        
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String f = hasil.getString("nilai_harian");  
                String g = hasil.getString("nilai_tugas"); 
                String h = hasil.getString("nilai_pat"); 
                String i = hasil.getString("nilai_pas");
                String j = hasil.getString("nilai_akhir");
                String k = hasil.getString("mapel");   
                String[]data={a,b,c,d,e,f,g,h,i,j,k};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "Maaf data beluma ada "+e.getMessage());
        }    
    }
    
    public void cari_nama_atau_tahun() {
        Object[] baris1 = {"TAHUN AJARAN","NIS","NAMA SISWA","SEMESTER","KELAS","NILAI HARIAN", "NILAI TUGAS", "NILAI PAT", "NILAI PTS","NILAI ALHIR","MATA PELAJARAN"};
        tabmode=new DefaultTableModel(null, baris1);
        Tabel_Siswa.setModel(tabmode);
        //String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, jadwal.kelas, nilai.nilai from siswa LEFT JOIN nilai ON siswa.nis = nilai.nis AND tahun = '"+tahun.getText()+"' AND kode_mapel = '"+kode_mapel.getText()+"'";
        String sql1 = "select siswa.tahun, siswa.nis, siswa.nama_siswa, siswa.semester, siswa.kelas, nilai.nilai_harian, nilai.nilai_tugas, nilai.nilai_pat, nilai.nilai_pas, nilai.nilai_akhir, mapel.mapel from siswa LEFT JOIN nilai ON nilai.nis = siswa.nis LEFT JOIN mapel ON mapel.kode_mapel = nilai.kode_mapel where (siswa.tahun = '"+cari_nama.getText()+"' OR nilai.nis = '"+cari_nama.getText()+"')";
        
        
        try{
            java.sql.Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()){
                String a = hasil.getString("tahun");
                String b = hasil.getString("nis");  
                String c = hasil.getString("nama_siswa");  
                String d = hasil.getString("semester");  
                String e = hasil.getString("kelas");   
                String f = hasil.getString("nilai_harian");  
                String g = hasil.getString("nilai_tugas"); 
                String h = hasil.getString("nilai_pat"); 
                String i = hasil.getString("nilai_pas");
                String j = hasil.getString("nilai_akhir");
                String k = hasil.getString("mapel");   
                String[]data={a,b,c,d,e,f,g,h,i,j,k};
                tabmode.addRow(data);
            }
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "error tidak ada data tabel siswa "+e.getMessage());
        }    
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Data_Mapel = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel_Mapel = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tahun_ajaran = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        tahun_ajar = new javax.swing.JComboBox<>();
        kode_mapel = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        kelas = new javax.swing.JComboBox<>();
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
        Tabel_Mapel.setRowHeight(30);
        Tabel_Mapel.setShowVerticalLines(false);
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

        setAutoscrolls(true);
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(37, 37, 37));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1062, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Data Nilai Tahun Ajaran");

        tahun_ajaran.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tahun_ajaran.setForeground(new java.awt.Color(204, 204, 204));
        tahun_ajaran.setText("2000");

        jToolBar1.setBackground(new java.awt.Color(37, 37, 37));
        jToolBar1.setFloatable(false);

        tahun_ajar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        tahun_ajar.setMaximumSize(new java.awt.Dimension(150, 27));
        tahun_ajar.setMinimumSize(new java.awt.Dimension(150, 27));
        tahun_ajar.setPreferredSize(new java.awt.Dimension(150, 27));
        jToolBar1.add(tahun_ajar);

        kode_mapel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kode_mapel.setMaximumSize(new java.awt.Dimension(150, 27));
        kode_mapel.setMinimumSize(new java.awt.Dimension(150, 27));
        kode_mapel.setPreferredSize(new java.awt.Dimension(150, 27));
        jToolBar1.add(kode_mapel);

        jButton3.setBackground(new java.awt.Color(37, 37, 37));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_add.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMaximumSize(new java.awt.Dimension(27, 27));
        jButton3.setMinimumSize(new java.awt.Dimension(27, 27));
        jButton3.setPreferredSize(new java.awt.Dimension(27, 27));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        kelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kelas 10", "Kelas 11", "Kelas 12" }));
        kelas.setMaximumSize(new java.awt.Dimension(150, 27));
        kelas.setMinimumSize(new java.awt.Dimension(150, 27));
        kelas.setPreferredSize(new java.awt.Dimension(150, 27));
        jToolBar1.add(kelas);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tahun_ajaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE)))
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
        jScrollPane1.setViewportView(Tabel_Siswa);

        jToolBar2.setBackground(new java.awt.Color(17, 18, 34));
        jToolBar2.setFloatable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Cari Nama / Tahun Ajaran");
        jLabel6.setMaximumSize(new java.awt.Dimension(150, 23));
        jLabel6.setMinimumSize(new java.awt.Dimension(150, 23));
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 23));
        jToolBar2.add(jLabel6);

        cari_nama.setBackground(new java.awt.Color(37, 37, 77));
        cari_nama.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cari_nama.setForeground(new java.awt.Color(204, 204, 204));
        cari_nama.setCaretColor(new java.awt.Color(0, 204, 51));
        cari_nama.setMaximumSize(new java.awt.Dimension(200, 23));
        cari_nama.setMinimumSize(new java.awt.Dimension(200, 23));
        cari_nama.setPreferredSize(new java.awt.Dimension(200, 23));
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
        BUTTON_CARI.setPreferredSize(new java.awt.Dimension(60, 23));
        BUTTON_CARI.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BUTTON_CARI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUTTON_CARIActionPerformed(evt);
            }
        });
        jToolBar2.add(BUTTON_CARI);

        fokus_kelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kelas 10", "Kelas 11", "Kelas 12"}));
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
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
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

    private void BUTTON_CARIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUTTON_CARIActionPerformed
        String kolom = cari_nama.getText();
        if (kolom.isEmpty() && lihat.isSelected()){
            cari_nama_atau_tahun();
        } else if (kolom.isEmpty()){
            Tabel_Siswa();
        } else if (lihat.isSelected()){
            cari_nama_atau_tahun();
        } else {
            cari_nis_tahun_ajaran_sekarang();
        }
    }//GEN-LAST:event_BUTTON_CARIActionPerformed

    private void fokus_kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fokus_kelasActionPerformed
        String kolom = cari_nama.getText();
        if(kolom.isEmpty() && lihat.isSelected()){
            fokus_kelas_tanpa_tahun();
        } else if (kolom.isEmpty()){
            fokus_kelas();
        } else {
            cari_nis_atau_tahun_fokus_kelas();
        }
        
    }//GEN-LAST:event_fokus_kelasActionPerformed

    private void lihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatActionPerformed
        String kolom = cari_nama.getText();
        if (kolom.isEmpty() && lihat.isSelected()){
            data_seluruh_siswa();
        } else if (kolom.isEmpty()){
            Tabel_Siswa();
        } else if (lihat.isSelected()) {
            cari_nama_atau_tahun();
        } else {
            cari_nis_tahun_ajaran_sekarang();
        }
    }//GEN-LAST:event_lihatActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       try
        {
            String namaFile = "src/Laporan/LaporanNilai.jasper";
            HashMap param = new HashMap();
            param.put("tahun", tahun_ajaran.getText());
             param.put("kelas", kelas.getSelectedItem());
             param.put("kode_mapel", kode_mapel.getText());
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


            }
        } catch(Exception e){}
        Data_Mapel.dispose();
    }//GEN-LAST:event_Tabel_MapelMouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        Data_Mapel.dispose();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void Data_MapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Data_MapelMouseClicked

    }//GEN-LAST:event_Data_MapelMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Data_Mapel.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BUTTON_CARI;
    private javax.swing.JDialog Data_Mapel;
    private javax.swing.JTable Tabel_Mapel;
    private javax.swing.JTable Tabel_Siswa;
    private javax.swing.JTextField cari_nama;
    private javax.swing.JComboBox<String> fokus_kelas;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JComboBox<String> kelas;
    private javax.swing.JTextField kode_mapel;
    private javax.swing.JCheckBox lihat;
    private javax.swing.JComboBox<String> tahun_ajar;
    private javax.swing.JLabel tahun_ajaran;
    // End of variables declaration//GEN-END:variables

    private void tahun_ajar() {
    try { 
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smk", "root", "");
             Statement statement = con.createStatement();
             String query = "select * from siswa group by tahun";
             rs = statement.executeQuery(query);
             while (rs.next()){       
                tahun_ajar.addItem(rs.getString("tahun"));
             }
        }catch (Exception e){}
    }
}
