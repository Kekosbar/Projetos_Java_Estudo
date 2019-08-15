package script;

import java.util.regex.Pattern;

public class Formatar {
    
    public static String dataToSQL(String data){
        String s[] = data.split(Pattern.quote("/"));
        String nova = null;
        if(s.length == 3)
            nova = s[0]+"-"+ s[1] +"-"+ s[2];
        return nova;
    }
    
}
