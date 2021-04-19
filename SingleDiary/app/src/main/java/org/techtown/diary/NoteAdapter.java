package org.techtown.diary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> implements OnNoteItemClickListener{

    ArrayList<Note> items = new ArrayList<Note>();

    OnNoteItemClickListener listener;

    int layoutType = 0;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.note_item, viewGroup, false);

        return new ViewHolder(itemView, this, layoutType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position){
        Note item = items.get(position);
        viewHolder.setItem(item);
        viewHolder.setLayoutType(layoutType);
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public void addItem(Note item){
        items.add(item);
    }

    public void setItems(ArrayList<Note> items){
        this.items = items;
    }

    public Note getItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnNoteItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position){
        if(listener!=null){
            listener.onItemClick(holder, view, position);
        }
    }

    public void switchLayout(int position){
        layoutType = position;
    }

}
