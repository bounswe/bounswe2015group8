package service;

import dao.HeritageDao;
import dao.HeritageDaoImpl;
import model.Heritage;
import model.Post;
import model.Tag;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public class HeritageService {
    private HeritageDao heritageDao;

    public HeritageService(SessionFactory sessionFactory) {
        heritageDao = new HeritageDaoImpl();
        heritageDao.setSessionFactory(sessionFactory);
    }

    public Heritage getHeritageById(long id) {
        return heritageDao.getHeritageById(id);
    }

    public Heritage getHeritageByName(String name) { return heritageDao.getHeritageByName(name); }

    public List<Heritage> getAllHeritages() {
        return heritageDao.getAllHeritages();
    }

    public List<Heritage> getHeritagesByPost(Post post){ return heritageDao.getHeritagesByPost(post); }

    public boolean doesHeritageHavePost(long heritageId, Post post){
        return heritageDao.doesHeritageHavaPost(getHeritageById(heritageId), post);
    }

    public List<Heritage> getHeritagesByTag(Tag tag){ return heritageDao.getHeritagesByTag(tag); }

    public Heritage getFirstHeritageByPost(Post post){ return getHeritagesByPost(post).get(0); }

    public Heritage saveHeritage(String name, String place, String description, Timestamp postDate) {
        Heritage heritage = new Heritage(name, place, description, postDate);
        return heritageDao.saveHeritage(heritage);
    }

    public List<Heritage> sortByPopularity(List<Heritage> heritages){
        List<Heritage> sortedHeritages = new ArrayList<>();
        int size = heritages.size();

        for(int i = 0; i < size; i++){
            int maxPostNum = -1;
            Heritage mostPopular = null;
            for(int j = 0; j < heritages.size(); j++){
                Heritage currentHeritage = heritages.get(j);
                int currentPostNum = heritageDao.countPostsInHeritage(currentHeritage);
                if(currentPostNum > maxPostNum){
                    maxPostNum = currentPostNum;
                    mostPopular = currentHeritage;
                }
            }
            sortedHeritages.add(mostPopular);
            heritages.remove(mostPopular);
        }
        return sortedHeritages;
    }

    public List<Heritage> getRecentlyMostPopularHeritages(){
        long now = System.currentTimeMillis();
        long nowMinusOneWeek = now - 7L * 24L * 3600L * 1000L; // From last week up until now
        Timestamp nowMinusOneWeekTimestamp = new Timestamp(nowMinusOneWeek);
        List<Heritage> recentHeritages = heritageDao.getHeritagesCreatedAfter(nowMinusOneWeekTimestamp);
        return sortByPopularity(recentHeritages);
    }
}
