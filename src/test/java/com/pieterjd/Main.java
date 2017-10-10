package com.pieterjd;

import com.pieterjd.aemadmin.command.AbstractCommand;
import com.pieterjd.aemadmin.command.CompositeCommand;
import com.pieterjd.aemadmin.command.HttpRequestCommand;
import com.pieterjd.aemadmin.command.LoginCommand;
import com.pieterjd.aemadmin.command.QueryCommand;
import com.pieterjd.aemadmin.command.StatusBundlesCommand;
import com.pieterjd.aemadmin.command.aem.replication.DeactivatePageCommand;
import com.pieterjd.aemadmin.command.aem.replication.ReplicationCommand;
import com.pieterjd.aemadmin.command.aem.replication.TreeActivationCommand;
import com.pieterjd.aemadmin.command.aem.replication.TreeDeactivationCommand;
import com.pieterjd.aemadmin.command.crx.node.CopyNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.DeleteNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.GetNodeCommand;
import com.pieterjd.aemadmin.command.aem.CreatePageCommand;
import com.pieterjd.aemadmin.command.aem.CreatePageRangeCommand;
import com.pieterjd.aemadmin.command.crx.property.DeletePropertyCommand;
import com.pieterjd.aemadmin.command.crx.property.GetPropertyCommand;
import com.pieterjd.aemadmin.command.crx.property.PropertyCommand;
import com.pieterjd.aemadmin.command.crx.property.SetPropertyCommand;
import com.pieterjd.aemadmin.command.packmgr.ListPackagesCommand;
import com.pieterjd.aemadmin.command.packmgr.PackageMgrCommand;
import com.pieterjd.aemadmin.mvc5058.ComponentQueryCommand;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by pdrouill on 21/06/2017.
 */
public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Properties props = new Properties();
        props.load(ClassLoader.getSystemResourceAsStream("localhost.properties"));
        AbstractCommand login = new LoginCommand();
        HttpRequestCommand delete = new DeleteNodeCommand("/apps/kpngb/widgets/multifield-store");
        HttpRequestCommand get = new GetNodeCommand("/content/kpngb-base/nl/sandbox/homepage/jcr:content/footer");
        StatusBundlesCommand statusBundles = new StatusBundlesCommand();
        PropertyCommand pcSet = new SetPropertyCommand("/content/kpngb-base/nl/sandbox/mvc-3456/jcr:content","blabla","testValue");
        PropertyCommand pcDel = new DeletePropertyCommand("/content/kpngb-base/nl/sandbox/mvc-3456/jcr:content","blabla","testValue");
        CopyNodeCommand copy = new CopyNodeCommand("/content/kpngb-base/nl/sandbox/homepage/jcr:content/footer","/content/kpngb-base/nl/jcr:content/footercopy");
        CreatePageCommand create = new CreatePageCommand("/content/kpngb-base/nl/sandbox/didmtc-faq","faq-2","FAQ 2","kpngb-whitelabel/components/page/responsive-faq-page","/apps/kpngb-whitelabel/templates/responsive-faq-page");
        CreatePageRangeCommand createRange = new CreatePageRangeCommand(create,10,17);
        CompositeCommand comp = new CompositeCommand();
        QueryCommand qCom = new QueryCommand();
        qCom.put("path","/apps");
        //qCom.put("1_property","jcr:primaryType");
        //qCom.put("1_property.value","cq:Page");
        qCom.put("p.limit","33");
        comp.add(statusBundles);
        comp.add(delete);
        PackageMgrCommand pmCmd = new ListPackagesCommand();

        ReplicationCommand rc = new TreeActivationCommand("/content/kpngb-base/nl/retail/our-offer",false,false);
        AbstractCommand toExecute = rc;

        GetPropertyCommand gpc = new GetPropertyCommand("/content/kpngb-base/nl/retail/jcr:content/onlinesales","isActivated");
        toExecute = gpc;
        toExecute.execute();
        System.out.println(gpc.getHttpResponseAsJSON());




        ComponentQueryCommand pathsQuery = new ComponentQueryCommand();
        //System.out.println(pathsQuery);

        qCom = pathsQuery;
        //qCom.execute();
        qCom.writeHttpResponseToFile("c:\\temp\\aemadmin-query.json");
        //statusBundles.execute();
        //statusBundles.toJson().write(new PrintWriter(System.out),2,0);

        //System.out.println(toExecute);
        HttpRequestCommand http = statusBundles;
        //http.execute();
        //System.out.println(http.getHttpResponseAsJSON());
        //System.out.println(get.getHttpResponseAsString());
        //c.add(login1);
        /*
        c2.execute();
        System.out.println(c2.getHttpResponseAsString());
        System.out.println(c2);
        */
    }
}
