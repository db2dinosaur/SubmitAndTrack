# SubmitAndTrack
Some Groovy functions to support z/OS Jenkins batch pipeline operations.

To use this you will need to:
1. Have the Rocket open source Git client installed on z/OS. There are good instructions on how to do this with miniconda on the Rocket Open Source site. It's free to register and use these, and there is a paid support license if you need to go down that route. Having installed it, make sure that the .profile for the Jenkins node userid sets PATH and LD_LIBRARY_PATH to pick it up. Note also that the z/OS remote node definition in Jenkins needs to specify "git" as the Git tool, otherwise Jenkins will try and use "git.exe".
2. Have the IBM z/OS Open Automation (ZOA) Utilities installed. These turn our Jenkins z/OS node from a fairly pointless thing into a really useful this! Once installed (default path is /usr/lpp/IBM/zoautil), make sure that it is in the PATH and LD_LIBRARY_PATH (and MAN) for the Jenkins node userids .profile.

Functions available in SubmitAndTrack are:

* String SubmitJob(String datasetName)
* String CheckJob(String jobNumber)
* String[] DDNamesJob(String jobNumber)
* String GetDDNameJob(String jobNumber, String ddName)

To use:

1. Setup this repository in Jenkins as a shared library (Manage Jenkins -> Configure Jenkins -> Global Pipeline Libraries, Add
   a) Name = SubmitAndTrack
   b) Version = main
   c) Retrieval method = Modern SCM
   d) Source code management = Git
   e) Project repository = https://github.com/db2dinosaur/SubmitAndTrack.git
   f) Credentials -> Add -> User = GitHub userid / Pwd = GitHub password
   SAVE
