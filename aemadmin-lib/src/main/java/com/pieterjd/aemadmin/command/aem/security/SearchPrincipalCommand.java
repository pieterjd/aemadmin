package com.pieterjd.aemadmin.command.aem.security;

import com.pieterjd.aemadmin.command.HttpRequestCommand;
import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

/**
 * Searches for a principal, similar as you do in useradmin
 *
 * Json output is like this for the everyone group:
 * <pre>
 *     {@code
 *{
   "authorizables":[
      {
         "aboutMe":"Built-in group automatically containing all existing users and groups. The list of members cannot be edited.",
         "aboutMe_xss":"Built-in group automatically containing all existing users and groups. The list of members cannot be edited.",
         "jcr:created":"2018-02-05T08:33:44.667+01:00",
         "jcr:createdBy":"admin",
         "principal":"everyone",
         "memberOf":[

         ],
         "memberOfTotal":0,
         "groupName":"everyone",
         "members":[
            {
               "id":"anonymous",
               "name":"anonymous",
               "name_xss":"anonymous",
               "home":"/home/users/v/vM723T8oABIaxt2DlnOE"
            },
            {
               "id":"admin",
               "name":"Administrator",
               "name_xss":"Administrator",
               "home":"/home/users/v/vRq2rskVGVHnn1cx3K7r"
            },
            <removed entrie to save space>
            {
               "id":"contributor",
               "name":"Contributors",
               "name_xss":"Contributors",
               "home":"/home/groups/c/contributor"
            }
         ],
         "membersTotal":169,
         "thumbnail":"",
         "modification":{

         },
         "replication":{
            "numQueued":0
         },
         "type":"group",
         "id":"everyone",
         "name":"everyone",
         "name_xss":"everyone",
         "home":"/home/groups/e/everyone"
      }
   ],
   "results":-1
}

 *     }
 * </pre>
 */

public class SearchPrincipalCommand extends HttpRequestCommand {
    private String keyword;

    public SearchPrincipalCommand(String keyword){
        setKeyword(keyword);
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedGetRequestBuilder("/bin/security/authorizables.json?filter="+getKeyword())
                .build();
    }
}
