package com.pieterjd.aemadmin.command;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pdrouill on 21/06/2017.
 */
public class CompositeCommand extends AbstractCommand {
    private List<AbstractCommand> commands;

    public CompositeCommand() {
        commands = new ArrayList<AbstractCommand>();
    }

    public int size() {
        return commands.size();
    }

    public boolean isEmpty() {
        return commands.isEmpty();
    }

    public Iterator<AbstractCommand> iterator() {
        return commands.iterator();
    }

    public boolean add(AbstractCommand abstractCommand) {
        return commands.add(abstractCommand);
    }

    public AbstractCommand get(int index) {
        return commands.get(index);
    }

    public void clear() {
        commands.clear();
    }

    public boolean addAll(Collection<? extends AbstractCommand> c) {
        return commands.addAll(c);
    }

    public void execute() {

        for(int i = 0; i<size() ; i++){

            AbstractCommand current = get(i);
            System.out.println("starting "+ current.getClass().getSimpleName());
            current.execute();
            setSuccess(isSuccess() && current.isSuccess());
            System.out.println(current);
            AbstractCommand previous = current;
            if(i+1<size()){
                current = get(i+1);
                //pass httpclient on to next in sequence
                //current.setHttpClient(previous.getHttpClient());
            }
        }
        setExecuted(true);
    }

    @Override
    protected ToStringBuilder getToStringBuilder() {
        return super.getToStringBuilder().append("size",size());
    }
}
