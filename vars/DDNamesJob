#!groovy

/* This function returns a list of DD names available for download for a job:
     <step>  <proc>  <ddname>  <type>  <lrecl>  <#recs>
   Where: <step> is either JES2 for messages from JES2, or the job step name
          <proc> is the JCL proc name (if used), or "-"
          <type> is the data type - e.g. VB, VBA, or some weird ones for JES2
          <lrecl> is the logical (max) record length
          <#recs> is the number of records in the file
*/

def call(def jobNum) {
  def ddnames = sh(returnStdout: true, script: "ddls ${jobNum}").trim();
  return ddnames;
}
