package com.juns.wechat.moments.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.juns.wechat.moments.adapter.FavortListAdapter;
import com.juns.wechat.moments.spannable.ISpanClick;

/**
 * @author yiw
 * @Description:
 * @date 16/1/2 18:47
 */
public class FavortListView extends TextView{
    private ISpanClick mSpanClickListener;

    public void setSpanClickListener(ISpanClick listener){
        mSpanClickListener = listener;
    }
    public ISpanClick getSpanClickListener(){
        return  mSpanClickListener;
    }

    public FavortListView(Context context) {
        super(context);
    }

    public FavortListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FavortListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAdapter(FavortListAdapter adapter){
        adapter.bindListView(this);
    }

}
