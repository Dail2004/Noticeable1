package com.example.noticeable.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.noticeable.R;
import com.example.noticeable.adapter.NoteAdapter;
import com.example.noticeable.constant.App;
import com.example.noticeable.constant.Constants;
import com.example.noticeable.databinding.FragmentHomeBinding;
import com.example.noticeable.model.NoteModel;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private NoteAdapter adapter;
    private boolean isDashboard = false;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    LinearLayoutManager linearLayoutManager;

    List<NoteModel> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        linearLayoutManager = new LinearLayoutManager(requireContext());
        initView();
        textTitle();
        getDataFromDB();
        search();
    }

    private void textTitle() {
        getParentFragmentManager().setFragmentResultListener(Constants.REQUEST_KEY, getViewLifecycleOwner(), (requestKey, result) -> {
            NoteModel model = (NoteModel) result.getSerializable(Constants.BUNDLE_KEY);
            adapter.addTaskModel(model);
            if (isDashboard) {
                binding.recyclerView.setLayoutManager(staggeredGridLayoutManager);
            } else {
                binding.recyclerView.setLayoutManager(linearLayoutManager);
            }
        });
    }

    private void getDataFromDB() {
        App.initDatabase(requireContext()).getDao().getAll().observe(getViewLifecycleOwner(), noteModels -> {
            adapter.setList(noteModels);
            list = noteModels;
        });
    }

    private void search() {
        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String toString) {
        ArrayList<NoteModel> filterList = new ArrayList<>();
        for (NoteModel item : list) {
            if (item.getTextNote().toLowerCase().contains(toString.toLowerCase())) {
                filterList.add(item);
            }
        }
        adapter.filteredList(filterList);
    }

    private void initView() {
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                App.database.getDao().delete(list.get(viewHolder.getAdapterPosition()));
                adapter.delete(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(binding.recyclerView);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NoteAdapter();
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.dashboard) {
            isDashboard = !isDashboard;
            if (isDashboard) {
                item.setIcon(R.drawable.ic_format_list);
            } else {
                item.setIcon(R.drawable.ic_dashboard);
            }
            binding.recyclerView.setLayoutManager(isDashboard ? staggeredGridLayoutManager : linearLayoutManager);
        }
        return super.onOptionsItemSelected(item);
    }
}
