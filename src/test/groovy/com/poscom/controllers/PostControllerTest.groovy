package com.poscom.controllers

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.poscom.model.Post
import com.poscom.repository.PostRepository
import com.poscom.rest.RestResponse
import spock.lang.Ignore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(["controllerTest"])
class PostControllerTest extends Specification {

    @Autowired MockMvc mockMvc
    @Autowired PostRepository postRepository;

    ObjectMapper mapper

    void setup() {
        mapper  = new ObjectMapper()
    }

    def 'getPosts() return all posts' () {
        when:
        def response = mockMvc.perform(
                post("/posts")
                        .header("Content-Type", "application/json;charset=UTF-8")
        )
                .andDo(print())

        then: 'Verify no exception thrown'
        noExceptionThrown()

        and: 'Validate response'
        def result = response
                .andExpect(status().isOk())
                .andReturn()

        and: 'Validate content'
        List<Post> posts = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), RestResponse)
                .getBody(new TypeReference<List<Post>>() {})

        posts[0].id == 1L
        posts[1].id == 2L
    }

    def 'getPost() return first post' () {
        when:
        def response = mockMvc.perform(
                post("/post/1")
                        .header("Content-Type", "application/json;charset=UTF-8")
        )
                .andDo(print())

        then: 'Verify no exception thrown'
        noExceptionThrown()

        and: 'Validate response'
        def result = response
                .andExpect(status().isOk())
                .andReturn()

        and: 'Validate content'
        Post post = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), RestResponse)
                .getBody(new TypeReference<Post>() {})

        post.id == 1L
        post.title == "This is about fitness"
        post.description == "Fitness is a very interesting thing"

    }

    //ToDo return IllegalStateException instead of NestedServletException
    @Ignore
    def 'getPost() When asked to get post with invalid ID, Error thrown' () {
        when:
        def response = mockMvc.perform(
                post("/post/500")
                        .header("Content-Type", "application/json;charset=UTF-8")
        )
                .andDo(print())

        then: 'Verify IllegalStateException is thrown'
        thrown IllegalStateException


        and: "Validate response"
        def result = response
                .andExpect(status().isInternalServerError())
                .andReturn()
    }
}
