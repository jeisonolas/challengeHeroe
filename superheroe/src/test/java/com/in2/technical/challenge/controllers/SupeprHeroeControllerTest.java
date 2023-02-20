package com.in2.technical.challenge.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in2.technical.challenge.domain.entities.SuperHeroe;
import com.in2.technical.challenge.domain.dtos.SuperHeroeRequest;
import com.in2.technical.challenge.domain.interfaces.ISuperHeroeService;
import com.in2.technical.challenge.domain.repositories.ISuperHeroeRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SuperHeroeController.class)
public class SupeprHeroeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    SuperHeroeController heroeController;

    @MockBean
    private ISuperHeroeService service;
    @MockBean
    private ISuperHeroeRepository repository;
    private List<SuperHeroe> heroes = new ArrayList<>();
    private SuperHeroe heroe = new SuperHeroe();

    @Before
    public void Setup(){

        this.heroe = new SuperHeroe();
        this.heroe.setName("Superman");
        this.heroe.setAlterEgo("Lex Luthor");
        this.heroe.setPublisher("DC Comics");
        this.heroe.setFirstAppearance("1950");

        heroes.add(this.heroe);

        this.heroe = new SuperHeroe();
        this.heroe.setName("Shazam");
        this.heroe.setAlterEgo("Black Adam");
        this.heroe.setPublisher("DC Comics");
        this.heroe.setFirstAppearance("1960");

        heroes.add(this.heroe);
    }

    @Test
    public void getHeroeByIdTest() throws Exception {

        Mockito.when(service.getHeroeById(Mockito.anyLong()))
                        .thenReturn(this.heroes.get(1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/heroes/ById")
                        .param("id","5")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin","admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Is.is("Shazam")))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getHeroeById_ExceptionTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/heroes/ById")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin","admin")))
                .andExpect(status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getHeroeById_UnAuthorizedTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/heroes/ById"))
                .andExpect(status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getHeroesAllTest() throws Exception {

        Mockito.when(service.getHeroes())
                .thenReturn(this.heroes);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/heroes/all")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin","admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getHeroesByNameTest() throws Exception {

        this.heroes = this.heroes.stream().filter(item -> item.getName().contains("man"))
                        .collect(Collectors.toList());

        Mockito.when(service.getHeroesByName(Mockito.anyString()))
                .thenReturn(this.heroes);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/heroes/ByName")
                        .param("name","man")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin","admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void deleteHeroeTest() throws Exception {

        Mockito.when(service.getHeroeById(Mockito.anyLong()))
                .thenReturn(this.heroes.get(1));

        Mockito.when(service.deleteHeroe(Mockito.anyLong()))
                .thenReturn("Heroe was eliminated");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/heroes")
                        .param("id","5")
                        .with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin","admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is("Heroe was eliminated")))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void createHeroeTest() throws Exception {

        this.heroe = new SuperHeroe();
        this.heroe.setName("Ant man");
        this.heroe.setPublisher("Marvel Comics");
        this.heroe.setAlterEgo("Kang");
        this.heroe.setFirstAppearance("1975");

        Mockito.when(service.addHeroe(Mockito.any(SuperHeroeRequest.class)))
                .thenReturn(this.heroe);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/heroes")
                        .content(new ObjectMapper().writeValueAsString(this.heroe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin","admin")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Is.is(this.heroe.getName())))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void createHeroe_BadRequestTest() throws Exception {

        this.heroe = new SuperHeroe();
        this.heroe.setName("Ant man");

        Mockito.when(service.addHeroe(Mockito.any(SuperHeroeRequest.class)))
                .thenReturn(this.heroe);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/heroes")
                        .content(new ObjectMapper().writeValueAsString(this.heroe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin","admin")))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateHeroeTest() throws Exception {

        this.heroe = new SuperHeroe();
        this.heroe.setName("Ant man");
        this.heroe.setPublisher("Marvel Comics");
        this.heroe.setAlterEgo("Kang Conquer");
        this.heroe.setFirstAppearance("1985");

        Mockito.when(service.updateHeroe(Mockito.anyLong(),Mockito.any(SuperHeroeRequest.class)))
                .thenReturn(this.heroe);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/heroes")
                        .content(new ObjectMapper().writeValueAsString(this.heroe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id","5")
                        .with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin","admin")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Is.is(this.heroe.getName())))
                .andDo(MockMvcResultHandlers.print());
    }
}
