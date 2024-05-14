/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Templates;

import Classes.Tools;
import Classes.Window;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCursor;
import com.mycompany.cuevadeana.Main;
import com.mycompany.cuevadeana.Mongo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose
 */
public class Options extends javax.swing.JFrame {

    private final Main parentFrame;
    private String Uri;
    private String DBName;

    /**
     * Creates new form Options
     *
     * @param parentFrame Main
     */
    public Options(Main parentFrame) {
        this.parentFrame = parentFrame;
        initComponents();
        URI.setText(parentFrame.getURIMONGO());
        DBNAME.setText(parentFrame.getDBNAME());
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        URI = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        DBNAME = new javax.swing.JTextField();
        TestConnectionButton = new javax.swing.JButton();
        DataDefault = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));

        jLabel5.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Opciones");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addGap(86, 86, 86))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("URI");

        jLabel2.setText("Ingrese los datos de la base de datos");

        jLabel3.setText("Nombre de la base de datos");

        TestConnectionButton.setText("Probar conexión");
        TestConnectionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TestConnectionButtonMouseClicked(evt);
            }
        });

        DataDefault.setText("Cargar valores de prueba");
        DataDefault.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataDefaultMouseClicked(evt);
            }
        });

        SaveButton.setBackground(new java.awt.Color(255, 51, 51));
        SaveButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        SaveButton.setForeground(new java.awt.Color(255, 255, 255));
        SaveButton.setText("Guardar");
        SaveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveButtonMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jLabel4.setText("*El servidor ya debe estar iniciado previamente");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(TestConnectionButton)
                                        .addGap(89, 89, 89)
                                        .addComponent(DataDefault))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(27, 27, 27)
                                            .addComponent(DBNAME))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addGap(18, 18, 18)
                                            .addComponent(URI, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel4)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(URI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(DBNAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DataDefault)
                    .addComponent(TestConnectionButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private boolean getValues() {
        List<String> errorList = new ArrayList<>();
        String UriString = URI.getText().trim();
        String NameString = DBNAME.getText().trim();
        if (!Tools.validate("dbname", NameString)) {
            errorList.add("\n" + NameString);
        }
        if (!Tools.validate("uri", UriString)) {
            errorList.add("\n" + UriString);
        }
        if (errorList.isEmpty()) {
            Uri = UriString;
            DBName = NameString;
            return true;
        }
        System.out.println(Tools.validate("dbname", NameString));
        System.out.println(Tools.validate("uri", UriString));
        Window.Message("warning", "Los datos ingresados son invalidos: " + errorList.toString().substring(1, errorList.toString().length()), "Datos invalidos");
        return false;
    }
    private void SaveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveButtonMouseClicked
        // TODO add your handling code here:
        parentFrame.changeDB_Options(Uri, DBName);
    }//GEN-LAST:event_SaveButtonMouseClicked

    private boolean testConnection() {
        try {
            MongoClientURI clientURI = new MongoClientURI(Uri);
            MongoClient mongoClient = new MongoClient(clientURI);
            MongoCursor databases = mongoClient.listDatabaseNames().iterator();
            while (databases.hasNext()) {
                String dbname = (String) databases.next();
                if (dbname.equals(DBName)) {
                    return true;
                }
            }
            mongoClient.close();
        } catch (MongoException e) {
            Window.Message("danger", "\n" + e.toString(), "Error en la prueba de conexión");
        } catch (Exception e) {
            Window.Message("danger", "\n" + e.toString(), "Error en la prueba de conexión");
        }
        return false;
    }
    private void TestConnectionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TestConnectionButtonMouseClicked
        // TODO add your handling code here:
        if (getValues()) {
            boolean status = testConnection(); // Resultado de la conexion
            Window.Message(status ? "info" : "danger", status ? "Conexión valida" : "Conexión invalida", "Prueba de conexión");
        }
    }//GEN-LAST:event_TestConnectionButtonMouseClicked

    private void DataDefaultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataDefaultMouseClicked
        // TODO add your handling code here:
        if (getValues()) {
            //Bloquear boton
            DataDefault.setEnabled(false);
            getValues();
            int op = JOptionPane.showConfirmDialog(parentFrame, "¿Desea cargarlos valores iniciales del programa?\nEsto eliminara los valores existentes almacenados (incluyendo la base de datos: " + DBName + ")", "Cargar valores por defecto", JOptionPane.OK_CANCEL_OPTION);
            if (op == 0) {
                int op1 = JOptionPane.showConfirmDialog(parentFrame,
                        "ATENCIÓN\nSe realizaran las siguientes operaciones:\n"
                        + "\t1. Eliminación de la base de datos: " + DBName + "\n"
                        + "\t2. Creación de las colecciones Bill, Showtimes, Movies y Users\n"
                        + "\t3. Creación automatica de dos usuarios de prueba de diferente cargo (Administrador y cajero) con contraseñas: 123\n"
                        + "=====================================================================================\n"
                        + "Todos los datos almacenados y la base de datos " + DBName + " serán elimados, al continuar con esta\n"
                        + "operación debera reingresar las peliculas y tiempos de funciones de cada una.",
                        "Confirmacion de carga de datos por defecto", JOptionPane.OK_CANCEL_OPTION);
                if (op1 == 0) {
                    String textOriginal, text;
                    textOriginal = DBName + ((int) (Math.random() * 99) + 10) + "";
                    text = JOptionPane.showInputDialog(parentFrame, "Ingrese el siguiente texto: " + textOriginal, "Ingrese el texto de seguridad", JOptionPane.WARNING_MESSAGE);
                    if (textOriginal.equals(text)) {
                        //Cargar valores por defecto
                        Mongo mongo = new Mongo(Uri, DBName);
                        //Eliminamos y creamos los datos
                        mongo.dropDatabase();
                        if (mongo.createDefaultCollections()) {
                            this.dispose();
                            Window.Message("info", "Datos creados", "Exitos en la creación de los datos");
                            Window.Message("info", "Cuentas por defecto:\n\n\nNombre de usuario: Administrador\nContraseña: 123\n\nNombre de usuario: Cajero\nContraseña: 123", "Cuentas creadas");
                        } else {
                            Window.Message("danger", "Error en la creacion de los datos, se recomienda  validar los datos y reintentar", "ERROR EN LA CARGA DE DATOS POR DEFECTO");
                            DataDefault.setEnabled(true);
                        }
                    } else {
                        Window.Message("warning", "El texto de confimación es incorrecto", "Texto incorrecto");
                        DataDefault.setEnabled(true);
                    }
                } else {
                    DataDefault.setEnabled(true);
                }
            } else {
                DataDefault.setEnabled(true);
            }
        }
    }//GEN-LAST:event_DataDefaultMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DBNAME;
    private javax.swing.JButton DataDefault;
    private javax.swing.JButton SaveButton;
    private javax.swing.JButton TestConnectionButton;
    private javax.swing.JTextField URI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
