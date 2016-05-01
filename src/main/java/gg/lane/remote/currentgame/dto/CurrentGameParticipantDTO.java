package gg.lane.remote.currentgame.dto;

import java.util.List;

public class CurrentGameParticipantDTO{
  private boolean bot;
  private long championId;
  private List<MasteryDTO> masteries;
  private long profileIconId;
  private List<RuneDTO> runes;
  private long spell1Id;
  private long spell2Id;
  private long summonerId;
  private String summonerName;
  private long teamId;

  public boolean isBot() {
    return bot;
  }

  public void setBot(boolean bot) {
    this.bot = bot;
  }

  public long getChampionId() {
    return championId;
  }

  public void setChampionId(long championId) {
    this.championId = championId;
  }

  public List<MasteryDTO> getMasteries() {
    return masteries;
  }

  public void setMasteries(List<MasteryDTO> masteries) {
    this.masteries = masteries;
  }

  public long getProfileIconId() {
    return profileIconId;
  }

  public void setProfileIconId(long profileIconId) {
    this.profileIconId = profileIconId;
  }

  public List<RuneDTO> getRunes() {
    return runes;
  }

  public void setRunes(List<RuneDTO> runes) {
    this.runes = runes;
  }

  public long getSpell1Id() {
    return spell1Id;
  }

  public void setSpell1Id(long spell1Id) {
    this.spell1Id = spell1Id;
  }

  public long getSpell2Id() {
    return spell2Id;
  }

  public void setSpell2Id(long spell2Id) {
    this.spell2Id = spell2Id;
  }

  public long getSummonerId() {
    return summonerId;
  }

  public void setSummonerId(long summonerId) {
    this.summonerId = summonerId;
  }

  public String getSummonerName() {
    return summonerName;
  }

  public void setSummonerName(String summonerName) {
    this.summonerName = summonerName;
  }

  public long getTeamId() {
    return teamId;
  }

  public void setTeamId(long teamId) {
    this.teamId = teamId;
  }
}
