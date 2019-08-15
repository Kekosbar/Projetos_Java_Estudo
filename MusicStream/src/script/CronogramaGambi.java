package script;

import java.util.TimerTask;
import javax.swing.JLabel;

public class CronogramaGambi extends TimerTask {
	
    private JLabel JLtimer;
    
    public CronogramaGambi(JLabel JLtimer){
        this.JLtimer = JLtimer;
    }
    
    private int minuto = 0;
    private int segundo = 0;

    /**
     * @param args
     * @throws
     */
    public void run() {
        JLtimer.setText(String.format("%02d:%02d", minuto, segundo));
        // Processa o timer
        segundo++;
        if (segundo == 59) {
            segundo = 0;
            minuto++;
        }
    }
    
    public void setTimer(int minutos, int segundos){
        this.minuto = minutos;
        this.segundo = segundos;
    }
    
    public int[] getTime(){
        int tempo[] = new int[2];
        tempo[0] = segundo;
        tempo[1] = minuto;
        return tempo;
    }
}
