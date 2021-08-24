package com.example.noticeable.onBoard;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.noticeable.R;
import com.example.noticeable.adapter.OnBoardAdapter;
import com.example.noticeable.constant.PrefHelper;
import com.example.noticeable.databinding.FragmentOnBoardBinding;
import com.example.noticeable.model.OnBoardModel;

import java.util.ArrayList;
import java.util.List;

public class OnBoardFragment extends Fragment {
    private FragmentOnBoardBinding binding;
    OnBoardAdapter adapter;
    int list[];
    TextView[] dots;
    List<OnBoardModel> listt;
    boolean aBoolean = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        View view = inflater.inflate(R.layout.fragment_on_board, container, false);
        dotsMethod();
        setupOnBoardingFragment(navController);
        if (aBoolean){
            selectedIndicator(0);
        }
        listener();
        skipButton();
        return binding.getRoot();
    }

    private void start() {
        binding.startWork.setOnClickListener(view -> {
            PrefHelper.setOnBoardIsShow();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigateUp();
        });
    }

    private void listener() {
        binding.scrollView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (binding.scrollView.getCurrentItem() == 0||binding.scrollView.getCurrentItem() == 1){
                    binding.skip.setText("Пропустить");
                }else{
                    binding.skip.setText("");
                }
                if (position==2){
                    binding.startWork.setText("Начать работу");
                }else{
                    binding.startWork.setText("");
                }
                aBoolean=false;
                selectedIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void selectedIndicator(int position) {
        for (int i = 0; i <dots.length; i++) {
            if (i==position){
                dots[i].setTextColor(list[position]);
            }else{
                dots[i].setTextColor(getResources().getColor(R.color.black_white));
            }
        }
    }

    private void dotsMethod() {
        list=new int[3];
        list[0]=getResources().getColor(R.color.black_orange);
        list[1]=getResources().getColor(R.color.black_orange);
        list[2]=getResources().getColor(R.color.black_orange);

        dots=new TextView[3];
        dotsIndicator();
    }

    private void dotsIndicator() {
        for (int i = 0; i < dots.length; i++) {
            dots[i]=new TextView(requireActivity());
            dots[i].setText(Html.fromHtml("&#9679"));
            dots[i].setTextSize(18);
            binding.onBoardingIndicator.addView(dots[i]);
        }
    }

    private void skipButton() {
    }

    private void setupOnBoardingFragment(NavController navController) {
        listt = new ArrayList<>();
        listt.add(new OnBoardModel("Очень удобный функционал", R.drawable.animation));
        listt.add(new OnBoardModel("Быстрый, качественный продукт", R.drawable.cutting));
        listt.add(new OnBoardModel("Куча функций и интересных фишек", R.drawable.desing));
        adapter = new OnBoardAdapter(listt, getActivity().getSupportFragmentManager());
        binding.scrollView.setAdapter(adapter);

        binding.skip.setOnClickListener(view -> {
            if (binding.scrollView.getCurrentItem()==0||binding.scrollView.getCurrentItem()==1){
                binding.scrollView.setCurrentItem(2);
            }
        });
        start();
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