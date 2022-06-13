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

	@OneToOne(fetch = LAZY)
	private User owner;

	@Embedded
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

	@Embeddable
	@Getter
	@NoArgsConstructor(access = PROTECTED)
	@AllArgsConstructor(access = PRIVATE)
	private class Members {
		@OneToMany(mappedBy = "family", fetch = LAZY)
		private Set<User> members = new HashSet<>();

		public void add(User user) {
			members.add(user);
		}

		public int getCount() {
			return members.size();
		}
	}
}
