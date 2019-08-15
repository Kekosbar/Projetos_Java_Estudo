package script;

import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        ArrayList<String> datas = ConnectSQL.getInstancia().getAllDoacaoComunidade();
        System.out.println(""+datas.size());
        ArrayList<String> novas = new ArrayList<>();
        for(String data : datas)
            novas.add(Formatar.dataToSQL(data));
        System.out.println("Começou...");
//        for(String data : novas)
//            System.out.println(data);
        for(int i=0; i<novas.size(); i++){
            ConnectSQL.getInstancia().atualizar(datas.get(i), novas.get(i));
        }
        System.out.println("Terminou");
    }
    
}
