package net.coleh.autoautolanguageplugin;

import android.annotation.Nullable;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;

public class NotificationShowerHelper {
    private static final NotificationGroup NOTIFICATION_GROUP =
            new NotificationGroup("Autoauto Notifications", NotificationDisplayType.BALLOON, true);

    public static void showNotif(String title, String subtitle, String content, Project project) {
        NOTIFICATION_GROUP.createNotification(title, subtitle, content).notify(project);
    }
}
