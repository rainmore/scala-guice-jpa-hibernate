package com.rainmore.scala

import com.google.inject.persist.PersistService
import javax.inject.Inject

class ApplicationInitializer @Inject() (val persistService: PersistService) {

  start

  def start: Unit = {
    persistService.start
  }

  def stop: Unit = {
    persistService.stop
  }
}
