package com.yapp.api.domain.family.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yapp.api.domain.common.BaseEntity;
import com.yapp.api.domain.family.persistence.entity.element.FamilyInfo;
import com.yapp.api.domain.user.persistence.entity.User;

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

	@Embedded
	@Getter(PRIVATE)
	private FamilyInfos familyInfos = new FamilyInfos();

	@OneToOne(fetch = LAZY)
	private User owner;

	@Embedded
	@Getter(PRIVATE)
	private Members members = new Members();

	@Builder
	public Family(User user, String name, String motto) {
		this.owner = user;
		this.name = name;
		this.motto = motto;
		members.add(user);
	}

	public int getMemberCount() {
		return members.getCount();
	}

	public void addFamilyInfo(FamilyInfo familyInfo) {
		this.familyInfos.add(this, familyInfo);
	}

	@Embeddable
	@Getter
	@NoArgsConstructor(access = PROTECTED)
	@AllArgsConstructor(access = PRIVATE)
	private class Members {
		@OneToMany(mappedBy = "family", fetch = LAZY)
		private Set<User> members = new HashSet<>();

		void add(User user) {
			members.add(user);
		}

		int getCount() {
			return members.size();
		}
	}

	@Embeddable
	@Getter
	@NoArgsConstructor(access = PROTECTED)
	@AllArgsConstructor(access = PRIVATE)
	private class FamilyInfos {
		@OneToMany(mappedBy = "family", fetch = LAZY)
		private Set<FamilyInfo> familyInfos = new HashSet<>();

		public void add(Family family, FamilyInfo familyInfo) {
			familyInfo.setFamily(family);
			familyInfos.add(familyInfo);
		}
	}
}
