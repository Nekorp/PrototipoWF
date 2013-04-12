package org.nekorp.workflow.desktop.view.resource.imp;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.geom.*;
import org.nekorp.workflow.desktop.view.resource.ShapeView;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
@org.springframework.stereotype.Component("autoCuatroFrontView")
public class AutoCuatroFrontView extends ShapeView {

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
        g.transform(new AffineTransform(1, 0, 0, 1, 0, 0.40002045f));

        // _0_0_0

        // _0_0_0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(25.334284, 58.92538);
        ((GeneralPath) shape).curveTo(25.940847, 56.41754, 26.315065, 54.457348, 26.373701, 53.585697);
        ((GeneralPath) shape).curveTo(26.41386, 52.98856, 25.910397, 52.764297, 25.729692, 52.645226);
        ((GeneralPath) shape).curveTo(24.475508, 51.56843, 12.759215, 50.68037, 5.8581104, 52.789436);
        ((GeneralPath) shape).curveTo(3.9391413, 53.375896, 2.7723682, 54.09534, 1.9395182, 54.813995);
        ((GeneralPath) shape).curveTo(0.9371941, 55.67889, 0.65905595, 56.69904, 0.51897657, 58.42478);
        ((GeneralPath) shape).curveTo(0.29094607, 60.981422, 0.6564114, 65.547905, 0.8329206, 66.39848);
        ((GeneralPath) shape).curveTo(1.0513078, 67.461235, 2.9849863, 68.30153, 6.198816, 68.31053);
        ((GeneralPath) shape).curveTo(10.518539, 68.32267, 17.030746, 68.3318, 22.930504, 68.2773);
        ((GeneralPath) shape).lineTo(23.623762, 65.722626);
        ((GeneralPath) shape).lineTo(27.397293, 65.70125);
        ((GeneralPath) shape).curveTo(21.797058, 77.73492, 19.910784, 86.61714, 20.005985, 94.72102);
        ((GeneralPath) shape).curveTo(17.575365, 108.08831, 18.80938, 122.901566, 21.657692, 136.58035);
        ((GeneralPath) shape).curveTo(24.781042, 152.55382, 32.12507, 176.65184, 46.966972, 165.45995);
        ((GeneralPath) shape).curveTo(47.988625, 164.68953, 49.25534, 164.03401, 50.653137, 164.13356);
        ((GeneralPath) shape).lineTo(217.32944, 164.11485);
        ((GeneralPath) shape).curveTo(218.53587, 164.10686, 219.74162, 164.86751, 221.7369, 166.20164);
        ((GeneralPath) shape).curveTo(227.75974, 170.22882, 238.77878, 171.82103, 245.65427, 137.90387);
        ((GeneralPath) shape).curveTo(248.52039, 123.47155, 250.1296, 109.927734, 247.55493, 94.65152);
        ((GeneralPath) shape).curveTo(247.66295, 82.61936, 243.75403, 73.927795, 240.18434, 65.71329);
        ((GeneralPath) shape).lineTo(243.73763, 65.70369);
        ((GeneralPath) shape).lineTo(244.41385, 68.29407);
        ((GeneralPath) shape).lineTo(261.77997, 68.29407);
        ((GeneralPath) shape).curveTo(264.2378, 68.15183, 266.39465, 67.60392, 266.55533, 66.142);
        ((GeneralPath) shape).curveTo(266.93005, 62.793007, 267.02127, 59.703827, 266.6611, 57.177032);
        ((GeneralPath) shape).curveTo(266.47897, 56.06624, 265.96826, 55.19474, 264.8796, 54.38065);
        ((GeneralPath) shape).curveTo(263.0093, 52.981983, 259.8985, 52.236767, 257.01434, 51.866116);
        ((GeneralPath) shape).curveTo(252.45853, 51.280636, 246.12868, 51.477463, 243.10536, 52.0976);
        ((GeneralPath) shape).curveTo(241.69742, 52.486156, 240.99564, 52.81777, 240.94893, 53.575615);
        ((GeneralPath) shape).curveTo(241.18182, 55.30273, 241.57997, 57.24202, 242.00623, 58.930386);
        ((GeneralPath) shape).lineTo(237.16469, 58.914967);
        ((GeneralPath) shape).curveTo(230.82082, 43.369225, 223.67256, 26.038721, 214.39137, 12.25612);
        ((GeneralPath) shape).curveTo(212.37349, 9.366981, 209.43163, 5.46558, 206.91515, 3.9219024);
        ((GeneralPath) shape).curveTo(206.00339, 3.3625977, 205.0111, 3.3423524, 203.52048, 3.0966945);
        ((GeneralPath) shape).curveTo(192.94824, 1.9157637, 153.77461, 0.9624963, 134.76984, 0.80563104);
        ((GeneralPath) shape).curveTo(121.48511, 0.6959818, 70.54741, 2.0277324, 63.452168, 3.1727388);
        ((GeneralPath) shape).curveTo(62.089813, 3.4107223, 61.490658, 3.3908145, 60.477192, 4.023523);
        ((GeneralPath) shape).curveTo(49.535393, 12.450799, 38.16786, 40.114075, 30.441137, 58.901657);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.fill(shape);
        this.autoShape = shape;
        // _0_0_0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(133.79123, 95.287964);
        ((GeneralPath) shape).lineTo(81.74867, 95.287964);
        ((GeneralPath) shape).lineTo(85.10597, 110.638016);
        ((GeneralPath) shape).curveTo(85.10597, 110.638016, 58.965576, 110.638016, 51.051907, 110.638016);
        ((GeneralPath) shape).curveTo(43.137817, 110.638016, 42.41687, 104.642685, 42.17781, 105.36195);
        ((GeneralPath) shape).curveTo(41.937496, 106.08164, 42.897503, 92.170975, 42.897503, 92.170975);
        ((GeneralPath) shape).lineTo(37.140774, 62.430454);
        ((GeneralPath) shape).curveTo(37.381214, 61.71453, 230.43958, 62.430454, 230.43958, 62.430454);
        ((GeneralPath) shape).lineTo(224.68456, 92.17098);
        ((GeneralPath) shape).curveTo(224.68456, 92.17098, 225.64288, 106.08165, 225.40257, 105.36196);
        ((GeneralPath) shape).curveTo(225.16393, 104.64269, 224.44257, 110.63802, 216.53058, 110.63802);
        ((GeneralPath) shape).curveTo(208.61649, 110.63802, 182.47609, 110.63802, 182.47609, 110.63802);
        ((GeneralPath) shape).lineTo(185.83173, 95.28797);
        ((GeneralPath) shape).lineTo(133.78958, 95.28797);

        g.setPaint(new Color(0x1E1D1F));
        g.setStroke(new BasicStroke(0.87222433f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(133.78957, 11.591555);
        ((GeneralPath) shape).curveTo(133.78957, 11.591555, 203.57782, 13.7518835, 206.93555, 14.467807);
        ((GeneralPath) shape).curveTo(210.29285, 15.187916, 227.08061, 53.797512, 230.43959, 62.430454);
        ((GeneralPath) shape).curveTo(233.31458, 62.911922, 235.23627, 60.994423, 240.75098, 73.94383);
        ((GeneralPath) shape).curveTo(246.26567, 86.89492, 253.70207, 103.44195, 244.58598, 142.77417);
        ((GeneralPath) shape).curveTo(240.51067, 159.32288, 235.23462, 167.4756, 228.9973, 168.19528);
        ((GeneralPath) shape).curveTo(222.7629, 168.91455, 220.60593, 164.11787, 217.24654, 164.11787);
        ((GeneralPath) shape).curveTo(213.88924, 164.11787, 133.78961, 164.11787, 133.78961, 164.11787);
        ((GeneralPath) shape).curveTo(133.78961, 164.11787, 53.689575, 164.11787, 50.332275, 164.11787);
        ((GeneralPath) shape).curveTo(46.974556, 164.11787, 44.8159, 168.91455, 38.581635, 168.19528);
        ((GeneralPath) shape).curveTo(32.34578, 167.47559, 27.06993, 159.32286, 22.992811, 142.77417);
        ((GeneralPath) shape).curveTo(13.878569, 103.441956, 21.31324, 86.89492, 26.829489, 73.94383);
        ((GeneralPath) shape).curveTo(32.344147, 60.99442, 34.26412, 62.911922, 37.14083, 62.43045);
        ((GeneralPath) shape).curveTo(40.498383, 53.79751, 57.287613, 15.187912, 60.645332, 14.467804);
        ((GeneralPath) shape).curveTo(64.00263, 13.751881, 133.78961, 11.591552, 133.78961, 11.591552);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(247.56895, 94.74831);
        ((GeneralPath) shape).curveTo(247.91269, 78.61952, 239.31198, 65.07226, 236.67395, 57.636703);
        ((GeneralPath) shape).curveTo(219.1665, 13.747697, 209.09418, 4.8761144, 206.45615, 3.6745362);
        ((GeneralPath) shape).curveTo(201.65947, 1.5183942, 133.78957, 0.79828465, 133.78957, 0.79828465);
        ((GeneralPath) shape).curveTo(133.78957, 0.79828465, 65.92092, 1.5183942, 61.124237, 3.6745362);
        ((GeneralPath) shape).curveTo(58.486206, 4.8761144, 48.41388, 13.747697, 30.906551, 57.6367);
        ((GeneralPath) shape).curveTo(28.268606, 65.07225, 19.666311, 78.61952, 20.009745, 94.74831);

        g.draw(shape);

        // _0_0_0_4
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(35.462837, 62.112267);
        ((GeneralPath) shape).curveTo(45.054485, 27.496765, 63.42691, 3.1846943, 63.42691, 3.1846943);
        ((GeneralPath) shape).curveTo(63.42691, 3.1846943, 60.581223, 10.046669, 60.133247, 14.777621);

        g.draw(shape);

        // _0_0_0_5
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(231.48624, 62.112267);
        ((GeneralPath) shape).curveTo(221.89287, 27.496765, 203.52211, 3.1846962, 203.52211, 3.1846962);
        ((GeneralPath) shape).curveTo(203.52211, 3.1846962, 206.3678, 10.046671, 206.81578, 14.777623);

        g.draw(shape);

        // _0_0_0_6
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(18.70957, 115.43471);
        ((GeneralPath) shape).curveTo(18.70957, 115.43471, 22.752357, 107.99832, 26.589035, 107.759674);
        ((GeneralPath) shape).curveTo(30.427385, 107.521034, 43.738194, 107.759674, 43.738194, 107.759674);

        g.draw(shape);

        // _0_0_0_7
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(169.26668, 117.11273);
        ((GeneralPath) shape).curveTo(169.50533, 122.629105, 170.67967, 126.94641, 170.67967, 126.94641);
        ((GeneralPath) shape).curveTo(170.67967, 126.94641, 231.59424, 126.94641, 239.26761, 126.94641);
        ((GeneralPath) shape).curveTo(244.78398, 120.47003, 240.22762, 117.11273, 240.22762, 117.11273);
        ((GeneralPath) shape).lineTo(133.2662, 117.11273);
        ((GeneralPath) shape).lineTo(133.78955, 117.11273);
        ((GeneralPath) shape).lineTo(26.829422, 117.11273);
        ((GeneralPath) shape).curveTo(26.829422, 117.11273, 22.273138, 120.47003, 27.789429, 126.94641);
        ((GeneralPath) shape).curveTo(35.462826, 126.94641, 96.37736, 126.94641, 96.37736, 126.94641);
        ((GeneralPath) shape).curveTo(96.37736, 126.94641, 97.55005, 122.629105, 97.79037, 117.11273);

        g.draw(shape);

        // _0_0_0_8
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(67.10658, 62.129013);
        ((GeneralPath) shape).curveTo(67.10658, 62.129013, 79.568665, 79.39992, 81.74867, 95.287964);

        g.draw(shape);

        // _0_0_0_9
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(42.897507, 92.17098);
        ((GeneralPath) shape).lineTo(81.07797, 92.17098);

        g.draw(shape);

        // _0_0_0_10
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(249.023, 115.43471);
        ((GeneralPath) shape).curveTo(249.023, 115.43471, 244.98033, 107.99832, 241.14366, 107.759674);
        ((GeneralPath) shape).curveTo(237.30698, 107.521034, 223.9946, 107.759674, 223.9946, 107.759674);

        g.draw(shape);

        // _0_0_0_11
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(200.62576, 62.129013);
        ((GeneralPath) shape).curveTo(200.62576, 62.129013, 188.16579, 79.39992, 185.98535, 95.287964);

        g.draw(shape);

        // _0_0_0_12
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(224.83485, 92.17098);
        ((GeneralPath) shape).lineTo(186.65607, 92.17098);

        g.draw(shape);

        // _0_0_0_13
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(178.27475, 95.287964);
        ((GeneralPath) shape).curveTo(178.27475, 95.287964, 176.271, 103.10534, 174.34973, 108.936966);
        ((GeneralPath) shape).curveTo(172.76968, 113.73533, 170.86098, 113.73533, 170.86098, 113.73533);
        ((GeneralPath) shape).lineTo(132.74289, 113.73533);
        ((GeneralPath) shape).lineTo(133.79123, 113.73533);
        ((GeneralPath) shape).lineTo(95.672745, 113.73533);
        ((GeneralPath) shape).curveTo(95.672745, 113.73533, 93.76404, 113.73533, 92.1844, 108.936966);
        ((GeneralPath) shape).curveTo(90.26104, 103.10534, 88.259384, 95.287964, 88.259384, 95.287964);

        g.draw(shape);

        // _0_0_0_14
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(89.145706, 98.632286);
        ((GeneralPath) shape).lineTo(177.388, 98.632286);
        ((GeneralPath) shape).lineTo(176.57536, 101.56464);
        ((GeneralPath) shape).lineTo(90.197395, 101.56464);
        ((GeneralPath) shape).lineTo(89.1457, 98.632286);

        g.fill(shape);

        // _0_0_0_15
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(90.414696, 103.04463);
        ((GeneralPath) shape).lineTo(176.15335, 103.04463);
        ((GeneralPath) shape).lineTo(175.28503, 105.97866);
        ((GeneralPath) shape).lineTo(91.29766, 105.97866);
        ((GeneralPath) shape).lineTo(90.41469, 103.04463);

        g.fill(shape);

        // _0_0_0_16
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(91.8277, 107.65334);
        ((GeneralPath) shape).lineTo(174.76505, 107.65334);
        ((GeneralPath) shape).lineTo(173.71669, 110.587364);
        ((GeneralPath) shape).lineTo(92.91204, 110.587364);
        ((GeneralPath) shape).lineTo(91.82769, 107.65334);

        g.fill(shape);

        // _0_0_0_17
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(141.91759, 102.49199);
        ((GeneralPath) shape).curveTo(141.91759, 107.269, 138.04323, 111.14168, 133.26622, 111.14168);
        ((GeneralPath) shape).curveTo(128.49046, 111.14168, 124.61612, 107.269, 124.61612, 102.49199);
        ((GeneralPath) shape).curveTo(124.61612, 97.71331, 128.49046, 93.8423, 133.26622, 93.8423);
        ((GeneralPath) shape).curveTo(138.04323, 93.8423, 141.91759, 97.71331, 141.91759, 102.49199);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_0_18
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(141.91759, 102.49199);
        ((GeneralPath) shape).curveTo(141.91759, 107.269, 138.04323, 111.14168, 133.26622, 111.14168);
        ((GeneralPath) shape).curveTo(128.49046, 111.14168, 124.61612, 107.269, 124.61612, 102.49199);
        ((GeneralPath) shape).curveTo(124.61612, 97.71331, 128.49046, 93.8423, 133.26622, 93.8423);
        ((GeneralPath) shape).curveTo(138.04323, 93.8423, 141.91759, 97.71331, 141.91759, 102.49199);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x1E1D1F));
        g.setStroke(new BasicStroke(1.4618685f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_19
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(48.035824, 139.21884);
        ((GeneralPath) shape).curveTo(48.035824, 139.21884, 44.32349, 140.28017, 45.120216, 145.2305);
        ((GeneralPath) shape).curveTo(45.120216, 145.2305, 45.82651, 152.74516, 55.197563, 152.74516);
        ((GeneralPath) shape).curveTo(64.56694, 152.74516, 90.41469, 152.74516, 90.41469, 152.74516);
        ((GeneralPath) shape).lineTo(87.81602, 139.21883);
        ((GeneralPath) shape).lineTo(48.035824, 139.21883);
        ((GeneralPath) shape).closePath();

        g.setStroke(new BasicStroke(0.87222433f, 0, 0, 4));
        g.draw(shape);

        // _0_0_0_20
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(97.01039, 139.21884);
        ((GeneralPath) shape).curveTo(97.01039, 139.21884, 94.18103, 139.83847, 94.00436, 142.04778);
        ((GeneralPath) shape).curveTo(93.827675, 144.2575, 94.97902, 152.74516, 100.01643, 152.74516);
        ((GeneralPath) shape).curveTo(105.05511, 152.74516, 133.26622, 152.74516, 133.26622, 152.74516);
        ((GeneralPath) shape).lineTo(133.33952, 152.74516);
        ((GeneralPath) shape).curveTo(133.33952, 152.74516, 161.55066, 152.74516, 166.58932, 152.74516);
        ((GeneralPath) shape).curveTo(171.62842, 152.74516, 172.77809, 144.2575, 172.60141, 142.04778);
        ((GeneralPath) shape).curveTo(172.42473, 139.83847, 169.59537, 139.21884, 169.59537, 139.21884);
        ((GeneralPath) shape).lineTo(97.01043, 139.21884);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_21
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(45.007175, 144.34586);
        ((GeneralPath) shape).lineTo(88.80072, 144.34586);

        g.draw(shape);

        // _0_0_0_22
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(46.237225, 148.36089);
        ((GeneralPath) shape).lineTo(89.57233, 148.36089);

        g.draw(shape);

        // _0_0_0_23
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(218.53055, 139.21884);
        ((GeneralPath) shape).curveTo(218.53055, 139.21884, 222.24455, 140.28017, 221.44992, 145.2305);
        ((GeneralPath) shape).curveTo(221.44992, 145.2305, 220.74321, 152.74516, 211.37215, 152.74516);
        ((GeneralPath) shape).curveTo(202.0011, 152.74516, 176.15335, 152.74516, 176.15335, 152.74516);
        ((GeneralPath) shape).lineTo(178.75203, 139.21883);
        ((GeneralPath) shape).lineTo(218.53055, 139.21883);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_24
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(221.56087, 144.34586);
        ((GeneralPath) shape).lineTo(177.76773, 144.34586);

        g.draw(shape);

        // _0_0_0_25
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(220.33124, 148.36089);
        ((GeneralPath) shape).lineTo(176.99571, 148.36089);

        g.draw(shape);

        // _0_0_0_26
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(172.14502, 144.34586);
        ((GeneralPath) shape).lineTo(94.35938, 144.34586);

        g.draw(shape);

        // _0_0_0_27
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(171.35834, 148.36089);
        ((GeneralPath) shape).lineTo(94.97901, 148.36089);

        g.draw(shape);

        // _0_0_0_28
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(122.64879, 139.21884);
        ((GeneralPath) shape).lineTo(122.64879, 152.74518);

        g.draw(shape);

        // _0_0_0_29
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(144.30693, 139.21884);
        ((GeneralPath) shape).lineTo(144.30693, 152.74518);

        g.draw(shape);

        // _0_0_0_30
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(26.768936, 154.90257);
        ((GeneralPath) shape).lineTo(240.81166, 154.90257);

        g.draw(shape);

        // _0_0_0_31
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(25.161333, 122.02873);
        ((GeneralPath) shape).curveTo(25.208723, 122.198715, 21.656618, 121.27471, 21.656618, 136.58417);
        ((GeneralPath) shape).lineTo(21.656618, 186.12895);
        ((GeneralPath) shape).curveTo(21.656618, 186.12895, 22.188118, 190.38304, 26.330631, 190.92606);
        ((GeneralPath) shape).curveTo(30.692318, 190.92606, 41.48609, 190.92606, 41.48609, 190.92606);
        ((GeneralPath) shape).curveTo(41.48609, 190.92606, 45.7545, 190.78372, 45.7545, 186.65732);
        ((GeneralPath) shape).curveTo(45.7545, 182.53302, 45.7545, 166.28491, 45.7545, 166.28491);

        g.draw(shape);

        // _0_0_0_32
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(242.15433, 122.02873);
        ((GeneralPath) shape).curveTo(242.10663, 122.198715, 245.659, 121.27471, 245.659, 136.58417);
        ((GeneralPath) shape).lineTo(245.659, 186.12895);
        ((GeneralPath) shape).curveTo(245.659, 186.12895, 245.12897, 190.38304, 240.98666, 190.92606);
        ((GeneralPath) shape).curveTo(236.62497, 190.92606, 225.8292, 190.92606, 225.8292, 190.92606);
        ((GeneralPath) shape).curveTo(225.8292, 190.92606, 221.56087, 190.78372, 221.56087, 186.65732);
        ((GeneralPath) shape).curveTo(221.56087, 182.53302, 221.56087, 166.28491, 221.56087, 166.28491);

        g.draw(shape);

        // _0_0_0_33
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(15.072389, 51.54508);
        ((GeneralPath) shape).curveTo(15.072389, 51.54508, 3.8893633, 51.457157, 1.161479, 55.74432);
        ((GeneralPath) shape).curveTo(-0.24825692, 58.23121, 0.83112454, 66.35756, 0.83112454, 66.35756);
        ((GeneralPath) shape).curveTo(0.83112454, 66.35756, 0.92270124, 68.30437, 6.468419, 68.30437);
        ((GeneralPath) shape).curveTo(12.014141, 68.30437, 22.925686, 68.30437, 22.925686, 68.30437);
        ((GeneralPath) shape).curveTo(22.925686, 68.30437, 26.373169, 55.710823, 26.373169, 53.462574);
        ((GeneralPath) shape).curveTo(26.373169, 51.21851, 15.072389, 51.545074, 15.072389, 51.545074);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_34
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(25.336294, 58.917828);
        ((GeneralPath) shape).lineTo(30.679214, 58.917828);

        g.draw(shape);

        // _0_0_0_35
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(23.619127, 65.717);
        ((GeneralPath) shape).lineTo(27.400204, 65.717);

        g.draw(shape);

        // _0_0_0_36
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(252.266, 51.54508);
        ((GeneralPath) shape).curveTo(252.266, 51.54508, 263.44907, 51.457157, 266.1754, 55.74432);
        ((GeneralPath) shape).curveTo(267.58508, 58.23121, 266.50574, 66.35756, 266.50574, 66.35756);
        ((GeneralPath) shape).curveTo(266.50574, 66.35756, 266.41574, 68.30437, 260.86838, 68.30437);
        ((GeneralPath) shape).curveTo(255.32437, 68.30437, 244.41261, 68.30437, 244.41261, 68.30437);
        ((GeneralPath) shape).curveTo(244.41261, 68.30437, 240.9653, 55.710823, 240.9653, 53.462574);
        ((GeneralPath) shape).curveTo(240.9653, 51.21851, 252.266, 51.545074, 252.266, 51.545074);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_37
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(242.00026, 58.917828);
        ((GeneralPath) shape).lineTo(236.65762, 58.917828);

        g.draw(shape);

        // _0_0_0_38
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(243.71764, 65.717);
        ((GeneralPath) shape).lineTo(239.93832, 65.717);

        g.draw(shape);

        // _0_0_0_39
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(72.33994, 30.582352);
        ((GeneralPath) shape).curveTo(73.40294, 27.450712, 74.68365, 25.348997, 77.573296, 25.348997);
        ((GeneralPath) shape).lineTo(99.81505, 25.348997);
        ((GeneralPath) shape).curveTo(102.706375, 25.348997, 103.88911, 27.325111, 105.04841, 30.582352);
        ((GeneralPath) shape).lineTo(108.53843, 43.376858);
        ((GeneralPath) shape).curveTo(108.53843, 46.26567, 106.19472, 48.61021, 103.30507, 48.61021);
        ((GeneralPath) shape).lineTo(74.084946, 48.61021);
        ((GeneralPath) shape).curveTo(71.1953, 48.61021, 68.85159, 46.265667, 68.85159, 43.376858);
        ((GeneralPath) shape).lineTo(72.339935, 30.582352);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_40
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(58.967255, 62.112267);
        ((GeneralPath) shape).curveTo(58.967255, 62.112267, 56.93085, 55.007465, 88.69438, 55.007465);
        ((GeneralPath) shape).curveTo(120.45916, 55.007465, 119.29484, 62.112267, 119.29484, 62.112267);

        g.draw(shape);

        // _0_0_0_41
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(78.30094, 55.007465);
        ((GeneralPath) shape).lineTo(81.07789, 55.007465);
        ((GeneralPath) shape).lineTo(81.07789, 48.611343);
        ((GeneralPath) shape).lineTo(78.30094, 48.611343);
        ((GeneralPath) shape).lineTo(78.30094, 55.007465);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_42
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(97.47469, 55.007465);
        ((GeneralPath) shape).lineTo(100.25164, 55.007465);
        ((GeneralPath) shape).lineTo(100.25164, 48.611343);
        ((GeneralPath) shape).lineTo(97.47469, 48.611343);
        ((GeneralPath) shape).lineTo(97.47469, 55.007465);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_43
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(160.60693, 30.582352);
        ((GeneralPath) shape).curveTo(161.66994, 27.450712, 162.95064, 25.348997, 165.84029, 25.348997);
        ((GeneralPath) shape).lineTo(188.08203, 25.348997);
        ((GeneralPath) shape).curveTo(190.9721, 25.348997, 192.15442, 27.325111, 193.31538, 30.582352);
        ((GeneralPath) shape).lineTo(196.80373, 43.376858);
        ((GeneralPath) shape).curveTo(196.80373, 46.26567, 194.46211, 48.61021, 191.57037, 48.61021);
        ((GeneralPath) shape).lineTo(162.35193, 48.61021);
        ((GeneralPath) shape).curveTo(159.46228, 48.61021, 157.11858, 46.265667, 157.11858, 43.376858);
        ((GeneralPath) shape).lineTo(160.60692, 30.582352);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_44
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(147.23425, 62.112267);
        ((GeneralPath) shape).curveTo(147.23425, 62.112267, 145.19827, 55.007465, 176.96138, 55.007465);
        ((GeneralPath) shape).curveTo(208.72617, 55.007465, 207.56184, 62.112267, 207.56184, 62.112267);

        g.draw(shape);

        // _0_0_0_45
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(166.5667, 55.007465);
        ((GeneralPath) shape).lineTo(169.34364, 55.007465);
        ((GeneralPath) shape).lineTo(169.34364, 48.611343);
        ((GeneralPath) shape).lineTo(166.5667, 48.611343);
        ((GeneralPath) shape).lineTo(166.5667, 55.007465);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_46
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(185.7417, 55.007465);
        ((GeneralPath) shape).lineTo(188.51701, 55.007465);
        ((GeneralPath) shape).lineTo(188.51701, 48.611343);
        ((GeneralPath) shape).lineTo(185.7417, 48.611343);
        ((GeneralPath) shape).lineTo(185.7417, 55.007465);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_47
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(157.09096, 62.12064);
        ((GeneralPath) shape).curveTo(157.09096, 62.12064, 163.7796, 50.611446, 178.33168, 50.611446);
        ((GeneralPath) shape).curveTo(191.54443, 50.611446, 199.4288, 62.129013, 199.4288, 62.129013);
        ((GeneralPath) shape).lineTo(195.20612, 61.99504);
        ((GeneralPath) shape).curveTo(195.20612, 61.99504, 189.50972, 53.115086, 178.27475, 53.115086);
        ((GeneralPath) shape).curveTo(165.9793, 53.115086, 161.49493, 62.066216, 161.49493, 62.066216);
        ((GeneralPath) shape).lineTo(157.09096, 62.120644);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_0_48
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(157.09096, 62.12064);
        ((GeneralPath) shape).curveTo(157.09096, 62.12064, 163.7796, 50.611446, 178.33168, 50.611446);
        ((GeneralPath) shape).curveTo(191.54443, 50.611446, 199.4288, 62.129013, 199.4288, 62.129013);
        ((GeneralPath) shape).lineTo(195.20612, 61.99504);
        ((GeneralPath) shape).curveTo(195.20612, 61.99504, 189.50972, 53.115086, 178.27475, 53.115086);
        ((GeneralPath) shape).curveTo(165.9793, 53.115086, 161.49493, 62.066216, 161.49493, 62.066216);
        ((GeneralPath) shape).lineTo(157.09096, 62.120644);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x171516));
        g.setStroke(new BasicStroke(0.7457991f, 0, 0, 4));
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
        return 268;
    }

    @Override
    public int getShapeHeight() {
        return 192;
    }
}

