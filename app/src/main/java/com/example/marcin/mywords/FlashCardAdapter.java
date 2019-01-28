package com.example.marcin.mywords;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;

import java.util.List;
//managing data model - przypisywanie adaptera w activity poprzez RecyclerView.setAdapter
//na podstawie inputa adapter zwraca cala ilosc itemow - getItemCount
public class FlashCardAdapter
        extends RecyclerView.Adapter<FlashCardAdapter.FlashCardHolder> {

    //inflate
    public final LayoutInflater defInflater;
    //dane
    public List<FlashCard> flashCards;
    //context
    private Context mcontext;


    //referencje do view - nie trzeba jak w listview tworzyc findViewByID....
    //przypisanie odpowiednich TextView - w bind view laczenie z danymi
    static class FlashCardHolder extends RecyclerView.ViewHolder{
        private final TextView flashcardDefinition;
        private final TextView flashcardWord;
        private final LinearLayout parentLayout;

        private FlashCardHolder(View itemView){
            super(itemView);
            flashcardWord = itemView.findViewById(R.id.Word);
            flashcardDefinition = itemView.findViewById(R.id.Definition);
//            itemView.setOnClickListener(this);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
        /*@Override
        public void onClick(View v){
            int position = getAdapterPosition();
            FlashCard flashCard = new FlashCard(flashcardWord.getText().toString(),flashcardDefinition.getText().toString())
            Intent intent = new Intent()
        }*/
    }




    public FlashCardAdapter(Context context) {
        mcontext = context;
        this.defInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public FlashCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout dla poszczegolnych itemow poprzez inflate - konkretny layout dla indywidualnych elementow danych
        View view = defInflater.inflate(R.layout.row_change,parent,false);

        FlashCardHolder myViewHolder = new FlashCardHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FlashCardHolder holder,final int position) {
        if (flashCards!=null) {
            FlashCard current = flashCards.get(position);
            holder.flashcardDefinition.setText(current.getDefinitionDb());
            holder.flashcardWord.setText(current.getWordDb());
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext,ShowDefinitionActivity.class);
                    intent.putExtra("Word",flashCards.get(position).getWordDb());
                    intent.putExtra("Definition", flashCards.get(position).getDefinitionDb());
                    mcontext.startActivity(intent);
                }
            });
        }
        else {
            holder.flashcardDefinition.setText("No Defintions");
            holder.flashcardWord.setText("No Words Declared");
        }
    }
    void setFlashCards(List<FlashCard> flashCardsSet){
        flashCards = flashCardsSet;
        notifyDataSetChanged();
    }
    public FlashCard getFlashCardAt(int position){
        return flashCards.get(position);
    }
    @Override
    public int getItemCount() {
        if (flashCards != null)
            return flashCards.size();
        else return 0;
    }
}
