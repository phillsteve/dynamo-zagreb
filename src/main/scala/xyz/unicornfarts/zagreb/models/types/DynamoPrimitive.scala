package xyz.unicornfarts.dynamozagreb.models.types

trait DynamoPrimitive[T]
trait DynamoNumber extends DynamoPrimitive[scala.math.BigDecimal]
trait DynamoString extends DynamoPrimitive[String]
trait DynamoBinary extends DynamoPrimitive[java.nio.ByteBuffer]
trait DynamoBoolean extends DynamoPrimitive[Boolean]
object DynamoPrimitive {
  implicit object ToNumber extends DynamoNumber
  implicit object ToString extends DynamoString
  implicit object ToBinary extends DynamoBinary
  implicit object ToBoolean extends DynamoBoolean
  implicit object DynamoNull extends DynamoPrimitive[Nothing]
}
