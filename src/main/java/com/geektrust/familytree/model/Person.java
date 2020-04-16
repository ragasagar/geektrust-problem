package com.geektrust.familytree.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.geektrust.familytree.constant.FamilyConstant;
import com.geektrust.familytree.exception.FamilyException;

/**
 * The Class Person model class that represent the individual person.
 * 
 * @author Sagar Poudel
 *
 */
public class Person {

	/** The name. */
	private String name;

	/** The gender. */
	private Gender gender;

	/** The mother. */
	private Person mother;

	/** The spouse. */
	private Person spouse;

	/** The children. */
	private Set<Person> children;

	/**
	 * Instantiates a new person.
	 *
	 * @param name   the name
	 * @param gender the gender
	 */
	public Person(String name, Gender gender) {
		super();
		this.name = name;
		this.gender = gender;
		this.children = new LinkedHashSet<>();
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(final Gender gender) {
		this.gender = gender;
	}

	/**
	 * Gets the mother.
	 *
	 * @return the mother
	 */
	public Person getMother() {
		return mother;
	}

	/**
	 * Sets the mother.
	 *
	 * @param mother the new mother
	 */
	public void setMother(final Person mother) {
		this.mother = mother;
	}

	/**
	 * Gets the spouse.
	 *
	 * @return the spouse
	 */
	public Person getSpouse() {
		return spouse;
	}

	/**
	 * Sets the spouse.
	 *
	 * @param spouse the new spouse
	 */
	public void setSpouse(final Person spouse) {
		this.spouse = spouse;
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public Set<Person> getChildren() {
		if (Gender.MALE.equals(this.gender) && null != this.spouse) {
			return this.spouse.getChildren();
		} else
			return this.children;

	}

	/**
	 * Adds the child.
	 *
	 * @param name   the name
	 * @param gender the gender
	 * @return the person
	 * @throws FamilyException the family exception
	 */
	public Person addChild(final String name, final Gender gender) throws FamilyException {
		if (!Gender.FEMALE.equals(this.gender)) {
			throw new FamilyException(FamilyConstant.CHILD_ADDITION_FAILED_EXCEPTION_MSG);
		}
		final Person child = new Person(name, gender);
		this.children.add(child);
		child.mother = this;
		return child;
	}

	/**
	 * Adds the spouse.
	 *
	 * @param spouseName   the spouse name
	 * @param spouseGender the spouse gender
	 * @return the person
	 * @throws FamilyException the family exception
	 */
	public Person addSpouse(final String spouseName, final Gender spouseGender) throws FamilyException {
		if (null != this.spouse) {
			throw new FamilyException(FamilyConstant.SPOUSE_ALREADY_EXIST_EXCEPTION_MSG);
		}
		if (this.gender.equals(spouseGender)) {
			throw new FamilyException(FamilyConstant.SPOUSE_GENDER_SAME_EXCEPTION_MSG);
		}

		final Person spousePerson = new Person(spouseName, spouseGender);
		this.spouse = spousePerson;
		spousePerson.spouse = this;
		return spousePerson;
	}

	/**
	 * Gets the sons.
	 *
	 * @return the sons
	 */
	public Set<Person> getSons() {
		return this.getChildren().stream().filter(x -> Gender.MALE.equals(x.getGender())).collect(Collectors.toSet());
	}

	/**
	 * Gets the daughters.
	 *
	 * @return the daughters
	 */
	public Set<Person> getDaughters() {
		return this.getChildren().stream().filter(x -> Gender.FEMALE.equals(x.getGender())).collect(Collectors.toSet());
	}

	/**
	 * Gets the sibilings.
	 *
	 * @return the sibilings
	 */
	public Set<Person> getSibilings() {
		Set<Person> sibilings = new LinkedHashSet<>();
		if (null != this.mother) {
			sibilings = this.mother.getChildren().stream().filter(x -> !this.name.equals(x.getName()))
					.collect(Collectors.toSet());
		}
		return sibilings;
	}

	/**
	 * Gets the brothers.
	 *
	 * @return the brothers
	 */
	public Set<Person> getBrothers() {
		final Set<Person> sibilings = this.getSibilings();
		Set<Person> brothers = null;
		if (null != sibilings) {
			brothers = sibilings.stream().filter(x -> Gender.MALE.equals(x.getGender())).collect(Collectors.toSet());
		}
		return brothers;
	}

	/**
	 * Gets the sisters.
	 *
	 * @return the sisters
	 */
	public Set<Person> getSisters() {
		final Set<Person> sibilings = this.getSibilings();
		Set<Person> sisters = null;
		if (null != sibilings) {
			sisters = sibilings.stream().filter(x -> Gender.FEMALE.equals(x.getGender())).collect(Collectors.toSet());
		}
		return sisters;
	}

	/**
	 * Gets the paternal uncle.
	 *
	 * @return the paternal uncle
	 */
	public Set<Person> getPaternalUncle() {
		if (null != this.mother) {
			return this.mother.spouse.getBrothers();
		}
		return Collections.emptySet();
	}

	/**
	 * Gets the paternal aunt.
	 *
	 * @return the paternal aunt
	 */
	public Set<Person> getPaternalAunt() {
		if (null != this.mother) {
			return this.mother.spouse.getSisters();
		}
		return Collections.emptySet();
	}

	/**
	 * Gets the maternal aunt.
	 *
	 * @return the maternal aunt
	 */
	public Set<Person> getMaternalAunt() {
		if (null != this.mother) {
			return this.mother.getSisters();
		}
		return Collections.emptySet();
	}

	/**
	 * Gets the maternal uncle.
	 *
	 * @return the maternal uncle
	 */
	public Set<Person> getMaternalUncle() {
		return this.mother.getBrothers();
	}

	/**
	 * Gets the sister in law.
	 *
	 * @return the sister in law
	 */
	public Set<Person> getSisterInLaw() {
		Set<Person> sisterInLaw = new LinkedHashSet<>();
		if (null != this.spouse) {
			sisterInLaw.addAll(this.spouse.getSisters());
		}
		final Set<Person> wivesOfBrothers = this.getBrothers().stream().map(x -> x.getSpouse()).filter(Objects::nonNull)
				.collect(Collectors.toSet());
		sisterInLaw.addAll(wivesOfBrothers);
		return sisterInLaw;
	}

	/**
	 * Gets the brother in law.
	 *
	 * @return the brother in law
	 */
	public Set<Person> getBrotherInLaw() {
		Set<Person> brotherInLaw = new LinkedHashSet<>();
		final Set<Person> husbandOfSisters = this.getSisters().stream().map(x -> x.getSpouse()).filter(Objects::nonNull)
				.collect(Collectors.toSet());
		brotherInLaw.addAll(husbandOfSisters);
		if (null != this.spouse) {
			brotherInLaw.addAll(this.spouse.getBrothers());
		}
		return brotherInLaw;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
