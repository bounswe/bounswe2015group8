package service;

import dao.GamificationDao;
import dao.GamificationDaoImpl;
import model.Gamification;
import model.Member;
import org.hibernate.SessionFactory;

/**
 * Created by gokcan on 27.12.2015.
 */
public class GamificationService {
    private GamificationDao gamificationDao;

    public GamificationService(SessionFactory sessionFactory) {
        gamificationDao = new GamificationDaoImpl();
        gamificationDao.setSessionFactory(sessionFactory);
    }


    public Gamification getGamification(Member member){
        if(!gamificationDao.doesExist(member)){
            return gamificationDao.save(new Gamification(member));
        }
        return gamificationDao.get(member);
    }

    public Gamification incrementPostLevel(Gamification gamification){
        gamification.setPostLevel(gamification.getPostLevel()+1);
        return gamificationDao.update(gamification);
    }

    public Gamification incrementHeritageLevel(Gamification gamification){
        gamification.setHeritageLevel(gamification.getHeritageLevel() + 1);
        return gamificationDao.update(gamification);
    }

    public Gamification incrementFollowerLevel(Gamification gamification){
        gamification.setFollowerLevel(gamification.getFollowerLevel()+1);
        return gamificationDao.update(gamification);
    }

    public Gamification incrementFolloweeLevel(Gamification gamification){
        gamification.setFolloweeLevel(gamification.getFolloweeLevel()+1);
        return gamificationDao.update(gamification);
    }

    public Gamification incrementCommentLevel(Gamification gamification){
        gamification.setCommentLevel(gamification.getCommentLevel()+1);
        return gamificationDao.update(gamification);
    }

    public Gamification incrementUpvoteLevel(Gamification gamification){
        gamification.setUpvoteLevel(gamification.getUpvoteLevel() + 1);
        return gamificationDao.update(gamification);
    }

    public Gamification incrementDownvoteLevel(Gamification gamification){
        gamification.setDownvoteLevel(gamification.getDownvoteLevel() + 1);
        return gamificationDao.update(gamification);
    }

    public Gamification incrementOverallLevel(Gamification gamification){
        gamification.setOverallLevel(gamification.getOverallLevel() + 1);
        return gamificationDao.update(gamification);
    }

    public Gamification update(Gamification gamification){
        return gamificationDao.update(gamification);
    }
}
