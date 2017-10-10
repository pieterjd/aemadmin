package com.pieterjd.aemadmin.command.aem;

import com.pieterjd.aemadmin.command.CompositeCommand;
import com.pieterjd.aemadmin.command.crx.node.CreateNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.DeleteNodeCommand;
import com.pieterjd.aemadmin.command.crx.property.SetPropertyCommand;

/**
 * Created by pdrouill on 3/07/2017.
 */
public class CreatePageCommand extends CompositeCommand implements Cloneable{
    private String path,name,title,resourceType,template;
    private boolean deleteFirst;

    public CreatePageCommand(String path, String name, String title, String resourceType, String template) {
        this(path,name,title,resourceType,template,false);
    }
    public CreatePageCommand(String path, String name, String title, String resourceType, String template,boolean deleteFirst) {
        setPath(path);
        setName(name);
        setTitle(title);
        setResourceType(resourceType);
        setTemplate(template);
        setDeleteFirst(deleteFirst);
    }



    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public boolean isDeleteFirst() {
        return deleteFirst;
    }

    public void setDeleteFirst(boolean deleteFirst) {
        this.deleteFirst = deleteFirst;
    }

    @Override
    public void execute() {
        compose();
        super.execute();
    }

    protected void compose(){
        if(isDeleteFirst()){
            DeleteNodeCommand delete = new DeleteNodeCommand(getPath()+"/"+getName());
            add(delete);
        }
        CreateNodeCommand create = new CreateNodeCommand(getPath()+"/"+getName(),"cq:Page");
        add(create);
        String contentPath = getPath()+"/"+getName()+"/jcr:content";
        CreateNodeCommand createContent = new CreateNodeCommand(contentPath,"cq:PageContent");
        add(createContent);
        SetPropertyCommand template = new SetPropertyCommand(contentPath,"cq:template",getTemplate());
        add(template);
        SetPropertyCommand resourceType = new SetPropertyCommand(contentPath,"sling:resourceType",getResourceType());
        add(resourceType);
        SetPropertyCommand title = new SetPropertyCommand(contentPath,"jcr:title",getTitle());
        add(title);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new CreatePageCommand(getPath(),getName(),getTitle(),getResourceType(),getTemplate(),isDeleteFirst());
    }
}
