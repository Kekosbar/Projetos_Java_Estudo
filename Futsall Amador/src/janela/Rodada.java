package janela;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import script.Campeonato;
import script.ConnectSQL;
import script.Partida;
import script.Time;

/**
 *
 * @author caique
 */
public class Rodada extends javax.swing.JFrame {

    Campeonato campeonato;
    int rodada;
    Time times[];
    JLabel timeLocal[];
    JLabel timeVisitante[];
    JTextField gols[];
    JLabel labels[];
    
    public Rodada(Campeonato campeonato) {
        initComponents();
        this.campeonato = campeonato;
        Time[] aux = new Time[campeonato.getTimes().size()];
        aux = campeonato.getTimes().toArray(aux);
        preparaAdversarios(aux);
        imp();
        int tam = campeonato.getTimes().size();
        this.timeLocal = new JLabel[5];
        this.timeVisitante = new JLabel[5];
        this.gols = new JTextField[10];
        //this.golsvisitante = new JTextField[5];
        this.labels = new JLabel[5];
        this.rodada = times[0].getJogos()+1;
        inicia(tam);
        organizaJogos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        LTvi1 = new javax.swing.JLabel();
        EdLo1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        EdVi1 = new javax.swing.JTextField();
        LTlo1 = new javax.swing.JLabel();
        LTlo2 = new javax.swing.JLabel();
        EdLo2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        EdVi2 = new javax.swing.JTextField();
        LTvi2 = new javax.swing.JLabel();
        LTlo3 = new javax.swing.JLabel();
        EdLo3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        EdVi3 = new javax.swing.JTextField();
        LTvi3 = new javax.swing.JLabel();
        LTlo4 = new javax.swing.JLabel();
        EdLo4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        EdVi4 = new javax.swing.JTextField();
        LTvi4 = new javax.swing.JLabel();
        LTlo5 = new javax.swing.JLabel();
        EdLo5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        EdVi5 = new javax.swing.JTextField();
        LTvi5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel1.setText("Rodada");

        LTvi1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        LTvi1.setText("Time");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel3.setText("X");

        LTlo1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        LTlo1.setText("Time");

        LTlo2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        LTlo2.setText("Time");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel4.setText("X");

        LTvi2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        LTvi2.setText("Time");

        LTlo3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        LTlo3.setText("Time");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel5.setText("X");

        LTvi3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        LTvi3.setText("Time");

        LTlo4.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        LTlo4.setText("Time");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel6.setText("X");

        LTvi4.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        LTvi4.setText("Time");

        LTlo5.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        LTlo5.setText("Time");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel7.setText("X");

        LTvi5.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        LTvi5.setText("Time");

        jButton1.setText("Confirmar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LTlo3)
                            .addComponent(LTlo4)
                            .addComponent(LTlo5)
                            .addComponent(LTlo1)
                            .addComponent(LTlo2))
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EdLo5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(EdVi5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EdLo4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(EdVi4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(EdLo3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(EdVi3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(EdLo2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(EdVi2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(EdLo1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(EdVi1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel1)
                        .addGap(23, 23, 23)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LTvi5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LTvi4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LTvi2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LTvi1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LTvi3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(77, 77, 77)
                .addComponent(jButton1)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LTvi1)
                    .addComponent(EdLo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(EdVi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LTlo1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LTvi2)
                    .addComponent(EdLo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(EdVi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LTlo2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LTvi3)
                    .addComponent(EdLo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(EdVi3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LTlo3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LTvi4)
                    .addComponent(EdLo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(EdVi4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LTlo4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LTvi5)
                    .addComponent(jLabel7)
                    .addComponent(EdVi5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LTlo5)
                    .addComponent(EdLo5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // CONFIRMAR
        ConnectSQL sql = new ConnectSQL();
        System.out.println(gols[0].getText());
        for(int i=0; i<times.length; i+=2){
            sql.cadastrarPartida(campeonato.getId(), rodada, times[i], times[i+1], 
                    Integer.parseInt(gols[i].getText()), Integer.parseInt(gols[i+1].getText()));
        }
        
        sql.disconectar();
        dispose();
        Tabela janela = new Tabela(campeonato);
        janela.setVisible(true);
        janela.setLocation(300, 300);
        janela.setResizable(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void inicia(int tam){
        timeLocal[0] = LTlo1; timeVisitante[0] = LTvi1; gols[0] = EdLo1; gols[1] = EdVi1; labels[0] = jLabel3;
        timeLocal[1] = LTlo2; timeVisitante[1] = LTvi2; gols[2] = EdLo2; gols[3] = EdVi2; labels[1] = jLabel4;
        timeLocal[2] = LTlo3; timeVisitante[2] = LTvi3; gols[4] = EdLo3; gols[5] = EdVi3; labels[2] = jLabel5;
        timeLocal[3] = LTlo4; timeVisitante[3] = LTvi4; gols[6] = EdLo4; gols[7] = EdVi4; labels[3] = jLabel6;
        timeLocal[4] = LTlo5; timeVisitante[4] = LTvi5; gols[8] = EdLo5; gols[9] = EdVi5; labels[4] = jLabel7;
        // Desativas exesso
        for(int i=tam/2; i<5; i++){
            timeLocal[i].setVisible(false); timeVisitante[i].setVisible(false); labels[i].setVisible(false);
        }
        for(int i=tam; i<10; i++){
            gols[i].setVisible(false);
        }
    }

    public void preparaAdversarios(Time[] aux){
        ConnectSQL sql = new ConnectSQL();
        ArrayList<Partida> partidas = sql.getAllPartidas(campeonato.getId());
        
        // Prepara agora
        times = new Time[aux.length];
        int index=0; // Posição no vetor times
        for(int i=0; i<aux.length; i++){
            if(aux[i] != null)
            for(int j=0; j<aux.length; j++){
                if(i == j) continue;
                if(aux[j] != null){
                    Partida novaPartida = new Partida(aux[i].getId(), aux[j].getId());
                    System.out.println(aux[i].getNome()+" X "+aux[j].getNome());
                    boolean permitir = true;
                    // Verifica se esta partida já existe
                    for(Partida partida : partidas){
                        if(novaPartida.compara(partida)){
                            permitir = false;
                            break;
                        }
                    }
                    if(permitir){
                        times[index++] = aux[i];
                        times[index++] = aux[j];
                        aux[i] = null;
                        aux[j] = null;
                        break;
                    }
                }
            }
        }
    }
    
    public void imp(){
        for(Time time : times)
            System.out.println(time);
    }
    
    public void organizaJogos(){
        int tam = times.length;
        int count = 0;
        try{
        for(int i=0; i<tam; i+=2){
            timeLocal[count].setText(times[i].getNome());
            timeVisitante[count].setText(times[i+1].getNome());
            count++;
        }
        }catch(Exception e){
            
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EdLo1;
    private javax.swing.JTextField EdLo2;
    private javax.swing.JTextField EdLo3;
    private javax.swing.JTextField EdLo4;
    private javax.swing.JTextField EdLo5;
    private javax.swing.JTextField EdVi1;
    private javax.swing.JTextField EdVi2;
    private javax.swing.JTextField EdVi3;
    private javax.swing.JTextField EdVi4;
    private javax.swing.JTextField EdVi5;
    private javax.swing.JLabel LTlo1;
    private javax.swing.JLabel LTlo2;
    private javax.swing.JLabel LTlo3;
    private javax.swing.JLabel LTlo4;
    private javax.swing.JLabel LTlo5;
    private javax.swing.JLabel LTvi1;
    private javax.swing.JLabel LTvi2;
    private javax.swing.JLabel LTvi3;
    private javax.swing.JLabel LTvi4;
    private javax.swing.JLabel LTvi5;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
