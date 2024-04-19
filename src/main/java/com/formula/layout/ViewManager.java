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

import com.formula.layout.components.TopMenu;
import com.formula.layout.components.navbar.NavBar;
import com.formula.layout.view.DashboardView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewManager {
    private NavBar navBar;
    private TopMenu topMenu;

    private DashboardView dashboardView;

    public ViewManager(ApplicationWindow applicationWindow) {
    dashboardView = new DashboardView(applicationWindow);
    }

    public void setup(ApplicationWindow applicationWindow){
        navBar = new NavBar(applicationWindow);
        topMenu = new TopMenu(applicationWindow);
    }
}