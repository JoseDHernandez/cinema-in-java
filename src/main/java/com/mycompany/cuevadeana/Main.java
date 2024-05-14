package com.mycompany.cuevadeana;

import Classes.User;
import Classes.Window;
import Templates.List_movies;
import Templates.Login;
import Templates.Options;
import Templates.RegisterMovies;
import Templates.RegisterShowtimes;
import Templates.RegisterUser;
import Templates.Resolution;
import Templates.SellSeats;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.bson.Document;

/**
 *
 * @author Jose
 */
public class Main extends javax.swing.JFrame implements Resolution {

    /**
     * Creates new form Main
     */
    //===============Variables globales===================
    private User userData = new User();
    //Base de datos por defecto
    private String URIMONGO = "mongodb://localhost:27017";
    private String DBNAME = "cine";
    private Mongo mongoDB = null;
    private JPanel actualScenne = null;
    //====================================================

    public Main() {
        initComponents();
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Image miIcono = miPantalla.getImage("src/Logo2.png");
        setIconImage(miIcono);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        enableButtons(false);//Deshabilitar botones
        visibleButtons(false);
        SC_Login();
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
        MasterPanel = new javax.swing.JPanel();
        Menu = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        UserNameTitle = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        Button4 = new javax.swing.JButton();
        Button1 = new javax.swing.JButton();
        Button2 = new javax.swing.JButton();
        Button3 = new javax.swing.JButton();
        Button5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 102, 102));
        setIconImage(getIconImage());
        setPreferredSize(new java.awt.Dimension(1366, 768));
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

        MasterPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout MasterPanelLayout = new javax.swing.GroupLayout(MasterPanel);
        MasterPanel.setLayout(MasterPanelLayout);
        MasterPanelLayout.setHorizontalGroup(
            MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        MasterPanelLayout.setVerticalGroup(
            MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );

        Menu.setBackground(new java.awt.Color(255, 102, 102));

        jPanel4.setBackground(new java.awt.Color(255, 102, 102));

        jLabel5.setFont(new java.awt.Font("Ebrima", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("La Cueva de Ana");

        UserNameTitle.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        UserNameTitle.setForeground(new java.awt.Color(255, 255, 255));
        UserNameTitle.setText(" ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserNameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(UserNameTitle)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 102, 102));

        Button4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button4.setForeground(new java.awt.Color(255, 102, 102));
        Button4.setText("Opciones");
        Button4.setBorder(null);
        Button4.setBorderPainted(false);
        Button4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Button4.setFocusPainted(false);
        Button4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button4MouseClicked(evt);
            }
        });

        Button1.setBackground(new java.awt.Color(255, 153, 153));
        Button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button1.setForeground(new java.awt.Color(255, 255, 255));
        Button1.setText("asdasd");
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
        Button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button2ActionPerformed(evt);
            }
        });

        Button3.setBackground(new java.awt.Color(255, 153, 153));
        Button3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button3.setForeground(new java.awt.Color(255, 255, 255));
        Button3.setText("Peliculas");
        Button3.setBorder(null);
        Button3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button3MouseClicked(evt);
            }
        });

        Button5.setBackground(new java.awt.Color(255, 153, 153));
        Button5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button5.setForeground(new java.awt.Color(255, 255, 255));
        Button5.setText("Venta");
        Button5.setBorder(null);
        Button5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(283, Short.MAX_VALUE)
                .addComponent(Button4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Button4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Button5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MasterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MasterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1335, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void changeDB_Options(String uri, String name) {
        URIMONGO = uri;
        DBNAME = name;
    }

    public String getURIMONGO() {
        return URIMONGO;
    }

    public String getDBNAME() {
        return DBNAME;
    }

    //Re dibuja el JPanel cuando se cambia el tamaño del contenedor 
    public void changeScenne(JPanel scenne) {
        actualScenne = scenne; //910 *630
        MasterPanel.removeAll();
        MasterPanel.add(scenne);
        scenne.setSize(MasterPanel.getWidth(), MasterPanel.getHeight());
        MasterPanel.revalidate();
        MasterPanel.repaint();
    }

    private void SC_RegisterShowtime() {
        RegisterShowtimes re = new RegisterShowtimes(mongoDB);
        changeScenne(re);
    }

    private void SC_SellSeats() {
        SellSeats v = new SellSeats(mongoDB, userData);
        changeScenne(v);
    }

    private void SC_RegisterMovie() {
        RegisterMovies registerMovie = new RegisterMovies(mongoDB);
        changeScenne(registerMovie);
    }
    private void Button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button1MouseClicked
        // TODO add your handling code here:
        if (userData.getRol().equals("Administrador")) {
            SC_RegisterMovie();
        } else {
            SC_SellSeats();
        }
    }//GEN-LAST:event_Button1MouseClicked

    private void SC_ListMovies() {
        List_movies list_movies = new List_movies(mongoDB, this, userData);
        changeScenne(list_movies);
    }

    private void SC_RegisterUser() {
        RegisterUser newUser = new RegisterUser(mongoDB);
        changeScenne(newUser);
    }
    private void Button2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button2MouseClicked
        // TODO add your handling code here:
        if (userData.getRol().equals("Administrador")) {
            SC_RegisterShowtime();
        } else {
            SC_ListMovies();
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

    /**
     * Valida al usuario y estable sus datos en local
     *
     * @param username Nombre del usuario
     * @param pass Contraseña del usuario
     */
    public void login(String username, String pass) {
        //Establecer coneccion con mongoDB
        mongoDB = new Mongo(URIMONGO, DBNAME);
        //Validar usuario
        try {
            User user = new User();
            Document userDataLogin = mongoDB.findUser(user.hashPassword(pass), username);
            if (userDataLogin.getString("Password").equals(user.hashPassword(pass))) {
                user.setId(userDataLogin.getObjectId("_id").toString());
                user.setUserName(userDataLogin.getString("UserName"));
                user.setName(userDataLogin.getString("Name"));
                user.setIdentification(userDataLogin.getString("Identification"));
                user.setRol(userDataLogin.getString("Rol"));
                if (user.getRol().equals("Cajero")) {
                    user.setCashRegister(userDataLogin.getString("CashRegister"));
                }
                this.userData = user;
                //Cambiar botones segun el cargo
                Button1.setText(user.getRol().equals("Administrador") ? "Registrar" : "Venta");
                Button2.setText(user.getRol().equals("Administrador") ? "Funciones" : "Peliculas");

                Button4.setText("Salir");
                //
                UserNameTitle.setText(user.getName());
                enableButtons(true);
                visibleButtons(true);
                if (user.getRol().equals("Administrador")) {
                    Button3.setText("Usuarios");
                    Button5.setText("Películas");
                } else {
                    Button3.setVisible(false);
                    Button5.setVisible(false);
                }
                SC_ListMovies();
            } else {
                Window.Message("warning", "Usuario invalido", "Error de ingreso");
                SC_Login();
            }
        } catch (Exception e) {
            Window.Message("warning", "Error al conectar con la base de datos", "Error de ingreso");
            SC_Login();
        }
    }
    private void Button3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button3MouseClicked
        // TODO add your handling code here:
        if (userData.getRol().equals("Administrador")) {
            SC_RegisterUser();
        } else {

        }
    }//GEN-LAST:event_Button3MouseClicked

    private void Button4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button4MouseClicked
        // TODO add your handling code here:
        if (Button4.getText().equalsIgnoreCase("Opciones")) {
            Options op = new Options(this);
            op.setVisible(true);
        } else {
            //Cerrar sesion
            int op = JOptionPane.showConfirmDialog(this, "¿Estas seguro de querer cerrar sesion?", "Cerrar sesion", JOptionPane.OK_CANCEL_OPTION);
            if (op == 0) {
                //Cerrar conexion
                mongoDB.closeConnection();
                //Limpiar datos
                UserNameTitle.setText(" ");
                userData = new User();
                visibleButtons(false);
                Button4.setText("Opciones");
                //Repintar login
                SC_Login();
            }
        }
    }//GEN-LAST:event_Button4MouseClicked

    private void SC_Login() {
        Login loginT = new Login(this);
        loginT.setName("Login");
        changeScenne(loginT);
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (mongoDB != null) {
            mongoDB.closeConnection();
        }
    }//GEN-LAST:event_formWindowClosing

    private void Button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button2ActionPerformed

    private void Button5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button5MouseClicked
        // TODO add your handling code here:
        if (userData.getRol().equals("Administrador")) {
            SC_ListMovies();
        }
    }//GEN-LAST:event_Button5MouseClicked

    private void enableButtons(boolean enable) {
        Button1.setEnabled(enable);
        Button2.setEnabled(enable);
        Button3.setEnabled(enable);
        Button5.setEnabled(enable);
    }

    private void visibleButtons(boolean isVisible) {
        Button1.setVisible(isVisible);
        Button2.setVisible(isVisible);
        Button3.setVisible(isVisible);
        Button5.setVisible(isVisible);
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
    private javax.swing.JButton Button1;
    private javax.swing.JButton Button2;
    private javax.swing.JButton Button3;
    private javax.swing.JButton Button4;
    private javax.swing.JButton Button5;
    private javax.swing.JPanel MasterPanel;
    private javax.swing.JPanel Menu;
    private javax.swing.JLabel UserNameTitle;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
