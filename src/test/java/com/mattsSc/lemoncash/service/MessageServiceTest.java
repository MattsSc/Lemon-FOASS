package com.mattsSc.lemoncash.service;

import com.mattsSc.lemoncash.connector.FuckOffConnector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;

public class MessageServiceTest {
    @Mock
    private FuckOffConnector fuckOffConnector;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMessage(){
        var from = "from";

        Mockito.when(fuckOffConnector.getMessage(Mockito.eq(from))).thenReturn("Message");
        messageService.getMessage(from);

        Mockito.verify(fuckOffConnector, times(1)).getMessage(from);
        Mockito.verifyNoMoreInteractions(fuckOffConnector);
    }

    @Test
    public void getMessageThrowsError(){
        var from = "from";

        Mockito.when(fuckOffConnector.getMessage(Mockito.eq(from))).thenThrow(new RuntimeException());

        Assertions.assertThrows(RuntimeException.class, () -> messageService.getMessage(from));

        Mockito.verify(fuckOffConnector, times(1)).getMessage(from);
        Mockito.verifyNoMoreInteractions(fuckOffConnector);
    }

    @Test
    public void getMessageForName(){
        var from = "from";
        var name = "name";

        Mockito.when(fuckOffConnector.getMessageForName(Mockito.eq(name),Mockito.eq(from))).thenReturn("Message");
        messageService.getMessageForName(name, from);

        Mockito.verify(fuckOffConnector, times(1)).getMessageForName(name, from);
        Mockito.verifyNoMoreInteractions(fuckOffConnector);
    }

    @Test
    public void getMessageForCompany(){
        var from = "from";
        var company = "company";

        Mockito.when(fuckOffConnector.getMessageForCompany(Mockito.eq(company),Mockito.eq(from))).thenReturn("Message");
        messageService.getMessageForCompany(company, from);

        Mockito.verify(fuckOffConnector, times(1)).getMessageForCompany(company, from);
        Mockito.verifyNoMoreInteractions(fuckOffConnector);
    }

    @Test
    public void getMessageForCompanyAndName(){
        var from = "from";
        var company = "company";
        var name = "name";

        Mockito.when(fuckOffConnector.getMessage(Mockito.eq(company), Mockito.eq(name), Mockito.eq(from))).thenReturn("Message");
        messageService.getMessage(company, name, from);

        Mockito.verify(fuckOffConnector, times(1)).getMessage(company, name, from);
        Mockito.verifyNoMoreInteractions(fuckOffConnector);
    }



}

