/*
 * jes2 class used to submit and track jobs
 * file name = SubmitAndTrack/src/com/triton/jes2.groovy
 * repo = https://github.com/db2dinosaur/SubmitAndTrack.git
 */
import com.ibm.zoautil.Jobs;
import com.ibm.zoautil.JobsOptions;

package com.triton.zos

public class jes2 {
  
  String sub(String jclDataset) {
    String jobn = "--FAIL--";
    jobn = Jobs.submit(jclDataset);
    return jobn;
  }

}
