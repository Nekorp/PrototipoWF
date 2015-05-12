package org.nekorp.workflow.desktop.view.resource.damage;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.geom.*;
import org.nekorp.workflow.desktop.view.resource.ShapeView;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
@org.springframework.stereotype.Component("autoFrontView")
public class AutoFrontView extends ShapeView {

    private Shape autoShape;
    
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
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1, 0, 0, 1, 0, 0.7676826f));

        // _0_0_0

        // _0_0_0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(65.51575, 186.4134);
        ((GeneralPath) shape).curveTo(65.51575, 188.71655, 63.673225, 190.59451, 61.440945, 190.59451);
        ((GeneralPath) shape).lineTo(27.389763, 190.59451);
        ((GeneralPath) shape).curveTo(25.122046, 190.59451, 23.314959, 188.71655, 23.314959, 186.4134);
        ((GeneralPath) shape).lineTo(23.314959, 125.929146);
        ((GeneralPath) shape).curveTo(23.314959, 123.626, 25.122046, 121.74805, 27.389763, 121.74805);
        ((GeneralPath) shape).lineTo(61.440945, 121.74805);
        ((GeneralPath) shape).curveTo(63.67323, 121.74805, 65.51575, 123.626, 65.51575, 125.929146);
        ((GeneralPath) shape).lineTo(65.51575, 186.4134);

        g.setPaint(new Color(0x171516));
        g.fill(shape);

        // _0_0_0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(286.5118, 186.4134);
        ((GeneralPath) shape).curveTo(286.5118, 188.71655, 284.7047, 190.59451, 282.47244, 190.59451);
        ((GeneralPath) shape).lineTo(248.38582, 190.59451);
        ((GeneralPath) shape).curveTo(246.15353, 190.59451, 244.34645, 188.71655, 244.34645, 186.4134);
        ((GeneralPath) shape).lineTo(244.34645, 125.929146);
        ((GeneralPath) shape).curveTo(244.34645, 123.626, 246.15353, 121.74805, 248.38582, 121.74805);
        ((GeneralPath) shape).lineTo(282.47244, 121.74805);
        ((GeneralPath) shape).curveTo(284.7047, 121.74805, 286.5118, 123.626, 286.5118, 125.929146);
        ((GeneralPath) shape).lineTo(286.5118, 186.4134);

        g.fill(shape);

        // _0_0_0_2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(154.62993, 163.94884);
        ((GeneralPath) shape).lineTo(29.480316, 163.94884);
        ((GeneralPath) shape).curveTo(29.480316, 163.94884, 26.858269, 161.78741, 29.480316, 158.66931);
        ((GeneralPath) shape).curveTo(29.267717, 153.38979, 21.826773, 116.53939, 21.11811, 105.7323);
        ((GeneralPath) shape).curveTo(20.409449, 94.96063, 17.503937, 86.81103, 31.889765, 73.66536);
        ((GeneralPath) shape).curveTo(33.34252, 66.22441, 34.759842, 63.1063, 34.759842, 63.1063);
        ((GeneralPath) shape).lineTo(8.64567, 61.68898);
        ((GeneralPath) shape).curveTo(8.64567, 61.68898, 0.74409485, 58.11024, 0.74409485, 52.33465);
        ((GeneralPath) shape).curveTo(0.99212635, 46.59449, 8.1850395, 41.811028, 17.29134, 42.271656);
        ((GeneralPath) shape).curveTo(25.653545, 42.271656, 33.80315, 52.58268, 33.80315, 52.58268);
        ((GeneralPath) shape).lineTo(36.92126, 53.29134);
        ((GeneralPath) shape).curveTo(36.92126, 53.29134, 48.401577, 16.440945, 78.09449, 5.421261);
        ((GeneralPath) shape).curveTo(105.16536, -0.31889677, 150.69685, 0.85039425, 154.62993, 0.85039425);
        ((GeneralPath) shape).lineTo(154.70084, 0.85039425);
        ((GeneralPath) shape).curveTo(158.63391, 0.85039425, 204.1654, -0.31889713, 231.23627, 5.421261);
        ((GeneralPath) shape).curveTo(260.92917, 16.440947, 272.4095, 53.291344, 272.4095, 53.291344);
        ((GeneralPath) shape).lineTo(275.5276, 52.582684);
        ((GeneralPath) shape).curveTo(275.5276, 52.582684, 283.6772, 42.27166, 292.0394, 42.27166);
        ((GeneralPath) shape).curveTo(301.1457, 41.81103, 308.33862, 46.594494, 308.58664, 52.334652);
        ((GeneralPath) shape).curveTo(308.58664, 58.110245, 300.68506, 61.688984, 300.68506, 61.688984);
        ((GeneralPath) shape).lineTo(274.5709, 63.106308);
        ((GeneralPath) shape).curveTo(274.5709, 63.106308, 276.02365, 66.22442, 277.44098, 73.66537);
        ((GeneralPath) shape).curveTo(291.8268, 86.811035, 288.95673, 94.96065, 288.21265, 105.7323);
        ((GeneralPath) shape).curveTo(287.50397, 116.53939, 280.09848, 153.38977, 279.85043, 158.66931);
        ((GeneralPath) shape).curveTo(282.47247, 161.78741, 279.85043, 163.94884, 279.85043, 163.94884);
        ((GeneralPath) shape).lineTo(154.70082, 163.94884);
        ((GeneralPath) shape).lineTo(154.62991, 163.94884);

        g.setPaint(WHITE);
        g.fill(shape);
        this.autoShape = shape;

        // _0_0_0_3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(154.62993, 163.94884);
        ((GeneralPath) shape).lineTo(29.480316, 163.94884);
        ((GeneralPath) shape).curveTo(29.480316, 163.94884, 26.858269, 161.78741, 29.480316, 158.66931);
        ((GeneralPath) shape).curveTo(29.267717, 153.38979, 21.826773, 116.53939, 21.11811, 105.7323);
        ((GeneralPath) shape).curveTo(20.409449, 94.96063, 17.503937, 86.81103, 31.889765, 73.66536);
        ((GeneralPath) shape).curveTo(33.34252, 66.22441, 34.759842, 63.1063, 34.759842, 63.1063);
        ((GeneralPath) shape).lineTo(8.64567, 61.68898);
        ((GeneralPath) shape).curveTo(8.64567, 61.68898, 0.74409485, 58.11024, 0.74409485, 52.33465);
        ((GeneralPath) shape).curveTo(0.99212635, 46.59449, 8.1850395, 41.811028, 17.29134, 42.271656);
        ((GeneralPath) shape).curveTo(25.653545, 42.271656, 33.80315, 52.58268, 33.80315, 52.58268);
        ((GeneralPath) shape).lineTo(36.92126, 53.29134);
        ((GeneralPath) shape).curveTo(36.92126, 53.29134, 48.401577, 16.440945, 78.09449, 5.421261);
        ((GeneralPath) shape).curveTo(105.16536, -0.31889677, 150.69685, 0.85039425, 154.62993, 0.85039425);
        ((GeneralPath) shape).lineTo(154.70084, 0.85039425);
        ((GeneralPath) shape).curveTo(158.63391, 0.85039425, 204.1654, -0.31889713, 231.23627, 5.421261);
        ((GeneralPath) shape).curveTo(260.92917, 16.440947, 272.4095, 53.291344, 272.4095, 53.291344);
        ((GeneralPath) shape).lineTo(275.5276, 52.582684);
        ((GeneralPath) shape).curveTo(275.5276, 52.582684, 283.6772, 42.27166, 292.0394, 42.27166);
        ((GeneralPath) shape).curveTo(301.1457, 41.81103, 308.33862, 46.594494, 308.58664, 52.334652);
        ((GeneralPath) shape).curveTo(308.58664, 58.110245, 300.68506, 61.688984, 300.68506, 61.688984);
        ((GeneralPath) shape).lineTo(274.5709, 63.106308);
        ((GeneralPath) shape).curveTo(274.5709, 63.106308, 276.02365, 66.22442, 277.44098, 73.66537);
        ((GeneralPath) shape).curveTo(291.8268, 86.811035, 288.95673, 94.96065, 288.21265, 105.7323);
        ((GeneralPath) shape).curveTo(287.50397, 116.53939, 280.09848, 153.38977, 279.85043, 158.66931);
        ((GeneralPath) shape).curveTo(282.47247, 161.78741, 279.85043, 163.94884, 279.85043, 163.94884);
        ((GeneralPath) shape).lineTo(154.70082, 163.94884);
        ((GeneralPath) shape).lineTo(154.62991, 163.94884);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.setStroke(new BasicStroke(1.488189f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_4
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(154.3819, 60.590553);
        ((GeneralPath) shape).curveTo(199.5945, 60.590553, 266.3504, 65.97638, 266.8819, 64.80709);
        ((GeneralPath) shape).curveTo(258.0945, 34.795277, 240.09448, 17.29134, 237.89764, 16.122047);
        ((GeneralPath) shape).curveTo(219.5433, 7.795276, 154.34647, 8.539371, 154.34647, 8.539371);
        ((GeneralPath) shape).lineTo(154.70079, 8.539371);
        ((GeneralPath) shape).curveTo(154.70079, 8.539371, 89.50394, 7.795276, 71.18504, 16.122047);
        ((GeneralPath) shape).curveTo(68.98819, 17.291338, 50.95276, 34.795277, 42.20079, 64.80709);
        ((GeneralPath) shape).curveTo(42.696854, 65.97638, 109.45276, 60.590553, 154.66536, 60.590553);
        ((GeneralPath) shape).lineTo(154.3819, 60.590553);
        ((GeneralPath) shape).closePath();

        g.setStroke(new BasicStroke(0.992126f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_5
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(253.77167, 114.16536);
        ((GeneralPath) shape).lineTo(253.77167, 96.980316);
        ((GeneralPath) shape).lineTo(268.2638, 64.13386);
        ((GeneralPath) shape).curveTo(268.2638, 64.13386, 266.59842, 42.05905, 240.98032, 15.767715);
        ((GeneralPath) shape).curveTo(230.0315, 11.728346, 193.14566, 9.389763, 192.9685, 9.389763);

        g.setStroke(new BasicStroke(1.488189f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_6
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(277.19293, 72.4252);
        ((GeneralPath) shape).lineTo(261.70868, 95.633865);

        g.setStroke(new BasicStroke(0.992126f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_7
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(288.70868, 91.87796);
        ((GeneralPath) shape).lineTo(268.2638, 99.31891);

        g.draw(shape);

        // _0_0_0_8
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(287.2205, 114.16536);
        ((GeneralPath) shape).lineTo(22.11026, 114.16536);

        g.draw(shape);

        // _0_0_0_9
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(278.4685, 114.98032);
        ((GeneralPath) shape).curveTo(278.4685, 114.98032, 270.9567, 117.17718, 265.3937, 117.17718);
        ((GeneralPath) shape).lineTo(41.84645, 117.17718);
        ((GeneralPath) shape).curveTo(41.84645, 117.17718, 34.015743, 116.929146, 26.858261, 114.16537);

        g.draw(shape);

        // _0_0_0_10
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(270.9567, 114.16536);
        ((GeneralPath) shape).curveTo(270.9567, 114.16536, 273.33072, 98.82284, 260.18506, 96.803154);
        ((GeneralPath) shape).curveTo(244.34647, 96.44882, 212.17323, 97.65355, 212.17323, 97.65355);
        ((GeneralPath) shape).curveTo(212.17323, 97.65355, 201.40158, 108.60237, 198.17717, 113.492134);

        g.draw(shape);

        // _0_0_0_11
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(209.44489, 100.45276);
        ((GeneralPath) shape).lineTo(100.24016, 100.45276);

        g.draw(shape);

        // _0_0_0_12
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(271.13388, 111.11812);
        ((GeneralPath) shape).lineTo(253.77167, 111.11812);

        g.draw(shape);

        // _0_0_0_13
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(225.49606, 111.11812);
        ((GeneralPath) shape).lineTo(200.79921, 111.11812);

        g.draw(shape);

        // _0_0_0_14
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(109.59449, 111.11812);
        ((GeneralPath) shape).lineTo(84.862206, 111.11812);

        g.draw(shape);

        // _0_0_0_15
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(205.61812, 105.44882);
        ((GeneralPath) shape).lineTo(266.8819, 105.44882);

        g.draw(shape);

        // _0_0_0_16
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(209.44489, 101.19686);
        ((GeneralPath) shape).lineTo(261.46063, 101.19686);

        g.draw(shape);

        // _0_0_0_17
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(55.913387, 114.16536);
        ((GeneralPath) shape).lineTo(55.913387, 96.980316);
        ((GeneralPath) shape).lineTo(41.42126, 64.13386);
        ((GeneralPath) shape).curveTo(41.42126, 64.13386, 43.122047, 42.05905, 68.74016, 15.767715);
        ((GeneralPath) shape).curveTo(79.68898, 11.728346, 116.53937, 9.389763, 116.75197, 9.389763);

        g.setStroke(new BasicStroke(1.488189f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_18
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(32.492126, 72.4252);
        ((GeneralPath) shape).lineTo(48.01181, 95.633865);

        g.setStroke(new BasicStroke(0.992126f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_19
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(21.011812, 91.87796);
        ((GeneralPath) shape).lineTo(41.42126, 99.31891);

        g.draw(shape);

        // _0_0_0_20
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(38.728348, 114.16536);
        ((GeneralPath) shape).curveTo(38.728348, 114.16536, 36.389767, 98.82284, 49.535435, 96.803154);
        ((GeneralPath) shape).curveTo(65.338585, 96.44882, 97.54725, 97.65355, 97.54725, 97.65355);
        ((GeneralPath) shape).curveTo(97.54725, 97.65355, 108.3189, 108.60237, 111.50788, 113.492134);

        g.draw(shape);

        // _0_0_0_21
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(38.586617, 111.11812);
        ((GeneralPath) shape).lineTo(55.91339, 111.11812);

        g.draw(shape);

        // _0_0_0_22
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(104.95276, 105.44882);
        ((GeneralPath) shape).lineTo(42.83858, 105.44882);

        g.draw(shape);

        // _0_0_0_23
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(100.24016, 101.19686);
        ((GeneralPath) shape).lineTo(47.232285, 101.19686);

        g.draw(shape);

        // _0_0_0_24
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(87.838585, 97.334656);
        ((GeneralPath) shape).lineTo(92.72835, 114.16537);

        g.draw(shape);

        // _0_0_0_25
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(218.7638, 97.334656);
        ((GeneralPath) shape).lineTo(213.87402, 114.16537);

        g.draw(shape);

        // _0_0_0_26
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(109.59449, 100.45276);
        ((GeneralPath) shape).lineTo(118.84252, 111.11812);
        ((GeneralPath) shape).lineTo(189.1063, 111.11812);
        ((GeneralPath) shape).lineTo(199.02756, 100.45276);

        g.draw(shape);

        // _0_0_0_27
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(106.54725, 100.45276);
        ((GeneralPath) shape).lineTo(114.980316, 114.16536);

        g.draw(shape);

        // _0_0_0_28
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(201.57875, 101.19686);
        ((GeneralPath) shape).lineTo(193.8189, 114.16536);

        g.draw(shape);

        // _0_0_0_29
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(34.759842, 63.1063);
        ((GeneralPath) shape).curveTo(34.759842, 63.1063, 49.039368, 26.114174, 72.67323, 7.795273);

        g.draw(shape);

        // _0_0_0_30
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(274.1457, 63.1063);
        ((GeneralPath) shape).curveTo(274.1457, 63.1063, 259.83072, 26.114174, 236.2323, 7.795273);

        g.draw(shape);

        // _0_0_0_31
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(271.9134, 57.89764);
        ((GeneralPath) shape).lineTo(284.4567, 57.89764);
        ((GeneralPath) shape).lineTo(284.4567, 61.759846);

        g.draw(shape);

        // _0_0_0_32
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(36.425198, 59.17323);
        ((GeneralPath) shape).lineTo(24.661419, 59.17323);
        ((GeneralPath) shape).lineTo(24.661419, 61.759846);

        g.draw(shape);

        // _0_0_0_33
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(69.05906, 17.858269);
        ((GeneralPath) shape).curveTo(69.05906, 17.858269, 92.72835, 11.799213, 154.31104, 11.728347);
        ((GeneralPath) shape).curveTo(213.12994, 11.657476, 240.80316, 18.637796, 240.80316, 18.637796);

        g.setStroke(new BasicStroke(2.019685f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_34
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(26.255905, 138.61418);
        ((GeneralPath) shape).lineTo(283.07483, 138.61418);

        g.setStroke(new BasicStroke(0.992126f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_35
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(29.480316, 158.6693);
        ((GeneralPath) shape).lineTo(279.8504, 158.6693);

        g.draw(shape);

        // _0_0_0_36
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(62.893703, 64.09843);
        ((GeneralPath) shape).lineTo(62.893703, 56.26772);
        ((GeneralPath) shape).curveTo(62.893703, 56.26772, 62.858273, 49.67717, 71.114174, 49.67717);
        ((GeneralPath) shape).curveTo(79.37008, 49.67717, 124.086624, 49.46457, 124.086624, 49.46457);
        ((GeneralPath) shape).curveTo(124.086624, 49.46457, 129.50789, 49.35827, 129.72049, 57.33071);
        ((GeneralPath) shape).lineTo(129.72049, 60.980316);
        ((GeneralPath) shape).lineTo(62.893715, 64.09843);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_37
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(76.6063, 49.7126);
        ((GeneralPath) shape).curveTo(76.6063, 49.7126, 77.031494, 28.807087, 81.531494, 24.838583);
        ((GeneralPath) shape).lineTo(112.57086, 24.838583);
        ((GeneralPath) shape).curveTo(112.57086, 24.838583, 115.47637, 25.61811, 117.67322, 49.5);
        ((GeneralPath) shape).curveTo(76.74802, 49.5, 76.60629, 49.7126, 76.60629, 49.7126);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_38
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(82.24016, 49.46457);
        ((GeneralPath) shape).curveTo(82.24016, 49.287403, 87.69685, 41.208664, 96.625984, 41.208664);
        ((GeneralPath) shape).curveTo(105.555115, 41.208664, 110.232285, 49.46457, 110.232285, 49.46457);
        ((GeneralPath) shape).lineTo(82.24016, 49.46457);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_39
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(84.01181, 47.26772);
        ((GeneralPath) shape).curveTo(84.11811, 47.16142, 90.850395, 45.389767, 96.34252, 45.389767);
        ((GeneralPath) shape).curveTo(101.83465, 45.389767, 108.6378, 47.16142, 108.8504, 47.551186);

        g.draw(shape);

        // _0_0_0_40
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(246.9685, 64.09843);
        ((GeneralPath) shape).lineTo(246.9685, 56.26772);
        ((GeneralPath) shape).curveTo(246.9685, 56.26772, 247.03941, 49.67717, 238.78346, 49.67717);
        ((GeneralPath) shape).curveTo(230.49213, 49.67717, 185.77559, 49.46457, 185.77559, 49.46457);
        ((GeneralPath) shape).curveTo(185.77559, 49.46457, 180.38976, 49.35827, 180.14172, 57.33071);
        ((GeneralPath) shape).lineTo(180.14172, 60.980316);
        ((GeneralPath) shape).lineTo(246.96849, 64.09843);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_41
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(233.2559, 49.7126);
        ((GeneralPath) shape).curveTo(233.2559, 49.7126, 232.8307, 28.807087, 228.3307, 24.838583);
        ((GeneralPath) shape).lineTo(197.29134, 24.838583);
        ((GeneralPath) shape).curveTo(197.29134, 24.838583, 194.38583, 25.61811, 192.18898, 49.5);
        ((GeneralPath) shape).curveTo(233.11418, 49.5, 233.2559, 49.7126, 233.2559, 49.7126);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_42
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(227.62206, 49.46457);
        ((GeneralPath) shape).curveTo(227.62206, 49.287403, 222.20079, 41.208664, 213.23624, 41.208664);
        ((GeneralPath) shape).curveTo(204.3071, 41.208664, 199.66536, 49.46457, 199.66536, 49.46457);
        ((GeneralPath) shape).lineTo(227.62206, 49.46457);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_43
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(225.8504, 47.26772);
        ((GeneralPath) shape).curveTo(225.74411, 47.16142, 219.01183, 45.389767, 213.5197, 45.389767);
        ((GeneralPath) shape).curveTo(208.02757, 45.389767, 201.22443, 47.16142, 201.01183, 47.551186);

        g.draw(shape);

        // _0_0_0_44
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(69.05906, 63.850395);
        ((GeneralPath) shape).curveTo(69.05906, 63.850395, 78.732285, 46.913383, 97.08662, 46.913383);
        ((GeneralPath) shape).curveTo(115.44095, 46.913383, 124.01575, 61.26378, 124.01575, 61.26378);
        ((GeneralPath) shape).lineTo(120.649605, 61.26378);
        ((GeneralPath) shape).curveTo(120.649605, 61.26378, 113.84645, 49.925194, 97.08661, 49.712597);
        ((GeneralPath) shape).curveTo(80.433075, 49.500004, 72.67323, 63.602364, 72.67323, 63.602364);
        ((GeneralPath) shape).lineTo(69.05906, 63.850395);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_0_45
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(69.05906, 63.850395);
        ((GeneralPath) shape).curveTo(69.05906, 63.850395, 78.732285, 46.913383, 97.08662, 46.913383);
        ((GeneralPath) shape).curveTo(115.44095, 46.913383, 124.01575, 61.26378, 124.01575, 61.26378);
        ((GeneralPath) shape).lineTo(120.649605, 61.26378);
        ((GeneralPath) shape).curveTo(120.649605, 61.26378, 113.84645, 49.925194, 97.08661, 49.712597);
        ((GeneralPath) shape).curveTo(80.433075, 49.500004, 72.67323, 63.602364, 72.67323, 63.602364);
        ((GeneralPath) shape).lineTo(69.05906, 63.850395);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.draw(shape);

        // _0_0_0_46
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(154.31104, 16.26378);
        ((GeneralPath) shape).lineTo(141.90945, 16.26378);
        ((GeneralPath) shape).curveTo(141.90945, 16.26378, 137.72835, 16.440947, 137.19685, 20.480316);
        ((GeneralPath) shape).curveTo(136.70079, 24.519686, 134.32678, 27.389765, 140.91733, 27.389765);
        ((GeneralPath) shape).lineTo(167.88188, 27.389765);
        ((GeneralPath) shape).curveTo(167.88188, 27.389765, 174.93307, 29.090553, 171.88582, 21.11811);
        ((GeneralPath) shape).curveTo(170.43307, 17.326773, 167.70471, 16.263779, 166.18109, 16.263779);
        ((GeneralPath) shape).lineTo(154.311, 16.263779);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_47
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(147.3307, 23.350395);
        ((GeneralPath) shape).lineTo(162.63779, 23.350395);
        ((GeneralPath) shape).lineTo(162.63779, 13.925199);
        ((GeneralPath) shape).lineTo(147.3307, 13.925199);
        ((GeneralPath) shape).lineTo(147.3307, 23.350395);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        // _0_0_0_48
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(155.90552, 21.897638);
        ((GeneralPath) shape).lineTo(158.27953, 21.897638);
        ((GeneralPath) shape).lineTo(158.27953, 16.263779);
        ((GeneralPath) shape).lineTo(155.90552, 16.263779);
        ((GeneralPath) shape).lineTo(155.90552, 21.897638);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_0_49
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(150.8386, 21.897638);
        ((GeneralPath) shape).lineTo(153.2126, 21.897638);
        ((GeneralPath) shape).lineTo(153.2126, 16.263779);
        ((GeneralPath) shape).lineTo(150.8386, 16.263779);
        ((GeneralPath) shape).lineTo(150.8386, 21.897638);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        // _0_0_0_50
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(150.8386, 13.925198);
        ((GeneralPath) shape).lineTo(158.27953, 13.925198);
        ((GeneralPath) shape).lineTo(158.27953, 11.728347);
        ((GeneralPath) shape).lineTo(150.8386, 11.728347);
        ((GeneralPath) shape).lineTo(150.8386, 13.925198);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.setStroke(new BasicStroke(2.019685f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_51
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(97.22835, 158.84647);
        ((GeneralPath) shape).lineTo(210.50787, 158.84647);
        ((GeneralPath) shape).lineTo(210.50787, 138.61418);
        ((GeneralPath) shape).lineTo(97.22834, 138.61418);
        ((GeneralPath) shape).lineTo(97.22834, 158.84647);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        // _0_0_0_52
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(217.24016, 138.61418);
        ((GeneralPath) shape).lineTo(217.24016, 153.92126);
        ((GeneralPath) shape).curveTo(217.24016, 153.92126, 217.063, 155.09056, 220.07481, 155.09056);
        ((GeneralPath) shape).curveTo(223.12206, 155.09056, 261.7441, 155.58662, 266.8819, 148.00394);
        ((GeneralPath) shape).curveTo(266.8819, 140.59842, 267.1299, 138.61418, 266.8819, 138.61418);

        g.setStroke(new BasicStroke(0.992126f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_53
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(244.84253, 139.57088);
        ((GeneralPath) shape).lineTo(244.84253, 150.69687);
        ((GeneralPath) shape).curveTo(244.84253, 150.69687, 261.03543, 149.563, 261.46063, 146.83466);
        ((GeneralPath) shape).lineTo(261.70865, 138.61418);

        g.draw(shape);

        // _0_0_0_54
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(264.04724, 127.45276);
        ((GeneralPath) shape).curveTo(264.04724, 130.14568, 261.88583, 132.34253, 259.15747, 132.34253);
        ((GeneralPath) shape).lineTo(229.53542, 132.34253);
        ((GeneralPath) shape).curveTo(226.8425, 132.34253, 224.64566, 130.14568, 224.64566, 127.45276);
        ((GeneralPath) shape).lineTo(224.64566, 127.45276);
        ((GeneralPath) shape).curveTo(224.64566, 124.75985, 226.84251, 122.562996, 229.53542, 122.562996);
        ((GeneralPath) shape).lineTo(259.15747, 122.562996);
        ((GeneralPath) shape).curveTo(261.8858, 122.562996, 264.04724, 124.75985, 264.04724, 127.45276);
        ((GeneralPath) shape).lineTo(264.04724, 127.45276);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_55
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(84.862206, 127.45276);
        ((GeneralPath) shape).curveTo(84.862206, 130.14568, 82.70079, 132.34253, 80.00787, 132.34253);
        ((GeneralPath) shape).lineTo(50.350395, 132.34253);
        ((GeneralPath) shape).curveTo(47.657482, 132.34253, 45.460632, 130.14568, 45.460632, 127.45276);
        ((GeneralPath) shape).lineTo(45.460632, 127.45276);
        ((GeneralPath) shape).curveTo(45.460632, 124.75985, 47.657482, 122.562996, 50.350395, 122.562996);
        ((GeneralPath) shape).lineTo(80.00787, 122.562996);
        ((GeneralPath) shape).curveTo(82.70079, 122.562996, 84.862206, 124.75985, 84.862206, 127.45276);
        ((GeneralPath) shape).lineTo(84.862206, 127.45276);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_56
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(242.11418, 138.61418);
        ((GeneralPath) shape).lineTo(242.11418, 154.45276);

        g.draw(shape);

        // _0_0_0_57
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(92.05512, 138.61418);
        ((GeneralPath) shape).lineTo(92.05512, 153.92126);
        ((GeneralPath) shape).curveTo(92.05512, 153.92126, 92.232285, 155.09056, 89.18504, 155.09056);
        ((GeneralPath) shape).curveTo(86.17323, 155.09056, 47.51575, 155.58662, 42.413387, 148.00394);
        ((GeneralPath) shape).curveTo(42.413387, 140.59842, 42.12992, 138.61418, 42.413387, 138.61418);

        g.draw(shape);

        // _0_0_0_58
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(64.41733, 139.57088);
        ((GeneralPath) shape).lineTo(64.41733, 150.69687);
        ((GeneralPath) shape).curveTo(64.41733, 150.69687, 48.25985, 149.563, 47.83465, 146.83466);
        ((GeneralPath) shape).lineTo(47.586617, 138.61418);

        g.draw(shape);

        // _0_0_0_59
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(67.18111, 138.61418);
        ((GeneralPath) shape).lineTo(67.18111, 154.45276);

        g.draw(shape);

        // _0_0_0_60
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(65.16142, 173.44489);
        ((GeneralPath) shape).lineTo(78.23623, 173.44489);
        ((GeneralPath) shape).curveTo(78.23623, 173.44489, 98.610245, 165.36615, 149.49213, 165.36615);

        g.draw(shape);

        // _0_0_0_61
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(244.34647, 173.44489);
        ((GeneralPath) shape).lineTo(231.27167, 173.44489);
        ((GeneralPath) shape).curveTo(231.27167, 173.44489, 210.89764, 165.36615, 160.01575, 165.36615);

        g.draw(shape);

        // _0_0_0_62
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(149.49213, 167.74017);
        ((GeneralPath) shape).lineTo(160.01575, 167.74017);
        ((GeneralPath) shape).lineTo(160.01575, 163.94884);
        ((GeneralPath) shape).lineTo(149.49213, 163.94884);
        ((GeneralPath) shape).lineTo(149.49213, 167.74017);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_63
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(225.49606, 163.94884);
        ((GeneralPath) shape).curveTo(225.49606, 163.94884, 230.0315, 165.68506, 232.05118, 167.74017);
        ((GeneralPath) shape).curveTo(234.07086, 169.75986, 236.23228, 169.40552, 236.23228, 169.40552);
        ((GeneralPath) shape).lineTo(236.23228, 163.94882);
        ((GeneralPath) shape).lineTo(238.78346, 163.94882);
        ((GeneralPath) shape).lineTo(238.78346, 166.67715);
        ((GeneralPath) shape).lineTo(244.34645, 167.74016);

        g.draw(shape);

        // _0_0_0_64
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(84.15355, 163.94884);
        ((GeneralPath) shape).curveTo(84.15355, 163.94884, 79.61812, 165.68506, 77.59843, 167.74017);
        ((GeneralPath) shape).curveTo(75.57874, 169.75986, 73.41733, 169.40552, 73.41733, 169.40552);
        ((GeneralPath) shape).lineTo(73.41733, 163.94882);
        ((GeneralPath) shape).lineTo(70.86615, 163.94882);
        ((GeneralPath) shape).lineTo(70.86615, 166.67715);
        ((GeneralPath) shape).lineTo(65.303154, 167.74016);

        g.draw(shape);

        // _0_0_0_65
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(103.5, 149.52757);
        ((GeneralPath) shape).lineTo(203.06693, 149.52757);
        ((GeneralPath) shape).lineTo(203.06693, 146.83466);
        ((GeneralPath) shape).lineTo(103.49999, 146.83466);
        ((GeneralPath) shape).lineTo(103.49999, 149.52757);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_0_66
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(129.72047, 142.12206);
        ((GeneralPath) shape).lineTo(180.8504, 142.12206);
        ((GeneralPath) shape).lineTo(180.8504, 117.8504);
        ((GeneralPath) shape).lineTo(129.72049, 117.8504);
        ((GeneralPath) shape).lineTo(129.72049, 142.12206);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        // _0_0_0_67
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(129.72047, 142.12206);
        ((GeneralPath) shape).lineTo(180.8504, 142.12206);
        ((GeneralPath) shape).lineTo(180.8504, 117.8504);
        ((GeneralPath) shape).lineTo(129.72049, 117.8504);
        ((GeneralPath) shape).lineTo(129.72049, 142.12206);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.setStroke(new BasicStroke(2.019685f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_68
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(110.76378, 101.19686);
        ((GeneralPath) shape).lineTo(197.6811, 101.19686);

        g.draw(shape);

        // _0_0_0_69
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(118.84252, 111.11812);
        ((GeneralPath) shape).lineTo(189.1063, 111.11812);

        g.draw(shape);

        g.setTransform(transformations.poll()); // _0_0_0

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
        return 312;
    }

    @Override
    public int getShapeHeight() {
        return 192;
    }
}

