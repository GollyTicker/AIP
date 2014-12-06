AIP
===

How to import into IntelliJ:
* Import Project
* Click the default everywhere (except for the libraries, remove the one listed there.)
* when the Project folder opens, open the Module settings of API
* import the junit jar into the module dependencies (in tab Libraries)
* Now you can start the main programs

Testing the MPS System with independent Database:
* in /src/Main
* Start DatabaseServerMain
* Start MPSServerMain
* Start TestMPSConnector
* Now, write any number into the commandline into the mps connector.
* The system will execute the erstelleAuftrag(nr) for that number.
* Initially, only a test Angebot with number 0 will be created.
