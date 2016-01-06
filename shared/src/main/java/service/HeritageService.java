package service;

import dao.HeritageDao;
import dao.HeritageDaoImpl;
import model.Heritage;
import model.Post;
import model.Tag;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
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

    public Heritage saveHeritage(String name, String place, String description, Timestamp postDate, Timestamp eventDate) {
        Heritage heritage = new Heritage(name, place, description, postDate, eventDate);
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

    public List<Heritage> removeDuplicates(List<Heritage> heritages){
        List<Integer> toRemoved = new ArrayList<>();
        HashSet<Long> heritageIds = new HashSet<>();
        for(int i = 0; i < heritages.size(); i++){
            if(!heritageIds.contains(heritages.get(i).getId())){
                heritageIds.add(heritages.get(i).getId());
            }
            else{
                toRemoved.add(i);
            }
        }
        for(int i = toRemoved.size()-1; i >= 0; i--){
            heritages.remove((int)toRemoved.get(i));
        }
        return heritages;
    }

    public List<Heritage> getHeritagesCreatedAfter(Calendar calendar){
        Timestamp date = new Timestamp(calendar.getTimeInMillis());
        List<Heritage> heritages = heritageDao.getHeritagesCreatedAfter(date);
        return heritages;
    }

    public List<Heritage> getHeritagesCreatedBefore(Calendar calendar){
        Timestamp date = new Timestamp(calendar.getTimeInMillis());
        List<Heritage> heritages = heritageDao.getHeritagesCreatedBefore(date);
        return heritages;
    }

    public List<Heritage> getHeritagesContainName(String name) { return heritageDao.getHeritagesContainName(name); }

    public List<Heritage> getHeritagesContainDescription(String description) { return heritageDao.getHeritagesContainDescription(description); }
}
