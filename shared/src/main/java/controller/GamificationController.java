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
    //private int[] heritageLevelLimits = {1, 10, 25, 50, 100};  // only after heritage model is updated
    private int[] followLevelLimits = {1, 10, 25, 50, 100};
    private int[] commentLevelLimits = {1, 15, 30, 75, 150};
    private int[] voteLevelLimits = {1, 20, 50, 100, 200};

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Member currentUser = memberService.getMemberByUsername(username);

        Gamification gamification = gamificationService.getGamification(currentUser);
        JsonObject achievementsUnlocked = new JsonObject();

        // Checking Post Num and Level
        long postNum = postService.getPostNumber(currentUser);
        if(gamification.getPostNum() != postNum){
            int postLevel = gamification.getPostLevel();
            if(postNum >= postLevelLimits[postLevel]){
                gamificationService.incrementPostLevel(gamification);
                achievementsUnlocked.addProperty("postLevel", gamification.getPostLevel());
            }
        }

        /* This part will be uncommented when the Heritage model is updated
        // Checking Heritage Num and Level
        long heritageNum = heritageService.getHeritageNumber(currentUser);
        if(gamification.getHeritageNum() != heritageNum){
            int heritageLevel = gamification.getHeritageLevel();
            if(heritageNum >= heritageLevelLimits[heritageLevel]){
                gamificationService.incrementHeritageLevel(gamification);
                achievementsUnlocked.addProperty("heritageLevel", gamification.getHeritageLevel());
            }
        }
        */

        // Checking Follower Num and Level
        long followerNum = followService.getFollowerNum(currentUser);
        if(gamification.getFollowerNum() != followerNum){
            int followerLevel = gamification.getFollowerLevel();
            if(followerNum >= followLevelLimits[followerLevel]){
                gamificationService.incrementFollowerLevel(gamification);
                achievementsUnlocked.addProperty("followerLevel", gamification.getFollowerLevel());
            }
        }

        // Checking Followee Num and Level
        long followeeNum = followService.getFolloweeNum(currentUser);
        if(gamification.getFolloweeNum() != followeeNum){
            int followeeLevel = gamification.getFolloweeLevel();
            if(followeeNum >= followLevelLimits[followeeLevel]){
                gamificationService.incrementFolloweeLevel(gamification);
                achievementsUnlocked.addProperty("followeeLevel", gamification.getFolloweeLevel());
            }
        }

        // Checking Comment Num and Level
        long commentNum = commentService.getCommentNumber(currentUser);
        if(gamification.getCommentNum() != commentNum){
            int commentLevel = gamification.getCommentLevel();
            if(commentNum >= commentLevelLimits[commentLevel]){
                gamificationService.incrementCommentLevel(gamification);
                achievementsUnlocked.addProperty("commentLevel", gamification.getCommentLevel());
            }
        }

        // Checking Upvote Num and Level
        long upvoteNum = voteService.getUpvoteNum(currentUser);
        if(gamification.getUpvoteNum() != upvoteNum){
            int upvoteLevel = gamification.getUpvoteLevel();
            if(upvoteNum >= voteLevelLimits[upvoteLevel]){
                gamificationService.incrementUpvoteLevel(gamification);
                achievementsUnlocked.addProperty("upvoteLevel", gamification.getUpvoteLevel());
            }
        }

        // Checking Downvote Num and Level
        long downvoteNum = voteService.getDownvoteNum(currentUser);
        if(gamification.getDownvoteNum() != downvoteNum){
            int downvoteLevel = gamification.getDownvoteLevel();
            if(downvoteNum >= voteLevelLimits[downvoteLevel]){
                gamificationService.incrementDownvoteLevel(gamification);
                achievementsUnlocked.addProperty("downvoteLevel", gamification.getDownvoteLevel());
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
            achievementsUnlocked.addProperty("overallLevel", gamification.getOverallLevel());
        }

        return achievementsUnlocked.toString();
    }

}
