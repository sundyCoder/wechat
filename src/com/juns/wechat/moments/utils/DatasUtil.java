package com.juns.wechat.moments.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.juns.wechat.moments.bean.CircleItem;
import com.juns.wechat.moments.bean.CommentItem;
import com.juns.wechat.moments.bean.FavortItem;
import com.juns.wechat.moments.bean.User;
/**
 * 
* @ClassName: DatasUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yiw
* @date 2015-12-28 下午4:16:21 
*
 */
public class DatasUtil {
	public static final String[] CONTENTS = { "Welcome", "哈哈", "今天是个好日子", "呵呵", "图不错",
			"awesome" };
	public static final String[] PHOTOS = {
			"file:///sdcard/Download/p/1.jpg",
			"file:///sdcard/Download/p/2.jpg",
			"file:///sdcard/Download/p/3.jpg",
			"file:///sdcard/Download/p/4.jpg",
			"file:///sdcard/Download/p/5.jpg",
			"file:///sdcard/Download/p/6.jpg",
			"file:///sdcard/Download/p/7.png",
			"file:///sdcard/Download/p/8.png",
			"file:///sdcard/Download/p/9.jpg",
			"file:///sdcard/Download/p/10.jpg",
			"file:///sdcard/Download/p/11.jpg",
			"file:///sdcard/Download/p/12.jpg",
			"file:///sdcard/Download/p/13.jpg",
			"file:///sdcard/Download/p/14.jpg",
			"file:///sdcard/Download/p/15.jpg",
			"file:///sdcard/Download/p/16.jpg",
			"file:///sdcard/Download/p/17.jpg"

			};
	public static final String[] HEADIMG = {
			"file:///sdcard/Download/h/h1.jpg",
			"file:///sdcard/Download/h/h2.png",
			"file:///sdcard/Download/h/h3.jpg",
			"file:///sdcard/Download/h/h4.jpg",
			"file:///sdcard/Download/h/h5.jpg",
			"file:///sdcard/Download/h/h6.jpg",
			"file:///sdcard/Download/h/h7.jpeg",
			"file:///sdcard/Download/h/h8.jpg",
			"file:///sdcard/Download/h/h9.jpg"
			};

	public static List<User> users = new ArrayList<User>();
	/**
	 * 动态id自增长
	 */
	private static int circleId = 0;
	/**
	 * 点赞id自增长
	 */
	private static int favortId = 0;
	/**
	 * 评论id自增长
	 */
	private static int commentId = 0;
	public static final User curUser = new User("0", "自己", HEADIMG[0]);
	static {
		User user1 = new User("1", "张三", HEADIMG[1]);
		User user2 = new User("2", "李四", HEADIMG[2]);
		User user3 = new User("3", "隔壁老王", HEADIMG[3]);
		User user4 = new User("4", "赵六", HEADIMG[4]);
		User user5 = new User("5", "田七", HEADIMG[5]);
		User user6 = new User("6", "Naoki", HEADIMG[6]);
		User user7 = new User("7", "这个名字是不是很长，哈哈！因为我是用来测试换行的", HEADIMG[7]);

		users.add(curUser);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		users.add(user7);
	}

	public static List<CircleItem> createCircleDatas() {
		List<CircleItem> circleDatas = new ArrayList<CircleItem>();
		for (int i = 0; i < 15; i++) {
			CircleItem item = new CircleItem();
			User user = getUser();
			item.setId(String.valueOf(circleId++));
			item.setUser(user);
			item.setContent(getContent());
			item.setCreateTime("4月24日");

			item.setFavorters(createFavortItemList());
			item.setComments(createCommentItemList());
			if (getRandomNum(10) % 2 == 0) {
				item.setType("1");// 链接
				item.setLinkImg("file:///sdcard/Download/h/h1.jpg");
				item.setLinkTitle("百度一下，你就知道");
			} else {
				item.setType("2");// 图片
				item.setPhotos(createPhotos());
			}
			circleDatas.add(item);
		}

		return circleDatas;
	}

	public static User getUser() {
		return users.get(getRandomNum(users.size()));
	}

	public static String getContent() {
		return CONTENTS[getRandomNum(CONTENTS.length)];
	}

	public static int getRandomNum(int max) {
		Random random = new Random();
		int result = random.nextInt(max);
		return result;
	}

	public static List<String> createPhotos() {
		List<String> photos = new ArrayList<String>();
		int size = getRandomNum(PHOTOS.length);
		if (size > 0) {
			if (size > 9) {
				size = 9;
			}
			for (int i = 0; i < size; i++) {
				String photo = PHOTOS[getRandomNum(PHOTOS.length)];
				if (!photos.contains(photo)) {
					photos.add(photo);
				} else {
					i--;
				}
			}
		}
		return photos;
	}

	public static List<FavortItem> createFavortItemList() {
		int size = getRandomNum(users.size());
		List<FavortItem> items = new ArrayList<FavortItem>();
		List<String> history = new ArrayList<String>();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				FavortItem newItem = createFavortItem();
				String userid = newItem.getUser().getId();
				if (!history.contains(userid)) {
					items.add(newItem);
					history.add(userid);
				} else {
					i--;
				}
			}
		}
		return items;
	}

	public static FavortItem createFavortItem() {
		FavortItem item = new FavortItem();
		item.setId(String.valueOf(favortId++));
		item.setUser(getUser());
		return item;
	}
	
	public static FavortItem createCurUserFavortItem() {
		FavortItem item = new FavortItem();
		item.setId(String.valueOf(favortId++));
		item.setUser(curUser);
		return item;
	}

	public static List<CommentItem> createCommentItemList() {
		List<CommentItem> items = new ArrayList<CommentItem>();
		int size = getRandomNum(10);
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				items.add(createComment());
			}
		}
		return items;
	}

	public static CommentItem createComment() {
		CommentItem item = new CommentItem();
		item.setId(String.valueOf(commentId++));
		item.setContent("哈哈");
		User user = getUser();
		item.setUser(user);
		if (getRandomNum(10) % 2 == 0) {
			while (true) {
				User replyUser = getUser();
				if (!user.getId().equals(replyUser.getId())) {
					item.setToReplyUser(replyUser);
					break;
				}
			}
		}
		return item;
	}
	
	/**
	 * 创建发布评论
	 * @return
	 */
	public static CommentItem createPublicComment(String content){
		CommentItem item = new CommentItem();
		item.setId(String.valueOf(commentId++));
		item.setContent(content);
		item.setUser(curUser);
		return item;
	}
	
	/**
	 * 创建回复评论
	 * @return
	 */
	public static CommentItem createReplyComment(User replyUser, String content){
		CommentItem item = new CommentItem();
		item.setId(String.valueOf(commentId++));
		item.setContent(content);
		item.setUser(curUser);
		item.setToReplyUser(replyUser);
		return item;
	}
}
