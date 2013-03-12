package org.nekorp.workflow.desktop.view.resource.imp;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.geom.*;
import org.nekorp.workflow.desktop.view.resource.ShapeView;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
@org.springframework.stereotype.Component("autoRightView")
public class AutoRightView extends ShapeView {

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
        ((GeneralPath) shape).moveTo(465.29987, 61.174095);
        ((GeneralPath) shape).curveTo(465.29987, 61.174095, 382.75186, 14.6770935, 364.89587, 9.289097);
        ((GeneralPath) shape).curveTo(347.03687, 3.8950968, 252.02448, -4.5259027, 206.20067, 7.9360967);
        ((GeneralPath) shape).curveTo(170.82407, 19.732096, 101.08007, 48.709095, 101.08007, 48.709095);
        ((GeneralPath) shape).curveTo(101.08007, 48.709095, 90.97128, 52.075096, 85.24338, 50.731094);
        ((GeneralPath) shape).curveTo(79.51638, 49.378094, 66.376076, 44.665092, 66.376076, 44.665092);
        ((GeneralPath) shape).lineTo(28.97564, 45.00109);
        ((GeneralPath) shape).lineTo(32.34587, 50.38909);
        ((GeneralPath) shape).curveTo(32.34587, 50.38909, 20.215881, 55.108093, 18.194391, 85.7681);
        ((GeneralPath) shape).curveTo(9.43463, 92.1701, 2.0225296, 90.4841, 2.0225296, 101.6051);
        ((GeneralPath) shape).curveTo(2.0225296, 112.7231, -3.3681102, 124.852104, 8.76197, 133.27611);
        ((GeneralPath) shape).curveTo(8.42447, 137.31711, 5.7291794, 140.6891, 5.7291794, 140.6891);
        ((GeneralPath) shape).lineTo(0.8295593, 140.6891);
        ((GeneralPath) shape).curveTo(0.8295593, 140.6891, -0.33528066, 148.7771, 6.7405095, 150.7961);
        ((GeneralPath) shape).curveTo(12.804951, 151.1351, 21.22838, 148.7771, 24.596361, 148.09909);
        ((GeneralPath) shape).curveTo(26.617851, 148.43509, 24.596361, 156.52309, 24.596361, 156.52309);
        ((GeneralPath) shape).lineTo(101.75387, 156.52309);
        ((GeneralPath) shape).lineTo(104.78537, 135.63708);
        ((GeneralPath) shape).lineTo(108.49217, 135.63708);
        ((GeneralPath) shape).curveTo(108.49217, 135.63708, 100.40627, 188.19409, 151.61838, 190.88809);
        ((GeneralPath) shape).curveTo(189.01758, 190.5551, 195.75587, 163.93909, 196.76718, 162.2531);
        ((GeneralPath) shape).lineTo(208.89587, 162.9281);
        ((GeneralPath) shape).lineTo(209.90718, 169.3271);
        ((GeneralPath) shape).lineTo(482.1449, 170.3381);
        ((GeneralPath) shape).curveTo(482.1449, 170.3381, 494.61288, 170.6741, 495.6239, 164.60811);
        ((GeneralPath) shape).curveTo(499.6649, 172.6961, 512.4689, 191.5661, 539.0879, 192.57411);
        ((GeneralPath) shape).curveTo(565.7039, 193.58511, 578.9669, 168.74211, 580.9889, 163.35411);
        ((GeneralPath) shape).curveTo(583.0079, 162.3401, 583.89886, 170.0021, 583.89886, 170.0021);
        ((GeneralPath) shape).lineTo(591.6479, 169.6631);
        ((GeneralPath) shape).lineTo(591.6479, 173.0351);
        ((GeneralPath) shape).lineTo(672.1739, 172.36009);
        ((GeneralPath) shape).curveTo(672.1739, 172.36009, 676.5539, 164.2721, 670.1549, 163.59709);
        ((GeneralPath) shape).curveTo(670.8239, 157.8731, 675.5429, 137.9921, 679.2509, 137.9921);
        ((GeneralPath) shape).curveTo(680.2619, 129.57109, 679.2509, 120.8081, 679.2509, 120.8081);
        ((GeneralPath) shape).lineTo(670.8239, 118.453094);
        ((GeneralPath) shape).curveTo(670.8239, 118.453094, 660.7169, 114.745094, 660.3809, 103.9601);
        ((GeneralPath) shape).curveTo(653.64294, 95.53909, 586.5929, 68.923096, 465.29993, 61.174095);

        g.setPaint(WHITE);
        g.fill(shape);
        this.autoShape = shape;

        // _0_0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(465.29987, 61.174095);
        ((GeneralPath) shape).curveTo(465.29987, 61.174095, 382.75186, 14.6770935, 364.89587, 9.289097);
        ((GeneralPath) shape).curveTo(347.03687, 3.8950968, 252.02448, -4.5259027, 206.20067, 7.9360967);
        ((GeneralPath) shape).curveTo(170.82407, 19.732096, 101.08007, 48.709095, 101.08007, 48.709095);
        ((GeneralPath) shape).curveTo(101.08007, 48.709095, 90.97128, 52.075096, 85.24338, 50.731094);
        ((GeneralPath) shape).curveTo(79.51638, 49.378094, 66.376076, 44.665092, 66.376076, 44.665092);
        ((GeneralPath) shape).lineTo(28.97564, 45.00109);
        ((GeneralPath) shape).lineTo(32.34587, 50.38909);
        ((GeneralPath) shape).curveTo(32.34587, 50.38909, 20.215881, 55.108093, 18.194391, 85.7681);
        ((GeneralPath) shape).curveTo(9.43463, 92.1701, 2.0225296, 90.4841, 2.0225296, 101.6051);
        ((GeneralPath) shape).curveTo(2.0225296, 112.7231, -3.3681102, 124.852104, 8.76197, 133.27611);
        ((GeneralPath) shape).curveTo(8.42447, 137.31711, 5.7291794, 140.6891, 5.7291794, 140.6891);
        ((GeneralPath) shape).lineTo(0.8295593, 140.6891);
        ((GeneralPath) shape).curveTo(0.8295593, 140.6891, -0.33528066, 148.7771, 6.7405095, 150.7961);
        ((GeneralPath) shape).curveTo(12.804951, 151.1351, 21.22838, 148.7771, 24.596361, 148.09909);
        ((GeneralPath) shape).curveTo(26.617851, 148.43509, 24.596361, 156.52309, 24.596361, 156.52309);
        ((GeneralPath) shape).lineTo(101.75387, 156.52309);
        ((GeneralPath) shape).lineTo(104.78537, 135.63708);
        ((GeneralPath) shape).lineTo(108.49217, 135.63708);
        ((GeneralPath) shape).curveTo(108.49217, 135.63708, 100.40627, 188.19409, 151.61838, 190.88809);
        ((GeneralPath) shape).curveTo(189.01758, 190.5551, 195.75587, 163.93909, 196.76718, 162.2531);
        ((GeneralPath) shape).lineTo(208.89587, 162.9281);
        ((GeneralPath) shape).lineTo(209.90718, 169.3271);
        ((GeneralPath) shape).lineTo(482.1449, 170.3381);
        ((GeneralPath) shape).curveTo(482.1449, 170.3381, 494.61288, 170.6741, 495.6239, 164.60811);
        ((GeneralPath) shape).curveTo(499.6649, 172.6961, 512.4689, 191.5661, 539.0879, 192.57411);
        ((GeneralPath) shape).curveTo(565.7039, 193.58511, 578.9669, 168.74211, 580.9889, 163.35411);
        ((GeneralPath) shape).curveTo(583.0079, 162.3401, 583.89886, 170.0021, 583.89886, 170.0021);
        ((GeneralPath) shape).lineTo(591.6479, 169.6631);
        ((GeneralPath) shape).lineTo(591.6479, 173.0351);
        ((GeneralPath) shape).lineTo(672.1739, 172.36009);
        ((GeneralPath) shape).curveTo(672.1739, 172.36009, 676.5539, 164.2721, 670.1549, 163.59709);
        ((GeneralPath) shape).curveTo(670.8239, 157.8731, 675.5429, 137.9921, 679.2509, 137.9921);
        ((GeneralPath) shape).curveTo(680.2619, 129.57109, 679.2509, 120.8081, 679.2509, 120.8081);
        ((GeneralPath) shape).lineTo(670.8239, 118.453094);
        ((GeneralPath) shape).curveTo(670.8239, 118.453094, 660.7169, 114.745094, 660.3809, 103.9601);
        ((GeneralPath) shape).curveTo(653.64294, 95.53909, 586.5929, 68.923096, 465.29993, 61.174095);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.setStroke(new BasicStroke(1.516176f, 0, 0, 4));
        g.draw(shape);

        // _0_0_2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.13885, 146.2661);
        ((GeneralPath) shape).curveTo(491.13885, 171.8771, 511.90186, 192.6401, 537.5098, 192.6401);
        ((GeneralPath) shape).curveTo(569.97284, 192.6401, 583.88684, 172.7381, 583.88684, 146.26611);
        ((GeneralPath) shape).curveTo(583.88684, 120.65511, 563.12384, 99.892105, 537.5098, 99.892105);
        ((GeneralPath) shape).curveTo(511.90182, 99.892105, 491.13882, 120.655106, 491.13882, 146.26611);

        g.fill(shape);

        // _0_0_3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(107.51596, 146.2661);
        ((GeneralPath) shape).curveTo(107.51596, 171.8771, 128.28046, 192.6401, 153.88936, 192.6401);
        ((GeneralPath) shape).curveTo(179.50066, 192.6401, 200.26396, 171.8771, 200.26396, 146.26611);
        ((GeneralPath) shape).curveTo(200.26396, 120.65511, 179.50066, 99.892105, 153.88936, 99.892105);
        ((GeneralPath) shape).curveTo(128.28046, 99.892105, 107.51596, 120.655106, 107.51596, 146.26611);

        g.fill(shape);

        // _0_0_4
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(367.31686, 10.120096);
        ((GeneralPath) shape).curveTo(367.31686, 10.120096, 364.35287, 15.016096, 367.31686, 16.363096);
        ((GeneralPath) shape).curveTo(370.28687, 17.710096, 449.80188, 64.54009, 449.80188, 64.54009);
        ((GeneralPath) shape).curveTo(449.80188, 64.54009, 456.62988, 68.83309, 462.81888, 59.782093);

        g.setStroke(new BasicStroke(1.010787f, 0, 0, 4));
        g.draw(shape);

        // _0_0_5
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(660.38086, 103.9601);
        ((GeneralPath) shape).curveTo(660.38086, 103.9601, 630.3959, 101.2691, 624.66583, 106.3241);
        ((GeneralPath) shape).curveTo(622.30786, 108.6821, 621.97186, 118.453094, 621.97186, 118.453094);

        g.draw(shape);

        // _0_0_6
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(660.38086, 118.4531);
        ((GeneralPath) shape).curveTo(661.05585, 117.4421, 656.3879, 108.2231, 654.69586, 103.5701);

        g.draw(shape);

        // _0_0_7
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(649.9349, 118.4531);
        ((GeneralPath) shape).curveTo(649.9349, 118.4531, 646.90485, 101.8961, 633.0899, 97.0331);
        ((GeneralPath) shape).curveTo(619.6109, 91.83109, 573.4529, 69.9311, 440.36688, 67.5731);
        ((GeneralPath) shape).curveTo(307.28088, 65.215096, 57.615173, 59.4881, 57.615173, 59.4881);
        ((GeneralPath) shape).curveTo(57.615173, 59.4881, 43.46598, 58.477097, 32.34588, 50.3891);

        g.draw(shape);

        // _0_0_8
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(91.22687, 50.881096);
        ((GeneralPath) shape).lineTo(108.15585, 54.433098);
        ((GeneralPath) shape).lineTo(40.76236, 55.123096);
        ((GeneralPath) shape).curveTo(40.76236, 55.123096, 33.61276, 59.158096, 32.13616, 65.5571);

        g.draw(shape);

        // _0_0_9
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(420.75287, 55.123096);
        ((GeneralPath) shape).curveTo(420.75287, 55.123096, 365.32187, 20.065098, 342.1949, 17.710098);
        ((GeneralPath) shape).curveTo(319.0709, 15.352098, 258.089, 8.950098, 229.4495, 22.087097);
        ((GeneralPath) shape).curveTo(200.81, 35.227097, 175.54099, 50.3891, 175.54099, 50.3891);
        ((GeneralPath) shape).curveTo(175.54099, 50.3891, 162.76158, 57.8351, 208.22089, 58.906097);

        g.draw(shape);

        // _0_0_10
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(206.20065, 7.936096);
        ((GeneralPath) shape).curveTo(206.20065, 7.936096, 212.26515, 14.341096, 202.83136, 20.065096);
        ((GeneralPath) shape).curveTo(193.39906, 25.795095, 157.34746, 51.5711, 145.55386, 53.002098);
        ((GeneralPath) shape).curveTo(133.76117, 54.433098, 99.968864, 49.051098, 99.66077, 49.1381);

        g.setStroke(new BasicStroke(2.021574f, 0, 0, 4));
        g.draw(shape);

        // _0_0_11
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(202.16476, 9.3070965);
        ((GeneralPath) shape).curveTo(202.16476, 9.3070965, 207.88576, 14.002096, 197.10466, 20.065098);
        ((GeneralPath) shape).curveTo(186.32205, 26.131098, 153.25757, 47.6951, 144.20627, 50.3891);
        ((GeneralPath) shape).curveTo(134.77246, 51.0641, 109.69787, 48.5531, 106.56796, 46.4411);

        g.setStroke(new BasicStroke(1.010787f, 0, 0, 4));
        g.draw(shape);

        // _0_0_12
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(621.97186, 100.5941);
        ((GeneralPath) shape).lineTo(568.4009, 93.5171);

        g.draw(shape);

        // _0_0_13
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(509.09985, 87.787094);
        ((GeneralPath) shape).curveTo(509.09985, 87.787094, 83.89548, 72.967094, 50.203064, 75.325096);

        g.draw(shape);

        // _0_0_14
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(23.113935, 62.125095);
        ((GeneralPath) shape).curveTo(23.113935, 62.125095, 49.19176, 70.606094, 50.20306, 79.0301);
        ((GeneralPath) shape).curveTo(51.21316, 87.4541, 53.57326, 150.79611, 53.57326, 150.79611);

        g.draw(shape);

        // _0_0_15
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(50.71516, 85.7801);
        ((GeneralPath) shape).lineTo(18.194386, 85.7681);

        g.draw(shape);

        // _0_0_16
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(40.762363, 91.8311);
        ((GeneralPath) shape).curveTo(40.762363, 91.8311, 14.425634, 94.8641, 4.7084503, 93.5171);

        g.draw(shape);

        // _0_0_17
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(263.47845, 65.5571);
        ((GeneralPath) shape).lineTo(263.47845, 111.0101);
        ((GeneralPath) shape).curveTo(263.47845, 111.0101, 267.52246, 143.0471, 279.65146, 148.0991);
        ((GeneralPath) shape).curveTo(291.44507, 148.0991, 444.74686, 148.0991, 444.74686, 148.0991);
        ((GeneralPath) shape).curveTo(444.74686, 148.0991, 452.83487, 137.65611, 453.17087, 123.84111);
        ((GeneralPath) shape).curveTo(453.50687, 110.02611, 458.24988, 76.62411, 439.03186, 67.5491);
        ((GeneralPath) shape).curveTo(439.03186, 67.5491, 370.96185, 20.068104, 353.10284, 16.363102);
        ((GeneralPath) shape).curveTo(335.24384, 12.655102, 269.88043, 9.625102, 254.38124, 11.980103);
        ((GeneralPath) shape).curveTo(238.88293, 14.341103, 194.74423, 24.280102, 166.44374, 53.0021);
        ((GeneralPath) shape).curveTo(163.07324, 58.4771, 166.78123, 58.1411, 173.18175, 58.1411);
        ((GeneralPath) shape).curveTo(179.58374, 58.1411, 401.61884, 63.5321, 401.61884, 63.5321);

        g.setStroke(new BasicStroke(2.021574f, 0, 0, 4));
        g.draw(shape);

        // _0_0_18
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(484.84186, 133.9511);
        ((GeneralPath) shape).lineTo(451.56885, 133.9511);

        g.setStroke(new BasicStroke(1.010787f, 0, 0, 4));
        g.draw(shape);

        // _0_0_19
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(268.61356, 132.5741);
        ((GeneralPath) shape).lineTo(209.57086, 132.5741);

        g.draw(shape);

        // _0_0_20
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(475.74286, 164.2721);
        ((GeneralPath) shape).curveTo(475.74286, 164.2721, 357.34186, 162.5891, 340.22986, 162.5891);
        ((GeneralPath) shape).curveTo(323.11487, 162.5891, 213.61395, 164.2721, 213.61395, 164.2721);

        g.draw(shape);

        // _0_0_21
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(102.97606, 148.0991);
        ((GeneralPath) shape).curveTo(102.97606, 148.0991, 23.92369, 139.6781, 5.7291718, 140.6891);

        g.draw(shape);

        // _0_0_22
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(32.136166, 68.923096);
        ((GeneralPath) shape).lineTo(20.801805, 68.923096);

        g.draw(shape);

        // _0_0_23
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(32.136166, 75.0701);
        ((GeneralPath) shape).lineTo(19.449467, 75.0701);

        g.draw(shape);

        // _0_0_24
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(27.965506, 79.6241);
        ((GeneralPath) shape).lineTo(18.774466, 79.6241);

        g.draw(shape);

        // _0_0_25
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(53.28736, 143.3231);
        ((GeneralPath) shape).lineTo(44.476067, 132.57411);
        ((GeneralPath) shape).lineTo(4.708454, 127.54911);

        g.draw(shape);

        // _0_0_26
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(49.11436, 142.9691);
        ((GeneralPath) shape).curveTo(49.11436, 142.9691, 48.85756, 133.9511, 8.761963, 133.27611);

        g.setStroke(new BasicStroke(2.021574f, 0, 0, 4));
        g.draw(shape);

        // _0_0_27
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(583.2239, 118.4531);
        ((GeneralPath) shape).lineTo(670.82385, 118.4531);

        g.setStroke(new BasicStroke(1.010787f, 0, 0, 4));
        g.draw(shape);

        // _0_0_28
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(595.1849, 123.1691);
        ((GeneralPath) shape).lineTo(673.5209, 121.4831);
        ((GeneralPath) shape).lineTo(673.5209, 119.2061);

        g.draw(shape);

        // _0_0_29
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(667.7939, 123.1691);
        ((GeneralPath) shape).curveTo(667.7939, 123.1691, 667.4579, 140.3531, 665.4359, 148.09909);
        ((GeneralPath) shape).curveTo(663.41394, 155.85109, 665.4359, 158.8841, 665.4359, 158.8841);

        g.draw(shape);

        // _0_0_30
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(670.15485, 163.5971);
        ((GeneralPath) shape).lineTo(632.75385, 163.5971);

        g.draw(shape);

        // _0_0_31
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(679.25085, 137.9921);
        ((GeneralPath) shape).lineTo(657.5729, 137.9921);

        g.draw(shape);

        // _0_0_32
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(675.01184, 144.3551);
        ((GeneralPath) shape).lineTo(666.18585, 144.3551);

        g.draw(shape);

        // _0_0_33
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(673.5209, 121.4831);
        ((GeneralPath) shape).lineTo(676.5539, 121.4831);
        ((GeneralPath) shape).lineTo(676.5539, 137.9921);
        ((GeneralPath) shape).curveTo(676.5539, 137.9921, 668.68787, 149.4491, 668.41187, 156.5231);

        g.draw(shape);

        // _0_0_34
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(663.0779, 156.5231);
        ((GeneralPath) shape).lineTo(670.46387, 156.5231);

        g.draw(shape);

        // _0_0_35
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(625.0049, 140.6891);
        ((GeneralPath) shape).curveTo(620.2979, 140.3411, 614.5559, 141.3611, 616.9169, 146.08011);
        ((GeneralPath) shape).curveTo(620.6249, 146.08011, 632.75385, 146.08011, 632.75385, 146.08011);
        ((GeneralPath) shape).curveTo(632.75385, 146.08011, 634.1008, 141.3641, 625.0048, 140.6891);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_36
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(280.66275, 75.0701);
        ((GeneralPath) shape).curveTo(281.31436, 74.8751, 297.31876, 75.0701, 297.31876, 75.0701);
        ((GeneralPath) shape).curveTo(297.31876, 75.0701, 304.91986, 83.0741, 300.53986, 87.7871);
        ((GeneralPath) shape).curveTo(291.44507, 87.7901, 279.65146, 87.7871, 279.65146, 87.7871);
        ((GeneralPath) shape).curveTo(279.65146, 87.7871, 269.20636, 78.5201, 280.66275, 75.0701);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_37
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(280.66275, 75.0701);
        ((GeneralPath) shape).curveTo(281.31436, 74.8751, 297.31876, 75.0701, 297.31876, 75.0701);
        ((GeneralPath) shape).curveTo(297.31876, 75.0701, 304.91986, 83.0741, 300.53986, 87.7871);
        ((GeneralPath) shape).curveTo(291.44507, 87.7901, 279.65146, 87.7871, 279.65146, 87.7871);
        ((GeneralPath) shape).curveTo(279.65146, 87.7871, 269.20636, 78.5201, 280.66275, 75.0701);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.draw(shape);

        // _0_0_38
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(584.6669, 114.2381);
        ((GeneralPath) shape).curveTo(584.6669, 114.2381, 570.6299, 92.12509, 536.3999, 92.9471);
        ((GeneralPath) shape).curveTo(502.1699, 93.7751, 487.1489, 109.9241, 484.84192, 123.5981);

        g.draw(shape);

        // _0_0_39
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(592.1008, 172.3241);
        ((GeneralPath) shape).lineTo(590.4478, 138.7391);
        ((GeneralPath) shape).curveTo(590.4478, 138.7391, 586.9618, 97.1381, 534.1978, 96.616104);
        ((GeneralPath) shape).curveTo(487.6708, 96.1601, 484.84183, 135.98811, 484.2748, 135.98811);
        ((GeneralPath) shape).curveTo(483.71082, 135.98811, 482.1658, 170.85712, 482.1658, 170.85712);

        g.draw(shape);

        // _0_0_40
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(591.0569, 151.1321);
        ((GeneralPath) shape).lineTo(583.2239, 151.1321);

        g.draw(shape);

        // _0_0_41
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(492.07486, 151.5881);
        ((GeneralPath) shape).lineTo(482.76886, 158.0981);

        g.draw(shape);

        // _0_0_42
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(505.11887, 146.2661);
        ((GeneralPath) shape).curveTo(505.11887, 164.15509, 519.62085, 178.6601, 537.5099, 178.6601);
        ((GeneralPath) shape).curveTo(555.4019, 178.6601, 569.90686, 164.15509, 569.90686, 146.2661);
        ((GeneralPath) shape).curveTo(569.90686, 128.3741, 555.40186, 113.8721, 537.5099, 113.8721);
        ((GeneralPath) shape).curveTo(519.6209, 113.8721, 505.1189, 128.3741, 505.1189, 146.2661);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_43
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(505.11887, 146.2661);
        ((GeneralPath) shape).curveTo(505.11887, 164.15509, 519.62085, 178.6601, 537.5099, 178.6601);
        ((GeneralPath) shape).curveTo(555.4019, 178.6601, 569.90686, 164.15509, 569.90686, 146.2661);
        ((GeneralPath) shape).curveTo(569.90686, 128.3741, 555.40186, 113.8721, 537.5099, 113.8721);
        ((GeneralPath) shape).curveTo(519.6209, 113.8721, 505.1189, 128.3741, 505.1189, 146.2661);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.draw(shape);

        // _0_0_44
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(508.03186, 146.2661);
        ((GeneralPath) shape).curveTo(508.03186, 162.5471, 521.2289, 175.7471, 537.5099, 175.7471);
        ((GeneralPath) shape).curveTo(553.7939, 175.7471, 566.9939, 162.5471, 566.9939, 146.2661);
        ((GeneralPath) shape).curveTo(566.9939, 129.98509, 553.7939, 116.785095, 537.5099, 116.785095);
        ((GeneralPath) shape).curveTo(521.2289, 116.785095, 508.0319, 129.98509, 508.0319, 146.2661);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_45
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(525.02985, 146.2661);
        ((GeneralPath) shape).curveTo(525.02985, 153.1601, 530.61884, 158.7491, 537.5098, 158.7491);
        ((GeneralPath) shape).curveTo(544.4038, 158.7491, 549.9928, 153.1601, 549.9928, 146.2661);
        ((GeneralPath) shape).curveTo(549.9928, 139.3721, 544.4038, 133.7831, 537.5098, 133.7831);
        ((GeneralPath) shape).curveTo(530.61884, 133.7831, 525.02985, 139.3721, 525.02985, 146.2661);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_46
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(531.4559, 146.2661);
        ((GeneralPath) shape).curveTo(531.4559, 149.6111, 534.16785, 152.3231, 537.5099, 152.3231);
        ((GeneralPath) shape).curveTo(540.8579, 152.3231, 543.5669, 149.6111, 543.5669, 146.2661);
        ((GeneralPath) shape).curveTo(543.5669, 142.9211, 540.8579, 140.20909, 537.5099, 140.20909);
        ((GeneralPath) shape).curveTo(534.1679, 140.20909, 531.4559, 142.9211, 531.4559, 146.2661);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_47
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(514.1249, 128.4491);
        ((GeneralPath) shape).lineTo(529.2419, 136.7561);
        ((GeneralPath) shape).lineTo(524.8979, 144.4091);
        ((GeneralPath) shape).lineTo(511.0709, 134.47011);
        ((GeneralPath) shape).curveTo(511.0709, 134.47011, 510.8429, 130.8251, 514.1249, 128.44911);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_48
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(540.89685, 116.9021);
        ((GeneralPath) shape).lineTo(541.80585, 134.1311);
        ((GeneralPath) shape).lineTo(533.0128, 134.47011);
        ((GeneralPath) shape).lineTo(534.1738, 117.48111);
        ((GeneralPath) shape).curveTo(534.1738, 117.48111, 537.14984, 115.36611, 540.89685, 116.90211);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_49
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(564.14984, 133.2461);
        ((GeneralPath) shape).lineTo(550.48785, 143.78209);
        ((GeneralPath) shape).lineTo(545.21387, 136.74109);
        ((GeneralPath) shape).lineTo(559.8539, 128.04408);
        ((GeneralPath) shape).curveTo(559.8539, 128.04408, 563.2859, 129.29208, 564.1499, 133.24608);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_50
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(561.0959, 163.6301);
        ((GeneralPath) shape).lineTo(545.9729, 155.32309);
        ((GeneralPath) shape).lineTo(550.3169, 147.67009);
        ((GeneralPath) shape).lineTo(564.1439, 157.61209);
        ((GeneralPath) shape).curveTo(564.1439, 157.61209, 564.37494, 161.2571, 561.09595, 163.6301);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_51
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(534.32086, 175.1801);
        ((GeneralPath) shape).lineTo(533.41187, 157.9511);
        ((GeneralPath) shape).lineTo(542.2049, 157.6091);
        ((GeneralPath) shape).lineTo(541.8059, 175.8581);
        ((GeneralPath) shape).curveTo(541.8059, 175.8581, 538.0649, 176.71309, 534.3209, 175.1801);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_52
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(511.06787, 158.8331);
        ((GeneralPath) shape).lineTo(524.72986, 148.3001);
        ((GeneralPath) shape).lineTo(529.2419, 155.94409);
        ((GeneralPath) shape).lineTo(514.60187, 164.9141);
        ((GeneralPath) shape).curveTo(514.60187, 164.9141, 512.6249, 162.5891, 511.06787, 158.8331);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_53
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(121.49746, 146.2661);
        ((GeneralPath) shape).curveTo(121.49746, 164.15509, 135.99947, 178.6601, 153.88936, 178.6601);
        ((GeneralPath) shape).curveTo(171.78136, 178.6601, 186.28336, 164.15509, 186.28336, 146.2661);
        ((GeneralPath) shape).curveTo(186.28336, 128.3741, 171.78136, 113.8721, 153.88936, 113.8721);
        ((GeneralPath) shape).curveTo(135.99945, 113.8721, 121.49746, 128.3741, 121.49746, 146.2661);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_54
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(121.49746, 146.2661);
        ((GeneralPath) shape).curveTo(121.49746, 164.15509, 135.99947, 178.6601, 153.88936, 178.6601);
        ((GeneralPath) shape).curveTo(171.78136, 178.6601, 186.28336, 164.15509, 186.28336, 146.2661);
        ((GeneralPath) shape).curveTo(186.28336, 128.3741, 171.78136, 113.8721, 153.88936, 113.8721);
        ((GeneralPath) shape).curveTo(135.99945, 113.8721, 121.49746, 128.3741, 121.49746, 146.2661);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.draw(shape);

        // _0_0_55
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(124.41076, 146.2661);
        ((GeneralPath) shape).curveTo(124.41076, 162.5471, 137.60835, 175.7471, 153.88936, 175.7471);
        ((GeneralPath) shape).curveTo(170.17245, 175.7471, 183.37126, 162.5471, 183.37126, 146.2661);
        ((GeneralPath) shape).curveTo(183.37126, 129.98509, 170.17245, 116.785095, 153.88936, 116.785095);
        ((GeneralPath) shape).curveTo(137.60835, 116.785095, 124.41076, 129.98509, 124.41076, 146.2661);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_56
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(124.41076, 146.2661);
        ((GeneralPath) shape).curveTo(124.41076, 162.5471, 137.60835, 175.7471, 153.88936, 175.7471);
        ((GeneralPath) shape).curveTo(170.17245, 175.7471, 183.37126, 162.5471, 183.37126, 146.2661);
        ((GeneralPath) shape).curveTo(183.37126, 129.98509, 170.17245, 116.785095, 153.88936, 116.785095);
        ((GeneralPath) shape).curveTo(137.60835, 116.785095, 124.41076, 129.98509, 124.41076, 146.2661);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.draw(shape);

        // _0_0_57
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(141.40875, 146.2661);
        ((GeneralPath) shape).curveTo(141.40875, 153.1601, 146.99745, 158.7491, 153.88936, 158.7491);
        ((GeneralPath) shape).curveTo(160.78336, 158.7491, 166.37086, 153.1601, 166.37086, 146.2661);
        ((GeneralPath) shape).curveTo(166.37086, 139.3721, 160.78337, 133.7831, 153.88936, 133.7831);
        ((GeneralPath) shape).curveTo(146.99745, 133.7831, 141.40875, 139.3721, 141.40875, 146.2661);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_58
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(147.83296, 146.2661);
        ((GeneralPath) shape).curveTo(147.83296, 149.6111, 150.54587, 152.3231, 153.88936, 152.3231);
        ((GeneralPath) shape).curveTo(157.23495, 152.3231, 159.94786, 149.6111, 159.94786, 146.2661);
        ((GeneralPath) shape).curveTo(159.94786, 142.9211, 157.23495, 140.20909, 153.88936, 140.20909);
        ((GeneralPath) shape).curveTo(150.54585, 140.20909, 147.83296, 142.9211, 147.83296, 146.2661);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_59
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(130.50105, 128.4491);
        ((GeneralPath) shape).lineTo(145.62045, 136.7561);
        ((GeneralPath) shape).lineTo(141.27765, 144.4091);
        ((GeneralPath) shape).lineTo(127.45065, 134.47011);
        ((GeneralPath) shape).curveTo(127.45065, 134.47011, 127.21965, 130.8251, 130.50105, 128.44911);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_60
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(157.27727, 116.9021);
        ((GeneralPath) shape).lineTo(158.18297, 134.1311);
        ((GeneralPath) shape).lineTo(149.39177, 134.47011);
        ((GeneralPath) shape).lineTo(150.55428, 117.48111);
        ((GeneralPath) shape).curveTo(150.55428, 117.48111, 153.52847, 115.36611, 157.27728, 116.90211);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_61
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(150.69826, 175.1801);
        ((GeneralPath) shape).lineTo(149.79256, 157.9511);
        ((GeneralPath) shape).lineTo(158.58496, 157.6091);
        ((GeneralPath) shape).lineTo(158.18297, 175.8581);
        ((GeneralPath) shape).curveTo(158.18297, 175.8581, 154.44586, 176.71309, 150.69827, 175.1801);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_62
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(127.44706, 158.8331);
        ((GeneralPath) shape).lineTo(141.10756, 148.3001);
        ((GeneralPath) shape).lineTo(145.62045, 155.94409);
        ((GeneralPath) shape).lineTo(130.98045, 164.9141);
        ((GeneralPath) shape).curveTo(130.98045, 164.9141, 129.00345, 162.5891, 127.44705, 158.8331);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_63
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(208.64386, 118.1141);
        ((GeneralPath) shape).curveTo(208.64386, 118.1141, 194.57565, 88.801094, 156.08176, 88.96909);
        ((GeneralPath) shape).curveTo(117.589355, 89.13709, 106.134155, 111.5411, 104.70106, 117.7781);

        g.draw(shape);

        // _0_0_64
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(209.21117, 164.9141);
        ((GeneralPath) shape).lineTo(211.50676, 164.9141);
        ((GeneralPath) shape).curveTo(211.50676, 164.9141, 214.62526, 139.6781, 206.11755, 122.91709);
        ((GeneralPath) shape).curveTo(197.60835, 106.15309, 181.03455, 90.49309, 153.78735, 92.677086);
        ((GeneralPath) shape).curveTo(126.54015, 94.858086, 115.65225, 103.37509, 108.745056, 119.12809);
        ((GeneralPath) shape).curveTo(103.60545, 135.88908, 101.75385, 156.52309, 101.75385, 156.52309);
        ((GeneralPath) shape).curveTo(101.75385, 156.52309, 8.396347, 149.11009, 0.82954407, 140.68909);

        g.draw(shape);

        // _0_0_65
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(211.51396, 143.1221);
        ((GeneralPath) shape).lineTo(200.26396, 143.1221);

        g.draw(shape);

        // _0_0_66
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(212.06236, 153.3251);
        ((GeneralPath) shape).lineTo(199.72845, 153.3251);

        g.draw(shape);

        // _0_0_67
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(206.79137, 153.9131);
        ((GeneralPath) shape).lineTo(206.45387, 162.5891);

        g.draw(shape);

        // _0_0_68
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(115.19506, 120.7031);
        ((GeneralPath) shape).lineTo(108.27406, 120.7031);

        g.draw(shape);

        // _0_0_69
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(108.49216, 135.6371);
        ((GeneralPath) shape).lineTo(105.12166, 135.6371);

        g.draw(shape);

        // _0_0_70
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(111.24256, 128.0291);
        ((GeneralPath) shape).lineTo(106.36966, 128.0291);

        g.draw(shape);

        // _0_0_71
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.45386, 42.604095);
        ((GeneralPath) shape).curveTo(404.45386, 42.604095, 414.80984, 49.759094, 415.43985, 50.389095);
        ((GeneralPath) shape).curveTo(416.14185, 51.097095, 415.75186, 62.707096, 415.75186, 62.707096);
        ((GeneralPath) shape).lineTo(402.11386, 63.253098);
        ((GeneralPath) shape).lineTo(402.11386, 41.587097);
        ((GeneralPath) shape).lineTo(404.45386, 42.604095);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_72
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.45386, 42.604095);
        ((GeneralPath) shape).curveTo(404.45386, 42.604095, 414.80984, 49.759094, 415.43985, 50.389095);
        ((GeneralPath) shape).curveTo(416.14185, 51.097095, 415.75186, 62.707096, 415.75186, 62.707096);
        ((GeneralPath) shape).lineTo(402.11386, 63.253098);
        ((GeneralPath) shape).lineTo(402.11386, 41.587097);
        ((GeneralPath) shape).lineTo(404.45386, 42.604095);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.draw(shape);

        // _0_0_73
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.81085, 42.850098);
        ((GeneralPath) shape).lineTo(405.62686, 63.1121);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_74
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.81085, 42.850098);
        ((GeneralPath) shape).lineTo(405.62686, 63.1121);

        g.setPaint(new Color(0x171516));
        g.draw(shape);

        // _0_0_75
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(402.11386, 56.860096);
        ((GeneralPath) shape).lineTo(395.95786, 56.860096);
        ((GeneralPath) shape).lineTo(395.95786, 59.902096);
        ((GeneralPath) shape).lineTo(399.03586, 60.292095);
        ((GeneralPath) shape).lineTo(399.54285, 63.112095);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_76
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(402.11386, 56.860096);
        ((GeneralPath) shape).lineTo(395.95786, 56.860096);
        ((GeneralPath) shape).lineTo(395.95786, 59.902096);
        ((GeneralPath) shape).lineTo(399.03586, 60.292095);
        ((GeneralPath) shape).lineTo(399.54285, 63.112095);

        g.setPaint(new Color(0x171516));
        g.draw(shape);

        // _0_0_77
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(369.85486, 62.239098);
        ((GeneralPath) shape).curveTo(369.85486, 62.005096, 377.64584, 46.9661, 377.64584, 46.9661);
        ((GeneralPath) shape).curveTo(377.64584, 46.9661, 382.01385, 45.643097, 380.99985, 49.774097);
        ((GeneralPath) shape).curveTo(379.36185, 53.6681, 375.50385, 62.899097, 375.50385, 62.899097);
        ((GeneralPath) shape).lineTo(369.85486, 62.239098);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_78
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(369.85486, 62.239098);
        ((GeneralPath) shape).curveTo(369.85486, 62.005096, 377.64584, 46.9661, 377.64584, 46.9661);
        ((GeneralPath) shape).curveTo(377.64584, 46.9661, 382.01385, 45.643097, 380.99985, 49.774097);
        ((GeneralPath) shape).curveTo(379.36185, 53.6681, 375.50385, 62.899097, 375.50385, 62.899097);
        ((GeneralPath) shape).lineTo(369.85486, 62.239098);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.draw(shape);

        // _0_0_79
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(271.67087, 24.916096);
        ((GeneralPath) shape).lineTo(271.67087, 60.403095);
        ((GeneralPath) shape).lineTo(287.64136, 60.784096);
        ((GeneralPath) shape).curveTo(287.64136, 60.784096, 282.34787, 48.760094, 277.51636, 48.133095);
        ((GeneralPath) shape).curveTo(277.51636, 48.133095, 280.86435, 46.966095, 280.55386, 39.331093);
        ((GeneralPath) shape).curveTo(280.24097, 31.693092, 280.39786, 25.771091, 271.67087, 24.916092);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_80
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(277.51636, 48.133095);
        ((GeneralPath) shape).lineTo(271.67087, 48.133095);

        g.draw(shape);

        // _0_0_81
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(257.26755, 15.370096);
        ((GeneralPath) shape).lineTo(263.17365, 60.202095);
        ((GeneralPath) shape).lineTo(269.17725, 60.202095);
        ((GeneralPath) shape).lineTo(262.22446, 14.890095);
        ((GeneralPath) shape).lineTo(257.26755, 15.370094);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_82
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(180.88696, 47.266094);
        ((GeneralPath) shape).curveTo(181.15756, 47.113094, 191.09506, 48.991093, 191.79947, 58.069096);
        ((GeneralPath) shape).curveTo(188.39987, 57.730095, 177.97127, 56.248096, 175.69217, 54.802097);
        ((GeneralPath) shape).curveTo(173.41277, 53.3561, 173.41158, 51.649097, 175.54097, 50.3891);
        ((GeneralPath) shape).curveTo(177.66887, 49.135098, 180.88696, 47.266098, 180.88696, 47.266098);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_83
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(180.89626, 133.2461);
        ((GeneralPath) shape).lineTo(167.23575, 143.78209);
        ((GeneralPath) shape).lineTo(161.95995, 136.74109);
        ((GeneralPath) shape).lineTo(176.60025, 128.04408);
        ((GeneralPath) shape).curveTo(176.60025, 128.04408, 180.03136, 129.29208, 180.89626, 133.24608);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_84
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(177.84256, 163.6301);
        ((GeneralPath) shape).lineTo(162.72285, 155.32309);
        ((GeneralPath) shape).lineTo(167.06595, 147.67009);
        ((GeneralPath) shape).lineTo(180.89294, 157.61209);
        ((GeneralPath) shape).curveTo(180.89294, 157.61209, 181.12364, 161.2571, 177.84254, 163.6301);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_85
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(676.5539, 127.7801);
        ((GeneralPath) shape).curveTo(676.5539, 126.4691, 675.1199, 125.4071, 673.3559, 125.4071);
        ((GeneralPath) shape).curveTo(671.58887, 125.4071, 670.1549, 126.46909, 670.1549, 127.7801);
        ((GeneralPath) shape).curveTo(670.1549, 129.0941, 671.5889, 130.1591, 673.3559, 130.1591);
        ((GeneralPath) shape).curveTo(675.1199, 130.1591, 676.5539, 129.0941, 676.5539, 127.780106);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_86
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(583.2239, 153.9131);
        ((GeneralPath) shape).lineTo(591.0569, 158.0981);

        g.draw(shape);

        // _0_0_87
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(587.14185, 169.8581);
        ((GeneralPath) shape).lineTo(587.14185, 155.65009);

        g.draw(shape);

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
        return 682;
    }

    @Override
    public int getShapeHeight() {
        return 194;
    }
}

