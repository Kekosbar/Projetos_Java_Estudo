package janela;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JList;
import script.*;

/**
 *
 * @author caique
 */
public class TimeToCamp extends javax.swing.JFrame {

    private Campeonato camp;
    private ArrayList<Time> times = new ArrayList<>();
    private ArrayList<Time> timeCamp = new ArrayList<>();
    
    public TimeToCamp(Campeonato camp) {
        this.camp = camp;
        initComponents();
        inicia();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JLtimes = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JLtimesCamp = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JLtimes.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        JLtimes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        JLtimes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLtimesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JLtimes);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jLabel1.setText("Incluir Times ao Novo Campeonato");

        jButton1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jButton1.setText("Finalizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jButton2.setText("Cancelar");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel2.setText("Times");

        JLtimesCamp.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        JLtimesCamp.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        JLtimesCamp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLtimesCampMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JLtimesCamp);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel3.setText("Times no campeonato");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(437, 437, 437)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(204, 204, 204)
                                .addComponent(jButton1)
                                .addGap(327, 327, 327)
                                .addComponent(jButton2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // FINALIZAR
        ConnectSQL sql = new ConnectSQL();
        // Cadastra campeonato
        sql.cadastrarCampeonato(camp);
        // Pegar id campeonato
        int id = sql.getIdCampeonato(camp.getNome());
        camp.setId(id);
        Iterator it = timeCamp.iterator();
        while(it.hasNext()){
            Time time = (Time) it.next();
            // Cadastra time no campeonato
            sql.cadastrarTime_Info(time, camp);
        }
        
        dispose();
        Principal janela = new Principal();
        janela.setVisible(true);
        janela.setLocation(300, 300);
        janela.setResizable(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JLtimesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLtimesMouseClicked
        // DUPLO CLICK TIMES
        JList list = (JList)evt.getSource();
        if (evt.getClickCount() == 2) {
            int index = list.locationToIndex(evt.getPoint());
            // inversão do valor boolean
            timeCamp.add(times.remove(index));
            definiLista(JLtimes, times);
            definiLista(JLtimesCamp, timeCamp);
        }
    }//GEN-LAST:event_JLtimesMouseClicked

    private void JLtimesCampMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLtimesCampMouseClicked
        // DUPLO CLICK TIMES_CAMP
        JList list = (JList)evt.getSource();
        if (evt.getClickCount() == 2) {
            int index = list.locationToIndex(evt.getPoint());
            // inversão do valor boolean
            times.add(timeCamp.remove(index));
            definiLista(JLtimesCamp, timeCamp);
            definiLista(JLtimes, times);
        }
    }//GEN-LAST:event_JLtimesCampMouseClicked

    public void inicia(){
        ConnectSQL sql = new ConnectSQL();
        Time aux[] = sql.getAllTimes();
        for(Time t : aux)
            times.add(t);
        
        definiLista(JLtimes, times);
    }
    
    public void definiLista(JList<String> JLista, ArrayList<Time> times){
        String nomes[] = new String[times.size()];
        Iterator it = times.iterator();
        int i=0;
        while(it.hasNext()){
            Time time = (Time) it.next();
            nomes[i++] = time.getNome();
        }
        JLista.setListData(nomes);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> JLtimes;
    private javax.swing.JList<String> JLtimesCamp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
