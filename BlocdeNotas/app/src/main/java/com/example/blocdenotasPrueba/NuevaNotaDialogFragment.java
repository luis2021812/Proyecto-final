package com.example.blocdenotasPrueba;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.blocdenotasPrueba.db.Entinty.NotaEntinty;

public class NuevaNotaDialogFragment extends DialogFragment {





    public static NuevaNotaDialogFragment newInstance() {
        return new NuevaNotaDialogFragment();
    }

    private View view;
    private EditText etTitulo, etCotenido;
    private RadioGroup rgColor;
    private Switch snotaFavorita;




    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nueva nota");
        builder.setMessage("Instroduzca los datos de la nota ")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        String titulo = etTitulo.getText().toString();
                        String contenido = etCotenido.getText().toString();
                        String color = "Azul";
                        switch (rgColor.getCheckedRadioButtonId()) {
                            case R.id.radioButtonColorRojo:
                                color = "Rojo";
                                break;
                            case R.id.radioButtonColorVerde:
                                color = "Verde";
                                break;
                        }

                        boolean esFavorita = snotaFavorita.isChecked();

                        //Comunicar al ViewModel que se creara
                        NuevaNotaDialogViewModel mViewModel = new ViewModelProvider(getActivity()).get(NuevaNotaDialogViewModel.class);
                        mViewModel.insertarNota(new NotaEntinty(titulo, contenido, esFavorita, color));
                        //linea diferente
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("Cancelar", (dialog, id) -> {
                    dialog.dismiss();
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_nueva_nota_dialog, null);
        etTitulo = view.findViewById(R.id.editTextTitulo);
        etCotenido = view.findViewById(R.id.editTextContenido);
        rgColor = view.findViewById(R.id.radioGrupColor);
        snotaFavorita = view.findViewById(R.id.switch1);
        builder.setView(view);

        return builder.create();
    }

}