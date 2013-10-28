package com.rainmore.scala

import com.google.inject.{Provides, AbstractModule}
import com.rainmore.scala.services.{HelloWorldSingleton, HelloWorld, PersonService}

class PersistenceModule extends AbstractModule {
  def configure(): Unit = {
    bind(classOf[Example])
    bind(classOf[PersonService])
    bind(classOf[HelloWorldSingleton])
  }

  @Provides def provideHelloWorld = {
    new HelloWorld
  }
}
