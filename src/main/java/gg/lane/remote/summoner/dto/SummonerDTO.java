package gg.lane.remote.summoner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SummonerDTO implements Serializable {
  private Long id;
  private String name;
  private Integer profileIconId;
  private Long revisionDate;
  private Long summonerLevel;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getProfileIconId() {
    return profileIconId;
  }

  public void setProfileIconId(Integer profileIconId) {
    this.profileIconId = profileIconId;
  }

  public Long getRevisionDate() {
    return revisionDate;
  }

  public void setRevisionDate(Long revisionDate) {
    this.revisionDate = revisionDate;
  }

  public Long getSummonerLevel() {
    return summonerLevel;
  }

  public void setSummonerLevel(Long summonerLevel) {
    this.summonerLevel = summonerLevel;
  }
}
