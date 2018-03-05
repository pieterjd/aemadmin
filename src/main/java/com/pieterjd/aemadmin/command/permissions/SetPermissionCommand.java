package com.pieterjd.aemadmin.command.permissions;


/**
 * Abstract superclass for updating permissions on a JCR node. Updating includes
 * adding, modifying and deleting permissions for a given principal.
 */
public abstract class SetPermissionCommand extends PermissionCommand {
    private String principalId;
    private String permission;
    public SetPermissionCommand(String path,String principalId,JCRPermission permission) {
        super(path);
        setPrincipalId(principalId);
        setPermission(permission);
    }

    public String getPrincipalId() {
        return principalId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(JCRPermission permission) {
        if(permission != null) {
            this.permission = permission.value();
        }
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }
}
