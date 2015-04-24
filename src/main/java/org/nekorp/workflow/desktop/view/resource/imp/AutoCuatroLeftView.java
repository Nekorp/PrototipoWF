package org.nekorp.workflow.desktop.view.resource.imp;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.geom.*;
import java.util.LinkedList;
import org.nekorp.workflow.desktop.view.resource.ShapeView;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
@org.springframework.stereotype.Component("autoCuatroLeftView")
public class AutoCuatroLeftView extends ShapeView {

    private java.util.List<Shape> autoShape;
    
    public AutoCuatroLeftView() {
        autoShape = new LinkedList<>();
    }
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

        // _0_0_0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(68.27412, 168.34183);
        ((GeneralPath) shape).curveTo(68.27412, 168.34183, 57.02157, 166.24731, 49.94973, 166.57095);
        ((GeneralPath) shape).curveTo(42.87373, 166.89084, 18.760416, 166.53516, 18.760416, 166.53516);
        ((GeneralPath) shape).curveTo(18.760416, 166.53516, 9.434738, 160.83609, 11.043374, 154.0571);
        ((GeneralPath) shape).curveTo(13.61586, 150.1734, 7.184229, 142.2646, 3.326748, 141.23419);
        ((GeneralPath) shape).curveTo(-0.53114915, 140.20586, -0.53114915, 120.900986, 3.326748, 114.95856);
        ((GeneralPath) shape).curveTo(3.9698675, 104.19482, 5.897152, 70.11312, 149.29662, 62.719723);
        ((GeneralPath) shape).curveTo(173.7319, 43.746387, 240.28622, 9.023652, 258.29446, 5.4877315);
        ((GeneralPath) shape).curveTo(270.83243, 2.272124, 357.6455, -2.87368, 417.12802, 4.2023206);
        ((GeneralPath) shape).curveTo(443.16486, 10.309063, 494.29013, 34.744354, 515.8343, 46.32137);
        ((GeneralPath) shape).curveTo(532.23267, 47.927094, 558.59814, 50.1776, 560.20386, 51.1427);
        ((GeneralPath) shape).curveTo(561.59326, 51.97884, 567.60016, 57.461597, 566.7308, 92.94227);
        ((GeneralPath) shape).curveTo(566.7724, 94.485596, 567.66675, 92.88694, 567.67926, 94.54758);
        ((GeneralPath) shape).curveTo(567.7126, 97.75195, 567.68726, 101.177216, 567.6003, 104.838356);
        ((GeneralPath) shape).curveTo(571.1362, 105.15867, 580.4627, 102.90816, 580.783, 117.05475);
        ((GeneralPath) shape).curveTo(581.10333, 131.20341, 571.7768, 151.13852, 571.13617, 151.7804);
        ((GeneralPath) shape).curveTo(570.49554, 152.42227, 515.51404, 148.88635, 497.83032, 161.1044);
        ((GeneralPath) shape).curveTo(497.83032, 147.60094, 496.22043, 99.05025, 446.38892, 98.406715);
        ((GeneralPath) shape).curveTo(394.30273, 101.30076, 398.48343, 155.31631, 397.51416, 164.64032);
        ((GeneralPath) shape).curveTo(391.7319, 164.64032, 166.66016, 164.64032, 166.66016, 164.64032);
        ((GeneralPath) shape).curveTo(166.66016, 164.64032, 174.38095, 98.72869, 115.21876, 98.72869);
        ((GeneralPath) shape).curveTo(61.20238, 98.72869, 68.274216, 168.34181, 68.274216, 168.34181);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.fill(shape);
        g.setPaint(new Color(0x1E1D1F));
        g.setStroke(new BasicStroke(0.8666457f, 0, 0, 4));
        g.draw(shape);
        this.autoShape.add(shape);

        // _0_0_0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(567.60016, 104.83836);
        ((GeneralPath) shape).curveTo(567.60016, 104.83836, 561.4934, 105.48023, 561.16895, 104.51679);
        ((GeneralPath) shape).curveTo(560.8528, 57.245285, 554.0971, 55.003098, 550.8815, 55.003098);
        ((GeneralPath) shape).curveTo(507.47702, 52.107803, 504.58173, 51.142704, 504.58173, 51.142704);
        ((GeneralPath) shape).curveTo(504.58173, 51.142704, 424.19986, 13.524673, 413.9124, 9.343967);
        ((GeneralPath) shape).curveTo(403.62497, 5.167422, 297.19794, 3.557538, 266.65173, 9.343967);
        ((GeneralPath) shape).curveTo(236.10971, 15.130397, 175.98657, 62.719723, 175.98657, 62.719723);
        ((GeneralPath) shape).curveTo(175.98657, 62.719723, 59.916878, 82.974724, 47.699234, 87.47574);
        ((GeneralPath) shape).curveTo(38.05366, 91.97509, 29.69308, 109.33979, 29.69308, 109.33979);
        ((GeneralPath) shape).curveTo(29.69308, 109.33979, 25.8356, 114.48268, 13.295563, 114.16237);
        ((GeneralPath) shape).curveTo(18.762096, 101.944305, 26.477474, 92.94227, 26.477474, 92.94227);
        ((GeneralPath) shape).lineTo(41.448143, 92.157295);

        g.draw(shape);

        // _0_0_0_2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(175.98656, 62.719723);
        ((GeneralPath) shape).curveTo(175.98656, 62.719723, 164.08923, 121.23421, 174.05637, 151.13686);
        ((GeneralPath) shape).curveTo(192.06044, 151.13686, 384.97607, 151.13686, 384.97607, 151.13686);
        ((GeneralPath) shape).curveTo(384.97607, 151.13686, 399.76038, 93.58539, 437.06226, 86.8322);
        ((GeneralPath) shape).curveTo(447.99448, 83.938156, 456.3559, 76.54309, 456.3559, 72.04166);
        ((GeneralPath) shape).curveTo(456.3559, 67.8622, 454.42154, 44.711487, 454.42154, 44.711487);
        ((GeneralPath) shape).curveTo(454.42154, 44.711487, 447.3497, 30.567806, 402.65982, 12.239258);
        ((GeneralPath) shape).curveTo(398.8036, 10.309062, 397.16876, 7.155853, 397.16876, 7.155853);

        g.draw(shape);

        // _0_0_0_3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(328.7092, 5.5834093);
        ((GeneralPath) shape).curveTo(328.7092, 5.5834093, 314.4407, 62.71972, 313.2177, 70.434685);
        ((GeneralPath) shape).curveTo(311.99054, 78.151726, 310.26, 111.9102, 313.2177, 151.13687);

        g.draw(shape);

        // _0_0_0_4
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(482.5883, 112.70016);
        ((GeneralPath) shape).curveTo(482.5883, 112.70016, 488.82397, 100.33567, 500.7213, 100.33567);
        ((GeneralPath) shape).curveTo(512.6228, 100.33567, 561.12317, 100.33567, 561.12317, 100.33567);

        g.draw(shape);

        // _0_0_0_5
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(580.3128, 112.70016);
        ((GeneralPath) shape).lineTo(507.79315, 112.70016);
        ((GeneralPath) shape).curveTo(507.79315, 112.70016, 503.2963, 112.87571, 503.6166, 117.69829);
        ((GeneralPath) shape).curveTo(503.94107, 122.52128, 509.40305, 125.73564, 509.40305, 125.73564);
        ((GeneralPath) shape).lineTo(580.3128, 125.73564);

        g.draw(shape);

        // _0_0_0_6
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(559.7088, 75.27889);
        ((GeneralPath) shape).lineTo(566.7307, 75.27889);

        g.draw(shape);

        // _0_0_0_7
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(560.9484, 93.249275);
        ((GeneralPath) shape).lineTo(566.7307, 92.942276);

        g.draw(shape);

        // _0_0_0_8
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(558.3277, 66.63211);
        ((GeneralPath) shape).curveTo(558.3277, 66.63211, 547.5785, 67.07597, 544.6874, 71.89897);
        ((GeneralPath) shape).curveTo(541.79626, 76.72155, 538.3435, 100.33567, 538.3435, 100.33567);

        g.draw(shape);

        // _0_0_0_9
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(173.73605, 119.53946);
        ((GeneralPath) shape).curveTo(173.73605, 117.81227, 175.13377, 116.41163, 176.86429, 116.41163);
        ((GeneralPath) shape).lineTo(389.88477, 116.41163);
        ((GeneralPath) shape).curveTo(391.6111, 116.41163, 393.013, 117.81227, 393.013, 119.53946);
        ((GeneralPath) shape).lineTo(393.013, 119.53946);
        ((GeneralPath) shape).curveTo(393.013, 121.26666, 391.6111, 122.666046, 389.88477, 122.666046);
        ((GeneralPath) shape).lineTo(176.86429, 122.666046);
        ((GeneralPath) shape).curveTo(175.13377, 122.666046, 173.73605, 121.266655, 173.73605, 119.53946);
        ((GeneralPath) shape).lineTo(173.73605, 119.53946);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_0_10
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(173.73605, 119.53946);
        ((GeneralPath) shape).curveTo(173.73605, 117.81227, 175.13377, 116.41163, 176.86429, 116.41163);
        ((GeneralPath) shape).lineTo(389.88477, 116.41163);
        ((GeneralPath) shape).curveTo(391.6111, 116.41163, 393.013, 117.81227, 393.013, 119.53946);
        ((GeneralPath) shape).lineTo(393.013, 119.53946);
        ((GeneralPath) shape).curveTo(393.013, 121.26666, 391.6111, 122.666046, 389.88477, 122.666046);
        ((GeneralPath) shape).lineTo(176.86429, 122.666046);
        ((GeneralPath) shape).curveTo(175.13377, 122.666046, 173.73605, 121.266655, 173.73605, 119.53946);
        ((GeneralPath) shape).lineTo(173.73605, 119.53946);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x1E1D1F));
        g.draw(shape);

        // _0_0_0_11
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(301.69894, 87.74239);
        ((GeneralPath) shape).curveTo(301.69894, 90.61356, 299.37357, 92.942276, 296.49905, 92.942276);
        ((GeneralPath) shape).lineTo(288.57443, 92.942276);
        ((GeneralPath) shape).curveTo(285.7041, 92.942276, 283.37454, 90.61356, 283.37454, 87.74239);
        ((GeneralPath) shape).lineTo(283.37454, 84.34583);
        ((GeneralPath) shape).curveTo(283.37454, 81.47466, 285.7041, 79.14595, 288.57443, 79.14595);
        ((GeneralPath) shape).lineTo(296.49905, 79.14595);
        ((GeneralPath) shape).curveTo(299.37354, 79.14595, 301.69894, 81.47466, 301.69894, 84.34583);
        ((GeneralPath) shape).lineTo(301.69894, 87.74239);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_12
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(279.51416, 86.04432);
        ((GeneralPath) shape).curveTo(279.51416, 84.167366, 281.03668, 82.64651, 282.9128, 82.64651);
        ((GeneralPath) shape).lineTo(304.7357, 82.64651);
        ((GeneralPath) shape).curveTo(306.61182, 82.64651, 308.13434, 84.167366, 308.13434, 86.04432);
        ((GeneralPath) shape).lineTo(308.13434, 86.04432);
        ((GeneralPath) shape).curveTo(308.13434, 87.92085, 306.61182, 89.44171, 304.7357, 89.44171);
        ((GeneralPath) shape).lineTo(282.9128, 89.44171);
        ((GeneralPath) shape).curveTo(281.0367, 89.44171, 279.51416, 87.92085, 279.51416, 86.04432);
        ((GeneralPath) shape).lineTo(279.51416, 86.04432);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_0_13
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(279.51416, 86.04432);
        ((GeneralPath) shape).curveTo(279.51416, 84.167366, 281.03668, 82.64651, 282.9128, 82.64651);
        ((GeneralPath) shape).lineTo(304.7357, 82.64651);
        ((GeneralPath) shape).curveTo(306.61182, 82.64651, 308.13434, 84.167366, 308.13434, 86.04432);
        ((GeneralPath) shape).lineTo(308.13434, 86.04432);
        ((GeneralPath) shape).curveTo(308.13434, 87.92085, 306.61182, 89.44171, 304.7357, 89.44171);
        ((GeneralPath) shape).lineTo(282.9128, 89.44171);
        ((GeneralPath) shape).curveTo(281.0367, 89.44171, 279.51416, 87.92085, 279.51416, 86.04432);
        ((GeneralPath) shape).lineTo(279.51416, 86.04432);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x1E1D1F));
        g.draw(shape);

        // _0_0_0_14
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(287.87973, 82.64651);
        ((GeneralPath) shape).lineTo(287.87973, 89.44171);

        g.draw(shape);

        // _0_0_0_15
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(422.59412, 83.87492);
        ((GeneralPath) shape).curveTo(422.59412, 86.74609, 420.2646, 89.07481, 417.39423, 89.07481);
        ((GeneralPath) shape).lineTo(409.46545, 89.07481);
        ((GeneralPath) shape).curveTo(406.59512, 89.07481, 404.26556, 86.74609, 404.26556, 83.87492);
        ((GeneralPath) shape).lineTo(404.26556, 80.478775);
        ((GeneralPath) shape).curveTo(404.26556, 77.60719, 406.5951, 75.27889, 409.46545, 75.27889);
        ((GeneralPath) shape).lineTo(417.39423, 75.27889);
        ((GeneralPath) shape).curveTo(420.26456, 75.27889, 422.59412, 77.60719, 422.59412, 80.478775);
        ((GeneralPath) shape).lineTo(422.59412, 83.87492);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_16
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(400.40933, 82.17685);
        ((GeneralPath) shape).curveTo(400.40933, 80.299904, 401.9277, 78.77904, 403.80383, 78.77904);
        ((GeneralPath) shape).lineTo(425.6267, 78.77904);
        ((GeneralPath) shape).curveTo(427.5028, 78.77904, 429.02536, 80.2999, 429.02536, 82.17685);
        ((GeneralPath) shape).lineTo(429.02536, 82.17685);
        ((GeneralPath) shape).curveTo(429.02536, 84.05172, 427.50284, 85.573, 425.6267, 85.573);
        ((GeneralPath) shape).lineTo(403.80383, 85.573);
        ((GeneralPath) shape).curveTo(401.9277, 85.573, 400.40933, 84.05172, 400.40933, 82.17685);
        ((GeneralPath) shape).lineTo(400.40933, 82.17685);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_0_17
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(400.40933, 82.17685);
        ((GeneralPath) shape).curveTo(400.40933, 80.299904, 401.9277, 78.77904, 403.80383, 78.77904);
        ((GeneralPath) shape).lineTo(425.6267, 78.77904);
        ((GeneralPath) shape).curveTo(427.5028, 78.77904, 429.02536, 80.2999, 429.02536, 82.17685);
        ((GeneralPath) shape).lineTo(429.02536, 82.17685);
        ((GeneralPath) shape).curveTo(429.02536, 84.05172, 427.50284, 85.573, 425.6267, 85.573);
        ((GeneralPath) shape).lineTo(403.80383, 85.573);
        ((GeneralPath) shape).curveTo(401.9277, 85.573, 400.40933, 84.05172, 400.40933, 82.17685);
        ((GeneralPath) shape).lineTo(400.40933, 82.17685);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x1E1D1F));
        g.draw(shape);

        // _0_0_0_18
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(408.7666, 78.77904);
        ((GeneralPath) shape).lineTo(408.7666, 85.573);

        g.draw(shape);

        // _0_0_0_19
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(166.99284, 157.56517);
        ((GeneralPath) shape).lineTo(174.05637, 151.13686);

        g.draw(shape);

        // _0_0_0_20
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(397.9758, 154.35123);
        ((GeneralPath) shape).lineTo(384.97607, 151.13687);

        g.draw(shape);

        // _0_0_0_21
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(397.71375, 160.46753);
        ((GeneralPath) shape).lineTo(166.99286, 160.46753);

        g.draw(shape);

        // _0_0_0_22
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(154.76688, 86.96864);
        ((GeneralPath) shape).curveTo(154.76688, 85.603775, 155.87341, 84.49558, 157.23787, 84.49558);
        ((GeneralPath) shape).lineTo(163.22397, 84.49558);
        ((GeneralPath) shape).curveTo(164.59258, 84.49558, 165.69496, 85.60378, 165.69496, 86.96864);
        ((GeneralPath) shape).lineTo(165.69496, 86.96864);
        ((GeneralPath) shape).curveTo(165.69496, 88.33351, 164.59258, 89.4417, 163.22397, 89.4417);
        ((GeneralPath) shape).lineTo(157.23787, 89.4417);
        ((GeneralPath) shape).curveTo(155.87341, 89.4417, 154.76688, 88.3335, 154.76688, 86.96864);
        ((GeneralPath) shape).lineTo(154.76688, 86.96864);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_23
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(80.94103, 114.17568);
        ((GeneralPath) shape).curveTo(80.94103, 114.17568, 76.63553, 109.65969, 67.31318, 109.65969);
        ((GeneralPath) shape).curveTo(57.986668, 109.65969, 29.693066, 109.65969, 29.693066, 109.65969);

        g.draw(shape);

        // _0_0_0_24
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(1.63824, 119.21957);
        ((GeneralPath) shape).lineTo(64.41788, 119.21957);
        ((GeneralPath) shape).curveTo(64.41788, 119.21957, 65.38297, 128.95125, 58.95176, 128.95125);
        ((GeneralPath) shape).curveTo(52.520546, 128.95125, 0.4339409, 128.95125, 0.4339409, 128.95125);

        g.draw(shape);

        // _0_0_0_25
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(1.3849016, 138.38425);
        ((GeneralPath) shape).lineTo(20.04749, 138.38425);
        ((GeneralPath) shape).curveTo(20.04749, 138.38425, 27.120993, 144.06169, 23.585073, 154.35121);

        g.draw(shape);

        // _0_0_0_26
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(11.043372, 154.05711);
        ((GeneralPath) shape).lineTo(68.24915, 154.05711);

        g.draw(shape);

        // _0_0_0_27
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(6.2840233, 142.93228);
        ((GeneralPath) shape).lineTo(23.328407, 142.93228);

        g.draw(shape);

        // _0_0_0_28
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(11.043372, 149.26657);
        ((GeneralPath) shape).lineTo(24.377953, 149.26657);

        g.draw(shape);

        // _0_0_0_29
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(12.978976, 138.38425);
        ((GeneralPath) shape).lineTo(12.978976, 154.05711);

        g.draw(shape);

        // _0_0_0_30
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(14.806839, 138.38425);
        ((GeneralPath) shape).lineTo(14.806839, 154.35121);

        g.draw(shape);

        // _0_0_0_31
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(320.1481, 11.594474);
        ((GeneralPath) shape).curveTo(320.1481, 11.594474, 306.19995, 60.435932, 299.7729, 69.791145);
        ((GeneralPath) shape).curveTo(283.69485, 70.434685, 189.80994, 75.27889, 189.80994, 75.27889);
        ((GeneralPath) shape).curveTo(189.80994, 75.27889, 182.09332, 69.791145, 191.41566, 62.074932);
        ((GeneralPath) shape).curveTo(200.74217, 54.358307, 252.18774, 16.09549, 275.65793, 14.169453);
        ((GeneralPath) shape).curveTo(299.1281, 12.239256, 320.14813, 11.594471, 320.14813, 11.594471);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_32
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(333.855, 11.594474);
        ((GeneralPath) shape).lineTo(323.56342, 68.82771);
        ((GeneralPath) shape).lineTo(441.56332, 65.29013);
        ((GeneralPath) shape).curveTo(441.56332, 65.29013, 455.71115, 57.57392, 446.70496, 45.356277);
        ((GeneralPath) shape).curveTo(431.92065, 33.77926, 412.62283, 15.775185, 366.00693, 11.594479);
        ((GeneralPath) shape).curveTo(339.96176, 11.594479, 333.855, 11.594479, 333.855, 11.594479);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_33
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(209.10358, 52.748425);
        ((GeneralPath) shape).curveTo(209.10358, 52.748425, 197.20625, 57.573914, 196.56146, 64.648254);
        ((GeneralPath) shape).curveTo(197.85103, 69.791145, 206.20828, 71.078224, 213.92491, 70.11312);
        ((GeneralPath) shape).curveTo(219.07071, 69.14927, 217.83105, 50.344, 209.10358, 52.748425);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_34
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(209.10358, 52.748425);
        ((GeneralPath) shape).lineTo(209.10358, 70.40889);

        g.draw(shape);

        // _0_0_0_35
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(215.65959, 68.82771);
        ((GeneralPath) shape).curveTo(215.65959, 68.82771, 213.6046, 70.38268, 218.7504, 70.4089);
        ((GeneralPath) shape).curveTo(223.8962, 70.434685, 301.545, 66.63212, 301.545, 66.63212);

        g.draw(shape);

        // _0_0_0_36
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(323.9586, 66.63211);
        ((GeneralPath) shape).lineTo(444.7789, 62.940197);

        g.draw(shape);

        // _0_0_0_37
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(234.08797, 70.02368);
        ((GeneralPath) shape).curveTo(234.08797, 70.02368, 223.8962, 49.536976, 228.39722, 47.927094);
        ((GeneralPath) shape).curveTo(232.89824, 46.32137, 241.26381, 69.4987, 241.26381, 69.4987);

        g.draw(shape);

        // _0_0_0_38
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(420.46426, 26.85717);
        ((GeneralPath) shape).curveTo(420.46426, 26.85717, 415.3476, 48.08517, 426.6126, 48.95459);
        ((GeneralPath) shape).curveTo(437.87762, 49.81985, 440.91437, 47.848053, 440.91437, 47.848053);
        ((GeneralPath) shape).curveTo(440.91437, 47.848053, 442.8612, 47.731575, 440.91437, 40.759575);

        g.draw(shape);

        // _0_0_0_39
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(430.5437, 49.170906);
        ((GeneralPath) shape).lineTo(427.47784, 56.10131);
        ((GeneralPath) shape).lineTo(430.5437, 56.10131);
        ((GeneralPath) shape).lineTo(433.76346, 49.170906);
        ((GeneralPath) shape).lineTo(430.5437, 49.170906);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_40
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(434.84506, 62.940197);
        ((GeneralPath) shape).curveTo(434.84506, 62.940197, 436.36343, 57.295204, 429.26663, 56.375866);
        ((GeneralPath) shape).curveTo(429.26663, 56.375866, 421.63736, 54.166954, 416.64963, 63.80005);

        g.draw(shape);

        // _0_0_0_41
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(331.26755, 25.98359);
        ((GeneralPath) shape).curveTo(331.26755, 25.98359, 339.94928, 39.20793, 336.2636, 49.170906);
        ((GeneralPath) shape).curveTo(333.87997, 50.252483, 327.09933, 49.170906, 327.09933, 49.170906);

        g.draw(shape);

        // _0_0_0_42
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(330.84738, 49.653458);
        ((GeneralPath) shape).lineTo(330.84738, 57.61968);

        g.draw(shape);

        // _0_0_0_43
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(335.20282, 66.28934);
        ((GeneralPath) shape).curveTo(335.20282, 66.28934, 335.93082, 54.225193, 325.58096, 57.61968);

        g.draw(shape);

        // _0_0_0_44
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(24.37795, 95.59546);
        ((GeneralPath) shape).lineTo(11.043372, 96.62088);

        g.draw(shape);

        // _0_0_0_45
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(20.006721, 95.932);
        ((GeneralPath) shape).lineTo(11.043371, 113.51717);
        ((GeneralPath) shape).lineTo(3.4257507, 113.51717);

        g.draw(shape);

        // _0_0_0_46
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(9.116087, 98.86515);
        ((GeneralPath) shape).lineTo(18.722975, 98.44916);
        ((GeneralPath) shape).lineTo(17.46377, 100.92056);
        ((GeneralPath) shape).lineTo(7.371006, 101.40643);
        ((GeneralPath) shape).lineTo(9.116087, 98.86515);

        g.fill(shape);

        // _0_0_0_47
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(6.2141366, 103.34661);
        ((GeneralPath) shape).lineTo(16.225367, 103.34661);
        ((GeneralPath) shape).lineTo(15.014833, 105.72566);
        ((GeneralPath) shape).lineTo(5.1350565, 105.72566);
        ((GeneralPath) shape).lineTo(6.214136, 103.34661);

        g.fill(shape);

        // _0_0_0_48
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(12.367886, 110.92055);
        ((GeneralPath) shape).lineTo(3.7231846, 110.92055);
        ((GeneralPath) shape).lineTo(4.3405147, 108.09307);
        ((GeneralPath) shape).lineTo(13.807631, 108.09307);
        ((GeneralPath) shape).lineTo(12.367887, 110.92055);

        g.fill(shape);

        // _0_0_0_49
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(160.48259, 147.95204);
        ((GeneralPath) shape).curveTo(160.48259, 171.68639, 141.23888, 190.92886, 117.5066, 190.92886);
        ((GeneralPath) shape).curveTo(93.77018, 190.92886, 74.52229, 171.68639, 74.52229, 147.95204);
        ((GeneralPath) shape).curveTo(74.52229, 124.216034, 93.77018, 104.973145, 117.5066, 104.973145);
        ((GeneralPath) shape).curveTo(141.23886, 104.973145, 160.48259, 124.216034, 160.48259, 147.95204);
        ((GeneralPath) shape).closePath();

        g.draw(shape);
        this.autoShape.add(shape);

        // _0_0_0_50
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(142.4494, 147.95204);
        ((GeneralPath) shape).curveTo(142.4494, 161.7284, 131.28006, 172.89859, 117.5066, 172.89859);
        ((GeneralPath) shape).curveTo(103.72483, 172.89859, 92.55965, 161.72841, 92.55965, 147.95206);
        ((GeneralPath) shape).curveTo(92.55965, 134.17403, 103.72483, 123.005516, 117.5066, 123.005516);
        ((GeneralPath) shape).curveTo(131.28004, 123.005516, 142.4494, 134.17403, 142.4494, 147.95206);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_51
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(490.2259, 147.95204);
        ((GeneralPath) shape).curveTo(490.2259, 171.68639, 470.98215, 190.92886, 447.24573, 190.92886);
        ((GeneralPath) shape).curveTo(423.5093, 190.92886, 404.26556, 171.68639, 404.26556, 147.95204);
        ((GeneralPath) shape).curveTo(404.26556, 124.216034, 423.50928, 104.973145, 447.24573, 104.973145);
        ((GeneralPath) shape).curveTo(470.98215, 104.973145, 490.2259, 124.216034, 490.2259, 147.95204);
        ((GeneralPath) shape).closePath();

        g.draw(shape);
        this.autoShape.add(shape);

        // _0_0_0_52
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(472.18854, 147.95204);
        ((GeneralPath) shape).curveTo(472.18854, 161.7284, 461.02335, 172.89859, 447.24573, 172.89859);
        ((GeneralPath) shape).curveTo(433.4681, 172.89859, 422.29877, 161.72841, 422.29877, 147.95206);
        ((GeneralPath) shape).curveTo(422.29877, 134.17403, 433.4681, 123.005516, 447.24573, 123.005516);
        ((GeneralPath) shape).curveTo(461.02335, 123.005516, 472.18854, 134.17403, 472.18854, 147.95206);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_53
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(515.8343, 46.32137);
        ((GeneralPath) shape).lineTo(504.58176, 51.1427);

        g.draw(shape);

        // _0_0_0_54
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(429.8781, 7.9212766);
        ((GeneralPath) shape).lineTo(420.5932, 12.264219);

        g.draw(shape);

        // _0_0_0_55
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(244.94118, 9.926352);
        ((GeneralPath) shape).lineTo(255.6155, 12.65525);

        g.draw(shape);

        // _0_0_0_56
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(149.29662, 62.719723);
        ((GeneralPath) shape).lineTo(176.45663, 62.34949);

        g.draw(shape);
    }

    @Override
    public boolean shapeContains(double x, double y) {
        for (Shape shap: this.autoShape) {
            if (shap.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getShapeWidth() {
        return 582;
    }

    @Override
    public int getShapeHeight() {
        return 192;
    }
}

