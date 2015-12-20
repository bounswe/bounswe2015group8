package api;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by gokcan on 17.12.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-test-context.xml"})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FollowApiTest {
    final Logger logger = Logger.getLogger(FollowApiTest.class);
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(new FollowApi()).build();
    }

    @Test
    public void testFollow() throws Exception{
        logger.info("testing following members...");
        JsonObject jsonFollow = new JsonObject();
        jsonFollow.addProperty("followerId", 24);
        jsonFollow.addProperty("followeeId", 23);
        mockMvc.perform(post("/api/follow").content(jsonFollow.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().contains("23");
    }

    @Test
    public void testUnfollow() throws Exception{
        logger.info("testing unfollowing members...");
        JsonObject jsonFollow = new JsonObject();
        jsonFollow.addProperty("followerId", 24);
        jsonFollow.addProperty("followeeId", 23);
        mockMvc.perform(post("/api/unfollow").content(jsonFollow.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().contains("23");
    }

    @Test
    public void testFollowHeritage() throws Exception{
        logger.info("testing following heritages...");
        JsonObject jsonFollow = new JsonObject();
        jsonFollow.addProperty("followerId", 24);
        jsonFollow.addProperty("heritageId", 3);
        mockMvc.perform(post("/api/followHeritage").content(jsonFollow.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().contains("3");
    }

    @Test
    public void testUnfollowHeritage() throws Exception{
        logger.info("testing unfollowing heritages...");
        JsonObject jsonFollow = new JsonObject();
        jsonFollow.addProperty("followerId", 24);
        jsonFollow.addProperty("heritageId", 3);
        mockMvc.perform(post("/api/unfollowHeritage").content(jsonFollow.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().contains("3");
    }

}
