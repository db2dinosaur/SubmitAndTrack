#!groovy

/* Submit a batch job, track it until completion and retrieve the output
  Parms are:
    JCLDatasetName  : MVS dataset name for the JCL to submit
    MaxCC           : Max job CC for success
    MaxWait         : Max wait time for job to complete
    AllDDs          : If true then all DDs are returned, else just JESMSGLG
  Returns:
    <OK|FAIL> <Message>
    JESMSGLG
    Requested output DDs
*/
def call(String jcldsn,int maxcc,int maxwait,Boolean alldds) {
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
      if (alldds) {
        // get the DD names
        def ddnames = DDNamesJob(jobn);
        String[] ddnlist = ddnames.split("\\n");
        for (String ddn in ddnlist) {
          String[] ddnprts = ddn.tokenize(" ");
          if (ddnprts[1] == "-") {
            rmsg += "\n" + GetDDNameJob(jobn,ddnprts[0],ddnprts[2]);
          } else {
            rmsg += "\n" + GetDDNameJob(jobn,"${ddnprts[0]}.${ddnprts[1]}",ddnprts[2]);
          }
        }
      } else {
        rmsg += "\n" + GetDDNameJob(jobn,"JES2","JESMSGLG");
      }
    } else {
      rmsg = "FAIL ${st[3]} = ${st[4]}";
      rmsg += "\n" + GetDDNameJob(jobn,"JES2","JESMSGLG");
      error rmsg;
    }
  } else {
    rmsg = "FAIL Job still running after ${maxwait} seconds";
    error rmsg;
  }
  return rmsg;
}
