package net.betterpvp.osFighter.utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class UtilFile {

    public static  BufferedImage loadImage(String directory, String url, String fileName) throws IOException {
        BufferedImage bImage;

        File dir = new File(directory);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        File image = new File(directory, fileName);
        if (image.exists()) {
            System.out.println(image.getAbsolutePath());
            bImage = ImageIO.read(image);
        } else {
            URL uImage = new URL(url + fileName);

            InputStream is = uImage.openStream();

            OutputStream os = new FileOutputStream(directory + fileName);
            // Files.copy(is, Paths.get(getInstance().getDirectoryData() + "osFighter/"));
            byte[] b = new byte[1024];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
            bImage = ImageIO.read(uImage.openStream());
            is.close();
            os.close();


        }

        return bImage;
    }

}
