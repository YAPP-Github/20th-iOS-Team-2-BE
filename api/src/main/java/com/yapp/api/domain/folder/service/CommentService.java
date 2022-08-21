package com.yapp.api.domain.folder.service;

import com.yapp.api.domain.file.persistence.query.handler.FileQueryHandler;
import com.yapp.api.domain.folder.controller.model.AlbumResponse;
import com.yapp.api.domain.folder.persistence.command.handler.CommentCommandHandler;
import com.yapp.api.domain.folder.persistence.query.handler.CommentQueryHandler;
import com.yapp.api.global.error.exception.ApiException;
import com.yapp.realtime.entity.family.persistence.entity.Family;
import com.yapp.realtime.entity.file.persistence.entity.File;
import com.yapp.realtime.entity.folder.comment.entity.Comment;
import com.yapp.realtime.entity.user.entity.User;
import com.yapp.realtime.error.exception.ErrorCode;
import com.yapp.realtime.error.exception.ExceptionThrowableLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.yapp.realtime.error.exception.ErrorCode.COMMENT_NOT_FOUND;
import static com.yapp.realtime.error.exception.ErrorCode.FILE_NOT_FOUND;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService implements ExceptionThrowableLayer {

    private final FileQueryHandler fileQueryHandler;
    private final CommentCommandHandler commentCommandHandler;
    private final CommentQueryHandler commentQueryHandler;

    public List<AlbumResponse.CommentElement> getList(User user, Family family, Long fileId) {
        File file = fileQueryHandler.findOne(family, fileId)
                .orElseThrow(() -> new ApiException(FILE_NOT_FOUND, packageName(this.getClass())));

        return commentQueryHandler.findAll(family, file)
                .stream()
                .map(comment -> {
                    User commentOwner = comment.getUser();
                    String nickname = commentOwner.getNicknameForUser(user);

                    return new AlbumResponse.CommentElement(comment.getId(), commentOwner.getId(), commentOwner.getProfileInfo()
                            .getImageLink(), nickname, commentOwner.getRoleInFamily(), comment.getCreatedAt()
                            .format(ISO_DATE_TIME), comment.getContent());
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public Comment create(User user, Family family, Long fileId, String content) {
        File file = fileQueryHandler.findOne(family, fileId)
                .orElseThrow(() -> new ApiException(ErrorCode.FILE_NOT_FOUND, packageName(this.getClass())));

        return commentCommandHandler.save(Comment.builder()
                .user(user)
                .family(family)
                .file(file)
                .content(content)
                .build());
    }

    @Transactional
    public void modify(Long commentId, String content) {
        Comment comment = commentQueryHandler.findOne(commentId)
                .orElseThrow(() -> new ApiException(COMMENT_NOT_FOUND, packageName(this.getClass())));
        comment.modify(content);
    }

    @Transactional
    public void remove(Long commentId) {
        Comment comment = commentQueryHandler.findOne(commentId)
                .orElseThrow(() -> new ApiException(COMMENT_NOT_FOUND, packageName(this.getClass())));

        commentCommandHandler.remove(comment);
    }
}
