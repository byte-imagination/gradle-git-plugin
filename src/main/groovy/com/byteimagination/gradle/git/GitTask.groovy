package com.byteimagination.gradle.git
import org.gradle.api.DefaultTask

class GitTask extends DefaultTask {

  def add(String path) {
    project.exec {
      commandLine = ['git']
      args = ['add', path]
    }
  }
}
