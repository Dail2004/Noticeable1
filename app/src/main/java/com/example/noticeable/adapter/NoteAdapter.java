package com.example.noticeable.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noticeable.databinding.NoteItemBinding;
import com.example.noticeable.model.NoteModel;
import com.example.noticeable.room.NoteDao;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    List<NoteModel> list = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void addTaskModel(NoteModel noteModel) {
        list.add(noteModel);
        notifyDataSetChanged();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void filteredList(ArrayList<NoteModel> upFilteredList) {
        list = upFilteredList;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<NoteModel> list1) {
        list.clear();
        list.addAll(list1);
        notifyDataSetChanged();
    }

    public NoteModel getNoteAt(int position) {
        return list.get(position);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void delete(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(NoteItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
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
        NoteItemBinding binding;

        public MyViewHolder(@NonNull NoteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void OnBind(NoteModel s) {
            binding.date.setText(s.getDate());
            binding.itemTitle.setText(s.getTextNote());
        }
    }
}

