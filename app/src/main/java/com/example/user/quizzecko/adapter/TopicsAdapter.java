package com.example.user.quizzecko.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.quizzecko.R;

import java.util.ArrayList;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicCustomViewHolder> {
    Context mcontext;
    ArrayList<String > marrayList;
    ArrayList<GradientDrawable>mGradientsDrawable;
    public TopicsAdapter(Context context, ArrayList<String> arrayList)
    {
        mcontext=context;
      marrayList=arrayList;
      mGradientsDrawable=new ArrayList<>();
      fillGradients(mcontext);


    }
    public void addTopic(String topic )
    {marrayList.add(topic);}


    @NonNull
    @Override
    public TopicCustomViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        final View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item_list_view,parent,false);
        return new TopicCustomViewHolder(view);
    }
     public void fillGradients(Context context){
        mGradientsDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_1_start),ContextCompat.getColor(context,R.color.gradient_1_end)));
         mGradientsDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_2_start),ContextCompat.getColor(context,R.color.gradient_2_end)));
         mGradientsDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_3_start),ContextCompat.getColor(context,R.color.gradient_3_end)));
         mGradientsDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_4_start),ContextCompat.getColor(context,R.color.gradient_4_end)));
     }
     private GradientDrawable getTempGradientDrawable(int startColor,int endColor){
        GradientDrawable drawable=new GradientDrawable(GradientDrawable.Orientation.BR_TL,new int[]{startColor,endColor} );
        drawable.setDither(true);
        drawable.setGradientCenter(drawable.getIntrinsicWidth()/8,drawable.getIntrinsicHeight()/2);
        drawable.setCornerRadius(20);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setUseLevel(true);
        return drawable;
     }
    @Override
    public void onBindViewHolder(TopicCustomViewHolder holder, int position) {
        String topicname=marrayList.get(position);
     holder.mTextView.setText(topicname);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            holder.mTopicRelativeLayout.setBackground(mGradientsDrawable.get(position%4));
        }
        else
        {
            holder.mTopicRelativeLayout.setBackgroundDrawable((mGradientsDrawable.get(position%4)));
        }
        holder.mImageView.setImageResource(R.drawable.menu);

    }



    @Override
    public int getItemCount() {
        return marrayList.size();
    }

    public class TopicCustomViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        private TextView mTextView;
        RelativeLayout mTopicRelativeLayout;
        public TopicCustomViewHolder( View itemView) {
            super(itemView);
            mImageView=(ImageView)itemView.findViewById(R.id.topicImageView);
            mTextView=(TextView)itemView.findViewById(R.id.topicTextView);
            mTopicRelativeLayout=(RelativeLayout)itemView.findViewById(R.id.topicRelativeLayout);
        }
    }
}
