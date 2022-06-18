package com.yapp.util;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yapp.api.domain.album.element.comment.persistence.repository.CommentRepository;
import com.yapp.api.domain.album.element.folder.persistence.repository.AlbumRepository;
import com.yapp.api.domain.family.persistence.repository.FamilyRepository;
import com.yapp.api.domain.file.persistence.repository.FileRepository;

@ExtendWith(MockitoExtension.class)
public class Mocker {
	protected static final int ONE = 1;
	protected static final Long ONE_L = 1L;
	protected static final Long THREE_L = 3L;
	protected static final int NEVER = 0;
	protected static final int SIZE_FOUR = 4;
	protected static final int SIZE_THREE = 3;

	@Mock
	protected FamilyRepository familyRepository;

	@Mock
	protected AlbumRepository albumRepository;

	@Mock
	protected FileRepository fileRepository;

	@Mock
	protected CommentRepository commentRepository;
}
