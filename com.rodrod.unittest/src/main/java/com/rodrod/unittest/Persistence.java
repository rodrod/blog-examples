package com.rodrod.unittest;

import java.util.Optional;

public class Persistence {

  public Optional<Pojo> getPojoByName(String name) {
    Pojo pojo = new Pojo();
    pojo.setName(name);
    return Optional.of(pojo);
  }

  public Pojo createPojo(Pojo pojo) {
    pojo.setId(0);
    return pojo;
  }

  public boolean doSomething() {
    return true;
  }

}
