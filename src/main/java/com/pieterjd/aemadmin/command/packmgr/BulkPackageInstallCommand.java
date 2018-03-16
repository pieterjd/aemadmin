package com.pieterjd.aemadmin.command.packmgr;

import com.pieterjd.aemadmin.command.CompositeCommand;


/**
 * If you need to install a lot of packages over and over again, then this class is for you.
 * Define the list once, and run it over and over again.
 */
public class BulkPackageInstallCommand extends CompositeCommand {

    /**
     * Add a package to the list of packages to install
     * @param file the zip file you want to install
     * @return this
     */
    public BulkPackageInstallCommand addPackage(String file){
        UploadPackageCommand upc = new UploadPackageCommand(file,true,false);
        add(upc);
        return this;
    }
}
