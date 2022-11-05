package com.example.blocdenotasPrueba.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.blocdenotasPrueba.db.Dao.NotaDao;
import com.example.blocdenotasPrueba.db.Entinty.NotaEntinty;

@Database(entities = {NotaEntinty.class}, version = 1)
public abstract class NotaRoomDataBase extends RoomDatabase {
    public abstract NotaDao notaDao();
    private static volatile NotaRoomDataBase INSTANCE;

    public static NotaRoomDataBase getDataBase(final Context context){
        if(INSTANCE == null){
            synchronized (NotaRoomDataBase.class) {
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotaRoomDataBase.class, "notasDataBase")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
