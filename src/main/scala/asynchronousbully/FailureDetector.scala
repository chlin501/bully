package asynchronousbully

sealed trait FDMessage
case class Down(nodeId: String) extends FDMessage

trait FailureDetector {

  /**
    * This would reset the monitoring of node i if being invoked again.
    * @param nodeId
    */
  def start[R](nodeId: String)(f: FDMessage => R)
  def stop(nodeId: String)
}
