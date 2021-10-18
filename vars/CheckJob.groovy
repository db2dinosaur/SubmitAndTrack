#!groovy

/* This function returns a string of the form:
    <owner> <jobname> <status> <code>
 where <owner> = userid associated with the job / STC / TSO user
       <jobname> = the job / STC / TSO user name
       <status>  = AC       <code> = ?    Job is running or waiting to run
                   CC       <code> = 0000 Job completed with highest CC=0000
                   CANCELED <code> = ?    Job was cancelled by the operator
                   ABEND    <code> = S522 Job abnormally ended with S522
                   JCLERR   <code> = ?    Job failed with JCL errors
*/

def call(def jobNum) {
  def jobst = sh(returnStdout: true, script: "jls ${jobNum}").trim();
  return jobst;
}
