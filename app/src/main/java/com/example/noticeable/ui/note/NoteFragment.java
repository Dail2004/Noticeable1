package com.example.noticeable.ui.note;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.noticeable.R;
import com.example.noticeable.databinding.FragmentNoteBinding;

public class NoteFragment extends Fragment {
    FragmentNoteBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater,container,false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);


        getTitle(navController);

        return binding.getRoot(); }

    private void getTitle(NavController navController) {
        binding.ready.setOnClickListener(view -> {

            String title = binding.ready.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            Log.e("TAG", "onClick: " + bundle.toString() );
            navController.navigateUp();
        });
    }
}