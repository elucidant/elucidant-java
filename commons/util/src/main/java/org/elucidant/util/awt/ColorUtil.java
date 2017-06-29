/**
 *
 * Name:   ColorUtil.java
 * Author: John Domingo
 *
 * This file contains the implementation of the ColorUtil.
 *
 * Copyright 2016 by John Domingo
 *
 * This file is part of some open source application.
 * 
 * Some open source application is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU General Public 
 * License as published by the Free Software Foundation, either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty  of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 * 
 */

package org.elucidant.util.awt;

import java.awt.Color;

/**
 *
 * @author John Domingo
 *
 *
 */

public class ColorUtil
{
   public final static int SHARPBLUE_RED = 0;
   public final static int SHARPBLUE_GREEN = 85;
   public final static int SHARPBLUE_BLUE = 142;
   
   public final static int SHARPYELLOW_RED = 253;
   public final static int SHARPYELLOW_GREEN = 199;
   public final static int SHARPYELLOW_BLUE = 90;
   
   public final static Color WHITE = new Color(255, 255, 255);
   
   public static Color SHARPBLUE = null;
   public static Color SHARPYELLOW = null;
   
   static
   {
      SHARPBLUE = createColorFromRGB(
         SHARPBLUE_RED,
         SHARPBLUE_GREEN,
         SHARPBLUE_BLUE);
      
      SHARPYELLOW = createColorFromRGB(
         SHARPYELLOW_RED,
         SHARPYELLOW_GREEN,
         SHARPYELLOW_BLUE);
   }
   
   /**
    * 
    * Creates a Color from an RGB value.
    *
    * @param r Primitive integer representing the red value of the color to
    *    create.
    * @param g Primitive integer representing the green value of the color to
    *    create.
    * @param b Primitive integer representing the blue value of the color to
    *    create.
    *    
    * @return Color object created.
    *
    */
   
   public static Color createColorFromRGB(final int r, final int g, final int b)
   {
      float[] hsbArray =
         Color.RGBtoHSB(r, g, b, null);
      
      Color color = Color.getHSBColor(hsbArray[0], hsbArray[1], hsbArray[2]);
      
      return color;
   }
}
