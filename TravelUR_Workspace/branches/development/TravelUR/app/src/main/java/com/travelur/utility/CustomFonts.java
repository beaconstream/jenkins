package com.travelur.utility;

import android.graphics.Typeface;

/**
 * Created by Abhijit on 6/28/2017.
 */

public class CustomFonts {

    private Typeface font, font_Raleway_Light, font_Raleway_ExtraLight, font_Raleway_Medium, font_Raleway_Thin, font_OpenSans_Light, font_Raleway_SemiBold;

    public Typeface getFont() {
        return font;
    }

    public void setFont(Typeface font) {
        this.font = font;
    }

    public Typeface getFont_Raleway_Light() {
        return font_Raleway_Light;
    }

    public void setFont_Raleway_Light(Typeface font_Raleway_Light) {
        this.font_Raleway_Light = font_Raleway_Light;
    }

    public Typeface getFont_Raleway_ExtraLight() {
        return font_Raleway_ExtraLight;
    }

    public void setFont_Raleway_ExtraLight(Typeface font_Raleway_ExtraLight) {
        this.font_Raleway_ExtraLight = font_Raleway_ExtraLight;
    }

    public Typeface getFont_Raleway_Medium() {
        return font_Raleway_Medium;
    }

    public void setFont_Raleway_Medium(Typeface font_Raleway_Medium) {
        this.font_Raleway_Medium = font_Raleway_Medium;
    }

    public Typeface getFont_Raleway_Thin() {
        return font_Raleway_Thin;
    }

    public void setFont_Raleway_Thin(Typeface font_Raleway_Thin) {
        this.font_Raleway_Thin = font_Raleway_Thin;
    }

    public Typeface getFont_OpenSans_Light() {
        return font_OpenSans_Light;
    }

    public void setFont_OpenSans_Light(Typeface font_OpenSans_Light) {
        this.font_OpenSans_Light = font_OpenSans_Light;
    }

    public Typeface getFont_Raleway_SemiBold() {
        return font_Raleway_SemiBold;
    }

    public void setFont_Raleway_SemiBold(Typeface font_Raleway_SemiBold) {
        this.font_Raleway_SemiBold = font_Raleway_SemiBold;
    }

    public CustomFonts(Typeface font, Typeface font_Raleway_Light, Typeface font_Raleway_ExtraLight, Typeface font_Raleway_Medium, Typeface font_Raleway_Thin, Typeface font_OpenSans_Light, Typeface font_Raleway_SemiBold) {
        this.font = font;
        this.font_Raleway_Light = font_Raleway_Light;
        this.font_Raleway_ExtraLight = font_Raleway_ExtraLight;
        this.font_Raleway_Medium = font_Raleway_Medium;
        this.font_Raleway_Thin = font_Raleway_Thin;
        this.font_OpenSans_Light = font_OpenSans_Light;
        this.font_Raleway_SemiBold = font_Raleway_SemiBold;
    }
}
