package org.nekorp.workflow.desktop.view.resource.imp;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.geom.*;
import org.nekorp.workflow.desktop.view.resource.ShapeView;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
@org.springframework.stereotype.Component("autoCuatroLeftView")
public class AutoCuatroLeftView extends ShapeView {

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

        // _0_0_0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(512.9497, 168.34032);
        ((GeneralPath) shape).curveTo(512.9497, 168.34032, 524.2022, 166.24577, 531.27405, 166.56749);
        ((GeneralPath) shape).curveTo(538.35004, 166.88927, 562.46075, 166.53339, 562.46075, 166.53339);
        ((GeneralPath) shape).curveTo(562.46075, 166.53339, 571.7831, 160.83629, 570.17737, 154.05533);
        ((GeneralPath) shape).curveTo(567.60657, 150.17332, 574.0377, 142.26312, 577.894, 141.23604);
        ((GeneralPath) shape).curveTo(581.7502, 140.20563, 581.7502, 120.89958, 577.894, 114.95884);
        ((GeneralPath) shape).curveTo(577.24915, 104.19346, 575.3231, 70.111465, 431.9275, 62.719757);
        ((GeneralPath) shape).curveTo(407.48813, 43.748158, 340.93402, 9.024714, 322.93002, 5.4871426);
        ((GeneralPath) shape).curveTo(310.38794, 2.2711306, 223.57516, -2.871743, 164.09705, 4.2000723);
        ((GeneralPath) shape).curveTo(138.05614, 10.311784, 86.93107, 34.746155, 65.386986, 46.320633);
        ((GeneralPath) shape).curveTo(48.988693, 47.929264, 22.627464, 50.179764, 21.018417, 51.145275);
        ((GeneralPath) shape).curveTo(19.629015, 51.978916, 13.622963, 57.462902, 14.49238, 92.93887);
        ((GeneralPath) shape).curveTo(14.45328, 94.484276, 13.554741, 92.88562, 13.540181, 94.54792);
        ((GeneralPath) shape).curveTo(13.514391, 97.75062, 13.534681, 101.17587, 13.622961, 104.83699);
        ((GeneralPath) shape).curveTo(10.087053, 105.1573, 0.7614088, 102.90639, 0.44151402, 117.05501);
        ((GeneralPath) shape).curveTo(0.118290454, 131.20197, 9.445183, 151.13853, 10.087053, 151.78203);
        ((GeneralPath) shape).curveTo(10.727262, 152.42386, 65.71146, 148.8865, 83.39099, 161.10439);
        ((GeneralPath) shape).curveTo(83.39099, 147.60109, 85.00087, 99.05058, 134.83636, 98.40871);
        ((GeneralPath) shape).curveTo(186.92236, 101.30274, 182.74583, 155.3163, 183.70676, 164.6419);
        ((GeneralPath) shape).curveTo(189.49316, 164.6419, 414.55994, 164.6419, 414.55994, 164.6419);
        ((GeneralPath) shape).curveTo(414.55994, 164.6419, 406.84332, 98.72859, 466.00116, 98.72859);
        ((GeneralPath) shape).curveTo(520.0215, 98.72859, 512.9497, 168.3403, 512.9497, 168.3403);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.fill(shape);
        g.setPaint(new Color(0x1E1D1F));
        g.setStroke(new BasicStroke(0.8666426f, 0, 0, 4));
        g.draw(shape);
        
        this.autoShape = shape;

        // _0_0_0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(13.622967, 104.83699);
        ((GeneralPath) shape).curveTo(13.622967, 104.83699, 19.733015, 105.48053, 20.05291, 104.51543);
        ((GeneralPath) shape).curveTo(20.371557, 57.247, 27.12847, 55.001076, 30.344063, 55.001076);
        ((GeneralPath) shape).curveTo(73.74837, 52.110367, 76.643654, 51.14527, 76.643654, 51.14527);
        ((GeneralPath) shape).curveTo(76.643654, 51.14527, 157.02107, 13.527374, 167.30847, 9.346268);
        ((GeneralPath) shape).curveTo(177.60004, 5.165161, 284.02255, 3.558195, 314.5686, 9.346268);
        ((GeneralPath) shape).curveTo(345.11053, 15.132676, 405.2376, 62.719753, 405.2376, 62.719753);
        ((GeneralPath) shape).curveTo(405.2376, 62.719753, 521.30273, 82.973015, 533.52454, 87.4761);
        ((GeneralPath) shape).curveTo(543.1672, 91.97543, 551.52435, 109.33798, 551.52435, 109.33798);
        ((GeneralPath) shape).curveTo(551.52435, 109.33798, 555.3847, 114.48419, 567.92267, 114.16429);
        ((GeneralPath) shape).curveTo(562.45654, 101.94627, 554.74414, 92.93886, 554.74414, 92.93886);
        ((GeneralPath) shape).lineTo(539.7727, 92.15763);

        g.draw(shape);

        // _0_0_0_2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.2376, 62.719757);
        ((GeneralPath) shape).curveTo(405.2376, 62.719757, 417.13074, 121.236115, 407.16364, 151.13855);
        ((GeneralPath) shape).curveTo(389.15964, 151.13855, 196.24884, 151.13855, 196.24884, 151.13855);
        ((GeneralPath) shape).curveTo(196.24884, 151.13855, 181.46042, 93.584076, 144.16284, 86.83424);
        ((GeneralPath) shape).curveTo(133.22649, 83.938545, 124.86926, 76.54143, 124.86926, 72.04375);
        ((GeneralPath) shape).curveTo(124.86926, 67.86264, 126.79945, 44.712013, 126.79945, 44.712013);
        ((GeneralPath) shape).curveTo(126.79945, 44.712013, 133.87128, 30.565056, 178.56099, 12.2419815);
        ((GeneralPath) shape).curveTo(182.42137, 10.311791, 184.05205, 7.1560984, 184.05205, 7.1560984);

        g.draw(shape);

        // _0_0_0_3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(252.51553, 5.586148);
        ((GeneralPath) shape).curveTo(252.51553, 5.586148, 266.77982, 62.719757, 268.00696, 70.43511);
        ((GeneralPath) shape).curveTo(269.22998, 78.15046, 270.96048, 111.91213, 268.00696, 151.13855);

        g.draw(shape);

        // _0_0_0_4
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(98.63284, 112.70002);
        ((GeneralPath) shape).curveTo(98.63284, 112.70002, 92.39716, 100.33765, 80.49988, 100.33765);
        ((GeneralPath) shape).curveTo(68.602585, 100.33765, 20.100338, 100.33765, 20.100338, 100.33765);

        g.draw(shape);

        // _0_0_0_5
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(0.91117126, 112.70002);
        ((GeneralPath) shape).lineTo(73.42806, 112.70002);
        ((GeneralPath) shape).curveTo(73.42806, 112.70002, 77.92906, 112.87557, 77.60459, 117.69854);
        ((GeneralPath) shape).curveTo(77.28428, 122.52152, 71.818184, 125.73712, 71.818184, 125.73712);
        ((GeneralPath) shape).lineTo(0.91117096, 125.73712);

        g.draw(shape);

        // _0_0_0_6
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(21.516775, 75.278885);
        ((GeneralPath) shape).lineTo(14.492383, 75.278885);

        g.draw(shape);

        // _0_0_0_7
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(20.27588, 93.247955);
        ((GeneralPath) shape).lineTo(14.492384, 92.93887);

        g.draw(shape);

        // _0_0_0_8
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(22.89786, 66.63422);
        ((GeneralPath) shape).curveTo(22.89786, 66.63422, 33.64286, 67.07766, 36.53398, 71.89731);
        ((GeneralPath) shape).curveTo(39.429264, 76.721954, 42.881973, 100.33765, 42.881973, 100.33765);

        g.draw(shape);

        // _0_0_0_9
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(407.48813, 119.53805);
        ((GeneralPath) shape).curveTo(407.48813, 117.81211, 406.08624, 116.409805, 404.35574, 116.409805);
        ((GeneralPath) shape).lineTo(191.3402, 116.409805);
        ((GeneralPath) shape).curveTo(189.60968, 116.409805, 188.21196, 117.8121, 188.21196, 119.53805);
        ((GeneralPath) shape).lineTo(188.21196, 119.53805);
        ((GeneralPath) shape).curveTo(188.21196, 121.26523, 189.60968, 122.66753, 191.3402, 122.66753);
        ((GeneralPath) shape).lineTo(404.35574, 122.66753);
        ((GeneralPath) shape).curveTo(406.08627, 122.66753, 407.48813, 121.26523, 407.48813, 119.53805);
        ((GeneralPath) shape).lineTo(407.48813, 119.53805);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_0_10
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(407.48813, 119.53805);
        ((GeneralPath) shape).curveTo(407.48813, 117.81211, 406.08624, 116.409805, 404.35574, 116.409805);
        ((GeneralPath) shape).lineTo(191.3402, 116.409805);
        ((GeneralPath) shape).curveTo(189.60968, 116.409805, 188.21196, 117.8121, 188.21196, 119.53805);
        ((GeneralPath) shape).lineTo(188.21196, 119.53805);
        ((GeneralPath) shape).curveTo(188.21196, 121.26523, 189.60968, 122.66753, 191.3402, 122.66753);
        ((GeneralPath) shape).lineTo(404.35574, 122.66753);
        ((GeneralPath) shape).curveTo(406.08627, 122.66753, 407.48813, 121.26523, 407.48813, 119.53805);
        ((GeneralPath) shape).lineTo(407.48813, 119.53805);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x1E1D1F));
        g.draw(shape);

        // _0_0_0_11
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(279.52155, 87.739006);
        ((GeneralPath) shape).curveTo(279.52155, 90.61516, 281.84692, 92.93887, 284.7214, 92.93887);
        ((GeneralPath) shape).lineTo(292.646, 92.93887);
        ((GeneralPath) shape).curveTo(295.51633, 92.93887, 297.84586, 90.61516, 297.84586, 87.739006);
        ((GeneralPath) shape).lineTo(297.84586, 84.344536);
        ((GeneralPath) shape).curveTo(297.84586, 81.47338, 295.51633, 79.14467, 292.646, 79.14467);
        ((GeneralPath) shape).lineTo(284.7214, 79.14467);
        ((GeneralPath) shape).curveTo(281.84692, 79.14467, 279.52155, 81.47337, 279.52155, 84.344536);
        ((GeneralPath) shape).lineTo(279.52155, 87.739006);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_12
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(301.70624, 86.044266);
        ((GeneralPath) shape).curveTo(301.70624, 84.16608, 300.18372, 82.644806, 298.31177, 82.644806);
        ((GeneralPath) shape).lineTo(276.4848, 82.644806);
        ((GeneralPath) shape).curveTo(274.61285, 82.644806, 273.09033, 84.16608, 273.09033, 86.044266);
        ((GeneralPath) shape).lineTo(273.09033, 86.044266);
        ((GeneralPath) shape).curveTo(273.09033, 87.91955, 274.61285, 89.4404, 276.4848, 89.4404);
        ((GeneralPath) shape).lineTo(298.31177, 89.4404);
        ((GeneralPath) shape).curveTo(300.18372, 89.4404, 301.70624, 87.91954, 301.70624, 86.044266);
        ((GeneralPath) shape).lineTo(301.70624, 86.044266);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_0_13
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(301.70624, 86.044266);
        ((GeneralPath) shape).curveTo(301.70624, 84.16608, 300.18372, 82.644806, 298.31177, 82.644806);
        ((GeneralPath) shape).lineTo(276.4848, 82.644806);
        ((GeneralPath) shape).curveTo(274.61285, 82.644806, 273.09033, 84.16608, 273.09033, 86.044266);
        ((GeneralPath) shape).lineTo(273.09033, 86.044266);
        ((GeneralPath) shape).curveTo(273.09033, 87.91955, 274.61285, 89.4404, 276.4848, 89.4404);
        ((GeneralPath) shape).lineTo(298.31177, 89.4404);
        ((GeneralPath) shape).curveTo(300.18372, 89.4404, 301.70624, 87.91954, 301.70624, 86.044266);
        ((GeneralPath) shape).lineTo(301.70624, 86.044266);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x1E1D1F));
        g.draw(shape);

        // _0_0_0_14
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(293.34488, 82.644806);
        ((GeneralPath) shape).lineTo(293.34488, 89.44041);

        g.draw(shape);

        // _0_0_0_15
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(158.63095, 83.874886);
        ((GeneralPath) shape).curveTo(158.63095, 86.7448, 160.95633, 89.07475, 163.83081, 89.07475);
        ((GeneralPath) shape).lineTo(171.75542, 89.07475);
        ((GeneralPath) shape).curveTo(174.62573, 89.07475, 176.95528, 86.7448, 176.95528, 83.874886);
        ((GeneralPath) shape).lineTo(176.95528, 80.47875);
        ((GeneralPath) shape).curveTo(176.95528, 77.60926, 174.62573, 75.278885, 171.75542, 75.278885);
        ((GeneralPath) shape).lineTo(163.83081, 75.278885);
        ((GeneralPath) shape).curveTo(160.95633, 75.278885, 158.63095, 77.60925, 158.63095, 80.47875);
        ((GeneralPath) shape).lineTo(158.63095, 83.874886);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_16
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(180.81564, 82.173904);
        ((GeneralPath) shape).curveTo(180.81564, 80.29862, 179.29312, 78.779015, 177.417, 78.779015);
        ((GeneralPath) shape).lineTo(155.59422, 78.779015);
        ((GeneralPath) shape).curveTo(153.71811, 78.779015, 152.19975, 80.29862, 152.19975, 82.173904);
        ((GeneralPath) shape).lineTo(152.19975, 82.173904);
        ((GeneralPath) shape).curveTo(152.19975, 84.05376, 153.71811, 85.57295, 155.59422, 85.57295);
        ((GeneralPath) shape).lineTo(177.417, 85.57295);
        ((GeneralPath) shape).curveTo(179.29312, 85.57295, 180.81564, 84.05376, 180.81564, 82.173904);
        ((GeneralPath) shape).lineTo(180.81564, 82.173904);

        g.setPaint(WHITE);
        g.fill(shape);

        // _0_0_0_17
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(180.81564, 82.173904);
        ((GeneralPath) shape).curveTo(180.81564, 80.29862, 179.29312, 78.779015, 177.417, 78.779015);
        ((GeneralPath) shape).lineTo(155.59422, 78.779015);
        ((GeneralPath) shape).curveTo(153.71811, 78.779015, 152.19975, 80.29862, 152.19975, 82.173904);
        ((GeneralPath) shape).lineTo(152.19975, 82.173904);
        ((GeneralPath) shape).curveTo(152.19975, 84.05376, 153.71811, 85.57295, 155.59422, 85.57295);
        ((GeneralPath) shape).lineTo(177.417, 85.57295);
        ((GeneralPath) shape).curveTo(179.29312, 85.57295, 180.81564, 84.05376, 180.81564, 82.173904);
        ((GeneralPath) shape).lineTo(180.81564, 82.173904);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x1E1D1F));
        g.draw(shape);

        // _0_0_0_18
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(172.45427, 78.779015);
        ((GeneralPath) shape).lineTo(172.45427, 85.57295);

        g.draw(shape);

        // _0_0_0_19
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(414.22714, 157.56686);
        ((GeneralPath) shape).lineTo(407.16364, 151.13855);

        g.draw(shape);

        // _0_0_0_20
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(183.24503, 154.34943);
        ((GeneralPath) shape).lineTo(196.24884, 151.13853);

        g.draw(shape);

        // _0_0_0_21
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(183.5071, 160.46742);
        ((GeneralPath) shape).lineTo(414.22714, 160.46742);

        g.draw(shape);

        // _0_0_0_22
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(426.45306, 86.96901);
        ((GeneralPath) shape).curveTo(426.45306, 85.60415, 425.34653, 84.4972, 423.9821, 84.4972);
        ((GeneralPath) shape).lineTo(417.996, 84.4972);
        ((GeneralPath) shape).curveTo(416.62738, 84.4972, 415.52502, 85.60415, 415.52502, 86.96901);
        ((GeneralPath) shape).lineTo(415.52502, 86.96901);
        ((GeneralPath) shape).curveTo(415.52502, 88.33221, 416.62738, 89.4404, 417.996, 89.4404);
        ((GeneralPath) shape).lineTo(423.9821, 89.4404);
        ((GeneralPath) shape).curveTo(425.34653, 89.4404, 426.45306, 88.33221, 426.45306, 86.96901);
        ((GeneralPath) shape).lineTo(426.45306, 86.96901);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_23
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(500.27866, 114.17553);
        ((GeneralPath) shape).curveTo(500.27866, 114.17553, 504.58414, 109.66163, 513.91064, 109.66163);
        ((GeneralPath) shape).curveTo(523.233, 109.66163, 551.52435, 109.66163, 551.52435, 109.66163);

        g.draw(shape);

        // _0_0_0_24
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(579.5828, 119.21774);
        ((GeneralPath) shape).lineTo(516.80176, 119.21774);
        ((GeneralPath) shape).curveTo(516.80176, 119.21774, 515.8408, 128.9498, 522.27203, 128.9498);
        ((GeneralPath) shape).curveTo(528.69904, 128.9498, 580.78925, 128.9498, 580.78925, 128.9498);

        g.draw(shape);

        // _0_0_0_25
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(579.83655, 138.38443);
        ((GeneralPath) shape).lineTo(561.1753, 138.38443);
        ((GeneralPath) shape).curveTo(561.1753, 138.38443, 554.0993, 144.06352, 557.6394, 154.34943);

        g.draw(shape);

        // _0_0_0_26
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.1773, 154.05533);
        ((GeneralPath) shape).lineTo(512.9746, 154.05533);

        g.draw(shape);

        // _0_0_0_27
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(574.94037, 142.93079);
        ((GeneralPath) shape).lineTo(557.8931, 142.93079);

        g.draw(shape);

        // _0_0_0_28
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.1773, 149.26671);
        ((GeneralPath) shape).lineTo(556.84485, 149.26671);

        g.draw(shape);

        // _0_0_0_29
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.2388, 138.38443);
        ((GeneralPath) shape).lineTo(568.2388, 154.05533);

        g.draw(shape);

        // _0_0_0_30
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(566.4126, 138.38443);
        ((GeneralPath) shape).lineTo(566.4126, 154.34943);

        g.draw(shape);

        // _0_0_0_31
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(261.07242, 11.598439);
        ((GeneralPath) shape).curveTo(261.07242, 11.598439, 275.02054, 60.43681, 281.44757, 69.79157);
        ((GeneralPath) shape).curveTo(297.52554, 70.435104, 391.41013, 75.278885, 391.41013, 75.278885);
        ((GeneralPath) shape).curveTo(391.41013, 75.278885, 399.12674, 69.79157, 389.8044, 62.07622);
        ((GeneralPath) shape).curveTo(380.48212, 54.357544, 329.03256, 16.096527, 305.56247, 14.167587);
        ((GeneralPath) shape).curveTo(282.09235, 12.241974, 261.07242, 11.598439, 261.07242, 11.598439);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_32
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(247.36559, 11.598439);
        ((GeneralPath) shape).lineTo(257.65717, 68.82648);
        ((GeneralPath) shape).lineTo(139.65768, 65.2889);
        ((GeneralPath) shape).curveTo(139.65768, 65.2889, 125.509895, 57.57355, 134.51605, 45.355537);
        ((GeneralPath) shape).curveTo(149.30446, 33.782303, 168.59804, 15.7745495, 215.21794, 11.598438);
        ((GeneralPath) shape).curveTo(241.25887, 11.598438, 247.36559, 11.598438, 247.36559, 11.598438);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_33
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(372.12073, 52.75058);
        ((GeneralPath) shape).curveTo(372.12073, 52.75058, 384.01385, 57.57356, 384.65863, 64.6487);
        ((GeneralPath) shape).curveTo(383.36905, 69.79157, 375.01184, 71.07864, 367.29523, 70.111465);
        ((GeneralPath) shape).curveTo(362.1536, 69.14803, 363.3891, 50.34574, 372.1207, 52.750576);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_34
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(372.12073, 52.75058);
        ((GeneralPath) shape).lineTo(372.12073, 70.40724);

        g.draw(shape);

        // _0_0_0_35
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(365.56058, 68.82648);
        ((GeneralPath) shape).curveTo(365.56058, 68.82648, 367.61557, 70.38477, 362.4698, 70.407234);
        ((GeneralPath) shape).curveTo(357.32816, 70.435104, 279.67548, 66.63422, 279.67548, 66.63422);

        g.draw(shape);

        // _0_0_0_36
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(257.26196, 66.63422);
        ((GeneralPath) shape).lineTo(136.4421, 62.943977);

        g.draw(shape);

        // _0_0_0_37
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(347.13226, 70.022446);
        ((GeneralPath) shape).curveTo(347.13226, 70.022446, 357.32816, 49.53623, 352.82715, 47.92926);
        ((GeneralPath) shape).curveTo(348.32614, 46.32063, 339.95645, 69.49747, 339.95645, 69.49747);

        g.draw(shape);

        // _0_0_0_38
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(160.75665, 26.858583);
        ((GeneralPath) shape).curveTo(160.75665, 26.858583, 165.87749, 48.08526, 154.60834, 48.953014);
        ((GeneralPath) shape).curveTo(143.34335, 49.819103, 140.31079, 47.848145, 140.31079, 47.848145);
        ((GeneralPath) shape).curveTo(140.31079, 47.848145, 138.3598, 47.732914, 140.31079, 40.758442);

        g.draw(shape);

        // _0_0_0_39
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(150.6814, 49.170994);
        ((GeneralPath) shape).lineTo(153.74307, 56.103035);
        ((GeneralPath) shape).lineTo(150.6814, 56.103035);
        ((GeneralPath) shape).lineTo(147.45747, 49.17099);
        ((GeneralPath) shape).lineTo(150.6814, 49.17099);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_40
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(146.37592, 62.943977);
        ((GeneralPath) shape).curveTo(146.37592, 62.943977, 144.85756, 57.292347, 151.95433, 56.372597);
        ((GeneralPath) shape).curveTo(151.95433, 56.372597, 159.58774, 54.16411, 164.57127, 63.8005);

        g.draw(shape);

        // _0_0_0_41
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(249.95303, 25.985838);
        ((GeneralPath) shape).curveTo(249.95303, 25.985838, 241.2755, 39.208054, 244.957, 49.17099);
        ((GeneralPath) shape).curveTo(247.34062, 50.25298, 254.1254, 49.17099, 254.1254, 49.17099);

        g.draw(shape);

        // _0_0_0_42
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(250.37318, 49.651875);
        ((GeneralPath) shape).lineTo(250.37318, 57.618896);

        g.draw(shape);

        // _0_0_0_43
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(246.02194, 66.286446);
        ((GeneralPath) shape).curveTo(246.02194, 66.286446, 245.29396, 54.222763, 255.63962, 57.618896);

        g.draw(shape);

        // _0_0_0_44
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(556.84485, 95.595795);
        ((GeneralPath) shape).lineTo(570.1773, 96.619545);

        g.draw(shape);

        // _0_0_0_45
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(561.21277, 95.93399);
        ((GeneralPath) shape).lineTo(570.17737, 113.51744);
        ((GeneralPath) shape).lineTo(577.7941, 113.51744);

        g.draw(shape);

        // _0_0_0_46
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.10333, 98.86381);
        ((GeneralPath) shape).lineTo(562.49817, 98.447815);
        ((GeneralPath) shape).lineTo(563.7586, 100.91921);
        ((GeneralPath) shape).lineTo(573.8547, 101.40675);
        ((GeneralPath) shape).lineTo(572.1034, 98.86381);

        g.fill(shape);

        // _0_0_0_47
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(575.00696, 103.34692);
        ((GeneralPath) shape).lineTo(564.9941, 103.34692);
        ((GeneralPath) shape).lineTo(566.2046, 105.72596);
        ((GeneralPath) shape).lineTo(576.08435, 105.72596);
        ((GeneralPath) shape).lineTo(575.00696, 103.34692);

        g.fill(shape);

        // _0_0_0_48
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.85443, 110.91917);
        ((GeneralPath) shape).lineTo(577.49866, 110.91917);
        ((GeneralPath) shape).lineTo(576.883, 108.093346);
        ((GeneralPath) shape).lineTo(567.4151, 108.093346);
        ((GeneralPath) shape).lineTo(568.85443, 110.91917);

        g.fill(shape);

        // _0_0_0_49
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(420.73737, 147.95218);
        ((GeneralPath) shape).curveTo(420.73737, 171.68611, 439.98102, 190.92886, 463.7132, 190.92886);
        ((GeneralPath) shape).curveTo(487.4537, 190.92886, 506.69736, 171.6861, 506.69736, 147.95218);
        ((GeneralPath) shape).curveTo(506.69736, 124.21293, 487.4537, 104.97177, 463.7132, 104.97177);
        ((GeneralPath) shape).curveTo(439.98102, 104.97177, 420.73737, 124.21293, 420.73737, 147.95218);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_50
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(438.7705, 147.95218);
        ((GeneralPath) shape).curveTo(438.7705, 161.72675, 449.94397, 172.89835, 463.71323, 172.89835);
        ((GeneralPath) shape).curveTo(477.4991, 172.89835, 488.66425, 161.72676, 488.66425, 147.95218);
        ((GeneralPath) shape).curveTo(488.66425, 134.17255, 477.49908, 123.00573, 463.71323, 123.00573);
        ((GeneralPath) shape).curveTo(449.944, 123.00573, 438.7705, 134.17255, 438.7705, 147.95218);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_51
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(90.99944, 147.95218);
        ((GeneralPath) shape).curveTo(90.99944, 171.68611, 110.2431, 190.92886, 133.97528, 190.92886);
        ((GeneralPath) shape).curveTo(157.71161, 190.92886, 176.95526, 171.6861, 176.95526, 147.95218);
        ((GeneralPath) shape).curveTo(176.95526, 124.21293, 157.71161, 104.97177, 133.97528, 104.97177);
        ((GeneralPath) shape).curveTo(110.2431, 104.97177, 90.999435, 124.21293, 90.999435, 147.95218);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_52
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(109.03257, 147.95218);
        ((GeneralPath) shape).curveTo(109.03257, 161.72675, 120.20188, 172.89835, 133.97528, 172.89835);
        ((GeneralPath) shape).curveTo(147.75284, 172.89835, 158.92213, 161.72676, 158.92213, 147.95218);
        ((GeneralPath) shape).curveTo(158.92213, 134.17255, 147.75282, 123.00573, 133.97528, 123.00573);
        ((GeneralPath) shape).curveTo(120.20188, 123.00573, 109.03257, 134.17255, 109.03257, 147.95218);
        ((GeneralPath) shape).closePath();

        g.draw(shape);

        // _0_0_0_53
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(65.386986, 46.320633);
        ((GeneralPath) shape).lineTo(76.643654, 51.145275);

        g.draw(shape);

        // _0_0_0_54
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(151.34282, 7.921095);
        ((GeneralPath) shape).lineTo(160.63187, 12.263189);

        g.draw(shape);

        // _0_0_0_55
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(336.28326, 9.929906);
        ((GeneralPath) shape).lineTo(325.60483, 12.657963);

        g.draw(shape);

        // _0_0_0_56
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(431.9275, 62.719757);
        ((GeneralPath) shape).lineTo(404.76755, 62.350777);

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
        return 582;
    }

    @Override
    public int getShapeHeight() {
        return 192;
    }
}

