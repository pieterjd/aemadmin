package com.pieterjd.aemadmin.command.permissions;


/**
 * Based on the information on http://sling.apache.org/documentation/bundles/managing-permissions-jackrabbit-accessmanager.html#privileges
 */
public enum JCRPermission {
    READ("jcr:read","the privilege to retrieve a node and get its properties and their values"),
    READACCESSCONTROL("jcr:readAccessControl","the privilege to get the access control policy of a node"),
    MODIFYPROPERTIES("jcr:modifyProperties","the privilege to create, modify and remove the properties of a node"),
    ADDCHILDNODES("jcr:addChildNodes","the privilege to create child nodes of a node"),
    REMOVECHILDNODES("jcr:removeChildNodes","the privilege to remove child nodes of a node"),
    REMOVENODE("jcr:removeNode","the privilege to remove a node"),
    WRITE("jcr:write","an aggregate privilege that contains: jcr:modifyProperties jcr:addChildNodes jcr:removeNode jcr:removeChildNodes"),
    MODIFYACCESSCONTROL("jcr:modifyAccessControl","the privilege to modify the access control policies of a node"),
    LOCKMANAGEMENT("jcr:lockManagement","the privilege to lock and unlock a node"),
    VERSIONMANAGEMENT("jcr:versionManagement","the privilege to perform versioning operations on a node"),
    NODETYPEMANAGEMENT("jcr:nodeTypeManagement","the privilege to add and remove mixin node types and change the primary node type of a node"),
    RETENTIONMANAGEMENT("jcr:retentionManagement","the privilege to perform retention management operations on a node"),
    LIFECYCLEMANAGEMENT("jcr:lifecycleManagement","the privilege to perform lifecycle operations on a node"),
    ALL("jcr:all","an aggregate privilege that contains all predefined privileges");


    private final String permission;
    private final String description;
    JCRPermission(String permission,String description) {
        this.permission = permission;
        this.description = description;
    }
    public String value(){
        return permission;
    }
}
