package mov.metaweblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mov.metaweblog.dao.MetaWebLogDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MetaWebLog API Demo Service implement
 * 
 * @author mov
 * @since 0.0.1
 */
public class MetaWebLogService {
	private static Logger log = LoggerFactory.getLogger(MetaWebLogService.class);
	
	private MetaWebLogDAO metaWebLogDAO = new MetaWebLogDAO();
	
	// user-info
	// ------------------------------------------------------------------------
	/**
	 * Returns information on all the blogs a given user is a member
	 * @param key
	 * @param username
	 * @param pwd
	 * @return
	 */
	public List<Map<String, String>> getUsersBlogs(String key, String username, String password) {
		log.debug("key: {}, username: {}, password: {}", key, username, password);
		
		return metaWebLogDAO.getUsersBlogs(key, username, password);
	}
	
	
	// category
	// ------------------------------------------------------------------------
	/**
	 * Retrieves a list of valid categories for a post using the metaWeblog API. 
	 * Returns the metaWeblog categories struct collection
	 * @param blogid
	 * @param username
	 * @param password
	 * @return
	 */
	public List<Map<String, String>> getCategories(String blogid, String username, String password) {
		log.debug("blogid: {}, username: {}, password: {}", blogid, username, password);
		
		return metaWebLogDAO.getCategories(blogid);
	}
	
	
	/**
	 * Create a new category
	 * @param blog_id
	 * @param username
	 * @param password
	 * @param category
	 * @return
	 */
	public String newCategory(String blogId, String username, String password,
			Map<String, String> category) {
		log.debug("blogId: {}, username: {}, password: {}, post: {}", 
				blogId, username, password, category);
		
		return metaWebLogDAO.newCategory(blogId, category);
	}
	
	
	// post
	// ------------------------------------------------------------------------
	/**
	 * Makes a new post to a designated blog using the metaWeblog API. Returns postid as a string
	 * @param blogid
	 * @param username
	 * @param password
	 * @param post
	 * @param publish
	 * @return
	 */
	public String newPost(String blogid, String username, String password,
			Map<String, Object> post, boolean publish) {
		log.debug("blogid: {}, username: {}, password: {}, post: {}, publish: {}", 
				blogid, username, password, post, publish);
		
		return metaWebLogDAO.newPost(blogid, post, publish);
	}
	
	
	/**
	 * Deletes a post
	 * 
	 * @param appKey
	 * @param postid
	 * @param username
	 * @param password
	 * @param publish
	 *            Where applicable, this specifies whether the blog should be
	 *            republished after the post has been deleted
	 * @return
	 */
	public boolean deletePost(String appKey, String postid, String username, 
			String password, boolean publish) {
		log.debug("appKey: {}, postid: {}, username: {}, password: {}, publish: {}", 
				appKey, postid, username, password, publish);
		
		return metaWebLogDAO.deletePost(postid);
	}


	/**
	 * Updates and existing post to a designated blog using the metaWeblog API. 
	 * Returns true if completed.
	 * @param postid
	 * @param username
	 * @param password
	 * @param post
	 * @param publish
	 * @return
	 */
	public boolean editPost(String postid, String username, String password, 
			Map<String, Object> post, boolean publish) {
		log.debug("postid: {}, username: {}, password: {}, post: {}, publish: {}", 
				postid, username, password, post, publish);
		
		return metaWebLogDAO.editPost(postid, post, publish);
	}
	
	
	/**
	 * Retrieves an existing post using the metaWeblog API. Returns the metaWeblog struct.
	 * @param postid
	 * @param username
	 * @param password
	 * @return
	 */
	public Map<String, Object> getPost(String postid, String username, String password) {
		log.debug("postid: {}, username: {}, password: {}", postid, username, password);
		
		return metaWebLogDAO.getPost(postid);
	}
	
	
	/**
	 * Retrieves a list of the most recent existing post using the metaWeblog API. 
	 * Returns the metaWeblog struct collection.
	 * @param postid
	 * @param username
	 * @param password
	 * @param numberOfPosts
	 * @return
	 */
	public List<Map<String, Object>> getRecentPosts(String blogid, String username, String password, int numberOfPosts) {
		log.debug("blogid: {}, username: {}, password: {}, numberOfPosts: {}", 
				blogid, username, password, numberOfPosts);
		
		return metaWebLogDAO.getRecentPosts(blogid, numberOfPosts);
	}
	
	
	// media object
	// ------------------------------------------------------------------------
	/**
	 * Makes a new file to a designated blog using the metaWeblog API. 
	 * Returns url as a string of a struct
	 * @param postid
	 * @param username
	 * @param password
	 * @param file
	 * @return
	 */
	public Map<String, String> newMediaObject(String postid, String username, String password, 
			Map<String, Object> file) {
		Map<String, String> map = new HashMap<>();
		map.put("url", "http://localhost/metaweblog-demo/test.jpg");
		
		String toFilePath = "D:\\testfsdsadfsdfsadfsdafsadfsdafa.jpg";
		
		FileOutputStream os = null;
		try {
			File toFile = new File(toFilePath);
			if (toFile != null && toFile.exists()) {
				toFile.delete();
			}
			
			byte[] imgBytes = (byte[])(file.get("bits"));
			os = new FileOutputStream(toFilePath);
			os.write(imgBytes, 0, imgBytes.length);
			
		} catch (IOException e) {
			log.error("error save file", e);
		} finally {
            if (os != null) {
            	try {
            		os.close();
            	} catch (IOException e){
            		os = null;
            	}
			}
		}

		return map;
	}
}
