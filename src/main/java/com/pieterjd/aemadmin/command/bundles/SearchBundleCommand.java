package com.pieterjd.aemadmin.command.bundles;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.AbstractCommand;
import com.pieterjd.aemadmin.command.StatusBundlesCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchBundleCommand extends AbstractCommand {
    private String keyword;
    private StatusBundlesCommand sbc;
    private List<Integer> hits;

    public List<Integer> getHits() {
        return hits;
    }

    private StatusBundlesCommand getSbc() {
        return sbc;
    }

    private void setSbc(StatusBundlesCommand sbc) {
        this.sbc = sbc;
    }

    public SearchBundleCommand(String keyword){
        setKeyword(keyword);
        setSbc(new StatusBundlesCommand());

    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public void execute() {
        getSbc().execute();
        try {
            JSONArray bundles = getSbc().getBundles();
            hits = new ArrayList<>();
            for(int i =0 ; i< bundles.length();i++){
                JSONObject bundle = bundles.getJSONObject(i);
                if(bundle.getString("name").contains(getKeyword()) ||
                        bundle.getString("symbolicName").contains(getKeyword())){
                    hits.add(bundle.getInt("id"));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
