package com.gildorymrp.gildorymcharactercards;

import java.io.Serializable;

public class CharacterCard
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer age;
  private Gender gender;
  private String description;
  private Race race;

  public CharacterCard(Integer age, Gender gender, String description, Race race)
  {
    this.age = age;
    this.gender = gender;
    this.description = description;
    this.race = race;
  }

  public Integer getAge()
  {
    return this.age;
  }

  public void setAge(Integer age)
  {
    this.age = age;
  }

  public Gender getGender()
  {
    return this.gender;
  }

  public void setGender(Gender gender)
  {
    this.gender = gender;
  }

  public String getDescription()
  {
    return this.description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public Race getRace()
  {
    return this.race;
  }

  public void setRace(Race race)
  {
    this.race = race;
  }
}