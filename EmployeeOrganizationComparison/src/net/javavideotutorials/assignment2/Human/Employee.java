package net.javavideotutorials.assignment2.Human;

import net.javavideotutorials.assignment2.Structure.Organization;
import java.util.Date;

public class Employee extends Person {
  private String name;
  private String sex;
  private String jobTitle;
  private Organization organization;
  private Date birthdate;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getSex() {
    return sex;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setSex(String sex) {
    this.sex = sex;
  }

  @Override
  public void setBirthday(Date birthdate) {
    this.birthdate = birthdate;
  }

  @Override
  public Date getBirthday() {
    return birthdate;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  public Organization getOrganization() {
    return organization;
  }

  @Override
  public String toString() {
    return "Name: " + name +
            ", Sex: " + sex +
            "\nJob Title: " + jobTitle+
            ", Organization: " + organization.getNameOfOrganization();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this == obj) {
      return true;
    }

    if (getClass() != obj.getClass()) {
      return false;
    }

    final Employee other = (Employee) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }

    if (sex == null) {
      if (other.sex != null) {
        return false;
      }
    } else if (!sex.equals(other.sex)) {
      return false;
    }

    if (jobTitle == null) {
      if (other.jobTitle != null) {
        return false;
      }
    } else if (!jobTitle.equals(other.jobTitle)) {
      return false;
    }

    if (organization == null) {
      if (other.organization != null) {
        return false;
      }
    } else if (!organization.getNameOfOrganization().equals(other.organization.getNameOfOrganization())) {
      return false;
    }

    return true;
  }
}
