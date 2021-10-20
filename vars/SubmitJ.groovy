
/* import com.ibm.zoautil.Jobs;
   import com.ibm.zoautil.JobsOptions;
*/
import com.ibm.zoautil.*;

String call(String jclDataset) {
  JobsOptions jobctrl = new JobsOptions();
  jobctrl.maxRC(0);
  jobctrl.failOnError();
  String jobn = "--FAIL--";
  try {
    jobn = Jobs.submit(jclDataset,jobctrl);
  } catch (Exception e) {
    e.printStackTrace();
  }
  return jobn;
}
