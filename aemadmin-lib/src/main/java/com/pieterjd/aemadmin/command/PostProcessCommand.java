package com.pieterjd.aemadmin.command;

/**
 * Created by pdrouill on 13/07/2017.
 * When a Command has executed, you sometimes want to do some post processing.
 * Keep in mind, this class only provides an abstract method for the actual post processing.
 * The subclass is responsible for providing a constructor that accepts
 * an AbstractCommand object or defining a field containing a Command object.
 *
 * This approach has been chosen to avoid casting an AbstractCommand object to a more specific
 * object (eg a GetNodeCommand object).
 *
 * Updated: using generics
 * The supplied command doesn't has to be execute before this postprocess is executed. It is now part
 * of the {@link PostProcessCommand}'s execute.
 * @param <T> The type of command that needs postprocessing
 */
public abstract class PostProcessCommand<T extends AbstractCommand> extends AbstractCommand {
    private T command;

    public PostProcessCommand(T command) {
        super();
        this.command = command;
    }
    public T getCommand(){
        return command;
    }

    /**
     * Do some post processing based on the already executed command.
     */
    public abstract void postProcess();

    @Override
    public void execute() {
        getCommand().execute();
        postProcess();
    }
}
