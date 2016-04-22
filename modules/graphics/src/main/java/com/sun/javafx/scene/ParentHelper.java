/*
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.sun.javafx.scene;

import com.sun.javafx.geom.PickRay;
import com.sun.javafx.scene.input.PickResultChooser;
import javafx.scene.Parent;

/**
 * Used to access internal methods of Parent.
 */
public class ParentHelper {

    private static ParentAccessor parentAccessor;

    static {
        forceInit(Parent.class);
    }

    private ParentHelper() {
    }

    public static boolean pickChildrenNode(Parent parent, PickRay pickRay,
            PickResultChooser result) {
        return parentAccessor.pickChildrenNode(parent, pickRay, result);
    }

    public static void setParentAccessor(final ParentAccessor newAccessor) {
        if (parentAccessor != null) {
            throw new IllegalStateException();
        }

        parentAccessor = newAccessor;
    }

    public interface ParentAccessor {
        boolean pickChildrenNode(Parent parent, PickRay pickRay, PickResultChooser result);
    }

    private static void forceInit(final Class<?> classToInit) {
        try {
            Class.forName(classToInit.getName(), true,
                    classToInit.getClassLoader());
        } catch (final ClassNotFoundException e) {
            throw new AssertionError(e);  // Can't happen
        }
    }
}
