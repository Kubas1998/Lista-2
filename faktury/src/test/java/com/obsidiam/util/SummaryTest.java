package com.obsidiam.util;

import static org.junit.Assert.assertEquals;

import com.obsidiam.util.model.Summary;
import com.obsidiam.util.model.SummaryElement;
import org.junit.Test;


public class SummaryTest {

    @Test
    public void testAddingElements(){
        /*Test if adding adding elements to the summary works*/

        Summary summary = new Summary();
        SummaryElement element = new SummaryElement();
        element.setElement("element");
        element.setAmount(1);
        element.setPrice(2.f);

        summary.addElement(element);

        assertEquals(1, summary.getAmountOfItems());
    }
}
