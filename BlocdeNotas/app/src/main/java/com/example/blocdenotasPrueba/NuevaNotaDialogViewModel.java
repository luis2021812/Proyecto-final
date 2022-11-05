package com.example.blocdenotasPrueba;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.blocdenotasPrueba.db.Entinty.NotaEntinty;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntinty>> allNotas;
    private NotaRepository notaRepository;

    public NuevaNotaDialogViewModel(Application application){
        super(application);
        notaRepository =  new NotaRepository(application);
        allNotas = notaRepository.getAll();

    }

    // El fragmento que necesita recibir la lista de datos
    public LiveData<List<NotaEntinty>> getAllNotas() { return allNotas; }

    // El fragmento que ingrese una nueva nota, debera comunicarlo a este ViewModel
    public void insertarNota(NotaEntinty nuevanotaEntinty) { notaRepository.insert(nuevanotaEntinty); }

    public void updateNota(NotaEntinty notaActualizarEntinty) {
        notaRepository.update(notaActualizarEntinty);
    }
}