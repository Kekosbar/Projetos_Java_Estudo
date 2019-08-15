package script;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cadastro implements Serializable{

    private String nome;
    private String senha;
    private String email;
    private Date dataNasc;
    private char sexo; // 'm' masculino / 'f' feminino

    public Cadastro(String nome, String senha, String email, char sexo, Date dataNasc) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    @Override
    public String toString() {
        String s = String.format("Nome: %s\nSenha: %s", nome, senha);
        return s;
    }

    public String getStringDataNasc(String formato){
        SimpleDateFormat textFormat = new SimpleDateFormat(formato);
        return textFormat.format(dataNasc);
    }

}