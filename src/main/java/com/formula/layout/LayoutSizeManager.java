/*
 * MIT License
 *
 * Copyright (c)2023 Matheus Markies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.formula.layout;

import javafx.geometry.Insets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
public class LayoutSizeManager {
    public static int ReferenceScreenWidth = 1920, ReferenceScreenHeight = 1080;
    public int screenWidth, screenHeight;

    public static double PageDefaultSideOffset = 160.0;
    public static double AnchorPaneDefaultLeftAnchor = 20.0;

    public static int getScreenHeight(){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return gd.getDisplayMode().getHeight();
    }
    public static int getScreenWidth(){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return gd.getDisplayMode().getWidth();
    }
    public static double getScreenAreaRatio(){
        return (double)(ReferenceScreenWidth * ReferenceScreenHeight) /
                (double)(getScreenWidth() * getScreenHeight());
    }
    public static double getInverseScreenAreaRatio(){
        return (double)(1/getScreenAreaRatio());
    }
    public static double getPageSideOffset(){
        return PageDefaultSideOffset/ReferenceScreenWidth * getScreenWidth();
    }
    public static double getAnchorPaneDefaultLeftAnchor(){
        return AnchorPaneDefaultLeftAnchor/ReferenceScreenWidth * getScreenWidth();
    }

    public static double getResizedWidth(double o){
        return o/ReferenceScreenWidth * getScreenWidth();
    }
    public static double getResizedHeight(double o){
        return o/ReferenceScreenHeight * getScreenHeight();
    }
    public static javafx.geometry.Insets getResizedInsert(double top, double right, double left, double bottom){
        return new Insets(
                getResizedHeight(top),
                getResizedWidth(right),
                getResizedWidth(left),
                getResizedHeight(bottom)
        );
    }
}