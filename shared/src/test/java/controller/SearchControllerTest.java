package controller;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by gokcan on 14.12.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-test-context.xml"})
@WebAppConfiguration
public class SearchControllerTest {
    final Logger logger = Logger.getLogger(SearchControllerTest.class);

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(new SearchController()).build();
    }

    @Test
    public void testSearchByTag() throws Exception{
        logger.info("testing searchByTag...");
        mockMvc.perform(get("/searchByTag/{wholetag}", "lokum"))
                .andExpect(status().isOk())
                .andExpect(view().name("list_post"));
    }
}
