# SubmitAndTrack
Some Groovy functions to support z/OS Jenkins batch pipeline operations.

To use this you will need to:
1. Have the Rocket open source Git client installed on z/OS. There are good instructions on how to do this with miniconda on the Rocket Open Source site. It's free to register and use these, and there is a paid support license if you need to go down that route.
2. Have the IBM z/OS Open Automation (ZOA) Utilities installed. These turn our Jenkins z/OS node from a fairly pointless thing into a really useful this!

Functions available in SubmitAndTrack are:

* String SubmitJob(String datasetName)
* String CheckJob(String jobNumber)
* String[] DDNamesJob(String jobNumber)
* String GetDDNameJob(String jobNumber, String ddName)
