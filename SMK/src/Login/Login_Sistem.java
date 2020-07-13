
package Login;

import Menu.guru_nilai;
import Tampilan.Tampilan_Admin;
import Tampilan.Tampilan_Guru;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.koneksi;

public class Login_Sistem extends javax.swing.JFrame {

    String jradio;
    private final Connection con;
    private final Statement st;
    private ResultSet rs;
    public PreparedStatement pst;
    
    public Login_Sistem() {
        initComponents();
        
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        st = DB.stm;        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        nav = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        footer = new javax.swing.JPanel();
        main = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nip = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        admin = new javax.swing.JRadioButton();
        guru = new javax.swing.JRadioButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 500));
        setResizable(false);

        nav.setBackground(new java.awt.Color(17, 18, 34));
        nav.setPreferredSize(new java.awt.Dimension(828, 120));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SMK CAKRA NUSANTARA");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/logo-small1.png"))); // NOI18N

        javax.swing.GroupLayout navLayout = new javax.swing.GroupLayout(nav);
        nav.setLayout(navLayout);
        navLayout.setHorizontalGroup(
            navLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        navLayout.setVerticalGroup(
            navLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(nav, java.awt.BorderLayout.PAGE_START);

        footer.setBackground(new java.awt.Color(51, 51, 51));
        footer.setPreferredSize(new java.awt.Dimension(828, 30));

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        main.setBackground(new java.awt.Color(17, 18, 34));
        main.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(17, 18, 34));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Masuk Sistem");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("NIP");

        nip.setBackground(new java.awt.Color(37, 37, 77));
        nip.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nip.setForeground(new java.awt.Color(204, 204, 204));
        nip.setCaretColor(new java.awt.Color(0, 204, 51));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Kata Sandi");

        admin.setBackground(new java.awt.Color(17, 18, 34));
        group.add(admin);
        admin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        admin.setForeground(new java.awt.Color(204, 204, 204));
        admin.setText("Admin");
        admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminActionPerformed(evt);
            }
        });

        guru.setBackground(new java.awt.Color(17, 18, 34));
        group.add(guru);
        guru.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        guru.setForeground(new java.awt.Color(204, 204, 204));
        guru.setText("Guru / Wali Kelas");
        guru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guruActionPerformed(evt);
            }
        });

        jToolBar1.setBackground(new java.awt.Color(17, 18, 34));
        jToolBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton1.setBackground(new java.awt.Color(17, 18, 34));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 204, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_white/gradient_login.png"))); // NOI18N
        jButton1.setText("Masuk");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.setMaximumSize(new java.awt.Dimension(100, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(100, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        password.setBackground(new java.awt.Color(37, 37, 77));
        password.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        password.setForeground(new java.awt.Color(204, 204, 204));
        password.setCaretColor(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 103, Short.MAX_VALUE)
                        .addComponent(guru))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nip)
                    .addComponent(password)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(admin)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nip, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admin)
                    .addComponent(guru))
                .addGap(18, 18, 18)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        main.add(jPanel2, new java.awt.GridBagConstraints());

        getContentPane().add(main, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(admin.isSelected()){
            try {
            String sql = "SELECT * FROM admin WHERE nip='"+nip.getText()+"' AND password='"+password.getText()+"' AND sebagai = '"+jradio+"'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                Tampilan_Admin ad = new Tampilan_Admin(); 
                String a = rs.getString("nama_admin");
                ad.admin.setText(a);
                if(nip.getText().equals(rs.getString("nip")) && password.getText().equals(rs.getString("password")) && jradio.equals(rs.getString("sebagai"))){
                                         
                        ad.setVisible(true);
                        this.setVisible(false);
                                            
                    } 
                }else{
                    JOptionPane.showMessageDialog(null, "Maaf anda bukan seorang Admin");
                }            
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Tidak terhubung ke server " +e.getMessage());
            }
        } else if (guru.isSelected()){
            try {
            String sql = "SELECT * FROM guru WHERE nip='"+nip.getText()+"'  AND sebagai = '"+jradio+"' AND password='"+password.getText()+"'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                Tampilan_Guru d = new Tampilan_Guru(); 
                if(nip.getText().equals(rs.getString("nip")) && password.getText().equals(rs.getString("password")) && jradio.equals(rs.getString("sebagai"))){
                                          
                        d.setVisible(true);
                        this.setVisible(false);
                        //d.guru.setText(this.password.getText());                         
                        d.akun_nip.setText(this.nip.getText());
                        //d.akun_nama.setText(this.password.getText());
                        
                        guru_nilai gs = new guru_nilai();
                        gs.nip.setText(this.nip.getText());
                    } 
                String nama = rs.getString("nama_guru");
                d.guru.setText(nama);
                
                }else{
                    JOptionPane.showMessageDialog(null, "Maaf anda bukan seorang Guru");
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Tidak terhubung ke server " +e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan Pilih jenis login Admin atau Guru");
        }
        
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminActionPerformed
        jradio = "admin";
    }//GEN-LAST:event_adminActionPerformed

    private void guruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guruActionPerformed
         jradio = "guru";
    }//GEN-LAST:event_guruActionPerformed

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
            java.util.logging.Logger.getLogger(Login_Sistem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_Sistem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_Sistem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_Sistem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_Sistem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton admin;
    private javax.swing.JPanel footer;
    private javax.swing.ButtonGroup group;
    private javax.swing.JRadioButton guru;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel main;
    private javax.swing.JPanel nav;
    private javax.swing.JTextField nip;
    private javax.swing.JPasswordField password;
    // End of variables declaration//GEN-END:variables
}
