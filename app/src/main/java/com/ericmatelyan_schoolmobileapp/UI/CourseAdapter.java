package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<CourseEntity> listCourses;
    private final Context context;
    private final LayoutInflater inflater;

    class CourseViewHolder extends RecyclerView.ViewHolder {

        private final TextView courseItemView;

        private CourseViewHolder(View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.list_item_text);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final CourseEntity current = listCourses.get(position);

                    Intent intent = new Intent(context, CourseDetailsActivity.class);
                    intent.putExtra("course", current);
                    context.startActivity(intent);
                }
            });
        }
    }

    public CourseAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if(listCourses != null) {
            final CourseEntity current = listCourses.get(position);
            holder.courseItemView.setText(current.getCourseName());
        }
        else {
            holder.courseItemView.setText("No Course Name");
        }
    }

    public void setCourses(List<CourseEntity> courses) {
        listCourses = courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listCourses != null)
            return listCourses.size();
        else
            return 0;
    }
}
