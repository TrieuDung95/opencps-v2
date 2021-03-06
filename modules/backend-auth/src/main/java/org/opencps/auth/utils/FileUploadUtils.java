package org.opencps.auth.utils;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class FileUploadUtils {

	public static FileEntry uploadFile(long userId, long companyId, long groupId, InputStream inputStream,
			String fileName, String fileType, long fileSize, String destination, String desc,
			ServiceContext serviceContext) throws Exception {
		FileEntry fileEntry = null;

		if (inputStream != null && fileSize > 0 && Validator.isNotNull(fileName)) {

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(new Date());

			destination += calendar.get(Calendar.YEAR) + StringPool.SLASH;
			destination += calendar.get(Calendar.MONTH) + StringPool.SLASH;
			destination += calendar.get(Calendar.DAY_OF_MONTH);

			DLFolder dlFolder = DLFolderUtil.getTargetFolder(userId, groupId, groupId, false, 0, destination,
					"Comment attactment", false, serviceContext);

			User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

			PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
			PermissionThreadLocal.setPermissionChecker(checker);
			
			fileEntry = DLAppServiceUtil.addFileEntry(groupId, dlFolder.getFolderId(), fileName, fileType,
					System.currentTimeMillis() + StringPool.DASH + fileName , desc, StringPool.BLANK, inputStream,
					fileSize, serviceContext);

		}

		return fileEntry;
	}

}
