package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ericmatelyan_schoolmobileapp.Entity.AssignmentEntity;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.R;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {

    private List<AssignmentEntity> listAssignments;
    private final Context context;
    private final LayoutInflater inflater;

    class AssignmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView assignmentItemView;

        private AssignmentViewHolder(View itemView) {
            super(itemView);
            assignmentItemView = itemView.findViewById(R.id.list_item_text);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final AssignmentEntity current = listAssignments.get(position);

                    Intent intent = new Intent(context, AssignmentDetailsActivity.class);
                    intent.putExtra("assignment", current);
                    context.startActivity(intent);
                }
            });
        }
    }

    public AssignmentAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new AssignmentAdapter.AssignmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentAdapter.AssignmentViewHolder holder, int position) {
        if (listAssignments != null) {
            final AssignmentEntity current = listAssignments.get(position);
            holder.assignmentItemView.setText(current.getAssignmentName());
        } else {
            holder.assignmentItemView.setText("No Course Name");
        }
    }

    public void setAssignments(List<AssignmentEntity> assignments) {
        listAssignments = assignments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (listAssignments != null)
            return listAssignments.size();
        else
            return 0;
    }
}

