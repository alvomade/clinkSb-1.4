
package clink;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import org.json.JSONArray;
import org.json.JSONObject;


public class clinkLogin extends javax.swing.JFrame {

    String dbEmail = null;
    String dbExpiry = null;
    String dbKey = null;
    private int x;
    private int y;
    
    
    public clinkLogin() {
        super("Login");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(null, "Do you want to Exit ?", "Exit Confirmation : ",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        setUndecorated(true);
        pack();
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        setSize(width / 3, height / 2);

        // here's the part where i center the jframe on screen
        setLocationRelativeTo(null);
        setVisible(true);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        main_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        login_btnSettings = new javax.swing.JButton();
        login_inputEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        login_inputPassword = new javax.swing.JPasswordField();
        login_btnLogin = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnExit1 = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 255));
        setForeground(new java.awt.Color(255, 0, 0));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        main_panel.setBackground(new java.awt.Color(19, 28, 59));
        main_panel.setForeground(new java.awt.Color(19, 28, 59));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(244, 244, 244));
        jLabel1.setText("Email");

        jLabel3.setBackground(new java.awt.Color(244, 244, 244));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(244, 244, 244));
        jLabel3.setText("LOGIN");

        login_btnSettings.setBackground(new java.awt.Color(28, 39, 84));
        login_btnSettings.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        login_btnSettings.setForeground(new java.awt.Color(255, 255, 255));
        login_btnSettings.setText("SETTINGS");
        login_btnSettings.setBorder(null);
        login_btnSettings.setBorderPainted(false);
        login_btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_btnSettingsActionPerformed(evt);
            }
        });

        login_inputEmail.setBackground(new java.awt.Color(19, 28, 59));
        login_inputEmail.setForeground(new java.awt.Color(244, 244, 244));
        login_inputEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 0, 213)));
        login_inputEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_inputEmailActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(244, 244, 244));
        jLabel2.setText("Password");

        login_inputPassword.setBackground(new java.awt.Color(19, 28, 59));
        login_inputPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        login_inputPassword.setForeground(new java.awt.Color(244, 244, 244));
        login_inputPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 0, 213)));

        login_btnLogin.setBackground(new java.awt.Color(28, 39, 84));
        login_btnLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        login_btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        login_btnLogin.setText("LAUNCH BOT");
        login_btnLogin.setBorder(null);
        login_btnLogin.setBorderPainted(false);
        login_btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_btnLoginActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(28, 39, 84));
        jPanel2.setToolTipText("[19,28,59]");
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(28, 39, 84));
        btnExit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("X");
        btnExit.setBorder(null);
        btnExit.setBorderPainted(false);
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(244, 244, 244));
        jLabel4.setText("Clink v1.4");

        btnExit1.setBackground(new java.awt.Color(28, 39, 84));
        btnExit1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExit1.setForeground(new java.awt.Color(255, 255, 255));
        btnExit1.setText("---");
        btnExit1.setBorder(null);
        btnExit1.setBorderPainted(false);
        btnExit1.setFocusPainted(false);
        btnExit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExit1MouseClicked(evt);
            }
        });
        btnExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(8, Short.MAX_VALUE))
            .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnExit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(215, 215, 215))
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(login_inputPassword)
                    .addComponent(login_inputEmail)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(login_btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(login_btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(login_inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(login_inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(login_btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(login_btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void login_btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_btnLoginActionPerformed
        String email = login_inputEmail.getText().trim();
        String pass = new String(login_inputPassword.getPassword());
        
        //new clinkHome(email, "1233", "12/12/2020",pass).setVisible(true);
       System.out.println(System.currentTimeMillis());
        
        if (email.equals("")) {
            JOptionPane.showMessageDialog(null, "Add A Email");
        } else if (!(isValid(email))) {
            JOptionPane.showMessageDialog(null, "Invalid Email");
        } else if (pass.equals("")) {
            JOptionPane.showMessageDialog(null, "Add A Password");
        } else {
            
//            PreparedStatement ps;
//            ResultSet rs;
//            
//            String query = "SELECT * FROM `subscription` WHERE `email` =?";
//
//            try {
//                ps = connection.getConnection().prepareStatement(query);
//                ps.setString(1, email);
//
//                rs = ps.executeQuery();
//
//                if (rs.next()) {
//                        dbEmail = rs.getString("email");
//                        dbKey = rs.getString("activation_key");
//                        dbExpiry = rs.getString("end");
        String activationCode=Config.readFile("activationCode.txt");
        
                try {
                            URL url = new URL("http://sb.clink.co.ke/getExpiry.php?act="+activationCode);
                            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                            httpURLConnection.setRequestMethod("GET");



                            String line="";
                            InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
                            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                            StringBuilder response=new StringBuilder();
                            while ((line=bufferedReader.readLine())!=null){
                                response.append(line);
                            }
                            bufferedReader.close();

                            //adding square brackets t0 make a valid json array
                            String datas="["+response.toString()+"]";
                            JSONArray jsonArray=new JSONArray(datas);
                            JSONObject jsonObject1 = jsonArray.getJSONObject(0);

                            int status=jsonObject1.getInt("status");

                            if(status==1){
                                dbExpiry=jsonObject1.getString("exp");
                            }else{
                                JOptionPane.showMessageDialog(null, "Your account is not registered with clink");
                            }





                        }
                        catch (Exception e){
                            System.out.println("Error in Making Get Request"+e);
                        }


                        int remaining = Activation.activationStatus(dbExpiry);
                        
                        //Boolean compare = email.equals(dbEmail);
                        Boolean compare =true;
                        
                        if(compare ==true && remaining > 0) {
                            JOptionPane.showMessageDialog(null, "Subscription is active,Expiry on"+dbExpiry+". proceed...");
                            //new clinkHome(dbEmail, dbKey, dbExpiry,pass).setVisible(true);
                            this.dispose();
                            new clinkHome(email,"1233",dbExpiry,pass).setVisible(true);
                            
                        }else if(compare = true && remaining < 1) {
                            JOptionPane.showMessageDialog(null, "Your Activation EXPIRED!");
                        }
//                        else{
//                            JOptionPane.showMessageDialog(null, "User Doesnt Exist! ");
//                        }
//  
//
//                } else {
//                JOptionPane.showMessageDialog(null, "User Doesnt Exist! ");
//                this.dispose();
//                }
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, ex);
//            }
        }
    }//GEN-LAST:event_login_btnLoginActionPerformed

    private void login_btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_btnSettingsActionPerformed
        new clinkSettings().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_login_btnSettingsActionPerformed

    private void login_inputEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_inputEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_login_inputEmailActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Do you want to Exit ?", "Exit Confirmation : ",
                        JOptionPane.YES_NO_OPTION);
        
                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    this.dispose();
                } else if (result == JOptionPane.NO_OPTION) {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExit1MouseClicked

    private void btnExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit1ActionPerformed
        this.setState(clinkLogin.ICONIFIED);
    }//GEN-LAST:event_btnExit1ActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x = evt.getXOnScreen()-this.x;
        int y = evt.getYOnScreen()-this.y;
        this.setLocation(x,y);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        int x = evt.getXOnScreen()-this.x;
        int y = evt.getYOnScreen()-this.y;
        this.setLocation(x,y);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());
    }//GEN-LAST:event_formWindowActivated

     public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
     
     public static void getCurrentDate(){
      
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
      if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
      }
    }
  } catch (ClassNotFoundException e) {
    e.printStackTrace();
  } catch (InstantiationException e) {
    e.printStackTrace();
  } catch (IllegalAccessException e) {
    e.printStackTrace();
  } catch (javax.swing.UnsupportedLookAndFeelException e) {
    e.printStackTrace();
  } catch (Exception e) {
    e.printStackTrace();
  }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new clinkLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExit1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton login_btnLogin;
    private javax.swing.JButton login_btnSettings;
    private javax.swing.JTextField login_inputEmail;
    private javax.swing.JPasswordField login_inputPassword;
    private javax.swing.JPanel main_panel;
    // End of variables declaration//GEN-END:variables
}
