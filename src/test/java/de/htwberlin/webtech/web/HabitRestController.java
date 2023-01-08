package de.htwberlin.webtech.web;

import de.htwberlin.webtech.service.HabitService;
import de.htwberlin.webtech.web.api.Habit;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HabitRestController.class)
class HabitRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HabitService habitService;

    @Test
    @DisplayName("should return found habits from habit service")
    void should_return_found_habit_from_habit_service() throws Exception {
        // given
        var habits = List.of(
                new Habit(1, "zuviele Sachen zu erledigen", true),
                new Habit(2, "nicht mehr procastinieren", false)
        );
        doReturn(habits).when(habitService).findAll();

        // when
        mockMvc.perform(get("/api/v1/habits"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("zuviele Sachen zu erledigen"))
                .andExpect(jsonPath("$[0].done").value(true))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("nicht mehr procastinieren"))
                .andExpect(jsonPath("$[1].done").value(false));
    }

    @Test
    @DisplayName("should return 404 if habit is not found")
    void should_return_404_if_habit_is_not_found() throws Exception {
        // given
        doReturn(null).when(habitService).findById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/habits/123"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status and Location header when creating a habit")
    void should_return_201_http_status_and_location_header_when_creating_a_habit() throws Exception {
        // given
        String habitToCreateAsJson = "{\"name\": \"zuviele Sachen zu erledigen\",\"done\": true}";
        var habit = new Habit(123, "",true);
        doReturn(habit).when(habitService).create(any());

        // when
        mockMvc.perform(
                        post("/api/v1/habits")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(habitToCreateAsJson)
                )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("api/v1/habits/" + habit.getId()))));
//            .andExpect(header().string("Location", Matchers.containsString(Long.toString(habit.getId()))));

    }

    @Test
    @DisplayName("should validate create habit request")
    void should_validate_create_habit_request() throws Exception {
        // given
        String habitToCreateAsJson = "{\"name\": \"\",\"done\": true}";

        // when
        mockMvc.perform(
                        post("/api/v1/habits")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(habitToCreateAsJson)
                )
                // then
                .andExpect(status().isBadRequest());
    }
}
