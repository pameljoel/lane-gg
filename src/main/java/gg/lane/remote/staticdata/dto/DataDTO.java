package gg.lane.remote.staticdata.dto;

import java.util.Map;

public class DataDTO {
  private Map<String, ChampionDTO> data;

  public Map<String, ChampionDTO> getData() {
    return data;
  }

  public void setData(Map<String, ChampionDTO> data) {
    this.data = data;
  }
}
