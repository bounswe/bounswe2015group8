package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dao.MemberDaoImpl;
import model.Gamification;
import model.Member;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.*;

/**
 * Created by gokcan on 27.12.2015.
 */

@Controller
public class GamificationController {
    private Logger logger = Logger.getLogger(GamificationController.class);
    private int[] postLevelLimits = {1, 10, 25, 50, 100};
    //private int[] heritageLevelLimits = {1, 5, 10, 25, 50};  // only after heritage model is updated
    private int[] followLevelLimits = {1, 10, 25, 50, 100};
    private int[] commentLevelLimits = {1, 10, 25, 50, 200};
    private int[] voteLevelLimits = {1, 25, 50, 100, 200};

    MemberDetailsService memberService;
    PostService postService;
    HeritageService heritageService;
    FollowService followService;
    CommentService commentService;
    VoteService voteService;
    GamificationService gamificationService;

    public GamificationController(){
        memberService = new MemberDetailsService();
        MemberDaoImpl mdao = new MemberDaoImpl();
        mdao.setSessionFactory(Main.getSessionFactory());
        memberService.setMemberDao(mdao);
        postService = new PostService(Main.getSessionFactory());
        heritageService = new HeritageService(Main.getSessionFactory());
        followService = new FollowService(Main.getSessionFactory());
        commentService = new CommentService(Main.getSessionFactory());
        voteService = new VoteService(Main.getSessionFactory());
        gamificationService = new GamificationService(Main.getSessionFactory());
    }

    @RequestMapping(value = "/checkAchievements", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String achievements(){
        JsonObject achievementsUnlocked = new JsonObject();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if(username.equals("anonymousUser")){
            return achievementsUnlocked.toString();
        }
        Member currentUser = memberService.getMemberByUsername(username);

        Gamification gamification = gamificationService.getGamification(currentUser);

        // Checking Post Num and Level
        long postNum = postService.getPostNumber(currentUser);
        if(gamification.getPostNum() != postNum){
            int postLevel = gamification.getPostLevel();
            if(postNum >= postLevelLimits[postLevel]){
                gamificationService.incrementPostLevel(gamification);
                achievementsUnlocked.addProperty("post", gamification.getPostLevel());
            }
        }

        /* This part will be uncommented when the Heritage model is updated
        // Checking Heritage Num and Level
        long heritageNum = heritageService.getHeritageNumber(currentUser);
        if(gamification.getHeritageNum() != heritageNum){
            int heritageLevel = gamification.getHeritageLevel();
            if(heritageNum >= heritageLevelLimits[heritageLevel]){
                gamificationService.incrementHeritageLevel(gamification);
                achievementsUnlocked.addProperty("heritage", gamification.getHeritageLevel());
            }
        }
        */

        // Checking Follower Num and Level
        long followerNum = followService.getFollowerNum(currentUser);
        if(gamification.getFollowerNum() != followerNum){
            int followerLevel = gamification.getFollowerLevel();
            if(followerNum >= followLevelLimits[followerLevel]){
                gamificationService.incrementFollowerLevel(gamification);
                achievementsUnlocked.addProperty("follower", gamification.getFollowerLevel());
            }
        }

        // Checking Followee Num and Level
        long followeeNum = followService.getFolloweeNum(currentUser);
        if(gamification.getFolloweeNum() != followeeNum){
            int followeeLevel = gamification.getFolloweeLevel();
            if(followeeNum >= followLevelLimits[followeeLevel]){
                gamificationService.incrementFolloweeLevel(gamification);
                achievementsUnlocked.addProperty("followee", gamification.getFolloweeLevel());
            }
        }

        // Checking Comment Num and Level
        long commentNum = commentService.getCommentNumber(currentUser);
        if(gamification.getCommentNum() != commentNum){
            int commentLevel = gamification.getCommentLevel();
            if(commentNum >= commentLevelLimits[commentLevel]){
                gamificationService.incrementCommentLevel(gamification);
                achievementsUnlocked.addProperty("comment", gamification.getCommentLevel());
            }
        }

        // Checking Upvote Num and Level
        long upvoteNum = voteService.getUpvoteNum(currentUser);
        logger.info("Upvote num: " + upvoteNum);
        logger.info("Gamification upvote: " + gamification.getUpvoteNum());
        if(gamification.getUpvoteNum() != upvoteNum){
            int upvoteLevel = gamification.getUpvoteLevel();
            if(upvoteNum >= voteLevelLimits[upvoteLevel]){
                gamificationService.incrementUpvoteLevel(gamification);
                achievementsUnlocked.addProperty("upvote", gamification.getUpvoteLevel());
            }
        }

        // Checking Downvote Num and Level
        long downvoteNum = voteService.getDownvoteNum(currentUser);
        if(gamification.getDownvoteNum() != downvoteNum){
            int downvoteLevel = gamification.getDownvoteLevel();
            if(downvoteNum >= voteLevelLimits[downvoteLevel]){
                gamificationService.incrementDownvoteLevel(gamification);
                achievementsUnlocked.addProperty("downvote", gamification.getDownvoteLevel());
            }
        }

        // Checking Overall Level
        int minLevel = 1000;
        if(gamification.getPostLevel() < minLevel) minLevel = gamification.getPostLevel();
        //if(gamification.getHeritageLevel() < minLevel) minLevel = gamification.getHeritageLevel();
        if(gamification.getFollowerLevel() < minLevel) minLevel = gamification.getFollowerLevel();
        if(gamification.getFolloweeLevel() < minLevel) minLevel = gamification.getFolloweeLevel();
        if(gamification.getCommentLevel() < minLevel) minLevel = gamification.getCommentLevel();
        if(gamification.getUpvoteLevel() < minLevel) minLevel = gamification.getUpvoteLevel();
        if(gamification.getDownvoteLevel() < minLevel) minLevel = gamification.getDownvoteLevel();
        if(gamification.getOverallLevel() < minLevel){
            gamificationService.incrementOverallLevel(gamification);
            achievementsUnlocked.addProperty("overall", gamification.getOverallLevel());
        }

        return achievementsUnlocked.toString();
    }

}
