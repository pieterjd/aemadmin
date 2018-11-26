package com.pieterjd.aemadmin.command;


import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.crx.property.SetPropertyCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A PostProcess command specific for queries. Usually you want to process the results
 * of a query. This class is provided to avoid boilerplate code. You only need to extend
 * this class and implement {@link #postProcessOneResult(JSONObject)}
 */
public abstract class QueryPostProcessCommand<T extends QueryCommand> extends PostProcessCommand<T> {


    public QueryPostProcessCommand(T command) {
        super(command);
    }

    /**
     * Queries return JSON with an array called hits. This method will process one element of this array
     * @param hit the result to process
     */
    public abstract void postProcessOneResult(JSONObject hit);

    /**
     * Loops over all searchresults and calls {@link #postProcess()}for each searchresult
     */
    @Override
    public void postProcess() {
        JSONObject results = getCommand().getQueryResult();
        JSONArray hits = results.getJSONArray("hits");
        System.out.println("# hits: " + hits.length());
        for (int i = 0; i < hits.length(); i++) {
           postProcessOneResult(hits.getJSONObject(i));
        }

    }

    @Override
    protected ToStringBuilder getToStringBuilder() {
        return super.getToStringBuilder().append("conditions",getCommand().getConditions());
    }
}
