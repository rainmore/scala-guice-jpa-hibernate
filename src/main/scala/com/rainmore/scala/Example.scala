package com.rainmore.scala

import com.rainmore.scala.models.Person
import javax.inject.Inject
import javax.persistence.EntityManager
import com.rainmore.scala.services.PersonService

class Example @Inject() (em: EntityManager, service: PersonService) {
  def run(): Unit = {

    em.getTransaction.begin()
    var p1 = service.create("Felix")
    var p2 = service.create("Catherine")
    p2.parent = p1
    em.getTransaction.commit()
    println("saved: ")
    if (p1 != null)
      println(p1)

    if (p2 != null)
      println(p2)

    val id: Long = p1.id

    var p3 = service.findById(id)
    println("found: ")
    println(p3)

    em.getTransaction.begin()
    service.changeName(p2.id, "YiYi")
    em.getTransaction.commit()


    println("--- Find all artists ---");
    var people = service.findAll
    people.foreach {person: Person =>
      println("Found: %s".format(person.toString))
      var children = service.findChildren(person)
      println("Found Children: %s".format(children.size))
    }

    em.getTransaction.begin()
    service.remove(p2.id)
    service.remove(p1.id)
    em.getTransaction.commit()
  }
}
