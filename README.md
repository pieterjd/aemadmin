# AEMAdmin

AEMAdmin is tool built to automate boring JCR/CRX.DE operations, such as delete a node, updating a property of a node, ...

It also contains AEM specific commands such as queries and page creation.

The list below is a high-level overview, much more details can be found in the Javadocs of each class.
## Node commands
These commands perform operations on node level, such as
* Create node
* Delete node
* Copy node
* Get a node

## Properties commands 
These commands perform operations on the properties of a node, such as
* add/update a property
* delete a property
* get a property

## CompositeCommand
Often you want to perform a sequence of tasks in a specific order. In this case,
you can use the CompositeCommand to define this sequence and execute it in one go.

## PostProcessCommand
In some cases you need to process the outcome of a command. That is exactly what
the ``PostProcessCommand`` class tackles.

A specific PostProcessCommand is already implemented for handling query results, ``QueryPostProcessCommand``.

## Node properties
When running your command, you sometimes need to provide information on the node you want to run the command on. If you have a main method, you can run this with the
``-DpropertiesFile=<path to a properties file>``

Required properties in this file are:
* userName (required)
* password (required)
* baseUrl (required)
* port (optional)

The properties file for a local author node looks like:
````
userName=admin
password=admin
baseUrl=http://localhost
port=4502
````
## Creating a standalone uberjar
If you want to run your command on a machine and do not want to download the whole codebase,
you can create a standalone uberjar.

Just make sure to update the mainClass value in the maven assembly plugin section in the``pom.xml`` file

