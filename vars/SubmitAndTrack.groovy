#!groovy

/* Submit a batch job, track it until completion and retrieve the output
  Parms are:
    JCLDatasetName  : MVS dataset name for the JCL to submit
    MaxCC           : Max job CC for success
    MaxWait         : Max wait time for job to complete
    MaxFileSize     : Max # lines to retrieve for an output file
    DDList          : Array files to return (proc.step.ddname)
  Returns:
    <OK|FAIL> <Message>
    JESMSGLG
    Requested output DDs
*/
def call(String jcldsn,def maxcc,def maxwait,def maxfilesize,String[] ddlist) {
  /* start timer */
  Date start = Date();
  /* submit the job */
  String jobn = SubmitJob(jcldsn);
  String rmsg = "";
  Date deadline = start.GetTime() + (1000 * maxwait);
  Boolean endofjob = false;
  String status = "";
  String[] st;
  /* wait for it to finish or timeout */
  do while ((Date().GetTime() <= deadline) && !endofjob) {
    status = CheckJob(jobn);
    /* get the status */
    st = status.tokenize(" ");
    if (st[3] != "AC") {
      endofjob = true;
    } else {
      sleep 1;
    }
  }
  if (endofjob) {
    // CHECK CC / ABEND / ETC HERE!
    rmsg = "OK";
    rmsg += "\n" + GetDDNameJob(jobn,"JES2","JES2MSGLG");
    for (String ddprst in ddlist) {
      String[] parts = ddprst.split("\\.");
      if (parts.size() < 3) {
        rmsg += "\n" + GetDDNameJob(jobn,parts[0],parts[1]);
      } else {
        rmsg += "\n" + GetDDNameJob(jobn,"${parts[0]}.${parts[1]}",parts[2]);
      }
    }
  } else {
    rmsg = "FAIL Job still running after ${maxwait} seconds";
  }
  return rmsg;
}
