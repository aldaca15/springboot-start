package com.test.springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springboot.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@AutoConfigureMockMvc
public class HttpRestapiJunitUserTest {

    private final Log LOG = LogFactory.getLog(HttpRestapiJunitUserTest.class);

    /*@MockBean
    UserService userService;*/

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    // Test an existing element for GET operations
    @Test
    public void testUserRetrieval() throws Exception {
        mockMvc.perform(get("/api/users/getFirst")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Ali"))
                .andExpect(jsonPath("$.email").value("ali.adamecantoran@test.com"))
                .andExpect(jsonPath("$.birthDate").value("1991-12-15"));
                //.andExpect(jsonPath("$[0].id").exists());
    }

    // Test non-existing element for GET operations
    @Test
    public void testUserRetrivalNotFound() throws Exception {
        mockMvc.perform(get("/api/users/1000")).andExpect(status().isNotFound());
    }

    @Test
    public void testUserInsertionOk() throws Exception {
        // Mock user obj
        String userEmail = "eliwinst@test.com";
        
        User newUser = new User();
        newUser.setEmail(userEmail);
        newUser.setName("Mary");
        newUser.setBirthDate(LocalDate.of(1986,10,10));

        mockMvc.perform(post("/api/users/")
                        .content(asJsonString(newUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Mary"))
                .andExpect(jsonPath("$.email").value(userEmail))
                .andExpect(jsonPath("$.birthDate").value("1986-10-10"));

    }

    @Test
    public void testUserInsertionFailed() throws Exception {

        User newUser = new User();
        newUser.setEmail(null); // A null email, violation
        newUser.setName("Sophie");
        newUser.setBirthDate(LocalDate.of(1986,10,10));

        String response = mockMvc.perform(post("/api/users/")
                        .content(asJsonString(newUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        LOG.info(response);

    }

    @Test
    public void testUserUpdateOk() throws Exception {
        // Mock user obj
        String userNewEmail = "kenya23.g@hotmail.com";
        String userNewName = "Kenya G.";
        LocalDate userNewBirthdate = LocalDate.of(1996,01,23);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        User newUser = new User();
        newUser.setId(3L);
        newUser.setEmail(userNewEmail);
        newUser.setName(userNewName);
        newUser.setBirthDate(userNewBirthdate);

        /* If not JSON params (test pending)
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>()
        requestParams.add("id", "1");
        requestParams.add("name", "john");
        requestParams.add("age", "30");*/

        mockMvc.perform(put("/api/users/"+newUser.getId().toString())
                        .content(asJsonString(newUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value(userNewName))
                .andExpect(jsonPath("$.email").value(userNewEmail))
                .andExpect(jsonPath("$.birthDate").value(userNewBirthdate.format(formatter)));

    }

    @Test
    public void testUserDeletionById() throws Exception {
        mockMvc.perform(delete("/api/users/138")).andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
