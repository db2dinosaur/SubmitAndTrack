# SubmitAndTrack
Some Groovy functions to support z/OS Jenkins batch pipeline operations.

To use this you will need to have the IBM z/OS Open Automation (ZOA) Utilities installed. These turn our Jenkins z/OS node from a fairly pointless thing into a really useful this! Once installed (default path is /usr/lpp/IBM/zoautil), make sure that it is in the PATH (.../bin) and LD_LIBRARY_PATH (.../lib) and MAN (.../docs/%L) for the Jenkins node userids .profile.

Functions that will be available in SubmitAndTrack are:

* String SubmitJob(String datasetName)
* String CheckJob(String jobNumber)
* String DDNamesJob(String jobNumber)
* String GetDDNameJob(String jobNumber, String ddName)

To use:

1. Setup this repository in Jenkins as a shared library (Manage Jenkins -> Configure Jenkins -> Global Pipeline Libraries, Add
   a) Name = SubmitAndTrack
   b) Version = main
   c) Retrieval method = Modern SCM
   d) Source code management = Git
   e) Project repository = https://github.com/db2dinosaur/SubmitAndTrack.git
   f) Credentials -> -none- is fine as this is a public repo
   SAVE
