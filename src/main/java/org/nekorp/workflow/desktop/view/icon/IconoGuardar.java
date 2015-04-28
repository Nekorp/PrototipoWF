package org.nekorp.workflow.desktop.view.icon;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class IconoGuardar implements javax.swing.Icon {

    /** The width of this icon. */
    private final int width;

    /** The height of this icon. */
    private final int height;
    
    private final Color colorA;
    
    private final Color colorB;

    /** The rendered image. */
    private BufferedImage image;

    /**
     * Creates a new transcoded SVG image.
     * @param width
     * @param height
     * @param colorA
     * @param colorB
     */
    public IconoGuardar(int width, int height, Color colorA, Color colorB) {
        this.width = width;
        this.height = height;
        this.colorA =colorA;
        this.colorB = colorB;
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (image == null) {
            image = new BufferedImage(getIconWidth(), getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            double coef = Math.min((double) width / (double) 107, (double) height / (double) 105);
            
            Graphics2D g2d = image.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.scale(coef, coef);
            paint(g2d);
            g2d.dispose();
        }
        
        g.drawImage(image, x, y, null);
    }

    /**
     * Paints the transcoded SVG image on the specified graphics context.
     * 
     * @param g Graphics context.
     */
    private void paint(Graphics2D g) {
        Shape shape = null;
        
        float origAlpha = 1.0f;
        
        java.util.LinkedList<AffineTransform> transformations = new java.util.LinkedList<AffineTransform>();
        

        // 
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1.0666667f, 0, 0, 1.0666667f, 0, 0));

        // _0

        // _0_0
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1, 0, 0, 1, 0, -0.0288202f));

        // _0_0_0

        // _0_0_0_0
        shape = new Rectangle2D.Double(15.467961311340332, 0.03578838333487511, 58.21004104614258, 28.410541534423828);
        g.setPaint(colorB);
        g.fill(shape);

        // _0_0_0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(15.798216, 0.0039196);
        ((GeneralPath) shape).curveTo(8.356607, 0.00532389, 0.03630279, 1.2360499, 0.0, 11.70168);
        ((GeneralPath) shape).lineTo(0.0267, 97.64121);
        ((GeneralPath) shape).lineTo(87.656044, 97.60061);
        ((GeneralPath) shape).curveTo(97.62634, 97.54641, 99.99294, 91.00561, 99.959404, 86.45897);
        ((GeneralPath) shape).lineTo(100.0, 0.00648);
        ((GeneralPath) shape).lineTo(73.60033, 0.05978);
        ((GeneralPath) shape).lineTo(73.46451, 28.17713);
        ((GeneralPath) shape).lineTo(15.814, 28.29901);
        ((GeneralPath) shape).closePath();

        g.setPaint(colorA);
        g.fill(shape);

        // _0_0_0_2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(16.2945, 51.43401);
        ((GeneralPath) shape).curveTo(16.348, 46.93422, 19.26404, 44.98381, 22.628971, 45.072838);
        ((GeneralPath) shape).lineTo(77.76451, 45.039238);
        ((GeneralPath) shape).lineTo(77.743614, 76.93188);
        ((GeneralPath) shape).curveTo(77.772415, 79.8255, 76.15395, 82.775536, 72.04753, 82.826485);
        ((GeneralPath) shape).lineTo(16.179611, 82.840485);
        ((GeneralPath) shape).closePath();

        g.setPaint(colorB);
        g.fill(shape);

        // _0_0_0_3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(50.207363, 0.00514934);
        ((GeneralPath) shape).lineTo(50.202663, 22.90411);
        ((GeneralPath) shape).lineTo(62.531532, 22.90411);
        ((GeneralPath) shape).lineTo(62.51123, 0.0033836365);
        ((GeneralPath) shape).closePath();

        g.setPaint(colorA);
        g.fill(shape);

        g.setTransform(transformations.poll()); // _0_0_0

        g.setTransform(transformations.poll()); // _0

    }
}

