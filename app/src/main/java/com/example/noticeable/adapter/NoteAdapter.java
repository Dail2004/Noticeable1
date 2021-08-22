package com.example.noticeable.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noticeable.databinding.NoteItemBinding;
import com.example.noticeable.model.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder>{

    ArrayList<NoteModel> list = new ArrayList<>();
    NoteItemBinding binding;

    @SuppressLint("NotifyDataSetChanged")
    public void addTaskModel(NoteModel noteModel) {
        list.add(noteModel);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filteredList(ArrayList<NoteModel> upFilteredList){
        list=upFilteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = NoteItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.OnBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        private void OnBind(NoteModel s) {
            binding.date.setText(s.getDate());
            binding.itemTitle.setText(s.getTextNote());
        }
    }
}
