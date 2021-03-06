package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    private List<TermEntity> listTerms;
    private final Context context;
    private final LayoutInflater inflater;

    class TermViewHolder extends RecyclerView.ViewHolder {

        private final TextView termItemView;


        private TermViewHolder(View itemView) {
            super(itemView);
            termItemView = itemView.findViewById(R.id.list_item_text);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final TermEntity current = listTerms.get(position);

                    Intent intent = new Intent(context, TermDetailsActivity.class);
                    intent.putExtra("term", current);
                    context.startActivity(intent);
                }
            });
        }
    }

    public TermAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public TermViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(listTerms != null) {
            final TermEntity current = listTerms.get(position);
            holder.termItemView.setText(current.getTermName());
        }
        else {
            holder.termItemView.setText("No Term Name");
        }
    }

    public void setTerms(List<TermEntity> terms) {
        listTerms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listTerms != null)
            return listTerms.size();
        else
            return 0;
    }
}
