package exercise;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    // BEGIN
    @Container
    private static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("testDb")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> db.getJdbcUrl());
        registry.add("spring.datasource.username", () -> db.getUsername());
        registry.add("spring.datasource.password", () -> db.getPassword());
    }

    @Test
    void testGetAllPeople() throws Exception {
        MockHttpServletResponse responseGet = mockMvc
                .perform(get("/people"))
                .andReturn()
                .getResponse();
        assertThat(responseGet.getContentAsString()).isNotEqualTo("");
        assertThat(responseGet.getContentAsString()).contains("Jassica");
        assertThat(responseGet.getContentAsString()).contains("Smith");
    }

    @Test
    void testGetPersonById() throws Exception {
        MockHttpServletResponse responseGet = mockMvc
                .perform(get("/people/1"))
                .andReturn()
                .getResponse();

        assertThat(responseGet.getContentAsString()).contains("Smith");
    }

    @Test
    void testUpdatePersonById() throws Exception {
        MockHttpServletResponse responsePatch = mockMvc
                .perform(patch("/people/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Will\", \"lastName\": \"Brown\"}"))
                .andReturn()
                .getResponse();

        assertThat(responsePatch.getStatus()).isEqualTo(200);

        MockHttpServletResponse responseGet = mockMvc
                .perform(get("/people/1"))
                .andReturn()
                .getResponse();

        assertThat(responseGet.getContentAsString()).contains("Will");
        assertThat(responseGet.getContentAsString()).doesNotContain("Smith");
    }

    @Test
    void testDeletePersonById() throws Exception {
        MockHttpServletResponse responseDelete = mockMvc
                .perform(delete("/people/2"))
                .andReturn()
                .getResponse();

        assertThat(responseDelete.getStatus()).isEqualTo(200);

        MockHttpServletResponse responseGet = mockMvc
                .perform(get("/people"))
                .andReturn()
                .getResponse();

        assertThat(responseGet.getContentAsString()).doesNotContain("Jack");
        assertThat(responseGet.getContentAsString()).doesNotContain("Doe");
    }
        // END

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }
}
