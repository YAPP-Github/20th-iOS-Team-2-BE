package com.yapp.core.persistance.family.persistence.entity;

import static java.util.Objects.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yapp.core.persistance.BaseEntity;
import com.yapp.core.persistance.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "FAMILY")
public class Family extends BaseEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;
	private String motto;
	private String imageLink;

	@OneToOne(fetch = LAZY)
	private User owner;

	@Embedded
	@Getter(PRIVATE)
	private FamilyMembers familyMembers = new FamilyMembers();

	@Builder
	public Family(User user, String name, String motto) {
		userAsFamily(user);
		this.name = name;
		this.motto = motto;
	}

	private void userAsFamily(User user) {
		this.owner = user;
	}

	public void addUser(User user) {
		user.setFamily(this);
		familyMembers.add(user);
	}

	public int getMemberCount() {
		return familyMembers.getCount();
	}

	public void addMember(User member) {
		this.familyMembers.add(member);
	}

	public void update(String imageLink, String familyName, String familyMotto) {
		if (needChange(imageLink)) {
			this.imageLink = imageLink;
		}
		if (needChange(familyName)) {
			this.name = familyName;
		}
		if (needChange(familyMotto)) {
			this.motto = familyMotto;
		}

	}

	private boolean needChange(String target) {
		return !isNull(target) && !target.isBlank();
	}

	public Set<User> getMembers() {
		return new HashSet<>(familyMembers.getMembers());
	}

	@Embeddable
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor(access = PRIVATE)
	public static class FamilyMembers {
		@OneToMany(mappedBy = "family")
		private Set<User> members = new HashSet<>();

		void add(User user) {
			members.add(user);
		}

		int getCount() {
			return members.size();
		}
	}
}
