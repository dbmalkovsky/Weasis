/*******************************************************************************
 * Copyright (c) 2009-2018 Weasis Team and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Nicolas Roduit - initial API and implementation
 *******************************************************************************/
package org.weasis.acquire.explorer.gui.central;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.weasis.acquire.explorer.AcquireImageInfo;
import org.weasis.acquire.explorer.AcquireManager;
import org.weasis.acquire.explorer.Messages;
import org.weasis.acquire.explorer.core.bean.SeriesGroup;
import org.weasis.acquire.explorer.gui.central.meta.panel.AcquireMetadataPanel;
import org.weasis.acquire.explorer.gui.central.meta.panel.imp.AcquireGlobalMetaPanel;
import org.weasis.acquire.explorer.gui.central.meta.panel.imp.AcquireImageMetaPanel;
import org.weasis.acquire.explorer.gui.central.meta.panel.imp.AcquireSerieMetaPanel;
import org.weasis.core.api.media.data.ImageElement;
import org.weasis.core.ui.util.JMVUtils;

@SuppressWarnings("serial")
public class AcquireCentralInfoPanel extends JPanel {

    private AcquireMetadataPanel globalInfoPanel =
        new AcquireGlobalMetaPanel(Messages.getString("AcquireCentralInfoPanel.global")); //$NON-NLS-1$
    private AcquireSerieMetaPanel serieInfoPanel = new AcquireSerieMetaPanel(null);
    private AcquireMetadataPanel imageInfoPanel =
        new AcquireImageMetaPanel(Messages.getString("AcquireCentralInfoPanel.image")); //$NON-NLS-1$

    private AcquireImageInfo imageInfo;

    public AcquireCentralInfoPanel(SeriesGroup seriesGroup) {
        setLayout(new GridLayout(1, 3));
        JMVUtils.setPreferredHeight(this, 230);

        setSerie(seriesGroup);

        add(globalInfoPanel);
        add(serieInfoPanel);
        add(imageInfoPanel);
    }

    public void setSerie(SeriesGroup newSerie) {
        serieInfoPanel.setSerie(newSerie);
    }

    public void setImage(ImageElement newImage) {
        if (newImage != null) {
            imageInfo = AcquireManager.findByImage(newImage);
            imageInfoPanel.setImageInfo(imageInfo);
        } else {
            imageInfoPanel.setImageInfo(null);
        }

        revalidate();
        repaint();
    }

    public void activate() {

    }

    protected void refreshGUI() {
        globalInfoPanel.update();
        serieInfoPanel.update();
        imageInfoPanel.update();

        revalidate();
        repaint();
    }
}
