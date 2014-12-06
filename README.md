AIP
===

How to import into IntelliJ:
* Import Project
* Click the default everywhere (except for the libraries, remove the one listed there.)
* when the Project folder opens, open the Module settings of API
* import the junit jar into the module dependencies (in tab Libraries)
* Now you can start the main programs

Running the Demo version:
* In pakage Main
* Start DatabaseServerMain
* Start MPSServerMain
* Edit the number in MPSServerMain.java and start a new one, if you want multiple mps systems
* Start ClientMain
* The GUI will start, and the list of servers is kept updated. Starting new Instances or killing older will be reflected in the list.
* Start/Stop server are not supported yet. Also, there are some exceptions.
* You can now start TestMPSConnector (which connects to a hardcoded MPSServer port) and write "0", "1", etc... to execute an erstelleAuftrag(n) system operation.
* Actual calls in a true program would be called after Initialization in ClientMain.

Testing the MPS System with independent Database:
* in /src/Main
* Start DatabaseServerMain
* Start MPSServerMain
* Start TestMPSConnector
* Now, write any number into the commandline into the mps connector.
* The system will execute the erstelleAuftrag(nr) for that number.
* Initially, only a test Angebot with number 0 will be created.

