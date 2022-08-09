package com.yapp.api.domain.file.service;

import static com.yapp.core.error.exception.ErrorCode.*;
import static java.util.Objects.*;
import static java.util.stream.Collectors.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class CloudService {
	private static final String DOT = ".";
	private static final String EXTENSION_DELIMITER = "\\.";
	private static final String BUCKET_FOLDER_PATH = "sofa/";
	private static final String BUCKET_ENV_PATH = "${aws.s3.bucket}";

	private final AmazonS3Client s3Client;
	private final String s3BucketName;
	private final KeyGenerator keyGenerator;

	public CloudService(AmazonS3Client s3Client,
						@Value(BUCKET_ENV_PATH) String s3BucketName,
						KeyGenerator keyGenerator) {
		this.s3Client = s3Client;
		this.s3BucketName = s3BucketName;
		this.keyGenerator = keyGenerator;
	}

	public List<String> upload(List<MultipartFile> fileList) {
		return fileList.stream()
					   .map(file -> upload(getFileKey(file), file))
					   .collect(toUnmodifiableList());
	}

	private String getFileKey(MultipartFile file) {
		return BUCKET_FOLDER_PATH + keyGenerator.generate() + DOT + getExtension(file);
	}

	private String getExtension(MultipartFile file) {
		return Arrays.stream(requireNonNull(file.getOriginalFilename()).split(EXTENSION_DELIMITER))
					 .reduce((a, b) -> b)
					 .orElseThrow(() -> new BaseBusinessException(FILE_IO_ERROR, new IllegalAccessException()));
	}

	private String upload(String fileKey, MultipartFile file) {
		s3Client.putObject(new PutObjectRequest(s3BucketName,
												fileKey,
												getBytesArrayInputStream(file),
												getObjectMetadata(file)));
		return s3Client.getUrl(s3BucketName, fileKey)
					   .toString();
	}

	private InputStream getBytesArrayInputStream(MultipartFile file) {
		try {
			return new ByteArrayInputStream(file.getBytes());
		} catch (IOException e) {
			throw new BaseBusinessException(FILE_IO_ERROR, e);
		}
	}

	private ObjectMetadata getObjectMetadata(MultipartFile file) {
		ObjectMetadata fileMetadata = new ObjectMetadata();
		fileMetadata.setContentLength(file.getSize());
		fileMetadata.setContentType(file.getContentType());
		return fileMetadata;
	}
}
