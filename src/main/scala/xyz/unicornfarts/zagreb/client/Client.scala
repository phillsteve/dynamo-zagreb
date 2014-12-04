package xyz.unicornfarts.dynamozagreb.client

import xyz.unicornfarts.dynamozagreb.models._, types._
import scala.concurrent.Future
trait Client {
  def createTable[T <: Table](t: T): T
  def insertItem[T <: Table, I <: DynamoItem](t: T, item: I): (T,I)
}
