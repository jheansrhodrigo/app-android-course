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
        db.execSQL("create table aula (chave integer primary key autoincrement, nome varchar(100), descricao varchar(2000), url varchar(500), concluido bool)");
        db.execSQL("insert into aula (nome, descricao, url, concluido) values('Aula 1 - Introdução','Explicação e conceituação sobre aplicações android e seu desenvolvimento','https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4', 0)");
        db.execSQL("insert into aula (nome, descricao, url, concluido) values('Aula 2 - Preparando o ambiente'," +
                "'Passo a Passo para instalação do ambiente android e explicação sobre o seu funcionamento','https://www.youtube.com/watch?v=k0fNMXAfqFc', 0)");
        db.execSQL("insert into aula (nome, descricao, url, concluido) values('Aula 3 - Criando o layout'," +
                "'Explicando estrutura das activityes e como se montam as telas.','https://www.youtube.com/watch?v=k0fNMXAfqFc', 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static SQLiteDatabase getDBInstance(Activity activity){
        DAOSQLiteAula dao = new DAOSQLiteAula(activity.getBaseContext(), DAOSQLiteAula.dbname , null, 1);
        return dao.getWritableDatabase();
    }
}
