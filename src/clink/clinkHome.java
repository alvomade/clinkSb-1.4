
package clink;

import org.apache.commons.lang3.text.WordUtils;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class clinkHome extends javax.swing.JFrame {
    
    static String email, code, expiry, password=null;
    String bidStatus = "null";
    boolean suspended = false;
    WebDriver driver = null;
    private int x;
    private int y;
    
    Thread bidding = new Thread(new Runnable() {
        public void run() {
            try {
                 clink();              
            }
            catch(Exception t) {
                 // appropriate error reporting here
            } 
        }
    });
    
    public clinkHome(String email, String code, String expiry, String password) {
        super("Clink-home/"+email);
        this.email=email;
        this.code=code;
        this.expiry=expiry;
        this.password=password;
        

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        home_btnLaunch = new javax.swing.JButton();
        home_btnExit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(45, 45, 45));
        setForeground(new java.awt.Color(45, 45, 45));
        setResizable(false);
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

        jPanel1.setBackground(new java.awt.Color(19, 28, 59));
        jPanel1.setForeground(new java.awt.Color(19, 28, 59));

        home_btnLaunch.setBackground(new java.awt.Color(0, 204, 0));
        home_btnLaunch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        home_btnLaunch.setForeground(new java.awt.Color(255, 255, 255));
        home_btnLaunch.setText("START");
        home_btnLaunch.setBorderPainted(false);
        home_btnLaunch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                home_btnLaunchMousePressed(evt);
            }
        });
        home_btnLaunch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_btnLaunchActionPerformed(evt);
            }
        });

        home_btnExit.setBackground(new java.awt.Color(28, 39, 84));
        home_btnExit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        home_btnExit.setForeground(new java.awt.Color(255, 255, 255));
        home_btnExit.setText("EXIT");
        home_btnExit.setBorderPainted(false);
        home_btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_btnExitActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        displayLog.setEditable(false);
        displayLog.setBackground(new java.awt.Color(19, 28, 59));
        displayLog.setColumns(20);
        displayLog.setForeground(new java.awt.Color(255, 255, 255));
        displayLog.setRows(5);
        displayLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 0, 213)));
        jScrollPane2.setViewportView(displayLog);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(home_btnLaunch, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(home_btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(home_btnLaunch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(home_btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void home_btnLaunchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_btnLaunchActionPerformed
       
        if(bidStatus == "null"){
            bidStatus = "started";
            bidding.start();     
         }else if(bidStatus == "stopped"){
            bidStatus = "started";
            Thread bidding = new Thread(new Runnable() {
            public void run() {
            try {
                 clink();              
            }
            catch(Exception t) {
                 // appropriate error reporting here
            } 
                }
             });
            bidding.start();
            
         }else if(bidStatus == "started"){
            bidStatus = "stopped";
            bidding.stop();
            driver.close(); //closes the browser
            
         }
        
        
    }//GEN-LAST:event_home_btnLaunchActionPerformed

    private void home_btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_btnExitActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Do you want to Exit ?", "Exit Confirmation : ",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    this.dispose();
                } else if (result == JOptionPane.NO_OPTION) {
                    setDefaultCloseOperation(clinkHome.DO_NOTHING_ON_CLOSE);
                }
    }//GEN-LAST:event_home_btnExitActionPerformed

    private void home_btnLaunchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_btnLaunchMousePressed
        if(bidStatus == "null" || bidStatus == "stopped"){
            home_btnLaunch.setBackground(Color.red);
            home_btnLaunch.setText("STOP");
            displayLog.append("Happy Bidday\n");
            displayLog.append("Firing up clinkbot...\n");
        
        }else if(bidStatus == "started"){
            home_btnLaunch.setBackground(Color.green);
            home_btnLaunch.setText("START");
            displayLog.append("Stopping Bid...\n");
            displayLog.append("Bid stopped...\n");
            return;
        }
        
    }//GEN-LAST:event_home_btnLaunchMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x = evt.getXOnScreen()-this.x;
        int y = evt.getYOnScreen()-this.y;
        this.setLocation(x,y); 
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    protected void clink() throws InterruptedException, IOException{
        System.setProperty("webdriver.chrome.driver", "drv.exe");
//        System.setProperty("webdriver.chrome.driver", "drv.exe");

    //removes chrome default test notification
    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("useAutomationExtension", false);
    options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));   
//    options.addArguments("--headless");
    displayLog.append("Opening browser...\n");
    //driver instance created
    driver = new ChromeDriver(options);
    
    //get filter subjects
    String filter=Config.readFile("sare_hizo.txt");
     String [] filterArray=filter.split(",");
     
     int i=0;
     while(i<filterArray.length){
         filterArray[i]=WordUtils.capitalizeFully(filterArray[i]);
         i++;
     }

     
//    if(filter.isEmpty()){
//      filterArray.toString();
//
//    }else{
//       filterArray=filter.split(",");
//    }
    //System.out.println(filterArray[0]+filterArray[1]+filterArray[2]+filterArray[3]+filterArray[4]+filterArray[5]);
    
    //get config from file
    Config conf=new Config();
    String configSentence=conf.readFile("clink.txt");
    
    String configArray[]=configSentence.split(", *");
    displayLog.append("Loading configuration file...\n");
    
     //configs
     String priceLevel=configArray[0];
     int delay=Integer.parseInt(configArray[1]);
     String bidOn=configArray[2];
   
    
     
     
     
     displayLog.append("Signing in...\n");
     displayLog.append("Authenticating session...\n");
     
     //run bot
       
       
       
       if(Bot.login(driver,email,password)==true){
           displayLog.append("login successful...\n");
           displayLog.append("bidding...\n");
           Bot.bid(driver,delay, priceLevel,bidOn,filterArray, displayLog); 
           
       }else{
       displayLog.append("error, signing in, relaunch and enter the correct login details...\n");
       }
       
        
        
    }
    
    
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
                new clinkHome(email,code,expiry,password).setVisible(true);
            }
        });
    }
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea displayLog;
    private javax.swing.JButton home_btnExit;
    private javax.swing.JButton home_btnLaunch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
