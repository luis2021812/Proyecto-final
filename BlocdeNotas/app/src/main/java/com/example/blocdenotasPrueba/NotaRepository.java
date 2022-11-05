package com.example.blocdenotasPrueba;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.blocdenotasPrueba.db.Dao.NotaDao;
import com.example.blocdenotasPrueba.db.Entinty.NotaEntinty;
import com.example.blocdenotasPrueba.db.NotaRoomDataBase;

import java.util.List;

public class NotaRepository {
    private NotaDao notaDao;
    private LiveData<List<NotaEntinty>> allNotas;
    private LiveData<List<NotaEntinty>> allNotasFavoritas;

    public NotaRepository(Application application) {
        NotaRoomDataBase db = NotaRoomDataBase.getDataBase(application);
        notaDao = db.notaDao();
        allNotas = notaDao.getAll();
        allNotasFavoritas = notaDao.getALLFavoritas();
    }

    public LiveData<List<NotaEntinty>> getAll() { return allNotas; }

    public LiveData<List<NotaEntinty>> getAllFavs() { return allNotasFavoritas; }

    public void insert(NotaEntinty nota) {
        new insertAsyncTack(notaDao).execute(nota);
    }

    private static class insertAsyncTack extends AsyncTask<NotaEntinty, Void, Void> {
        private NotaDao notaDatoAsyncTask;

        insertAsyncTack(NotaDao dao){
            notaDatoAsyncTask = dao;
        }
        @Override
        protected Void doInBackground(NotaEntinty... notaEntinties) {
            notaDatoAsyncTask.insert(notaEntinties[0]);
            return null;
        }
    }

    public void update (NotaEntinty nota) { new updateAsyncTack(notaDao).execute(nota); }

    private static class updateAsyncTack extends AsyncTask<NotaEntinty, Void, Void> {
        private NotaDao notaDatoAsyncTask;

        updateAsyncTack(NotaDao dao){
            notaDatoAsyncTask = dao;
        }
        @Override
        protected Void doInBackground(NotaEntinty... notaEntinties) {
            notaDatoAsyncTask.update(notaEntinties[0]);
            return null;
        }
    }
}
