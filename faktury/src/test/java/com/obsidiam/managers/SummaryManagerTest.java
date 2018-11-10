package com.obsidiam.managers;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class SummaryManagerTest {

    @Test
    public void createSummaryTest(){
        SummaryManager summaryManager = new SummaryManager();
        int result = summaryManager.createSummary(0,1);
        assertNotEquals(result, 0);
        assertNotEquals(result, -1);
    }

    @Test
    public void createSummaryElementTest(){
        SummaryManager summaryManager = new SummaryManager();
        int result = summaryManager.createSummaryElement(0, "test", 12.0, 1);
        assertNotEquals(result, 0);
        assertNotEquals(result, -1);
    }
}
