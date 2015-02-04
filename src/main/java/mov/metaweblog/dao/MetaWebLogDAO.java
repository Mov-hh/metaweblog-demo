package mov.metaweblog.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MetaWebLog DAO Demo
 * 
 * @author mov
 * @since 0.0.1
 */
public class MetaWebLogDAO {
	private static int categorySequence = 1;
	private static int postSequence = 1;
	
	private static Map<String, String> userBlogInfo;
	private static List<Map<String, String>> categories;
	private static List<Map<String, Object>> posts;
	
	static {
		userBlogInfo =  new HashMap<>();
		userBlogInfo.put("blogid", "12345678");
		userBlogInfo.put("url", "http://localhost/metaweblog-demo/test");
		userBlogInfo.put("blogName", "test");

		categories = new ArrayList<>();
		Map<String, String> category = new HashMap<>();
		String categoryId = "" + categorySequence++;
		category.put("description", "Java异常");
		category.put("title", "Java异常");
		category.put("categoryid", categoryId);
		category.put("htmlUrl", "http://localhost/metaweblog-demo/test/" + categoryId);

		categories.add(category);

		posts = new ArrayList<>();
	}
	
	public List<Map<String, String>> getUsersBlogs(String key, String username, String password) {
		List<Map<String, String>> list = new ArrayList<>();
		list.add(userBlogInfo);
		
		return list;
	}
	
	public List<Map<String, String>> getCategories(String blogid) {
		return categories;
	}
	
	public String newCategory(String blogId, Map<String, String> category) {
		String categoryId = "" + categorySequence++;
		category.put("categoryid", categoryId);
		
		categories.add(category);
		
		return categoryId;
	}
	
	public String newPost(String blogid, Map<String, Object> post, boolean publish) {
		String postid = "" + postSequence++;
		post.put("postid", postid);
		
		posts.add(post);
		
		return postid;
	}
	
	public boolean deletePost(String postid) {
		
		Map<String, Object> post = null;
		
		for (int i = 0; i < posts.size(); i++) {
			post = posts.get(i);
			if (postid.equals(post.get("postid"))) {
				break;
			}
		}
		
		if (post != null) {
			posts.remove(post);
		}
		
		return true;
	}
	
	public boolean editPost(String postid, Map<String, Object> post, boolean publish) {
		deletePost(postid);
		posts.add(post);
		
		return true;
	}

	public Map<String, Object> getPost(String postid) {
		Map<String, Object> post = null;
		
		for (int i = 0; i < posts.size(); i++) {
			post = posts.get(i);
			if (postid.equals(post.get("postid"))) {
				break;
			}
		}
		
		return post;
	}

	public List<Map<String, Object>> getRecentPosts(String blogid,
			int numberOfPosts) {
		return posts;
	}
}