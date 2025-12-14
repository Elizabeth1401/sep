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
  @Column(name = "category",nullable = false)
  private String category;
  @Column(name = "description",nullable = false)
  private String description;

  public Club() {}

  public Club(String name, String location, String category, String description){
    this.name = name;
    this.location = location;
    this.category = category;
    this.description = description;
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

  public String getCategory()
  {
    return category;
  }

  public void setCategory(String category)
  {
    this.category = category;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }
}
