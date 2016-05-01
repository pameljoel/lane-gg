package gg.lane.remote.staticdata.dto;

public class ChampionDTO {
  private Long id;
  private String title;
  private String name;
  private ImageDTO image;
  private String key;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ImageDTO getImage() {
    return image;
  }

  public void setImage(ImageDTO image) {
    this.image = image;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
