package xyz.unicornfarts.dynamozagreb.models

import types._
import xyz.unicornfarts.dynamozagreb.client.Client
import xyz.unicornfarts.dynamozagreb.util._

sealed trait Table {
  val readCapacity: Long
  val writeCapacity: Long
  val tableName: String
}

sealed trait Keyed
class HashKeyed[H: DynamoPrimitive](val hashKey: H) extends Keyed
class RangeKeyed[H: DynamoPrimitive, R: DynamoPrimitive](hashKey: H, val rangeKey: R) extends HashKeyed[H](hashKey)

trait KeyedTable extends Table with Keyed {
  def create(implicit client: Client with Concurrent): client.R[KeyedTable]
}
