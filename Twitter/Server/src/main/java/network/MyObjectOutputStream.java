package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.SerializablePermission;

public class MyObjectOutputStream extends ObjectOutputStream {
    public MyObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    public MyObjectOutputStream() throws IOException, SecurityException {
    }

    /**
     * The writeStreamHeader method is provided so subclasses can append or
     * prepend their own header to the stream.  It writes the magic number and
     * version to the stream.
     *
     * @throws IOException if I/O errors occur while writing to the underlying
     *                     stream
     */
    @Override
    protected void writeStreamHeader() throws IOException {

    }
}
