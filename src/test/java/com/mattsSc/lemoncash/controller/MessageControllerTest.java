package com.mattsSc.lemoncash.controller;

import com.mattsSc.lemoncash.service.FuckOffService;
import com.mattsSc.lemoncash.utils.redis.RedisOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureJsonTesters
@WebMvcTest(controllers = MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuckOffService fuckOffService;

    @MockBean
    private RedisOperation redisOperation;

    @InjectMocks
    private MessageController messageController;


    @Test
    public void ValidateRequestWithNoQueryParams() throws Exception {
        Mockito.when(fuckOffService.getMessage(Mockito.anyString()))
                .thenReturn("Message");

        Mockito.when(redisOperation.exceedsRequest(Mockito.eq("User2"))).thenReturn(false);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "User2");

        MockHttpServletResponse response  = mockMvc.perform(
                        get("/api/lemon-fo/message")
                                .contextPath("/api/lemon-fo")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(),response.getStatus());
        Mockito.verify(fuckOffService, times(1)).getMessage(Mockito.anyString());
        Mockito.verify(redisOperation, times(1)).exceedsRequest(Mockito.eq("User2"));
        Mockito.verify(redisOperation, times(1)).saveAuthorizationKey(Mockito.eq("User2"));
        Mockito.verifyNoMoreInteractions(fuckOffService);
        Mockito.verifyNoMoreInteractions(redisOperation);
    }

    @Test
    public void ValidateRequestWithNameQueryParam() throws Exception {
        Mockito.when(fuckOffService.getMessageForName(Mockito.anyString(),Mockito.anyString()))
                .thenReturn("Message");

        Mockito.when(redisOperation.exceedsRequest(Mockito.eq("User2"))).thenReturn(false);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "User2");

        MockHttpServletResponse response  = mockMvc.perform(
                        get("/api/lemon-fo/message?name=menem")
                                .contextPath("/api/lemon-fo")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(),response.getStatus());
        Mockito.verify(fuckOffService, times(1)).getMessageForName(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(redisOperation, times(1)).exceedsRequest(Mockito.eq("User2"));
        Mockito.verify(redisOperation, times(1)).saveAuthorizationKey(Mockito.eq("User2"));
        Mockito.verifyNoMoreInteractions(fuckOffService);
        Mockito.verifyNoMoreInteractions(redisOperation);
    }

    @Test
    public void ValidateRequestWithCompanyQueryParam() throws Exception {
        Mockito.when(fuckOffService.getMessageForCompany(Mockito.anyString(),Mockito.anyString()))
                .thenReturn("Message");

        Mockito.when(redisOperation.exceedsRequest(Mockito.eq("User2"))).thenReturn(false);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "User2");

        MockHttpServletResponse response  = mockMvc.perform(
                        get("/api/lemon-fo/message?company=menem")
                                .contextPath("/api/lemon-fo")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(),response.getStatus());
        Mockito.verify(fuckOffService, times(1)).getMessageForCompany(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(redisOperation, times(1)).exceedsRequest(Mockito.eq("User2"));
        Mockito.verify(redisOperation, times(1)).saveAuthorizationKey(Mockito.eq("User2"));
        Mockito.verifyNoMoreInteractions(fuckOffService);
        Mockito.verifyNoMoreInteractions(redisOperation);
    }

    @Test
    public void ValidateRequestWithCompanyAndNameQueryParams() throws Exception {
        Mockito.when(fuckOffService.getMessage(Mockito.anyString(),Mockito.anyString(), Mockito.anyString()))
                .thenReturn("Message");

        Mockito.when(redisOperation.exceedsRequest(Mockito.eq("User2"))).thenReturn(false);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "User2");

        MockHttpServletResponse response  = mockMvc.perform(
                        get("/api/lemon-fo/message?company=menem&name=pepe")
                                .contextPath("/api/lemon-fo")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(),response.getStatus());
        Mockito.verify(fuckOffService, times(1)).getMessage(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(redisOperation, times(1)).exceedsRequest(Mockito.eq("User2"));
        Mockito.verify(redisOperation, times(1)).saveAuthorizationKey(Mockito.eq("User2"));
        Mockito.verifyNoMoreInteractions(fuckOffService);
        Mockito.verifyNoMoreInteractions(redisOperation);
    }

    @Test
    public void ReturnsUnauthorizedIfNotReceivedHeader() throws Exception {
        MockHttpServletResponse response  = mockMvc.perform(
                        get("/api/lemon-fo/message?company=menem&name=pepe")
                                .contextPath("/api/lemon-fo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        Mockito.verify(fuckOffService, times(0)).getMessage(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(redisOperation, times(0)).exceedsRequest(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(fuckOffService);
    }

    @Test
    public void ReturnsTooManyRequest() throws Exception {
        Mockito.when(redisOperation.exceedsRequest(Mockito.eq("User2"))).thenReturn(true);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "User2");

        MockHttpServletResponse response  = mockMvc.perform(
                        get("/api/lemon-fo/message?company=menem&name=pepe")
                                .contextPath("/api/lemon-fo")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.TOO_MANY_REQUESTS.value(), response.getStatus());
        Mockito.verify(fuckOffService, times(0)).getMessage(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(redisOperation, times(1)).exceedsRequest(Mockito.eq("User2"));
        Mockito.verifyNoMoreInteractions(fuckOffService);
        Mockito.verifyNoMoreInteractions(redisOperation);
    }

    @Test
    public void ReturnsServerErrorIfExceptionIsThrown() throws Exception {
        Mockito.when(fuckOffService.getMessage(Mockito.anyString(),Mockito.anyString(), Mockito.anyString()))
                .thenThrow(new RuntimeException());

        Mockito.when(redisOperation.exceedsRequest(Mockito.eq("User2"))).thenReturn(false);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "User2");

        MockHttpServletResponse response  = mockMvc.perform(
                        get("/api/lemon-fo/message?company=menem&name=pepe")
                                .contextPath("/api/lemon-fo")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        Mockito.verify(fuckOffService, times(1)).getMessage(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(redisOperation, times(1)).exceedsRequest(Mockito.eq("User2"));
        Mockito.verify(redisOperation, times(1)).saveAuthorizationKey(Mockito.eq("User2"));
        Mockito.verifyNoMoreInteractions(fuckOffService);
        Mockito.verifyNoMoreInteractions(redisOperation);
    }
}
