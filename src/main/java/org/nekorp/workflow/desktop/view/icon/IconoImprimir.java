package org.nekorp.workflow.desktop.view.icon;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class IconoImprimir implements FlatButtonIcon {

    /** The width of this icon. */
    private int width;

    /** The height of this icon. */
    private int height;
    
    private Color color = Color.BLACK;


    /** The rendered image. */
    private BufferedImage image;

    /**
     * Creates a new transcoded SVG image.
     */
    public IconoImprimir(int width, int height) {
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(52.51367, 0.025390625);
        ((GeneralPath) shape).lineTo(21.113281, 0.19921875);
        ((GeneralPath) shape).curveTo(18.428308, 0.11514409, 17.963789, 1.259646, 18.02539, 3.1640625);
        ((GeneralPath) shape).lineTo(17.97461, 28.457031);
        ((GeneralPath) shape).lineTo(8.5546875, 28.283203);
        ((GeneralPath) shape).curveTo(3.2300024, 28.336023, -0.17832541, 32.747173, 0.0078125, 38.226562);
        ((GeneralPath) shape).lineTo(0.0078125, 76.61328);
        ((GeneralPath) shape).lineTo(18.046875, 76.60156);
        ((GeneralPath) shape).lineTo(17.97461, 84.80078);
        ((GeneralPath) shape).curveTo(18.04221, 86.729744, 18.017435, 87.92703, 21.052734, 87.90039);
        ((GeneralPath) shape).lineTo(78.50391, 87.94141);
        ((GeneralPath) shape).curveTo(81.47087, 87.9729, 81.7637, 86.68582, 81.81836, 84.80078);
        ((GeneralPath) shape).lineTo(81.70508, 76.61328);
        ((GeneralPath) shape).lineTo(100.0, 76.60156);
        ((GeneralPath) shape).lineTo(99.82617, 37.066406);
        ((GeneralPath) shape).curveTo(99.90956, 33.091534, 96.34782, 28.252298, 91.07422, 28.417969);
        ((GeneralPath) shape).lineTo(81.53125, 28.324219);
        ((GeneralPath) shape).curveTo(81.74755, 21.176512, 64.175705, -0.051172085, 52.51367, 0.025390625);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(21.945312, 3.9238281);
        ((GeneralPath) shape).lineTo(53.558594, 4.0585938);
        ((GeneralPath) shape).curveTo(57.781124, 5.294704, 57.93398, 19.4302, 57.04883, 23.22461);
        ((GeneralPath) shape).curveTo(62.388042, 22.317432, 77.80276, 25.322523, 77.80664, 29.679688);
        ((GeneralPath) shape).lineTo(77.9082, 34.984375);
        ((GeneralPath) shape).lineTo(22.09961, 34.890625);
        ((GeneralPath) shape).lineTo(21.945312, 3.9238281);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(21.636719, 69.79883);
        ((GeneralPath) shape).lineTo(78.1543, 69.79883);
        ((GeneralPath) shape).lineTo(78.1543, 84.103516);
        ((GeneralPath) shape).lineTo(21.636719, 84.103516);
        ((GeneralPath) shape).lineTo(21.636719, 69.79883);
        ((GeneralPath) shape).closePath();

        g.setPaint(color);
        g.fill(shape);

        g.setTransform(transformations.poll()); // _0

    }
}

