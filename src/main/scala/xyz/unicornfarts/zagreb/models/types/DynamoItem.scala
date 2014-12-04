package xyz.unicornfarts.dynamozagreb.models.types

import xyz.unicornfarts.dynamozagreb.client.Client

trait DynamoItem {
  //def save(implicit client: Client): client.R[DynamoItem]
}
class PrimitiveItem[P: DynamoPrimitive](p: P) extends DynamoItem
class ListItem extends DynamoItem // with scala.collection.immutable.LinearSeq[DynamoItem]
class MapItem extends DynamoItem //with Map[String, DynamoItem]

