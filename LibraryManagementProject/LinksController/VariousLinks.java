package LinksController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class VariousLinks {
    private static VariousLinks links = new VariousLinks();

    public static VariousLinks getVariousLink() {
        return links;
    }

    public void GoTwitter() {
        try {
            String twitterHyperLink = "www.twitter.com";
            try {
                Desktop.getDesktop().browse(new URI(twitterHyperLink));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GoFacebook() {
        try {
            String facebookHyperLink = "www.facebook.com";
            try {
                Desktop.getDesktop().browse(new URI(facebookHyperLink));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GoGooglePlus() throws Exception {
        try {
            String facebookHyperLink = "www.google.com";
            Desktop.getDesktop().browse(new URI(facebookHyperLink));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
