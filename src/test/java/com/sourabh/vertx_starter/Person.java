package com.sourabh.vertx_starter;

public class Person {

  private Integer id;
  private  String name;
  private  boolean lovesVertex;

  public Person() {
    //Default constructor for Jackson
  }

  public Person(Integer id, String name, boolean lovesVertex) {
    this.id = id;
    this.name = name;
    this.lovesVertex = lovesVertex;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isLovesVertex() {
    return lovesVertex;
  }

  public void setLovesVertex(boolean lovesVertex) {
    this.lovesVertex = lovesVertex;
  }
}
