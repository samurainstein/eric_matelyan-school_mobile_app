package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    private List<TermEntity> listTerms;
    private final Context context;
    private final LayoutInflater inflater;

    class TermViewHolder extends RecyclerView.ViewHolder {

        private final TextView termItemView;
        private String startDateText;
        private String endDateText;


        private TermViewHolder(View itemView) {
            super(itemView);
            termItemView = itemView.findViewById(R.id.list_item_text);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final TermEntity current = listTerms.get(position);

                    startDateText = DateConverter.dateToString(current.getStartDate());
                    endDateText = DateConverter.dateToString(current.getEndDate());
                    Intent intent = new Intent(context, TermsDetailActivity.class);
                    intent.putExtra("termId", current.getTermId());
                    intent.putExtra("termName", current.getTermName());
                    intent.putExtra("startDate", startDateText);
                    intent.putExtra("endDate", endDateText);
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
