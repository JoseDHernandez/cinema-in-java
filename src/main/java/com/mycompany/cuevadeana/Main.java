/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cuevadeana;

import Classes.User;
import Templates.DebugWindow;
import Templates.List_movies;
import Templates.Login;
import Templates.RegisterShowtimes;
import Templates.RegisterUser;
import Templates.RegisterMovies;
import Templates.Seats;
import javax.swing.JPanel;
import org.bson.Document;

/**
 *
 * @author Jose
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    //Variables globales
    private User userData = new User();
    private Mongo mongoDB = null;
    //Fin variables globales

    public Main() {
        initComponents();
        enableButtons(false);//Deshabilitar botones
        PanelOptions.setVisible(false);

        login();
        //venta();
        //registerShowtime();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Button1 = new javax.swing.JButton();
        Button2 = new javax.swing.JButton();
        MasterPanel = new javax.swing.JPanel();
        PanelOptions = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        URIMongo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        DBName = new javax.swing.JTextField();
        BtSave = new javax.swing.JButton();
        Button3 = new javax.swing.JButton();
        Opciones = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        UserNameTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 102, 102));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 768));

        Button1.setBackground(new java.awt.Color(255, 153, 153));
        Button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button1.setForeground(new java.awt.Color(255, 255, 255));
        Button1.setText(" ");
        Button1.setBorder(null);
        Button1.setBorderPainted(false);
        Button1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button1MouseClicked(evt);
            }
        });

        Button2.setBackground(new java.awt.Color(255, 153, 153));
        Button2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button2.setForeground(new java.awt.Color(255, 255, 255));
        Button2.setText("Lista");
        Button2.setBorder(null);
        Button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button2MouseClicked(evt);
            }
        });

        MasterPanel.setBackground(new java.awt.Color(255, 255, 255));
        MasterPanel.setPreferredSize(new java.awt.Dimension(910, 630));

        jLabel4.setText("Opciones");

        jLabel6.setText("URI of mongo");

        jLabel7.setText("Database name");

        BtSave.setText("Save");
        BtSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtSaveMouseClicked(evt);
            }
        });
        BtSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelOptionsLayout = new javax.swing.GroupLayout(PanelOptions);
        PanelOptions.setLayout(PanelOptionsLayout);
        PanelOptionsLayout.setHorizontalGroup(
            PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelOptionsLayout.createSequentialGroup()
                        .addGroup(PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelOptionsLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(URIMongo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelOptionsLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DBName)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOptionsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(BtSave)
                                .addGap(98, 98, 98))))))
        );
        PanelOptionsLayout.setVerticalGroup(
            PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOptionsLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(URIMongo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(DBName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtSave)
                .addContainerGap())
        );

        javax.swing.GroupLayout MasterPanelLayout = new javax.swing.GroupLayout(MasterPanel);
        MasterPanel.setLayout(MasterPanelLayout);
        MasterPanelLayout.setHorizontalGroup(
            MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MasterPanelLayout.createSequentialGroup()
                .addGap(616, 616, 616)
                .addComponent(PanelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );
        MasterPanelLayout.setVerticalGroup(
            MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MasterPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(PanelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Button3.setBackground(new java.awt.Color(255, 153, 153));
        Button3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button3.setForeground(new java.awt.Color(255, 255, 255));
        Button3.setText("Venta");
        Button3.setBorder(null);
        Button3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button3MouseClicked(evt);
            }
        });

        Opciones.setText("Opciones");
        Opciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OpcionesMouseClicked(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));

        jLabel5.setFont(new java.awt.Font("Ebrima", 3, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("La cueva de ana");

        UserNameTitle.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        UserNameTitle.setForeground(new java.awt.Color(255, 255, 255));
        UserNameTitle.setText("  ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                    .addComponent(UserNameTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(UserNameTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(Button2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Button3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(MasterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MasterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    JPanel actualScenne = null;

    private void changeScenne(JPanel scenne) {
        actualScenne = scenne; //910 *630
        MasterPanel.removeAll();
        MasterPanel.add(scenne);
        scenne.setSize(MasterPanel.getWidth(), MasterPanel.getHeight());
        MasterPanel.revalidate();
        MasterPanel.repaint();
    }

    private void registerShowtime() {
        RegisterShowtimes re = new RegisterShowtimes(mongoDB);
        changeScenne(re);
    }

    private void venta() {
        Seats v = new Seats(mongoDB, userData);
        changeScenne(v);
    }

    private void registerMovie() {
        RegisterMovies registerMovie = new RegisterMovies(mongoDB);
        changeScenne(registerMovie);
    }
    private void Button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button1MouseClicked
        // TODO add your handling code here:
        if (userData.getRol().equals("Administrador")) {
            registerMovie();
        } else {
            venta();
        }
    }//GEN-LAST:event_Button1MouseClicked

    private void listMovies() {
        List_movies list_movies = new List_movies(mongoDB);
        changeScenne(list_movies);
    }

    private void registerUser() {
        RegisterUser newUser = new RegisterUser(mongoDB);
        changeScenne(newUser);
    }
    private void Button2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button2MouseClicked
        // TODO add your handling code here:
        if (userData.getRol().equals("Administrador")) {
            registerShowtime();
        } else {
            listMovies();
        }
    }//GEN-LAST:event_Button2MouseClicked

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        if (actualScenne != null) {
            actualScenne.setSize(MasterPanel.getWidth(), MasterPanel.getHeight());
            MasterPanel.revalidate();
            MasterPanel.repaint();
        }
    }//GEN-LAST:event_formComponentResized

    public void login(String username, String pass, boolean menuType) {
        //Establecer coneccion con mongoDB
        mongoDB = new Mongo();
        //Validar usuario
        Document userData = mongoDB.findUser(OptionsData.getInstance().getUser().hashPassword(pass), username);
        if (userData.getString("Password").equals(OptionsData.getInstance().getUser().hashPassword(pass))) {

            User user = new User();
            user.setId(userData.getObjectId("_id").toString());
            user.setUserName(userData.getString("UserName"));
            user.setName(userData.getString("Name"));
            user.setIdentification(userData.getString("Identification"));
            user.setRol(userData.getString("Rol"));
            if (user.getRol().equals("Cajero")) {
                user.setCashRegister(userData.getString("CashRegister"));
            }
            this.userData = user;
            //Cambiar botones segun el cargo
            Button1.setText(user.getRol().equals("Administrador") ? "Registrar" : "Venta");
            Button2.setText(user.getRol().equals("Administrador") ? "Funciones" : "Peliculas");
            Button3.setText(user.getRol().equals("Administrador") ? "Usuarios" : " ");
            //
            UserNameTitle.setText(user.getName());
            enableButtons(true);
            listMovies();
        } else {
            DebugWindow window = new DebugWindow();
            window.newWindow("warning", "Usuario invalido", "Error de ingreso");
        }
    }
    private void Button3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button3MouseClicked
        // TODO add your handling code here:
        if (userData.getRol().equals("Administrador")) {
            registerUser();
        } else {

        }
    }//GEN-LAST:event_Button3MouseClicked

    private void OpcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpcionesMouseClicked
        // TODO add your handling code here:
        PanelOptions.setVisible(true);
    }//GEN-LAST:event_OpcionesMouseClicked

    private void BtSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtSaveMouseClicked
        // TODO add your handling code here:
        OptionsData.getInstance().setURI(URIMongo.getText().trim());
        OptionsData.getInstance().setDBName(DBName.getText().trim());
    }//GEN-LAST:event_BtSaveMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BtSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtSaveActionPerformed

    private void login() {
        Login loginT = new Login(this);
        loginT.setName("Login");
        changeScenne(loginT);
    }
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        userData = null;
        UserNameTitle.setText(" ");
        enableButtons(false);
        //Repintar el formulario
        login();
    }//GEN-LAST:event_jButton2MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (mongoDB != null) {
            mongoDB.closeConnection();
        }
    }//GEN-LAST:event_formWindowClosing

    private void enableButtons(boolean enable) {
        Button2.setEnabled(enable);

        Button3.setEnabled(enable);
        jButton2.setEnabled(enable);
        Opciones.setEnabled(!enable);
        BtSave.setEnabled(!enable);
    }

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtSave;
    private javax.swing.JButton Button1;
    private javax.swing.JButton Button2;
    private javax.swing.JButton Button3;
    private javax.swing.JTextField DBName;
    private javax.swing.JPanel MasterPanel;
    private javax.swing.JButton Opciones;
    private javax.swing.JPanel PanelOptions;
    private javax.swing.JTextField URIMongo;
    private javax.swing.JLabel UserNameTitle;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
