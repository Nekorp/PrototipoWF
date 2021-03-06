package org.nekorp.workflow.desktop.view.icon;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class IconoGuardar implements FlatButtonIcon {

    /** The width of this icon. */
    private final int width;

    /** The height of this icon. */
    private final int height;
    
    private Color color = Color.BLACK;

    /** The rendered image. */
    private BufferedImage image;

    /**
     * Creates a new transcoded SVG image.
     * @param width
     * @param height
     */
    public IconoGuardar(int width, int height) {
        this.width = width;
        this.height = height;
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

        // _0_0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(15.798828, -0.025390625);
        ((GeneralPath) shape).curveTo(8.35722, -0.023986336, 0.03630279, 1.2081978, 0.0, 11.673828);
        ((GeneralPath) shape).lineTo(0.02734375, 97.61328);
        ((GeneralPath) shape).lineTo(87.65625, 97.572266);
        ((GeneralPath) shape).curveTo(97.62655, 97.51807, 99.99252, 90.976326, 99.958984, 86.42969);
        ((GeneralPath) shape).lineTo(100.0, -0.021484375);
        ((GeneralPath) shape).lineTo(73.59961, 0.03125);
        ((GeneralPath) shape).lineTo(73.46484, 28.148438);
        ((GeneralPath) shape).lineTo(15.814453, 28.269531);
        ((GeneralPath) shape).lineTo(15.798828, -0.025390625);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(77.76367, 45.009766);
        ((GeneralPath) shape).lineTo(77.74414, 76.90234);
        ((GeneralPath) shape).curveTo(77.77294, 79.79597, 76.1533, 82.745926, 72.046875, 82.796875);
        ((GeneralPath) shape).lineTo(16.179688, 82.8125);
        ((GeneralPath) shape).lineTo(16.294922, 51.404297);
        ((GeneralPath) shape).curveTo(16.348421, 46.904507, 19.263975, 44.95589, 22.628906, 45.04492);
        ((GeneralPath) shape).lineTo(77.76367, 45.009766);
        ((GeneralPath) shape).closePath();

        g.setPaint(color);
        g.fill(shape);

        // _0_0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(50.207363, -0.02367086);
        ((GeneralPath) shape).lineTo(50.202663, 22.87529);
        ((GeneralPath) shape).lineTo(62.531532, 22.87529);
        ((GeneralPath) shape).lineTo(62.51123, -0.025438309);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        g.setTransform(transformations.poll()); // _0

    }
}

