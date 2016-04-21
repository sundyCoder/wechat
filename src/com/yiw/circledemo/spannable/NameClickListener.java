package com.yiw.circledemo.spannable;

import com.yiw.circledemo.MomentsActivity;

import android.text.SpannableString;
import android.widget.Toast;

/**
 *
 * @ClassName: NameClickListener
 * @Description: 点赞和评论中人名的点击事件
 * @author yiw
 * @date 2015-01-02 下午3:42:21
 *
 */
public class NameClickListener implements ISpanClick {
    private SpannableString userName;
    private String userId;

    public NameClickListener(SpannableString name, String userid) {
        this.userName = name;
        this.userId = userid;
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(MomentsActivity.getContext(), userName + " &id = " + userId, Toast.LENGTH_SHORT).show();
    }
}
