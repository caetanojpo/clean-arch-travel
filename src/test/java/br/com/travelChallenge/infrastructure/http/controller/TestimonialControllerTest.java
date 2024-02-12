package br.com.travelChallenge.infrastructure.http.controller;

import br.com.travelChallenge.domain.model.Testimonial;
import br.com.travelChallenge.infrastructure.http.dto.request.TestimonialRequest;
import br.com.travelChallenge.infrastructure.http.dto.response.TestimonialResponse;
import br.com.travelChallenge.usecase.testimonial.FindTestimonial;
import br.com.travelChallenge.usecase.testimonial.RemoveTestimonial;
import br.com.travelChallenge.usecase.testimonial.SaveTestimonial;
import br.com.travelChallenge.usecase.testimonial.UpdateTestimonial;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RequiredArgsConstructor
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TestimonialControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FindTestimonial find;

    @MockBean
    private SaveTestimonial save;

    @MockBean
    private UpdateTestimonial update;

    @MockBean
    private RemoveTestimonial remove;

    @Test
    @DisplayName("Create: Should return HTTP 200")
    void save() throws Exception {

        TestimonialRequest request = new TestimonialRequest("test description", "url", "Tester");

        Testimonial mockedTestimonial = Mockito.mock(Testimonial.class);

        when(save.execute(ArgumentMatchers.any())).thenReturn(mockedTestimonial);

        MvcResult result = mvc.perform(post("/testimonials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(mockedTestimonial.getId()))
                .andExpect(jsonPath("$.description").value(mockedTestimonial.getDescription()))
                .andExpect(jsonPath("$.photo").value(mockedTestimonial.getPhoto()))
                .andExpect(jsonPath("$.author").value(mockedTestimonial.getAuthor()))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        TestimonialResponse response = objectMapper.readValue(responseBody, TestimonialResponse.class);

        assertThat(response.id()).isEqualTo(mockedTestimonial.getId());
        assertThat(response.description()).isEqualTo(mockedTestimonial.getDescription());
        assertThat(response.photo()).isEqualTo(mockedTestimonial.getPhoto());
        assertThat(response.author()).isEqualTo(mockedTestimonial.getAuthor());

    }

    @Test
    @DisplayName("Find: Should return HTTP 200")
    void findById() throws Exception {
        Long id = 123L;

        Testimonial mockedTestimonial = Mockito.mock(Testimonial.class);

        when(find.byId(ArgumentMatchers.anyLong())).thenReturn(mockedTestimonial);

        MvcResult result = mvc.perform(get("/testimonials/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockedTestimonial.getId()))
                .andExpect(jsonPath("$.description").value(mockedTestimonial.getDescription()))
                .andExpect(jsonPath("$.photo").value(mockedTestimonial.getPhoto()))
                .andExpect(jsonPath("$.author").value(mockedTestimonial.getAuthor()))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        TestimonialResponse response = objectMapper.readValue(responseBody, TestimonialResponse.class);

        assertThat(response.id()).isEqualTo(mockedTestimonial.getId());
        assertThat(response.description()).isEqualTo(mockedTestimonial.getDescription());
        assertThat(response.photo()).isEqualTo(mockedTestimonial.getPhoto());
        assertThat(response.author()).isEqualTo(mockedTestimonial.getAuthor());
    }

    @Test
    void randomThree() throws Exception {

        Testimonial mockedTestimonial1 = Mockito.mock(Testimonial.class);
        Testimonial mockedTestimonial2 = Mockito.mock(Testimonial.class);
        Testimonial mockedTestimonial3 = Mockito.mock(Testimonial.class);

        List<Testimonial> mockedList = new ArrayList<Testimonial>();

        mockedList.add(mockedTestimonial1);
        mockedList.add(mockedTestimonial2);
        mockedList.add(mockedTestimonial3);

        when(find.getThree()).thenReturn(mockedList);

         mvc.perform(get("/testimonials/random")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn();

    }

    @Test
    void update() throws Exception {
        Long id = 123L;

        Testimonial mockedTestimonial = Mockito.mock(Testimonial.class);

        when(update.execute(any(), any())).thenReturn(mockedTestimonial);
        MvcResult result = mvc.perform(put("/testimonials/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockedTestimonial.getId()))
                .andExpect(jsonPath("$.description").value(mockedTestimonial.getDescription()))
                .andExpect(jsonPath("$.photo").value(mockedTestimonial.getPhoto()))
                .andExpect(jsonPath("$.author").value(mockedTestimonial.getAuthor()))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        TestimonialResponse response = objectMapper.readValue(responseBody, TestimonialResponse.class);

        assertThat(response.id()).isEqualTo(mockedTestimonial.getId());
        assertThat(response.description()).isEqualTo(mockedTestimonial.getDescription());
        assertThat(response.photo()).isEqualTo(mockedTestimonial.getPhoto());
        assertThat(response.author()).isEqualTo(mockedTestimonial.getAuthor());
    }

    @Test
    void remove() throws Exception {
        Long id = 123L;

        doNothing().when(remove).execute(anyLong());

        MvcResult result = mvc.perform(delete("/testimonials/{id}", id)).andExpect(status().isNoContent()).andReturn();
    }
}