package org.nekorp.workflow.desktop.view.resource.damage;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.geom.*;
import org.nekorp.workflow.desktop.view.resource.ShapeView;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
@org.springframework.stereotype.Component("autoRearView")
public class AutoRearView extends ShapeView {

    private Shape autoShape;
    /**
     * Paints the transcoded SVG image on the specified graphics context. You
     * can install a custom transformation on the graphics context to scale the
     * image.
     * 
     * @param g Graphics context.
     */
    @Override
    public void paint(Graphics gOld) {
        Graphics2D g = (Graphics2D) gOld;
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);
        Shape shape;
        
        float origAlpha = 1.0f;
        Composite origComposite = ((Graphics2D)g).getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = (AlphaComposite)origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }
        
        java.util.LinkedList<AffineTransform> transformations = new java.util.LinkedList<>();
        

        // 

        // _0

        // _0_0

        // _0_0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(62.743122, 179.16452);
        ((GeneralPath) shape).curveTo(62.743122, 181.48366, 60.93112, 183.36218, 58.693123, 183.36218);
        ((GeneralPath) shape).lineTo(24.613121, 183.36218);
        ((GeneralPath) shape).curveTo(22.37812, 183.36218, 20.563122, 181.48366, 20.563122, 179.16452);
        ((GeneralPath) shape).lineTo(20.563122, 118.69448);
        ((GeneralPath) shape).curveTo(20.563122, 116.37908, 22.378122, 114.49688, 24.613121, 114.49688);
        ((GeneralPath) shape).lineTo(58.693123, 114.49688);
        ((GeneralPath) shape).curveTo(60.93112, 114.49688, 62.743122, 116.37908, 62.743122, 118.69448);
        ((GeneralPath) shape).lineTo(62.743122, 179.16452);

        g.setPaint(new Color(0x171516));
        g.fill(shape);

        // _0_0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(283.7681, 179.16452);
        ((GeneralPath) shape).curveTo(283.7681, 181.48366, 281.9531, 183.36218, 279.7181, 183.36218);
        ((GeneralPath) shape).lineTo(245.6381, 183.36218);
        ((GeneralPath) shape).curveTo(243.40312, 183.36218, 241.5881, 181.48366, 241.5881, 179.16452);
        ((GeneralPath) shape).lineTo(241.5881, 118.69448);
        ((GeneralPath) shape).curveTo(241.5881, 116.37908, 243.4031, 114.49688, 245.6381, 114.49688);
        ((GeneralPath) shape).lineTo(279.7181, 114.49688);
        ((GeneralPath) shape).curveTo(281.95312, 114.49688, 283.7681, 116.37908, 283.7681, 118.69448);
        ((GeneralPath) shape).lineTo(283.7681, 179.16452);

        g.fill(shape);

        // _0_0_2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(150.63411, 0.19808923);
        ((GeneralPath) shape).curveTo(150.63411, 0.19808923, 209.35611, 1.2093892, 224.17912, 3.7371895);
        ((GeneralPath) shape).curveTo(239.00513, 6.0948896, 255.34311, 16.20369, 268.3181, 53.264194);
        ((GeneralPath) shape).curveTo(269.83313, 53.936794, 272.3591, 53.264194, 272.3591, 53.264194);
        ((GeneralPath) shape).curveTo(272.3591, 53.264194, 275.8991, 43.160194, 282.6401, 42.989197);
        ((GeneralPath) shape).curveTo(289.3751, 42.818195, 301.8641, 41.9041, 302.5151, 53.564198);
        ((GeneralPath) shape).curveTo(303.1661, 65.3941, 289.7141, 62.867798, 285.3341, 62.867798);
        ((GeneralPath) shape).curveTo(280.9511, 62.867798, 270.50812, 63.0949, 270.50812, 63.0949);
        ((GeneralPath) shape).curveTo(270.50812, 63.0949, 280.2791, 81.734795, 281.62613, 85.77669);
        ((GeneralPath) shape).curveTo(282.9731, 89.82069, 280.61514, 107.33798, 278.76114, 116.26988);
        ((GeneralPath) shape).curveTo(276.91315, 125.19849, 268.99313, 151.47787, 262.92712, 151.47787);
        ((GeneralPath) shape).curveTo(258.88614, 151.47787, 261.74814, 141.87547, 240.01613, 140.36017);
        ((GeneralPath) shape).lineTo(236.30814, 139.85527);
        ((GeneralPath) shape).lineTo(152.65613, 139.85527);
        ((GeneralPath) shape).lineTo(152.66212, 139.85527);
        ((GeneralPath) shape).lineTo(66.98814, 139.85527);
        ((GeneralPath) shape).lineTo(63.280136, 140.36017);
        ((GeneralPath) shape).curveTo(41.545135, 141.87547, 44.413136, 151.47787, 40.369137, 151.47787);
        ((GeneralPath) shape).curveTo(34.30314, 151.47787, 26.386137, 125.19847, 24.535137, 116.269875);
        ((GeneralPath) shape).curveTo(22.681137, 107.337975, 20.320137, 89.82069, 21.670137, 85.77669);
        ((GeneralPath) shape).curveTo(23.017138, 81.73479, 32.78814, 63.094894, 32.78814, 63.094894);
        ((GeneralPath) shape).curveTo(32.78814, 63.094894, 22.345139, 62.867794, 17.96514, 62.867794);
        ((GeneralPath) shape).curveTo(13.58214, 62.867794, 0.12714005, 65.3941, 0.7811394, 53.564194);
        ((GeneralPath) shape).curveTo(1.4321394, 41.904095, 13.921139, 42.81819, 20.65914, 42.989193);
        ((GeneralPath) shape).curveTo(27.39714, 43.160194, 30.93714, 53.26419, 30.93714, 53.26419);
        ((GeneralPath) shape).curveTo(30.93714, 53.26419, 33.46314, 53.93679, 34.978138, 53.26419);
        ((GeneralPath) shape).curveTo(47.95314, 16.203686, 64.29114, 6.094887, 79.11714, 3.7371864);
        ((GeneralPath) shape).curveTo(93.94014, 1.2093861, 150.64313, 0.19808602, 150.64313, 0.19808602);
        ((GeneralPath) shape).lineTo(150.63412, 0.19808602);

        g.setPaint(WHITE);
        g.fill(shape);
        this.autoShape = shape;

        // _0_0_3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(150.63411, 0.19808923);
        ((GeneralPath) shape).curveTo(150.63411, 0.19808923, 209.35611, 1.2093892, 224.17912, 3.7371895);
        ((GeneralPath) shape).curveTo(239.00513, 6.0948896, 255.34311, 16.20369, 268.3181, 53.264194);
        ((GeneralPath) shape).curveTo(269.83313, 53.936794, 272.3591, 53.264194, 272.3591, 53.264194);
        ((GeneralPath) shape).curveTo(272.3591, 53.264194, 275.8991, 43.160194, 282.6401, 42.989197);
        ((GeneralPath) shape).curveTo(289.3751, 42.818195, 301.8641, 41.9041, 302.5151, 53.564198);
        ((GeneralPath) shape).curveTo(303.1661, 65.3941, 289.7141, 62.867798, 285.3341, 62.867798);
        ((GeneralPath) shape).curveTo(280.9511, 62.867798, 270.50812, 63.0949, 270.50812, 63.0949);
        ((GeneralPath) shape).curveTo(270.50812, 63.0949, 280.2791, 81.734795, 281.62613, 85.77669);
        ((GeneralPath) shape).curveTo(282.9731, 89.82069, 280.61514, 107.33798, 278.76114, 116.26988);
        ((GeneralPath) shape).curveTo(276.91315, 125.19849, 268.99313, 151.47787, 262.92712, 151.47787);
        ((GeneralPath) shape).curveTo(258.88614, 151.47787, 261.74814, 141.87547, 240.01613, 140.36017);
        ((GeneralPath) shape).lineTo(236.30814, 139.85527);
        ((GeneralPath) shape).lineTo(152.65613, 139.85527);
        ((GeneralPath) shape).lineTo(152.66212, 139.85527);
        ((GeneralPath) shape).lineTo(66.98814, 139.85527);
        ((GeneralPath) shape).lineTo(63.280136, 140.36017);
        ((GeneralPath) shape).curveTo(41.545135, 141.87547, 44.413136, 151.47787, 40.369137, 151.47787);
        ((GeneralPath) shape).curveTo(34.30314, 151.47787, 26.386137, 125.19847, 24.535137, 116.269875);
        ((GeneralPath) shape).curveTo(22.681137, 107.337975, 20.320137, 89.82069, 21.670137, 85.77669);
        ((GeneralPath) shape).curveTo(23.017138, 81.73479, 32.78814, 63.094894, 32.78814, 63.094894);
        ((GeneralPath) shape).curveTo(32.78814, 63.094894, 22.345139, 62.867794, 17.96514, 62.867794);
        ((GeneralPath) shape).curveTo(13.58214, 62.867794, 0.12714005, 65.3941, 0.7811394, 53.564194);
        ((GeneralPath) shape).curveTo(1.4321394, 41.904095, 13.921139, 42.81819, 20.65914, 42.989193);
        ((GeneralPath) shape).curveTo(27.39714, 43.160194, 30.93714, 53.26419, 30.93714, 53.26419);
        ((GeneralPath) shape).curveTo(30.93714, 53.26419, 33.46314, 53.93679, 34.978138, 53.26419);
        ((GeneralPath) shape).curveTo(47.95314, 16.203686, 64.29114, 6.094887, 79.11714, 3.7371864);
        ((GeneralPath) shape).curveTo(93.94014, 1.2093861, 150.64313, 0.19808602, 150.64313, 0.19808602);
        ((GeneralPath) shape).lineTo(150.63412, 0.19808602);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.setStroke(new BasicStroke(1.516176f, 0, 0, 4));
        g.draw(shape);

        // _0_0_4
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(151.64812, 38.271084);
        ((GeneralPath) shape).curveTo(233.00513, 38.271084, 257.3111, 50.705795, 257.3111, 50.705795);
        ((GeneralPath) shape).curveTo(257.3111, 50.705795, 257.3111, 50.705795, 258.3041, 46.229496);
        ((GeneralPath) shape).curveTo(258.8021, 34.291588, 242.7761, 16.255291, 238.6571, 12.6552925);
        ((GeneralPath) shape).curveTo(227.2151, 2.7069921, 151.64812, 3.7008924, 151.64812, 3.7008924);
        ((GeneralPath) shape).curveTo(151.64812, 3.7008924, 76.081116, 2.7069921, 64.639114, 12.6552925);
        ((GeneralPath) shape).curveTo(60.520115, 16.255293, 44.494114, 34.291588, 44.992115, 46.229496);
        ((GeneralPath) shape).curveTo(45.988113, 50.705795, 45.988113, 50.705795, 45.988113, 50.705795);
        ((GeneralPath) shape).curveTo(45.988113, 50.705795, 70.291115, 38.271088, 151.64812, 38.271088);

        g.setStroke(new BasicStroke(1.010787f, 0, 0, 4));
        g.draw(shape);

        // _0_0_5
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(190.6421, 39.360996);
        ((GeneralPath) shape).curveTo(227.17911, 38.956585, 247.3601, 45.233196, 254.32312, 47.223095);
        ((GeneralPath) shape).curveTo(255.06712, 36.529884, 241.50711, 15.547291, 231.94312, 13.401691);
        ((GeneralPath) shape).curveTo(216.3491, 4.6347914, 151.64812, 6.191191, 151.64812, 6.191191);
        ((GeneralPath) shape).curveTo(151.64812, 6.191191, 86.94712, 4.6347914, 71.35312, 13.401691);
        ((GeneralPath) shape).curveTo(61.792118, 15.547292, 48.22912, 36.529884, 48.97012, 47.223095);
        ((GeneralPath) shape).curveTo(55.93612, 45.233196, 76.11712, 38.956585, 112.657104, 39.360996);

        g.setStroke(new BasicStroke(1.516176f, 0, 0, 4));
        g.draw(shape);

        // _0_0_6
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(84.45712, 41.943695);
        ((GeneralPath) shape).curveTo(84.45712, 41.943695, 117.52911, 42.482796, 128.14311, 42.820293);

        g.setStroke(new BasicStroke(1.010787f, 0, 0, 4));
        g.draw(shape);

        // _0_0_7
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(175.06612, 41.943695);
        ((GeneralPath) shape).curveTo(220.89711, 42.697296, 225.94011, 42.963394, 222.31311, 42.849693);

        g.draw(shape);

        // _0_0_8
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(128.14313, 39.958595);
        ((GeneralPath) shape).lineTo(174.29813, 39.958595);
        ((GeneralPath) shape).lineTo(176.32013, 45.179497);
        ((GeneralPath) shape).lineTo(126.11813, 45.179497);
        ((GeneralPath) shape).lineTo(128.14314, 39.958595);

        g.fill(shape);

        // _0_0_9
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(45.736122, 49.566994);
        ((GeneralPath) shape).curveTo(45.736122, 49.566994, 41.215122, 51.078693, 42.73012, 54.110195);
        ((GeneralPath) shape).curveTo(45.42412, 53.432796, 74.91112, 46.190796, 151.22212, 46.190796);
        ((GeneralPath) shape).curveTo(227.5361, 46.190796, 260.3861, 54.110195, 260.3861, 54.110195);
        ((GeneralPath) shape).curveTo(260.3861, 54.110195, 259.5431, 50.504494, 256.5131, 50.705795);

        g.draw(shape);

        // _0_0_10
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(37.17112, 72.303696);
        ((GeneralPath) shape).curveTo(37.17112, 72.303696, 23.353119, 48.549698, 113.65311, 51.751297);
        ((GeneralPath) shape).lineTo(194.17911, 51.453697);
        ((GeneralPath) shape).curveTo(194.17911, 51.453697, 271.3391, 47.203297, 266.62012, 72.303696);

        g.draw(shape);

        // _0_0_11
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(43.28212, 148.93118);
        ((GeneralPath) shape).curveTo(43.28212, 148.93118, 31.27312, 132.61058, 31.27312, 120.146484);
        ((GeneralPath) shape).curveTo(31.27312, 107.68118, 31.27312, 89.31699, 31.27312, 89.31699);
        ((GeneralPath) shape).curveTo(31.27312, 89.31699, 33.46612, 63.037598, 58.39612, 63.037598);
        ((GeneralPath) shape).curveTo(83.32912, 63.037598, 151.4351, 63.037598, 151.4351, 63.037598);

        g.draw(shape);

        // _0_0_12
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(259.5881, 148.93118);
        ((GeneralPath) shape).curveTo(259.5881, 148.93118, 271.5971, 132.61058, 271.5971, 120.146484);
        ((GeneralPath) shape).curveTo(271.5971, 107.68118, 271.5971, 89.31699, 271.5971, 89.31699);
        ((GeneralPath) shape).curveTo(271.5971, 89.31699, 269.4041, 63.037598, 244.47409, 63.037598);
        ((GeneralPath) shape).curveTo(219.54109, 63.037598, 151.43509, 63.037598, 151.43509, 63.037598);

        g.draw(shape);

        // _0_0_13
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(50.395123, 64.0795);
        ((GeneralPath) shape).lineTo(252.47812, 64.0795);

        g.draw(shape);

        // _0_0_14
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(128.14313, 83.81739);
        ((GeneralPath) shape).curveTo(128.14313, 83.81739, 130.15913, 72.7009, 121.90612, 64.0795);

        g.draw(shape);

        // _0_0_15
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(172.0601, 83.81739);
        ((GeneralPath) shape).curveTo(172.0601, 83.81739, 170.0411, 72.7009, 178.2911, 64.0795);

        g.draw(shape);

        // _0_0_16
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(32.374123, 83.81739);
        ((GeneralPath) shape).lineTo(270.49612, 83.81739);

        g.draw(shape);

        // _0_0_17
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(31.27312, 94.54118);
        ((GeneralPath) shape).lineTo(271.59714, 94.54118);

        g.setStroke(new BasicStroke(1.516176f, 0, 0, 4));
        g.draw(shape);

        // _0_0_18
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(73.76812, 41.758595);
        ((GeneralPath) shape).curveTo(73.76812, 41.758595, 72.20812, 27.155788, 79.45312, 24.29199);
        ((GeneralPath) shape).curveTo(87.20512, 24.29199, 108.766106, 24.29199, 108.766106, 24.29199);
        ((GeneralPath) shape).curveTo(108.766106, 24.29199, 111.793106, 27.20739, 111.970116, 39.453697);

        g.setStroke(new BasicStroke(1.010787f, 0, 0, 4));
        g.draw(shape);

        // _0_0_19
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(229.02412, 41.758595);
        ((GeneralPath) shape).curveTo(229.02412, 41.758595, 230.58412, 27.155788, 223.33911, 24.29199);
        ((GeneralPath) shape).curveTo(215.59012, 24.29199, 194.02611, 24.29199, 194.02611, 24.29199);
        ((GeneralPath) shape).curveTo(194.02611, 24.29199, 190.99911, 27.20739, 190.8251, 39.453697);

        g.draw(shape);

        // _0_0_20
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(135.55312, 15.19449);
        ((GeneralPath) shape).curveTo(135.55312, 15.19449, 131.17311, 18.06579, 132.52013, 26.65329);
        ((GeneralPath) shape).curveTo(137.91112, 26.65329, 169.58511, 26.65329, 169.58511, 26.65329);
        ((GeneralPath) shape).curveTo(169.58511, 26.65329, 170.26012, 18.05979, 166.04512, 15.1944895);
        ((GeneralPath) shape).curveTo(162.17213, 15.1944895, 135.55312, 15.1944895, 135.55312, 15.1944895);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_21
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(266.1281, 138.18279);
        ((GeneralPath) shape).curveTo(263.1101, 126.91058, 251.62613, 124.52589, 251.62613, 124.52589);
        ((GeneralPath) shape).curveTo(251.62613, 124.52589, 80.46413, 124.52589, 60.418137, 124.52589);
        ((GeneralPath) shape).curveTo(40.37214, 124.52589, 37.819138, 140.20659, 37.819138, 140.20659);
        ((GeneralPath) shape).lineTo(265.05115, 140.20659);

        g.draw(shape);

        // _0_0_22
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.05112, 140.20657);
        ((GeneralPath) shape).curveTo(265.05112, 140.20657, 261.90112, 133.45657, 249.09712, 129.91867);
        ((GeneralPath) shape).curveTo(237.13612, 129.91867, 53.005127, 129.91867, 53.005127, 129.91867);
        ((GeneralPath) shape).curveTo(53.005127, 129.91867, 42.931126, 132.36098, 38.02913, 139.31137);

        g.draw(shape);

        // _0_0_23
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(179.1851, 135.98198);
        ((GeneralPath) shape).lineTo(122.749115, 135.98198);
        ((GeneralPath) shape).lineTo(119.380104, 102.96218);
        ((GeneralPath) shape).lineTo(183.23212, 102.96218);
        ((GeneralPath) shape).lineTo(179.1851, 135.98198);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_24
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(179.1851, 135.98198);
        ((GeneralPath) shape).lineTo(122.749115, 135.98198);
        ((GeneralPath) shape).lineTo(119.380104, 102.96218);
        ((GeneralPath) shape).lineTo(183.23212, 102.96218);
        ((GeneralPath) shape).lineTo(179.1851, 135.98198);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.draw(shape);

        // _0_0_25
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(176.32011, 108.01658);
        ((GeneralPath) shape).lineTo(183.23212, 102.96218);

        g.draw(shape);

        // _0_0_26
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(125.13411, 108.01658);
        ((GeneralPath) shape).lineTo(119.38011, 102.96218);

        g.draw(shape);

        // _0_0_27
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(178.2911, 68.4283);
        ((GeneralPath) shape).lineTo(256.17413, 68.4283);

        g.draw(shape);

        // _0_0_28
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(200.9201, 74.99889);
        ((GeneralPath) shape).lineTo(258.3161, 74.99889);

        g.draw(shape);

        // _0_0_29
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(171.84111, 80.72589);
        ((GeneralPath) shape).lineTo(256.17413, 80.72589);

        g.draw(shape);

        // _0_0_30
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(179.77611, 72.303696);
        ((GeneralPath) shape).curveTo(179.77611, 72.303696, 175.65111, 77.52429, 179.77611, 78.5356);
        ((GeneralPath) shape).curveTo(184.57611, 78.5356, 196.20111, 78.5356, 196.20111, 78.5356);
        ((GeneralPath) shape).curveTo(196.20111, 78.5356, 196.36911, 74.325096, 198.55911, 72.303696);
        ((GeneralPath) shape).curveTo(196.20111, 72.303696, 179.77611, 72.303696, 179.77611, 72.303696);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_31
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(122.97712, 68.4283);
        ((GeneralPath) shape).lineTo(45.10012, 68.4283);

        g.draw(shape);

        // _0_0_32
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(100.35712, 74.99889);
        ((GeneralPath) shape).lineTo(42.95812, 74.99889);

        g.draw(shape);

        // _0_0_33
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(128.42212, 80.72589);
        ((GeneralPath) shape).lineTo(44.092117, 80.72589);

        g.draw(shape);

        // _0_0_34
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(121.49511, 72.303696);
        ((GeneralPath) shape).curveTo(121.49511, 72.303696, 125.62311, 77.52429, 121.49511, 78.5356);
        ((GeneralPath) shape).curveTo(116.69812, 78.5356, 105.070114, 78.5356, 105.070114, 78.5356);
        ((GeneralPath) shape).curveTo(105.070114, 78.5356, 104.902115, 74.325096, 102.71511, 72.303696);
        ((GeneralPath) shape).curveTo(105.070114, 72.303696, 121.49511, 72.303696, 121.49511, 72.303696);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_35
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(200.9201, 141.03758);
        ((GeneralPath) shape).lineTo(200.9201, 148.93118);
        ((GeneralPath) shape).lineTo(241.51611, 148.93118);

        g.draw(shape);

        // _0_0_36
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(242.52711, 154.3442);
        ((GeneralPath) shape).lineTo(183.23212, 154.85045);
        ((GeneralPath) shape).curveTo(183.23212, 154.85045, 177.2171, 150.38918, 170.74611, 145.16737);
        ((GeneralPath) shape).curveTo(163.43811, 145.16737, 163.34811, 144.98317, 163.34811, 144.98317);
        ((GeneralPath) shape).lineTo(156.29512, 153.33517);
        ((GeneralPath) shape).lineTo(135.88911, 153.33517);

        g.draw(shape);

        // _0_0_37
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(61.76512, 154.85045);
        ((GeneralPath) shape).curveTo(61.76512, 154.85045, 64.45912, 158.22076, 66.82012, 158.22076);
        ((GeneralPath) shape).curveTo(69.17812, 158.22076, 102.71511, 158.22076, 102.71511, 158.22076);

        g.draw(shape);

        // _0_0_38
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(109.00911, 144.23558);
        ((GeneralPath) shape).lineTo(135.88911, 149.91579);
        ((GeneralPath) shape).curveTo(135.88911, 149.91579, 139.76512, 156.36453, 133.19511, 156.53445);
        ((GeneralPath) shape).curveTo(126.625114, 155.86063, 106.303116, 151.14969, 106.303116, 151.14969);

        g.draw(shape);

        // _0_0_39
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(104.84811, 161.75984);
        ((GeneralPath) shape).lineTo(111.96843, 161.75984);
        ((GeneralPath) shape).lineTo(111.96843, 152.02509);
        ((GeneralPath) shape).lineTo(104.84811, 152.02509);
        ((GeneralPath) shape).lineTo(104.84811, 161.75984);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_40
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(111.97012, 159.73601);
        ((GeneralPath) shape).lineTo(114.326775, 159.73601);
        ((GeneralPath) shape).lineTo(114.326775, 153.50162);
        ((GeneralPath) shape).lineTo(111.97012, 153.50162);
        ((GeneralPath) shape).lineTo(111.97012, 159.73601);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_41
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(102.40911, 159.73601);
        ((GeneralPath) shape).lineTo(104.84895, 159.73601);
        ((GeneralPath) shape).lineTo(104.84895, 153.50162);
        ((GeneralPath) shape).lineTo(102.40911, 153.50162);
        ((GeneralPath) shape).lineTo(102.40911, 159.73601);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_42
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(125.13411, 146.93198);
        ((GeneralPath) shape).lineTo(129.43312, 141.03758);
        ((GeneralPath) shape).lineTo(134.03812, 140.20659);
        ((GeneralPath) shape).lineTo(140.27213, 148.93118);
        ((GeneralPath) shape).lineTo(153.07312, 148.93118);
        ((GeneralPath) shape).lineTo(162.17212, 140.20659);

        g.draw(shape);

        // _0_0_43
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(173.62611, 139.85529);
        ((GeneralPath) shape).lineTo(184.57611, 150.38438);
        ((GeneralPath) shape).lineTo(188.25111, 150.38438);

        g.draw(shape);

        // _0_0_44
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(188.25111, 159.73601);
        ((GeneralPath) shape).lineTo(191.98705, 159.73601);
        ((GeneralPath) shape).lineTo(191.98705, 141.03758);
        ((GeneralPath) shape).lineTo(188.25111, 141.03758);
        ((GeneralPath) shape).lineTo(188.25111, 159.73601);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_45
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(191.9891, 160.24342);
        ((GeneralPath) shape).lineTo(198.5598, 160.24342);
        ((GeneralPath) shape).lineTo(198.5598, 140.2067);
        ((GeneralPath) shape).lineTo(191.9891, 140.2067);
        ((GeneralPath) shape).lineTo(191.9891, 160.24342);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_46
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(197.71611, 158.22076);
        ((GeneralPath) shape).lineTo(203.27667, 158.22076);
        ((GeneralPath) shape).lineTo(203.27667, 141.03757);
        ((GeneralPath) shape).lineTo(197.71611, 141.03757);
        ((GeneralPath) shape).lineTo(197.71611, 158.22076);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_47
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(154.4201, 154.3442);
        ((GeneralPath) shape).curveTo(154.4201, 154.3442, 151.22212, 161.75983, 147.8531, 161.75983);
        ((GeneralPath) shape).curveTo(144.48111, 161.75983, 130.83711, 162.26373, 130.33011, 161.75983);
        ((GeneralPath) shape).curveTo(129.82611, 161.25241, 125.13411, 155.2395, 125.13411, 155.2395);

        g.draw(shape);

        // _0_0_48
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(114.32511, 158.16335);
        ((GeneralPath) shape).lineTo(123.33111, 154.85045);

        g.draw(shape);

        // _0_0_49
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(17.12512, 42.908195);
        ((GeneralPath) shape).curveTo(17.12512, 42.908195, 19.98412, 43.667797, 20.32012, 48.045696);
        ((GeneralPath) shape).curveTo(19.98712, 53.098896, 20.32012, 63.037598, 20.32012, 63.037598);

        g.draw(shape);

        // _0_0_50
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(34.978123, 53.264194);
        ((GeneralPath) shape).lineTo(32.788124, 63.094894);

        g.draw(shape);

        // _0_0_51
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(20.320122, 58.180294);
        ((GeneralPath) shape).lineTo(42.41812, 58.180294);

        g.draw(shape);

        // _0_0_52
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(285.68213, 43.169495);
        ((GeneralPath) shape).curveTo(285.68213, 43.169495, 282.81714, 43.929096, 282.48114, 48.306995);
        ((GeneralPath) shape).curveTo(282.81714, 53.361397, 282.48114, 63.298897, 282.48114, 63.298897);

        g.draw(shape);

        // _0_0_53
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(267.8261, 53.264194);
        ((GeneralPath) shape).lineTo(270.0161, 63.094894);

        g.draw(shape);

        // _0_0_54
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(282.4811, 58.442795);
        ((GeneralPath) shape).lineTo(260.3861, 58.442795);

        g.draw(shape);

        // _0_0_55
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(282.56512, 60.161797);
        ((GeneralPath) shape).lineTo(298.12012, 60.161797);
        ((GeneralPath) shape).curveTo(298.12012, 60.161797, 302.3951, 57.393696, 302.4671, 52.972298);

        g.draw(shape);

        // _0_0_56
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(20.65912, 60.161797);
        ((GeneralPath) shape).lineTo(5.101121, 60.161797);
        ((GeneralPath) shape).curveTo(5.101121, 60.161797, 0.8291211, 57.393696, 0.7571211, 52.972298);

        g.draw(shape);

        // _0_0_57
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(125.13411, 133.96059);
        ((GeneralPath) shape).lineTo(176.32281, 133.96059);
        ((GeneralPath) shape).lineTo(176.32281, 108.01643);
        ((GeneralPath) shape).lineTo(125.13411, 108.01643);
        ((GeneralPath) shape).lineTo(125.13411, 133.96059);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_58
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(125.13411, 133.96059);
        ((GeneralPath) shape).lineTo(176.32281, 133.96059);
        ((GeneralPath) shape).lineTo(176.32281, 108.01643);
        ((GeneralPath) shape).lineTo(125.13411, 108.01643);
        ((GeneralPath) shape).lineTo(125.13411, 133.96059);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.setStroke(new BasicStroke(1.516176f, 0, 0, 4));
        g.draw(shape);

        // _0_0_59
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(74.74012, 135.06218);
        ((GeneralPath) shape).lineTo(64.39312, 140.20657);
        ((GeneralPath) shape).curveTo(64.39312, 140.20657, 63.82312, 148.78598, 64.39312, 148.27988);
        ((GeneralPath) shape).curveTo(64.96612, 147.77588, 71.65612, 154.3442, 71.65612, 154.3442);
        ((GeneralPath) shape).lineTo(101.017105, 154.3442);
        ((GeneralPath) shape).lineTo(107.42212, 150.46988);
        ((GeneralPath) shape).lineTo(109.609116, 141.87668);
        ((GeneralPath) shape).lineTo(102.02811, 135.06218);
        ((GeneralPath) shape).lineTo(74.74012, 135.06218);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_60
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(74.74012, 135.06218);
        ((GeneralPath) shape).lineTo(64.39312, 140.20657);
        ((GeneralPath) shape).curveTo(64.39312, 140.20657, 63.82312, 148.78598, 64.39312, 148.27988);
        ((GeneralPath) shape).curveTo(64.96612, 147.77588, 71.65612, 154.3442, 71.65612, 154.3442);
        ((GeneralPath) shape).lineTo(101.017105, 154.3442);
        ((GeneralPath) shape).lineTo(107.42212, 150.46988);
        ((GeneralPath) shape).lineTo(109.609116, 141.87668);
        ((GeneralPath) shape).lineTo(102.02811, 135.06218);
        ((GeneralPath) shape).lineTo(74.74012, 135.06218);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.setStroke(new BasicStroke(1.010787f, 0, 0, 4));
        g.draw(shape);

        // _0_0_61
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(92.79712, 144.23558);
        ((GeneralPath) shape).curveTo(92.79712, 147.30698, 90.136116, 149.79608, 86.85112, 149.79608);
        ((GeneralPath) shape).curveTo(83.56612, 149.79608, 80.90812, 147.30698, 80.90812, 144.23558);
        ((GeneralPath) shape).curveTo(80.90812, 141.16298, 83.56612, 138.67508, 86.85112, 138.67508);
        ((GeneralPath) shape).curveTo(90.13612, 138.67508, 92.79712, 141.16298, 92.79712, 144.23558);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_62
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(155.26312, 72.892);
        ((GeneralPath) shape).curveTo(155.26312, 75.3574, 153.26512, 77.3545, 150.79912, 77.3545);
        ((GeneralPath) shape).curveTo(148.3361, 77.3545, 146.3381, 75.3574, 146.3381, 72.892);
        ((GeneralPath) shape).curveTo(146.3381, 70.4263, 148.3361, 68.4283, 150.79912, 68.4283);
        ((GeneralPath) shape).curveTo(153.26512, 68.4283, 155.26312, 70.4263, 155.26312, 72.892);

        g.fill(shape);

        // _0_0_63
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(152.16711, 72.892);
        ((GeneralPath) shape).curveTo(152.16711, 73.6453, 151.5551, 74.2582, 150.79912, 74.2582);
        ((GeneralPath) shape).curveTo(150.04611, 74.2582, 149.4311, 73.6453, 149.4311, 72.892);
        ((GeneralPath) shape).curveTo(149.4311, 72.1372, 150.04611, 71.5255, 150.79912, 71.5255);
        ((GeneralPath) shape).curveTo(151.55511, 71.5255, 152.16711, 72.1372, 152.16711, 72.892);

        g.setPaint(WHITE);
        g.fill(shape);

    }

    public Shape getAutoShape() {
        return autoShape;
    }

    @Override
    public boolean shapeContains(double x, double y) {
        return this.autoShape.contains(x, y);
    }

    @Override
    public int getShapeWidth() {
        return 304;
    }

    @Override
    public int getShapeHeight() {
        return 184;
    }
}

