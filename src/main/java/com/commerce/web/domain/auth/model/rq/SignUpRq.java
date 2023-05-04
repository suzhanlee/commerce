package com.commerce.web.domain.auth.model.rq;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import okio.BufferedSink;

@Getter
@AllArgsConstructor
public class SignUpRq extends RequestBody {

    private String username;
    private String email;

    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

    }
}
