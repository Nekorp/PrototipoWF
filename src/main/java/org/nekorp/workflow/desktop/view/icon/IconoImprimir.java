package org.nekorp.workflow.desktop.view.icon;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class IconoImprimir implements javax.swing.Icon {

    /** The width of this icon. */
    private int width;

    /** The height of this icon. */
    private int height;
    
    private Color colorA;
    
    private Color colorB;


    /** The rendered image. */
    private BufferedImage image;

    /**
     * Creates a new transcoded SVG image.
     */
    public IconoImprimir(int width, int height, Color colorA, Color colorB) {
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
            double coef = Math.min((double) width / (double) 107, (double) height / (double) 94);
            
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
        g.transform(new AffineTransform(0.24421139f, 0, 0, 0.24421139f, -41.683117f, -86.026115f));

        // _0_0_0

        // _0_0_0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(244.2857, 468.79077);
        ((GeneralPath) shape).lineTo(205.7143, 468.07648);
        ((GeneralPath) shape).curveTo(183.9107, 468.2928, 169.95209, 486.3537, 170.7143, 508.79077);
        ((GeneralPath) shape).lineTo(170.7143, 665.9769);
        ((GeneralPath) shape).lineTo(244.58157, 665.93365);
        ((GeneralPath) shape).lineTo(244.2857, 699.50507);
        ((GeneralPath) shape).curveTo(244.56252, 707.4038, 244.46133, 712.3054, 256.89032, 712.19635);
        ((GeneralPath) shape).lineTo(492.14285, 712.3622);
        ((GeneralPath) shape).curveTo(504.29202, 712.49115, 505.49048, 707.22394, 505.7143, 699.50507);
        ((GeneralPath) shape).lineTo(505.25253, 665.977);
        ((GeneralPath) shape).lineTo(580.1659, 665.9337);
        ((GeneralPath) shape).lineTo(579.4516, 504.0434);
        ((GeneralPath) shape).curveTo(579.7931, 487.76703, 565.2091, 467.94656, 543.61475, 468.62497);
        ((GeneralPath) shape).lineTo(504.53824, 468.24246);
        ((GeneralPath) shape).curveTo(505.42395, 438.97394, 433.46814, 352.04877, 385.7143, 352.3623);
        ((GeneralPath) shape).lineTo(257.14288, 353.0766);
        ((GeneralPath) shape).curveTo(246.14842, 352.73233, 244.24269, 357.42123, 244.49495, 365.21945);
        ((GeneralPath) shape).closePath();

        g.setPaint(colorA);
        g.fill(shape);

        // _0_0_0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(260.5484, 368.32904);
        ((GeneralPath) shape).lineTo(261.17603, 495.1327);
        ((GeneralPath) shape).lineTo(489.70413, 495.5152);
        ((GeneralPath) shape).lineTo(489.2857, 473.79077);
        ((GeneralPath) shape).curveTo(489.2698, 455.949, 426.1488, 443.64746, 404.2857, 447.36218);
        ((GeneralPath) shape).curveTo(407.91025, 431.8248, 407.29047, 373.93906, 390.0, 368.8774);
        ((GeneralPath) shape).closePath();

        g.setPaint(colorB);
        g.fill(shape);

        // _0_0_0_2
        shape = new Rectangle2D.Double(259.28570556640625, 638.0764770507812, 231.42857360839844, 58.57143020629883);
        g.fill(shape);

        g.setTransform(transformations.poll()); // _0_0_0

        g.setTransform(transformations.poll()); // _0

    }
}

