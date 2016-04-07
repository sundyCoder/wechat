package com.juns.wechat.moments.mvp.presenter;

import android.view.View;

import com.juns.wechat.moments.bean.CommentConfig;
import com.juns.wechat.moments.bean.CommentItem;
import com.juns.wechat.moments.bean.FavortItem;
import com.juns.wechat.moments.mvp.modle.CircleModel;
import com.juns.wechat.moments.mvp.modle.IDataRequestListener;
import com.juns.wechat.moments.mvp.view.ICircleView;
import com.juns.wechat.moments.utils.DatasUtil;

/**
 * 
* @ClassName: CirclePresenter 
* @Description: 通知model请求服务器和通知view更新
* @author yiw
* @date 2015-12-28 下午4:06:03 
*
 */
public class CirclePresenter {
	private CircleModel mCircleModel;
	private ICircleView mCircleView;
	
	public CirclePresenter(ICircleView view){
		this.mCircleView = view;
		mCircleModel = new CircleModel();
	}
	/**
	 * 
	* @Title: deleteCircle 
	* @Description: 删除动态 
	* @param  circleId     
	* @return void    返回类型 
	* @throws
	 */
	public void deleteCircle(final String circleId){
		mCircleModel.deleteCircle(new IDataRequestListener() {

			@Override
			public void loadSuccess(Object object) {
				mCircleView.update2DeleteCircle(circleId);
			}
		});
	}
	/**
	 * 
	* @Title: addFavort 
	* @Description: 点赞
	* @param  circlePosition     
	* @return void    返回类型 
	* @throws
	 */
	public void addFavort(final int circlePosition){
		mCircleModel.addFavort(new IDataRequestListener() {

			@Override
			public void loadSuccess(Object object) {
				FavortItem item = DatasUtil.createCurUserFavortItem();
				mCircleView.update2AddFavorite(circlePosition, item);
			}
		});
	}
	/**
	 * 
	* @Title: deleteFavort 
	* @Description: 取消点赞 
	* @param @param circlePosition
	* @param @param favortId     
	* @return void    返回类型 
	* @throws
	 */
	public void deleteFavort(final int circlePosition, final String favortId){
		mCircleModel.deleteFavort(new IDataRequestListener() {

			@Override
			public void loadSuccess(Object object) {
				mCircleView.update2DeleteFavort(circlePosition, favortId);
			}
		});
	}
	
	/**
	 * 
	* @Title: addComment 
	* @Description: 增加评论
	* @param  content
	* @param  config  CommentConfig
	* @return void    返回类型 
	* @throws
	 */
	public void addComment(final String content, final CommentConfig config){
		if(config == null){
			return;
		}
		mCircleModel.addComment(new IDataRequestListener() {

			@Override
			public void loadSuccess(Object object) {
				CommentItem newItem = null;
				if (config.commentType == CommentConfig.Type.PUBLIC) {
					newItem = DatasUtil.createPublicComment(content);
				} else if (config.commentType == CommentConfig.Type.REPLY) {
					newItem = DatasUtil.createReplyComment(config.replyUser, content);
				}

				mCircleView.update2AddComment(config.circlePosition, newItem);
			}

		});
	}
	
	/**
	 * 
	* @Title: deleteComment 
	* @Description: 删除评论 
	* @param @param circlePosition
	* @param @param commentId     
	* @return void    返回类型 
	* @throws
	 */
	public void deleteComment(final int circlePosition, final String commentId){
		mCircleModel.deleteComment(new IDataRequestListener(){

			@Override
			public void loadSuccess(Object object) {
				mCircleView.update2DeleteComment(circlePosition, commentId);
			}
			
		});
	}

	/**
	 *
	 * @param commentConfig
	 */
	public void showEditTextBody(CommentConfig commentConfig){
		mCircleView.updateEditTextBodyVisible(View.VISIBLE, commentConfig);
	}

}
