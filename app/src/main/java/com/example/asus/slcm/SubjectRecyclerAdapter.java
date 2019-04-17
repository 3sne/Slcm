package com.example.asus.slcm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class SubjectRecyclerAdapter extends RecyclerView.Adapter<SubjectRecyclerAdapter.SubjectViewHolder> {

    private Context mContext;
    private List<Subject> mSubjectList;

    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectName, atdPer, totalClasses, presentClasses, bunkedClasses;

        public SubjectViewHolder(View view) {
            super(view);
            subjectName = (TextView) view.findViewById(R.id.subjectName);
            atdPer = (TextView) view.findViewById(R.id.atdPer);
            totalClasses = (TextView) view.findViewById(R.id.totalClasses);
            presentClasses = (TextView) view.findViewById(R.id.presentClasses);
            bunkedClasses = (TextView) view.findViewById(R.id.bunkedClasses);
        }
    }

    public SubjectRecyclerAdapter(Context mContext, List<Subject> mSubjectList) {
        this.mContext = mContext;
        this.mSubjectList = mSubjectList;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rview_item, parent, false);
        return new SubjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SubjectViewHolder holder, final int position) {
        Subject subject = mSubjectList.get(position);
        Log.d("HERE", "What up boioiiiii" + position);
        holder.subjectName.setText(subject.getmSubjectName());
        holder.atdPer.setText(subject.getmAttendancePercentage() + "%");
        holder.presentClasses.setText(subject.getmDaysPresent());
        holder.bunkedClasses.setText(subject.getmDaysAbsent());
        holder.totalClasses.setText(subject.getmTotalClasses());
    }

    @Override
    public int getItemCount() {
        return mSubjectList.size();
    }

}
