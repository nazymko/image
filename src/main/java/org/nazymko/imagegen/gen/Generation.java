package org.nazymko.imagegen.gen;

import org.nazymko.imagegen.model.Job;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Generation {
    public static final int COMMON = 200;
    public static final int WIDTH = COMMON * 3;
    public static final int HEIGHT = COMMON * 2;

    public static final double TITLE_SIZE = (COMMON / 60) * 13;

    public BufferedImage draw(Job job) throws IOException, FontFormatException {

        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR_PRE);

        Font titleFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("C:\\Users\\nazymko\\IdeaProjects\\image\\src\\main\\resources\\static\\Capture_it.ttf"));
        Font commonFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("C:\\Users\\nazymko\\IdeaProjects\\image\\src\\main\\resources\\static\\Lato-Medium.ttf"));
        System.out.println("font = " + titleFont);
        Graphics2D graphics = (Graphics2D) img.getGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);


        graphics.setColor(Color.BLACK);

        int startX = WIDTH / 10;
        int startY = (int) (HEIGHT / 2 + graphics.getFontMetrics().getHeight() * 1.5f);


        double padding = 0.1;
        graphics.setFont(titleFont.deriveFont((float) TITLE_SIZE));
        int textWidth = graphics.getFontMetrics().stringWidth(job.getTitle());
        double width = WIDTH * (1 - (2 * padding));
        if (width < textWidth) {
            System.err.println("We should truncate a name");
            List<String> data = splitIt(job.getTitle(), graphics.getFontMetrics(), width);

            int height = (int) (data.size() * graphics.getFontMetrics().getHeight() + ((data.size() - 1) * graphics.getFontMetrics().getHeight()) * 1.1);

            System.out.println("height = " + height);
            startY = (HEIGHT - height / 2) / 2;
            System.out.println("startY = " + startY);
            for (String datum : data) {
                graphics.drawString(datum, (float) (WIDTH * padding), startY);
                startY += graphics.getFontMetrics().getHeight() * 1.1;
                System.out.println("startY = " + startY);
            }
        } else {
            graphics.drawString(job.getTitle(), (float) (WIDTH * padding), startY);
        }

        
        ImageIO.write(img, "png", new File("jobboard.png"));

        return img;
    }

    private List<String> splitIt(String title, FontMetrics fontMetrics, double width) {

        StringBuilder bldr = new StringBuilder();
        ArrayList<String> lines = new ArrayList<>();

        String[] split = title.split(" ");
        for (String s : split) {
            if ((fontMetrics.stringWidth(s) + " ").length() > width) {
                if (!bldr.toString().isEmpty()) {
                    lines.add(bldr.toString());
                    bldr = new StringBuilder();
                }
                lines.add(s);
                continue;
            }

            if (fontMetrics.stringWidth(bldr.toString() + " " + s) > width) {
                lines.add(bldr.toString());
                bldr = new StringBuilder(s).append(" ");
                continue;
            }

            bldr.append(s).append(" ");
        }

        List<String> collect = lines.stream().filter(s -> {
            return !s.isEmpty();
        }).collect(Collectors.toList());

        System.out.println("width = " + width);
        for (String s : collect) {
            System.out.println("s - > '" + s + " ' , width: " + fontMetrics.stringWidth(s));
        }

        return collect;
    }


}
