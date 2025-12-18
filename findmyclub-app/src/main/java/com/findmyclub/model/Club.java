package com.findmyclub.model;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity @Table(name = "club")
public class Club
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;

  @Column(name = "name",nullable = false, length = 100)
  private String name;
  @Column(name = "location",nullable = false, length = 150)
  private String location;
  @Column(name = "description",nullable = false, length = 1000)
  private String description;
  @Column(name = "category",nullable = false, length = 255)
  private String category;

  public Club() {}

  public Club(String name, String location, String description,String category){
    this.name = name;
    this.location = location;
    this.description = description;
    this.category = category;
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

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getCategory()
  {
    return category;
  }

  public void setCategory(String category)
  {
    this.category = category;
  }
}
