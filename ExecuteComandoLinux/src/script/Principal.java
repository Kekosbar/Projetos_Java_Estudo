package script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {

    public static void main(String[] arg){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "python3 /home/caique/./tccExibeGraficos.py");
        
        try {

            processBuilder.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
