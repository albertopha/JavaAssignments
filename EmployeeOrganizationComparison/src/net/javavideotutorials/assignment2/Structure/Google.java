package net.javavideotutorials.assignment2.Structure;

public class Google implements Organization {
  private String nameOfOrganization;
  private Integer numEmployees;

  public Google(String nameOfOrganization) {
    this.nameOfOrganization = nameOfOrganization;
  }

  @Override
  public Integer getNumberOfEmployees() {
    return numEmployees;
  }

  @Override
  public String getNameOfOrganization() {
    return nameOfOrganization;
  }

  @Override
  public void setNumberOfEmployees(Integer numEmployees) {
    this.numEmployees = numEmployees;
  }

  @Override
  public void setNameOfOrganization(String nameOfOrganization) {
    this.nameOfOrganization = nameOfOrganization;
  }
}
