package com.autismdetectionapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.autismdetectionapp.R;
import com.autismdetectionapp.model.ResultModel;

import java.util.List;

public class ResultDetailAdapter extends BaseAdapter {

    List<ResultModel> resultModelList;
    Context context;
    LayoutInflater inflater;

    public ResultDetailAdapter(List<ResultModel> resultModelList, Context context) {
        this.resultModelList = resultModelList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return resultModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_result, viewGroup, false);
        TextView itemChildName = view.findViewById(R.id.itemChildName);
        TextView itemScore = view.findViewById(R.id.itemScore);
        TextView itemDateTime = view.findViewById(R.id.itemDateTime);

        itemChildName.setText(resultModelList.get(i).getChildName());
        itemScore.setText(resultModelList.get(i).getScore());
        itemDateTime.setText(resultModelList.get(i).getDateTime());

        return view;
    }
}
