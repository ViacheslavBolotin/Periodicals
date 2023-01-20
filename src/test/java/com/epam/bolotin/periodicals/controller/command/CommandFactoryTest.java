package com.epam.bolotin.periodicals.controller.command;

import com.epam.bolotin.periodicals.controller.command.admin.EditTopicCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommandFactoryTest {

    @Mock
    private HttpServletRequest request;

    @Test
    void testGetCommand() {

        Mockito.when(request.getParameter("action")).thenReturn("edit_topic");
        CommandFactory commandFactory = CommandFactory.getCommandFactory();
        Command command = commandFactory.getCommand(request);
        assertEquals(new EditTopicCommand().getClass(), command.getClass());

    }
}
