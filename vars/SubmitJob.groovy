#!groovy

def call(def jclDataset) {
  def jobn = sh(returnStdout: true, script: "jsub ${jclDataset").trim()
  return jobn
}
                
