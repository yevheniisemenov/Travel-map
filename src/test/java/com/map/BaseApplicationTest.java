package com.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Andrew Pasika
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest // TODO: 9/17/16 change it
public class BaseApplicationTest {

    protected ObjectMapper objectMapper;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }
}
