package com.rainmore.scala.services

import javax.persistence.{TypedQuery, EntityManager}
import com.rainmore.scala.models.Person
import scala.collection.JavaConversions._
import com.google.inject.persist.Transactional
import javax.inject.Inject

class PersonService @Inject() (val em: EntityManager) {

  @Transactional
  def create(name: String): Person = {
    val person: Person = new Person()
    person.name = name
    em.persist(person)
    person
  }

  @Transactional
  def changeName(id: Long, name: String): Person = {
    val person: Person = em.find(classOf[Person], id)
    if (person != null) {
      person.name = name
      em.persist(person)
    }
    person
  }

  @Transactional
  def remove(id: Long): Unit = {
    val person: Person = em.find(classOf[Person], id)
    if (person != null) {
      em.remove(person)
    }
  }

  def findById(id: Long): Person = {
    return em.find(classOf[Person], id)
  }

  def findAll: List[Person] = {
    val query: TypedQuery[Person] = em.createQuery("SELECT a FROM Person a", classOf[Person])
    query.getResultList.toList
  }

  def findChildren(person: Person): List[Person] = {
    val sql: String = "SELECT a FROM Person a WHERE parent_id = :parent_id"
    val query: TypedQuery[Person] = em.createQuery(sql, classOf[Person]).setParameter("parent_id", person.getId)

    query.getResultList.toList
  }

}

