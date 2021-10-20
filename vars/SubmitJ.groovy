#!groovy

include com.ibm.zoautils.Jobs
include com.ibm.zoautils.JobsOptions

String call(String jclDataset) {
  JobsOptions jobctrl = new JobsOptions();
  jobctrl.maxRC(0);
  jobctrl.failOnError();
  String jobn = submit(jclDataset,jobctrl);
  return jobn;
}
