package com.cmpe.bounswe2015group8.westory.back;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.model.Comment;
import com.cmpe.bounswe2015group8.westory.model.Heritage;
import com.cmpe.bounswe2015group8.westory.model.Media;
import com.cmpe.bounswe2015group8.westory.model.Member;
import com.cmpe.bounswe2015group8.westory.model.Post;
import com.cmpe.bounswe2015group8.westory.model.Requestable;
import com.cmpe.bounswe2015group8.westory.model.Tag;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Class handling all server requests through REST API.
 * Originally created by Bugrahan Memis, later changed by Doruk Kilitcioglu
 * to comply with REST API.
 * @author bugrahan
 * @author xyllan
 * Date: 31/10/15.
 */
public class ServerRequests{
    public enum ErrorType { NO_ERROR, NO_INTERNET, SERVER_ERROR, WRONG_LOGIN, UNKNOWN_ERROR }
    public static final String SERVER_ADDRESS = "http://ec2-54-187-115-133.us-west-2.compute.amazonaws.com:8080/WeStory";
    public static ErrorType handleErrors(Context c, ServerRequests sr) {
        switch(sr.getLastError()) {
            case NO_INTERNET:
                Toast.makeText(c, c.getString(R.string.generic_no_internet), Toast.LENGTH_LONG).show();
                break;
            case WRONG_LOGIN:
                Toast.makeText(c, c.getString(R.string.login_user_details_incorrect),Toast.LENGTH_LONG).show();
                break;
            case SERVER_ERROR:
            case UNKNOWN_ERROR:
                Toast.makeText(c, c.getString(R.string.generic_server_error),Toast.LENGTH_LONG).show();
                break;
        }
        return sr.getLastError();
    }
    private ProgressDialog progressDialog;
    private boolean display;
    private ErrorType lastError = ErrorType.NO_ERROR;
    public ServerRequests(Context context) {
        this(context, true);
    }
    public ServerRequests(Context context, boolean display) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");
        this.display = display;

    }
    public ErrorType getLastError() {
        return lastError;
    }
    public void getPostById(long id, Consumer<Post> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Post>(callback, HttpMethod.POST).execute(new Requestable<Post>("/api/getPostById", id, Post.class));
    }
    public void getPostsByHeritageId(long id, Consumer<Post[]> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Post[]>(callback, HttpMethod.POST).execute(new Requestable<Post[]>("/api/getHeritagePostsById", id, Post[].class));
    }
    public void getPostsByMemberId(long id, Consumer<Post[]> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Post[]>(callback, HttpMethod.GET).execute(new Requestable<Post[]>("/api/getPostsByMemberId/"+id, null, Post[].class));
    }
    public void getCommentsByPostId(long id, Consumer<Comment[]> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Comment[]>(callback, HttpMethod.GET).execute(new Requestable<Comment[]>("/api/getCommentsByPostId/" + id, id, Comment[].class));
    }
    public void getPostVoteCount(long id, Consumer<Long> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Long>(callback, HttpMethod.GET).execute(new Requestable<Long>("/api/getOverallPostVoteById/" + id, null, Long.class));
    }
    public void getCommentVoteCount(long id, Consumer<Long> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Long>(callback, HttpMethod.GET).execute(new Requestable<Long>("/api/getOverallCommentVoteById/" + id, null, Long.class));
    }
    public void getAllPosts(Consumer<Post[]> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Post[]>(callback, HttpMethod.POST).execute(new Requestable<Post[]>("/api/getAllPosts",null,Post[].class) );
    }
    public void getAllComments(Consumer<Comment[]> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Comment[]>(callback, HttpMethod.GET).execute(new Requestable<Comment[]>("/api/getAllComments",null,Comment[].class) );
    }
    public void createPost(Post p, long heritageId, Consumer<Long> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Long>(callback, HttpMethod.POST).execute(p.getCreateRequestable(heritageId));
    }
    public void createComment(Comment c, long postId, Consumer<String> callback){
        if(display) progressDialog.show();
        new RestAsyncTask<String>(callback, HttpMethod.POST).execute(c.getCreateRequestable(postId));
    }
    public void voteComment(Comment c,boolean voteType,Long ownerId, Consumer<String> callback){
        if(display) progressDialog.show();
        new RestAsyncTask<String>(callback, HttpMethod.POST).execute(c.getVoteRequestable(voteType, ownerId));
    }
    public void votePost(Post p,boolean voteType,Long ownerId, Consumer<String> callback){
        if(display) progressDialog.show();
        new RestAsyncTask<String>(callback, HttpMethod.POST).execute(p.getVoteRequestable(voteType, ownerId));
    }
    public void getHeritageById(long id, Consumer<Heritage> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Heritage>(callback, HttpMethod.POST).execute(new Requestable<Heritage>("/api/getHeritageById", id, Heritage.class));
    }
    public void getMemberById(long id, Consumer<Member> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Member>(callback, HttpMethod.POST).execute(new Requestable<Member>("/api/getMemberById", id, Member.class));
    }
    public void getAllHeritages(Consumer<Heritage[]> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Heritage[]>(callback, HttpMethod.POST).execute(new Requestable<Heritage[]>("/api/getAllHeritages",null,Heritage[].class) );
    }
    public void createHeritage(Heritage h, Consumer<Long> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Long>(callback, HttpMethod.POST).execute(h.getCreateRequestable());
    }
    public void login(Member m, Consumer<Member> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Member>(callback, HttpMethod.POST).execute(m.getLoginRequestable());
    }
    public void register(Member m, Consumer<Long> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<Long>(callback, HttpMethod.POST).execute(m.getRegisterRequestable());
    }
    public void addTags(Post p, Consumer<Tag[]> callback) {
        if(display) progressDialog.show();
        for(Tag t : p.getTags()) t.setPosts(new ArrayList<Post>());
        new RestAsyncTask<>(callback,HttpMethod.POST).execute(new Requestable<Tag[]>("/api/updatePostTags",p,Tag[].class));
    }
    public void addTags(Heritage h, Consumer<Tag[]> callback) {
        if(display) progressDialog.show();
        for(Tag t : h.getTags()) t.setHeritages(new ArrayList<Heritage>());
        new RestAsyncTask<>(callback,HttpMethod.POST).execute(new Requestable<Tag[]>("/api/updateHeritageTags",h,Tag[].class));
    }
    public void editTag(Tag t, Consumer<Tag> callback) {
        if(display) progressDialog.show();
        new RestAsyncTask<>(callback,HttpMethod.POST).execute(new Requestable<>("/api/editTag",t,Tag.class));
    }
    public void addMedia(Media m, Consumer<String> callback){
        if(display) progressDialog.show();
        new RestAsyncTask<>(callback,HttpMethod.POST).execute(new Requestable<>("/api/uploadCloudinary",m,String.class));
    }
    public void uploadProfilePicture(Member m,String link,  Consumer<Member> callback){
        if(display) progressDialog.show();
        new RestAsyncTask<>(callback,HttpMethod.POST).execute(m.getUploadPictureRequestable(link));
    }
    public void followMember(Member follower,Long followee,  Consumer<Long> callback) {
        if (display) progressDialog.show();
        new RestAsyncTask<>(callback, HttpMethod.POST).execute(follower.getFollowRequestable(followee));
    }
    public void unfollowMember(Member follower,Long followee,  Consumer<Long> callback) {
        if (display) progressDialog.show();
        new RestAsyncTask<>(callback, HttpMethod.POST).execute(follower.getUnfollowRequestable(followee));
    }
    public void followHeritage(Member follower,Long followee,  Consumer<Long> callback) {
        if (display) progressDialog.show();
        new RestAsyncTask<>(callback, HttpMethod.POST).execute(follower.getFollowHeritageRequestable(followee));
    }
    public void unfollowHeritage(Member follower,Long followee,  Consumer<Long> callback) {
        if (display) progressDialog.show();
        new RestAsyncTask<>(callback, HttpMethod.POST).execute(follower.getUnFollowHeritageRequestable(followee));
    }
    public void heritageFeed(Long id,  Consumer<Heritage[]> callback) {
        if (display) progressDialog.show();
        new RestAsyncTask<Heritage[]>(callback, HttpMethod.POST).execute(new Requestable<Heritage[]>("/api/heritageNewsfeed",id,Heritage[].class) );
    }
    public void postFeed(Long id,  Consumer<Post[]> callback) {
        if (display) progressDialog.show();
        new RestAsyncTask<Post[]>(callback, HttpMethod.POST).execute(new Requestable<Post[]>("/api/postNewsfeed",id,Post[].class) );
    }
    public void searchByHeritageName(String name, Consumer<Heritage[]> callback) {
        if (display) progressDialog.show();
        new RestAsyncTask<>(callback, HttpMethod.POST).execute(new Requestable<Heritage[]>("/api/searchByHeritageName",name,Heritage[].class));
    }
    public void searchByPostTitle(String title, Consumer<Post[]> callback) {
        if (display) progressDialog.show();
        new RestAsyncTask<>(callback, HttpMethod.POST).execute(new Requestable<Post[]>("/api/searchByPostTitle",title,Post[].class));
    }
    public void recommendHeritages(Long id, Consumer<Heritage[]> callback) {
        if (display) progressDialog.show();
        new RestAsyncTask<>(callback, HttpMethod.GET).execute(new Requestable<>("/api/recommendHeritages/"+id,null,Heritage[].class));
    }
    public void searchHeritagesByTag(Tag tag, Consumer<Heritage[]> callback) {
        if (display) progressDialog.show();
        String wholeTag = tag.getTagText() + "(" + tag.getTagContext() + ")";
        new RestAsyncTask<>(callback, HttpMethod.GET).execute(new Requestable<>("/api/searchHeritagesByTag/"+wholeTag,null,Heritage[].class));
    }
    public void searchPostsByTag(Tag tag, Consumer<Post[]> callback) {
        if (display) progressDialog.show();
        String wholeTag = tag.getTagText() + "(" + tag.getTagContext() + ")";
        new RestAsyncTask<>(callback, HttpMethod.GET).execute(new Requestable<>("/api/searchPostsByTag/"+wholeTag,null,Post[].class));
    }

    /** Class for handling REST requests.
     * Handles one request per task. Calls the given callback object once it is done
     * so that the result can be used. Note that all POST requests send data
     * with content type JSON.
     * @param <T> The class of the returned object.
     */
    public class RestAsyncTask<T> extends AsyncTask<Requestable<T>, Void, T> {
        Consumer<T> callback;
        HttpMethod method;
        public RestAsyncTask(Consumer<T>  callback, HttpMethod method) {
            this.callback = callback;
            this.method = method;
        }

        @Override
        protected T doInBackground(Requestable<T>... params) {
            RestTemplate rt = new RestTemplate(true);
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            try {
                switch (method) {
                    case GET:
                        lastError = ErrorType.NO_ERROR;
                        return rt.getForObject(SERVER_ADDRESS + params[0].getEndpoint(), params[0].getDataClass());
                    case POST:
                        lastError = ErrorType.NO_ERROR;
                        return rt.postForObject(SERVER_ADDRESS + params[0].getEndpoint(), new HttpEntity<>(params[0].getData(),headers), params[0].getDataClass());
                    default:
                        lastError = ErrorType.NO_ERROR;
                        return null;
                }
            } catch(HttpStatusCodeException e) {
                // This is thrown on server errors
                lastError = ErrorType.SERVER_ERROR;
                return null;
            } catch (ResourceAccessException e) {
                // This is thrown when there is no internet
                lastError = ErrorType.NO_INTERNET;
                return null;
            } catch (HttpMessageNotReadableException e) {
                // This is thrown on wrong login
                lastError = ErrorType.WRONG_LOGIN;
                return null;
            } catch (Exception e) {
                // We have no idea what the error is
                lastError = ErrorType.UNKNOWN_ERROR;
                return null;
            }
        }

        @Override
        protected void onPostExecute(T t) {
            if(display) progressDialog.dismiss();
            callback.accept(t);
            super.onPostExecute(t);
        }
    }
}
