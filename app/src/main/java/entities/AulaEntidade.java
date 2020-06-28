package entities;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import persistence.DAOSQLiteAula;

public class AulaEntidade implements Serializable {

    private String nome;
    private String descricao;
    private String chave_url;
    private Boolean concluido;
    private String pergunta;
    private String resposta;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getChave_url() {
        return chave_url;
    }

    public void setChave_url(String chave_url) {
        this.chave_url = chave_url;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public static void insere(Activity activity, AulaEntidade aula){
        Log.i("nome ", aula.getNome());
        SQLiteDatabase db = DAOSQLiteAula.getDBInstance(activity);

        String[] arrayParam= new String[6];
        arrayParam[0]=aula.getNome();
        arrayParam[1]=aula.getDescricao();
        arrayParam[2]=aula.getChave_url();
        arrayParam[3]=aula.getConcluido().toString();
        arrayParam[4]=aula.getPergunta();
        arrayParam[5]=aula.getResposta();
        db.execSQL("insert into aula (nome, descricao, chave_url, concluido, pergunta, resposta) values(?,?,?,?,?,?)", arrayParam);
    }

    public static void concluirAula(Activity activity, AulaEntidade aula){
        Log.i("Concluir ", aula.getNome());
        SQLiteDatabase db = DAOSQLiteAula.getDBInstance(activity);

        db.execSQL("update aula set concluido = 1 where nome = '"+aula.getNome()+"'");
    }

    /*public static void limparAulas(Activity activity){
        SQLiteDatabase db = DAOSQLiteAula.getDBInstance(activity);

        db.execSQL("delete from aula");
    }

    public static void excuirAula(Activity activity, String nome){
        SQLiteDatabase db = DAOSQLiteAula.getDBInstance(activity);

        db.execSQL("delete from aula where nome = '" + nome + "'");
    }*/

    public static List<AulaEntidade> buscaTodos(Activity activity, String filter){
        String sqlWhere = "";

        if(filter.length() > 0){
            sqlWhere = " where upper(nome) like upper('%"+filter+"%') or upper(descricao) like upper('%"+filter+"%') ";
        }

        ArrayList<AulaEntidade> lista= new ArrayList<AulaEntidade>();
        Cursor cursor= DAOSQLiteAula.getDBInstance(activity).rawQuery("select " +
                "nome," +
                " descricao," +
                " chave_url," +
                " concluido," +
                " pergunta," +
                " resposta" +
                " from aula "+ sqlWhere +" order by nome",null);

        while(cursor.moveToNext()) {
            AulaEntidade aula = new AulaEntidade();
            String nome = cursor.getString(0);
            String descricao = cursor.getString(1);
            String chave_url = cursor.getString(2);
            Boolean concluido = cursor.getInt(3) > 0;
            String pergunta = cursor.getString(4);
            String resposta = cursor.getString(5);

            aula.setNome(nome);
            aula.setDescricao(descricao);
            aula.setChave_url(chave_url);
            aula.setConcluido(concluido);
            aula.setPergunta(pergunta);
            aula.setResposta(resposta);
            lista.add(aula);
            Log.i("SQLLite", descricao);
        }

        return lista;
    }
}
