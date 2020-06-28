package persistence;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DAOSQLiteAula extends SQLiteOpenHelper {

    private static final String dbname= "curso";

    public DAOSQLiteAula(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table aula (" +
                "chave integer primary key autoincrement, " +
                "nome varchar(100), " +
                "descricao varchar(2000), " +
                "chave_url varchar(500), " +
                "concluido bool, " +
                "pergunta varchar(1000), " +
                "resposta varchar(1000))");
        db.execSQL("insert into aula (nome, descricao, chave_url, concluido, pergunta, resposta) values(" +
                "'Aula 1 - Introdução'," +
                "'Explicação e conceitos sobre aplicações android e seu desenvolvimento.'," +
                "'MnJEbS5p3kQ'," +
                "0," +
                "'Quais linguagens de programação disponíveis para criar App no Android Studio?'," +
                "'Java ou Kotlin')");
        db.execSQL("insert into aula (nome, descricao, chave_url, concluido, pergunta, resposta) values(" +
                "'Aula 2 - Preparando o ambiente'," +
                "'Passo a Passo para instalação do ambiente android e explicação sobre o seu funcionamento.'," +
                "'MnJEbS5p3kQ'," +
                "0," +
                "'Quais linguagens de programação disponíveis para criar App no Android Studio?'," +
                "'Java ou Kotlin')");
        db.execSQL("insert into aula (nome, descricao, chave_url, concluido, pergunta, resposta) values(" +
                "'Aula 3 - Criando o layout'," +
                "'Explicando estrutura das activities e como se montam as telas.'," +
                "'MnJEbS5p3kQ'," +
                "0," +
                "'Quais linguagens de programação disponíveis para criar App no Android Studio?'," +
                "'Java ou Kotlin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static SQLiteDatabase getDBInstance(Activity activity){
        DAOSQLiteAula dao = new DAOSQLiteAula(activity.getBaseContext(), DAOSQLiteAula.dbname , null, 1);
        return dao.getWritableDatabase();
    }
}
