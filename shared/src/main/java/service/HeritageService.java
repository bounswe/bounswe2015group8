package service;

import dao.HeritageDao;
import dao.HeritageDaoImpl;
import model.Heritage;
import model.Post;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
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

    public List<Heritage> getAllHeritages() {
        return heritageDao.getAllHeritages();
    }

    public List<Heritage> getHeritagesByPost(Post post){ return heritageDao.getHeritagesByPost(post); }

    public Heritage getFirstHeritageByPost(Post post){ return getHeritagesByPost(post).get(0); }

    public Heritage saveHeritage(String name, String place, String description, Timestamp postDate) {
        Heritage heritage = new Heritage(name, place, description, postDate);
        return heritageDao.saveHeritage(heritage);
    }
}
