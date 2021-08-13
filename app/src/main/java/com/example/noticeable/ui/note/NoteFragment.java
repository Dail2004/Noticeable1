package com.example.noticeable.ui.note;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noticeable.R;
import com.example.noticeable.constant.Constants;
import com.example.noticeable.databinding.FragmentNoteBinding;

public class NoteFragment extends Fragment {
    FragmentNoteBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        getTitle(navController);
        return binding.getRoot();
    }

    private void getTitle(NavController navController) {
        binding.ready.setOnClickListener(v -> {
            String text = binding.title.getText().toString().trim();
            if (TextUtils.isEmpty(text)) {
                binding.title.setError("Вы не правильно ввели");
                return;
            } else {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.BUNDLE_KEY, text);
                getParentFragmentManager().setFragmentResult(Constants.REQUEST_KEY, bundle);
                Log.e("TAG", "onClick" + bundle);
                close();
            }
        });
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigateUp();
    }

    @Override
    public void onResume() {
        super.onResume();
        androidx.appcompat.app.ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }
}