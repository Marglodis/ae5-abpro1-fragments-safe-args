package com.ae5_apro1.appmovilspa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ae5_apro1.appmovilspa.databinding.FragmentPrincipalBinding;

public class PrincipalFragment extends Fragment {

    private FragmentPrincipalBinding binding;

    public PrincipalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPrincipalBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // Uso de textWatcher para mostrar el resultado en el TextView en timepo real
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // COnfigurar el TextWatcher para el EditText
        binding.editTextBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No es necesario implementar este método

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Actualizar el TextView con el texto ingresado
                binding.textViewPreview.setText(s.toString());
            }
        });

        // COnfigurar el botón para navegar al FragmentResultados
        binding.buttonBuscar.setOnClickListener(v -> {
            // Obtener el texto de la búsqueda
            String busqueda = binding.editTextBusqueda.getText().toString().trim();
            // VAlidar que no esté vacío
            if (busqueda.isEmpty()) {
                binding.editTextBusqueda.setError("Por favor ingrese un término de búsqueda");
                return;
            }

            // Navegar a Fragment B pasando el argumento con SafeArgs
            PrincipalFragmentDirections.ActionPrincipalFragmentToResultadosFragment action = PrincipalFragmentDirections.actionPrincipalFragmentToResultadosFragment(busqueda);
            // Navega usando el NavController
            Navigation.findNavController(view).navigate(action);
        });
    }
}
