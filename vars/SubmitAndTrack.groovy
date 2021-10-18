#!groovy

/* Submit a batch job, track it until completion and retrieve the output
  Parms are:
    JCLDatasetName  : MVS dataset name for the JCL to submit
    MaxCC           : Max job CC for success
    MaxWait         : Max wait time for job to complete
    DDList          : Array files to return (proc.step.ddname)
  Returns:
    <OK|FAIL> <Message>
    JESMSGLG
    Requested output DDs
*/
def call(String jcldsn,int maxcc,int maxwait,String[] ddlist) {
  /* start timer */
  def start = System.currentTimeMillis();
  def ctime = start;
  /* submit the job */
  String jobn = SubmitJob(jcldsn);
  String rmsg = "";
  def deadline = start + (1000 * maxwait);
  Boolean endofjob = false;
  String status = "";
  String[] st;
  status = CheckJob(jobn);
  st = status.tokenize(" ");
  /* wait for it to finish or timeout */
  while ((ctime <= deadline) && !endofjob) {
    status = CheckJob(jobn);
    /* get the status */
    st = status.tokenize(" ");
    if (st[3] != "AC") {
      endofjob = true;
    } else {
      sleep 1;
    }
    ctime = System.currentTimeMillis();
  }
  if (endofjob) {
    // CHECK CC
    if ((st[3] == "CC") && (st[4].toInteger() <= maxcc)) {
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
      rmsg = "FAIL ${st[3]} = ${st[4]}";
      rmsg += "\n" + GetDDNameJob(jobn,"JES2","JES2MSGLG");
    }
  } else {
    rmsg = "FAIL Job still running after ${maxwait} seconds";
  }
  return rmsg;
}
