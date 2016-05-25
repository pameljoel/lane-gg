package gg.lane.model

class GameVersion(version: String) {
  var currentVersion = version

  fun update(newVersion: String) {
    currentVersion = newVersion
  }
}
