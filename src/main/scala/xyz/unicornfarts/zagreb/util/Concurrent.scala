package xyz.unicornfarts.dynamozagreb.util

import scala.util.Try
import scala.concurrent.{Future, future, ExecutionContext}

trait Concurrent {
  type R[+A]

  val executionContext: Option[ExecutionContext] = None
  implicit lazy val ec: ExecutionContext = executionContext match {
    case Some(ec) => ec
    case None => scala.concurrent.ExecutionContext.Implicits.global
  }

}
trait Sync extends Concurrent {
  type R[+A] = Option[Try[A]]
}
trait Async extends Concurrent {
  type R[+A] = Future[A]
}

object Concurrent {
  implicit class ExtendedConcurrent(val c: Concurrent) {
    implicit val ec = c.ec
    def execute[A](f:{ def task: A }): c.R[A] = this match {
      case self: Sync => Some(f.task).asInstanceOf[c.R[A]]
      case self: Async => future { f.task }.asInstanceOf[c.R[A]]
    }
  }
}
/*

This code allow you to abstruct an operation that might be preformed either Syncronicly or Asyncronicly
If 
Example: 

trait MyJob extends Concurrent {
  def equal(i:Int, s: String): Boolean = s.toInt == i
  def equalFor(i: Int,s: String): R[Boolean] = this match {
    case self: Sync => Some(equal(i,s)).asInstanceOf[R[Boolean]]
    case self: Async => future { equal(i,s) }.asInstanceOf[R[Boolean]]
  }
}
trait MyAsyncJob extends MyJob with Async {
  override val executionContext = Some(scala.concurrent.ExecutionContext.Implicits.global)
  override def equalFor(i: Int, s: String): R[Boolean] = super.equalFor(i,s)
}
class MySyncJob extends MyJob with Sync
object CodeConsumer {
  def doSomethingSynchronic: Sync#R[Boolean] = {
    val syncJob = new MyJob with Sync
    syncJob.equalFor(1,"1")
  }

  def doSsomethingAynchronic: Async#R[Boolean] = {
    val asyncJob = new MyAsyncJob {
      override val executionContext = Some(scala.concurrent.ExecutionContext.Implicits.global)
    }
    asyncJob.equalFor(1,"1")
  }
}
*/