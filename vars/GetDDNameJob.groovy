#!groovy

/* This function returns the output for a DDname from a job
*/

def call(def jobNum, def stepName, def ddName) {
  String[] procstep = stepName.split("\\.");
  def proc = "";
  def step = "";
  if (procstep.size() > 1) {
    proc = procstep[0];
    step = procstep[1];
  } else {
    proc = "";
    step = procstep[0];
  }
  def ddfile = sh(returnStdout: true, script: "pjdd ${jobNum} ${proc} ${step} ${ddName}").trim();
  return ddfile;
}
