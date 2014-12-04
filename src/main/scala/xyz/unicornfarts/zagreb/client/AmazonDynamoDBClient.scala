package xyz.unicornfarts.dynamozagreb.client

import com.amazonaws.services.dynamodbv2.model._

class AmazonDynamoDBClient extends SyncClient {
  override def createTable[T <: KeyedTable](t: T): T = {
    val provisionedThroughput = new ProvisionedThroughput(readCapacity, writeCapacity)

    val keys = t match {
      case HashKeyed(h) => List(new KeySchemaElement(KeyType.HASH))
    
  }
}
