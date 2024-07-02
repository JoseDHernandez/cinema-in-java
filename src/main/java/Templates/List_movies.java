/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Templates;

import Classes.Movie;
import Classes.User;
import Classes.Window;
import com.mycompany.cuevadeana.Main;
import com.mycompany.cuevadeana.Mongo;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jose
 */
public class List_movies extends javax.swing.JPanel implements Resolution {

    /**
     * Crea un nuevo formulario List_movies.
     *
     * @param client El cliente de MongoDB.
     */
    private Mongo mongoDB;
    private final Main parentFrame;
    private User user;
    private List<Movie> mongoMovies = new ArrayList<>();

    public List_movies(Mongo client, Main parentFrame, User user) {
        this.parentFrame = parentFrame;
        this.user = user;
        initComponents();
        mongoDB = client;
        loadMovies();
    }

    /**
     * Muestra las películas en el panel.
     */
    private void viewMovies() {
        Panel.removeAll();
        if (!mongoMovies.isEmpty()) {
            Warning.setVisible(false);
            final int maxUn = mongoMovies.size();
            final int columns = 5, rows = (int) Math.ceil((double) maxUn / columns);
            int gap = 5;
            GridLayout gb = new GridLayout(rows, columns, gap, gap);
            Panel.setLayout(gb);
            for (Movie movie : mongoMovies) {
                MoviePoster mt = new MoviePoster(this);
                mt.setData(movie.getTitle(), movie.getPoster());
                mt.setName(movie.getTitle());
                mt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //
                    }
                });
                Panel.add(mt);
            }
            Panel.revalidate();
            Panel.repaint();
        }
    }

    private void loadMovies() {
        mongoMovies = mongoDB.getMovies(0, 0);
        viewMovies();
    }

    /**
     * Cambia la escena por la pelicula seleccionada (según el titulo de esta)
     *
     * @param title Titulo de la pelicula
     *
     */
    public void openViewMovie(String title) {
        try {
            ViewMovie v = new ViewMovie(mongoDB, mongoDB.getMovie(title), user, parentFrame);
            parentFrame.changeScenne(v);
        } catch (RuntimeException e) {
            Window.Message("warning", "La pelicula " + title + " no fue encontrada", "Pelicula no encontrada");
        }
    }

    //Obtiene lista de generos
    private void findMovies() {
        String name = Name.getText().trim();
        List<String> actors = null;
        if (!Actors.getText().trim().isEmpty()) {
            actors = stringToList(Actors.getText().trim());
        }
        String genresString = getListGenres();
        List<String> genres = null;
        if (genresString != null && genresString.length() > 0) {
            genres = stringToList(getListGenres());
        }
        String director = Director.getText().trim();
        String classification = "";
        if (Classification.getSelectedIndex() > 0) {
            classification = Classification.getSelectedItem().toString();
        }
        int duration = 0;
        if (!Duration.getText().isEmpty()) {
            duration = Integer.parseInt(Duration.getText().trim());
        }
        //Date
        Date date = DateChooser.getDate();
        String dateString = null;
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            dateString = sdf.format(date);
        }
        // Realizar la busqueda
        mongoMovies.clear();
        List<Movie> temp = mongoDB.findMovies(name, director, actors, classification, duration, dateString, genres);
        if (!temp.isEmpty()) {
            mongoMovies.addAll(temp);
            viewMovies();
        } else {
            Window.Message("warning", "No se encontraron películas registradas según los criterios ingresados.", "No hay películas");
        }
    }

    private List<String> stringToList(String text) {
        return Arrays.asList(text.split(","));
    }

    private String getListGenres() {
        String list = "";
        if (Comedia.isSelected()) {
            list += ", " + Comedia.getText();
        }
        if (Accion.isSelected()) {
            list += ", " + Accion.getText();
        }
        if (Terror.isSelected()) {
            list += ", " + Terror.getText();
        }
        if (Fantasia.isSelected()) {
            list += ", " + Fantasia.getText();
        }
        if (Drama.isSelected()) {
            list += ", " + Drama.getText();
        }
        if (Familia.isSelected()) {
            list += ", " + Familia.getText();
        }
        if (Romance.isSelected()) {
            list += ", " + Romance.getText();
        }
        if (Crimen.isSelected()) {
            list += ", " + Crimen.getText();
        }
        if (Suspenso.isSelected()) {
            list += ", " + Suspenso.getText();
        }
        if (Misterio.isSelected()) {
            list += ", " + Misterio.getText();
        }
        if (Ciencia_f.isSelected()) {
            list += ", " + Ciencia_f.getText();
        }
        if (Aventura.isSelected()) {
            list += ", " + Aventura.getText();
        }
        if (list.length() == 0) {
            list = null;
        } else {
            list = list.substring(2, list.length());
        }
        return list;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Panel1 = new javax.swing.JScrollPane();
        Panel = new javax.swing.JPanel();
        Warning = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Comedia = new javax.swing.JCheckBox();
        Accion = new javax.swing.JCheckBox();
        Terror = new javax.swing.JCheckBox();
        Fantasia = new javax.swing.JCheckBox();
        Drama = new javax.swing.JCheckBox();
        Familia = new javax.swing.JCheckBox();
        Suspenso = new javax.swing.JCheckBox();
        Misterio = new javax.swing.JCheckBox();
        Ciencia_f = new javax.swing.JCheckBox();
        Aventura = new javax.swing.JCheckBox();
        Romance = new javax.swing.JCheckBox();
        Crimen = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Actors = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Director = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Classification = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Duration = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        DateChooser = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(DEF_RESOLUTION);
        setPreferredSize(new java.awt.Dimension(1336, 673));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(910, 630));

        Panel1.setBackground(new java.awt.Color(255, 255, 255));
        Panel1.setBorder(null);

        Panel.setBackground(new java.awt.Color(255, 255, 255));

        Warning.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        Warning.setText("No hay peliculas registradas");

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(Warning)
                .addContainerGap(500, Short.MAX_VALUE))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(Warning)
                .addContainerGap(467, Short.MAX_VALUE))
        );

        Panel1.setViewportView(Panel);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        jLabel1.setFont(new java.awt.Font("Ebrima", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Películas");

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        Comedia.setBackground(new java.awt.Color(255, 102, 102));
        Comedia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Comedia.setForeground(new java.awt.Color(255, 255, 255));
        Comedia.setText("Comedia");
        Comedia.setBorderPainted(true);
        Comedia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Accion.setBackground(new java.awt.Color(255, 102, 102));
        Accion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Accion.setForeground(new java.awt.Color(255, 255, 255));
        Accion.setText("Acción");
        Accion.setBorderPainted(true);
        Accion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Terror.setBackground(new java.awt.Color(255, 102, 102));
        Terror.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Terror.setForeground(new java.awt.Color(255, 255, 255));
        Terror.setText("Terror");
        Terror.setBorderPainted(true);
        Terror.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Fantasia.setBackground(new java.awt.Color(255, 102, 102));
        Fantasia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Fantasia.setForeground(new java.awt.Color(255, 255, 255));
        Fantasia.setText("Fantasia");
        Fantasia.setBorderPainted(true);
        Fantasia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Drama.setBackground(new java.awt.Color(255, 102, 102));
        Drama.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Drama.setForeground(new java.awt.Color(255, 255, 255));
        Drama.setText("Drama");
        Drama.setBorderPainted(true);
        Drama.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Familia.setBackground(new java.awt.Color(255, 102, 102));
        Familia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Familia.setForeground(new java.awt.Color(255, 255, 255));
        Familia.setText("Familia");
        Familia.setBorderPainted(true);
        Familia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Suspenso.setBackground(new java.awt.Color(255, 102, 102));
        Suspenso.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Suspenso.setForeground(new java.awt.Color(255, 255, 255));
        Suspenso.setText("Suspenso");
        Suspenso.setBorderPainted(true);
        Suspenso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Misterio.setBackground(new java.awt.Color(255, 102, 102));
        Misterio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Misterio.setForeground(new java.awt.Color(255, 255, 255));
        Misterio.setText("Misterio");
        Misterio.setBorderPainted(true);
        Misterio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Ciencia_f.setBackground(new java.awt.Color(255, 102, 102));
        Ciencia_f.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Ciencia_f.setForeground(new java.awt.Color(255, 255, 255));
        Ciencia_f.setText("Ciencia ficción");
        Ciencia_f.setBorderPainted(true);
        Ciencia_f.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Aventura.setBackground(new java.awt.Color(255, 102, 102));
        Aventura.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Aventura.setForeground(new java.awt.Color(255, 255, 255));
        Aventura.setText("Aventura");
        Aventura.setBorderPainted(true);
        Aventura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Romance.setBackground(new java.awt.Color(255, 102, 102));
        Romance.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Romance.setForeground(new java.awt.Color(255, 255, 255));
        Romance.setText("Romance");
        Romance.setBorderPainted(true);
        Romance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Crimen.setBackground(new java.awt.Color(255, 102, 102));
        Crimen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Crimen.setForeground(new java.awt.Color(255, 255, 255));
        Crimen.setText("Crimen");
        Crimen.setBorderPainted(true);
        Crimen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Accion)
                    .addComponent(Terror)
                    .addComponent(Fantasia)
                    .addComponent(Comedia)
                    .addComponent(Familia)
                    .addComponent(Drama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Romance)
                    .addComponent(Crimen)
                    .addComponent(Suspenso)
                    .addComponent(Aventura)
                    .addComponent(Ciencia_f)
                    .addComponent(Misterio))
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Suspenso)
                    .addComponent(Comedia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Misterio)
                    .addComponent(Accion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Ciencia_f)
                    .addComponent(Terror))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Aventura)
                    .addComponent(Fantasia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Romance)
                    .addComponent(Drama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Crimen)
                    .addComponent(Familia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Actores");

        jLabel4.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Director");

        jButton1.setFont(new java.awt.Font("Ebrima", 1, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 102, 102));
        jButton1.setText("Buscar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        Classification.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "T", "7", "12", "15", "18", "X", "Prohibido" }));
        Classification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClassificationActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Clasificación");

        jLabel6.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Duración");

        jLabel9.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fecha");

        DateChooser.setDateFormatString("dd-MM-yyyy");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jLabel7.setText("*Minutos");

        jLabel8.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Generos");

        jButton2.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 102, 102));
        jButton2.setText("Todas las películas");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Actors, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Classification, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Duration, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Director, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)))
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Actors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Director, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Classification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1331, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ClassificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClassificationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClassificationActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        findMovies();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        loadMovies();
    }//GEN-LAST:event_jButton2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Accion;
    private javax.swing.JTextField Actors;
    private javax.swing.JCheckBox Aventura;
    private javax.swing.JCheckBox Ciencia_f;
    private javax.swing.JComboBox<String> Classification;
    private javax.swing.JCheckBox Comedia;
    private javax.swing.JCheckBox Crimen;
    private com.toedter.calendar.JDateChooser DateChooser;
    private javax.swing.JTextField Director;
    private javax.swing.JCheckBox Drama;
    private javax.swing.JTextField Duration;
    private javax.swing.JCheckBox Familia;
    private javax.swing.JCheckBox Fantasia;
    private javax.swing.JCheckBox Misterio;
    private javax.swing.JTextField Name;
    private javax.swing.JPanel Panel;
    private javax.swing.JScrollPane Panel1;
    private javax.swing.JCheckBox Romance;
    private javax.swing.JCheckBox Suspenso;
    private javax.swing.JCheckBox Terror;
    private javax.swing.JLabel Warning;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
