# XpathValidator

This tool will validate the Xpaths for the Given Json and Xml files , and print the values on the console.

I have made some changes in the java-json.jar to pick "content" as key in order to print content as node in the generated xpaths.

HOW TO USE ::
Copy the .jar files and the library, run the below command :

Json::  java -cp XpathValidator.jar XpathCheck  -json test.json -p "Xpath"

eg  ::   java -cp XpathValidator.jar XpathCheck -json ../pns.json -p "//push-message[1]/nmsEventList[1]/nmsEvent[1]/changedObject[1]/message[1]/content[1]/rcs-data[1]/sip-call-id[1]/text()"

O/P ::   00001000000001145806829

Xml ::   java -cp XpathValidator.jar XpathCheck -xml test.xml -p "xpath"
