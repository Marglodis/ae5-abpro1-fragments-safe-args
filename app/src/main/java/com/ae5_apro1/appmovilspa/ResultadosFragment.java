package com.ae5_apro1.appmovilspa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ae5_apro1.appmovilspa.databinding.FragmentPrincipalBinding;
import com.ae5_apro1.appmovilspa.databinding.FragmentResultadosBinding;

import java.util.List;

public class ResultadosFragment extends Fragment {

    private FragmentResultadosBinding binding;

    public ResultadosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultadosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener el argumento del Fragment Principal
        ResultadosFragmentArgs args = ResultadosFragmentArgs.fromBundle(getArguments());
        String palabra_clave = args.getTextoIngreso();

        binding.textReceived.setText(palabra_clave);

        // Inicializar el cargado rde Json
        JsonLoader jsonLoader = new JsonLoader(requireContext());
        List<FraseMotivacional> frases = jsonLoader.cargarFrases();

        // BUscar la frse que coincida con la palabra clave
        String fraseAMostrar = buscarFrasePorPalabraClave(frases, palabra_clave);

        // Mostrar el texto en el TextView
        binding.textViewReceived.setText('"' + fraseAMostrar + '"');

        binding.buttonVolver.setOnClickListener(v->{
            Navigation.findNavController(v).popBackStack();
        });
    }

    private String buscarFrasePorPalabraClave(List<FraseMotivacional> frases, String palabraClave) {
        for (FraseMotivacional frase : frases) {
            if (frase.getPalabraClave().equalsIgnoreCase(palabraClave)) {
                return frase.getFrase().replace("[INPUT]", palabraClave);
            }
        }
        // Mensaje por defecto si no se encuentra la palabra clave
        return "Lo siento, no encontramos una frase motivacional para '" + palabraClave + "'.";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ///  Ajuro hay que limpiarlo para evitar fugas de memoeria
        binding = null;
    }
}