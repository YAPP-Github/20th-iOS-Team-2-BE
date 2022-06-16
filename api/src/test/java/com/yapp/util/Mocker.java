package com.yapp.util;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yapp.api.domain.album.element.folder.persistence.repository.AlbumRepository;
import com.yapp.api.domain.family.persistence.repository.FamilyRepository;
import com.yapp.api.domain.file.persistence.repository.FileRepository;

@ExtendWith(MockitoExtension.class)
public class Mocker {

	@Mock
	protected FamilyRepository familyRepository;

	@Mock
	protected AlbumRepository albumRepository;

	@Mock
	protected FileRepository fileRepository;
}
