package gg.lane.model

enum class Team(val code: Long) {
  BLUE(100), PURPLE(200);

  companion object{
    fun fromCode(code: Long): Team {
      if (code == BLUE.code){
        return BLUE
      }
      return PURPLE
    }
  }
}
