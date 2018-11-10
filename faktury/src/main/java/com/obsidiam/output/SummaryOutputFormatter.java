package com.obsidiam.output;

import com.obsidiam.util.model.Summary;
import com.obsidiam.util.model.SummaryElement;

import java.util.logging.LogRecord;

/**
 * Implementation of AbstractOutputFormatter
 * @see AbstractOutputFormatter
 */
public class SummaryOutputFormatter extends AbstractOutputFormatter {
    private Summary summary;

    public SummaryOutputFormatter(FormatterType formatterType) {
        super(formatterType);
        this.formatterType = formatterType;
    }

    @Override
    public FormatterType getFormatterType() {
        return this.formatterType;
    }

    public void setSummary(Summary summary){
        this.summary = summary;
    }

    @Override
    public String format(LogRecord record) {
        String output = "";
        if(summary != null) {
            String header = String.format("%s%-20s\n",summary.getSeller().toString(),summary.getCustomer().toString());
            StringBuilder productList = new StringBuilder();
            output += summarySeparator();
            for(SummaryElement summaryElement : summary.getElements()){
                productList.append(summaryElement.toString());
            }
            String body = "Ordered products("+summary.getAmountOfItems()+")\n";
            output = output.concat(header).concat(body);
            //TODO: Additionally, we can add here a footer.
        }
        return output;
    }

    private String summarySeparator(){
        StringBuilder separator = new StringBuilder();
        for(int i = 0; i < 40; i++){
            separator.append("--");
        }
        return separator.toString()+"\n";
    }
}
