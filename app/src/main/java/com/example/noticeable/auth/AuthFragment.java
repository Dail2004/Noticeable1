package com.example.noticeable.auth;

import android.os.Bundle;

import androidx.core.text.TextUtilsCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noticeable.R;
import com.example.noticeable.databinding.FragmentAuthBinding;

public class AuthFragment extends Fragment {
    FragmentAuthBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAuthBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.codeButton.setOnClickListener(view -> {
            String number = binding.number.getText().toString().trim();
            if (TextUtils.isEmpty(number)){
                binding.number.setError("");
            }
        });
    }

}