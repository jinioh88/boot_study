package com.bootstudy.study.common.file;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
public class EncodeUtils {

    public static String encodeUtf8(String filename) {
        String encoded = null;

        try {
            encoded = URLEncoder.encode(filename, "UTF-8");
        } catch (UnsupportedEncodingException ignore) {
            // should never happens
        }

        return encoded;
    }
}