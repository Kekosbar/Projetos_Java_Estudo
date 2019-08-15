package script;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import janela.JAN_Principal;

public class Tocador {
    
    private FileInputStream fis;
    private BufferedInputStream bis;
    private Player player;
    private long pauseLocation;
    private long songTotalLength;
    private String fileLcation;
    
    private Timer timer;
    private CronogramaGambi cronometro;
    private boolean tocando = false;
    private boolean stopTotal = true; // Somente para o stop
    
    public Tocador(String music){
        this.fileLcation = ArquivaMusic.pasta+"\\"+music;
        //ArquivaMusic.reservaDeleteMusic(music);
    }
    
    public void stop(){
        if(player == null && stopTotal == true) return;
        player.close();
        pauseLocation = 0;
        stopTotal = true;
        tocando = false;
        cronometro.cancel();
        timer.cancel();
    }
    
    public void pause(){
        if(player != null && tocando == false) 
            return;
        try {
            cronometro.cancel();
            timer.cancel();
            pauseLocation = fis.available();
            player.close();
            tocando = false;
        } catch (IOException ex) {
            Logger.getLogger(Tocador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void play(){
        try{
            fis = new FileInputStream(fileLcation);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
            
            songTotalLength = fis.available();
            tocando = true;
            stopTotal = false;
        } catch (FileNotFoundException | JavaLayerException ex) {
            Logger.getLogger(Tocador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tocador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread(){
            @Override
            public void run() {
                try {
                    player.play();
                    
                    if(player.isComplete()){
                        if(AutoPlay.isRepeti()) {
                            cronometro.cancel();
                            timer.cancel();
                            play();
                        }
                        else JAN_Principal.proximaMusica();
                    }
                } catch (JavaLayerException | IOException ex) {
                    Logger.getLogger(Tocador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }.start();
        timer = new Timer();
        cronometro = new CronogramaGambi(JAN_Principal.JLtimer);
        timer.schedule(cronometro, 1000, 1000);
    }
    
    
    public void resume(){
        if(tocando == true) return;
        try{
            int tempo[] = cronometro.getTime();
            timer = new Timer();
            cronometro = new CronogramaGambi(JAN_Principal.JLtimer);
            cronometro.setTimer(tempo[1], tempo[0]);
            timer.schedule(cronometro, 1000, 1000);
            
            fis = new FileInputStream(fileLcation);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
            
            fis.skip(songTotalLength - pauseLocation);
            tocando = true;
        } catch (FileNotFoundException | JavaLayerException ex) {
            Logger.getLogger(Tocador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tocador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread(){
            @Override
            public void run() {
                try {
                    player.play();
                    
                    if(player.isComplete()){
                        if(AutoPlay.isRepeti()){
                            cronometro.cancel();
                            timer.cancel();
                            play();
                        }
                        else JAN_Principal.proximaMusica();
                    }
                } catch (JavaLayerException | IOException ex) {
                    Logger.getLogger(Tocador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }.start();
    }

    public boolean isTocando() {
        return tocando;
    }

    public boolean isStopTotal() {
        return stopTotal;
    }
    
}
