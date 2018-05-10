[![Build Status](https://travis-ci.org/pieterjd/aemadmin.svg?branch=master)](https://travis-ci.org/pieterjd/aemadmin)

- [AEMAdmin](#aemadmin)
  * [Node commands](#node-commands)
  * [Properties commands](#properties-commands)
  * [CompositeCommand](#compositecommand)
  * [PostProcessCommand](#postprocesscommand)
  * [Instance properties](#instance-properties)
  * [Creating a standalone uberjar](#creating-a-standalone-uberjar)
- [Talks about AEMAdmin](#talks-about-aemadmin)
  * [AEM meetup hosted by Emakina Amsterdam](#aem-meetup-hosted-by-emakina-amsterdam)
- [Licensing & Attribution](#licensing---attribution)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>
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

## Instance properties
When running your command, you sometimes need to provide information on the instance you want to run the command on. If you have a main method, you can run this with the
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

Just make sure to update the mainClass value in the maven assembly plugin section in the ``pom.xml`` file.

By default the name is just a concatenation of the artifactid, version and ``jar-with-dependencies``. If you want
to replace the first2 parts, update the ``uberjar.prefix`` property in the pom file.

# Talks about AEMAdmin
## AEM meetup hosted by Emakina Amsterdam
My collegue Joeri asked me to give a last-minute presentation on "that tool you're working on". More information
on the [meetup page itself](https://www.meetup.com/AEM-Developer-Meetup/events/248690741/).
<strong> <a href="//www.slideshare.net/m9600174/talk-aem-meetup-hosted-by-emakina-amsterdam" title="Talk @ AEM meetup hosted by Emakina Amsterdam" target="_blank">Talk @ AEM meetup hosted by Emakina Amsterdam</a> </strong> from <strong><a href="https://www.slideshare.net/m9600174" target="_blank">Pieter-Jan Drouillon</a></strong>

# Licensing & Attribution
This software is available for free and can be modified in any way. However, when you do so, please add the following small
text to your webpage, blogpost, paper, ...

> Original idea and implementation by Pieter-Jan Drouillon
