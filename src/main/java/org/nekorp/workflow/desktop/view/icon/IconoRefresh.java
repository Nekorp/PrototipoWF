package org.nekorp.workflow.desktop.view.icon;



import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import static java.awt.Color.*;
import static java.awt.MultipleGradientPaint.CycleMethod.*;
import static java.awt.MultipleGradientPaint.ColorSpaceType.*;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class IconoRefresh implements FlatButtonIcon {

    /** The width of this icon. */
    private int width;

    /** The height of this icon. */
    private int height;

    /** The rendered image. */
    private BufferedImage image;
    
    private Color color;

    /**
     * Creates a new transcoded SVG image.
     */
    public IconoRefresh(int width, int height) {
        this.width = width;
        this.height = height;
        color = Color.BLACK;
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
    public void setColor(Color color) {
        this.color = color;
        this.image = null;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (image == null) {
            image = new BufferedImage(getIconWidth(), getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            double coef = Math.min((double) width / (double) 68, (double) height / (double) 81);
            
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

        // _0_0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(44.85476, 13.658783);
        ((GeneralPath) shape).lineTo(39.8797, 18.833183);
        ((GeneralPath) shape).lineTo(62.034473, 24.087093);
        ((GeneralPath) shape).lineTo(56.182423, 2.2196937);
        ((GeneralPath) shape).lineTo(51.25553, 7.156234);
        ((GeneralPath) shape).curveTo(23.11004, -15.968987, -18.852411, 21.698124, 9.346271, 53.566833);
        ((GeneralPath) shape).curveTo(34.37855, 75.45358, 62.938217, 55.468464, 62.84925, 31.573273);
        ((GeneralPath) shape).lineTo(53.94392, 31.646673);
        ((GeneralPath) shape).curveTo(53.04116, 50.485714, 32.096634, 60.792103, 16.790325, 48.184963);
        ((GeneralPath) shape).curveTo(-3.4530144, 28.782303, 21.423546, -2.7391064, 44.85476, 13.658743);
        ((GeneralPath) shape).closePath();

        g.setPaint(color);
        g.fill(shape);

        g.setTransform(transformations.poll()); // _0
    }
}

