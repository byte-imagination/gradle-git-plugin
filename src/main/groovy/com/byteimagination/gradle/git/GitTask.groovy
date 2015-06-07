package com.byteimagination.gradle.git
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.StopActionException

class GitTask extends DefaultTask {

  def add(String path) {
    project.exec {
      commandLine = ['git']
      args = ['add', path]
    }
  }

  def commit(String message) {
    project.exec {
      commandLine = ['git']
      args = ['commit', '-a', '-m', message]
    }
  }

  def flowStartRelease(String version) {
    project.exec {
      commandLine = ['git']
      args = ['flow', 'release', 'start', "${version}"]
    }
  }

  def flowStopRelease(String version) {
    project.exec {
      commandLine = ['git']
      args = ['flow', 'release', 'finish', "${version}", '-m', "Merge branch 'release/${version}'"]
    }
  }

  def push(String branch) {
    project.exec {
      commandLine = ['git']
      args = ['push', 'origin', branch]
    }
  }

  def pushAll() {
    project.exec {
      commandLine = ['git']
      args = ['push', '--all']
    }
  }

  def pushTags() {
    project.exec {
      commandLine = ['git']
      args = ['push', '--tags']
    }
  }

  def currentBranch() {
    ByteArrayOutputStream stdOut = new ByteArrayOutputStream()
    project.exec {
      commandLine = ['git']
      args = ['branch']
      standardOutput = stdOut
    }
    String[] branches = stdOut.toString().trim().split('\n')
    for (String branch : branches)
      if (branch.startsWith('*'))
        return branch.split('\\* ')[1]
    throw new StopActionException("No branch found.")
  }
}
