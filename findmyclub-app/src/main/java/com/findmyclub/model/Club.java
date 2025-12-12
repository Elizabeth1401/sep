package com.findmyclub.model;

import jakarta.persistence.*;

@Entity @Table(name = "club")
public class Club
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;

  @Column(name = "name",nullable = false)
  private String name;
  @Column(name = "location")
  private String location;

  public Club() {}

  public Club(String name, String location){
    this.name = name;
    this.location = location;
  }
  // Getters and Setters
  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
   this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }
}
