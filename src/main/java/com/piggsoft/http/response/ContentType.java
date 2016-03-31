package com.piggsoft.http.response;

import org.apache.http.util.CharArrayBuffer;

import java.nio.charset.Charset;

/**
 * <br>Created by fire pigg on 2016/3/30.
 *
 * @author piggsoft@163.com
 */
public class ContentType {

    public static final ContentType APPLICATION_JSON = create(
            "application/json", Charset.forName("UTF-8"));

    private final String mimeType;
    private final Charset charset;

    ContentType(
            final String mimeType,
            final Charset charset) {
        this.mimeType = mimeType;
        this.charset = charset;
    }

    @Override
    public String toString() {
        final CharArrayBuffer buf = new CharArrayBuffer(64);
        buf.append(this.mimeType);
        if (this.charset != null) {
            buf.append("; charset=");
            buf.append(this.charset.name());
        }
        return buf.toString();
    }

    public static ContentType create(final String mimeType, final Charset charset) {
        return new ContentType(mimeType, charset);
    }

    public String getMimeType() {
        return mimeType;
    }

    public Charset getCharset() {
        return charset;
    }
}
