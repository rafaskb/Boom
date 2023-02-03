package com.rafaskoberg.boom.util;

import com.badlogic.gdx.Gdx;

public class BoomError {

    public static boolean check(String message) {
        try {
            EFXUtil.checkAlError();
        } catch(Exception e) {
            Gdx.app.error("Boom", message, e);
            return true;
        }
        return false;
    }

}
