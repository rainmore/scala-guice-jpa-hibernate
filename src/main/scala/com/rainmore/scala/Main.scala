package com.rainmore.scala

import javax.persistence.{EntityManager, Persistence, EntityManagerFactory}
import com.rainmore.scala.services.{HelloWorldSingleton, HelloWorld, PersonService}
import com.rainmore.scala.models.Person
import com.google.inject.{Guice, Injector}
import com.google.inject.persist.jpa.JpaPersistModule

object Main extends App {
  val injector: Injector = Guice.createInjector(new PersistenceModule, new JpaPersistModule("JpaBasicsTutorial"))
  val app: ApplicationInitializer = injector.getInstance(classOf[ApplicationInitializer])

  val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("JpaBasicsTutorial")
  val em: EntityManager = emf.createEntityManager()
  val service: PersonService = new PersonService(em)

  val h1: HelloWorld = injector.getInstance(classOf[HelloWorld])
  val h2: HelloWorld = injector.getInstance(classOf[HelloWorld])
  System.out.println(h1.toString)


  System.out.println(h2.toString)
  val hs1: HelloWorldSingleton = injector.getInstance(classOf[HelloWorldSingleton])
  val hs2: HelloWorldSingleton = injector.getInstance(classOf[HelloWorldSingleton])
  System.out.println(hs1.toString)


  System.out.println(hs2.toString)
  val p1: PersonService = injector.getInstance(classOf[PersonService])
  val p2: PersonService = injector.getInstance(classOf[PersonService])
  System.out.println(p1.toString)


  System.out.println(p2.toString)

  emf.close()

  def prPerson(person: Person): Unit = {
    println(person.toString)
  }
}
