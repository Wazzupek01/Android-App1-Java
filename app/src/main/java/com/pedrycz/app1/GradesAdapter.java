package com.pedrycz.app1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class GradesAdapter extends RecyclerView.Adapter<GradesAdapter.GradesViewHolder> {

    private List<Grade> gradeList;
    private LayoutInflater layoutInflater;

    public GradesAdapter(Activity context, List<Grade> gradeList) {
        this.layoutInflater = context.getLayoutInflater();
        this.gradeList = gradeList;
    }

    @NonNull
    @Override
    public GradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_row, parent, false);
        return new GradesViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull GradesViewHolder holder, int position) {
        holder.getName().setText(gradeList.get(position).getName());
        holder.getRadioGroup().setTag(position);

        switch(gradeList.get(position).getValue()){
            case 2:
                holder.getRadioGroup().check(R.id.grade2);
                break;
            case 3:
                holder.getRadioGroup().check(R.id.grade3);
                break;
            case 4:
                holder.getRadioGroup().check(R.id.grade4);
                break;
            case 5:
                holder.getRadioGroup().check(R.id.grade5);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return gradeList.size();
    }

    public class GradesViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener {

        public TextView name;
        public RadioGroup radioGroup;

        public GradesViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.gradeName);
            radioGroup = view.findViewById(R.id.radioGrades);

            radioGroup.setOnCheckedChangeListener((_radioGroup, checkedId) -> {
                int index = (int) radioGroup.getTag();
                switch (checkedId){
                    case R.id.grade2:
                        gradeList.get(index).setValue(2);
                        break;
                    case R.id.grade3:
                        gradeList.get(index).setValue(3);
                        break;
                    case R.id.grade4:
                        gradeList.get(index).setValue(4);
                        break;
                    case R.id.grade5:
                        gradeList.get(index).setValue(5);
                        break;
                }
            });
        }

        public RadioGroup getRadioGroup() {
            return radioGroup;
        }

        public TextView getName() {
            return name;
        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            radioGroup.check(i);
//            Grade grade = (Grade) radioGroup.getTag();
//            gradeList.indexOf(grade);
        }


    }
}

