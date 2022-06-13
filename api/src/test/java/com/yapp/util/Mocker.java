package com.yapp.util;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yapp.api.domain.family.persistence.repository.FamilyRepository;

@ExtendWith(MockitoExtension.class)
public class Mocker {

	@Mock
	protected FamilyRepository familyRepository;

}
