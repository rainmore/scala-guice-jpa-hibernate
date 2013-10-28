package com.rainmore.scala.models

import javax.persistence._
import scala.beans.BeanProperty

@Entity
@Table(name = "person")
class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty var id: Long = _

  @BeanProperty var name: String = _

  @ManyToOne(cascade = Array(CascadeType.ALL))
  @JoinColumn(name = "parent_id")
  @BeanProperty var parent: Person = _

  override def toString = "id: %d, name: %s, pareant: %s".format(id, name, parent)

}
