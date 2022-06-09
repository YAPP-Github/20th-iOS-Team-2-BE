package com.yapp.core.constant;

public class ApiConstant {
	public static final String KIND = "kind";

	public static final String USER_ID = "userId";
	public static final String ALBUM_ID = "albumId";
	public static final String FILE_ID = "fileId";
	public static final String COMMENT_ID = "commentId";
	public static final String FAMILY_ID = "familyId";
	public static final String MESSAGE_ID = "messageId";

	public static final String _ALBUM = "/album";
	public static final String _ALBUM_PHOTOS = "/album/photos";
	public static final String _ALBUM_RECORDINGS = "/album/recordings";
	public static final String _ALBUM_FAVOURITE = "/album/favourite";
	public static final String _ALBUM_RESOURCE = "/album/{albumId}";
	public static final String _ALBUM_COMMENTS = "/album/{fileId}/comments";
	public static final String _ALBUM_COMMENT_RESOURCE = "/album/comments/{commentId}";
	public static final String _ALBUM_DETAILS_RESOURCE = "/album/details/{albumId}";
	public static final String _ALBUM_DETAILS = "/album/details";

	public static final String _FAMILY = "/family";
	public static final String _FAMILY_RESOURCE = "/family/{familyId}";
	public static final String _FAMILY_GREETING_MESSAGE = "/family/greeting/message";
	public static final String _FAMILY_GREETING_EMOJI = "/family/greeting/emoji";
	public static final String _FAMILY_HOME = "/family/home";
	public static final String _FAMILY_NOTIFICATIONS = "/family/notifications";

	public static final String _FILES = "/files";
	public static final String _FILES_RESOURCE = "/files/{fileId}";

	public static final String _USER = "/user";
	public static final String _USER_HISTORY_RESOURCE = "/user/history/{messageId}";
	public static final String _USER_HISTORY = "/user/{userId}/history";

}
