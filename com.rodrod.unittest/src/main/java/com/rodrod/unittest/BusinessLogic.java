package com.rodrod.unittest;

import java.util.Optional;

public class BusinessLogic {
  private final Persistence persistence;

  public BusinessLogic(Persistence persistence) {
    this.persistence = persistence;
  }

  public Pojo createPojo(Pojo pojo) {

    if (pojo.getName() == null || pojo.getName().trim() == "") {
      throw new IllegalArgumentException("Name is required");
    }

    Optional<Pojo> existingPojo = persistence.getPojoByName(pojo.getName());

    if (existingPojo.isPresent()) {
      return existingPojo.get();
    } else {
      persistence.doSomething();
    }

    return persistence.createPojo(pojo);
  }
}

