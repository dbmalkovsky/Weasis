package org.weasis.core.ui.model.graphic.imp.line;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

import org.weasis.core.api.service.WProperties;
import org.weasis.core.ui.test.testers.GraphicTester;

public class PolylineGraphicTest extends GraphicTester<PolylineGraphic> {
    private static final String XML_0 = "/graphic/polyline/polyline.graphic.0.xml";
    private static final String XML_1 = "/graphic/polyline/polyline.graphic.1.xml";
    
    static final String BASIC_TPL =
        "<polyline fill=\"%s\" showLabel=\"%s\" thickness=\"%s\" uuid=\"%s\">"
       +     "<paint rgb=\"%s\"/>"
       +     "<pts/>"
       + "</polyline>";

    public static final PolylineGraphic COMPLETE_OBJECT =  new PolylineGraphic();
    static {
        COMPLETE_OBJECT.setUuid(GRAPHIC_UUID_1);
        
        List<Point2D.Double> pts = Arrays.asList(
            new Point2D.Double(935.5, 902.0),
            new Point2D.Double(1042.5, 863.0),
            new Point2D.Double(1188.5, 902.0),
            new Point2D.Double(1242.5, 997.0),
            new Point2D.Double(1409.5, 950.0),
            new Point2D.Double(1453.5, 1059.0),
            new Point2D.Double(1288.5, 1105.0),
            new Point2D.Double(1391.5, 1224.0),
            new Point2D.Double(1641.5, 1250.0),
            new Point2D.Double(1600.5, 1365.0),
            new Point2D.Double(1413.5, 1376.0),
            new Point2D.Double(1260.5, 1356.0),
            new Point2D.Double(1262.5, 1355.0)
        );
        COMPLETE_OBJECT.setPts(pts); 
    }
    
    @Override
    public String getTemplate() {
        return BASIC_TPL;
    }

    @Override
    public Object[] getParameters() {
        return new Object[]{ 
            PolylineGraphic.DEFAULT_FILLED, 
            PolylineGraphic.DEFAULT_LABEL_VISISIBLE,
            PolylineGraphic.DEFAULT_LINE_THICKNESS, 
            getGraphicUuid(), 
            WProperties.color2Hexadecimal(PolylineGraphic.DEFAULT_COLOR, true) 
        };
    }
    
    @Override
    public String getXmlFilePathCase0() {
        return XML_0;
    }

    @Override
    public String getXmlFilePathCase1() {
        return XML_1;
    }

    @Override
    public PolylineGraphic getExpectedDeserializeCompleteGraphic() {
        return COMPLETE_OBJECT;
    }
}
