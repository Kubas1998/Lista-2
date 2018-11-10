package com.obsidiam.output;

import com.obsidiam.util.model.Summary;
import com.obsidiam.util.model.SummaryElement;
import com.obsidiam.util.model.User;
import com.obsidiam.util.model.UserType;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import static org.junit.Assert.assertNotNull;

public class OutputControllerTest {
    @Test
    public void testSummaryPrint(){
        //TODO: Add normal get from db here, use BeforeTest override interface
        Summary summary = new Summary();
        summary.setCustomer(new User());
        summary.getCustomer().setType(UserType.CUSTOMER);
        summary.setSeller(new User());
        summary.getSeller().setType(UserType.SELLER);

        SummaryElement element = new SummaryElement();
        element.setElement("element");
        element.setAmount(1);
        element.setPrice(2.f);

        summary.addElement(element);

        OutputController.getControllerInstance().printSummary(summary);
    }

    @Test
    public void testStandardOutput(){
        OutputController.getControllerInstance().printMessage(new LogRecord(Level.ALL, "Test message."));
    }

    @Test
    public void outputControllerInstanceCheck(){
        assertNotNull(OutputController.getControllerInstance());
    }
}
