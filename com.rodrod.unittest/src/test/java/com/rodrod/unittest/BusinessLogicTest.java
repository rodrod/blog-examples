package com.rodrod.unittest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusinessLogicTest {

  private BusinessLogic businessLogic;
  private Pojo pojo;
  private String name = "TestName";

  @Mock
  private Persistence mockPersistence;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setup() {
    businessLogic = new BusinessLogic(mockPersistence);
    pojo = new Pojo();
    pojo.setName(name);
  }

  @Test
  public void testCreatePojoWithValidNameShouldReturnWithAnId() {
    int id = 123;
    Pojo createdPojo = new Pojo();
    createdPojo.setId(id);
    createdPojo.setName(pojo.getName());

    when(mockPersistence.getPojoByName(name)).thenReturn(Optional.empty());
    when(mockPersistence.createPojo(pojo)).thenReturn(createdPojo);

    Pojo returnedPojo = businessLogic.createPojo(pojo);

    assertEquals(name, returnedPojo.getName());
    assertEquals(id, returnedPojo.getId().intValue());
    verify(mockPersistence).doSomething();
  }

  @Test
  public void testCreatePojoWithEmptyNameShouldThrowIllegalArgumentException() {
    pojo.setName("");

    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Name is required");

    businessLogic.createPojo(pojo);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreatePojoWithEmptyNullShouldThrowIllegalArgumentException() {
    pojo.setName(null);

    businessLogic.createPojo(pojo);
  }

  @Test
  public void testCreatePojoWithExistingNameShouldRetrunTheExisting() {
    Pojo existingPojo = new Pojo();

    when(mockPersistence.getPojoByName(name)).thenReturn(Optional.of(existingPojo));

    Pojo createdPojo = businessLogic.createPojo(pojo);

    assertEquals(existingPojo, createdPojo);
    verify(mockPersistence, never()).doSomething();
  }

}
