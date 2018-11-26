package com.pieterjd.aemadmin.command.aem;

import com.pieterjd.aemadmin.command.CompositeCommand;
import org.apache.commons.lang3.ObjectUtils;

/**
 * Created by pdrouill on 3/07/2017.
 */
public class CreatePageRangeCommand extends CompositeCommand {
    private CreatePageCommand createPageCommand;
    private int startIndex, endIndex;

    public CreatePageRangeCommand(CreatePageCommand command, int
            startIndex, int endIndex) {
        setCreatePageCommand(command);
        setStartIndex(startIndex);
        setEndIndex(endIndex);
        compose();
    }


    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public CreatePageCommand getCreatePageCommand() {
        return createPageCommand;
    }

    public void setCreatePageCommand(CreatePageCommand createPageCommand) {
        this.createPageCommand = createPageCommand;
    }

    protected void compose() {
        for (int i = getStartIndex(); i <= getEndIndex(); i++) {
            CreatePageCommand create = ObjectUtils.clone(getCreatePageCommand());
            create.setName(create.getName() + "-" + i);
            create.setTitle(create.getTitle() + "-" + i);
            //System.out.println(create.getId());
            add(create);
        }
    }
}
