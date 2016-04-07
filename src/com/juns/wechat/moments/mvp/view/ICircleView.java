package com.juns.wechat.moments.mvp.view;

import com.juns.wechat.moments.bean.CommentConfig;
import com.juns.wechat.moments.bean.CommentItem;
import com.juns.wechat.moments.bean.FavortItem;
/**
 * 
* @ClassName: ICircleViewUpdateListener 
* @Description: view,服务器响应后更新界面 
* @author yiw
* @date 2015-12-28 下午4:13:04 
*
 */
public interface ICircleView {

	public void update2DeleteCircle(String circleId);
	public void update2AddFavorite(int circlePosition, FavortItem addItem);
	public void update2DeleteFavort(int circlePosition, String favortId);
	public void update2AddComment(int circlePosition, CommentItem addItem);
	public void update2DeleteComment(int circlePosition, String commentId);

	public void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig);

}
