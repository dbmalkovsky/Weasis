/*
 * Copyright (c) 2009-2020 Weasis Team and other contributors.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which is available at https://www.eclipse.org/legal/epl-2.0, or the Apache
 * License, Version 2.0 which is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
 */
package org.weasis.base.viewer2d;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public enum ResetTools {
  ALL(Messages.getString("ResetTools.all")),

  WL(Messages.getString("ResetTools.wl")),

  ZOOM(Messages.getString("ResetTools.zoom")),

  ROTATION(Messages.getString("ResetTools.rotation")),

  PAN(Messages.getString("ResetTools.pan"));

  private final String name;

  ResetTools(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  public static JMenu createUnregisteredJMenu() {
    ButtonGroup group = new ButtonGroup();
    JMenu menu = new JMenu(Messages.getString("ResetTools.reset"));
    for (final ResetTools action : values()) {
      final JMenuItem item = new JMenuItem(action.toString());
      item.addActionListener(e -> EventManager.getInstance().reset(action));
      menu.add(item);
      group.add(item);
    }
    return menu;
  }
}
