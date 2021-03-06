package com.github.gvolpe.fs2rabbit

import com.github.gvolpe.fs2rabbit.model.{AmqpHeaderVal, AmqpProperties, IntVal}
import org.scalatest.{FlatSpecLike, Matchers}
import com.rabbitmq.client.AMQP

class AmqpPropertiesSpec extends FlatSpecLike with Matchers {

  it should "create an empty amqp properties" in {
    AmqpProperties.empty should be (AmqpProperties(None, None, Map.empty[String, AmqpHeaderVal]))
  }

  it should "convert from and to Java AMQP.BasicProperties" in {
    val props = AmqpProperties(Some("application/json"), Some("UTF-8"), Map("k" -> IntVal(1)))
    val basic = props.asBasicProps
    AmqpProperties.from(basic) should be (props)
  }

  it should "handle null values in Java AMQP.BasicProperties" in {
    val basic = new AMQP.BasicProperties()
    AmqpProperties.from(basic) should be (AmqpProperties.empty)
  }
}
