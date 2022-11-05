package com.example.blocdenotasPrueba.db.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.blocdenotasPrueba.db.Entinty.NotaEntinty;

import java.util.List;

@Dao
public interface NotaDao {
    @Insert
    void insert(NotaEntinty nota);
    @Update
    void update(NotaEntinty nota);
    @Query("DELETE FROM notas")
    void deleteALL();
    @Query("DELETE FROM notas WHERE id = :idNota")
    void deleteId(int idNota);
    @Query("SELECT * FROM notas ORDER BY titulo ASC")
    LiveData<List<NotaEntinty>> getAll();
    @Query("SELECT * FROM notas WHERE favorita LIKE 'true'")
    LiveData<List<NotaEntinty>> getALLFavoritas();

}
