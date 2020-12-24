package org.example;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void arraylistTest() {
        ArrayList<Integer> mockList = mock(ArrayList.class);
        expect(mockList.add(10)).andReturn(true);
        expect(mockList.add(20)).andReturn(true);
        expect(mockList.size()).andReturn(2);
        expect(mockList.get(0)).andReturn(10);
        replay(mockList);
        mockList.add(10);
        mockList.add(20);
        assertEquals((int)mockList.get(0), 10);
        assertEquals(mockList.size(), 2);
        verify(mockList);
    }

}
