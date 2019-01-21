package com.example.marcin.mywords;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import java.util.List;

public class FlashCardAdapter
        extends RecyclerView.Adapter<FlashCardAdapter.FlashCardHolder> {

    private int flashcardItemLayout;
    private List<FlashCard> flashCards;

    public FlashCardAdapter(int flashcardItemLayout) {
        this.flashcardItemLayout = flashcardItemLayout;
    }

    static class FlashCardHolder extends RecyclerView.ViewHolder{
        private final TextView flashcardItemView;

        private FlashCardHolder(View itemView){
            super(itemView);
            flashcardItemView = itemView.findViewById(R.id.textView);
        }
    }


    @NonNull
    @Override
    public FlashCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(flashcardItemLayout,parent,false);
        FlashCardHolder myViewHolder = new FlashCardHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FlashCardHolder holder,final int position) {
        if (flashCards!=null) {
            TextView item = holder.flashcardItemView;
            item.setText(flashCards.get(position).getDefinitionDb());
        }
        else {
            holder.flashcardItemView.setText("No Defintions");
        }
    }

    @Override
    public int getItemCount() {
        if (flashCards != null)
            return flashCards.size();
        else return 0;
    }
}
