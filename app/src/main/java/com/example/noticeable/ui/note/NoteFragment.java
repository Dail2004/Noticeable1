package com.example.noticeable.ui.note;

import android.annotation.SuppressLint;
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
import com.example.noticeable.model.NoteModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteFragment extends Fragment {
    FragmentNoteBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        getTitle(navController);
        return binding.getRoot();
    }

    public String getDataConverter() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MMMM HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    private void getTitle(NavController navController) {
        binding.back.setOnClickListener(view -> {
            close();
        });
        binding.date.setText(getDataConverter());

        binding.ready.setOnClickListener(v -> {
            String text = binding.title.getText().toString().trim();
            if (TextUtils.isEmpty(text)) {
                binding.title.setError("Вы не правильно ввели");
            } else {
                NoteModel model = new NoteModel(text, getDataConverter());
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.BUNDLE_KEY, model);
                bundle.putSerializable("time", model);
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