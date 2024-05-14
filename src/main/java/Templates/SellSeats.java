/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Templates;

import Classes.Bill;
import Classes.Movie;
import Classes.Showtime;
import Classes.Theater;
import Classes.Tools;
import Classes.User;
import Classes.Window;
import com.mycompany.cuevadeana.Mongo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jose
 */
public class SellSeats extends javax.swing.JPanel {

    /**
     * Creates new form Venta
     */
    public Mongo mongoDB;
    public User cashier;
    //Precios
    public final double PRECIO_BASE_2D = 10000.0; // Precio base para proyección 2D
    public final double PRECIO_BASE_3D = 15000.0; // Precio base para proyección 3D
    public final double PRECIO_BASE_4D = 20000.0; // Precio base para proyección 4D
    public final double PRECIO_VIP = 5000.0;// Precios adicionales para asientos VIP
    //Sillas exclusivas
    public final List<String> VIP_SEATS = Arrays.asList("G7", "G8", "G9", "G10", "H7", "H8", "H9",
            "H10", "I7", "I8", "I9", "I10", "J7", "J8", "J9", "J10");
    public final List<String> WHEELCHAIR_SEATS = Arrays.asList("Q1", "Q2", "Q15", "Q16");
    //Colores
    public final Color SILLA_RUEDAS = Color.red;
    public final Color VENDIDA = Color.darkGray;
    public final Color SELECCION = Color.ORANGE;
    public final Color VIP = Color.CYAN;
    public final Color VOID = Color.white;
    //Variables de menus
    public List<String> allSeatsSold = new ArrayList<>();//Todas las silla vendidas de una funcion
    public List<String> seatsSold = new ArrayList<>();//Sillas seleccionadas
    public String titleOfMovie = "";
    public String dateString = "";//Dia actual
    public List<String> showtimesListString = new ArrayList<>(); // Para lista de horarios
    public List<Theater> theaterList = new ArrayList<>();//Almacena la lista de funciones regresadas de la DB
    public List<Showtime> showtimeList = new ArrayList<>();
    public Showtime tempShow = new Showtime(); //Showtime temporal para tener como referencia
    public final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public SellSeats(Mongo client, User cashier) {
        initComponents();
        mongoDB = client;
        this.cashier = cashier;
        //Establer nombres a los botones
        initSeats();
        //Obtener lista de peliculas
        getMovieTitles();
        //Obtener fecha actual
        LocalDate dateNow = LocalDate.now();

        // Formatear la fecha como una cadena de texto en el formato deseado
        dateString = dateNow.format(formatterDate);
        //Fecha actual en dateChosser 
        DateChoosser.setDate(new Date());
    }

    private void initSeats() {
        final String[] letters = {"-", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q"};

        int ButtonSize = 10;
        int Gap = 2;
        final Dimension sizeButton = new Dimension(ButtonSize, ButtonSize);
        GridLayout gridLayout = new GridLayout(letters.length, 17);

        gridLayout.setHgap(Gap);
        gridLayout.setVgap(Gap);
        Sala.setLayout(gridLayout);

        // Fila de números
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 17; j++) {
                if (j == 0) {
                    JLabel l = new JLabel(" ");
                    Sala.add(l);
                } else {
                    JLabel l = new JLabel(String.valueOf(j));
                    Sala.add(l);
                    if (j == 3 || j == 13) {
                        JLabel l2 = new JLabel(" ");
                        Sala.add(l2);
                    }
                }
            }
        }

        // Filas de letras y botones
        for (int i = 1; i < letters.length; i++) {
            JLabel t = new JLabel(letters[i]);
            Sala.add(t);
            for (int j = 0; j < 16; j++) {
                //Botones
                String cellName = letters[i] + (j + 1);
                JButton cell = new JButton();
                cell.setName(cellName);
                cell.setSize(sizeButton);
                cell.setMaximumSize(sizeButton);
                cell.setMinimumSize(sizeButton);
                cell.setBackground(VOID);
                cell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        System.out.println(clickedButton.getName());
                        addChair(clickedButton);
                    }
                });
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        cell.setBackground(Color.MAGENTA);
                        if (seatsSold.contains(cell.getName())) {
                            cell.setCursor(Cursor.getDefaultCursor());
                        } else {
                            cell.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (Showtimes.getSelectedIndex() != 0) {
                            if (seatsSold.contains(cell.getName())) {
                                cell.setBackground(SELECCION);
                            } else if (allSeatsSold.contains(cell.getName())) {
                                cell.setBackground(VENDIDA);
                            } else if (VIP_SEATS.contains(cell.getName())) {
                                cell.setBackground(VIP);
                            } else if (WHEELCHAIR_SEATS.contains(cell.getName())) {
                                cell.setBackground(SILLA_RUEDAS);
                            } else {
                                cell.setBackground(VOID);
                            }
                        } else {
                            cell.setCursor(Cursor.getDefaultCursor());
                            cell.setBackground(VOID);
                        }
                    }
                });
                Sala.add(cell);
                if (j == 2 || j == 12) {
                    JPanel p = new JPanel();
                    p.setBackground(Color.white);
                    Sala.add(p);
                }
            }
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        clientName = new javax.swing.JTextField();
        Type = new javax.swing.JComboBox<>();
        Identification = new javax.swing.JTextField();
        DateChoosser = new com.toedter.calendar.JDateChooser();
        jLabel38 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        ListMovies = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        Features = new javax.swing.JComboBox<>();
        Showtimes = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        SeatsSoldLabel = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        SoldButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Screen = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Sala = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 0));
        setPreferredSize(new java.awt.Dimension(1336, 768));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 768));

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));

        jLabel37.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Nombre");

        clientName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientNameActionPerformed(evt);
            }
        });

        Type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CC", "TI", " " }));

        DateChoosser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DateChoosserPropertyChange(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Identificación");

        jLabel35.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Fecha");

        ListMovies.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seleccionar" }));
        ListMovies.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ListMoviesItemStateChanged(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Pelicula");

        jLabel36.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Característica");

        Features.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "2D", "3D", "4D", "2D - CC", "3D - CC", "4D - CC" }));
        Features.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeaturesActionPerformed(evt);
            }
        });

        Showtimes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        Showtimes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ShowtimesItemStateChanged(evt);
            }
        });
        Showtimes.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                ShowtimesPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Funciones");

        jLabel39.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Código de sillas:");

        SeatsSoldLabel.setBackground(new java.awt.Color(255, 255, 255));
        SeatsSoldLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SeatsSoldLabel.setText(" ");
        SeatsSoldLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel33.setFont(new java.awt.Font("Ebrima", 3, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Venta de entradas");

        SoldButton.setFont(new java.awt.Font("Ebrima", 1, 20)); // NOI18N
        SoldButton.setForeground(new java.awt.Color(255, 102, 102));
        SoldButton.setText("Registrar venta");
        SoldButton.setFocusPainted(false);
        SoldButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SoldButtonMouseClicked(evt);
            }
        });
        SoldButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SoldButtonActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(51, 51, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("Guía de colores");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel38)
                                        .addComponent(jLabel37)
                                        .addComponent(jLabel35)
                                        .addComponent(jLabel34)
                                        .addComponent(jLabel36)
                                        .addComponent(jLabel40))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(DateChoosser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(Type, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(Identification, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(clientName, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ListMovies, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Features, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Showtimes, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addComponent(SeatsSoldLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(SoldButton, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(clientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Identification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(DateChoosser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ListMovies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(Features, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(Showtimes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SeatsSoldLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SoldButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        Screen.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ScreenLayout = new javax.swing.GroupLayout(Screen);
        Screen.setLayout(ScreenLayout);
        ScreenLayout.setHorizontalGroup(
            ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );
        ScreenLayout.setVerticalGroup(
            ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        Sala.setBackground(new java.awt.Color(255, 255, 255));
        Sala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        Sala.setPreferredSize(new java.awt.Dimension(720, 768));

        javax.swing.GroupLayout SalaLayout = new javax.swing.GroupLayout(Sala);
        Sala.setLayout(SalaLayout);
        SalaLayout.setHorizontalGroup(
            SalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );
        SalaLayout.setVerticalGroup(
            SalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(Sala, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sala, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(Screen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Screen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 632, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1336, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1242, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //Evitar que se ejecute dos veces setSeatsSold
    private int indexControl = 0;

    //Cargar sillas vendidas segun la presentacion
    public void loadSeatsSold() {
        if (Showtimes.getSelectedIndex() > 0 && ListMovies.getSelectedIndex() != 0 && indexControl != Showtimes.getSelectedIndex()) {
            indexControl = Showtimes.getSelectedIndex();
            //Obtener sillas vendidas
            String[] text = Showtimes.getSelectedItem().toString().split(" ");
            String Theater = text[0] + " " + text[1];
            //Datos para la busqueda de la funcion
            Theater = Theater.substring(0, Theater.length() - 1);
            String startHour = text[3];
            String endHour = text[5];
            //Buscar funcion para asignar sillas
            for (Showtime show : showtimeList) {
                if (show.getTheater().equals(Theater) && show.getStartHour().toString().equals(startHour) && show.getEndHour().toString().equals(endHour)) {
                    tempShow.setStartHour(show.getStartHour());
                    tempShow.setEndHour(show.getEndHour());
                    tempShow.setTheater(show.getTheater());
                    titleOfMovie = show.getMovie();
                    tempShow.setMovie(titleOfMovie);

                    /*
                     Sala 1    - 2D
            Sala 2    - 2D - CC
            Sala 3    - 2D 
            Sala 4    - 3D
            Sala 5    - 3D - CC
            Sala 6    - 3D
            Sala 7    - 4D - CC
            Sala 8    - 4D
            Sala 9    - 4D
                     */
                    int num = Integer.parseInt((show.getTheater().split(" "))[1]);
                    int index = switch (num) {
                        case 2 ->
                            4;
                        case 5 ->
                            5;
                        case 7 ->
                            6;
                        case 1, 3 ->
                            1;
                        case 4, 6 ->
                            2;
                        case 8, 9 ->
                            3;
                        default ->
                            0;
                    };
                    Features.setSelectedIndex(index);
                    //Asignar sillas vendidas
                    allSeatsSold = show.getSeatsSold();
                    break;
                }
            }
            //Limpiar datos
            SeatsSoldLabel.setText("");
            //Obtener botones
            Component[] sillas = Sala.getComponents();
            //Indicar las sillas ya vendidas
            for (Component silla : sillas) {
                if (silla instanceof JButton button) {
                    if (button.getName() != null) {
                        if (VIP_SEATS.contains(button.getName())) {
                            button.setBackground(VIP);
                        } else if (allSeatsSold.contains(button.getName())) {
                            button.setBackground(VENDIDA);
                        } else if (WHEELCHAIR_SEATS.contains(button.getName())) {
                            button.setBackground(SILLA_RUEDAS);
                        } else {
                            button.setBackground(VOID);
                        }
                    }
                }
            }
            seatsSold = new ArrayList<>();
        }
    }

    //Establecer las funciones en el JComboBox segun las caracteristicas y pelicula
    public void setShows() {
        /*
            Sala 1    - 2D
            Sala 2    - 2D - CC
            Sala 3    - 2D 
            Sala 4    - 3D
            Sala 5    - 3D - CC
            Sala 6    - 3D
            Sala 7    - 4D - CC
            Sala 8    - 4D
            Sala 9    - 4D
         */
        Showtimes.removeAllItems();
        Showtimes.addItem("Selecciona");
        Showtimes.setSelectedIndex(0);
        if (Features.getSelectedIndex() != 0) {
            //Salas
            List<String> theaterList = new ArrayList<>();
            //Filtro de salas segun caracteristica
            String[] features = Features.getSelectedItem().toString().split(" ");
            String Feature = features[0];
            //Filtrar por dimencion
            switch (Feature) {
                case "2D":
                    //Si tiene CC
                    if (features.length > 1) {
                        theaterList.add("Sala 2");
                    } else {
                        theaterList.add("Sala 1");
                        theaterList.add("Sala 3");
                    }
                    break;
                case "3D":
                    if (features.length > 1) {
                        theaterList.add("Sala 5");
                    } else {
                        theaterList.add("Sala 4");
                        theaterList.add("Sala 6");
                    }
                    break;
                case "4D":
                    if (features.length > 1) {
                        theaterList.add("Sala 7");
                    } else {
                        theaterList.add("Sala 8");
                        theaterList.add("Sala 9");
                    }
                    break;
                default:
                    break;
            }
            System.out.println("Salas: " + showtimesListString.toString());
            System.out.println("Teatros: " + theaterList.toString());
            //Agregar sala a lista
            if (!theaterList.isEmpty()) {
                int constains = 0;
                for (String show : showtimesListString) {
                    System.out.println("SHOW: " + show);
                    String[] text = show.split(" ");
                    String Theater = text[0] + " " + text[1];
                    Theater = Theater.substring(0, Theater.length() - 1);
                    //Si la sala cumple la caracteristica
                    if (theaterList.contains(Theater)) {
                        Showtimes.addItem(show);
                        constains++;
                    }
                }
                if (constains == 0) {
                    Showtimes.addItem("No hay funciones con " + Features.getSelectedItem().toString());
                }
            } else {
                Showtimes.addItem("No hay funciones con " + Features.getSelectedItem().toString());
            }
        } else {
            for (String show : showtimesListString) {
                Showtimes.addItem(show);
            }
            //Limpiar sillas
            loadSeatsSold();
        }
    }

    //Obtener lista de funciones
    public void getShows() {
        if (ListMovies.getSelectedIndex() != 0 && !titleOfMovie.equalsIgnoreCase(ListMovies.getSelectedItem().toString())) {
            System.out.println("\n\nPeliculas");
            Features.setSelectedIndex(0);
            titleOfMovie = ListMovies.getSelectedItem().toString();
            try {
                //Fecha (date chooser o actual)
                String dateSelected = DateChoosser.getDate().compareTo(new Date()) != 0 ? dateString : formatterDate.format(DateChoosser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                //Cambiar fecha si DateChosser y dateSelected son diferentes
                if (!dateSelected.equals(dateString)) {
                    dateString = dateSelected;
                }
                theaterList = mongoDB.getTheater(titleOfMovie, dateSelected);
                if (!theaterList.isEmpty()) {
                    showtimeList.clear();
                    showtimesListString.clear();
                    for (Theater shows : theaterList) {
                        for (Showtime hours : shows.getShowtimes()) {
                            String temp = shows.getName() + ":  " + hours.getStartHour() + " - " + hours.getEndHour();
                            System.out.println(temp);
                            showtimesListString.add(temp);
                            showtimeList.add(hours);
                        }
                    }
                    setShows();

                } else {
                    //Restablecer showtimes
                    theaterList = new ArrayList<>();
                    Window.Message("alert", "Funcion no encontrada:\n solicite el registro de la funcion correspodiente al dia " + dateString
                            + " para la pelicula " + titleOfMovie + " con un administrador.", "Funcion no encontrada");
                }
            } catch (Exception e) {
                System.out.println(e);
                Window.Message("danger", e.toString(), "Error en venta de entrada");
            }
        } else {
            titleOfMovie = "";
        }
    }

    //Obtener lista de peliculas
    private void getMovieTitles() {
        List<Movie> movies = mongoDB.getMovies(0, 0);
        System.out.println("\n\nSize: " + movies.size());
        if (movies.isEmpty()) {
            ListMovies.removeItemAt(0);
            ListMovies.addItem("No hay peliculas disponibles");
            ListMovies.setSelectedIndex(0);
        } else {
            for (Movie movie : movies) {
                ListMovies.addItem(movie.getTitle());
            }
        }
    }

    private void openGuie() {
        Window.Message("info", "Colores:\n\n AZUL: Sillas V.I.P.\n ROJO: Espacio para sillas de ruedas\n AMARILLO: Silla seleccionada\n VIOLETA: Inidicador de la silla a seleccionar\n BLANCO: Silla normar sin usar\n GRIS OSCURO: Silla vendida", "GUÍA DE COLORES");
    }

    //Agregar silla a la factura
    public void addChair(JButton evt) {
        if (!titleOfMovie.isEmpty()) {
            String seatName = evt.getName();
            //Valida si la silla no esta vendida, agrega o remove de las sillas de la factura
            if (!allSeatsSold.contains(seatName) && !seatsSold.contains(seatName)) {
                seatsSold.add(seatName);
                evt.setBackground(SELECCION);
            } else {
                seatsSold.remove(seatsSold.indexOf(seatName));
                evt.setBackground(VOID);
            }
            String seats = seatsSold.toString().substring(1, seatsSold.toString().length() - 1);
            SeatsSoldLabel.setText("<html><p>" + seats + "</p></html>");
        }
    }

    //Calcular precio
    public double getPrice() {
        double price = 0.0;
        //Precio segun categoria
        String[] features = Features.getSelectedItem().toString().split(" ");
        String Feature = features[0];
        //Filtrar por dimencion
        switch (Feature) {
            case "2D":
                price += PRECIO_BASE_2D;
                break;
            case "3D":
                price += PRECIO_BASE_3D;
                break;
            case "4D":
                price += PRECIO_BASE_4D;
                break;
        }
        if (seatsSold.isEmpty()) {
            throw new InternalError("Lista de asientos vacia");
        }
        //Multiplicar por cantidad de sillas
        price *= seatsSold.size();
        //Añadir precio vip
        for (String seat : seatsSold) {
            if (VIP_SEATS.contains(seat)) {
                price += PRECIO_VIP;
            }
        }
        return price;
    }

    //Generar factura
    public void ticketsSold() {
        String nameClient = clientName.getText().trim();
        String numIde = Identification.getText().trim();
        //Validar campos
        if (Tools.validate("name", nameClient) && numIde.length() > 9 && !seatsSold.isEmpty() && Features.getSelectedIndex() > 0 && Tools.validate("date", dateString)) {
            Bill bill = new Bill();
            //Generar factura
            String iden = (Type.getSelectedIndex() > 0 ? Type.getSelectedItem().toString() : "CC") + " " + numIde;
            bill.setNameClient(nameClient);
            bill.setIdentification(iden);
            bill.setCashier(cashier);
            bill.setShowtime(tempShow);
            bill.setSeats(seatsSold);
            bill.setDateShow(dateString);
            bill.setPrice(getPrice());
            int selectedOption = JOptionPane.showConfirmDialog(null, bill.toString(), "Factura", JOptionPane.OK_CANCEL_OPTION);
            //Almacenar factura en la base de datos
            if (selectedOption == 0) {
                if (mongoDB.insert(bill)) {
                    Window.Message("info", "Exito en registrar: \n" + bill.toString(), "Venta registrada");
                    //Limpiar inputs
                    loadSeatsSold();
                } else {
                    Window.Message("danger", "Error al registrar la factura ", "Error al registrar la factura");
                }
            } else {
                Window.Message("warning", "Factura cancelada con exito", "Factura cancelada");
                //Limpiar inputs
                loadSeatsSold();
            }
        } else {
            StringBuilder str = new StringBuilder();
            if (!Tools.validate("name", nameClient)) {
                str.append("  Nombre de incliente invalido \n");
            }
            if (numIde.length() < 9) {
                str.append("  Número de identifiación invalido (deber ser mayor a 9 digitos) \n");
            }
            if (seatsSold.isEmpty()) {
                str.append("  No hay asientos senleccionados\n");
            }
            if (Features.getSelectedIndex() == 0) {
                str.append("  No hay una característica seleccionada\n");
            }
            if (ListMovies.getSelectedIndex() == 0) {
                str.append("  Película no seleccionada");
            }
            if (Showtimes.getSelectedIndex() == 0) {
                str.append("  Función o proyección no seleccionada");
            }
            if (!Tools.validate("date", dateString)) {
                str.append("  Fecha invalida o inexistente\n");
            }
            Window.Message("warning", "Por favor verifique los datos ingresados y/o seleccionados:\n\n" + str.toString(), "Error en generacion de factura: datos invalidos");
        }
    }

    private void SoldButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SoldButtonMouseClicked
        // TODO add your handling code here:
        ticketsSold();
    }//GEN-LAST:event_SoldButtonMouseClicked

    private void clientNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientNameActionPerformed

    private void FeaturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FeaturesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FeaturesActionPerformed

    private void ListMoviesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ListMoviesItemStateChanged
        // TODO add your handling code here:

        getShows();
    }//GEN-LAST:event_ListMoviesItemStateChanged

    private void ShowtimesPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_ShowtimesPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        setShows();
    }//GEN-LAST:event_ShowtimesPopupMenuWillBecomeVisible

    private void ShowtimesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ShowtimesItemStateChanged
        // TODO add your handling code here:
        loadSeatsSold();
    }//GEN-LAST:event_ShowtimesItemStateChanged

    private void DateChoosserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DateChoosserPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_DateChoosserPropertyChange

    private void SoldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SoldButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SoldButtonActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        openGuie();
    }//GEN-LAST:event_jLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChoosser;
    private javax.swing.JComboBox<String> Features;
    private javax.swing.JTextField Identification;
    private javax.swing.JComboBox<String> ListMovies;
    private javax.swing.JPanel Sala;
    private javax.swing.JPanel Screen;
    private javax.swing.JLabel SeatsSoldLabel;
    private javax.swing.JComboBox<String> Showtimes;
    private javax.swing.JButton SoldButton;
    private javax.swing.JComboBox<String> Type;
    private javax.swing.JTextField clientName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
