package com.example.npatel.myapplication;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends ArrayAdapter<word> {

int colorid;
Context mcon;

    public WordAdapter(Context mcontext, ArrayList<word> mnumberlist,int colorid) {
        super(mcontext,0,mnumberlist);
        this.colorid=colorid;
        mcon=mcontext;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View listItemView = view;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.listitem,viewGroup,false);
        }
        word currentword = getItem(position);
        TextView defaultword = (TextView) listItemView.findViewById(R.id.defaultword);
        TextView miwokword = (TextView) listItemView.findViewById(R.id.miwokword);
        ImageView image = (ImageView) listItemView.findViewById(R.id.image);
        LinearLayout twotext = (LinearLayout) listItemView.findViewById(R.id.twotext);
        defaultword.setText(currentword.getDefualtword());

        miwokword.setText(currentword.getMiwokword());
        int color= ContextCompat.getColor(mcon,colorid);
        twotext.setBackgroundColor(color);
        if(currentword.hasimage()) {
            image.setImageResource(currentword.getImageresid());
            image.setVisibility(View.VISIBLE);
        }
        else
        {
            image.setVisibility(View.GONE);
        }

            return listItemView;
    }
}
