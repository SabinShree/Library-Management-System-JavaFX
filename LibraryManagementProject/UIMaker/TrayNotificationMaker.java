package UIMaker;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class TrayNotificationMaker {
    public static void welcomeAdmin() {
        String title = "Welcome sir.";
        String message = "Have a great day! ";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.setAnimationType(AnimationType.SLIDE);
        tray.setMessage(message);
        tray.showAndDismiss(Duration.seconds(5));
    }

    public static void addBooks(String name) {
        String title = "Added Book.";
        String message = "Book :  " + name + " successfully added.";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setNotificationType(NotificationType.NOTICE);
        tray.setAnimationType(AnimationType.POPUP);
        tray.setMessage(message);
        tray.showAndDismiss(Duration.seconds(1));
    }

    public static void addMembers(String name) {
        String title = "Added Members.";
        String message = "Member :  " + name + " successfully registered..";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.setAnimationType(AnimationType.POPUP);
        tray.setMessage(message);
        tray.showAndDismiss(Duration.seconds(1));
    }

}
